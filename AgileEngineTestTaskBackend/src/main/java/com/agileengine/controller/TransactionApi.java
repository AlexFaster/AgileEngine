package com.agileengine.controller;

import com.agileengine.dto.out.TransactionDTO;
import com.agileengine.model.Transaction;
import com.agileengine.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionApi {

    private final TransactionService transactionService;

    private final ModelMapper modelMapper;

    @Autowired
    public TransactionApi(final TransactionService transactionService, final ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(
            path = "/v1/transactions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<TransactionDTO> getTransactions(final Pageable pageable) {
        final Page<Transaction> transactions = transactionService.getTransactions(pageable);
        final List<TransactionDTO> transactionsDTO = transactions.getContent()
                .stream()
                .map(trans -> modelMapper.map(trans, TransactionDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(
                transactionsDTO,
                pageable,
                transactions.getTotalElements()
        );
    }

    @DeleteMapping(
            path = "/v1/transactions/{hash}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void verifyTransaction(

            @PathVariable
            @NotBlank
            final String hash
    ) {
        transactionService.getTransactionBy(hash);
    }
}
