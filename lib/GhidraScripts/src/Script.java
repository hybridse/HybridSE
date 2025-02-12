import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ghidra.app.script.GhidraScript;
import ghidra.app.util.bin.ByteProvider;
import ghidra.app.util.bin.MemoryByteProvider;
import ghidra.app.util.bin.format.coff.CoffFileHeader;
import ghidra.app.util.bin.format.coff.CoffSectionHeader;
import ghidra.app.util.bin.format.coff.CoffSectionHeaderFlags;
import ghidra.app.util.bin.format.elf.ElfSectionHeader;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressFactory;
import ghidra.program.model.address.AddressIterator;
import ghidra.program.model.address.AddressRange;
import ghidra.program.model.listing.*;
import ghidra.program.model.data.Structure;
import ghidra.program.model.mem.MemoryBlock;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Script extends GhidraScript {
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
        private String opStr;
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
        public String getRefAddress () {
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
    private String getNameFromStruct(Data data) {
        String name = null;
        String dataTypeName = data.getDataType().getName();
        if (dataTypeName.equals("cfstringStruct")) {
            Data stringPointerField = data.getComponent(2);
            if (stringPointerField != null) {
                Object value = stringPointerField.getValue();
                if (value instanceof Address) {
                    Address stringAddress = (Address) stringPointerField.getValue();
                    Data stringData = getDataAt(stringAddress);
                    name = "sp_" + (String) stringData.getValue();
                }
            }
        }
        else {
            name = data.getLabel();
            if (name != null) {
                name = "p_" + name;
            }
        }
        return name;
    }
    private String getStringFromPointer(Program program, String sourceAddress) {
        Address address = program.getAddressFactory().getAddress(sourceAddress);
        if (address == null) return null;
        try {
            Data data = getDataAt(address);
            if (data != null) {
                Object value = data.getValue();
                if (value == null && data.getDataType() instanceof Structure) {
                    println("~~> " + address.toString() + " ==>is instanceof Structure");
                    return getNameFromStruct(data);
                }
                if (value instanceof String) {
                    println("~~> " + address.toString() + " ==>is instanceof String");
                    return "sp_" + value;
                }
                else if (value instanceof Address) {
                    println("~~> " + address.toString() + " ==>is instanceof Address");
                    // String name = getStringFromPointer((Address) value);
                    String name = data.getLabel();
                    // orig: name = name.substring(0,1) + "p" +
                    // name.substring(1);
                    if (name == null) {
                        return null;
                    }
                    // before: name = name.substring(0,1) + "p" +
                    // name.substring(1);
                    name = "p_" + name;
                    return name;
                }
            }
            // this wasn't a pointer to string. Let's check for function pointer
            ghidra.program.model.listing.Function func = getFunctionAt(address);
            if (func != null) {
                String name = func.getName();
                if (name != null) {
                    println("~~> " + address.toString() + " ==>is instanceof Function");
                    return "fp_" + name;
                }
                return null;
            }
            // let's check for undefined symbol with a label last -- like ObjC
            // NSObject.
            data = getUndefinedDataAt(address);
            if (data != null) {
                String name = data.getLabel();
                if (name != null) {
                    println("~~> " + address.toString() + " ==>is instanceof Undefined");
                    return "p_" + name;
                }
                return null;
            }
        }
        catch (NullPointerException e) {
            // by default do nothing to change the existing label
            println("NullPointerException error caught for " + address);
            return null;
        }
        return null;
    }
    private String getRefStringInInstruction(Program program, String refAddress) {
        return getStringFromPointer(program, refAddress);
    }

    public String getRefAddressFromAddress(Program program, String sourceAddress) {
        Address sourceAddr = program.getAddressFactory().getAddress(sourceAddress);
        Instruction insn = program.getListing().getInstructionAt(sourceAddr);
        if (insn == null) return null;
        String refAddress = insn.getDefaultOperandRepresentation(1);
        if (refAddress != null) {
            refAddress = refAddress.replace("[", "").replace("]", "");
        }
        //println(getRefStringInInstruction(program, sourceAddress));
        return refAddress.contains("x") ? refAddress : null;
    }

    public void run() throws Exception {
        //TODO Add User Code Here

        ArrayList<Function> jniList = new ArrayList();
        HashMap<Long, AssemblyInstruction> insnList = new HashMap<>();
        HashMap<Long, String> plt = new HashMap<>();
        System.out.println("START GHIDRA");
        FunctionManager fm = currentProgram.getFunctionManager();
        FunctionIterator functions = fm.getFunctions(true);
        AssemblyInstruction inst = null;

        //Iterate through all functions
        for (ghidra.program.model.listing.Function f : functions) {
            String[] sub = f.getName().split("_");
            AddressIterator adI = f.getBody().getAddresses(true);
            long minOffset = f.getEntryPoint().getOffset() - 65536;
            long maxOffset = f.getBody().getMaxAddress().getOffset() - 1 - 65536;

            for (Address ad : adI) {
                if (ad == null) continue;
                Instruction ghidraInsn = currentProgram.getListing().getInstructionAt(ad);
                if (ghidraInsn != null) {
                    inst = new AssemblyInstruction(ghidraInsn.getMnemonicString(), ghidraInsn.getLength(), ghidraInsn.getLength());
                    //inst.setGhidraInsn(ghidraInsn);
                    String refAddress = getRefAddressFromAddress(currentProgram, ad.toString());
                    if (refAddress != null) {
                        String refString = getRefStringInInstruction(currentProgram, refAddress);
                        if (refString != null)
                            inst.setReference(refAddress, refString);
                    }
                    insnList.put(ad.getOffset() - 65536, inst);
                }
            }
            //if (f.getName().toLowerCase().contains("java") || f.getName().toLowerCase().contains("jni") || f.getName().toLowerCase().contains("android"))
            if (!f.getName().toLowerCase().contains("fun_") && !f.getName().toLowerCase().contains("gnu") && !f.getName().toLowerCase().contains("unwind"))
            jniList.add(new Function(f.getName(), minOffset, maxOffset, insnList));
            //}
        }

        //Iterate through the program
        InstructionIterator iterator = currentProgram.getListing().getInstructions(true);
        for (Instruction instruction : iterator) {
            Instruction insn = instruction;
            if (insn != null){
            }
            long offset = instruction.getAddress().getOffset() - 65536;

            AssemblyInstruction pInst = new AssemblyInstruction(instruction.getMnemonicString(), offset, instruction.getLength());
            String refAddress = getRefAddressFromAddress(currentProgram, insn.getAddress().toString());
            if (refAddress != null) {
                String refString = getRefStringInInstruction(currentProgram, refAddress);
                if (refString != null)
                    pInst.setReference(refAddress, refString);
            }
            insnList.put(offset, pInst);
        }

        MemoryBlock block = getMemoryBlock(".got");
        AddressIterator addIter = getAddressFactory().getAddressSet(block.getStart(), block.getEnd()).getAddresses(true);
        for (Address adBlock : addIter) {
            Data data = getDataAt(adBlock);
            if (data != null)
                plt.put(adBlock.getOffset(), data.getDefaultValueRepresentation() + "<" + data.getLabel() + ">");
            //println(data.getDefaultValueRepresentation());
        }

//        CoffFileHeader header = new CoffFileHeader(provider);
//        List<CoffSectionHeader> sections = header.getSections();
//        for (CoffSectionHeader section : sections) {
//            if ((section.getFlags() & CoffSectionHeaderFlags.STYP_BSS) != 0) {
//            }}


        //TEST concrete address
        println("TESTING CONCRETE ADDRESS");
        String refAddress = getRefAddressFromAddress(currentProgram, "0x10792");
        if (refAddress != null) {
            String refString = getRefStringInInstruction(currentProgram, refAddress);
            if (refString != null)
                println(refAddress + "_" + refString);
        }

        Gson gson = new Gson();
        String funcString = gson.toJson(jniList);
        String assemblyString = gson.toJson(insnList);
        String memoryString = gson.toJson(plt);
        Type type = new TypeToken<ArrayList<Function>>(){}.getType();
        try {
            FileOutputStream fileOut = new FileOutputStream("./lib/GhidraObj");
            FileOutputStream assemblyFile = new FileOutputStream("./lib/AssemblyOut");
            FileOutputStream pltFile = new FileOutputStream("./lib/PltOut");

            ObjectOutputStream funcOut = new ObjectOutputStream(fileOut);
            ObjectOutputStream assemblyOut = new ObjectOutputStream(assemblyFile);
            ObjectOutputStream pltOut = new ObjectOutputStream(pltFile);

            funcOut.writeObject(funcString);
            assemblyOut.writeObject(assemblyString);
            pltOut.writeObject(memoryString);

            fileOut.close();
            assemblyFile.close();
            pltFile.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
