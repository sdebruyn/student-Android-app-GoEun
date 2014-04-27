package be.muel.sa.entities;

import java.sql.Time;

/**
 * Created by Samuel on 25/03/2014.
 */
public class OpeningHour {

    public enum WeekDay{
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    private int id;
    private Time start;
    private Time end;
    private WeekDay day;
    private final PlaceOfInterest placeOfInterest;

    public OpeningHour(int id, Time start, Time end, WeekDay day, PlaceOfInterest placeOfInterest) {
        setId(id);
        setStart(start);
        setEnd(end);
        setDay(day);
        this.placeOfInterest = placeOfInterest;
    }

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

    public WeekDay getDay() {
        return day;
    }

    public void setDay(WeekDay day) {
        this.day = day;
    }

    public PlaceOfInterest getPlaceOfInterest() {
        return placeOfInterest;
    }

}
