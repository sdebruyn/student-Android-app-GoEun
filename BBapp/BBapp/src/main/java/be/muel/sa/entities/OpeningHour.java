package be.muel.sa.entities;

import java.sql.Time;

/**
 * Created by Samuel on 25/03/2014.
 */
public class OpeningHour {

    //variables
    private int id;
    private Time start;
    private Time end;
    private int weekday;

    private PlaceOfInterest placeOfInterest;

    //constructors
    public OpeningHour(int id, Time start, Time end, int weekday, PlaceOfInterest placeOfInterest) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.weekday = weekday;
        this.placeOfInterest = placeOfInterest;
    }

    //methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public PlaceOfInterest getPlaceOfInterest() {
        return placeOfInterest;
    }

    public void setPlaceOfInterest(PlaceOfInterest placeOfInterest) {
        this.placeOfInterest = placeOfInterest;
    }
}
