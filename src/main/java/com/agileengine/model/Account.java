package com.agileengine.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long money;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private Set<Transaction> transactions;

    public Account() {
        transactions = new HashSet<>();
    }

    public Account(Long money) {
        this();
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
