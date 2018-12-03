package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import models.Submission;
import models.UpdateUser;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class SubmissionsController extends Controller {
    private FormFactory formFactory;
    GlobalObjects globalObjects = new GlobalObjects();

    @Inject
    public SubmissionsController(FormFactory formFactory) {
        this.formFactory = formFactory;

    }

    public Result fetchSubmissions(String email) {
        User user = User.find.where().eq("email", email).findUnique();
        List<Submission> submissions = Submission.find.where()
                .eq("email", user.email)
                .findList();
        if (user.authority.equals("admin")) {
            submissions = Submission.find.all();
        }
        return ok(
                Json.toJson(submissions)
        );
    }

    public Result fetchSubmission(Long id, String email) {
        System.out.println("fetch submissions"+id);
        User user = User.find.where().eq("email", email).findUnique();
        Submission submission = Submission.find.where().eq("id", id).findUnique();
        if (!user.authority.equals("admin")) {
            submission = Submission.find.where().eq("email", user.email).eq("id", id).findUnique();
        }
        if(submission == null){
            return badRequest();
        }
        return ok(
                Json.toJson(submission)
        );
    }

    public Result save(String email) {
        User user = User.find.where().eq("email", email).findUnique();
        Submission submission = Json.fromJson(request().body().asJson(), Submission.class);
        try {
            submission.status = "0";
            submission.email = user.email;
            submission.save();
            return ok();
        } catch (PersistenceException e) {
            return badRequest();
        }
    }

    public Result update(Long submissionId, String email) throws PersistenceException {
        User user = User.find.where().eq("email", email).findUnique();
        Submission submissionForm = Json.fromJson(request().body().asJson(), Submission.class);
        Submission savedSubmission = Submission.find.where().eq("id", submissionId).findUnique();
        if(!user.authority.equals("admin")){
            savedSubmission = Submission.find.where().eq("id", submissionId).eq("email", user.email).findUnique();
        }
        if (savedSubmission != null) {
            Transaction txn = Ebean.beginTransaction();
            String ownerEmail = savedSubmission.email;
            savedSubmission = submissionForm;
            savedSubmission.email = ownerEmail;
            savedSubmission.id = submissionId;
            savedSubmission.status = "0";
            savedSubmission.update();
            txn.commit();
            txn.end();
            return ok();
        } else {
            return badRequest();
        }
    }

    public Result approve(Long submissionId,String email) throws PersistenceException {
        User user = User.find.where().eq("email", email).findUnique();
        if (user.authority.equals("admin")) {
            Transaction txn = Ebean.beginTransaction();
            Submission submission = Submission.find.byId(submissionId);
            submission.status = "1";
            submission.update();
            txn.commit();
            return ok("blah blah");
        }else{
            return badRequest("blah blah");
        }
    }
}
