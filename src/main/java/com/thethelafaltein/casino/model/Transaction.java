package com.thethelafaltein.casino.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@JsonIgnoreProperties("player")
public class Transaction {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
    private String transactionType;
    private Double balanceBeforeTransaction;
    private Double balanceAfterTransaction;
    private LocalDateTime transactionDateTime;

    public Transaction() {
    }

    public Transaction(Player player, String transactionType, Double balanceBeforeTransaction, Double balanceAfterTransaction, LocalDateTime transactionDateTime) {
        this.player = player;
        this.transactionType = transactionType;
        this.balanceBeforeTransaction = balanceBeforeTransaction;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.transactionDateTime = transactionDateTime;
    }

    public Transaction(Long id, Player player, String transactionType, Double balanceBeforeTransaction, Double balanceAfterTransaction, LocalDateTime transactionDateTime) {
        this.id = id;
        this.player = player;
        this.transactionType = transactionType;
        this.balanceBeforeTransaction = balanceBeforeTransaction;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.transactionDateTime = transactionDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public void setBalanceBeforeTransaction(Double balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}
