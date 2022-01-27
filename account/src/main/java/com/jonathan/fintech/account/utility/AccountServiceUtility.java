package com.jonathan.fintech.account.utility;

import com.jonathan.fintech.account.VO.JwtRequest;
import com.jonathan.fintech.account.VO.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
public class AccountServiceUtility {
    private final String username = "username";
    private final String password = "password";

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    public String getToken(){
        JwtRequest jwtRequest = new JwtRequest(username,password);
        JwtResponse jwtResponse = restTemplate.postForObject("http://TRANSACTION/transaction/authenticate",
                jwtRequest, JwtResponse.class);
        return jwtResponse.getToken();
    }
    public String getAccountNumber(){
        Random randomFirst5 = new Random(System.currentTimeMillis());
        Random randomSecond5 = new Random(System.currentTimeMillis());
        int first5 = 100000 + randomFirst5.nextInt(900000);
        int second5 = 100000 + randomSecond5.nextInt(900000);
        return String.valueOf(first5) + String.valueOf(second5);
    }



}
