package com.example.joans.timetracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.TextView;

import static android.R.id.list;

/**
 * Created by Vernon on 13/01/2017.
 */

public class CreateReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

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
    TextView reportDisplay;
    WebView reportWebDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        // reportDisplay = (TextView) findViewById((R.id.reportTextField));
        reportWebDisplay = (WebView) findViewById((R.id.reportWebField));
        String myReport = new String();

        //reportDisplay.setText(myReport);
        Log.d(tag, "will send create report intent to gestor");
        Intent createReportIntent = new Intent(GestorArbreActivitats.CREATE_REPORT);
        sendBroadcast(createReportIntent);
    }

    public class Receptor extends BroadcastReceiver {
        @Override
        public final void onReceive(final Context context, final Intent intent) {
            Log.d(tag, "onReceive From createactivity");
            if (intent.getAction().equals(GestorArbreActivitats.CREATE_REPORT_DONE)) {
                
                String myReport = new String();
                myReport = intent.getStringExtra("reportContent");

                //myTextView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
                //reportDisplay.setText(Html.fromHtml(myReport));
                //reportDisplay.setText(myReport);

                reportWebDisplay.loadDataWithBaseURL(null, myReport, "text/html", "utf-8", null);

                Log.d(tag, "report done");
                Log.d(tag, "report done");
                Log.d(tag, "report content was:" + myReport);
                System.out.println(myReport);
            }
            Log.i(tag, "onReceive From createactivity end");
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
