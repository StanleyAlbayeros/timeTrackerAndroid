package com.example.joans.timetracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Vernon on 13/01/2017.
 */

public class CreateReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onStop() {
        unregisterReceiver(receptor);
        super.onStop();
    }


    public static final String CREATE_REPORT = "Create_report";
    public static final String CREATE_REPORT_DONE = "Create_report_done";
    private final String tag = this.getClass().getSimpleName();
    WebView reportWebDisplay;
    String myReport;
    Spinner reportTypeSpinner;
    int reportTypeSpinnerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        reportWebDisplay = (WebView) findViewById((R.id.reportWebField));
        myReport = new String();
        reportTypeSpinner = (Spinner) findViewById((R.id.CreateReportTypeSpinner));

        reportTypeSpinnerIndex = reportTypeSpinner.getSelectedItemPosition();
        String[] size_values = getResources().getStringArray(R.array.createReportListSpinnerValues);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, size_values);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reportTypeSpinner.setOnItemSelectedListener(this);



        Log.d(tag, "will send create report intent to gestor");
        Intent createReportIntent = new Intent(GestorArbreActivitats.CREATE_REPORT);
        createReportIntent.putExtra("reportType", "simpleTXT");
        sendBroadcast(createReportIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        reportTypeSpinnerIndex = reportTypeSpinner.getSelectedItemPosition();
        String[] size_values = getResources().getStringArray(R.array.createReportListSpinnerValues);
        String reportType = size_values[reportTypeSpinnerIndex].toString();
        Intent createReportIntent = new Intent(GestorArbreActivitats.CREATE_REPORT);
        createReportIntent.putExtra("reportType", reportType);
        sendBroadcast(createReportIntent);
    }

    public class Receptor extends BroadcastReceiver {
        @Override
        public final void onReceive(final Context context, final Intent intent) {
            Log.d(tag, "onReceive From createReport");
            if (intent.getAction().equals(GestorArbreActivitats.CREATE_REPORT_DONE)) {

                myReport = intent.getStringExtra("reportContent");

                reportWebDisplay.loadDataWithBaseURL(null, myReport, "text/html", "utf-8", null);

                Log.d(tag, "report done");
                Log.d(tag, "report done");
                Log.d(tag, "report content was:" + myReport);
                System.out.println(myReport);
            }
            Log.i(tag, "onReceive From createReport end");
        }
    }

    private Receptor receptor;

    @Override
    public final void onResume() {

        Log.i(tag, "onResume");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GestorArbreActivitats.CREATE_REPORT_DONE);
        receptor = new Receptor();
        registerReceiver(receptor, intentFilter);

        Intent receiverService = new Intent(this, GestorArbreActivitats.class);

        startService(receiverService);
        super.onResume();
    }
}
