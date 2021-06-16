package com.thethelafaltein.casino.dao;

import com.thethelafaltein.casino.model.Player;
import com.thethelafaltein.casino.model.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PlayerConfig {

    @Bean
    CommandLineRunner commandLineRunner(PlayerRepository playerRepository){
        return args -> {
            Player tim = new Player(
                   "Tim_1975" ,
                    "Tim",
                    "Smit",
                    "067754678",
                    "TimSm@yahoo.com",
                    55,
                    1000D,
                    new ArrayList<Transaction>()

            );
            Player tshepo = new Player(
                    "tshepo2" ,
                    "Tshepo",
                    "Ndlovu",
                    "067754532",
                    "tshepo@gmail.com",
                    32,
                    2000D,
                    new ArrayList<Transaction>()

            );
            Player cindy = new Player(
                    "cindy05" ,
                    "Cindy",
                    "Baker",
                    "0784535",
                    "cindyBa@yahoo.com",
                    26,
                    5000D,
                    new ArrayList<Transaction>()

            );

            ArrayList<Player>list = new ArrayList<>();
            list.add(cindy);
            list.add(tim);
            list.add(tshepo);
            playerRepository.saveAll(list);
        };
    }
}
