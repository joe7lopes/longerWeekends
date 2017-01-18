package com.longerweekends.longerweekends;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.roomorama.caldroid.CaldroidListener;

import java.util.Date;

import static com.longerweekends.longerweekends.SelectionMode.AL;
import static com.longerweekends.longerweekends.SelectionMode.C;
import static com.longerweekends.longerweekends.SelectionMode.PL;
import static com.longerweekends.longerweekends.SelectionMode.REMOVE;
import static com.longerweekends.longerweekends.SelectionMode.SELECT;

public class MainActivity extends AppCompatActivity implements MainView {

    private SelectionMode selectionMode = SELECT;
    private LongWeekendsCalendar longWeekendsCalendar;

    private EditText entitledLeavesTxt;
    private EditText balanceTxt;
    private EditText approvedLeavesTxt;
    private EditText plannedLeavesTxt;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImpl(this);
        initializeHeader();
        buildCalendar();
    }

    private void initializeHeader() {
        entitledLeavesTxt = (EditText) findViewById(R.id.entitleLeavesText);
        balanceTxt = (EditText) findViewById(R.id.balanceText);
        approvedLeavesTxt = (EditText) findViewById(R.id.approvedLeaveText);
        plannedLeavesTxt = (EditText) findViewById(R.id.plannedLeaveText);
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
                presenter.onSelectDate(selectionMode, date, view);
            }
        });
    }

    private void updateHeader() {
        entitledLeavesTxt.setText(String.valueOf(longWeekendsCalendar.getEntitledLeaves()));
        balanceTxt.setText(String.valueOf(longWeekendsCalendar.getBalance()));
        approvedLeavesTxt.setText(String.valueOf(longWeekendsCalendar.getApprovedLeaves()));
        plannedLeavesTxt.setText(String.valueOf(longWeekendsCalendar.getPlannedLeaves()));

    }

    //When user clicks on selection mode
    public void updateListener(View view) {
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

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
//save
    }

    @Override
    public void removeSelection(Date date) {
        longWeekendsCalendar.removeSelection(date);
    }

    @Override
    public void addApprovedLeave(Date date) {
        longWeekendsCalendar.setApprovedLeave(date);
    }

    @Override
    public void addPlannedLeave(Date date) {
        longWeekendsCalendar.setPlannedLeave(date);
    }

    @Override
    public void setBackgroundDrawableForDate(ColorDrawable color, Date date) {
        longWeekendsCalendar.setBackgroundDrawableForDate(color, date);
    }

    @Override
    public void refreshView() {
        longWeekendsCalendar.refreshView();
        updateHeader();
    }
}
