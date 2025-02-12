package main.corana.emulator.taint;

import main.corana.external.connector.ArithmeticUtils;
import main.corana.pojos.BitVec;
import main.corana.utils.Arithmetic;

import java.util.HashMap;

public class TMemory {
    public final HashMap<String, Boolean> memoryTaint;
    public final HashMap<String, String> memory;
    public final int length;

    public TMemory(int length) {
        this.memory = new HashMap<>();
        this.length = length;
        this.memoryTaint = new HashMap<>();
    }
    public void setMemory(String offset, String value, boolean taint) {
        memoryTaint.put(offset, taint);
        memory.put(offset, value);
    }
    public void setMemory(String offset, int value, boolean taint) {
        memoryTaint.put(offset, taint);
        memory.put(offset, Arithmetic.intToHex(value));
    }
}
