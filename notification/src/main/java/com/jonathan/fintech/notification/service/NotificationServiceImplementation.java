package com.jonathan.fintech.notification.service;

import com.jonathan.fintech.notification.VO.Response;
import com.jonathan.fintech.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImplementation implements NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendNotification(Notification notification) {
        String url = "http://EMAIL/mailer/mail";
        restTemplate.postForObject(url, notification, Response.class);

    }
}
