package com.example.demo.transactions.service;

import com.example.demo.transactions.dao.FiatTxn;
import com.example.demo.transactions.repo.FiatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FiatService {

    @Autowired
    private FiatRepository fiatRepository;

    public FiatTxn addNewTxn(String symbol, BigDecimal amount, boolean isThrowNeeded) throws Exception {
        // Intentionally throwing error to verify roll-back functionality
        if (isThrowNeeded) throw new Exception();
        return fiatRepository.save(new FiatTxn(symbol, amount));
    }
}
