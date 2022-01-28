package com.jonathan.fintech.account.service;

import com.jonathan.fintech.account.VO.Ledger;
import com.jonathan.fintech.account.VO.Notification;
import com.jonathan.fintech.account.dao.AccountRegistrationDao;
import com.jonathan.fintech.account.model.Account;
import com.jonathan.fintech.account.model.AccountRegistration;
import com.jonathan.fintech.account.model.Response;
import com.jonathan.fintech.account.utility.AccountServiceUtility;
import com.jonathan.fintech.account.utility.StringUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
public class AccountRegistrationServiceImplementation implements AccountRegistrationService {

    @Autowired
    AccountRegistrationDao accountRegistrationDao;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AccountServiceUtility accountServiceUtility;

    @Override
    public Account createAccount(AccountRegistration accountRegistration) {

        Account account = new Account();
        account.setAccountCreated(now());
        account.setDob(accountRegistration.getDob());
        account.setEmail(accountRegistration.getEmail());
        account.setName(accountRegistration.getName());
        account.setAccountNumber(accountServiceUtility.getAccountNumber());


        //Notification builder and API call in the Notification microservice
        Notification notification = new Notification();
        notification.setAccountNumber(account.getAccountNumber());
        notification.setEmail(account.getEmail());
        notification.setMessage("Creation successful");
        notification.setOperation(StringUtility.ACCOUNT_CREATION);

        String urlNotification = "http://NOTIFICATION/notification/notify";
        restTemplate.postForObject(urlNotification, notification, Response.class);


        //Ledger object and update on the Transaction microservice
        Ledger ledger = new Ledger();
        ledger.setOperationType(StringUtility.ACCOUNT_CREATION);
        ledger.setActorAccountNumber(accountServiceUtility.getAccountNumber());


        Account account1 = accountRegistrationDao.save(account);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String token = accountServiceUtility.getToken();
        if(token != null) {
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Ledger> entity = new HttpEntity<Ledger>(ledger, headers);
            String url = "http://TRANSACTION/transaction/ledger";
            log.info(restTemplate.exchange(url, HttpMethod.POST, entity, Response.class).toString());
        }
        return account1;
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountRegistrationDao.findByAccountNumber(accountNumber);
    }

    @Override
    public boolean isBVNConnected(String accountNumber) {
        Account account = accountRegistrationDao.findByAccountNumber(accountNumber);
        return account.getBvn() != null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRegistrationDao.findAll();
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRegistrationDao.deleteById(id);
    }
    @Override
    public void updateAccount(Account account){
        accountRegistrationDao.save(account);

    }
}