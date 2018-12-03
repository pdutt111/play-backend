package controllers;


import org.apache.commons.mail.*;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class Mail {
    public String send(String mailAddress) {
        String devStr = (int)(Math.random()*100000000)+"";
        System.out.println(devStr);
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthenticator(new DefaultAuthenticator("pdutt111@gmail.com", "hamptapass@123"));
        email.setSSLOnConnect(true);
        try{
            email.setFrom("pdutt111@gmail.com");
            email.setSubject("Password reset link");
            email.setHtmlMsg("Click <a href=\"http://localhost:9000/users/reset/"+devStr+"\">here</a> to reset your link ");
            email.addTo(mailAddress);
            email.send();
            System.out.println("message sent");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("error!!!!");
        }
        return devStr;
    }

}
