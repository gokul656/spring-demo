package com.example.demo.transactions.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FiatTxn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String symbol;

    private BigDecimal price;

    public FiatTxn(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }
}
