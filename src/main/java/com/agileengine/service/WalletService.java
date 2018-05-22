package com.agileengine.service;

import com.agileengine.model.Wallet;

public interface WalletService {

    Wallet deposit(long walletId, long amount, String hash);

    Wallet withdraw(long walletId, long amount, String hash);

    Wallet balance(long walletId);
}
