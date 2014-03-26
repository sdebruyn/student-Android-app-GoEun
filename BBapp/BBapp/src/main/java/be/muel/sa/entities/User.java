package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class User {

    //variables
    private int id;
    private String email;
    private String password;
    private String reset;

    private Address address;

    //constructors
    public User(int id, String email, String password, String reset, Address address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.reset = reset;
        this.address = address;
    }

    //methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
