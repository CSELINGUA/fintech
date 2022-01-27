package com.jonathan.fintech.rabbitmq.listener;

import com.jonathan.fintech.rabbitmq.VO.Notification;
import com.jonathan.fintech.rabbitmq.VO.Response;
import com.jonathan.fintech.rabbitmq.configuration.MQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MQNotificationListener {
    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void Listener(Notification notification){

//        String url = "http://EMAIL/mailer/mail";
//        restTemplate.postForObject(url, notification, Response.class);
        System.out.println("Checking bugs here and there");

    }
}
