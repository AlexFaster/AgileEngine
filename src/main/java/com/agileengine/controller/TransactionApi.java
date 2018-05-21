package com.agileengine.controller;

import com.agileengine.dto.TransactionDTO;
import com.agileengine.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionApi {

    private final TransactionServiceImpl transactionService;

    @Autowired
    public TransactionApi(final TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<TransactionDTO> getTransactions(final Pageable pageable) {
        return transactionService.getTransactions(pageable);
    }
}
