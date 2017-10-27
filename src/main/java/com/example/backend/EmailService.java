package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by kiranreddy on 22/04/17.
 */
@Service
public class EmailService {

  private JavaMailSender javaMailSender;

  @Autowired
  public EmailService(JavaMailSender sender) {
    this.javaMailSender = sender;
  }

  public void sendMail(String email, String id) {
    System.out.println("Building email for "+ email + "with id: "+id);
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(email);
    mailMessage.setSubject("Confirm registration");
    // add link
    mailMessage.setText("Follow the link to finish your registraton:\n"
            +"http://localhost:8080/user/confirm/"+id);
    System.out.println("Sending mail");
    javaMailSender.send(mailMessage);
    System.out.println("Sent");
  }
}
