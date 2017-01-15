package com.longerweekends.longerweekends;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.longerweekends.longerweekends.SelectionMode.AL;
import static com.longerweekends.longerweekends.SelectionMode.C;
import static com.longerweekends.longerweekends.SelectionMode.PL;
import static com.longerweekends.longerweekends.SelectionMode.REMOVE;
import static com.longerweekends.longerweekends.SelectionMode.SELECT;

public class MainActivity extends AppCompatActivity {

    private int entitledLeaves;
    private List<Date> approvedLeaves = new ArrayList<>();
    private List<Date> plannedLeaves = new ArrayList<>();
    private SelectionMode selectionMode = SELECT;
    private LongWeekendsCalendar longWeekendsCalendar;

    private static final ColorDrawable GREEN = new ColorDrawable(Color.GREEN);
    private static final ColorDrawable YELLOW = new ColorDrawable(Color.YELLOW);
    private static final ColorDrawable MAGENTA = new ColorDrawable(Color.MAGENTA);
    private static final ColorDrawable DEFAULT_COLOR = new ColorDrawable(Color.WHITE);

    private EditText entitledLeavesTxt;
    private EditText balanceTxt;
    private EditText approvedLeavesTxt;
    private EditText plannedLeavesTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeHeader();
        buildCalendar();

    }

    private void initializeHeader() {
        entitledLeavesTxt = (EditText) findViewById(R.id.entitleLeavesText);
        balanceTxt = (EditText) findViewById(R.id.balanceText);
        approvedLeavesTxt = (EditText) findViewById(R.id.approvedLeaveText);
        plannedLeavesTxt = (EditText) findViewById(R.id.plannedLeaveText);
        entitledLeaves = 10;
        updateHeader();
    }

    private void buildCalendar() {
        longWeekendsCalendar = new LongWeekendsCalendar();
        addListener();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.calendar, longWeekendsCalendar);
        fragmentTransaction.commit();
    }

    private void addListener() {
        //TODO refactor to a custom listener that accepts a drawer.
        //create different drawers for different colors and substitute the ifs.
        longWeekendsCalendar.setCaldroidListener(new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                if (selectionMode != SELECT) {
                    removeSelection(date);
                    ColorDrawable color = DEFAULT_COLOR;
                    if (selectionMode == AL) {
                        color = GREEN;
                        approvedLeaves.add(date);
                    } else if (selectionMode == PL) {
                        color = YELLOW;
                        plannedLeaves.add(date);
                    } else if (selectionMode == C) {
                        color = MAGENTA;
                    } else if (selectionMode == REMOVE) {
                        color = DEFAULT_COLOR;
                        removeSelection(date);
                    }
                    longWeekendsCalendar.setBackgroundDrawableForDate(color, date);
                    longWeekendsCalendar.refreshView();
                    updateHeader();
                }
            }
        });
    }

    private void updateHeader() {

        int _approvedLeaves = approvedLeaves.size();
        int _plannedLeaves = plannedLeaves.size();
        int balance = entitledLeaves - (_approvedLeaves + _plannedLeaves);

        entitledLeavesTxt.setText(String.valueOf(entitledLeaves));
        balanceTxt.setText(String.valueOf(balance));
        approvedLeavesTxt.setText(String.valueOf(_approvedLeaves));
        plannedLeavesTxt.setText(String.valueOf(_plannedLeaves));

    }

    private void removeSelection(Date date) {
        approvedLeaves.remove(date);
        plannedLeaves.remove(date);
    }

    //When user clicks on selection mode
    public void updateListener(View view) {
        //TODO Move to factory, avoid creating new instances
        if (R.id.approvedLeave == view.getId()) {
            selectionMode = AL;
        } else if (R.id.plannedLeave == view.getId()) {
            selectionMode = PL;
        } else if (R.id.companyDayOff == view.getId()) {
            selectionMode = C;
        } else if (R.id.remove == view.getId()) {
            selectionMode = REMOVE;
        } else if (R.id.select == view.getId()) {
            selectionMode = SELECT;
        }

    }


}
