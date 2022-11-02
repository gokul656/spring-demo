package com.example.demo.callablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.Callable;

@Slf4j
public class CustomCallable implements Callable<String> {

    @Override
    public String call() throws InterruptedException {
        Thread.sleep(100);
        String uid = UUID.randomUUID().toString();
        log.info("UID : {}", uid);
        return uid;
    }
}
