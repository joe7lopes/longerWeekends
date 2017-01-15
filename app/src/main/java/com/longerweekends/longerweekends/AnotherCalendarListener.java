package com.longerweekends.longerweekends;

import android.graphics.Color;
import android.view.View;

import com.roomorama.caldroid.CaldroidListener;

import java.util.Date;

/**
 * Created by joe on 14/01/2017.
 */

public class AnotherCalendarListener extends CaldroidListener {
    @Override
    public void onSelectDate(Date date, View view) {
        view.setBackgroundColor(Color.YELLOW);
    }
}
