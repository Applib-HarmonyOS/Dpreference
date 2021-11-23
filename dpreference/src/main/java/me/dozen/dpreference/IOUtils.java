package me.dozen.dpreference;

import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

final class IOUtils {
    private static final HiLogLabel HILOG_LABEL_1 = new HiLogLabel(0, 0, "ioutils");
    // NOTE: This class is focussed on InputStream, OutputStream, Reader and
    // Writer. Each method should take at least one of these as a parameter,
    // or return one of them.
    private IOUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void closeQuietly(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                HiLog.error(HILOG_LABEL_1, "IOException in closeQuietly when the parameter: is");
            }
        }
    }

    public static void closeQuietly(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                HiLog.error(HILOG_LABEL_1, "closeQuietly IOException - parameter: os");
            }
        }
    }

    public static void closeQuietly(Reader r) {
        if (r != null) {
            try {
                r.close();
            } catch (IOException e) {
                HiLog.error(HILOG_LABEL_1, "IOException for closeQuietly - parameter: r");
            }
        }
    }

    public static void closeQuietly(ResultSet cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
