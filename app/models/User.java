package models;

import com.avaje.ebean.PagedList;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends com.avaje.ebean.Model {
    @Id
    public Long id;
    @Constraints.Required
    public String email;
    @Constraints.Required
    public String password;
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
    @Column(columnDefinition = "varchar(45) default user")
    public String authority;
    public transient static Find<Long,User> find = new Find<Long,User>(){};
    public static User getUser(String email,String password) {
        return find
                .where()
                .eq("email",email)
                .eq("password",password)
                .findPagedList()
                .getList().get(0);
    }
}
