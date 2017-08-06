package com.tharminhtet.paragonflow;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tharminhtet.paragonflow.data.InputContract;
import com.tharminhtet.paragonflow.data.InputDbHelper;

import java.util.ArrayList;

import static com.tharminhtet.paragonflow.R.id.fab;

/**
 * Created by Thar Min Htet on 8/5/2017.
 */

public class ManageFinanceActivity extends AppCompatActivity {

    private InputDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_finance);

//        final ArrayList<Input> inputs = new ArrayList<Input>();
//        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
//        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
//        inputs.add(new Input("Wat Lay", "Shampoo", 3000));
//
//        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
//        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
//        inputs.add(new Input("Wat Lay", "Shampoo", 3000));
//
//        InputAdapter adapter = new InputAdapter(this, inputs);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(adapter);

        FloatingActionButton addFinance = (FloatingActionButton) findViewById(R.id.AddFinance);
        addFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Context context = getApplicationContext();
//                CharSequence text = "Haven't set the Activity yet!";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
                Intent addIntent = new Intent(ManageFinanceActivity.this, InputEditorActivity.class);
                startActivity(addIntent);
            }
        });
        mDbHelper = new InputDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDataInfo();
    }

    private void displayDataInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                InputContract.InputEntry._ID,
                InputContract.InputEntry.COLUMN_STAFF,
                InputContract.InputEntry.COLUMN_SERVICE,
                InputContract.InputEntry.COLUMN_PRICE};

        Cursor cursor = db.query(
            InputContract.InputEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
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

//                displayView.append(("\n" + currentID + " - " +
//                        currentName + " - " +
//                        currentBreed + " - " +
//                        currentGender + " - " +
//                        currentWeight));
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
