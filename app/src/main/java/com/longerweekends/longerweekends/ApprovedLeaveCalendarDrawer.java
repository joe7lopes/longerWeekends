package com.longerweekends.longerweekends;


import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Date;

import static android.graphics.Color.GREEN;

public class ApprovedLeaveCalendarDrawer implements CalendarDrawer{

    @Override
    public void draw(CaldroidFragment caldroidFragment, Date date) {
        ColorDrawable green = new ColorDrawable(GREEN);
        caldroidFragment.setBackgroundDrawableForDate(green, date);
        System.out.printf("listener listening");
        caldroidFragment.refreshView();
    }
}
