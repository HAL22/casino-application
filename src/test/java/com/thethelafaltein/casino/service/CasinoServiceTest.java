package com.thethelafaltein.casino.service;

import com.thethelafaltein.casino.dao.PlayerRepository;
import com.thethelafaltein.casino.dao.TransactionRepoistory;
import com.thethelafaltein.casino.exception.ApiOutofBalanceException;
import com.thethelafaltein.casino.exception.ApiPlayerException;
import com.thethelafaltein.casino.model.Player;
import com.thethelafaltein.casino.model.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CasinoServiceTest {

    @Mock private PlayerRepository playerRepository;
    @Mock private TransactionRepoistory transactionRepoistory;
    private CasinoService casinoService;

    @BeforeEach
    void setUp() {
        casinoService = new CasinoService(playerRepository,transactionRepoistory);

    }

    @Test
    void canDeposit() {
        Player player = new Player(
                1L,
                "Tim_1975" ,
                "Tim",
                "Smit",
                "067754678",
                "TimSm@yahoo.com",
                55,
                1000D,
                new ArrayList<Transaction>()

        );
        Long transactionId = 10L;
        Double amount = 100D;
        Double newBalance = player.getBalance() + amount;
        given(playerRepository.findPlayerById(player.getId())).willReturn(Optional.of(player));
        casinoService.deposit(player.getId(),transactionId,amount);
        ArgumentCaptor<Transaction>transactionArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepoistory).save(transactionArgumentCaptor.capture());
        Transaction transaction = transactionArgumentCaptor.getValue();
        assertThat(transaction.getId()).isEqualTo(transactionId);
        assertThat(transaction.getBalanceAfterTransaction()).isEqualTo(newBalance);
        assertThat(transaction.getTransactionType()).isEqualTo(CasinoService.TRANSACTION_TYPES[1]);
    }

    @Test
    void willThrowPlayerDoesNotExist(){
        given(playerRepository.findPlayerById(anyLong())).willReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> casinoService.deposit(1L,10L,100D))
                .isInstanceOf(ApiPlayerException.class)
                .hasMessageContaining("Player does not exist");

        assertThatThrownBy(() -> casinoService.deduct(1L,10L,100D))
                .isInstanceOf(ApiPlayerException.class)
                .hasMessageContaining("Player does not exist");
    }

    @Test
    void willThrowOutOfFunds(){
        Player player = new Player(
                1L,
                "Tim_1975" ,
                "Tim",
                "Smit",
                "067754678",
                "TimSm@yahoo.com",
                55,
                1D,
                new ArrayList<Transaction>()

        );
        given(playerRepository.findPlayerById(player.getId())).willReturn(Optional.of(player));
        assertThatThrownBy(() -> casinoService.deduct(1L,10L,100D))
                .isInstanceOf(ApiOutofBalanceException.class)
                .hasMessageContaining("Insufficient funds");
    }

    @Test
    void deduct() {
        Player player = new Player(
                1L,
                "Tim_1975" ,
                "Tim",
                "Smit",
                "067754678",
                "TimSm@yahoo.com",
                55,
                1000D,
                new ArrayList<Transaction>()

        );
        Long transactionId = 10L;
        Double amount = 100D;
        Double newBalance = player.getBalance() - amount;
        given(playerRepository.findPlayerById(player.getId())).willReturn(Optional.of(player));
        casinoService.deduct(player.getId(),transactionId,amount);
        ArgumentCaptor<Transaction>transactionArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepoistory).save(transactionArgumentCaptor.capture());
        Transaction transaction = transactionArgumentCaptor.getValue();
        assertThat(transaction.getId()).isEqualTo(transactionId);
        assertThat(transaction.getBalanceAfterTransaction()).isEqualTo(newBalance);
        assertThat(transaction.getTransactionType()).isEqualTo(CasinoService.TRANSACTION_TYPES[2]);
    }



    @Test
    void getBalance() {
        Player player = new Player(
                1L,
                "Tim_1975" ,
                "Tim",
                "Smit",
                "067754678",
                "TimSm@yahoo.com",
                55,
                1000D,
                new ArrayList<Transaction>()

        );
        Long transactionId = 10L;
        Transaction newTransaction = new Transaction(
                transactionId,
                player,
                CasinoService.TRANSACTION_TYPES[0],
                player.getBalance(),
                player.getBalance(),
                LocalDateTime.now()
        );
        given(playerRepository.findPlayerById(player.getId())).willReturn(Optional.of(player));
        casinoService.getBalance(player.getId(),transactionId);
        ArgumentCaptor<Transaction>transactionArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepoistory).save(transactionArgumentCaptor.capture());
        Transaction transaction = transactionArgumentCaptor.getValue();
        assertThat(transaction.getId()).isEqualTo(transactionId);
        assertThat(transaction.getBalanceAfterTransaction()).isEqualTo(player.getBalance());
        assertThat(transaction.getTransactionType()).isEqualTo(CasinoService.TRANSACTION_TYPES[0]);
    }

}