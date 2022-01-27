package com.jonathan.fintech.rabbitmq.resource;

import com.jonathan.fintech.rabbitmq.VO.Notification;
import com.jonathan.fintech.rabbitmq.VO.Response;
import com.jonathan.fintech.rabbitmq.service.MQNotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/publisher")
public class MQNotificationPublisherResource {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MQNotificationService mqNotificationService;

    @ResponseBody
    @PostMapping("/publish")
    public ResponseEntity<Response> publishMessage(@RequestBody Notification notification){
        return ResponseEntity.ok(
                Response.builder()
                        .data(of("Publisher",mqNotificationService.convertAndSend(notification)))
                        .message("Notification being publisher to mailer")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
