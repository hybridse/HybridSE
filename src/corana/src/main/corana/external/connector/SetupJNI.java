package main.corana.external.connector;

import jdk.internal.org.jline.utils.Log;
import main.corana.emulator.semantics.Environment;
import main.corana.emulator.semantics.Memory;
import main.corana.emulator.taint.TaintModel;
import main.corana.enums.Variation;
import main.corana.executor.BinParser;
import main.corana.executor.Corana;
import main.corana.executor.Executor;
import main.corana.external.handler.JNIEnvHelper;
import main.corana.pojos.BitVec;
import main.corana.utils.Arithmetic;
import main.corana.utils.GhidraRun;
import main.corana.utils.Logs;
import main.corana.utils.Z3Solver;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class SetupJNI {
    private File libraryFile;
    private boolean initialized = false;

    public SetupJNI(){}
    public SetupJNI(File libraryFile) {
        init(libraryFile);
    }
    public void init(File libF) {
        this.libraryFile = libF;
        GhidraRun.initBinary(libraryFile.getAbsolutePath());
        this.initialized = true;
    }

    public Map.Entry<Environment, List<String>> execJNI(String methodName, Environment initEnv) {
        if (initialized) {
            try {
                //methodName = methodName.toLowerCase();
                Corana.inpFile = this.libraryFile.getName();
                HashMap<String, Map.Entry> methodInfo = BinParser.getJNIFunctions(libraryFile.getAbsolutePath());
                if (methodInfo == null) {
                    methodInfo = BinParser.getJNIFunctions(libraryFile.getAbsolutePath());
                }
                Map.Entry<Long, Long> startEndPair = null;
                if (!methodInfo.containsKey(methodName)) {
                    for (String key: methodInfo.keySet()) {
                        if (key.contains(methodName)) {
                            startEndPair = methodInfo.get(methodName);
                        }
                    }
                } else {
                    startEndPair = methodInfo.get(methodName);
                }
                if (startEndPair != null) {
                    System.out.println("Exploring " + methodName);
                    long start = startEndPair.getKey();
                    long end = startEndPair.getValue();
                    return Executor.customExecute(Variation.M0, libraryFile.getPath(), start, end, initEnv, new TaintModel());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
       else {
            Logs.infoLn("Library file is not set up.");
        }
        return null;
    }

    public Map.Entry<Environment, List<String>>  taintJNI(String methodName, Environment initEnv, TaintModel taintModel) {
        TaintModel result = new TaintModel();
        if (initialized) {
            try {
                //methodName = methodName.toLowerCase();
                Corana.inpFile = this.libraryFile.getName();
                HashMap<String, Map.Entry> methodInfo = BinParser.getJNIFunctions(libraryFile.getAbsolutePath());
                if (methodInfo == null) {
                    methodInfo = BinParser.getJNIFunctions(libraryFile.getAbsolutePath());
                }
                Map.Entry<Long, Long> startEndPair = null;
                if (!methodInfo.containsKey(methodName)) {
                    for (String key: methodInfo.keySet()) {
                        if (key.contains(methodName)) {
                            startEndPair = methodInfo.get(methodName);
                        }
                    }
                } else {
                    startEndPair = methodInfo.get(methodName);
                }
                if (startEndPair != null) {
                    Logs.info("Exploring " + methodName);
                    long start = startEndPair.getKey();
                    long end = startEndPair.getValue();
                    return Executor.customExecute(Variation.M0, libraryFile.getPath(), start, end, initEnv, taintModel);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Logs.infoLn("Library file is not set up.");
        }
        return null;
    }

    public Set<String> getNativeFunction() {
        HashMap<String, Map.Entry> result = BinParser.getJNIFunctions(this.libraryFile.getAbsolutePath());
        if (result != null) {
            return result.keySet();
        }
        return null;
    }

    public void setLog(boolean isLog) {
        Logs.setLog(isLog);
    }

    public void setPrintStream(PrintStream ps) {
        System.setOut(ps);
    }

    public static void main(String[] args) {
        File dir = new File("/home/va/git/DroiDSE/temp_20240207_135440/java_projects/native_leak_array/lib/armeabi-v7a/libleak_array.so");
        SetupJNI su = new SetupJNI(dir);
        Environment initEnv = new Environment();
        TaintModel tModel = new TaintModel();
        PrintStream console = System.out;
        Corana.inpFile = dir.getName();

        su.setLog(true);
        initEnv.register.set('2', ArithmeticUtils.IntegerToBitVec(813));
        //initEnv.register.set('3', ArithmeticUtils.IntegerToBitVec(792));

        String offset = Arithmetic.longToHex(813);
        String[] arrStr = new String[]{"null", "imei", "null", "null", "null", "null", "null", "null", "null", "null", "null"};
        boolean[] taintArr = new boolean[]{false, true, false, false, false, false, false, false, false, false, false, false };
        tModel.allocateMemory(offset, arrStr, taintArr);

        //Map.Entry<Environment, List<String>> result = su.execJNI("sendFoo", initEnv);
          Map.Entry<Environment, List<String>> result = su.taintJNI("send", initEnv, tModel);
//        System.out.println(result);
//        System.out.println(result.getKey().register.toString());
//        System.out.println(result.getKey().stacks.toString());
//        System.out.println(result.getKey().memory.toString());
//        System.out.println(result.getValue());
    }
}
