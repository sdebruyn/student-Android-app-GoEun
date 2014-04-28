package be.muel.sa.entities;

import java.util.Calendar;

/**
 * Created by Samuel on 25/03/2014.
 */
public class OpeningHour {

    private PlaceOfInterest placeOfInterest;
    private int id;
    private Calendar start;
    private Calendar end;
    private WeekDay day;

    public OpeningHour(int id, Calendar start, Calendar end, WeekDay day) {
        setId(id);
        setStart(start);
        setEnd(end);
        setDay(day);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
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

    public void setPlaceOfInterest(PlaceOfInterest placeOfInterest) {
        this.placeOfInterest = placeOfInterest;
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
}
