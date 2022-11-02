package com.example.demo.transactions.service;

import com.example.demo.transactions.dao.CryptoTxn;
import com.example.demo.transactions.dao.FiatTxn;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class WalletService {

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private FiatService fiatService;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Map<String, ?> addTxn(String symbol, BigDecimal amount, boolean isThrowNeeded) {
        CryptoTxn cryptoTxn = cryptoService.addNewTxn(symbol, amount);
        FiatTxn fiatTxn = fiatService.addNewTxn(symbol, amount, isThrowNeeded);

        return Map.of("CRYPTO", cryptoTxn, "FIAT", fiatTxn);
    }
}
