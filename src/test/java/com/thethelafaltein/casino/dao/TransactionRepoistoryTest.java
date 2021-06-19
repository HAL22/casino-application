package com.thethelafaltein.casino.dao;

import com.thethelafaltein.casino.model.Player;
import com.thethelafaltein.casino.model.Transaction;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class TransactionRepoistoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TransactionRepoistory transactionRepoistory;

    @AfterEach
    void tearDown() {
        transactionRepoistory.deleteAll();
        playerRepository.deleteAll();

    }

    @Test
    void itShouldfindTransactionById() {
        Player player= new Player(
                "Tim_1" ,
                "Tim",
                "Smit",
                "067754678",
                "TimSm@yahoo.com",
                65,
                2000D,
                new ArrayList<Transaction>()
        );
        playerRepository.save(player);
        Transaction transaction = new Transaction(10L, player, "Balance", 100D, 100D, LocalDateTime.now());
        transactionRepoistory.save(transaction);
        Optional<Transaction>transactionById = transactionRepoistory.findTransactionById(10L);
        assertThat(transactionById.isPresent()).isTrue();
    }

    @Test
    void itShouldNotfindTransactionById(){
        Player player= new Player(
                "Tim_1" ,
                "Tim",
                "Smit",
                "067754678",
                "TimSm@yahoo.com",
                65,
                2000D,
                new ArrayList<Transaction>()

        );
        playerRepository.save(player);
        Transaction transaction = new Transaction(11L, player, "Balance", 100D, 100D, LocalDateTime.now());
        transactionRepoistory.save(transaction);
        Optional<Transaction>transactionById = transactionRepoistory.findTransactionById(1L);
        assertThat(transactionById.isPresent()).isFalse();
    }

    @Test
    void itShouldfindAllByPlayer() {
        Player player1= new Player(
                "Tim_1" ,
                "Tim",
                "Smit",
                "067754678",
                "TimSm@yahoo.com",
                65,
                2000D,
                new ArrayList<Transaction>()

        );
        Player player2 = new Player(
                "Tshepo" ,
                "Tshepo",
                "Ndlovu",
                "067754532",
                "tshepo@gmail.com",
                32,
                2000D,
                new ArrayList<Transaction>()
        );
        playerRepository.save(player1);
        playerRepository.save(player2);
        Transaction transaction1 = new Transaction(100L, player1, "Balance", 100D, 100D, LocalDateTime.now());
        Transaction transaction2 = new Transaction(101L, player1, "Deposit", 100D, 120D, LocalDateTime.now());
        Transaction transaction3 = new Transaction(102L, player2, "Balance", 100D, 100D, LocalDateTime.now());
        transactionRepoistory.save(transaction1);
        transactionRepoistory.save(transaction2);
        transactionRepoistory.save(transaction3);
        Optional<List<Transaction>> transactionRepoistoryAllByPlayer = transactionRepoistory.findAllByPlayer(4L);
        assertThat(transactionRepoistoryAllByPlayer.get().size()).isEqualTo(2);
    }

    @Test
    void itShouldNotfindAllByPlayer(){
        Player player1= new Player(
                "Tim_1" ,
                "Tim",
                "Smit",
                "067754678",
                "TimSm@yahoo.com",
                65,
                2000D,
                new ArrayList<Transaction>()

        );
        Player player2 = new Player(
                "Tshepo" ,
                "Tshepo",
                "Ndlovu",
                "067754532",
                "tshepo@gmail.com",
                32,
                2000D,
                new ArrayList<Transaction>()
        );
        playerRepository.save(player1);
        playerRepository.save(player2);
        Transaction transaction1 = new Transaction(100L, player1, "Balance", 100D, 100D, LocalDateTime.now());
        Transaction transaction2 = new Transaction(101L, player1, "Deposit", 100D, 120D, LocalDateTime.now());
        Transaction transaction3 = new Transaction(102L, player2, "Balance", 100D, 100D, LocalDateTime.now());
        transactionRepoistory.save(transaction1);
        transactionRepoistory.save(transaction2);
        transactionRepoistory.save(transaction3);
        Optional<List<Transaction>> transactionRepoistoryAllByPlayer = transactionRepoistory.findAllByPlayer(1L);
        assertThat(transactionRepoistoryAllByPlayer.get().size()).isEqualTo(0);

    }
}