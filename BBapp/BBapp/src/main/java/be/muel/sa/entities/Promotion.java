package be.muel.sa.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Promotion {

    private final List<Photo> photos;
    private Room room;
    private int id;
    private String description;

    public Promotion(int id, String description) {
        setId(id);
        setDescription(description);
        photos = new ArrayList<Photo>();
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

    public void setRoom(Room room) {
        this.room = room;
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
