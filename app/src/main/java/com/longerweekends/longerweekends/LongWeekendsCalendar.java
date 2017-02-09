package com.longerweekends.longerweekends;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.MAGENTA;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

public class LongWeekendsCalendar extends CaldroidFragment {

    private static final ColorDrawable AL = new ColorDrawable(GREEN);
    private static final ColorDrawable PL = new ColorDrawable(YELLOW);
    private static final ColorDrawable C = new ColorDrawable(MAGENTA);
    private static final ColorDrawable DEFAULT = new ColorDrawable(WHITE);

    private Collection<Date> approvedLeaves = new HashSet<>();
    private Collection<Date> plannedLeaves = new HashSet<>();
    private Collection<Date> companyLeaves = new HashSet<>();
    private int entitledLeaves = 0;

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
        companyLeaves.remove(date);
        setBackgroundDrawableForDate(DEFAULT, date);
        refreshView();
    }

    public int getBalance() {
        int _approvedLeaves = approvedLeaves.size();
        int _plannedLeaves = plannedLeaves.size();
        return entitledLeaves - (_approvedLeaves + _plannedLeaves);
    }

    public int getApprovedLeavesCount() {
        return approvedLeaves.size();
    }

    public int getPlannedLeavesCount() {
        return plannedLeaves.size();
    }

    public int getEntitledLeaves() {
        return entitledLeaves;
    }

    public void setApprovedLeave(Date date) {
        this.approvedLeaves.add(date);
        setBackgroundDrawableForDate(AL, date);
    }

    public void setPlannedLeave(Date date) {
        this.plannedLeaves.add(date);
        setBackgroundDrawableForDate(PL, date);
    }

    public void setCompanyLeave(Date date) {
        this.companyLeaves.add(date);
        setBackgroundDrawableForDate(C, date);
    }

    public void setApprovedLeaves(Collection<Date> approvedLeaves) {
        for (Date date : approvedLeaves) {
            setApprovedLeave(date);
        }
        refreshView();
    }

    public void setPlannedLeaves(Collection<Date> plannedLeaves) {
        for (Date date : plannedLeaves) {
            setPlannedLeave(date);
        }
        refreshView();
    }

    public void setCompanyLeaves(Collection<Date> companyLeaves) {
        for (Date date : companyLeaves) {
            setCompanyLeave(date);
        }
        refreshView();
    }

    public void setEntitledLeaves(int entitledLeaves) {
        this.entitledLeaves = entitledLeaves;
    }

    public Collection<Date> getApprovedLeaves() {
        return approvedLeaves;
    }

    public Collection<Date> getPlannedLeaves() {
        return plannedLeaves;
    }

    public Collection<Date> getCompanyLeaves() {
        return companyLeaves;
    }
}