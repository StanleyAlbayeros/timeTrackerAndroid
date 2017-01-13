package com.example.joans.timetracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.example.joans.timetracker.R.string.deadline;

/**
 * Created by Vernon on 12/01/2017.
 */
public class CreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String tag = this.getClass().getSimpleName();
    private EditText newActivityNameField;
    private EditText newActivityDescriptionField;
    private Spinner activityTypeSpinner;
    private LinearLayout linearLayout;
    private DatePicker deadlineDate;
    private TimePicker deadlineTime;
    private Button createActivityButton;

    public static final String CREATE_ACTIVITY = "Create_activity";
    public static final String CREATE_ACTIVITY_DONE = "Create_activity_done";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_create_newactivity);
        linearLayout = (LinearLayout) findViewById(R.id.CreateActivityLinearLayout);
        createActivityButton = (Button) findViewById(R.id.CreateActivityButton);
        newActivityNameField = (EditText) findViewById(R.id.CreateActivityNameTextField);
        newActivityDescriptionField = (EditText) findViewById(R.id.CreateActivityDescriptionTextField);
        activityTypeSpinner = (Spinner) findViewById(R.id.CreateActivityTypeSpinner);
        deadlineDate = (DatePicker) findViewById(R.id.DeadlineDate);
        deadlineTime = (TimePicker) findViewById(R.id.DeadlineTime);

        deadlineTime.setVisibility(View.GONE);
        deadlineDate.setVisibility(View.GONE);

        createActivityButton.setOnClickListener(createActivityButtonListener);
        int activitySpinnerIndex = activityTypeSpinner.getSelectedItemPosition();
        String[] size_values = getResources().getStringArray(R.array.createActivityListSpinnerValues);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, size_values);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityTypeSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public final void onResume() {

        Log.i(tag, "onResume");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GestorArbreActivitats.CREATE_ACTIVITY_DONE);
        receptor = new Receptor();
        registerReceiver(receptor, intentFilter);

        Intent receiverService = new Intent(this, GestorArbreActivitats.class);

        startService(receiverService);
        super.onResume();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int activitySpinnerIndex = activityTypeSpinner.getSelectedItemPosition();
        String[] size_values = getResources().getStringArray(R.array.createActivityListSpinnerValues);
        String actitivtyType = size_values[activitySpinnerIndex].toString();
        switch (actitivtyType) {
            case "Project":
            case "BasicTask":
                deadlineTime.setVisibility(View.GONE);
                deadlineDate.setVisibility(View.GONE);
                Log.d(tag, "case proj/basic correcto");
                break;
            case "DeadlineTask":
                deadlineDate.setVisibility(View.VISIBLE);
                deadlineTime.setVisibility(View.VISIBLE);
                Log.d(tag, "case deadline task correcto");
                break;

        }
    }

    private View.OnClickListener createActivityButtonListener = new View.OnClickListener() {

        public void onClick(View v) {
            //createActivity();
            Calendar calendar = new GregorianCalendar(deadlineDate.getYear(),
                    deadlineDate.getMonth(),
                    deadlineDate.getDayOfMonth(),
                    deadlineTime.getCurrentHour(),
                    deadlineTime.getCurrentMinute());
            long newActivityDeadline = calendar.getTimeInMillis();

            Log.d(tag, "Starting createnewactrivity() from createactivity");

            String newActivityName = newActivityNameField.getText().toString();
            String newActivityDescription = newActivityDescriptionField.getText().toString();
            int activitySpinnerIndex = activityTypeSpinner.getSelectedItemPosition();
            String[] size_values = getResources().getStringArray(R.array.createActivityListSpinnerValues);
            String newActivityType = size_values[activitySpinnerIndex];

            Intent createActivityIntent = new Intent(GestorArbreActivitats.CREATE_ACTIVITY);
            switch (newActivityType) {
                case "Project":
                    createActivityIntent.putExtra("newActivityName", newActivityName);
                    createActivityIntent.putExtra("newActivityDescription", newActivityDescription);
                    createActivityIntent.putExtra("newActivityType", newActivityType);
                    Log.d(tag, "new activity name: " + newActivityName);
                    Log.d(tag, "new activity type: " + newActivityType);
                    break;
                case "BasicTask":
                    createActivityIntent.putExtra("newActivityName", newActivityName);
                    createActivityIntent.putExtra("newActivityDescription", newActivityDescription);
                    createActivityIntent.putExtra("newActivityType", newActivityType);
                    Log.d(tag, "new activity name: " + newActivityName);
                    Log.d(tag, "new activity type: " + newActivityType);
                    break;
                case "DeadlineTask":

                    createActivityIntent.putExtra("newActivityName", newActivityName);
                    createActivityIntent.putExtra("newActivityDescription", newActivityDescription);
                    createActivityIntent.putExtra("newActivityType", newActivityType);
                    createActivityIntent.putExtra("newActivityDeadline", newActivityDeadline);
                    Log.d(tag, "new activity name: " + newActivityName);
                    Log.d(tag, "new activity type: " + newActivityType);
                    Date tmp = new Date();
                    tmp.setTime(newActivityDeadline);
                    Log.d(tag, "deadline task has a deadline in: " + tmp.toString());
                    break;
                default:
                    break;
            }
            sendBroadcast(createActivityIntent);
        }
    };

    private Receptor receptor;

    public class Receptor extends BroadcastReceiver {
        @Override
        public final void onReceive(final Context context, final Intent intent) {
            Log.d(tag, "onReceive From createactivity");
            if (intent.getAction().equals(GestorArbreActivitats.CREATE_ACTIVITY_DONE)) {
                Intent backToActivityList = new Intent(CreateActivity.this,
                        LlistaActivitatsActivity.class);
                startActivity(backToActivityList);
            }
            Log.i(tag, "onReceive From createactivity end");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onStop() {
        unregisterReceiver(receptor);
        super.onStop();
    }
}
