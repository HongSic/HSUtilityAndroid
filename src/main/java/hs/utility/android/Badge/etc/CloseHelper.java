package hs.utility.android.Badge.etc;

import android.database.Cursor;

/**
 * @author Leolin
 * Cursor close Helper
 */
public class CloseHelper {
    public static void close(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
