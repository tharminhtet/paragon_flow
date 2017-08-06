package com.tharminhtet.paragonflow;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tharminhtet.paragonflow.data.InputContract;
import com.tharminhtet.paragonflow.data.InputDbHelper;

/**
 * Created by Thar Min Htet on 8/5/2017.
 */

public class viewByDayActivity extends AppCompatActivity{

    private InputDbHelper mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_by_day);

        mDbHelper = new InputDbHelper(this);
    }

    private void calcTotal(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                InputContract.InputEntry.COLUMN_PRICE
        };

        Cursor cursor = db.query(
                InputContract.InputEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        TextView totalView = (TextView) findViewById(R.id.total_view);
        try {
            int priceColumnIndex = cursor.getColumnIndex(InputContract.InputEntry.COLUMN_PRICE);

            int total = 0;
            while (cursor.moveToNext()) {
                int currentPrice = cursor.getInt(priceColumnIndex);
                total += currentPrice;
            }

            totalView.setText(""+total);
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
