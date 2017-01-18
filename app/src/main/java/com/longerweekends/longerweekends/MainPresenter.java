package com.longerweekends.longerweekends;

import android.view.View;

import java.util.Date;

public interface MainPresenter {

    void onSelectDate(SelectionMode selectionMode, Date date, View view);
}
