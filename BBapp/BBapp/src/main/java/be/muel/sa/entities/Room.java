package be.muel.sa.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Room {

    private int id;
    private String name;
    private String description;
    private int type;
    private double price;
    private final List<Promotion> promotions;
    private final List<Photo> photos;

    public Room(int id, String name, String description, int type, double price) {
        setId(id);
        setName(name);
        setDescription(description);
        setType(type);
        setPrice(price);
        promotions = new ArrayList<Promotion>();
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

    public void addPromotion(Promotion promotion){
        promotions.add(promotion);
    }

    public void removePromotion(Promotion promotion){
        promotions.remove(promotion);
    }

    public List<Promotion> getPromotions(){
        return promotions;
    }

    public void addPhoto(Photo photo){
        photos.add(photo);
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void removePhoto(Photo photo){
        photos.remove(photo);
    }
}
