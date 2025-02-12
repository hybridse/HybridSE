package main.corana.emulator.taint;

import main.corana.emulator.semantics.*;
import main.corana.executor.Configs;
import main.corana.pojos.BitVec;
import main.corana.utils.Arithmetic;
import main.corana.utils.MyStr;
import main.corana.utils.SysUtils;

import java.util.HashMap;
import java.util.Random;

public class TaintModel {
    public TFlags flags;
    public TRegister register;
    public TMemory memory;
    public TStacks stacks;

    public TaintModel(boolean... isRandom) {
        Configs.RANDOM_SEED += 1;
        // Default value of registers and flags
        flags = new TFlags(false, false, false, false, false, false);
        register = new TRegister(Configs.architecture);
        memory = new TMemory(Configs.architecture);
        stacks = new TStacks();
    }

    public String toString() {
        return new MyStr(flags.toString(), register.toString(), stacks.toString(), memory.toString()).value();
    }
    private static String addHex(String a, String b) {
        long sum = Arithmetic.hexToInt(a) + Arithmetic.hexToInt(b);
        return SysUtils.getAddressValue(Arithmetic.longToHex(sum));
    }
    public void allocateMemory(String offset, String[] arrStr, boolean[] taintValues) {
        String start = offset;
        for (int i = 0; i < arrStr.length; i++) {
            String item = arrStr[i];
            boolean taint = taintValues[i];
            memory.setMemory(start, item, taint);
            start = addHex(start, "#x00000004");
        }
    }
    public void allocateMemory(String offset, int[] intArr, boolean[] taintValues) {
        String start = offset;
        for (int i = 0; i < intArr.length; i++) {
            int item = intArr[i];
            boolean taint = taintValues[i];
            memory.setMemory(start, item, taint);
            start = addHex(start, "#x00000004");
        }
    }
}
