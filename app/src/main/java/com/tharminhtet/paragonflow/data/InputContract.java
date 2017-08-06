package com.tharminhtet.paragonflow.data;

import android.provider.BaseColumns;

/**
 * Created by Thar Min Htet on 8/6/2017.
 */

public final class InputContract {
    private InputContract(){};

    public static final class InputEntry implements BaseColumns{
        public final static String TABLE_NAME = "input";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_STAFF = "staff";
        public final static String COLUMN_SERVICE = "service";
        public final static String COLUMN_PRICE = "price";

    }
}
