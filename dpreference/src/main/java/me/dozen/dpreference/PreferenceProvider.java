package me.dozen.dpreference;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.PathMatcher;
import ohos.aafwk.content.Intent;
import ohos.agp.utils.TextTool;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.data.resultset.TableResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.LightweightMap;
import ohos.utils.net.Uri;
import java.util.Map;

/**
 * Created by wangyida on 15/12/18.
 */
public class PreferenceProvider extends Ability {
    private static final String TAG = PreferenceProvider.class.getSimpleName();
    private static final String AUTHORITY = "me.dozen.dpreference.PreferenceProvider";
    private static final String ACTION = "values is null!!!";
    private static final String DATA_ABILITY = "dataability:///";
    public static final String CONTENT_PREF_BOOLEAN_URI =  DATA_ABILITY + AUTHORITY + "/boolean/";
    public static final String CONTENT_PREF_STRING_URI =  DATA_ABILITY + AUTHORITY + "/string/";
    public static final String CONTENT_PREF_INT_URI =  DATA_ABILITY + AUTHORITY + "/integer/";
    public static final String CONTENT_PREF_LONG_URI =  DATA_ABILITY + AUTHORITY + "/long/";
    public static final String PREF_KEY = "key";
    public static final String PREF_VALUE = "value";
    public static final int PREF_BOOLEAN = 1;
    public static final int PREF_STRING = 2;
    public static final int PREF_INT = 3;
    public static final int PREF_LONG = 4;
    public static final HiLogLabel HI_LOG_LABEL_2 = new HiLogLabel(0, 0, TAG);
    private static final PathMatcher sUriMatcher = new PathMatcher();

    static {
        sUriMatcher.addPath("boolean/*/*", PREF_BOOLEAN);
        sUriMatcher.addPath("string/*/*", PREF_STRING);
        sUriMatcher.addPath("integer/*/*", PREF_INT);
        sUriMatcher.addPath("long/*/*", PREF_LONG);
    }

    @Override
    public void onStart(Intent intent) {
        // empty method.
    }

    private int getPathId(String path) {
        if (path == null) {
            return -1;
        }
        if (path.contains("/boolean/")) {
            return PREF_BOOLEAN;
        } else if (path.contains("/string/")) {
            return PREF_STRING;
        } else if (path.contains("/integer/")) {
            return PREF_INT;
        } else if (path.contains("/long/")) {
            return PREF_LONG;
        }
        return -1;
    }

    @Override
    public ResultSet query(Uri uri, String[] projection, DataAbilityPredicates predicates) {
        TableResultSet cursor = null;
        PrefModel model = getPrefModelByUri(uri);
        String lastPath = PreferenceProvider.changeParamToPath(uri);
        switch (getPathId(lastPath)) {
            case PREF_BOOLEAN:
                if (getDPreference(model.getName()).hasKey(model.getKey())) {
                    cursor = preferenceToCursor(getDPreference(model.getName())
                            .getPrefBoolean(model.getKey(), false) ? 1 : 0);
                }
                break;
            case PREF_STRING:
                if (getDPreference(model.getName()).hasKey(model.getKey())) {
                    cursor = preferenceToCursor(getDPreference(model.getName()).getPrefString(model.getKey(), ""));
                }
                break;
            case PREF_INT:
                if (getDPreference(model.getName()).hasKey(model.getKey())) {
                    cursor = preferenceToCursor(getDPreference(model.getName()).getPrefInt(model.getKey(), -1));
                }
                break;
            case PREF_LONG:
                if (getDPreference(model.getName()).hasKey(model.getKey())) {
                    cursor = preferenceToCursor(getDPreference(model.getName()).getPrefLong(model.getKey(), -1));
                }
                break;
            default:
                // do nothing
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int insert(Uri uri, ValuesBucket values) {
        throw new IllegalStateException("insert unsupport!!!");
    }

    @Override
    public int delete(Uri uri, DataAbilityPredicates predicates) {
        String lastPath = PreferenceProvider.changeParamToPath(uri);
        switch (getPathId(lastPath)) {
            case PREF_BOOLEAN:
            case PREF_LONG:
            case PREF_STRING:
            case PREF_INT:
                break;
            default:
                throw new IllegalStateException(" unsupported uri : " + uri);
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ValuesBucket values, DataAbilityPredicates predicates) {
        PrefModel model = getPrefModelByUri(uri);
        String lastPath = PreferenceProvider.changeParamToPath(uri);
        HiLog.debug(HI_LOG_LABEL_2, "lastPath " + lastPath);
        switch (getPathId(uri.toString())) {
            case PREF_BOOLEAN:
                persistBoolean(model.getName(), values);
                break;
            case PREF_LONG:
                persistLong(model.getName(), values);
                break;
            case PREF_STRING:
                persistString(model.getName(), values);
                break;
            case PREF_INT:
                persistInt(model.getName(), values);
                break;
            default:
                throw new IllegalStateException("update unsupported uri : " + uri);
        }
        return 0;
    }

    private static String[] preferencecolumns = { PREF_VALUE };

    private <T> TableResultSet preferenceToCursor(T value) {
        TableResultSet matrixCursor = new TableResultSet(preferencecolumns, 1);
        ohos.data.resultset.TableResultSet.RowBuilder builder = matrixCursor.addRowByBuilder();
        builder.setColumnValue(value);
        return matrixCursor;
    }

    private void persistInt(String name, ValuesBucket values) {
        if (values == null) {
            throw new IllegalArgumentException(ACTION);
        }
        String kinteger = values.getString(PREF_KEY);
        int vinteger = values.getInteger(PREF_VALUE);
        getDPreference(name).setPrefInt(kinteger, vinteger);
    }

    private void persistBoolean(String name, ValuesBucket values) {
        if (values == null) {
            throw new IllegalArgumentException(ACTION);
        }
        String kboolean = values.getString(PREF_KEY);
        boolean vboolean = values.getBoolean(PREF_VALUE);
        getDPreference(name).setPrefBoolean(kboolean, vboolean);
    }

    private void persistLong(String name, ValuesBucket values) {
        if (values == null) {
            throw new IllegalArgumentException(ACTION);
        }
        String klong = values.getString(PREF_KEY);
        long vlong = values.getLong(PREF_VALUE);
        getDPreference(name).setPrefLong(klong, vlong);
    }

    private void persistString(String name, ValuesBucket values) {
        if (values == null) {
            throw new IllegalArgumentException(ACTION);
        }
        String kstring = values.getString(PREF_KEY);
        String vstring = values.getString(PREF_VALUE);
        getDPreference(name).setPrefString(kstring, vstring);
    }

    private static Map<String, IPrefImpl> sPreferences = new LightweightMap();

    private IPrefImpl getDPreference(String name) {
        if (TextTool.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("getDPreference name is null!!!");
        }
        if (sPreferences.get(name) == null) {
            IPrefImpl pref = new PreferenceImpl(getContext(), name);
            sPreferences.put(name, pref);
        }
        return sPreferences.get(name);
    }

    private PrefModel getPrefModelByUri(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("getPrefModelByUri uri is null : " + uri);
        }
        HiLog.debug(HI_LOG_LABEL_2, "PreferenceProvider", "" + uri.getDecodedPathList().size());
        uri.getDecodedPathList().forEach(u -> HiLog.debug(HI_LOG_LABEL_2, "PreferenceProvider", "item: " + u));
        if (uri.getDecodedPathList().size() != 4) {
            throw new IllegalArgumentException("getPrefModelByUri uri is wrong : " + uri);
        }
        String name = uri.getDecodedPathList().get(2);
        String key = uri.getDecodedPathList().get(3);
        return new PrefModel(name, key);
    }

    public static Uri buildUri(String name, String key, int type) {
        return ohos.utils.net.Uri.parse(getUriByType(type) + name + "/" + key);
    }

    private static String getUriByType(int type) {
        switch (type) {
            case PreferenceProvider.PREF_BOOLEAN:
                return PreferenceProvider.CONTENT_PREF_BOOLEAN_URI;
            case PreferenceProvider.PREF_INT:
                return PreferenceProvider.CONTENT_PREF_INT_URI;
            case PreferenceProvider.PREF_LONG:
                return PreferenceProvider.CONTENT_PREF_LONG_URI;
            case PreferenceProvider.PREF_STRING:
                return PreferenceProvider.CONTENT_PREF_STRING_URI;
            default:
                //do nothing
        }
        throw new IllegalStateException("unsupport preftype : " + type);
    }

    private static class PrefModel {
        String name;
        String key;

        public PrefModel(String name, String key) {
            this.name = name;
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public String getKey() {
            return key;
        }
    }

    public static String changeParamToPath(ohos.utils.net.Uri hmosUri) {
        return String.valueOf(hmosUri);
    }
}
