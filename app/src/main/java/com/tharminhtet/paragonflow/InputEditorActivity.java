package com.tharminhtet.paragonflow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.tharminhtet.paragonflow.data.InputContract;
import com.tharminhtet.paragonflow.data.InputDbHelper;



/**
 * Created by Thar Min Htet on 8/6/2017.
 */

public class InputEditorActivity extends AppCompatActivity {

    private EditText mStaffEditText;
    private EditText mServiceEditText;
    private EditText mPriceEditText;

    //Passing these from the previous activity
    String dayString;
    String monthString;
    String yearString;
    int mBranch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_editor);

        //passing from previous activity
        Intent in = getIntent();
        dayString = in.getExtras().getString("dayString");
        monthString = in.getExtras().getString("monthString");
        yearString = in.getExtras().getString("yearString");
        mBranch = in.getExtras().getInt("branchInt");

        mStaffEditText = (EditText) findViewById(R.id.edit_staff);
        mServiceEditText = (EditText) findViewById(R.id.edit_service);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
    }

    private void insertInput() {

        String staffString = mStaffEditText.getText().toString().trim();
        String serviceString = mServiceEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);

        int day = Integer.parseInt(dayString);
        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);


        InputDbHelper mDbHelper = new InputDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InputContract.InputEntry.COLUMN_STAFF, staffString);
        values.put(InputContract.InputEntry.COLUMN_SERVICE, serviceString);
        values.put(InputContract.InputEntry.COLUMN_PRICE, price);
        values.put(InputContract.InputEntry.COLUMN_DAY, day);
        values.put(InputContract.InputEntry.COLUMN_MONTH, month);
        values.put(InputContract.InputEntry.COLUMN_YEAR, year);
        values.put(InputContract.InputEntry.COLUMN_BRANCH, mBranch);

        Uri newUri = getContentResolver().insert(InputContract.InputEntry.CONTENT_URI, values);

        if (newUri == null) {
            // If the new content URI is null, then there was an error with insertion.
            Toast.makeText(this, "Error saving input",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(this, "Input saved",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                insertInput();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
