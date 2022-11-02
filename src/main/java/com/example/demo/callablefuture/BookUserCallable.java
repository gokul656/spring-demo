package com.example.demo.callablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class BookUserCallable implements Callable<User> {

    private final UserService userService;

    private final String uid;

    ReentrantLock lock = new ReentrantLock();

    public BookUserCallable(UserService userService, String uid) {
        this.userService = userService;
        this.uid = uid;
    }

    @Override
    public User call() {
        synchronized (userService) {
            return userService.bookUser(uid);
        }
    }
}
