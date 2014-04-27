package be.muel.sa.entities;

import java.sql.Time;

/**
 * Created by Samuel on 25/03/2014.
 */
public class OpeningHour {

    private final PlaceOfInterest placeOfInterest;
    private int id;
    private Time start;
    private Time end;
    private WeekDay day;
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

    @Override
    public String toString() {
        return "OpeningHour{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", day=" + day +
                '}';
    }

    public enum WeekDay {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

}
