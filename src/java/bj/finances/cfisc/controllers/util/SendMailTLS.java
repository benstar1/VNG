/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author JOHNSON
 */
public class SendMailTLS implements Runnable {

    private final String host = "smtp.gmail.com";
    private final String user = "cfiscbj@gmail.com";//change accordingly  
    private final String password = "cfisc123";//change accordingly  
    private final String to = "bentsijohnson@gmail.com";//change accordingly  
    
    private String subject;
    private String content;
    
    public void sendMail(String subject, String content) {

        Properties props = new Properties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));            
            message.setSubject(subject);
            Date maintenant = new Date();
            SimpleDateFormat format_heure = new SimpleDateFormat("hh:mm:ss");
            SimpleDateFormat format_jour = new SimpleDateFormat("dd/MM/yyyy");
            message.setText("Echec intégration du fichier " + content + " ce " + format_jour.format(maintenant) + " à " + format_heure.format(maintenant) );

            //send the message  
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Echec envoi mail...");
        }
    }

    @Override
    public void run() {
        sendMail(subject, content);
    }

    public SendMailTLS(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
    
    
}
