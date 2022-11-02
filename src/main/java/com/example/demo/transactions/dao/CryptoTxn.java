package com.example.demo.transactions.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CryptoTxn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String symbol;

    private BigDecimal price;

    public CryptoTxn(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }
}
