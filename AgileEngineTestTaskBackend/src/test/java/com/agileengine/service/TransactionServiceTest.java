package com.agileengine.service;

import com.agileengine.exception.ResourceNotFoundException;
import com.agileengine.model.Transaction;
import com.agileengine.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    private static final String EXISTED_TRANSACTION_HASH = "12";
    private static final String NOT_EXISTED_TRANSACTION_HASH = "123421";

    @MockBean
    private TransactionRepository transactionRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TransactionService transactionService;

    @Before
    public void init() {
        transactionService = new TransactionServiceImpl(transactionRepository);
        final Transaction transaction = mock(Transaction.class);
        when(transaction.getHash()).thenReturn(EXISTED_TRANSACTION_HASH);
        when(transactionRepository.findByHash(transaction.getHash())).thenReturn(
                Optional.of(transaction)
        );
    }

    @Test
    public void verifyThatTransactionExist() {
        transactionService.getTransactionBy(EXISTED_TRANSACTION_HASH);
    }

    @Test
    public void verifyThatTransactionDoesntExist() {
        expectedException.expect(ResourceNotFoundException.class);
        transactionService.getTransactionBy(NOT_EXISTED_TRANSACTION_HASH);
    }
}