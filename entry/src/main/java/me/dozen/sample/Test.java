package me.dozen.sample;

import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import me.dozen.dpreference.DPreference;

/**
 * Created by wangyida on 15/12/18.
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
    public static void testDPreference(Context context) {
        // this Preference comes for free from the library
        final DPreference dPreference = new DPreference(context, "dpref");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dPreference.setPrefString(TESTSTRING, TESTPREF);
        }
        long end = System.currentTimeMillis();
        HiLog.debug(HI_LOG_LABEL, "DPreferecne called setString 1000 times cost : " + (end - start));
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dPreference.getPrefString(TESTSTRING, TESTPREF);
            HiLog.debug(HI_LOG_LABEL, "DPreferecne every seconds : "
                    + dPreference.getPrefString(TESTSTRING, TESTPREF));
        }
        long end1 = System.currentTimeMillis();
        HiLog.debug(HI_LOG_LABEL, "DPreferecne called getString 1000 times cost : " + (end1 - start1));
        boolean b = dPreference.getPrefBoolean(TESTBOOLEAN, false);
        HiLog.debug(HI_LOG_LABEL, " getboolean default : " + b);
        dPreference.setPrefBoolean(TESTBOOLEAN, true);
        HiLog.debug(HI_LOG_LABEL, " getPrefBoolean : " + dPreference.getPrefBoolean(TESTBOOLEAN, false));
        int v = dPreference.getPrefInt(TESTINT, 0);
        HiLog.debug(HI_LOG_LABEL, " getInt default : " + v);
        dPreference.setPrefInt(TESTINT, 20);
        HiLog.debug(HI_LOG_LABEL, " getPrefInt : " + dPreference.getPrefInt(TESTINT, 0));
        long l = dPreference.getPrefLong(TESTLONG, 1);
        HiLog.debug(HI_LOG_LABEL, " getPrefLong default : " + l);
        dPreference.setPrefLong(TESTLONG, 100);
        HiLog.debug(HI_LOG_LABEL, " setPrefLong : " + dPreference.getPrefLong(TESTLONG, 1));
    }
}