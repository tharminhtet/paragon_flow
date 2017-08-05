package com.tharminhtet.paragonflow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.tharminhtet.paragonflow.R.id.fab;

/**
 * Created by Thar Min Htet on 8/5/2017.
 */

public class ManageFinanceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_finance);

        final ArrayList<Input> inputs = new ArrayList<Input>();
        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
        inputs.add(new Input("Wat Lay", "Shampoo", 3000));

        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
        inputs.add(new Input("Wat Lay", "Shampoo", 3000));
        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
        inputs.add(new Input("Wat Lay", "Shampoo", 3000));
        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
        inputs.add(new Input("Wat Lay", "Shampoo", 3000));
        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
        inputs.add(new Input("Wat Lay", "Shampoo", 3000));
        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
        inputs.add(new Input("Wat Lay", "Shampoo", 3000));
        inputs.add(new Input("Kyaw Kyaw", "Hair Cut", 2000));
        inputs.add(new Input("Mg Mg", "Hair Dye", 15000));
        inputs.add(new Input("Wat Lay", "Shampoo", 3000));

        InputAdapter adapter = new InputAdapter(this, inputs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        FloatingActionButton addFinance = (FloatingActionButton) findViewById(R.id.AddFinance);
        addFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Haven't set the Activity yet!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
