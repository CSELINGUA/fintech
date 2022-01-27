package com.jonathan.fintech.transaction.dao;

import com.jonathan.fintech.transaction.model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerDao extends JpaRepository<Ledger,Integer> {

}
