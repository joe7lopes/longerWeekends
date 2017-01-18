package com.longerweekends.longerweekends;

import org.hamcrest.CoreMatchers;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class JsonConverterTest {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void given() throws Exception {
      //  String dates="Mon Jan 02 00:00:00 GMT+01:00 2017","Thu Jan 05 00:00:00 GMT+01:00 2017";
       // Date date=formatter.parse(dates[0]);
       //Assert.assertThat(JsonConverter.convert(dates).get(0), CoreMatchers.is(date))
    }

}