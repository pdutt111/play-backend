package models;

public class UpdateUser {
    public String researchAreas;
    public String firstName;
    public String lastName;
    public String position;
    public String affiliation;
    public String phone;
    public String fax;
    public String address;
    public String city;
    public String country;
    public int zip;
    public String email;
    public String password;
    public String authority;

    public UpdateUser(){}
    public UpdateUser(User user){
        this.researchAreas = user.researchAreas;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.position = user.position;
        this.affiliation = user.affiliation;
        this.phone = user.phone;
        this.fax = user.fax;
        this.city = user.city;
        this.country = user.country;
        this.zip = user.zip;
        this.authority = user.authority;
    }
}
