package be.muel.sa.entities;

/**
 * Created by Samuel on 28/04/2014.
 */
public enum POIType {

    RESTAURANT,
    MUSEUM,
    BAR,
    PARK;

    public static POIType fromInt(int input) {
        switch (input) {
            case 0:
            default:
                return RESTAURANT;
            case 1:
                return MUSEUM;
            case 2:
                return BAR;
            case 3:
                return PARK;
        }
    }

}
