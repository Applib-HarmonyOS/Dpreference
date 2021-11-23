package me.dozen.dpreference;

import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.app.Context;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.net.Uri;
import java.util.Arrays;

/**
 * Created by wangyida on 15/12/18.
 */
class PrefAccessor {
    private static final HiLogLabel HILOG_LABEL = new HiLogLabel(0, 0, "prefaccessor");

    private PrefAccessor() {
        throw new IllegalStateException("PrefAccessor class");
    }

    public static String getString(Context context, String name, String key, String defaultValue) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_STRING);
        String value = defaultValue;
        ResultSet cursor = null;
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        dataAbilityPredicates.setOrder(null);
        try {
            cursor = DataAbilityHelper.creator(context).query(uri, null, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in getString");
        }
        if (cursor != null && cursor.goToFirstRow()) {
            value = cursor.getString(cursor.getColumnIndexForName(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static int getInt(Context context, String name, String key, int defaultValue) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_INT);
        int value = defaultValue;
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        ResultSet cursor = null;
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        dataAbilityPredicates.setOrder(null);
        try {
            cursor = DataAbilityHelper.creator(context).query(uri, null, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in getInt");
        }
        if (cursor != null && cursor.goToFirstRow()) {
            value = cursor.getInt(cursor.getColumnIndexForName(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static long getLong(Context context, String name, String key, long defaultValue) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_LONG);
        long value = defaultValue;
        ResultSet cursor = null;
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        dataAbilityPredicates.setOrder(null);
        try {
            cursor = DataAbilityHelper.creator(context).query(uri, null, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in getLong");
        }
        if (cursor != null && cursor.goToFirstRow()) {
            value = cursor.getLong(cursor.getColumnIndexForName(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static boolean getBoolean(Context context, String name, String key, boolean defaultValue) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_BOOLEAN);
        int value = defaultValue ? 1 : 0;
        ResultSet cursor = null;
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        dataAbilityPredicates.setOrder(null);
        try {
            cursor = DataAbilityHelper.creator(context).query(uri, null, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in getBoolean");
        }
        if (cursor != null && cursor.goToFirstRow()) {
            value = cursor.getInt(cursor.getColumnIndexForName(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value == 1;
    }

    public static void remove(Context context, String name, String key) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_STRING);
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        try {
            DataAbilityHelper.creator(context).delete(uri, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in remove");
        }
    }

    public static void setString(Context context, String name, String key, String value) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_STRING);
        ValuesBucket cv = new ValuesBucket();
        cv.putString(PreferenceProvider.PREF_KEY, key);
        cv.putString(PreferenceProvider.PREF_VALUE, value);
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        try {
            DataAbilityHelper.creator(context).update(uri, cv, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in getIntFromAttr");
        }
    }

    public static void setBoolean(Context context, String name, String key, boolean value) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_BOOLEAN);
        ValuesBucket cv = new ValuesBucket();
        cv.putString(PreferenceProvider.PREF_KEY, key);
        cv.putBoolean(PreferenceProvider.PREF_VALUE, value);
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        try {
            DataAbilityHelper.creator(context).update(uri, cv, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in setBoolean");
        }
    }

    public static void setInt(Context context, String name, String key, int value) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_INT);
        ValuesBucket cv = new ValuesBucket();
        cv.putString(PreferenceProvider.PREF_KEY, key);
        cv.putInteger(PreferenceProvider.PREF_VALUE, value);
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        try {
            DataAbilityHelper.creator(context).update(uri, cv, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in setInt");
        }
    }

    public static void setLong(Context context, String name, String key, long value) {
        Uri uri = PreferenceProvider.buildUri(name, key, PreferenceProvider.PREF_LONG);
        ValuesBucket cv = new ValuesBucket();
        cv.putString(PreferenceProvider.PREF_KEY, key);
        cv.putLong(PreferenceProvider.PREF_VALUE, value);
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates((java.lang.String) null);
        dataAbilityPredicates.setWhereArgs(Arrays.asList());
        try {
            DataAbilityHelper.creator(context).update(uri, cv, dataAbilityPredicates);
        } catch (DataAbilityRemoteException e) {
            HiLog.error(HILOG_LABEL, "exception in setLong");
        }
    }
}
