package com.tharminhtet.paragonflow.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thar Min Htet on 8/6/2017.
 */

public class InputDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "paragon.db";
    private static final int DATABASE_VERSION = 1;
    public InputDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INPUT_TABLE = "CREATE TABLE " + InputContract.InputEntry.TABLE_NAME + " ("
                + InputContract.InputEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InputContract.InputEntry.COLUMN_STAFF + " TEXT NOT NULL, "
                + InputContract.InputEntry.COLUMN_SERVICE + " TEXT NOT NULL, "
                + InputContract.InputEntry.COLUMN_PRICE + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_INPUT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
