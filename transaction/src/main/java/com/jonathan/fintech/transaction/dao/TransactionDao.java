package com.jonathan.fintech.transaction.dao;

import com.jonathan.fintech.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction,Integer> {

    public Transaction findByAccountNumber(String accountNumber);


}
