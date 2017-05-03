package com.wanwei.szjz.reddatetime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), isVibrate());
        final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY) ,calendar.get(Calendar.MINUTE), false, false);
//        datePickerDialog.



        findViewById(R.id.datebutton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(isVibrate());
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });
        findViewById(R.id.timebutton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                timePickerDialog.setVibrate(isVibrate());
                timePickerDialog.setCloseOnSingleTapMinute(isCloseOnSingleTapMinute());
                timePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_TAG);
            }
        });



    }


    private boolean isVibrate() {
        return ((CheckBox) findViewById(R.id.checkBoxVibrate)).isChecked();
    }

    private boolean isCloseOnSingleTapDay() {
        return ((CheckBox) findViewById(R.id.checkBoxCloseOnSingleTapDay)).isChecked();
    }

    private boolean isCloseOnSingleTapMinute() {
        return ((CheckBox) findViewById(R.id.checkBoxCloseOnSingleTapMinute)).isChecked();
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }
}
