package com.longerweekends.longerweekends;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.roomorama.caldroid.CaldroidListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private CalendarDrawer calendarDrawer = new NullCalendarDrawer();

    private LongWeekendsCalendar longWeekendsCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildCalendar();

    }

    private void buildCalendar() {
        longWeekendsCalendar = new LongWeekendsCalendar();
        addListener();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.calendar, longWeekendsCalendar);
        fragmentTransaction.commit();
    }

    private void addListener() {
        longWeekendsCalendar.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

                //TODO, create a leave processor which will draw, register the date and check for repeated date.

/*
                leaveProcessor.process(longWeekendsCalendar, date);
                calendarDrawer.draw(longWeekendsCalendar, date);
                */
            }
        });
    }


    public void updateListener(View view) {
        //TODO Move to factory, avoid creating new instances
        if (R.id.approvedLeave == view.getId()) {
            calendarDrawer = new ApprovedLeaveCalendarDrawer();
        } else if (R.id.plannedLeave == view.getId()) {
            calendarDrawer = new PlannedLeaveCalendarDrawer();
        } else if (R.id.companyDayOff == view.getId()) {
            calendarDrawer = new CompanyDayOffLeaveCalendarDrawer();
        } else {
            calendarDrawer = new NullCalendarDrawer();
        }

    }


}
