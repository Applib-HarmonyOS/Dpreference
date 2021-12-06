package me.dozen.dpreference;

import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

/**
 * Created by wangyida on 15/12/18.
 */
class PreferenceImpl implements IPrefImpl {
    private Context mContext;
    private String mPrefName;

    public PreferenceImpl(Context context, String prefName) {
        mContext = context;
        mPrefName = prefName;
    }

    public String getPrefString(final String key, final String defaultValue) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        return settings.getString(key, defaultValue);
    }

    public void setPrefString(final String key, final String value) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        settings.putString(key, value).flush();
    }

    public boolean getPrefBoolean(final String key, final boolean defaultValue) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        return settings.getBoolean(key, defaultValue);
    }

    public boolean hasKey(final String key) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        return databaseHelper.getPreferences(mPrefName).hasKey(key);
    }

    public void setPrefBoolean(final String key, final boolean value) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        settings.putBoolean(key, value).flush();
    }

    public void setPrefInt(final String key, final int value) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        settings.putInt(key, value).flush();
    }

    public void increasePrefInt(final String key) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        increasePrefInt(settings, key);
    }

    public void increasePrefInt(final Preferences sp, final String key) {
        final int v = sp.getInt(key, 0) + 1;
        sp.putInt(key, v).flush();
    }

    public void increasePrefInt(final Preferences sp, final String key, final int increment) {
        final int v = sp.getInt(key, 0) + increment;
        sp.putInt(key, v).flush();
    }

    public int getPrefInt(final String key, final int defaultValue) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        return settings.getInt(key, defaultValue);
    }

    public void setPrefFloat(final String key, final float value) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        settings.putFloat(key, value).flush();
    }

    public float getPrefFloat(final String key, final float defaultValue) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        return settings.getFloat(key, defaultValue);
    }

    public void setPrefLong(final String key, final long value) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        settings.putLong(key, value).flush();
    }

    public long getPrefLong(final String key, final long defaultValue) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences settings = databaseHelper.getPreferences(mPrefName);
        return settings.getLong(key, defaultValue);
    }

    public void removePreference(final String key) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        final Preferences prefs = databaseHelper.getPreferences(mPrefName);
        prefs.delete(key).flush();
    }

    public void clearPreference(final Preferences p) {
        final Preferences editor = p;
        editor.clear();
        editor.flush();
    }
}
