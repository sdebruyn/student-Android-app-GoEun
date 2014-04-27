package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class PlaceOfInterest {

    public enum POIType{
        RESTAURANT, MUSEUM
    }

    private int id;
    private String name;
    private String telephone;
    private Address address;
    private POIType type;

    public PlaceOfInterest(int id, String name, String telephone, POIType type, Address address) {
        setId(id);
        setName(name);
        setTelephone(telephone);
        setType(type);
        setAddress(address);
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
}
