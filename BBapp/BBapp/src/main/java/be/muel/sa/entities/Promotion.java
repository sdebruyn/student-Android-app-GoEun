package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Promotion {

    //variables
    private int id;
    private String description;

    private Room room;

    //constructors
    public Promotion(int id, String description, Room room) {
        this.id = id;
        this.description = description;
        this.room = room;
    }

    //methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
