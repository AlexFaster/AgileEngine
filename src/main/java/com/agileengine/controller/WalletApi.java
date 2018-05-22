package com.agileengine.controller;

import com.agileengine.dto.in.MoneyDTO;
import com.agileengine.dto.out.BalanceDTO;
import com.agileengine.model.Wallet;
import com.agileengine.service.WalletService;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    @Autowired
    public WalletApi(final WalletService walletService, final ModelMapper modelMapper) {
        this.walletService = walletService;
        this.modelMapper = modelMapper;
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
        final Wallet wallet = walletService.deposit(walletId, moneyValue, hash);
        return modelMapper.map(wallet, BalanceDTO.class);
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
        final Wallet wallet = walletService.withdraw(walletId, moneyValue, hash);
        return modelMapper.map(wallet, BalanceDTO.class);
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
        final Wallet wallet = walletService.balance(walletId);
        return modelMapper.map(wallet, BalanceDTO.class);
    }


}
