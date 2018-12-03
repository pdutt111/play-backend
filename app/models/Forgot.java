package models;

import com.avaje.ebean.PagedList;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class Forgot{
    public String password;
    public String passcode;
}
