package com.agileengine.service;

import com.agileengine.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    Page<Transaction> getTransactions(Pageable pageable);

    Transaction getTransactionBy(String hash);
}
