package me.dozen.sample;

import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import me.dozen.dpreference.DPreference;

/**
 * This is a Test class.
 */
public class Test {
    private static final String TESTPREF = "testPref";
    private static final String TESTSTRING = "testString";
    private static final String TESTBOOLEAN = "test_boolean";
    private static final String TESTINT = "test_int";
    private static final String TESTLONG = "test_long";
    private static final String TAG = Test.class.getSimpleName();
    public static final HiLogLabel HI_LOG_LABEL = new HiLogLabel(0, 0, TAG);

    private Test() {
        throw new IllegalStateException("Test class");
    }

    /**
     * This is a testDPreference method.
     *
     * @param context Context
     */
    public static void testDpreference(Context context) {
        // this Preference comes for free from the library
        final DPreference dPreference = new DPreference(context, "dpref");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dPreference.setPrefString(TESTSTRING, TESTPREF);
        }
        long end = System.currentTimeMillis();
        HiLog.debug(HI_LOG_LABEL, "DPreference - setString called 1000 times cost : " + (end - start));
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dPreference.getPrefString(TESTSTRING, TESTPREF);
        }
        long end1 = System.currentTimeMillis();
        HiLog.debug(HI_LOG_LABEL, "DPreference - getString called 1000 times cost : " + (end1 - start1));
        boolean b = dPreference.getPrefBoolean(TESTBOOLEAN, false);
        HiLog.debug(HI_LOG_LABEL, "DPreference - default value of getBoolean : " + b);
        dPreference.setPrefBoolean(TESTBOOLEAN, true);
        HiLog.debug(HI_LOG_LABEL, "DPreference - value of getPrefBoolean : "
                + dPreference.getPrefBoolean(TESTBOOLEAN, false));
        int v = dPreference.getPrefInt(TESTINT, 0);
        HiLog.debug(HI_LOG_LABEL, "DPreference - default value of getInt : " + v);
        dPreference.setPrefInt(TESTINT, 20);
        HiLog.debug(HI_LOG_LABEL, "DPreference - value of getPrefInt : " + dPreference.getPrefInt(TESTINT, 0));
        long l = dPreference.getPrefLong(TESTLONG, 1);
        HiLog.debug(HI_LOG_LABEL, "DPreference - default value of getLong : " + l);
        dPreference.setPrefLong(TESTLONG, 100);
        HiLog.debug(HI_LOG_LABEL, "DPreference - value of getPrefLong : " + dPreference.getPrefLong(TESTLONG, 1));
    }
}