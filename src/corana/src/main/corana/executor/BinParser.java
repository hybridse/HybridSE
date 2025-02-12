package main.corana.executor;

import main.corana.capstone.Capstone;
import main.corana.elfutils.Elf;
import main.corana.elfutils.SectionHeader;
import main.corana.emulator.base.Emulator;
import main.corana.external.handler.APIStub;
import main.corana.pojos.AsmNode;
import main.corana.utils.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

public class BinParser {
    private static HashMap<String, String> symbolTable = new HashMap<>(); // <hex address, symbol name>
    private static HashMap<String, String> dynamicLink; //hex address, functionPtr
    public static long _init = 0;
    public static long _start = 0;
    public static long main = 0;
    public static long end = -1;

    public static long get_start() {
        return main;
    }
    public static void set_start(long beginPC) { main=beginPC; }
    public static void set_end(long endPC) { end=endPC; }
    public static HashMap<String, String> getSymbolTable() {
        return symbolTable;
    }
    public static HashMap<String, GhidraRun.AssemblyInstruction> ghidraModel = new HashMap<>();

    public static Map.Entry getReferences(Long label) {
        HashMap<Long, GhidraRun.AssemblyInstruction> insns = GhidraRun.getAssemblyList();
        GhidraRun.AssemblyInstruction insn = insns.get(label);
        return Pair.of(insn.getRefAddress(), insn.getRefString());
    }

    public static ArrayList<AsmNode> parseBySection(String inp) {
        Logs.infoLn(" + Analyzing " + inp + " ...");
        try {
            // Load symbol table init and start address
            // findEntryPoint(inp);
            loadSymbolTable(inp);

            ArrayList<AsmNode> totalNodes = new ArrayList<>();
            Elf e = new Elf(new File(inp));
            SectionHeader[] sections = e.sectionHeaders;
            int beginCodeSection = -1;
            int endCodeSection = -1;

            for (int i = 0; i < sections.length; i++) {
                SectionHeader sh = sections[i];
                if (sh.getName().equals(".init")) beginCodeSection = i;
                if (sh.getName().equals(".fini")) endCodeSection = i;
            }
            if (beginCodeSection == -1 || endCodeSection == -1) {
                for (int i = 0; i < sections.length; i++) {
                    SectionHeader sh = sections[i];
                    totalNodes.addAll(parseSection(e, sh));
                }
            } else {
                for (int i = beginCodeSection; i <= endCodeSection; i++) {
                    SectionHeader sh = sections[i];
                    totalNodes.addAll(parseSection(e, sh));
                }
            }

            return totalNodes;
        } catch (Exception ex) {
            Logs.infoLn("-> Cannot read header section. File might be corrupted.");
            ex.printStackTrace();
            return null;
        }
    }

    private static void loadSymbolTable(String binpath) {
        String objCmd = "arm-none-eabi-objdump -t " + binpath;
        String exRes = SysUtils.execCmd(objCmd);
        String[] resultLines = exRes.split("\n");
        long main_size;
        HashMap<String, String> symTable = new HashMap<>();
        for (String line : resultLines) {
            String[] contents = line.split("\\s+");
            if (contents.length >= 6) {
                if (!symTable.containsKey(contents[0])) {
                    symTable.put(contents[0].trim(), contents[contents.length-1].trim()); // e.g. 00014fa0 printf
//                    symTable.put(contents[contents.length-1].trim(), contents[0].trim()); //e.g. printf 14fa0
                } else {
                    if (!contents[5].contains("_") && !contents[5].equals(".hidden")) {
                        symTable.put(contents[0].trim(), contents[5].trim());
                    }
                }
                // Find the address of init and _start
                if (contents[5].trim().equals(".init")) {
                    _init = Arithmetic.hexToInt(contents[0]);
                } else if (contents[5].trim().equals("_start")) {
                    _start = Arithmetic.hexToInt(contents[0]);
                } else if (contents[5].trim().equals("main")) {
                    main = Arithmetic.hexToInt(contents[0]);
                    main_size = Arithmetic.hexToInt(contents[4]);
                    end = main + main_size - 4;
                }
            }
        }
        // Dynamic linking
        String objCmd2 = "arm-none-eabi-objdump -D -S -R --section=.got " + binpath;
        String exRes2 = SysUtils.execCmd(objCmd2);
        String[] resultLines2 = exRes2.split("\n");
        String hex = "000000";
        String functionPtr = "@@Base";
        String label = "R_ARM_JUMP_SLOT";
        String functionName = "";
        for (String line : resultLines2) {
            if (line.contains("JUMP_SLOT")) {
                hex = line.split(":")[0].trim();
                functionName = line.split(":")[1].split("\t")[1].replace("@@Base", "").trim();
                //TESTING
                    symbolTable.put(hex, functionName);
            }
        }
        symbolTable = symTable;
    }

    private static ArrayList<AsmNode> parseSection(Elf e, SectionHeader sh) throws IOException {
        byte[] totalByteArray = new byte[0];
        ByteBuffer buff = e.getSection(sh);
        byte[] arr = new byte[buff.remaining()];
        buff.get(arr);
        totalByteArray = SysUtils.concatByteArray(totalByteArray, arr);
        return parse(totalByteArray, sh.virtualAddress);
    }

    private static void loadDynamicFunction(String binpath) {
        String tbCmd = "arm-none-eabi-objdump -t " + binpath;
        String exRes1 = SysUtils.execCmd(tbCmd);
        String[] resultLines1 = exRes1.split("\n");

        HashMap<String, String> symTable = new HashMap<>();
        for (String line : resultLines1) {
            String[] contents = line.split("\\s+");
            if (contents.length >= 6) {
                if (!symTable.containsKey(contents[0])) {
                    symTable.put(contents[contents.length - 1].trim(), contents[0].trim()); //e.g. printf 14fa0
                } else {
                    if (!contents[5].contains("_") && !contents[5].equals(".hidden")) {
                        symTable.put(contents[5].trim(), contents[0].trim());
                    }
                }
            }
        }
        String objCmd = "arm-none-eabi-objdump -D -S -R --section=.got " + binpath;
        String exRes = SysUtils.execCmd(objCmd);
        String[] resultLines = exRes.split("\n");
        String hex = "000000";
        String functionPtr = "@@Base";
        String label = "R_ARM_JUMP_SLOT";
        String functionName = "";
        for (String line : resultLines) {
            if (line.contains("JUMP_SLOT")) {
                hex = line.split(":")[0].trim();
                functionName = line.split(":")[1].split("\t")[1].replace("@@Base", "").trim();
                if (symTable.containsKey(functionName)) {
                    functionPtr = symTable.get(functionName);
                    symTable.put(hex, functionName);
                }
            }
        }
    }

    public static ArrayList<AsmNode> parse(String inp) {
        Logs.infoLn(" + Analyzing " + inp + " ...");
        try {
            Elf e = new Elf(new File(inp));
            SectionHeader[] sections = e.sectionHeaders;
            int beginCodeSection = -1;
            int endCodeSection = -1;
            for (int i = 0; i < sections.length; i++) {
                SectionHeader sh = sections[i];
                if (sh.getName().equals(".init")) beginCodeSection = i;
                if (sh.getName().equals(".fini")) endCodeSection = i;
            }
            byte[] totalByteArray = new byte[0];
            for (int i = beginCodeSection; i <= endCodeSection; i++) {
                SectionHeader sh = sections[i];
                ByteBuffer buff = e.getSection(sh);
                byte[] arr = new byte[buff.remaining()];
                buff.get(arr);
                totalByteArray = SysUtils.concatByteArray(totalByteArray, arr);
            }
            // Set init and start address

            return parse(totalByteArray, 0);
        } catch (Exception ex) {
            Logs.infoLn("-> Cannot read header section. File might be corrupted.");
            return null;
        }
    }
//TODO
    public static ArrayList<AsmNode> parse(byte[] bytes, long virtualAddress) {
        ArrayList<AsmNode> asmNodes = new ArrayList<>();
        long label = virtualAddress;
        int i = 0;
        Capstone cs = new Capstone(Capstone.CS_ARCH_ARM, Configs.executionMode);
        HashMap<Long, GhidraRun.AssemblyInstruction> insns = GhidraRun.getAssemblyList();
        GhidraRun.AssemblyInstruction ghidraInst = insns.get(label);
        AsmNode n;
        int instrSize = (ghidraInst != null) ? ghidraInst.getSize(): Configs.instructionSize;

        while (i + instrSize-1 < bytes.length) {
            byte[] bs;
            if (instrSize == 4) {
                //cs.setMode(Capstone.CS_MODE_ARM);
                bs = new byte[]{bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]};
            } else {
                //cs.setMode(Capstone.CS_MODE_THUMB);
                bs = new byte[]{bytes[i], bytes[i+1]};
            }
            if (cs.disasm(bs, label).length == 0) {
                cs.setMode(Configs.switchExecutionMode());
            }
            Capstone.CsInsn[] insn = cs.disasm(bs, label);
            if (insn.length == 0) {
                label += instrSize;
            } else {
                // Else encode capstone inst as an asmNode
                for (Capstone.CsInsn csInsn : insn) {
                    String opcode = csInsn.mnemonic.replace(".w", "");
                    String condSuffix = "";
                    if (opcode.length() >= 3) {
                        String temp = opcode.substring(opcode.length() - 2);
                        List<String> opcodeList = Arrays.stream(Emulator.class.getMethods()).map(s -> s.getName()).collect(Collectors.toList());
                        if (Mapping.condStrToChar.get(temp.toUpperCase()) != null) {
                            condSuffix = temp;
                            if (condSuffix.equals("ls") && !opcodeList.contains(opcode.substring(0, opcode.length() - 2))) {
                                opcode = opcode;
                            } else if (condSuffix.equals("vs") && !opcodeList.contains(opcode.substring(0, opcode.length() - 2))) {
                                opcode = opcode;
                            } else if (condSuffix.equals("cs") && !opcodeList.contains(opcode.substring(0, opcode.length() - 2))) {
                                opcode = opcode;
                            } else if (condSuffix.equals("rs") && !opcodeList.contains(opcode.substring(0, opcode.length() - 2))) {
                                opcode = opcode;
                            }
                            else opcode = opcode.substring(0, opcode.length() - 2);
                        }
                    }
                    boolean updateFlag = opcode.endsWith("s");
                    if (updateFlag) opcode = opcode.substring(0, opcode.length() - 1);

                    StringBuilder params = new StringBuilder();
                    String[] paramsArr = csInsn.opStr.split("\\,");
                    for (int p = 0; p < paramsArr.length; p++) {
                        params.append(paramsArr[p].trim());
                        if (p < paramsArr.length - 1) {
                            params.append(",");
                        }
                    }

                    n = new AsmNode(String.valueOf(csInsn.address), opcode, condSuffix, params.toString(), updateFlag, Arithmetic.intToHex(csInsn.address));

                    asmNodes.add(n);
                    label += instrSize/insn.length;
                }
            }
            i += instrSize;
            ghidraInst = insns.get(label);
//            if (ghidraInst != null && ghidraInst.getRefAddress() != null) {
//                ghidraModel.put(n.getLabel(), ghidraInst);
//            }
            instrSize = (ghidraInst != null) ? ghidraInst.getSize(): 2;
            }
            return asmNodes;
    }
    public static ArrayList<AsmNode> parseInstruction(byte[] bytes, long label) {
        ArrayList<AsmNode> asmNodes = new ArrayList<>();

        int i = 0;
        Capstone cs = new Capstone(Capstone.CS_ARCH_ARM, Configs.executionMode);
        int instrSize = 4;

        while (i + instrSize-1 < bytes.length) {
            byte[] bs;
            cs.setMode(Capstone.CS_MODE_ARM);
            bs = new byte[]{bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3]};

            Capstone.CsInsn[] insn = cs.disasm(bs, label);
            if (insn.length == 0) {
                label += instrSize;
            } else {
                // Else encode capstone inst as an asmNode
                for (Capstone.CsInsn csInsn : insn) {
                    String opcode = csInsn.mnemonic;
                    String condSuffix = "";
                    if (opcode.length() >= 3) {
                        String temp = opcode.substring(opcode.length() - 2);
                        List<String> opcodeList = Arrays.stream(Emulator.class.getMethods()).map(s -> s.getName()).collect(Collectors.toList());
                        if (Mapping.condStrToChar.get(temp.toUpperCase()) != null) {
                            condSuffix = temp;
                            if (condSuffix.equals("ls") && !opcodeList.contains(opcode.substring(0, opcode.length() - 2))) {
                                condSuffix = "";
                                opcode = opcode;
                            } else opcode = opcode.substring(0, opcode.length() - 2);
                        }
                    }
                    boolean updateFlag = opcode.endsWith("s");
                    if (updateFlag) opcode = opcode.substring(0, opcode.length() - 1);

                    StringBuilder params = new StringBuilder();
                    String[] paramsArr = csInsn.opStr.split("\\,");
                    for (int p = 0; p < paramsArr.length; p++) {
                        params.append(paramsArr[p].trim());
                        if (p < paramsArr.length - 1) {
                            params.append(",");
                        }
                    }
                    AsmNode n = new AsmNode(String.valueOf(csInsn.address), opcode, condSuffix, params.toString(), updateFlag, Arithmetic.intToHex(csInsn.address));
                    asmNodes.add(n);
                    label += instrSize/insn.length;
                }
            }
            i += instrSize;
        }
        return asmNodes;
    }

    public static ArrayList<AsmNode> expand(ArrayList<AsmNode> asmNodes) {
        ArrayList<AsmNode> expandedNodes = new ArrayList<>();
        for (AsmNode an : asmNodes) {
            String label = an.getLabel();
            String opcode = an.getOpcode();
            String condSuffix = an.getCondSuffix();
            String address = an.getAddress();
            List<String> branches = Arrays.asList("b", "bx", "bl", "blx");
            if (label != null && opcode != null && condSuffix != null && !condSuffix.equals("") && !branches.contains(opcode)) {
                AsmNode originNode = new AsmNode(an);
                originNode.setCondSuffix("");
                String newOriginLabel = Integer.parseInt(label) + "-2";
                originNode.setLabel(newOriginLabel);
                expandedNodes.add(originNode);
                expandedNodes.add(new AsmNode(label, "b", condSuffix, newOriginLabel, false, address));
                expandedNodes.add(new AsmNode(Integer.parseInt(label) + "+2", "b", "",
                        "0x" + Arithmetic.intToHex(Integer.parseInt(label) + Configs.instructionSize), false, address + "+2"));
            } else {
                expandedNodes.add(an);
            }
        }
        return expandedNodes;
    }

    private static void findEntryPoint(String binpath) {
        String objCmd = "readelf -h " + binpath;
        String exRes = SysUtils.execCmd(objCmd);
        String[] resultLines = exRes.split("\n");
        String hexstart = "000000";
        for (String line : resultLines) {
            if (line.contains("Entry point address")) {
                hexstart = line.split(":")[1].trim();
            }
        }
        _start = Arithmetic.hexToInt(hexstart);
    }

    public static HashMap<String, Map.Entry> getJNIFunctions(String binpath, boolean... noSymbolTable) {
        HashMap<String, Long> sizeMap = new HashMap<>();
        HashMap<String, Map.Entry> exportFuncs = new HashMap<>();
        ArrayList<GhidraRun.Function> functions = GhidraRun.getFunctions();
        if (functions != null) {
            for (GhidraRun.Function f : functions) {
                String name = f.getJniFunctionName();
                //if (name.contains("Java_") || (name.split("_").length==2 && name.startsWith("_")) || name.contains("android")) {
                String[] sub = f.getJniFunctionName().split("_");
                exportFuncs.put(sub[sub.length - 1], Pair.of(f.getMinOffset(), f.getMaxOffset())); //-10000 hex
                //}
                //sizeMap.put(f.getName(), f.getEntryPoint().getOffset());
            }
            return exportFuncs;
        }
        return null;
    }
    public static HashMap<String, Map.Entry> getJNIFunctions_nm(String binpath) {
        // Get size map of JNI functions
        HashMap<String, Long> sizeMap = new HashMap<>();
        String ndCmd = "nm -gD " + binpath;
        String stRes = SysUtils.execCmd(ndCmd);

        HashMap<String, Map.Entry> exportFuncs = new HashMap<>();
        String[] resultLines = stRes.split("\n");
        long endAdd;
        long startAdd;
        for (String line : resultLines) {
            String[] ls = line.split(" ");
            if (ls.length > 2) {
                if (ls[2].contains("Java_")) {
                    ls[2] = ls[2].contains("(") ? ls[2].substring(0, ls[2].indexOf("(")) : ls[2];
                    String[] sub = ls[2].split("_");

                    startAdd = Arithmetic.hexToInt(ls[0]);
                    endAdd = -1;
                    //endAdd = sizeMap.containsKey(ls[2]) ? calculateEndAddress(startAdd, sizeMap.get(ls[2])) : startAdd;
                    exportFuncs.put(sub[sub.length-1], Pair.of(startAdd, endAdd));
                }
            }
        }
        return exportFuncs;
    }
    /**
     * Get JNIExport function (for SPF Connection)
     * @param binpath
     * @return
     */
    public static HashMap<String, Map.Entry> getJNIFunctions_old(String binpath) {
        // Get size map of JNI functions
        HashMap<String, Long> sizeMap = new HashMap<>();
        String ndCmd = "nm --print-size --size-sort --radix=d " + binpath;
        String ndout = SysUtils.execCmd(ndCmd);
        String[] ndRes = ndout.split("\n");
        for (String line: ndRes) {
            String[] ls = line.split(" ");
            if (ls.length > 3) {
                sizeMap.put(ls[3], Long.parseLong(ls[1])); // name --- hex
            }
        }
        // Get JNIInfo Hashmap
        String stCmd = "nm -g -C --radix=d " + binpath;
        String stRes = SysUtils.execCmd(stCmd);

        HashMap<String, Map.Entry> exportFuncs = new HashMap<>();
        String[] resultLines = stRes.split("\n");
        long endAdd;
        long startAdd;
        for (String line : resultLines) {
            String[] ls = line.split(" ");
            if (ls.length > 2) {
                if (ls[2].contains("Java_")) {
                    ls[2] = ls[2].contains("(") ? ls[2].substring(0, ls[2].indexOf("(")) : ls[2];
                    String[] sub = ls[2].split("_");
                    startAdd = Long.parseLong(ls[0]);
                    endAdd = sizeMap.containsKey(ls[2]) ? calculateEndAddress(startAdd, sizeMap.get(ls[2])) : startAdd;
                    exportFuncs.put(sub[sub.length-1], Pair.of(startAdd, endAdd));
                }
            }
        }
        return exportFuncs;
    }

    private static long calculateEndAddress(long startAddress, long size) {
        long endAddress = startAddress + size;
        return endAddress;
    }

    public static List<String> getExternalFunctions(String binpath) {
        String objCmd = "nm -g -C " + binpath;
        String exRes = SysUtils.execCmd(objCmd);
        List<String> externalFuncs = new ArrayList<>();
        String[] resultLines = exRes.split("\n");

        for (String line : resultLines) {
            String[] ls = line.split(" ");
            if (ls.length > 2) {
                externalFuncs.add(ls[2]);
            }
        }
        List<String> alllist = Arrays.stream(APIStub.class.getMethods()).map(s -> s.getName()).collect(Collectors.toList());
        List<String> res = new ArrayList<>();
        for (String func : externalFuncs) {
            if (alllist.contains(func)) {
                res.add(func);
            }
        }
        return res;
    }

    public static List<String> getInternalSymbols(String binpath) {
        String objCmd = "nm -g -C " + binpath;
        String exRes = SysUtils.execCmd(objCmd);
        List<String> externalFuncs = new ArrayList<>();
        String[] resultLines = exRes.split("\n");

        for (String line : resultLines) {
            String[] ls = line.split(" ");
            if (ls.length > 2) {
                externalFuncs.add(ls[2]);
            }
        }

        objCmd = "nm -C " + binpath;
        exRes = SysUtils.execCmd(objCmd);
        List<String> inFuncs = new ArrayList<>();
        resultLines = exRes.split("\n");

        for (String line : resultLines) {
            String[] ls = line.split(" ");
            if (ls.length > 2) {
                if (!externalFuncs.contains(ls[2]) && !ls[2].contains("$"))
                    inFuncs.add(ls[2]);
            }
        }
        return inFuncs;
    }


    public static ArrayList<AsmNode> parseObjDump(String inp) {
        Logs.infoLn(" + Parsing " + inp + " ...");
        String disassembleCmd = "arm-none-eabi-objdump -D -b binary -marm ";
        String execResult = SysUtils.execCmd(disassembleCmd + inp);
        if (execResult == null) {
            Logs.infoLn("-> Parsing binary file error !");
            return null;
        }
        String[] resultLines = execResult.split("\n");
        ArrayList<AsmNode> asmNodes = new ArrayList<>();
        for (String line : resultLines) {
            line = line.trim().replaceAll(" +", " ");
            if (line.contains(";")) {
                line = line.split("\\;")[0];
            }
            if (line.contains("\t")) {
                line = line.replace("\t", " ");
                line = line.replaceAll(" +", " ");
                String[] parts = line.split("\\:");
                String label = parts[0];
                String[] contents = parts[1].split("\\s+");
                if (contents.length > 3) {
                    String opcode = contents[2];
                    String condSuffix = "";
                    if (opcode.length() >= 3) {
                        String temp = opcode.substring(opcode.length() - 2);
                        if (Mapping.condStrToChar.get(temp.toUpperCase()) != null) {
                            condSuffix = temp;
                            opcode = opcode.substring(0, opcode.length() - 2);
                        }
                    }
                    StringBuilder params = new StringBuilder();
                    for (int i = 3; i < contents.length; i++) {
                        params.append(contents[i]).append(" ");
                    }
                    boolean updateFlag = opcode.endsWith("s");
                    if (updateFlag) {
                        opcode = opcode.substring(0, opcode.length() - 1);
                    }
                    AsmNode n = new AsmNode(label, opcode, condSuffix, params.toString().trim(), updateFlag);
                    asmNodes.add(n);
                }
            }
        }
        return asmNodes;
    }
}
