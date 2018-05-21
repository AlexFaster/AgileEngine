package com.agileengine.controller;

import com.agileengine.dto.out.TransactionDTO;
import com.agileengine.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api")
public class TransactionApi {

    private final TransactionServiceImpl transactionService;

    @Autowired
    public TransactionApi(final TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(
            path = "/v1/transactions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<TransactionDTO> getTransactions(final Pageable pageable) {
        return transactionService.getTransactions(pageable);
    }

    @GetMapping(
            path = "/v1/transactions/{hash}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void verifyTransaction(

            @PathVariable
            @NotBlank
            final String hash
    ) {
        transactionService.verifyTransaction(hash);
    }
}
