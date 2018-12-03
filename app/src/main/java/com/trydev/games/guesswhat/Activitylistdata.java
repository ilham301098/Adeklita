package com.trydev.games.guesswhat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Activitylistdata extends AppCompatActivity {
    TextView listdata;
    TextView isi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);

        listdata = findViewById(R.id.listdata);
        isi = findViewById(R.id.isiData);

        Intent intent = getIntent();
        String receivedName =  intent.getStringExtra("name");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String desc = databaseAccess.getDesc(receivedName);

        listdata.setText(receivedName);
        isi.setText(desc);
        //enable back Button
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
