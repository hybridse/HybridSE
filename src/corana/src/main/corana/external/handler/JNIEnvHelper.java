package main.corana.external.handler;

import com.opencsv.CSVReader;
import main.corana.emulator.semantics.Memory;
import main.corana.executor.DBDriver;
import main.corana.pojos.BitVec;
import main.corana.utils.Arithmetic;
import main.corana.utils.SysUtils;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JNIEnvHelper {

    public static String addHex(String a, String b) {
        long sum = Arithmetic.hexToInt(a) + Arithmetic.hexToInt(b);
        return SysUtils.getAddressValue(Arithmetic.longToHex(sum));
    }
    public static List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
    public static void allocateArray(String offset, String[] arrStr, boolean[] taintValues) {
        // Allocate arr
        String start = offset;
        for (int i = 0; i < arrStr.length; i++) {
            String item = arrStr[i];
            boolean taint = taintValues[i];
            Memory.set(Arithmetic.fromHexStr(start), new BitVec(item));
            if (taint) Memory.taint(start);
            start = addHex(start, "#x00000004");
        }

    }
    public static void allocateArray(String offset, int[] intArr, boolean[] taintValues) {
        String start = offset;
        for (int i = 0; i < intArr.length; i++) {
            int item = intArr[i];
            boolean taint = taintValues[i];
            Memory.set(Arithmetic.fromHexStr(start), new BitVec(item));
            if (taint) Memory.taint(start);
            start = addHex(start, "#x00000004");
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        Memory.loadMemory();
        String offset = "#x000002ce";
        String[] arrStr = new String[]{"0", "imei", "0"};
        boolean[] taintArr = new boolean[]{false, true, false};
        allocateArray(offset, arrStr, taintArr);
    }
}
