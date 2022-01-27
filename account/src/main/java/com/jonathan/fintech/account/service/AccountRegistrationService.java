package com.jonathan.fintech.account.service;

import com.jonathan.fintech.account.model.Account;
import com.jonathan.fintech.account.model.AccountRegistration;

import java.util.List;

public interface AccountRegistrationService {


    public Account createAccount(AccountRegistration account);

    public Account getAccount(String accountNumber);

    public boolean isBVNConnected(String accountNumbr);

    public List<Account> getAllAccounts();

    void deleteAccount(Integer id);

    void updateAccount(Account account);
}
