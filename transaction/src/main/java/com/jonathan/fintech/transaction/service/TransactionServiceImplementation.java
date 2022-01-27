package com.jonathan.fintech.transaction.service;

import com.jonathan.fintech.transaction.VO.Notification;
import com.jonathan.fintech.transaction.dao.LedgerDao;
import com.jonathan.fintech.transaction.dao.TransactionDao;
import com.jonathan.fintech.transaction.model.Ledger;
import com.jonathan.fintech.transaction.model.Response;
import com.jonathan.fintech.transaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
public class TransactionServiceImplementation implements TransactionService, LedgerService, SecurityConfigurationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private LedgerDao ledgerDao;

    private final String USERNAME = "admin";
    private final String PASSWORD = "password";


    @Override
    public Transaction makeTransaction(Transaction transaction) {
        Notification notification = Notification.builder()
                .accountNumber(transaction.getAccountNumber())
                .message(transaction.getMessage())
                .email(transaction.getEmail())
                .operation(transaction.getOperation())
                .build();

        String urlNotification = "http://NOTIFICATION/notification/notify";
//        String urlAccount = "http://ACCOUNT/account/clients/{accountNumber}";

       /* Response response = new Response();
        response = restTemplate.getForObject(urlAccount, Response.class, transaction.getAccountNumber());
        if(response.toString().contains("accountNumber")) {
            restTemplate.postForObject(urlNotification, notification, Response.class);
            return transactionDao.save(transaction);
        }
        else
            return null;*/
        restTemplate.postForObject(urlNotification, notification, Response.class);
        return transactionDao.save(transaction);
    }

    @Override
    public void getTransactionByAccountNumber(String accountNumber) {
        transactionDao.findByAccountNumber(accountNumber);
    }
    @Override
    public Optional<Transaction> getTransactionById(Integer id){
        return transactionDao.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDao.findAll();
    }

    @Override
    public void revertTransaction() {

    }

    @Override
    public Ledger addLedger(Ledger ledger) {
        ledger.setDateAndTime(now());
        return ledgerDao.save(ledger);

    }

    @Override
    public List<Ledger> getAllLedgers() {
        return ledgerDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Hardcoded login parameters
        return new User(USERNAME, PASSWORD, new ArrayList<>());
    }
}
