package main.corana.emulator.taint;

import main.corana.utils.Mapping;
import main.corana.utils.MyStr;

import java.util.*;

public class TRegister {
    public HashMap<Character, Boolean> regs;
    public int length;
    public TRegister(int length) {
        this.length = length;
        regs = new HashMap<>();
        //regs.put('0', new BitVec("0x00000001", r0));
        regs.put('0', false);
        regs.put('1', false);
        regs.put('2', false);
        regs.put('3', false);
        regs.put('4', false);
        regs.put('5', false);
        regs.put('6', false);
        regs.put('7', false);
        regs.put('8', false);
        regs.put('9', false);
        regs.put('x', false);
        regs.put('e', false);
        regs.put('t', false);
        regs.put('s', false);
        regs.put('l', false);
        regs.put('p', false);

        Mapping.regStrToChar.put("r0", '0');
        Mapping.regStrToChar.put("r1", '1');
        Mapping.regStrToChar.put("r2", '2');
        Mapping.regStrToChar.put("r3", '3');
        Mapping.regStrToChar.put("r4", '4');
        Mapping.regStrToChar.put("r5", '5');
        Mapping.regStrToChar.put("r6", '6');
        Mapping.regStrToChar.put("r7", '7');
        Mapping.regStrToChar.put("r8", '8');
        Mapping.regStrToChar.put("r9", '9');
        Mapping.regStrToChar.put("r10", 'x');
        Mapping.regStrToChar.put("r11", 'e');
        Mapping.regStrToChar.put("r12", 't');
        Mapping.regStrToChar.put("sp", 's');
        Mapping.regStrToChar.put("lr", 'l');
        Mapping.regStrToChar.put("pc", 'p');

        for (String key : Mapping.regStrToChar.keySet()) {
            Mapping.regCharToStr.put(Mapping.regStrToChar.get(key), key);
        }
    }
    public TRegister(int length, boolean r0, boolean r1, boolean r2, boolean r3, boolean r4, boolean r5, boolean r6,
                     boolean r7, boolean r8, boolean r9, boolean r10, boolean r11, boolean r12, boolean sp, boolean lr, boolean pc) {
        this.length = length;
        regs = new HashMap<>();
        //regs.put('0', new BitVec("0x00000001", r0));
        regs.put('0', r0);
        regs.put('1', r1);
        regs.put('2', r2);
        regs.put('3', r3);
        regs.put('4', r4);
        regs.put('5', r5);
        regs.put('6', r6);
        regs.put('7', r7);
        regs.put('8', r8);
        regs.put('9', r9);
        regs.put('x', r10);
        regs.put('e', r11);
        regs.put('t', r12);
        regs.put('s', sp);
        regs.put('l', lr);
        regs.put('p', pc);

        Mapping.regStrToChar.put("r0", '0');
        Mapping.regStrToChar.put("r1", '1');
        Mapping.regStrToChar.put("r2", '2');
        Mapping.regStrToChar.put("r3", '3');
        Mapping.regStrToChar.put("r4", '4');
        Mapping.regStrToChar.put("r5", '5');
        Mapping.regStrToChar.put("r6", '6');
        Mapping.regStrToChar.put("r7", '7');
        Mapping.regStrToChar.put("r8", '8');
        Mapping.regStrToChar.put("r9", '9');
        Mapping.regStrToChar.put("r10", 'x');
        Mapping.regStrToChar.put("r11", 'e');
        Mapping.regStrToChar.put("r12", 't');
        Mapping.regStrToChar.put("sp", 's');
        Mapping.regStrToChar.put("lr", 'l');
        Mapping.regStrToChar.put("pc", 'p');

        for (String key : Mapping.regStrToChar.keySet()) {
            Mapping.regCharToStr.put(Mapping.regStrToChar.get(key), key);
        }
    }

    public String getValue(char r) {
        if (!regs.containsKey(r)) return null;
        return regs.get(r).toString();
    }

    public void taint(char r) {
        regs.put(r, true);
    }

    public void sanitize(char r) {
        regs.put(r, false);
    }
    public String toString() {
        MyStr s = new MyStr("+ Register (" + length + "-bit):\n");
        SortedSet<Character> keys = new TreeSet<>(regs.keySet());
        for (Character key : keys) {
            if (regs.get(key) instanceof Boolean) {
                String val = getValue(key);
                s.append("\t- ", Mapping.regCharToStr.get(key).toUpperCase(), "\t: ", val + "\n");
            }
        }
        return s.value();
    }

}
