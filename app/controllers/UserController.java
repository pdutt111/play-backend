package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.Update;
import models.Forgot;
import models.UpdateUser;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.Arrays;

public class UserController extends Controller {
    private FormFactory formFactory;
    GlobalObjects globalObjects = new GlobalObjects();

    @Inject
    public UserController(FormFactory formFactory) {
        this.formFactory = formFactory;

    }

    public Result getUser(String email) {
        User usr = User.find.where().eq("email", email).findUnique();
        if (usr != null) {
            String json = Json.toJson(usr).toString();
            return ok(json);
        }
        return badRequest();
    }

    public Result save() {
        User user = Json.fromJson(request().body().asJson(), User.class);
        try {
            user.authority = "user";
            user.save();
        } catch (PersistenceException e) {
            return badRequest();
        }
        return ok(Json.toJson(user));
    }

    public Result resetPassword(String email) {
        User setUser = Json.fromJson(request().body().asJson(), User.class);
        User user = User.find.where()
                .eq("email", email)
                .findUnique();
        if (user == null) {
            return badRequest();
        }
        user.password = setUser.password;
        user.update();
        return ok();
    }

    public Result update(String email) throws PersistenceException {
        UpdateUser newUserData = Json.fromJson(request().body().asJson(), UpdateUser.class);
        User savedUser = User.find.where().eq("email", email).findUnique();
        if (savedUser != null) {
            Transaction txn = Ebean.beginTransaction();
            savedUser.address = newUserData.address;
            savedUser.affiliation = newUserData.affiliation;
            savedUser.firstName = newUserData.firstName;
            savedUser.lastName = newUserData.lastName;
            savedUser.city = newUserData.city;
            savedUser.country = newUserData.country;
            savedUser.fax = newUserData.fax;
            savedUser.phone = newUserData.phone;
            savedUser.position = newUserData.position;
            savedUser.researchAreas = newUserData.researchAreas;
            savedUser.zip = newUserData.zip;
            savedUser.update();
            txn.commit();
            txn.end();
            System.out.println(savedUser.firstName);
            return ok();
        }
        return badRequest();
    }
}
