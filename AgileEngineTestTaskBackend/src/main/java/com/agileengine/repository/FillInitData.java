package com.agileengine.repository;

import com.agileengine.model.Wallet;
import com.agileengine.model.Transaction;
import com.agileengine.model.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public class FillInitData implements CommandLineRunner {

    private static final Logger LOG =
            LoggerFactory.getLogger(FillInitData.class);

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public FillInitData(
            final WalletRepository walletRepository,
            final TransactionRepository transactionRepository
    ) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        LOG.info("Start fill up db");
        final Date requestTime = new Date();
        final Wallet wallet = new Wallet(500L);
        walletRepository.save(wallet);
        transactionRepository.save(
                new Transaction(
                        TransactionType.DEPOSIT,
                        100L,
                        "123",
                        wallet,
                        requestTime
                )
        );
        transactionRepository.save(
                new Transaction(
                        TransactionType.WITHDRAW,
                        200L,
                        "456",
                        wallet,
                        requestTime
                )
        );
        LOG.info("Finish fill up db");
    }

}
