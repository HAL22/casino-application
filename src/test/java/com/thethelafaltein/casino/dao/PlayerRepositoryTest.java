package com.thethelafaltein.casino.dao;

import com.thethelafaltein.casino.model.Player;
import com.thethelafaltein.casino.model.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @AfterEach
    void tearDown() {
        playerRepository.deleteAll();
    }

    @Test
    void itShouldfindPlayerById() {
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
        Optional<Player>playerById = playerRepository.findPlayerById(2L);
        assertThat(playerById.isPresent()).isTrue();
    }

    @Test
    void itShouldNotFindPlayerById(){
        Optional<Player>playerById = playerRepository.findPlayerById(1L);
        assertThat(playerById.isPresent()).isFalse();
    }

    @Test
    void itShouldfindPlayerByUsername() {
        String username = "tshepo2";
        Player player = new Player(
                username ,
                "Tshepo",
                "Ndlovu",
                "067754532",
                "tshepo@gmail.com",
                32,
                2000D,
                new ArrayList<Transaction>()
        );
        playerRepository.save(player);
        Optional<Player>playerByUsername = playerRepository.findPlayerByUsername(username);
        assertThat(playerByUsername.isPresent()).isTrue();
    }

    @Test
    void itShouldNotFindPlayerByUsername(){
        String username = "name";
        Player player = new Player(
                "Tshepo2" ,
                "Tshepo",
                "Ndlovu",
                "067754532",
                "tshepo@gmail.com",
                32,
                2000D,
                new ArrayList<Transaction>()
        );
        playerRepository.save(player);
        Optional<Player>playerByUsername = playerRepository.findPlayerByUsername(username);
        assertThat(playerByUsername.isPresent()).isFalse();
    }


}