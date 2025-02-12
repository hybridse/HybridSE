package main.corana.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GhidraRun {
    static String objFile = "./lib/GhidraObj";
    static String assemblyFile = "./lib/AssemblyOut";
    static String pltFile ="./lib/PltOut";
    private static ArrayList<Function> functions;
    private static HashMap<Long, AssemblyInstruction> assemblyList;
    public static ArrayList<Function> getFunctions() {
        return functions;
    }

    public static HashMap<Long, AssemblyInstruction> getAssemblyList() {
        return assemblyList;
    }
    public static HashMap<Long, String> getGotSection() {
        return pltList;
    }

    public static HashMap<Long, String> pltList;
    public static void initBinary(String binaryPath) {
        String workingDir = System.getProperty("user.dir");
        String projectPath = workingDir + "/lib/GhidraScripts";
        String tempProject = UUID.randomUUID().toString();
        String scriptLog = workingDir + "/lib/GhidraScripts/ghidra.log";
        String cmd = workingDir + "/lib/ghidra/ghidra_10.1.4_PUBLIC/support/analyzeHeadless " + projectPath + " "
                + tempProject + " -import " + binaryPath + " -scriptPath " +  "\"" + workingDir
                + "/lib/GhidraScripts/src/\" -preScript PreScript.java -postScript Script.java -log " + scriptLog + " -deleteProject";
        System.out.println(cmd);
        String res = SysUtils.execCmd(cmd);
//        objFile = objFile + binaryPath.substring(binaryPath.lastIndexOf('.'), binaryPath.length()-1);
//        assemblyFile = assemblyFile + binaryPath.substring(binaryPath.lastIndexOf('.'), binaryPath.length()-1);
//        pltFile =  pltFile + binaryPath.substring(binaryPath.lastIndexOf('.'), binaryPath.length()-1);

        if (res != null) {
            Gson gson = new Gson();
            Type jniFunctionType = new TypeToken<ArrayList<Function>>() {}.getType();
            Type assemblyType = new TypeToken<HashMap<Long, AssemblyInstruction>>(){}.getType();
            Type pltType = new TypeToken<HashMap<Long, String>>(){}.getType();
            try {
                FileInputStream fis = new FileInputStream(objFile);
                FileInputStream fis_assembly = new FileInputStream(assemblyFile);
                FileInputStream fis_plt = new FileInputStream(pltFile);

                ObjectInputStream ois = new ObjectInputStream(fis);
                ObjectInputStream ois_assembly = new ObjectInputStream(fis_assembly);
                ObjectInputStream ois_plt = new ObjectInputStream(fis_plt);

                functions = gson.fromJson((String) ois.readObject(), jniFunctionType);
                assemblyList = gson.fromJson((String) ois_assembly.readObject(), assemblyType);
                pltList = gson.fromJson((String) ois_plt.readObject(), pltType);

                fis.close();
                fis_assembly.close();
                fis_plt.close();

                FileUtils.delete(objFile);
                FileUtils.delete(assemblyFile);
                FileUtils.delete(pltFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        GhidraRun.initBinary("/home/va/Projects/java_androidnative1/androidnative1/lib/armeabi-v7a/libnative-lib.so");

    }
    public class Function implements Serializable {
        //default serialVersion id
        private static final long serialVersionUID = 1L;
        private String jniFunctionName;
        private long minOffset;
        private long maxOffset;
        private HashMap<Long, AssemblyInstruction> inst;

        public Function(String jniFunctionName, long minOffset, long maxOffset, HashMap<Long, AssemblyInstruction> insns) {
            this.jniFunctionName = jniFunctionName;
            this.minOffset = minOffset;
            this.maxOffset = maxOffset;
            this.inst = insns;
        }

        public String getJniFunctionName() {
            return jniFunctionName;
        }
        public long getMinOffset() {
            return minOffset;
        }
        public long getMaxOffset() {
            return maxOffset;
        }
    }

    public class AssemblyInstruction implements Serializable {
        private static final long serialVersionUID = 1L;
        private String opCode;
        private int size;
        private long addressOffset;
        //private Instruction ghidraInsn = null;
        private String refAddress = null;
        private String refString = null;
        public AssemblyInstruction(){};
        public AssemblyInstruction(String opCode, long addressOffset, int size) {
            this.opCode = opCode;
            this.size = size;
        }
        public String getOpCode() {
            return opCode;
        }
//        public void setGhidraInsn(Instruction insn) {
//            this.ghidraInsn = insn;
//        }
//        public Instruction getGhidraInsn() {
//            return this.ghidraInsn;
//        }
        public String getRefAddress() {
            return this.refAddress;
        }
        public String getRefString() {
            return this.refString;
        }
        public void setReference(String address, String data) {
            this.refAddress = address;
            this.refString = data;
        }
        public int getSize() {
            return size;
        }
        public long getAddressOffset() {
            return addressOffset;
        }
    }

}
