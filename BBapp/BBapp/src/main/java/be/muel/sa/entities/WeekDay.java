package be.muel.sa.entities;

/**
 * Created by Samuel on 28/04/2014.
 */
public enum WeekDay {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    public static WeekDay fromInt(int input) {
        switch (input) {
            case 0:
            case 7:
            default:
                return SUNDAY;
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
        }
    }
}
