package com.example.joans.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseReportCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);

        Button createActivity = (Button) findViewById(R.id.choseActivity);
        createActivity.setOnClickListener(createActivityListener);

        Button createReport = (Button) findViewById(R.id.choseReport);
        createReport.setOnClickListener(createReportListener);
    }

    private View.OnClickListener createActivityListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ChooseReportCreate.this, CreateActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener createReportListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ChooseReportCreate.this, CreateReport.class);
            startActivity(intent);
        }
    };

}