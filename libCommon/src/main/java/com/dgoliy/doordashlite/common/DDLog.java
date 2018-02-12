package com.dgoliy.doordashlite.common;

import android.util.Log;

/**
 * Created by dgoliy on 2/11/18.
 */

public class DDLog {
    private static boolean isEnabled = false;

    public static void enable() {
        isEnabled = true;
    }

    public static void d(String tag, String message, Object... args) {
        if (isEnabled) {
            Log.d(tag, String.format(message, args));
        }
    }

    public static void e(String tag, String message, Object... args) {
        if (isEnabled) {
            Log.e(tag, String.format(message, args));
        }
    }
}
