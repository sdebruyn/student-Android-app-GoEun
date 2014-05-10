package be.muel.sa.entities;

import android.graphics.Bitmap;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Photo {

    private Bitmap bitmap;
    private int id;
    private Room room;
    private PlaceOfInterest placeOfInterest;
    private Promotion promotion;

    public Photo(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public PlaceOfInterest getPlaceOfInterest() {
        return placeOfInterest;
    }

    public void setPlaceOfInterest(PlaceOfInterest placeOfInterest) {
        this.placeOfInterest = placeOfInterest;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                '}';
    }
}
