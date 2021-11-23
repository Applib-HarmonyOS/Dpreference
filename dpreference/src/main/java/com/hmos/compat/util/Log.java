package com.hmos.compat.util;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

/**
 * Log utils.
 *
 * @since 2020-03-01
 */
public class Log {
    private static final String TAG_LOG = "HMOSCompat";
    private static final int DOMAIN_ID = 0xD000F00;
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, DOMAIN_ID, Log.TAG_LOG);
    private static final String LOG_FORMAT = "%{public}s: %{public}s";
    public static final int VERBOSE = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int DEBUG = 4;
    public static final int ERROR = 5;

    private Log() { }

    /**
     * Print debug log.
     *
     * @param tag log tag
     * @param msg log message
     */
    public static int d(String tag, String msg) {
        return HiLog.debug(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static int d(String tag, String msg, Throwable th) {
        return HiLog.debug(LABEL_LOG, LOG_FORMAT, tag, msg, th);
    }

    /**
     * Print info log.
     *
     * @param tag log tag
     * @param msg log message
     */
    public static int i(String tag, String msg) {
        return HiLog.info(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static int i(String msg, Throwable th) {
        return HiLog.info(LABEL_LOG, LOG_FORMAT, msg, th);
    }

    /**
     * Print warn log.
     *
     * @param tag log tag
     * @param msg log message
     */
    public static int w(String tag, String msg) {
        return HiLog.warn(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return HiLog.warn(LABEL_LOG, LOG_FORMAT, tag, msg, tr);
    }

    public static int w(String tag, Throwable tr) {
        return HiLog.warn(LABEL_LOG, LOG_FORMAT, tag, tr);
    }

    /**
     * Print error log.
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void e(String tag, String msg) {
        HiLog.error(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return HiLog.error(LABEL_LOG, LOG_FORMAT, tag, msg, tr);
    }

    public static String getStackTraceString(Throwable tr) {
        return HiLog.getStackTrace(tr);
    }

    public static boolean isLoggable(String tag, int level) {
        return HiLog.isLoggable(DOMAIN_ID, tag, level);
    }

    public static void v(String tag, String msg) {
        HiLog.warn(LABEL_LOG, LOG_FORMAT, tag, msg);
    }
}
