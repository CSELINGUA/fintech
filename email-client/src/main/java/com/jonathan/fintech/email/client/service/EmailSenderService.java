package com.jonathan.fintech.email.client.service;

import com.jonathan.fintech.email.client.VO.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public Notification sendSimpleEmail(Notification notification){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(notification.getOperation());
        simpleMailMessage.setText((notification.getMessage()) +"\nAccount Number: "+notification.getAccountNumber());
        simpleMailMessage.setTo(notification.getEmail());
        simpleMailMessage.setFrom("salcsjoe@gmail.com");

        javaMailSender.send(simpleMailMessage);
        return notification;
    }
}