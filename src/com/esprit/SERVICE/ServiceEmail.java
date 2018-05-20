/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.SERVICE;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author houssem
 */
public class ServiceEmail {

    public void sendEmail(String email, String text) {
        String from = "mourynesse@gmail.com";
        String password = "09151213";

        Properties props = System.getProperties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication gePasswordAuthenticationt() {
                return new PasswordAuthentication(from, password);
            }
        });
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("Zanimo");
            message.setText(text);
            System.out.println("1");
            Transport transport = session.getTransport("smtp");
            System.out.println("2");
            transport.connect("smtp.gmail.com", from, password);
            System.out.println("3");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("email envoyer avec succes");

        } catch (MessagingException me) {
            System.out.println("error l'hors de l'envoi ");
        }
    }
}
