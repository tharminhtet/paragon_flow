package com.tharminhtet.paragonflow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tharminhtet.paragonflow.data.InputContract;

import static com.tharminhtet.paragonflow.R.id.fab_create;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText mDayEditText;
    private EditText mMonthEditText;
    private EditText mYearEditText;
    private Spinner mBranchSpinner;

    private static String dayString;
    private static String monthString;
    private static String yearString;

    private int mBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBranchSpinner = (Spinner) findViewById(R.id.spinner_branch);
        setupSpinner();

        FloatingActionButton fabCreate = (FloatingActionButton) findViewById(R.id.fab_create);
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDayEditText = (EditText) findViewById(R.id.edit_day);
                mMonthEditText = (EditText) findViewById(R.id.edit_month);
                mYearEditText = (EditText) findViewById(R.id.edit_year);

                dayString = mDayEditText.getText().toString().trim();
                monthString = mMonthEditText.getText().toString().trim();
                yearString = mYearEditText.getText().toString().trim();

                if (((dayString.length() == 0 ) || (monthString.length() == 0) || (yearString.length() == 0))) {
                    Context context = getApplicationContext();
                    CharSequence text = "Date not completed!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Intent manageFinanceIntent = new Intent(MainActivity.this, ManageFinanceActivity.class);
                    manageFinanceIntent.putExtra("dayString", dayString);
                    manageFinanceIntent.putExtra("monthString", monthString);
                    manageFinanceIntent.putExtra("yearString", yearString);
                    manageFinanceIntent.putExtra("branchInt", mBranch);
                    startActivity(manageFinanceIntent);
                }
            }
        });

        FloatingActionButton fabFinance = (FloatingActionButton) findViewById(R.id.fab_finance);
        fabFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDayEditText = (EditText) findViewById(R.id.edit_day);
                mMonthEditText = (EditText) findViewById(R.id.edit_month);
                mYearEditText = (EditText) findViewById(R.id.edit_year);

                dayString = mDayEditText.getText().toString().trim();
                monthString = mMonthEditText.getText().toString().trim();
                yearString = mYearEditText.getText().toString().trim();

                if (((dayString.length() == 0 ) || (monthString.length() == 0) || (yearString.length() == 0))) {
                    Context context = getApplicationContext();
                    CharSequence text = "Date not completed!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Intent FinanceIntent = new Intent(MainActivity.this, FinanceActivity.class);
                    FinanceIntent.putExtra("dayString", dayString);
                    FinanceIntent.putExtra("monthString", monthString);
                    FinanceIntent.putExtra("yearString", yearString);
                    startActivity(FinanceIntent);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter branchSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_branch_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        branchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mBranchSpinner.setAdapter(branchSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mBranchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.branch_1))) {
                        mBranch = InputContract.InputEntry.BRANCH_1;
                    }else if (selection.equals(getString(R.string.branch_2))) {
                        mBranch = InputContract.InputEntry.BRANCH_2;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mBranch = InputContract.InputEntry.BRANCH_UNKNOWN;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_finance) {
            Intent financeIntent = new Intent(MainActivity.this, FinanceActivity.class);
            startActivity(financeIntent);
        } else if (id == R.id.nav_staff) {

        } else if (id == R.id.nav_services) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
