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

    String dayString;
    String monthString;
    String yearString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_by_day);

        Bundle extras = getIntent().getExtras();
        dayString = extras.getString("dayString");
        monthString = extras.getString("monthString");
        yearString = extras.getString("yearString");

        TextView dateView = (TextView) findViewById(R.id.finance_day_date);
        dateView.setText(dayString + "/" + monthString + "/" + yearString);


        calcTotal(1);
        calcTotal(2);
    }

    private void calcTotal(int branch){
        String[] projection = {
                InputContract.InputEntry.COLUMN_PRICE
        };

        String selection = InputContract.InputEntry.COLUMN_DAY + " =? AND "
                + InputContract.InputEntry.COLUMN_MONTH + " =? AND "
                + InputContract.InputEntry.COLUMN_YEAR + " =? AND "
                + InputContract.InputEntry.COLUMN_BRANCH + " =? ";
        String[] selectionArgs = new String[]{dayString, monthString, yearString, Integer.toString(branch)};

        Cursor cursor = getContentResolver().query(
                InputContract.InputEntry.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);

        TextView totalView;
        if (branch == 1) {
            totalView = (TextView) findViewById(R.id.branch_1_total_view);
        }else {
            totalView = (TextView) findViewById(R.id.branch_2_total_view);
        }

        try {
            int priceColumnIndex = cursor.getColumnIndex(InputContract.InputEntry.COLUMN_PRICE);

            int total = 0;
            while (cursor.moveToNext()) {
                int currentPrice = cursor.getInt(priceColumnIndex);
                total += currentPrice;
            }

            totalView.setText("Branch " + branch + " : " +total + "  Kyats");
        } finally {
            cursor.close();
        }
    }
}
