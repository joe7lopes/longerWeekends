package com.longerweekends.longerweekends;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Storage {

    private static final String ENTITLED_LEAVES_KEY = "ent_leaves";
    private static final String APPROVED_LEAVES_KEY = "app_leaves";
    private static final String PLANNED_LEAVES_KEY = "plan_leaves";
    private static final String COMPANY_LEAVES_KEY = "comp_leaves";
    private static final String EMPTY = "empty";

    private SharedPreferences sharedPreferences;

    public Storage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Collection<Date> getApprovedLeaves() {
        return getDates(APPROVED_LEAVES_KEY);
    }

    public Collection<Date> getPlannedLeaves() {
        return getDates(PLANNED_LEAVES_KEY);
    }

    public Collection<Date> getCompanyLeaves() {
        return getDates(COMPANY_LEAVES_KEY);
    }

    public int getEntitledLeaves() {
        String resultJson = getJsonResult(ENTITLED_LEAVES_KEY);
        return Integer.valueOf(resultJson);
    }

    public void save(LongWeekendsCalendar calendar) {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String entitledLeaves = gson.toJson(calendar.getEntitledLeaves());
        String approvedLeaves = gson.toJson(calendar.getApprovedLeaves());
        String plannedLeaves = gson.toJson(calendar.getPlannedLeaves());
        String companyLeaves= gson.toJson(calendar.getCompanyLeaves());
        prefEditor.putString(ENTITLED_LEAVES_KEY, entitledLeaves);
        prefEditor.putString(APPROVED_LEAVES_KEY, approvedLeaves);
        prefEditor.putString(PLANNED_LEAVES_KEY, plannedLeaves);
        prefEditor.putString(COMPANY_LEAVES_KEY, companyLeaves);
        prefEditor.apply();
    }

    private Collection getDates(String key) {
        Gson gson = new Gson();
        String resultJson = getJsonResult(key);
        return resultJson.equals(EMPTY) ? new HashSet<Date>() : (Collection) gson.fromJson(resultJson, new TypeToken<Collection<Date>>() {
        }.getType());
    }

    private String getJsonResult(String key) {
        return sharedPreferences.getString(key, EMPTY);
    }
}
