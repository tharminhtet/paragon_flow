package com.tharminhtet.paragonflow;

/**
 * Created by Thar Min Htet on 8/5/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class FinanceActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance);

        TextView viewByDay = (TextView) findViewById(R.id.viewByDay);

        viewByDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewByDayIntent = new Intent(FinanceActivity.this, viewByDayActivity.class);
                startActivity(viewByDayIntent);
            }
        });
    }
}