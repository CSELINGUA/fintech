package com.jonathan.fintech.notification.resource;

import com.jonathan.fintech.notification.model.Notification;
import com.jonathan.fintech.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationResource {

    @Autowired
    private NotificationService accountRegistrationService;

    @PostMapping("/notify")
    public void sendNotification(@RequestBody Notification notification){
        accountRegistrationService.sendNotification(notification);
    }

}
