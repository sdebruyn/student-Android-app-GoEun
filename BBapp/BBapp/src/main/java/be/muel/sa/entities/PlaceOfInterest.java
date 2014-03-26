package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class PlaceOfInterest {

    //variables
    private int id;
    private String name;
    private String telephone;
    private int type;

    private Address address;

    //constructors
    public PlaceOfInterest(int id, String name, String telephone, int type, Address address) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.type = type;
        this.address = address;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
