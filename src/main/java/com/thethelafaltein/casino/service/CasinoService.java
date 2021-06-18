/*
 * The Business logic Layer
 */

package com.thethelafaltein.casino.service;

import com.thethelafaltein.casino.dao.PlayerRepository;
import com.thethelafaltein.casino.dao.TransactionRepoistory;
import com.thethelafaltein.casino.exception.ApiOutofBalanceException;
import com.thethelafaltein.casino.exception.ApiPlayerException;
import com.thethelafaltein.casino.model.Player;
import com.thethelafaltein.casino.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CasinoService {

    private PlayerRepository playerRepository;
    private TransactionRepoistory transactionRepoistory;

    @Autowired
    public CasinoService(PlayerRepository playerRepository, TransactionRepoistory transactionRepoistory) {
        this.playerRepository = playerRepository;
        this.transactionRepoistory = transactionRepoistory;
    }

    @Transactional
    public void deposit(Long playerId,Long transactionId,Double amount){
        Optional<Player>playerById = playerRepository.findPlayerById(playerId);
        Optional<Transaction>transactionById = transactionRepoistory.findTransactionById(transactionId);
        if(!playerById.isPresent()){
            throw new ApiPlayerException("Player does not exist");
        }
        if(!transactionById.isPresent()){
            Player player = playerById.get();
            Double amountBefore = player.getBalance();
            if(amount != null){
                player.updateBalance(amount);
                Transaction newTransaction = new Transaction(
                        transactionId,
                        player,
                        "Deposit",
                        amountBefore,
                        player.getBalance(),
                        LocalDateTime.now()

                );
                transactionRepoistory.save(newTransaction);

            }
        }
    }

    @Transactional
    public void deduct(Long playerId,Long transactionId,Double amount){
        Optional<Player>playerById = playerRepository.findPlayerById(playerId);
        Optional<Transaction>transactionById = transactionRepoistory.findTransactionById(transactionId);
        if(!playerById.isPresent()){
            throw new ApiPlayerException("Player does not exist");
        }
        if(!transactionById.isPresent()){
            Player player = playerById.get();
            Double amountBefore = player.getBalance();
            if(amount != null){
                if(amount > amountBefore){
                    throw new ApiOutofBalanceException("Insufficient funds");
                }
                player.updateBalance(-1*Math.abs(amount));
                Transaction newTransaction = new Transaction(
                        transactionId,
                        player,
                        "Deduct",
                        amountBefore,
                        player.getBalance(),
                        LocalDateTime.now()

                );
                transactionRepoistory.save(newTransaction);
            }
        }

    }

    public Double getBalance(Long playerId,Long transactionId){
        Optional<Player>playerById = playerRepository.findPlayerById(playerId);
        Optional<Transaction>transactionById = transactionRepoistory.findTransactionById(transactionId);
        if(!playerById.isPresent()){
            throw new ApiPlayerException("Player does not exist");
        }
        Player player = playerById.get();
        if(!transactionById.isPresent()){
            Transaction newTransaction = new Transaction(
                    transactionId,
                    player,
                    "Balance",
                    player.getBalance(),
                    player.getBalance(),
                    LocalDateTime.now()
            );
            transactionRepoistory.save(newTransaction);
        }
        return player.getBalance();
    }

    public List<Transaction>getTransactions(String username){
        Optional<Player>playerByUsername = playerRepository.findPlayerByUsername(username);
        if(!playerByUsername.isPresent()){
            throw new ApiPlayerException("Player does not exist");
        }
        Player player = playerByUsername.get();

        Optional<List<Transaction>>transactionRepoistoryAllByPlayer = transactionRepoistory.findAllByPlayer(player.getId());

        List<Transaction>transactionList=null;

        if(transactionRepoistoryAllByPlayer.isPresent()){
            transactionList = transactionRepoistoryAllByPlayer.get();
        }


        return transactionList;


    }

}
