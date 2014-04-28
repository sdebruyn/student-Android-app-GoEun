package be.muel.sa.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuel on 25/03/2014.
 */
public class PlaceOfInterest {

    private final List<OpeningHour> openingHours;
    private final List<Photo> photos;
    private int id;
    private String name;
    private String telephone;
    private Address address;
    private POIType type;
    public PlaceOfInterest(int id, String name, String telephone, POIType type) {
        setId(id);
        setName(name);
        setTelephone(telephone);
        setType(type);
        openingHours = new ArrayList<OpeningHour>();
        photos = new ArrayList<Photo>();
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public POIType getType() {
        return type;
    }

    public void setType(POIType type) {
        this.type = type;
    }

    public void addOpeningHour(OpeningHour openingHour) {
        openingHours.add(openingHour);
    }

    public List<OpeningHour> getOpeningHours() {
        return openingHours;
    }

    public void removeOpeningHour(OpeningHour openingHour) {
        openingHours.remove(openingHour);
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
        return "PlaceOfInterest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address=" + address +
                ", type=" + type +
                '}';
    }

}
