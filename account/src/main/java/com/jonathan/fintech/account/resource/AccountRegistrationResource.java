package com.jonathan.fintech.account.resource;

import com.jonathan.fintech.account.model.AccountRegistration;
import com.jonathan.fintech.account.model.Response;
import com.jonathan.fintech.account.service.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/account")
public class AccountRegistrationResource {

    @Autowired
    AccountRegistrationService accountRegistrationService;

    @PostMapping("/clients")
    public ResponseEntity<Response> makeTransaction(@RequestBody AccountRegistration account){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now( ))
                        .data(of("Account creation: ", accountRegistrationService.createAccount(account)))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping("/clients/{accountNumber}")
    public ResponseEntity<Response> makeTransaction(@PathVariable String accountNumber){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now( ))
                        .data(of("Account creation: ",accountRegistrationService.getAccount(accountNumber)))
                        .message("")
                        .developerMessage("")
                        .reason("")
                        .build()
        );
    }

    //return all accounts
    @GetMapping("/clients")
    public ResponseEntity<Response> getAllAccounts(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("Available accounts: ",accountRegistrationService.getAllAccounts()))
                        .message("")
                        .developerMessage("")
                        .reason("")
                        .build()
        );
    }
}