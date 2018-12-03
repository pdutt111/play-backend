package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Submission extends Model{
    @Id
    public Long id;
    public String title;
    public String categories;
    public String email;
    public String author1;
    public String author2;
    public String author3;
    public String author4;
    public String author5;
    public String author6;
    public String author7;
    public String projectAbstract;
    public String details;
    public String status;
    public static Model.Find<Long,Submission> find = new Model.Find<Long,Submission>(){};
}
