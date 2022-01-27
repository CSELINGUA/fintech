package com.jonathan.fintech.email.client.resource;

import com.jonathan.fintech.email.client.VO.Response;
import com.jonathan.fintech.email.client.service.EmailSenderService;
import com.jonathan.fintech.email.client.VO.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/mailer")
public class EmailSenderResource {
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/mail")
    public ResponseEntity<Response> sendMail(@RequestBody Notification notification){
        return ResponseEntity.ok(
                Response.builder()
                        .data(of("Mailer",emailSenderService.sendSimpleEmail(notification)))
                        .timeStamp(now())
                        .status(OK)
                        .message("Sending a mail")
                        .statusCode(OK.value())
                        .build()
        );
    }
}
