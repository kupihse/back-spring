package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by kiranreddy on 22/04/17.
 */
@RestController
@Service
public class EmailService {

  private JavaMailSender javaMailSender;

  @Autowired
  public EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @RequestMapping("/email")
  public String sendMail() {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo("imrzfgvb6324@gmail.com");
    mailMessage.setSubject("wtf");
    mailMessage.setText("wtf msg from gmail?");
    mailMessage.setFrom("admin@admin.com");
    javaMailSender.send(mailMessage);
    return "sent";
  }
}
