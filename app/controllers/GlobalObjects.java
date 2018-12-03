package controllers;

import models.User;

import java.util.HashMap;

public class GlobalObjects {
    static HashMap<String,User> forgotPasscodes;
    public GlobalObjects(){
        if(forgotPasscodes == null){
            forgotPasscodes= new HashMap<>();
        }
    }
    public HashMap<String,User> getForgotPasscodes(){
        return forgotPasscodes;
    }

}
