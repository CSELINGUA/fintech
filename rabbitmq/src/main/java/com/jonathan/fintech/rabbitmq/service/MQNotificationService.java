package com.jonathan.fintech.rabbitmq.service;

import com.jonathan.fintech.rabbitmq.configuration.MQConfig;
import com.jonathan.fintech.rabbitmq.VO.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MQNotificationService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Notification convertAndSend(Notification notification){
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY, notification);
        return notification;
    }
}
