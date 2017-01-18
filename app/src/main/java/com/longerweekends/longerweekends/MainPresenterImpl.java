package com.longerweekends.longerweekends;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.longerweekends.longerweekends.SelectionMode.AL;
import static com.longerweekends.longerweekends.SelectionMode.C;
import static com.longerweekends.longerweekends.SelectionMode.PL;
import static com.longerweekends.longerweekends.SelectionMode.REMOVE;
import static com.longerweekends.longerweekends.SelectionMode.SELECT;

public class MainPresenterImpl implements MainPresenter {

    private static final Map<SelectionMode, ColorDrawable> COLORS = new HashMap<>(4);

    static {
        COLORS.put(AL, new ColorDrawable(Color.GREEN));
        COLORS.put(PL, new ColorDrawable(Color.YELLOW));
        COLORS.put(C, new ColorDrawable(Color.MAGENTA));
        COLORS.put(SELECT, new ColorDrawable(Color.WHITE));
    }

    MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onSelectDate(SelectionMode selectionMode, Date date, View view) {
        if (selectionMode != SELECT) {
            mainView.removeSelection(date);
            ColorDrawable color = COLORS.get(selectionMode);
            if (selectionMode == AL) {
                mainView.addApprovedLeave(date);
            } else if (selectionMode == PL) {
                mainView.addPlannedLeave(date);
            } else if (selectionMode == REMOVE) {
                mainView.removeSelection(date);
            }
            mainView.setBackgroundDrawableForDate(color, date);
            mainView.refreshView();
        }
    }
}
