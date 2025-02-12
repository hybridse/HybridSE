package main.corana.executor;

import main.corana.capstone.Capstone;
import main.corana.enums.ExecutionMode;

public class Configs {
    public static int RANDOM_SEED = 0;
    public static String smtFuncs = "smt-funcs";

    public static int instructionSize = 4;
    public static int executionMode = Capstone.CS_MODE_THUMB;
    public static String neo4jUser = "neo4j";
    public static String neo4jPassword = "password";

    public static int architecture = Integer.SIZE;
    public static String topStack =  "#xbefffc08"; //"sp_SYM" #xff0000000, befffc88
    public static String argc = "r0_SYM"; //"#x00000001";
    public static String jniOffset = "#x00000000";
    public static int envVarCount = 17; // default in GCC
    public static int wordSize = architecture / 8;

    public static byte getCharSize() {
        return 1;
    }

    public static byte getShortSize() {
        return 2;
    }

    public static byte getIntSize() {
        return 4;
    }

    public static byte getLongSize() {
        return (byte) ((byte) architecture / 8);
    }

    public static int switchExecutionMode() {
        if (Configs.executionMode == Capstone.CS_MODE_THUMB) {
            Configs.executionMode = Capstone.CS_MODE_ARM;
        } else {
            Configs.executionMode = Capstone.CS_MODE_THUMB;
        }
        return Configs.executionMode;
    }
}
