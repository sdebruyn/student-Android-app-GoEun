package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Photo {

    private int id;
    private String link;
    private Room room;
    private PlaceOfInterest placeOfInterest;
    private Promotion promotion;

    //constructors
    public Photo(int id, String link) {
        setId(id);
        setLink(link);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
}
