package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Country {

    private final int id;
    private final String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
