package com.longerweekends.longerweekends;


import android.os.Bundle;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class LongWeekendsCalendar extends CaldroidFragment {

    private Collection<Date> approvedLeaves = new HashSet<>();
    private Collection<Date> plannedLeaves = new HashSet<>();
    private int entitledLeaves = 10;

    public LongWeekendsCalendar() {
        Bundle args = new Bundle();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(java.util.Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(java.util.Calendar.YEAR));
        this.setArguments(args);
    }

    public void removeSelection(Date date) {
        approvedLeaves.remove(date);
        plannedLeaves.remove(date);
    }

    public int getBalance() {
        int _approvedLeaves = approvedLeaves.size();
        int _plannedLeaves = plannedLeaves.size();
        return entitledLeaves - (_approvedLeaves + _plannedLeaves);
    }

    public int getApprovedLeaves() {
        return approvedLeaves.size();
    }

    public int getPlannedLeaves() {
        return plannedLeaves.size();
    }

    public int getEntitledLeaves() {
        return entitledLeaves;
    }

    public void setApprovedLeave(Date date) {
        this.approvedLeaves.add(date);
    }

    public void setPlannedLeave(Date date) {
        this.plannedLeaves.add(date);
    }
}