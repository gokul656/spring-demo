package com.example.demo.transactions.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class AddTxnReq {
    @NotNull
    private String symbol;

    @NotNull
    private BigDecimal amount;

    private final Boolean isThrowNeeded = false;
}
