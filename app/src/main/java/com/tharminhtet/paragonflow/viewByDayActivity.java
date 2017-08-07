package com.tharminhtet.paragonflow;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.tharminhtet.paragonflow.data.InputContract;
import com.tharminhtet.paragonflow.data.InputDbHelper;

/**
 * Created by Thar Min Htet on 8/5/2017.
 */

public class viewByDayActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_by_day);
        calcTotal();
    }

    private void calcTotal(){
        String[] projection = {
                InputContract.InputEntry.COLUMN_PRICE
        };

        Cursor cursor = getContentResolver().query(
                InputContract.InputEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
        TextView totalView = (TextView) findViewById(R.id.total_view);
        try {
            int priceColumnIndex = cursor.getColumnIndex(InputContract.InputEntry.COLUMN_PRICE);

            int total = 0;
            while (cursor.moveToNext()) {
                int currentPrice = cursor.getInt(priceColumnIndex);
                total += currentPrice;
            }
            final String TAG = "MyActivity";

            totalView.setText(""+total);
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
