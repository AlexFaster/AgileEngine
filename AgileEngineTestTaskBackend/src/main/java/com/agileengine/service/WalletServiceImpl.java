package com.agileengine.service;

import com.agileengine.config.date.RequestDate;
import com.agileengine.dto.out.BalanceDTO;
import com.agileengine.exception.InsufficientFundsException;
import com.agileengine.exception.NotFoundEnum;
import com.agileengine.exception.ResourceNotFoundException;
import com.agileengine.model.Transaction;
import com.agileengine.model.TransactionType;
import com.agileengine.model.Wallet;
import com.agileengine.repository.TransactionRepository;
import com.agileengine.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final RequestDate requestDate;

    @Autowired
    public WalletServiceImpl(
            final WalletRepository walletRepository,
            final TransactionRepository transactionRepository,
            final RequestDate requestDate
    ) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.requestDate = requestDate;
    }

    @Override
    @Transactional
    public Wallet deposit(final long walletId, final long amount, final String hash) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must exceed zero");
        }
        final Optional<Wallet> walletOpt = walletRepository.findById(walletId);
        if (walletOpt.isPresent()) {
            final Wallet wallet = walletOpt.get();
            wallet.setMoney(wallet.getMoney() + amount);
            walletRepository.save(wallet);
            final Transaction transaction = new Transaction(
                    TransactionType.DEPOSIT,
                    amount,
                    hash,
                    wallet,
                    requestDate.getDate()
            );
            transactionRepository.save(transaction);
            return wallet;
        }
        throw new ResourceNotFoundException(NotFoundEnum.WALLET);
    }

    @Override
    @Transactional
    public Wallet withdraw(final long walletId, final long amount, final String hash) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must exceed zero");
        }
        final Optional<Wallet> walletOpt = walletRepository.findById(walletId);
        if (walletOpt.isPresent()) {
            final Wallet wallet = walletOpt.get();
            if (wallet.getMoney() >= amount) {
                wallet.setMoney(wallet.getMoney() - amount);
                walletRepository.save(wallet);
                final Transaction transaction = new Transaction(
                        TransactionType.WITHDRAW,
                        amount,
                        hash,
                        wallet,
                        requestDate.getDate()
                );
                transactionRepository.save(transaction);
                return wallet;
            } else {
                throw new InsufficientFundsException(wallet.getMoney(), amount);
            }
        }
        throw new ResourceNotFoundException(NotFoundEnum.WALLET);
    }

    @Override
    @Transactional
    public Wallet balance(final long walletId) {
        final Optional<Wallet> walletOpt = walletRepository.findById(walletId);
        if (walletOpt.isPresent()) {
            return walletOpt.get();
        }
        throw new ResourceNotFoundException(NotFoundEnum.WALLET);
    }

}
