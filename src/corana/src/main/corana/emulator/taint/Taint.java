package main.corana.emulator.taint;

import java.util.ArrayList;
import java.util.List;

public class Taint {
    private static List<String> sources;
    private static List<String> sinks;

    static {
        sources = new ArrayList<>();
        sources.add("getDeviceId"); // android/telephony/TelephonyManager
        sources.add("s_/proc/version");

        sinks = new ArrayList<>();
        sinks.add("_android_log_print");
    }
    public static boolean isSource(String method) {
        for (String source : sources) {
            if (isSame(method, source)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isSame(String method, String source) {
        return method.toLowerCase().contains(source.toLowerCase());
    }
}
