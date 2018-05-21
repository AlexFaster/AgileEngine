package com.agileengine.controller;

import com.agileengine.dto.in.MoneyDTO;
import com.agileengine.dto.out.BalanceDTO;
import com.agileengine.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/api")
public class WalletApi {

    private final WalletService walletService;

    @Autowired
    public WalletApi(final WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping(
            path = "/v1/wallets/{walletId}/deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BalanceDTO deposit(

            @PathVariable
            @NotNull
            @Min(1)
            final Long walletId,

            @RequestBody
            @Valid
            @NotNull
            final MoneyDTO money
    ) {
        final long moneyValue = money.getAmount();
        final String hash = money.getHash();
        return walletService.deposit(walletId, moneyValue, hash);
    }

    @PostMapping(
            path = "/v1/wallets/{walletId}/withdraw",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BalanceDTO withdraw(

            @PathVariable
            @NotNull
            @Min(1)
            final Long walletId,

            @RequestBody
            @Valid
            @NotNull
            final MoneyDTO money
    ) {
        final long moneyValue = money.getAmount();
        final String hash = money.getHash();
        return walletService.withdraw(walletId, moneyValue, hash);
    }

    @GetMapping(
            path = "/v1/wallets/{walletId}/balance",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BalanceDTO balance(

            @PathVariable
            @NotNull
            @Min(1)
            final Long walletId
    ) {
        return walletService.balance(walletId);
    }


}
