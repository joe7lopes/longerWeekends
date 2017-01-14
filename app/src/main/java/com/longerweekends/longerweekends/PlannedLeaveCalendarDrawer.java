package com.longerweekends.longerweekends;


import android.graphics.drawable.ColorDrawable;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Date;

import static android.graphics.Color.YELLOW;

public class PlannedLeaveCalendarDrawer implements CalendarDrawer {

    @Override
    public void draw(CaldroidFragment caldroidFragment, Date date) {
        ColorDrawable yellow = new ColorDrawable(YELLOW);
        caldroidFragment.setBackgroundDrawableForDate(yellow, date);
        caldroidFragment.refreshView();
    }
}
