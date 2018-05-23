package com.agileengine.service;

import com.agileengine.config.date.RequestDate;
import com.agileengine.exception.InsufficientFundsException;
import com.agileengine.exception.ResourceNotFoundException;
import com.agileengine.model.Wallet;
import com.agileengine.repository.TransactionRepository;
import com.agileengine.repository.WalletRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class WalletServiceTest {

    private static final String DEFAULT_HASH = "12";

    private static final long EXIST_WALLET_ID = 1L;
    private static final long NOT_EXIST_WALLET_ID = 2L;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private WalletRepository walletRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private RequestDate requestDate;

    private WalletService walletService;

    @Before
    public void init() {
        walletService = new WalletServiceImpl(
                walletRepository,
                transactionRepository,
                requestDate
        );
        final Wallet wallet = new Wallet(500L);
        when(requestDate.getDate()).thenReturn(new Date());
        when(walletRepository.findById(EXIST_WALLET_ID)).thenReturn(Optional.of(wallet));
    }

    @Test
    public void verifyThatWalletDoesntExist() {
        expectedException.expect(ResourceNotFoundException.class);
        walletService.deposit(NOT_EXIST_WALLET_ID, 1, DEFAULT_HASH);
    }

    @Test
    public void verifyThatNegativeDepositFailed() {
        expectedException.expect(IllegalArgumentException.class);
        walletService.deposit(EXIST_WALLET_ID, -1, DEFAULT_HASH);
    }

    @Test
    public void verifyThatZeroDepositFailed() {
        expectedException.expect(IllegalArgumentException.class);
        walletService.deposit(EXIST_WALLET_ID, 0, DEFAULT_HASH);
    }

    @Test
    public void verifyThatDepositIsSuccessful() {
        final Wallet wallet = walletService.deposit(EXIST_WALLET_ID, 200L, DEFAULT_HASH);
        assertThat(wallet.getMoney(), is(700L));
        verify(transactionRepository, times(1)).save(any());
        verify(walletRepository, times(1)).save(any());
    }

    @Test
    public void verifyThatNegativeWithdrawFailed() {
        expectedException.expect(IllegalArgumentException.class);
        walletService.withdraw(EXIST_WALLET_ID, -1, DEFAULT_HASH);
    }

    @Test
    public void verifyThatZeroWithdrawFailed() {
        expectedException.expect(IllegalArgumentException.class);
        walletService.withdraw(EXIST_WALLET_ID, 0, DEFAULT_HASH);
    }

    @Test
    public void verifyThatWithdrawSuccessfulInCaseOfFullWithdraw() {
        final Wallet wallet = walletService.withdraw(EXIST_WALLET_ID, 500L, DEFAULT_HASH);
        assertThat(wallet.getMoney(), is(0L));
        verify(transactionRepository, times(1)).save(any());
        verify(walletRepository, times(1)).save(any());
    }

    @Test
    public void verifyThatWithdrawSuccessfulInCaseOfPartialWithdraw() {
        final Wallet wallet = walletService.withdraw(EXIST_WALLET_ID, 300L, DEFAULT_HASH);
        assertThat(wallet.getMoney(), is(200L));
        verify(transactionRepository, times(1)).save(any());
        verify(walletRepository, times(1)).save(any());
    }

    @Test
    public void verifyThatWithdrawFailsIfInsufficientMoney() {
        expectedException.expect(InsufficientFundsException.class);
        walletService.withdraw(EXIST_WALLET_ID, 501L, DEFAULT_HASH);
    }

}