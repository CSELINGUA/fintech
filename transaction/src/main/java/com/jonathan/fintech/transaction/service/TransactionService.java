package com.jonathan.fintech.transaction.service;

import com.jonathan.fintech.transaction.model.Ledger;
import com.jonathan.fintech.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TransactionService{

    Transaction makeTransaction(Transaction transaction);

    void getTransactionByAccountNumber(String accountNumber);

    Optional<Transaction> getTransactionById(Integer id);

    List<Transaction> getAllTransactions();

    void revertTransaction();



}
