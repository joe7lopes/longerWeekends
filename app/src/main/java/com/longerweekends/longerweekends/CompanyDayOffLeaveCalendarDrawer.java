package com.longerweekends.longerweekends;

import android.graphics.drawable.ColorDrawable;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Date;

import static android.graphics.Color.MAGENTA;


public class CompanyDayOffLeaveCalendarDrawer implements CalendarDrawer {

    @Override
    public void draw(CaldroidFragment caldroidFragment, Date date) {
        ColorDrawable magenta = new ColorDrawable(MAGENTA);
        caldroidFragment.setBackgroundDrawableForDate(magenta, date);
        caldroidFragment.refreshView();
    }
}
