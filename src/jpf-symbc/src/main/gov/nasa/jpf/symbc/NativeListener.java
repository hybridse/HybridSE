package gov.nasa.jpf.symbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import antlr4.smtlib.SMTLIBv2Lexer;
import antlr4.smtlib.SMTLIBv2Parser;
import antlr4.smtlib.SMTLIBv2Parser.HexadecimalContext;
import antlr4.smtlib.SMTLIBv2Parser.Qual_identiferContext;
import antlr4.smtlib.SMTLIBv2Parser.TermContext;
import choco.cp.solver.constraints.global.tree.structure.internalStructure.graphStructures.algorithms.ArticulationPoints;
import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.PropertyListenerAdapter;
import gov.nasa.jpf.jvm.JVMNativeStackFrame;
import gov.nasa.jpf.jvm.JVMStackFrame;
import gov.nasa.jpf.jvm.bytecode.ARETURN;
import gov.nasa.jpf.jvm.bytecode.EXECUTENATIVE;
import gov.nasa.jpf.jvm.bytecode.IRETURN;
import gov.nasa.jpf.jvm.bytecode.JVMInvokeInstruction;
import gov.nasa.jpf.jvm.bytecode.JVMReturnInstruction;
import gov.nasa.jpf.report.ConsolePublisher;
import gov.nasa.jpf.report.Publisher;
import gov.nasa.jpf.report.PublisherExtension;
import gov.nasa.jpf.symbc.SymbolicListener.MethodSummary;
import gov.nasa.jpf.symbc.bytecode.BytecodeUtils;
import gov.nasa.jpf.symbc.bytecode.INVOKESPECIAL;
import gov.nasa.jpf.symbc.bytecode.INVOKESTATIC;
import gov.nasa.jpf.symbc.bytecode.INVOKEVIRTUAL;
import gov.nasa.jpf.symbc.concolic.PCAnalyzer;
import gov.nasa.jpf.symbc.numeric.BinaryLinearIntegerExpression;
import gov.nasa.jpf.symbc.numeric.Comparator;
import gov.nasa.jpf.symbc.numeric.Constraint;
import gov.nasa.jpf.symbc.numeric.IntegerConstant;
import gov.nasa.jpf.symbc.numeric.IntegerExpression;
import gov.nasa.jpf.symbc.numeric.LinearIntegerConstraint;
import gov.nasa.jpf.symbc.numeric.LogicalORLinearIntegerConstraints;
import gov.nasa.jpf.symbc.numeric.Operator;
import gov.nasa.jpf.symbc.numeric.PCChoiceGenerator;
import gov.nasa.jpf.symbc.numeric.PathCondition;
import gov.nasa.jpf.symbc.numeric.SymbolicConstraintsGeneral;
import gov.nasa.jpf.symbc.numeric.SymbolicInteger;
import gov.nasa.jpf.symbc.string.StringExpression;
import gov.nasa.jpf.symbc.string.SymbolicStringBuilder;
import gov.nasa.jpf.util.LogManager;
import gov.nasa.jpf.util.MethodSpec;
import gov.nasa.jpf.util.Pair;
import gov.nasa.jpf.vm.ChoiceGenerator;
import gov.nasa.jpf.vm.ClassInfo;
import gov.nasa.jpf.vm.ClassLoaderInfo;
import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.Heap;
import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.MethodInfo;
import gov.nasa.jpf.vm.NativeMethodInfo;
import gov.nasa.jpf.vm.NativePeer;
import gov.nasa.jpf.vm.NativeStackFrame;
import gov.nasa.jpf.vm.SkippedMethodInfo;
import gov.nasa.jpf.vm.SkippedNativeMethodInfo;
import gov.nasa.jpf.vm.StackFrame;
import gov.nasa.jpf.vm.ThreadInfo;
import gov.nasa.jpf.vm.Types;
import gov.nasa.jpf.vm.VM;
import main.corana.emulator.semantics.Environment;
import main.corana.external.connector.ArithmeticUtils;
import main.corana.external.connector.SetupJNI;
import main.corana.pojos.BitVec;

public class NativeListener extends PropertyListenerAdapter  {
	private Map<String, MethodSummary> allSummaries;
    private String currentMethodName = "";
    private static String[] skip_spec = null;
    private static boolean skipNatives = false;
	private SetupJNI su;
	private static String outputPath = "";
	Logger currentLog;
	private static boolean isSkip = false;
	
    private static boolean initialized = false;
    private String libraryPath;
    private Logger LOGGER = Logger.getLogger(TaintListener.class.getName());
    private static boolean enterMain = false;
  
    private void init (Config conf){
        if (!initialized){
          skip_spec = conf.getStringArray("nhandler.spec.skip");
          skipNatives = conf.getBoolean("nhandler.skipNative");
          initialized = true;
          libraryPath = conf.getString("librarypath");
          outputPath = conf.getString("output");
      	  File dir = new File(this.libraryPath);
      	  su = new SetupJNI(dir);
        }
      }
    

    @Override
    public void classLoaded (VM vm, ClassInfo ci){
      init(vm.getConfig());
      skipNatives(ci);
      processSkipped(ci);
    }
    
    private void skipNatives (ClassInfo ci){
        MethodInfo[] mth = ci.getDeclaredMethodInfos();
        for (MethodInfo mi : mth){
          if (mi.isNative() && !isHandled(mi)){
            skipUnhandledNative(mi);
          }
        }
      }
    
    private boolean isHandled(MethodInfo mi) {
        NativeMethodInfo nmi = (NativeMethodInfo) mi;
        NativePeer nativePeer = nmi.getNativePeer();

        // check if there is any native peer class associated to the class of this
        // method at all
        if(nativePeer == null) {
          return false;
        }

        Method[] mth = nativePeer.getPeerClass().getMethods();
        for(Method m: mth) {
          String jniName = nmi.getJNIName();
          if(m.getName().equals(jniName) || jniName.contains(m.getName())) {
            return true;
          }
        }

        return false;
      }

    private void processSkipped (ClassInfo ci){
        if (skip_spec != null){
          MethodInfo[] mth = ci.getDeclaredMethodInfos();
      
          for (MethodInfo mi : mth){
            for (String spec : skip_spec){
              MethodSpec ms = MethodSpec.createMethodSpec(spec);
              if (ms.matches(mi)){
                skipMethod(mi);
              }
            }
          }
        }
      }
    
    private void skipMethod (MethodInfo mi){
        MethodInfo new_m = new SkippedMethodInfo(mi);
        ClassInfo ci = mi.getClassInfo();
        ci.putDeclaredMethod(new_m);
      }
    
    private void skipUnhandledNative (MethodInfo mi){
        MethodInfo new_m = new SkippedNativeMethodInfo(mi);
        ClassInfo ci = mi.getClassInfo();
        ci.putDeclaredMethod(new_m);
      }
    
	@Override  
	public void methodEntered (VM vm, ThreadInfo currentThread, MethodInfo enteredMethod) {
		System.out.println("======================================================" ); 
    	System.out.println("enter method " + enteredMethod.getBaseName());
    	   	
		if (enteredMethod.getBaseName().contains(currentThread.getEntryMethod().getBaseName())) {
			enterMain=true;
		
		}
	}
    
	@Override
	  public void executeInstruction(VM vm, ThreadInfo currentThread, Instruction instructionToExecute) {
		if (!enterMain) return;
		Instruction insn = instructionToExecute;
		if (insn != null && !insn.getMethodInfo().getFullName().contains("java.util")) 
			System.out.println(insn.getMnemonic() + " " + insn.getMethodInfo().getFullName());
		
		if (!vm.getSystemState().isIgnored()) {
           ThreadInfo ti = currentThread;
           Config conf = vm.getConfig();
           if (insn instanceof EXECUTENATIVE){ // CHANGE THISSSSS
               NativeMethodInfo mi = (NativeMethodInfo) ((EXECUTENATIVE) insn).getExecutedMethod();
        
               ClassInfo ci = mi.getClassInfo();
               if (mi.getName().equals("checkSelfPermission"))
            	   System.out.println(mi.getName());
               File dir = new File(this.libraryPath);
           	if (isJNIFunction(mi.getName())) {
	             currentThread.skipInstruction(instructionToExecute);
    		} 
           } else if (insn instanceof INVOKEVIRTUAL) {
        	   //System.out.println(insn);
        	   //MethodInfo new_m = new SkippedMethodInfo(insn.getMethodInfo());
        	   if (insn.toString().contains("String.equals")|| insn.toString().contains("org.apache.http.impl.client")) {
        		   currentThread.skipInstruction(instructionToExecute);
        	   }
        	   if (((INVOKEVIRTUAL) insn).getInvokedMethodName().contains("checkSelfPermission") ) {
        		   System.out.println(((INVOKEVIRTUAL) insn).getInvokedMethodName());   
        	   }
        	   
        	  String invokedClass = ((INVOKEVIRTUAL) insn).getInvokedMethodClassName();
        	  if (invokedClass != null) {
        		  if (invokedClass.contains("android.telephony.TelephonyManager")) {
        		  } 
        	  ClassInfo ci = ClassLoaderInfo.getCurrentResolvedClassInfo(((INVOKEVIRTUAL) insn).getInvokedMethodClassName());
        	  ClassLoaderInfo.getCurrentClassLoader(ti).loadClass(ci.getName());
        	  }
        	   //ci.putDeclaredMethod(new_m);
           } else if (insn instanceof ARETURN) {
       	   	Object ret = ((ARETURN) insn).getReturnValue(ti);
       	   	JVMStackFrame frame = (JVMStackFrame) ti.getModifiableTopFrame();
       	   
       	   
       	   	ClassInfo retCi = ClassLoaderInfo.getCurrentResolvedClassInfo(insn.getMethodInfo().getReturnTypeName());
   	   		ElementInfo ei = null;
       	   	
   	   		if (ret == null) {
       	   		ei = ti.getHeap().newObject(retCi, ti);
       	   		frame.pop();
       	   		//if (isTaint) ei.taint = true;
       	   		frame.pushRef(ei.getObjectRef());	
       	   } else {
       		   int retRef = frame.pop();
       		   //if (isTaint) ti.getHeap().get(retRef).taint = true;
       		   frame.pushRef(retRef);
       	   }
          } 
		}
		}
	
	@Override
	public void instructionExecuted(VM vm, ThreadInfo currentThread, Instruction nextInstruction, Instruction executedInstruction) {
		if (nextInstruction == null || executedInstruction == null) return;
		
		if (!vm.getSystemState().isIgnored()) {
			
			Instruction insn = executedInstruction;
            ThreadInfo ti = currentThread;
            Config conf = vm.getConfig();
    		if (insn != null && !insn.getMethodInfo().getFullName().contains("java.util")) {
    			//System.out.println("-" + insn.getLineNumber() + " " + insn.toPostExecString());
			}
    		
            if (insn instanceof JVMInvokeInstruction) {
                JVMInvokeInstruction md = (JVMInvokeInstruction) insn;
                String methodName = md.getInvokedMethodName();
                MethodInfo mi = md.getInvokedMethod();
          
                if (insn.toString().contains("String.equals") || insn.toString().contains("org.apache.http.impl")) {
                	StackFrame top = ti.getTopFrame();
                	Instruction nextPC = top.getPC().getNext();
                    top.setPC(nextPC);
                    currentThread.setNextPC(nextPC);
                    return;
                }  
                if (mi == null) {
                	return;
                }
                ClassInfo ci = mi.getClassInfo();
                String className = ci.getName(); 

                int numberOfArgs = md.getArgumentValues(ti).length;
             
                StackFrame sf = ti.getTopFrame();
                String shortName = methodName;
                String longName = mi.getLongName();
                
                if (ti.getLastInvokedStackFrame() instanceof JVMNativeStackFrame && isJNIFunction(methodName)) { //INVOKENATIVE
                    Map.Entry<Environment, List<String>> returnNative = null;
                    Map.Entry<Environment, List<String>> returnConcreteNative = null;
                    
                    Environment returnConcreteEnv = new Environment();
                    Environment returnEnv = new Environment();
                    List<String> nativePC = new ArrayList();
                    
                    JVMNativeStackFrame nativeStack = (JVMNativeStackFrame) ti.getLastInvokedStackFrame() ;
                    MJIEnv envArgs = (MJIEnv) nativeStack.getArguments()[0];
                    
                    if (isNative(currentThread)) {
                      	Heap heap = vm.getSystemState().getHeap();
                        returnNative = runCorana(md, ti); // Execute CORANA and return env in BitVec
                        returnConcreteNative = runConcreteCorana(md, ti);
                    }
                    
                    List<List<Constraint>> nativeConstraints = new ArrayList<>();
                    IntegerExpression returnSym = null;
                    NativeStackFrame nativeFrame = (NativeStackFrame) ti.getTopFrame();
               	 
               	 	StackFrame top = ti.getTopFrame();
               	 	NativeStackFrame ntop = (NativeStackFrame)top;
               	 	Object   ret = null;
               	 	
	               	 	if (returnConcreteNative != null) {
	                    	 returnConcreteEnv = returnConcreteNative.getKey();
	                                                 
	                    	 MJIEnv   env = ti.getMJIEnv();
	                    	 
	                    	 // get arg types
	                         byte[] argTypes = mi.getArgumentTypes();
	                         String argTypesStr = "";
	                         for (int i = 0; i < argTypes.length; i++) {
	                             argTypesStr = argTypesStr + argTypes[i];
	                             if ((i + 1) < argTypes.length)
	                                 argTypesStr = argTypesStr + ",";
	                         }       
	                    	 ret = new Integer(10);
	                    	 String c = mi.getReturnType();
	                    	 switch (c.charAt(0)) {
		                         case 'B': //"byte";
		                        	 ret = ArithmeticUtils.BitVecToInteger(returnConcreteEnv.register.get('0'));
		                        	 break;
		                         case 'C': //"char";
		                        	 ret = Character.forDigit(ArithmeticUtils.BitVecToInteger(returnConcreteEnv.register.get('0')), 10);
		                        	 break;
		                         case 'D': //"double";
		                        	 ret = ArithmeticUtils.BitVecToDouble(returnConcreteEnv.register.get('0'));
		                        	 break;
		                         case 'F': //"float";
		                        	 ret = ArithmeticUtils.BitVecToDouble(returnConcreteEnv.register.get('0'));
		                        	 break;
		                         case 'I': //"int";
		                        	 ret = ArithmeticUtils.BitVecToInteger(returnConcreteEnv.register.get('0'));
		                        	 break;
		                         case 'L': 
		                        	 ret = ArithmeticUtils.BitVecToInteger(returnConcreteEnv.register.get('0'));
		                        	 break;
		                         case 'V': //"void";
		                        	 break;
		                         case 'Z': //"boolean";
		                        	 ret = ArithmeticUtils.BitVecToInteger(returnConcreteEnv.register.get('0'));
		                        	 break;
	                         }
	                    	 ntop.setReturnValue(ret);
	                    	 //System.out.println("Return value:" + ret);
	                    }
	               	 	
	                    if (returnNative != null) {
	                    	 returnEnv = returnNative.getKey();
		                   	 for (String pc : nativePC) {
		                    	 nativeConstraints.add(BV2LIAConverter.preToInorder(pc));
		                     }
		                    // convert from BitVector theory to LIA
		                     returnSym = BV2LIAConverter.toIntegerExpression(returnEnv.register.get('0'));
		                	
		                	 for (List<Constraint> npc : nativeConstraints)  {
		                		 System.out.println("Native Path Condition: " + nativeConstraints.get(0).toString());
		                	 }
		                	 ntop.setReturnAttr(returnSym);
	                    }
	                   
	                    Instruction nextPC = ntop.getPC().getNext();
	                    top.setPC(nextPC);
	
	                    currentThread.setNextPC(nextPC);
 //                 }
                   
                    ChoiceGenerator<?> cg = vm.getChoiceGenerator();
                    if (!(cg instanceof PCChoiceGenerator)) {
                        ChoiceGenerator<?> prev_cg = cg.getPreviousChoiceGenerator();
                        while (!((prev_cg == null) || (prev_cg instanceof PCChoiceGenerator))) {
                            prev_cg = prev_cg.getPreviousChoiceGenerator();
                        }
                        cg = prev_cg;
                    }	
                    
                    if ((cg instanceof PCChoiceGenerator) && ((PCChoiceGenerator) cg).getCurrentPC() != null) {
                        PathCondition pc = ((PCChoiceGenerator) cg).getCurrentPC();
                        // pc.solve(); //we only solve the pc
                        
                        System.out.println(methodName + " " + numberOfArgs);
                        for (Constraint c : nativeConstraints.get(0)) {
                            pc.appendAllConjuncts(c);
                        }
                        pc.simplify();
                        System.out.println(pc.toString().replace("(null)", ""));
                        if (SymbolicInstructionFactory.concolicMode) { // TODO: cleaner
                            SymbolicConstraintsGeneral solver = new SymbolicConstraintsGeneral();
                            PCAnalyzer pa = new PCAnalyzer();
                            pa.solve(pc, solver);
                        } else
                            pc.solve();
                    	}
                    
                    if (!PathCondition.flagSolved) {
                        return;
                    }
                 } 
            } 
         
            else if (insn instanceof JVMReturnInstruction) {
                MethodInfo mi = insn.getMethodInfo();
                ClassInfo ci = mi.getClassInfo();
                if (null != ci) {	
                    String className = ci.getName();
                    String methodName = mi.getName();
                    String longName = mi.getLongName();
                    int numberOfArgs = mi.getNumberOfArguments();
                   
                    if (((BytecodeUtils.isClassSymbolic(conf, className, mi, methodName))
                            || BytecodeUtils.isMethodSymbolic(conf, mi.getFullName(), numberOfArgs, null))) {
	
                        ChoiceGenerator<?> cg = vm.getChoiceGenerator();
                        if (!(cg instanceof PCChoiceGenerator)) {
                            ChoiceGenerator<?> prev_cg = cg.getPreviousChoiceGenerator();
                            while (!((prev_cg == null) || (prev_cg instanceof PCChoiceGenerator))) {
                                prev_cg = prev_cg.getPreviousChoiceGenerator();
                            }
                            cg = prev_cg;
                        }
                        if ((cg instanceof PCChoiceGenerator) && ((PCChoiceGenerator) cg).getCurrentPC() != null) {
                            PathCondition pc = ((PCChoiceGenerator) cg).getCurrentPC();
                            // pc.solve(); //we only solve the pc
                            if (SymbolicInstructionFactory.concolicMode) { // TODO: cleaner
                                SymbolicConstraintsGeneral solver = new SymbolicConstraintsGeneral();
                                PCAnalyzer pa = new PCAnalyzer();
                                pa.solve(pc, solver);
                            } else
                                pc.solve();
                            
                            if (!PathCondition.flagSolved) {
                                return;
                            } 
                    }
                }
            }
            }
		}
	}
	
	private boolean isNative(ThreadInfo currentThread) {
		if (currentThread.getLastInvokedStackFrame() != null) {
			return currentThread.getLastInvokedStackFrame().isNative();
		}
		return false;
	}
	
	private boolean isJNIFunction(String methodName) {
		String shortName = methodName.contains("(") ? methodName.substring(0, methodName.indexOf("(")) : methodName;
		return su.getNativeFunction().contains(shortName);
	}
	
	public Map.Entry<Environment, List<String>> runCorana(JVMInvokeInstruction jniMethod, ThreadInfo currentThread) {

		String methodName = jniMethod.getInvokedMethod().getName();
		if (!su.getNativeFunction().contains(methodName)) {
			return null;
		}
		
		su.setLog(true);
	
		long startTime = System.nanoTime();
        Environment initEnv = new Environment();
        ThreadInfo ti = currentThread.getCurrentThread();
        MethodInfo mi = jniMethod.getInvokedMethod();
        
        int numberOfArgs = mi.getArgumentsSize();
        JVMNativeStackFrame nativeStack = (JVMNativeStackFrame) ti.getLastInvokedStackFrame();
        MJIEnv envArgs = (MJIEnv) nativeStack.getArguments()[0];
        
        // sym attrs
        Object[] symArgs = nativeStack.getCallerFrame().getSlotAttrs();
        int localStackBase = nativeStack.getCallerFrame().getLocalVariableCount();
        
        if (symArgs == null && numberOfArgs != 0) return null; // No symbolic args in SPF
        
        if (symArgs != null ) { //void
            //initEnv.register.set('0', ArithmeticUtils.IntegerToBitVec(10));
            for (int i = 1; i < numberOfArgs+1; i++) {
            	Object stackEle = nativeStack.getArguments()[i];
            	Object symElement = symArgs[localStackBase++];
            	if (i < 4) {
                	if (symElement != null) {
                		// Change from LNA to BitVec
                		if (symElement instanceof SymbolicStringBuilder && ((SymbolicStringBuilder) symElement).getstr() == null) {
                			System.out.println("JPF Argument is Symbolic String");
                		} else initEnv.register.set(Character.forDigit(i,  10), new BitVec(symElement.toString()));
                	}
            	} else {
                	if (symElement != null) {
                		// Change from LNA to BitVec
                		BitVec val = new BitVec(symElement.toString());
                	    initEnv.stacks.push(val);
                	}
            	}
            }
        }
 
        Map.Entry<Environment, List<String>> afterEnv = null;
        try {
        	afterEnv = su.execJNI(jniMethod.getInvokedMethod().getName(), initEnv);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return afterEnv;
        
	}
	
	public Map.Entry<Environment, List<String>> runConcreteCorana(JVMInvokeInstruction jniMethod, ThreadInfo currentThread) {
		
		String methodName = jniMethod.getInvokedMethod().getName();
		String shortName = methodName.contains("(") ? methodName.substring(0, methodName.indexOf("(")) : methodName;
		if (!su.getNativeFunction().contains(methodName)) {
			return null;
		}
		su.setLog(true);
		long startTime = System.nanoTime();
     
        Environment initEnv = new Environment();
        ThreadInfo ti = currentThread.getCurrentThread();
        
        MethodInfo mi = jniMethod.getInvokedMethod();
       
        JVMNativeStackFrame nativeStack = (JVMNativeStackFrame) ti.getLastInvokedStackFrame();
        MJIEnv envArgs = (MJIEnv) nativeStack.getArguments()[0];
        
        // sym attrs
        int numberOfArgs = nativeStack.getArguments().length-1;
        Object[] arguments = nativeStack.getArguments();
        byte[] argsTypes = mi.getArgumentTypes(); // without MJIEnv and this
        int type_counter;
        if (arguments != null) { //void
            initEnv.register.set('0', ArithmeticUtils.IntegerToBitVec((Integer) envArgs.hashCode()));
            type_counter = (numberOfArgs > argsTypes.length) ? -1 : 0; // 2nd args is 'this' ref
           
            for (int i = 1; i < arguments.length; i++) {
            	System.out.println("JPF Argument: " + arguments[i].toString());
            	BitVec val = fromJPFArgument(arguments[i], (type_counter < 0) ? -1 : argsTypes[type_counter++]);
            	if (i < 4) {
            		initEnv.register.set(Character.forDigit(i,  10), val);
            	} else {	
        	        initEnv.stacks.push(val);
            	}
            }
        }
        
        System.out.print(initEnv.toString());
        Map.Entry<Environment, List<String>> afterEnv = null;
        try {
        	afterEnv = su.execJNI(mi.getName(), initEnv);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return afterEnv;
	}
	
	private BitVec fromJPFArgument(Object arg, int... type) {
		BitVec res = null;
		int t_type = (type.length > 0) ? type[0]: -1;
		if (arg instanceof Integer || t_type == Types.T_INT) {
			res = ArithmeticUtils.IntegerToBitVec((Integer) arg);
		} else if (arg instanceof Double || t_type == Types.T_DOUBLE) {
			res = ArithmeticUtils.DoubleToBitVec((Double) arg);
		} 
		// add more
		return res;
	}
	

	
	protected class MethodSummary {
        private String methodName = "";
        private String argTypes = "";
        private String argValues = "";
        private String symValues = "";
        private Vector<Pair> pathConditions;

        public MethodSummary() {
            pathConditions = new Vector<Pair>();
        }

        public void setMethodName(String mName) {
            this.methodName = mName;
        }

        public String getMethodName() {
            return this.methodName;
        }

        public void setArgTypes(String args) {
            this.argTypes = args;
        }

        public String getArgTypes() {
            return this.argTypes;
        }

        public void setArgValues(String vals) {
            this.argValues = vals;
        }

        public String getArgValues() {
            return this.argValues;
        }

        public void setSymValues(String sym) {
            this.symValues = sym;
        }

        public String getSymValues() {
            return this.symValues;
        }

        public void addPathCondition(Pair pc) {
            pathConditions.add(pc);
        }

        public Vector<Pair> getPathConditions() {
            return this.pathConditions;
        }

    }
}