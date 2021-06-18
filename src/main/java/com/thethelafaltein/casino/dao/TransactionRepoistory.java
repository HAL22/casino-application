package com.thethelafaltein.casino.dao;

import com.thethelafaltein.casino.model.Player;
import com.thethelafaltein.casino.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepoistory extends JpaRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE t.id = ?1")
    Optional<Transaction> findTransactionById(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.player.id = ?1")
    Optional<List<Transaction>> findAllByPlayer(Long playerID);
}
