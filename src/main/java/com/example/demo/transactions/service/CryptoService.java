package com.example.demo.transactions.service;

import com.example.demo.transactions.dao.CryptoTxn;
import com.example.demo.transactions.repo.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    public CryptoTxn addNewTxn(String symbol, BigDecimal amount) {
        return cryptoRepository.save(new CryptoTxn(symbol, amount));
    }
}
