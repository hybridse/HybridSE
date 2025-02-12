package main.corana.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;

public class Logs {
    private static String output = "./output.out";
    private static FileWriter fileWriter;
    private static final int processNo = 0;
    private static boolean toFile = false;
    private static boolean isLog = true;
    private static Logger LOGGER = Logger.getLogger(Logs.class.getName());
    private String currentMethodName = "";
    Logger  currentLog;


    static {
        class LogFormatter extends Formatter {
            @Override
            public String format(LogRecord record) {
                StringBuilder builder = new StringBuilder();
                //builder.append(record.getLevel() + ": ");
                builder.append(formatMessage(record));
                // builder.append(System.lineSeparator());
                //builder.append(System.getProperty("line.separator"));
                return builder.toString();
            }
        }
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
        LOGGER.addHandler(handler);
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);

        LOGGER.setLevel(Level.INFO);
        LOGGER.setUseParentHandlers(false);
    }
    public static void setLog(boolean showLog) {
        isLog = showLog;
    }
    public static void logFile(String name) {
        output = name;
        toFile = true;
        if (toFile) {
            try {
                fileWriter = new FileWriter(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void infoLn(Object o) {
        info(o);
        info(System.getProperty("line.separator"));
    }

    public static void logTaint(Object o) {

    }
    public static void infoLn(Object... os) {
        if (os.length == 0) {
            infoLn("");
        }
        for (Object o : os) {
            infoLn(o);
        }
    }

    public static void info(Object o) {
        if (!isLog) return;
        if (toFile) {
            try {
                fileWriter.write(toStr(o));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.log(Level.INFO, toStr(o));
    }

    public static void info(Object... os) {
        if (!isLog) return;
        for (int i = 0; i < os.length; i++) {
            info(os[i]);
            if (i < os.length - 1) {
                info(" ");
            }
        }
    }

    public static void closeLog() {
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String toStr(Object o) {
        return o == null ? "null" : o.toString();
    }

    public static String shorten(String s) {
        return s.length() > 100 ? s.substring(s.length() - 80) : s;
    }
}