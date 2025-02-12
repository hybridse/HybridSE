package gov.nasa.jpf.symbc;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Formatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.PropertyListenerAdapter;
import gov.nasa.jpf.jvm.JVMNativeStackFrame;
import gov.nasa.jpf.jvm.JVMStackFrame;
import gov.nasa.jpf.jvm.bytecode.AASTORE;
import gov.nasa.jpf.jvm.bytecode.ALOAD;
import gov.nasa.jpf.jvm.bytecode.ARETURN;
import gov.nasa.jpf.jvm.bytecode.ARRAYLENGTH;
import gov.nasa.jpf.jvm.bytecode.ATHROW;
import gov.nasa.jpf.jvm.bytecode.CHECKCAST;
import gov.nasa.jpf.jvm.bytecode.EXECUTENATIVE;
import gov.nasa.jpf.jvm.bytecode.ICONST;
import gov.nasa.jpf.jvm.bytecode.IFEQ;
import gov.nasa.jpf.jvm.bytecode.INVOKECLINIT;
import gov.nasa.jpf.jvm.bytecode.INVOKEINTERFACE;
import gov.nasa.jpf.jvm.bytecode.INVOKEVIRTUAL;
import gov.nasa.jpf.jvm.bytecode.IRETURN;
import gov.nasa.jpf.jvm.bytecode.IfInstruction;
import gov.nasa.jpf.jvm.bytecode.JVMInvokeInstruction;
import gov.nasa.jpf.jvm.bytecode.LSTORE;
import gov.nasa.jpf.jvm.bytecode.NATIVERETURN;
import gov.nasa.jpf.jvm.bytecode.PUTFIELD;
import gov.nasa.jpf.jvm.bytecode.RETURN;
import gov.nasa.jpf.symbc.bytecode.GETFIELD;
import gov.nasa.jpf.symbc.bytecode.IFNONNULL;
import gov.nasa.jpf.symbc.bytecode.INVOKESPECIAL;
import gov.nasa.jpf.symbc.bytecode.INVOKESTATIC;
import gov.nasa.jpf.symbc.numeric.IntegerExpression;
import gov.nasa.jpf.symbc.string.StringExpression;
import gov.nasa.jpf.symbc.string.SymbolicStringBuilder;
import gov.nasa.jpf.util.LogManager;
import gov.nasa.jpf.util.MethodSpec;
import gov.nasa.jpf.vm.ArrayFields;
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
import gov.nasa.jpf.vm.ReferenceArrayFields;
import gov.nasa.jpf.vm.SkippedMethodInfo;
import gov.nasa.jpf.vm.SkippedNativeMethodInfo;
import gov.nasa.jpf.vm.StackFrame;
import gov.nasa.jpf.vm.ThreadInfo;
import gov.nasa.jpf.vm.Types;
import gov.nasa.jpf.vm.VM;
import main.corana.emulator.semantics.Environment;
import main.corana.emulator.semantics.Memory;
import main.corana.emulator.taint.TaintModel;
import main.corana.external.connector.ArithmeticUtils;
import main.corana.external.connector.SetupJNI;
import main.corana.pojos.BitVec;
import main.corana.utils.Arithmetic;

public class TaintListener extends PropertyListenerAdapter {
	private static boolean  enterMain = false;
    private static String[] skip_spec = null;
    private static boolean  skipNatives = false;
	private static boolean  isSkip = false;
    private static String   outputPath = "";
    private static boolean  initialized = false;
    
	private Logger LOGGER = Logger.getLogger(TaintListener.class.getName() );
    private String currentMethodName = "";
	Logger  currentLog;
	private SetupJNI su;
    private String libraryPath;
    
	private static String[] delegate_spec = null;
	private static String[] delegateNative_spec = null;
	private static String[] filter_spec = null;
	private static boolean  delegateUnhandledNatives = false;
	private static HashMap<Integer, String> nativeData = new HashMap();
	private static ArrayList<String> sources = null;
	private static ArrayList<String> sinks = null;
	private static boolean is_strict = true;
    
	class LogFormatter extends Formatter {
	    @Override
	    public String format(LogRecord record) {
	        StringBuilder builder = new StringBuilder();
	        builder.append(record.getLevel() + ": ");
	        builder.append(formatMessage(record));
	        // builder.append(System.lineSeparator());
	        builder.append(System.getProperty("line.separator"));
	        return builder.toString();
	    }
	}
	
	private void init (Config conf) {
	        if (!initialized){
	        	 initialized = true;
	        	 // Set up the logging handler
	        	 Handler handler = new ConsoleHandler();
	             LOGGER.addHandler(handler);
		       	 Formatter formatter = new LogFormatter();  
		       	 handler.setFormatter(formatter);  
		       	  
		       	 LOGGER.setLevel(Level.WARNING);
		       	 LOGGER.setUseParentHandlers(false);
		       	 LOGGER = LogManager.getLogger("gov.nasa.jpf");
		       	 
		       	 // Set up config file
		       	 skip_spec = conf.getStringArray("nhandler.spec.skip");
		         skipNatives = conf.getBoolean("nhandler.skipNative");
		         initialized = true;
		         libraryPath = conf.getString("librarypath");
		         delegate_spec = conf.getStringArray("nhandler.spec.delegate");
			     delegateNative_spec = conf.getStringArray("nhandler.spec.delegateNative");
			     filter_spec = conf.getStringArray("nhandler.spec.filter");
			     delegateUnhandledNatives = conf.getBoolean("nhandler.delegateUnhandledNative");  
			     
			     File dir = new File(this.libraryPath);
			     su = new SetupJNI(dir);
			     sources = readListFromFile(System.getProperty("user.dir") + "/lib/sources.txt");
			     sinks = readListFromFile(System.getProperty("user.dir") + "/lib/sinks.txt");
	        }
	      }

	  @Override
	  public void classLoaded (VM vm, ClassInfo ci){
	    init(vm.getConfig());
	    // processNatives(ci);
	    // processDelegated(ci);
	    // processNativeDelegated(ci);
	    skipNatives(ci);
	    processSkipped(ci);
	  } 
	  
	  private void skipNatives (ClassInfo ci) {
	    MethodInfo[] mth = ci.getDeclaredMethodInfos();
	    for (MethodInfo mi : mth) {
	      if (mi.isNative() && !isHandled(mi) && isAllowed(mi) && !isFiltered(mi)){
	        skipUnhandledNative(mi);
	      } 
	    }
	  }
	  
	  @Override
	  public void exceptionThrown (VM vm, ThreadInfo currentThread, ElementInfo thrownException) {
		  //System.out.println();
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

	  // We do not allow user to delegate or skip the methods of certain classes that are
	  // subjected to jpf-nhandler limitations.
	  String[] builtinFiltered = {"	.ClassLoader.*"};
	  
	  private boolean isAllowed (MethodInfo mi){
	    for(String spec : builtinFiltered){
	      MethodSpec ms = MethodSpec.createMethodSpec(spec);
	      if (ms.matches(mi)){ 
	        return false; 
	      }
	    }
	    return true;
	  }

	  private boolean isFiltered (MethodInfo mi) {
	    if (filter_spec != null) {
	      for (String spec : filter_spec) {
	        MethodSpec ms = MethodSpec.createMethodSpec(spec);
	        if (ms.matches(mi)){ 
	          return true; 
	        }
	      }
	    }
	    return false;
	  }

	  private void processSkipped (ClassInfo ci) {
	    if (skip_spec != null) {
	      MethodInfo[] mth = ci.getDeclaredMethodInfos();
	      for (MethodInfo mi : mth) {
	        for (String spec : skip_spec) {
	          MethodSpec ms = MethodSpec.createMethodSpec(spec);
	          if (ms.matches(mi)) {
	            skipMethod(mi);
	          }
	        }
	      }
	    }
	  }

	  private void skipUnhandledNative (MethodInfo mi) {
	    MethodInfo new_m = new SkippedNativeMethodInfo(mi);	
	    ClassInfo ci = mi.getClassInfo();
	    ci.putDeclaredMethod(new_m);
	  }

	  private void skipMethod (MethodInfo mi){
	    MethodInfo new_m = new SkippedMethodInfo(mi);
	    ClassInfo ci = mi.getClassInfo();
	    ci.putDeclaredMethod(new_m);
	  }
	  
	@Override  
	public void methodEntered (VM vm, ThreadInfo currentThread, MethodInfo enteredMethod) {
//		System.out.println("======================================================" ); 
//    	System.out.println("enter method " + enteredMethod.getBaseName());	
		if (enteredMethod.getBaseName().contains(currentThread.getEntryMethod().getBaseName())) {
			enterMain=true;
			LOGGER.log(Level.WARNING, enteredMethod.getBaseName());
		}
	}
	
	@Override
	public void executeInstruction (VM vm, ThreadInfo currentThread, Instruction instructionToExecute) {
		Instruction insn = instructionToExecute;
		if (!enterMain) return;

		if (insn instanceof JVMInvokeInstruction) {
			LOGGER.warning(insn.getLineNumber() + " " + insn.getMnemonic() + " " + ((JVMInvokeInstruction) insn).getInvokedMethodClassName() + "." + ((JVMInvokeInstruction) insn).getInvokedMethodName() );
			//System.out.println(insn.getMnemonic() + " " + ((JVMInvokeInstruction) insn).getInvokedMethodClassName() + "." + ((JVMInvokeInstruction) insn).getInvokedMethodName());
		} else 
			LOGGER.warning(insn.getLineNumber() + " " + insn.getMnemonic() + " " + insn.getMethodInfo().getFullName());
			//System.out.println(insn.getMnemonic() + " " + insn.getMethodInfo().getFullName());
		
		if (!vm.getSystemState().isIgnored()) {
           ThreadInfo ti = currentThread;
           Config conf = vm.getConfig();
           if (insn instanceof INVOKEVIRTUAL) {
        	   
        	   if (insn.toString().contains("org.apache.http")
        			   //|| insn.toString().contains("java.lang.String")
        			   || insn.toString().contains("android.widget") || insn.toString().contains("android.content")
        			   || insn.toString().contains("android.view.Window") || insn.toString().contains("setContentView")
        			   || insn.toString().contains("android.os") 
        			   || insn.toString().contains("java.io.File")
        			   //|| insn.toString().contains("java.lang.StringBuilder") 
        			   || insn.toString().contains("android.net.")
        			   || (insn.toString().contains("java.lang.System") && !insn.toString().contains("arraycopy"))
        			   || insn.toString().contains("java.lang.Object.toString")
        			   || insn.toString().contains("android.location.LocationManager.requestLocationUpdates")
        			   || insn.toString().contains("android.app.ActivityManager")
        			   || insn.toString().contains("setTitle")
        			   || insn.toString().contains("java.nio.charset.Charset")
        			   || insn.toString().contains("java.util.Properties")
        			   || insn.toString().contains("java.io.InputStream")
        			   || insn.toString().contains("android.content.pm.PackageManager.")
        				 || insn.toString().contains("org.apache.http.impl.")
        				 || insn.toString().contains("sun.misc.Unsafe.")
        		) {
        		   currentThread.skipInstruction(instructionToExecute);
        	   }
        	
        	  String invokedClass = ((INVOKEVIRTUAL) insn).getInvokedMethodClassName();
        	  if (invokedClass != null) {
        	  ClassInfo ci = ClassLoaderInfo.getCurrentResolvedClassInfo(((INVOKEVIRTUAL) insn).getInvokedMethodClassName());
        	  ClassLoaderInfo.getCurrentClassLoader(ti).loadClass(ci.getName());
        	  // CHECK if native function is invoke from invokevirtual
        	  if (isJNIFunction(((INVOKEVIRTUAL) insn).getInvokedMethodName())) {
        		  currentThread.skipInstruction(instructionToExecute);
        	  } else if (insn.toString().contains("android.telephony.TelephonyManager")) {
        		  currentThread.skipInstruction(instructionToExecute);
        	  } else if (insn.toString().contains("checkSelfPermission")) {
        		  currentThread.skipInstruction(instructionToExecute);
        	  } else if (insn.toString().contains("android.content.Intent.getExtras")) {
        		  currentThread.skipInstruction(instructionToExecute);
        	  } 
        	  else if (((INVOKEVIRTUAL) insn).getInvokedMethod() == null) {
        		  currentThread.skipInstruction(instructionToExecute);
        	  }
        	  // Check if is source
        	  for (String src : sources) {
	    			   if (insn.toString().contains(src)) {
	    				  LOGGER.warning("SOURCE " + invokedClass);
	    				  //currentThread.skipInstruction(instructionToExecute);
	    			   }
	    		   }
        	  }
           } else if (insn instanceof INVOKESPECIAL) {
        	   if (insn.toString().contains("javax.crypto")
        			   || insn.toString().contains("android.os.AsyncTask.")
        			   || insn.toString().contains("org.apache.http.impl.client.AbstractHttpClient.")
        			   ) {
        		   currentThread.skipInstruction(instructionToExecute);
        	   }
        	 	   	
           } else if (insn instanceof INVOKECLINIT) {
        	   if (insn.toString().contains("android.os.AsyncTask.")) {
        		   currentThread.skipInstruction(instructionToExecute);
        	   }
           }
           else if (insn instanceof INVOKEINTERFACE) {
        	   if (insn.toString().contains("org.apache.http") 
        			   //|| insn.toString().contains("java.lang.String.") 
        			   || insn.toString().contains("android.widget.TextView") 
        			   || insn.toString().contains("SharedPreferences.edit")
        			   || insn.toString().contains("java.util.concurrent.")
        			   || insn.toString().contains("libcore.io.")
        			   ) {
          		   currentThread.skipInstruction(instructionToExecute);
          	   } else if (insn.toString().contains("SharedPreferences.getLong")
          			   || (insn.toString().contains("SharedPreferences"))) {
          		  currentThread.skipInstruction(instructionToExecute);
          	   } else if (((INVOKEINTERFACE) insn).getInvokedMethod() == null) {
          		 currentThread.skipInstruction(instructionToExecute);
          	   }
           } 
           else if(insn instanceof INVOKESTATIC) {
        	   if (insn.toString().contains("java.lang.System.load")) {
        		   currentThread.getCallerStackFrame();
        	   }
        	   if (insn.toString().contains("java.lang.String.format")
        			   || ( insn.toString().contains("java.lang.System") && !insn.toString().contains("arraycopy"))
        			   || insn.toString().contains("java.io.File.fixSlashes")
        			   || insn.toString().contains("dalvik.system")
        			   || insn.toString().contains("android.os.Build.getString")
        			   || insn.toString().contains("android.os.Build.getLong")
        			   || insn.toString().contains("android.os.SystemProperties.getInt")
        			   || insn.toString().contains("android.os.SystemProperties.getLong")
        			   ) {
        		   currentThread.skipInstruction(instructionToExecute);
        	   } else {
        		   String invokedMethodFull = ((INVOKESTATIC) insn).getInvokedMethod().getFullName();
        		   String invokedMethod = ((INVOKESTATIC) insn).getInvokedMethodName();
        		   // CHECK if native function is invoke from invokestatic
     			  if (isJNIFunction(((INVOKESTATIC) insn).getInvokedMethodName())) {
     	  				currentThread.skipInstruction(instructionToExecute);
     	  			  }
        	   }
           } else if (insn instanceof EXECUTENATIVE) { 
        	   // SKIP if is native function in .so
  				NativeMethodInfo mi = (NativeMethodInfo) ((EXECUTENATIVE) insn).getExecutedMethod();
             	if (isJNIFunction(mi.getName())) {
   	            currentThread.skipInstruction(instructionToExecute);
             	}
           } else if (insn instanceof NATIVERETURN) {
        	   NativeStackFrame frame = (NativeStackFrame) ti.getModifiableTopFrame();
        	   Object ret = frame.getReturnValue();
       
        	   ClassInfo retCi = ClassLoaderInfo.getCurrentResolvedClassInfo(insn.getMethodInfo().getReturnTypeName());
        	   if (insn.toString().contains("java.lang.System.currentTimeMillis")) {
        		   //System.out.println();
        	   	}
        	   
        	   if (ret == null || (ret.equals(new Integer(0))) && !retCi.getName().contains("int")) {
	    		   if (insn.getMethodInfo().getName().contains("getSystemService")) { // Skip executing getSystemService
	    			   ClassInfo telephoneCi = ClassLoaderInfo.getCurrentResolvedClassInfo("android/telephony/TelephonyManager");
	    			   processSkipped(telephoneCi);
//	    			   ElementInfo ei = ti.getHeap().newObject(telephoneCi, ti);
//	    			   frame.setReturnValue(ei.getObjectRef());
	    		   } 
	    		    else if (insn.getMethodInfo().getName().contains("getSharedPreferences")) {
	    			   ClassInfo telephoneCi = ClassLoaderInfo.getCurrentResolvedClassInfo("android/content/SharedPreferences");
	    			   processSkipped(telephoneCi);
	    			   ElementInfo ei = ti.getHeap().newObject(telephoneCi, ti);
	    			   frame.setReturnValue(ei.getObjectRef());
	    		   } else if (insn.getMethodInfo().getName().contains("arraycopy")) {
	    			   //void arraycopy(Object src, int srcIndex, Object dest, int destIndex, int len)
	    			   // MJIEnv, this, ...
	    			   Object[] args = frame.getArguments();
	    			   ElementInfo src = ti.getHeap().get((Integer) args[2]);
	    			   ElementInfo dest = ti.getHeap().get((Integer) args[4]);
	    			   dest.arrayCopy(src, 0, 0, (Integer) args[6]);
	    		   }
	    		   else {
	    			   if (retCi.getName() == "void") retCi = null;
	    			   
	    			   if (retCi != null) {
	    				   ElementInfo ei;
	    				   if (retCi.getName().contains("[")) {
	 	    				 ei = ti.getHeap().newArray(Types.getArrayElementType(insn.getMethodInfo().getReturnType()), 0, ti);
	 	    			   } else {
	 	    				   ei = ti.getHeap().newObject(retCi, ti);
	 	    			   }
	    				   for (String src : sources) {
		    			   if (insn.getMethodInfo().getName().contains(src)) {
		    				  ei.taint();
		    				  frame.taint = true;
		    				  LOGGER.warning("SOURCE " + insn.getMethodInfo().getName());
		    				  //frame.setReturnValue(ei.getObjectRef());
		    			   }
		    			   }
	    			   frame.setReturnValue(ei.getObjectRef());
	    			   // frame.setReturnValue(792);
	    			   }
	    		   }
	    		   ((NATIVERETURN) insn).setReturnFrame(frame);
    		   }
        	   for (String sink : sinks) {
        		   if (insn.getMethodInfo().getBaseName().contains(sink)) {
        			   if (checkSink(frame, ti)) LOGGER.warning("SINK " + insn.getMethodInfo().getBaseName());
        	   }
        	   }
           } else if (insn instanceof ARETURN) {
        	   	Object ret = ((ARETURN) insn).getReturnValue(ti);
        	   	JVMStackFrame frame = (JVMStackFrame) ti.getModifiableTopFrame();
        	   	boolean isTaint = isTaintedParameters(frame, ti);
        	   
        	   	ClassInfo retCi = ClassLoaderInfo.getCurrentResolvedClassInfo(insn.getMethodInfo().getReturnTypeName());
    	   		ElementInfo ei = null;
    	  		
    	   		
    	   		if (ret == null) {
    	   			if (insn.getMethodInfo().getReturnTypeName().contains("[")) {
       				 ei = ti.getHeap().newArray(Types.getArrayElementType(insn.getMethodInfo().getReturnType()), 0, ti);
          	   		} else {
       				   ei = ti.getHeap().newObject(retCi, ti);
       			   }
        	   		frame.pop();
        	   		if (isTaint) ei.taint = true;
        	   		frame.pushRef(ei.getObjectRef());	
        	   } else {
        		   
	    			   int retRef = frame.pop();
	        		   if (isTaint) ti.getHeap().get(retRef).taint = true;
	        		   frame.pushRef(retRef);
        	   }
           }  else if (insn instanceof RETURN) {
        	   	Object ret = ((RETURN) insn).getReturnValue(ti);
	       	   	JVMStackFrame frame = (JVMStackFrame) ti.getModifiableTopFrame();
	       	   	boolean isTaint = isTaintedParameters(frame, ti);
	       	   
	       	   	ClassInfo retCi = ClassLoaderInfo.getCurrentResolvedClassInfo(insn.getMethodInfo().getReturnTypeName());
	   	   		ElementInfo ei = null;
	       	   	
	   	   		if (ret == null) {
		   	   		if (insn.getMethodInfo().getReturnTypeName().contains("[")) {
	   				 ei = ti.getHeap().newArray(Types.getArrayElementType(insn.getMethodInfo().getReturnType()), 0, ti);
	      	   		} else {
	   				   ei = ti.getHeap().newObject(retCi, ti);
	   			   }
	       	   	
	       	   		if (frame.getTopPos() >= 0)  {
	       	   		frame.pop();
	       	   		if (isTaint) ei.taint = true;
	       	   		frame.push(ei.getObjectRef());
	       	   		}
	       	   } 
           }
           else if (insn instanceof CHECKCAST) {

           } else if (insn instanceof IfInstruction) {

           } else if (insn instanceof GETFIELD) {
        	   StackFrame frame = ti.getModifiableTopFrame();
        	   int objRef = frame.peek(); // don't pop 
        	    if (objRef == MJIEnv.NULL) {
        	    	currentThread.skipInstruction(instructionToExecute);        	    
        	    	}
        	    
           } else if (insn instanceof IRETURN) {
         	
           } else if (insn instanceof ATHROW) {
        	   currentThread.skipInstruction(instructionToExecute);
           } else if (insn instanceof IFNONNULL) {
           } else if (insn instanceof ARRAYLENGTH) {
           } 
           }
	}
	
	public TaintModel runVirtualCorana (JVMInvokeInstruction jniMethod, ThreadInfo currentThread) {
		String signature = jniMethod.getInvokedMethodName();
		String methodName = signature.substring(0, signature.indexOf('('));
		if (!isJNIFunction(methodName)) {
			return null;
		}
		su.setLog(true);
		long startTime = System.nanoTime();
        Environment initEnv = new Environment();
        
        TaintModel afterEnv = taintParamsInvokeVirtual(jniMethod, currentThread);
       
        return afterEnv;
	}
	
	public TaintModel taintParamsInvokeVirtual(JVMInvokeInstruction jniMethod, ThreadInfo currentThread) {
		ThreadInfo ti = currentThread.getCurrentThread();
		StackFrame stack = ti.getLastInvokedStackFrame();
		MethodInfo mi = jniMethod.getInvokedMethod();
		
		int numberOfArgs = (mi != null) ? mi.getArgumentsSize() : 0;
	    JVMStackFrame nativeStack = (JVMStackFrame) ti.getLastInvokedStackFrame();
	
	    TaintModel tm = new TaintModel();
	    
	    // sym attrs
	    Object[] symArgs = nativeStack.getCallerFrame().getSlotAttrs();
	    int localStackBase = nativeStack.getLocalVariableCount();
	    int top =  nativeStack.getTopPos();
	    byte[] argsTypes; // without MJIEnv and this
	    Object[] arguments;
	    
	    if (mi != null) { 
	    	argsTypes = mi.getArgumentTypes() ;
	    	arguments = nativeStack.getCallArguments(ti) ;
	    } else {
	    	argsTypes = new byte[localStackBase - 2];
	    	arguments = new Object[localStackBase - 2]; // without MJIEnv and this
	    	for (int i = 0; i < localStackBase - 2; i++) {
	    		int slot = nativeStack.getSlot(top - localStackBase + 2 + i + 1);
	    		if (ti.getHeap().get(slot) != null) {
	    			arguments[i] = ti.getHeap().get(slot);
	    			argsTypes[i] = Types.T_REFERENCE;
	    		} else {
	    			arguments[i] = slot;
	    			argsTypes[i] = Types.T_INT;
	    		}
	    	}		
	    }

        Environment initEnv = new Environment();
        
        
        int type_counter;
	    if (arguments != null) { //void
	            type_counter = (numberOfArgs > argsTypes.length) ? -1 : 0; // 2nd args is 'this' ref
	           
	            for (int i = 0; i < arguments.length; i++) {
	            	System.out.println("JPF Argument: " + arguments[i].toString());
	            	// First arguments start from R2
	            	Character r = Character.forDigit(i + 2, 10);
	            	
	            	if (argsTypes[i] == Types.T_ARRAY) {
	            		ElementInfo ei = (ElementInfo) arguments[i];
	            		String offset = Arithmetic.intToHex(ei.getObjectRef());	            		
	                    
	            		if (ei.getArrayFields() instanceof gov.nasa.jpf.vm.ReferenceArrayFields) {
	            			ReferenceArrayFields fields = (ReferenceArrayFields) ei.getArrayFields();
	            			String[] strArr = new String[fields.arrayLength()];
		            		int[] intArr = new int[fields.arrayLength()];
		                    boolean[] taintArr = new boolean[fields.arrayLength()];
	            			
	            			for (int field_index = 0; field_index < fields.arrayLength(); field_index ++) {
	            				int item = fields.asReferenceArray()[field_index];
	            				if (item != 0) {
	            					// for each ref ele in the array
	            					ElementInfo itemEi = ti.getHeap().get(item);
	            					if (itemEi.getType().contains("java.lang.String")) {
	            						strArr[field_index] = new String(itemEi.getStringChars());
	            					} else { // other reference types
	            						strArr[field_index] = new String(itemEi.toString());
	            					}
	            					taintArr[field_index] = itemEi.taint;
	            				} else {
	            					strArr[field_index] = "sym";
	            					taintArr[field_index] = false;
	            				}
	            			}
	            			
	            		initEnv.register.set(r, ArithmeticUtils.IntegerToBitVec(ei.getObjectRef()));	
	            		tm.register.regs.put(r, ei.taint);
	            		tm.allocateMemory(offset, strArr, taintArr);
	            		}
	            	} else if (argsTypes[i] == Types.T_REFERENCE) {
	            		ElementInfo ei = (ElementInfo) arguments[i];
	            		ei.taint();
	            		String offset = Arithmetic.intToHex(ei.getObjectRef());
	            		tm.register.regs.put(r, ei.taint);
	            		tm.allocateMemory(offset, new String[] {ei.toString()}, new boolean[] {ei.taint});
	            		BitVec val = new BitVec(offset);
	            		val.taint();
	            		initEnv.register.set(r, val);
	            	}
	            	else {
	            		BitVec val = LIA2BVConverter.fromJPFArgument(arguments[i], (type_counter < 0) ? -1 : argsTypes[type_counter++]);
	                	if (i < 4) {
	                		initEnv.register.set(r, val);
	                		tm.register.regs.put(r, false);
	                	} else {	
	            	        initEnv.stacks.push(val);
	                	}
	            	}
	            }
	        }
		try {
			String nativeName = (jniMethod.getInvokedMethod() != null) ? jniMethod.getInvokedMethod().getName() :
				jniMethod.getInvokedMethodName().substring(0, jniMethod.getInvokedMethodName().indexOf('('));
			Map.Entry<Environment, List<String>> result = su.taintJNI(nativeName, initEnv, tm);
			
			if (result.getKey().register.get('0').taint || Memory.get(result.getKey().register.get('0').getSym()).taint) {
				tm.register.taint('0');
			}
	     } catch (Exception e) { 
	        e.printStackTrace();
	     }
		 return tm;
	}
	
	public TaintModel runCorana (JVMInvokeInstruction jniMethod, ThreadInfo currentThread) {	
		String methodName = jniMethod.getInvokedMethod().getName();
		if (!su.getNativeFunction().contains(methodName)) {
			return null;
		}
		
		su.setLog(true);
	
		long startTime = System.nanoTime();
        Environment initEnv = new Environment();
        ThreadInfo ti = currentThread.getCurrentThread();
   
        TaintModel afterEnv = null;
        nativeData.put(792, "#x6c616e69");
        try {
        	// DUMMY RESULT for Testing
        	afterEnv = new TaintModel();
        	afterEnv.register.taint('0');
        	afterEnv.register.taint('1');
        	
        	ti.getHeap().get(792).taint = true; 
        	// afterEnv = su.taintJNI(jniMethod.getInvokedMethod().getName(), initEnv, tm);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return afterEnv;
	}
	
	private boolean isNative (ThreadInfo currentThread) {
		if (currentThread.getLastInvokedStackFrame() != null) {
			return currentThread.getLastInvokedStackFrame().isNative();
		}
		return false;
	}
	
	private boolean isJNIFunction (String methodName) {
		if (su.getNativeFunction() == null) return false;
		String shortName = methodName.contains("(") ? methodName.substring(0, methodName.indexOf("(")) : methodName;
		for (String func: su.getNativeFunction()) {
			if (func.contains(shortName) && shortName.length() > 1) {
				return true; 
			}
		}
		return false;
	}
	
	@Override
	public void instructionExecuted(VM vm, ThreadInfo currentThread, Instruction nextInstruction, Instruction executedInstruction) {
 		if (nextInstruction == null || executedInstruction == null) return;
		if (!enterMain) return;
		if (!vm.getSystemState().isIgnored()) {
			Instruction insn = executedInstruction;
	        ThreadInfo ti = currentThread;
	        Config conf = vm.getConfig(); 
	        
	        if (insn instanceof JVMInvokeInstruction) {
	        	    JVMInvokeInstruction md = (JVMInvokeInstruction) insn;
	               
	                //MethodInfo mi = md.getInvokedMethod();
	                if (insn instanceof INVOKEVIRTUAL || insn instanceof INVOKEINTERFACE) {
	                	if ( insn.toString().contains("org.apache.http") 
	                		//|| insn.toString().contains("java.lang.String")
	                		|| insn.toString().contains("android.widget") || insn.toString().contains("android.content")
	                		|| insn.toString().contains("android.view.Window") || insn.toString().contains("setContentView")
	                		|| insn.toString().contains("android.os") || insn.toString().contains("java.io.File")
	                		//|| insn.toString().contains("java.lang.StringBuilder") 
	                		//|| insn.toString().contains("java.lang.Object")
	                		|| insn.toString().contains("android.net")
	                		|| (insn.toString().contains("java.lang.System") && !insn.toString().contains("arraycopy"))
	                		|| insn.toString().contains("setTitle")
	                		|| insn.toString().contains("java.util.concurrent.")
	                		|| insn.toString().contains("java.nio.charset.Charset")
	                		|| insn.toString().contains("java.util.Properties")
	                		|| insn.toString().contains("java.io.InputStream")
	                		 || insn.toString().contains("libcore.io.")
	                		 || insn.toString().contains("org.apache.http.impl.")
	                		 || insn.toString().contains("android.content.pm.PackageManager.")
	                		 || insn.toString().contains("sun.misc.Unsafe.")
	                	) {
	                	StackFrame top = ti.getTopFrame();
	                	Instruction nextPC = top.getPC().getNext();
	        
	                	ClassInfo retCi = ClassLoaderInfo.getCurrentResolvedClassInfo(insn.getMethodInfo().getReturnTypeName());
	                	
	    				 ElementInfo ei;
	                	if (retCi != null && retCi.getName() != "void") {
		    				   if (retCi.getName().contains("[")) {   
		 	    				 ei = ti.getHeap().newArray(Types.getArrayElementType(insn.getMethodInfo().getReturnType()), 0, ti);
		 	    			   } else {
		 	    				   ei = ti.getHeap().newObject(retCi, ti);
		 	    			   }
		                	top.setResult(ei.getObjectRef(), null);
	                		//top.setResult(0, null);
	                		top.setPC(nextPC);
	                	} else if (retCi.getName() == "void") {
	                		top.setPC(nextPC);
	                	} else {
	                		//top.setResult(0, null);
		                    top.setPC(nextPC);
	                	}
	                    currentThread.setNextPC(nextPC);
	                } 
//	                	else if (insn.toString().contains("java.lang.Object.toString")) {
//	                  	StackFrame top = ti.getModifiableTopFrame();
//	                	Instruction nextPC = top.getPC().getNext();
//	                	SymbolicStringBuilder str = new SymbolicStringBuilder("toString");
//	                	ElementInfo ei = ti.getHeap().newString(str.getstr().toString(), ti);
//	                	top.setResult(ei.getObjectRef(), str.getstr());
//	                    top.setPC(nextPC);
//	                    currentThread.setNextPC(nextPC);
//	               
//	         	   } 
	                else if (insn.toString().contains("java.lang.String.")) {
	         		   	StackFrame top = ti.getModifiableTopFrame();
	         		   	Instruction nextPC = top.getPC().getNext();
	         		   	ClassInfo retCi = ClassLoaderInfo.getCurrentResolvedClassInfo(insn.getMethodInfo().getReturnTypeName());
	                	if (retCi == null) retCi = ClassLoaderInfo.getCurrentResolvedClassInfo("java.lang.Object");
		    			 
	                	ElementInfo ei = ti.getHeap().newObject(retCi, ti);
	                	top.setResult(ei.getObjectRef(), null);
	                	top.setPC(nextPC);
	                    currentThread.setNextPC(nextPC);
	                    return;
	         	   } 
	                
	                else if (insn.toString().contains("checkSelfPermission")) {
	                	StackFrame top = ti.getTopFrame();
	                	Instruction nextPC = top.getPC().getNext();
	                	top.setResult(0, null);
	                    top.setPC(nextPC);
	                    currentThread.setNextPC(nextPC);
	              
	                } else if (insn.toString().contains("android.telephony.TelephonyManager")) {
	                	StackFrame top = ti.getModifiableTopFrame();
	                	Instruction nextPC = top.getPC().getNext();
	                	
	                	SymbolicStringBuilder imei = new SymbolicStringBuilder("547059598477731");
	                	ElementInfo ei = ti.getHeap().newString(imei.getstr().toString(), ti);
	                	ei.taint();
	                	top.taint();
	                	LOGGER.warning("SOURCE " + ((JVMInvokeInstruction) insn).getInvokedMethodName());
	   	    			   
	                	top.setResult(ei.getObjectRef(), imei.getstr());
	                    top.setPC(nextPC);
	                    currentThread.setNextPC(nextPC);
	               
	                } else if (insn.toString().contains("android.location.LocationManager.requestLocationUpdates")) {
	                	StackFrame top = ti.getTopFrame();
	                	Instruction nextPC = top.getPC().getNext();
	                    top.setPC(nextPC);
	                    currentThread.setNextPC(nextPC);
	        
	                } 
	                else {
	                	StackFrame top = ti.getModifiableTopFrame();
	                	
	                	Instruction nextPC = top.getPC().getNext();
	                	ClassInfo retCi = ClassLoaderInfo.getCurrentResolvedClassInfo(insn.getMethodInfo().getReturnTypeName());
	                	ElementInfo ei;
	                	if (retCi == null) {
	                		retCi = ClassLoaderInfo.getCurrentResolvedClassInfo("java.lang.Object");
	                		ei = ti.getHeap().newObject(retCi, ti);
	                	} else {
	                		if (retCi.getName().contains("[")) {
		 	    				  ei = ti.getHeap().newArray(Types.getArrayElementType(insn.getMethodInfo().getReturnType()), 0, ti);
		 	    			   } else {
		 	    				  ei = ti.getHeap().newObject(retCi, ti);
		 	    			   }
	                	}	    			 
	                	if (ti.getHeap().get(top.getThis()).taint)
	                		ei.taint();
	              
	                	for (String src: sources) {
	                		if (((JVMInvokeInstruction)  insn).getInvokedMethodName().contains(src)) {
	    	                	ei.taint();
	    	                	top.taint = true;
	    	                	//top.setResult(ei.getObjectRef(), "source");
	    	                	LOGGER.warning("SOURCE " + ((JVMInvokeInstruction)  insn).getInvokedMethodName());
	                		}
	                	}
	                	
	                	if (((JVMInvokeInstruction) insn).getInvokedMethod() == null) {
		                    top.setPC(nextPC);
		                    currentThread.setNextPC(nextPC);
	              	  }
	                }
	                } else if (insn instanceof INVOKESTATIC) {
	                	 if (insn.toString().contains("java.lang.String.format")
	                			 || (insn.toString().contains("java.lang.System") && !insn.toString().contains("arraycopy"))
	                			 || insn.toString().contains("java.io.File.fixSlashes")
	                			 || insn.toString().contains("dalvik.system")
	                		) {
		                	StackFrame top = ti.getTopFrame();
		                	Instruction nextPC = top.getPC().getNext();
		                    top.setPC(nextPC);
		                    currentThread.setNextPC(nextPC);
		                  
	                	 } else if (insn.toString().contains("android.os.Build.getString")) {
	                		StackFrame top = ti.getModifiableTopFrame();
	 	                	Instruction nextPC = top.getPC().getNext();
	 	                	SymbolicStringBuilder build_number = new SymbolicStringBuilder("180625392");
	 	                	ElementInfo ei = ti.getHeap().newString(build_number.getstr().toString(), ti);
	 	                	ei.taint();
	 	                	LOGGER.warning("SOURCE " + ((JVMInvokeInstruction) insn).getInvokedMethod().getFullName());
	 	   	    			  
	 	                	top.setResult(ei.getObjectRef(), build_number.getstr());
	 	                    top.setPC(nextPC);
	 	                    
	 	                    currentThread.setNextPC(nextPC); 
	                		 
			             
	                	 } else if (insn.toString().contains("android.os.Build.getLong")) {
	                		 StackFrame top = ti.getModifiableTopFrame();
		 	                 Instruction nextPC = top.getPC().getNext();
		 	                 LOGGER.warning("SOURCE " + ((JVMInvokeInstruction) insn).getInvokedMethod().getFullName());
		 	   	    		 top.taint = true;	  
		 	                 top.setResult(180625392, "CONST_180625392");
		 	                 top.setPC(nextPC);
		 	                    
		 	                 currentThread.setNextPC(nextPC); 
		            
	                	 } else if (insn.toString().contains("android.os.SystemProperties.getInt")
	                			 || insn.toString().contains("android.os.SystemProperties.getLong")) {
	                		 StackFrame top = ti.getModifiableTopFrame();
		 	                	Instruction nextPC = top.getPC().getNext();
		 	                	//LOGGER.warning("SOURCE " + ((JVMInvokeInstruction) insn).getInvokedMethod().getFullName());
		 	   	    			  
		 	                	top.setResult(180625392, null );
		 	                    top.setPC(nextPC);
		 	                    
		 	                    currentThread.setNextPC(nextPC); 
		                		 
				    
	                	 }
	               	} else if (insn instanceof INVOKESPECIAL) {
	               		if (insn.toString().contains("javax.crypto")
	               				|| insn.toString().contains("android.os.AsyncTask.")
	               				|| insn.toString().contains("org.apache.http.impl.client.AbstractHttpClient.")
	               				) {
	               			StackFrame top = ti.getTopFrame();
		                	Instruction nextPC = top.getPC().getNext();
		                    top.setPC(nextPC);
		                    currentThread.setNextPC(nextPC);  
	               		} 
	               	} else if (insn instanceof INVOKECLINIT) {
	               		if (insn.toString().contains("android.os.AsyncTask.")) {
	               			StackFrame top = ti.getTopFrame();
		                	Instruction nextPC = top.getPC().getNext();
		                    top.setPC(nextPC);
		                    currentThread.setNextPC(nextPC);
		       
	               		}
	               	}
	                String methodName = md.getInvokedMethodName();
	                if (isJNIFunction(methodName)) {
	                if (ti.getLastInvokedStackFrame() instanceof JVMNativeStackFrame) { // INVOKENATIVE
	                    TaintModel returnNative = null;
	           
	                    Environment returnConcreteEnv = new Environment();
	                    Environment returnEnv = new Environment();
	                    List<String> nativePC = new ArrayList();
	                    
	                    JVMNativeStackFrame nativeStack = (JVMNativeStackFrame) ti.getLastInvokedStackFrame() ;
	                    MJIEnv envArgs = (MJIEnv) nativeStack.getArguments()[0];
	                    
	                    if (isNative(currentThread)) {
	                      	Heap heap = vm.getSystemState().getHeap();
	                        returnNative = runCorana(md, ti); 
	                        // Execute CORANA and return env in BitVec      
	                    }
	                	StackFrame top = ti.getTopFrame();
	               	 	NativeStackFrame ntop = (NativeStackFrame) top;
		                Instruction nextPC = ntop.getPC().getNext();
	                    top.setPC(nextPC);

	                    currentThread.setNextPC(nextPC);
	                } else {
	                	TaintModel returnNative = null;
	                    
	                    Environment returnConcreteEnv = new Environment();
	                    Environment returnEnv = new Environment();
	                    List<String> nativePC = new ArrayList();
	                   
	                      Heap heap = vm.getSystemState().getHeap();
	                      returnNative = runVirtualCorana(md, ti); 
	                      
	                      // Execute CORANA and return env in BitVec 
		                   // Update memory after native execution
	                      
		          			for (String offset : returnNative.memory.memory.keySet()) {
		          				int objref = (int) Arithmetic.hexToInt(offset);
		          				if ((Memory.get(offset) != null) && (ti.getHeap().get(objref)) != null) {
		          					boolean updateTaint = Memory.get(offset).taint;
		          					ti.getHeap().get(objref).taint = updateTaint;
		          					for (int fieldref : ti.getHeap().get(objref).getFields().asFieldSlots()) {
		          						if (ti.getHeap().get(fieldref) != null) { // Over-approximation
		          							ti.getHeap().get(fieldref).taint = ti.getHeap().get(objref).taint;
		          						}
		          					}
		          				}
		          			}
		          		
	                    
	                    StackFrame top = ti.getTopFrame();
	                    
	                	Instruction nextPC = null;
	                	if (insn != top.getPC()) {
	                		nextPC = top.getPC();
	                	} else {
	                		nextPC = top.getPC().getNext();
	                	}
	     
	                    top.setPC(nextPC);
	                    ClassInfo retCi = ClassLoaderInfo.getCurrentResolvedClassInfo(((JVMInvokeInstruction) md).getReturnTypeName());
	                    if (retCi != null) {
	                    	ElementInfo ei;
	                    	ei = ti.getHeap().newObject(retCi, ti);
	                    	if (returnNative.register.regs.get('0')) {
	                    		ei.taint = true;
	                    	}
	                    	top.setResult(ei.getObjectRef(), ei);
	                    	
	                    	
	                    }
	                    //top.setResult(398, ti.getHeap().get(398));
	                    currentThread.setNextPC(nextPC);
	               }
      	           }
	               for (String sink: sinks) {
	            	   //TODO: Handle case where native function same name as SINK
	                	  if (insn.toString().contains(sink) && !isJNIFunction(sink) && !(ti.getTopFrame() instanceof NativeStackFrame)) {
	           			   if (checkSink(ti.getTopFrame(), ti)) 
	           				   LOGGER.warning("SINK " + ((JVMInvokeInstruction) insn).getInvokedMethodName());
	           	   }
	               }
	               return;
	               } else if (insn instanceof NATIVERETURN) {
	        	   if ((insn.getMethodInfo().getReturnTypeCode() == Types.T_REFERENCE)) {
	        	   }
	        } else if (insn instanceof ARETURN) {
	        	// System.out.println();
	        } else if (insn instanceof RETURN) {
	        	
	        } else if (insn instanceof LSTORE) {
	        	    
	        } else if (insn instanceof ATHROW) {
	        	StackFrame top = ti.getModifiableTopFrame();
            	Instruction nextPC = top.getPC().getNext();
                top.setPC(nextPC);
                currentThread.setNextPC(nextPC);
	         }  
	        else if (insn instanceof GETFIELD) {
	        	   StackFrame frame = ti.getModifiableTopFrame();
	        	   int objRef = frame.peek(); 
	        	    if (objRef == MJIEnv.NULL) {
	        	    	Instruction nextPC = frame.getPC().getNext();
	                    
	                    // Check if object is tainted
	                    ClassInfo fieldCi = ClassLoaderInfo.getCurrentResolvedClassInfo(((GETFIELD) insn).getFieldInfo().getType());
	                	ElementInfo ei;
	                	if (fieldCi == null) {
	                		fieldCi = ClassLoaderInfo.getCurrentResolvedClassInfo("java.lang.Object");
	                		ei = ti.getHeap().newObject(fieldCi, ti);
	                	} else {
	                		if (fieldCi.getName().contains("[")) {
		 	    				  ei = ti.getHeap().newArray(Types.getArrayElementType(insn.getMethodInfo().getReturnType()), 0, ti);
		 	    			   } else {
		 	    				  ei = ti.getHeap().newObject(fieldCi, ti);
		 	    			   }
	                	}
	                	ei.taint = currentThread.getHeap().get(frame.getThis()).taint || currentThread.getHeap().get(((GETFIELD) insn).getLastThis()).taint;
	                    frame.pop();
	                    frame.push(ei.getObjectRef());
	                    //if (currentThread.getHeap().get(frame.getThis()).taint)
	                    currentThread.setNextPC(nextPC);	    
	        	    }
	        }
		}
	}
	
	private boolean isTaintedParameters (JVMStackFrame frame, ThreadInfo ti) {
		for (int i = 0; i < frame.getLocalVariableCount(); i++) {
			int localVar = frame.getLocalVariable(i);
			if (ti.getHeap().get(localVar) != null) {
				if (((ElementInfo) ti.getHeap().get(localVar)).taint) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkSink (NativeStackFrame frame, ThreadInfo ti) {
		for (Object arg :  frame.getArguments()) {
			if (arg instanceof Integer) {
				ElementInfo ei = ti.getHeap().get((Integer) arg);
				if (ei != null) {
					if (ei.taint) return true;
				}
				else if (arg instanceof ElementInfo) {
					if ( ((ElementInfo) arg).taint) return true;
				}
			}
		}
		if (frame.taint && !is_strict) return true;
		return false;
	}
	
	private boolean checkSink (StackFrame frame, ThreadInfo ti) {
		if (frame.getArgumentValues(ti) != null) {
			for (Object arg :  frame.getArgumentValues(ti)) {
				if (arg instanceof Integer) {
					ElementInfo ei = ti.getHeap().get((Integer) arg);
					if (ei != null) {
						if (ei.taint) return true;
					}
				} else if (arg instanceof ElementInfo) {
					if ( ((ElementInfo) arg).taint) return true;
				}
			}
		}
		if (frame.taint && !is_strict) return true;
		return false;
	}
	
	
	private ArrayList<String> readListFromFile (String filePath) {
		ArrayList<String> result = new ArrayList();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			while (line != null) {
				line = line.trim();
				if (line != "") result.add(line);
			
				// Read next line
				line = reader.readLine();
	
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
