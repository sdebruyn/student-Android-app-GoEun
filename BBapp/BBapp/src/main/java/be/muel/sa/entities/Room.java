package be.muel.sa.entities;

import java.lang.reflect.Type;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Room {

    //variables
    private int id;
    private String name;
    private String description;
    private int type;
    private int price;

    //constructors
    public Room(int id, String name, String description, int type, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
