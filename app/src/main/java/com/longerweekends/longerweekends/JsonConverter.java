package com.longerweekends.longerweekends;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JsonConverter {

    public static Collection<Date> convert(String stringArray) throws Exception {
        List<Date> dates = new ArrayList<>();

        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(stringArray);
        } catch (JSONException e) {
            throw new Exception("could not deserialize");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        for (int i = 0; i < jsonArray.length(); i++) {
            date = formatter.parse(jsonArray.getString(i));
            dates.add(date);
        }

        return dates;
    }
}
