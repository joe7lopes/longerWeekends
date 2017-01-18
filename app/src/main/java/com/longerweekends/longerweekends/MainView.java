package com.longerweekends.longerweekends;

import android.graphics.drawable.ColorDrawable;

import java.util.Date;

public interface MainView {

    void removeSelection(Date date);

    void addApprovedLeave(Date date);

    void addPlannedLeave(Date date);

    void setBackgroundDrawableForDate(ColorDrawable color, Date date);

    void refreshView();
}
