package com.jonathan.fintech.notification.service;

import com.jonathan.fintech.notification.model.Notification;
import org.springframework.stereotype.Service;

public interface NotificationService {
    public void sendNotification(Notification notification);

}
