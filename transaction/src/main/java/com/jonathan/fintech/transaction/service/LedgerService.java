package com.jonathan.fintech.transaction.service;

import com.jonathan.fintech.transaction.model.Ledger;

import java.util.List;

public interface LedgerService {
    public Ledger addLedger(Ledger ledger);
    public List<Ledger> getAllLedgers();
}
