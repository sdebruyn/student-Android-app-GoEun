package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Promotion {

    private int id;
    private String description;
    private final Room room;

    public Promotion(int id, String description, Room room) {
        setId(id);
        setDescription(description);
        this.room = room;
    }

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
}
