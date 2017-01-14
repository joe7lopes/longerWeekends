package com.longerweekends.longerweekends;


import android.os.Bundle;

import com.roomorama.caldroid.CaldroidFragment;

public class LongWeekendsCalendar extends CaldroidFragment {

    public LongWeekendsCalendar() {
        Bundle args = new Bundle();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(java.util.Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(java.util.Calendar.YEAR));
        this.setArguments(args);
    }
}
