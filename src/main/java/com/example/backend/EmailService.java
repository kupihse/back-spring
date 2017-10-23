package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

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

//  private static void set() throws Exception{
//    FileInputStream in = new FileInputStream("application.properties");
//    Properties props =  new Properties();
//    props.load(in);
//    javaMailSender.setJavaMailProperties(props);
//    in.close();
//  }

  public void sendMail(String email, String id) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(email);
    mailMessage.setSubject("Confirm registration");
    // add link
    mailMessage.setText("Follow the link to finish your registraton:\n"
            +"http://localhost:8080/user/confirm/"+id);
    javaMailSender.send(mailMessage);
  }
}
