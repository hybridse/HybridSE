package main.corana.external.connector;

import main.corana.pojos.BitVec;
import main.corana.utils.Arithmetic;

import java.util.BitSet;


public class ArithmeticUtils {
    public static BitVec IntegerToBitVec(Integer i) {
        return new BitVec(Arithmetic.intToHex(i), Arithmetic.intToBitSet(i.intValue()));
    }
    public static BitVec DoubleToBitVec(Double d) {
        return new BitVec(Arithmetic.floatToHexSmt(d.floatValue()), Arithmetic.floatToBitSet(d.floatValue()));
    }
    public static Integer BitVecToInteger(BitVec bv) {
        return new Integer((int) Arithmetic.hexToInt(bv.getSym()));
    }
    public static Double BitVecToDouble(BitVec bv) {
        if (bv.getSym().contains("bv")) return new Double(convert(bv.getVal()));
        long longHex = parseUnsignedHex(bv.getSym());
        double d = Double.longBitsToDouble(longHex);
        return new Double(d);
    }
    public static long convert(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
    }

    private static long parseUnsignedHex(String text) {
        if (text.length() == 16) {
            return (parseUnsignedHex(text.substring(0, 1)) << 60)
                    | parseUnsignedHex(text.substring(1));
        }
        return Long.parseLong(text, 16);
    }

}
