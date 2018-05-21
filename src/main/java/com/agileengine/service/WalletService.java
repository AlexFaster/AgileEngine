package com.agileengine.service;

import com.agileengine.dto.out.BalanceDTO;

public interface WalletService {

    BalanceDTO deposit(long walletId, long amount, String hash);

    BalanceDTO withdraw(long walletId, long amount, String hash);

    BalanceDTO balance(long walletId);
}
