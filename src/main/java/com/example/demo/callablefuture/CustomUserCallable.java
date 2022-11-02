package com.example.demo.callablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.Callable;

@Slf4j
public class CustomUserCallable implements Callable<Optional<User>> {

    private final String uid;

    private final UserService userService;

    private final boolean isSave;

    public CustomUserCallable(UserService userService, String uid, boolean isSave) {
        this.userService = userService;
        this.uid = uid;
        this.isSave = isSave;
    }

    @Override
    public Optional<User> call() {
        synchronized (userService) {
            if (isSave) {
                log.info("SAVING   : {}", uid);
                Optional<User> user = Optional.of(userService.addUser(new User(Long.valueOf(uid), uid, false)));
                log.info("SAVED    : {}", user.get().getUid());
                return user;
            }

            Optional<User> user = userService.getUser(uid);
            user.ifPresentOrElse(it -> log.info("PRESENT : {}", it.getUid()), () -> log.info("NOT PRESENT : {}", uid));
            return user;
        }
    }
}
