package com.agileengine.controller;

import com.agileengine.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/wallets")
public class WalletApi {

    private final WalletService walletService;

    @Autowired
    public WalletApi(final WalletService walletService) {
        this.walletService = walletService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{walletId}")
    public void deposit(

            @PathVariable
            @NotNull
            final Long walletId,

            final Long amount;
    ) {

        walletService.
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{walletId}")
    public void withdraw(

            @PathVariable
            @NotNull
            final Long walletId
    ) {
    }


}
