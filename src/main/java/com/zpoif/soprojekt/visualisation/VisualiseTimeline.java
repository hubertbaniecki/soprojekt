package com.zpoif.soprojekt.visualisation;

import com.google.code.stackexchange.schema.TimePeriod;
import com.zpoif.soprojekt.api.QuestionCounter;
import org.springframework.util.StopWatch;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.TimeSeriesPlot;
import tech.tablesaw.plotly.components.Figure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisualiseTimeline {

    public static String visualise(String fromDate, String toDate, String nazwa){
        String message;
        Date date1 = new Date(); //1543363200000L
        Date date2 = new Date(); //1543622400000L

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date1 = sdf.parse(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date2 = sdf.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TimePeriod timePeriod = new TimePeriod(date1, date2);
        StopWatch sw = new org.springframework.util.StopWatch();
        sw.start("test");
        Table testData = new QuestionCounter(timePeriod).receiveData();
        sw.stop();
        System.out.println("Twój czas działania QuestionCounter wyniósł: " + sw.getLastTaskTimeMillis()/1000 + "sekund");

        Figure fig = TimeSeriesPlot.create("Liczba nowych pytan na stronie w poszczegolnych dniach", testData,"data", "liczba");
        message = fig.asJavascript(nazwa);

        return message;
    }
}
