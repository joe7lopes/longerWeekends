package com.longerweekends.longerweekends;

import android.view.View;

import java.util.Date;

import static com.longerweekends.longerweekends.SelectionMode.AL;
import static com.longerweekends.longerweekends.SelectionMode.PL;
import static com.longerweekends.longerweekends.SelectionMode.REMOVE;
import static com.longerweekends.longerweekends.SelectionMode.SELECT;

public class MainPresenterImpl implements MainPresenter {

    MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onSelectDate(SelectionMode selectionMode, Date date, View view) {
        if (selectionMode != SELECT) {
            mainView.removeSelection(date);
            if (selectionMode == AL) {
                mainView.addApprovedLeave(date);
            } else if (selectionMode == PL) {
                mainView.addPlannedLeave(date);
            } else if (selectionMode == REMOVE) {
                mainView.removeSelection(date);
            }
            mainView.refreshView();
        }
    }
}
