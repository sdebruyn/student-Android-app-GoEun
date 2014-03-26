package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Country {

    //variables
    private int id;
    private String name;

    //constructors
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
