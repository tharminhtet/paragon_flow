package com.tharminhtet.paragonflow.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Thar Min Htet on 8/6/2017.
 */

public final class InputContract {
    private InputContract(){};

    public static final String CONTENT_AUTHORITY = "com.tharminhtet.paragonflow.data";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_INPUTS = "inputs";



    public static final class InputEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INPUTS);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INPUTS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INPUTS;


        public final static String TABLE_NAME = "input";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_STAFF = "staff";
        public final static String COLUMN_SERVICE = "service";
        public final static String COLUMN_PRICE = "price";

        /* Date */
        public final static String COLUMN_DAY = "day";
        public final static String COLUMN_MONTH = "month";
        public final static String COLUMN_YEAR = "year";

    }
}
