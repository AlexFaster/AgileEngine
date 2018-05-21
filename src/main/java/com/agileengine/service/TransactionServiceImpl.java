package com.agileengine.service;

import com.agileengine.dto.out.TransactionDTO;
import com.agileengine.exception.ResourceNotFoundException;
import com.agileengine.model.Transaction;
import com.agileengine.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionServiceImpl(final TransactionRepository transactionRepository, final ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<TransactionDTO> getTransactions(final Pageable pageable) {
        final Page<Transaction> transactions = transactionRepository.findAll(pageable);
        return new PageImpl<>(
                transactions.getContent().stream().map(trans -> modelMapper.map(trans, TransactionDTO.class)).collect(Collectors.toList()),
                pageable,
                transactions.getTotalElements()
        );
    }

    @Override
    public Transaction getTransactionBy(String hash) {
        final Optional<Transaction> transactionOptional = transactionRepository.findByHash(hash);
        if (transactionOptional.isPresent()) {
            return transactionOptional.get();
        }
        throw new ResourceNotFoundException();
    }
}