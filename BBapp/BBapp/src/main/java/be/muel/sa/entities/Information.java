package be.muel.sa.entities;

/**
 * Created by Samuel on 25/03/2014.
 */
public class Information {

    private String telephone;
    private String cellPhone;
    private String email;
    private String description;
    private String breakfast;
    public final static int ID = 1;

    //constructors
    public Information(String telephone, String cellPhone, String email, String description, String breakfast) {
        setTelephone(telephone);
        setCellPhone(cellPhone);
        setEmail(email);
        setDescription(description);
        setBreakfast(breakfast);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }
}
