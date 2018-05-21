package com.agileengine.repository;

import com.agileengine.model.Account;
import com.agileengine.model.Transaction;
import com.agileengine.model.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class FillInitData implements CommandLineRunner {

    private static final Logger LOG =
            LoggerFactory.getLogger(FillInitData.class);

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public FillInitData(
            final AccountRepository accountRepository,
            final TransactionRepository transactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        LOG.info("Start fill up db");
        final Account account = new Account(500L);
        accountRepository.save(account);
        transactionRepository.save(
                new Transaction(
                        TransactionType.DEPOSIT,
                        100L,
                        account
                )
        );
        transactionRepository.save(
                new Transaction(
                        TransactionType.WITHDRAW,
                        200L,
                        account
                )
        );
        LOG.info("Finish fill up db");
    }

}
