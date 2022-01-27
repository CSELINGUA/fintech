package com.jonathan.fintech.account.dao;

import com.jonathan.fintech.account.model.Account;
import com.jonathan.fintech.account.model.AccountRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRegistrationDao extends JpaRepository<Account,Integer> {
    Account findByAccountNumber(String accountNumber);
}
