package com.jonathan.fintech.transaction.service;

import com.jonathan.fintech.transaction.model.Ledger;
import com.jonathan.fintech.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionService{

    public Transaction makeTransaction(Transaction transaction);

    public void getTransactionByAccountNumber(String accountNumber);

    Optional<Transaction> getTransactionById(Integer id);

    public List<Transaction> getAllTransactions();

    public void revertTransaction();



}
