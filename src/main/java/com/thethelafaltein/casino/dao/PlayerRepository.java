package com.thethelafaltein.casino.dao;

import com.thethelafaltein.casino.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    @Query("SELECT p FROM Player p WHERE p.id = ?1")
    Optional<Player> findPlayerById(Long id);

    @Query("SELECT p FROM Player p WHERE p.username = ?1")
    Optional<Player>findPlayerByUsername(String username);
}
