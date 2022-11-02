package com.example.demo.transactions;

import com.example.demo.transactions.dto.AddTxnReq;
import com.example.demo.transactions.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/txn")
public class WalletController {

    @Autowired
    WalletService walletService;

    @PostMapping("add")
    public ResponseEntity<?> addTxn(@Valid @RequestBody AddTxnReq request) {
        return ResponseEntity.ok().body(walletService.addTxn(request.getSymbol(), request.getAmount(), request.getIsThrowNeeded()));
    }
}
