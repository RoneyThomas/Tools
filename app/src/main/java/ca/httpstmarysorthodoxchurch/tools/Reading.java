package ca.httpstmarysorthodoxchurch.tools;

import java.util.ArrayList;

/**
 * Created by roneythomas on 2016-08-12.
 */

public class Reading {
    String title;
    ArrayList<String> verse = new ArrayList<>();

    Reading() {

    }

    Reading(String title, ArrayList<String> verse) {
        this.title = title;
        this.verse = verse;
    }

    public ArrayList<String> getVerse() {
        return verse;
    }

    public void setVerse(ArrayList<String> verse) {
        this.verse = verse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
