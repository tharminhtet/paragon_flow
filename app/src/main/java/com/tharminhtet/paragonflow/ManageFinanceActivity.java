package com.tharminhtet.paragonflow;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tharminhtet.paragonflow.data.InputContract;
import com.tharminhtet.paragonflow.data.InputDbHelper;

import java.util.ArrayList;

import static com.tharminhtet.paragonflow.R.id.fab_create;

/**
 * Created by Thar Min Htet on 8/5/2017.
 */

public class ManageFinanceActivity extends AppCompatActivity {

    String dayString;
    String monthString;
    String yearString;
    int mBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_finance);

        //Passing these from the previous activity, only after onCreate
        Bundle extras = getIntent().getExtras();
        dayString = extras.getString("dayString");
        monthString = extras.getString("monthString");
        yearString = extras.getString("yearString");
        mBranch = extras.getInt("branchInt");

        TextView branchView = (TextView) findViewById(R.id.info_branch);
        TextView dateView = (TextView) findViewById(R.id.info_date);
        branchView.setText("Branch " + mBranch);
        dateView.setText(dayString + "/" + monthString + "/" + yearString);

        displayDataInfo();

        FloatingActionButton addFinance = (FloatingActionButton) findViewById(R.id.AddFinance);
        addFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*If date is blank, give a toast. Else, go to other activity */

                if (((dayString.length() == 0 ) || (monthString.length() == 0) || (yearString.length() == 0))) {
                    Context context = getApplicationContext();
                    CharSequence text = "Date not completed!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Intent addIntent = new Intent(ManageFinanceActivity.this, InputEditorActivity.class);
                    addIntent.putExtra("dayString", dayString);
                    addIntent.putExtra("monthString", monthString);
                    addIntent.putExtra("yearString", yearString);
                    addIntent.putExtra("branchInt", mBranch);
                    startActivity(addIntent);
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDataInfo();
    }

    private void displayDataInfo() {

        String[] projection = {
                InputContract.InputEntry._ID,
                InputContract.InputEntry.COLUMN_STAFF,
                InputContract.InputEntry.COLUMN_SERVICE,
                InputContract.InputEntry.COLUMN_PRICE};



        String selection = InputContract.InputEntry.COLUMN_DAY + " =? AND "
                        + InputContract.InputEntry.COLUMN_MONTH + " =? AND "
                        + InputContract.InputEntry.COLUMN_YEAR + " =? AND "
                        + InputContract.InputEntry.COLUMN_BRANCH + " =? ";

        String[] selectionArgs = new String[]{dayString, monthString, yearString, Integer.toString(mBranch)};


        Cursor cursor = getContentResolver().query(
            InputContract.InputEntry.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null);

        final ArrayList<Input> inputs = new ArrayList<Input>();

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InputContract.InputEntry._ID);
            int staffColumnIndex = cursor.getColumnIndex(InputContract.InputEntry.COLUMN_STAFF);
            int serviceColumnIndex = cursor.getColumnIndex(InputContract.InputEntry.COLUMN_SERVICE);
            int priceColumnIndex = cursor.getColumnIndex(InputContract.InputEntry.COLUMN_PRICE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentStaff= cursor.getString(staffColumnIndex);
                String currentService = cursor.getString(serviceColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);


                inputs.add(new Input(currentStaff, currentService, currentPrice));

            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

        InputAdapter adapter = new InputAdapter(this, inputs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
