package ca.httpstmarysorthodoxchurch.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by roneythomas on 2016-08-09.
 */

public class Lectionary {

    String title;
    long date;
    String timeZone;
    Map<String, ArrayList<String>> reading;

    Lectionary() {

    }

    Lectionary(String title, long date, String timeZone, Map<String, ArrayList<String>> reading) {
        this.title = title;
        this.date = date;
        this.timeZone = timeZone;
        this.reading = reading;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Map<String, ArrayList<String>> getReading() {
        return reading;
    }

    public void setReading(Map<String, ArrayList<String>> reading) {
        this.reading = reading;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        return dateFormat.format(new Date(date));
    }
}