package com.agileengine.service;

import com.agileengine.exception.ResourceNotFoundException;
import com.agileengine.model.Transaction;
import com.agileengine.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<Transaction> getTransactions(final Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Transaction getTransactionBy(final String hash) {
        final Optional<Transaction> transactionOptional = transactionRepository.findByHash(hash);
        if (transactionOptional.isPresent()) {
            return transactionOptional.get();
        }
        throw new ResourceNotFoundException();
    }
}