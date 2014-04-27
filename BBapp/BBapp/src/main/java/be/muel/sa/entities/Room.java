package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Room {

    private int id;
    private String name;
    private String description;
    private int type;
    private double price;

    public Room(int id, String name, String description, int type, double price) {
        setId(id);
        setName(name);
        setDescription(description);
        setType(type);
        setPrice(price);
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
