package com.example.demo.callablefuture;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
@SpringBootApplication
public class CallableApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CallableApplication.class, args);
    }

    @Autowired
    UserService userService;

    public long number;

    int count = 20;

    @Override
    @SneakyThrows
    public void run(String... args) {

//        doSomething();
        long uid = (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);

        User user = new User(uid, Long.toString(uid), false);
        userService.addUser(user);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<User>> callableList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            callableList.add(new BookUserCallable(userService, Long.toString(uid)));
        }

        List<Future<User>> futureList = executorService.invokeAll(callableList);
        number = futureList.stream().filter(Future::isDone).filter(
                userFuture -> {
                    try {
                        return userFuture.get() != null;
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).count();

        log.info("BOOKING COUNT : {}", number);
    }

    @SneakyThrows
    public void doSomething() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Optional<User>>> callableList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            long uid = (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            callableList.add(new CustomUserCallable(userService, Long.toString(uid), i % 2 == 0));
            callableList.add(new CustomUserCallable(userService, Long.toString(uid), i % 2 != 0));
        }

        List<Future<Optional<User>>> futureList = executorService.invokeAll(callableList);
        number = futureList.stream().filter(Future::isDone).map(optionalFuture -> {
            try {
                return optionalFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).filter(Optional::isPresent).count();

        log.info("SUCCESS : {}", number);
    }

    public List<Callable<Optional<User>>> populateThreads(Callable<Optional<User>> callable, int count) {
        List<Callable<Optional<User>>> callables = new ArrayList<>();
        for (int i = 0; i < count; i++)
            callables.add(callable);
        return callables;
    }

    public List<Callable<Integer>> incrementNumber(int count) {
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < count; i++)
            callableList.add(() -> {
                synchronized (this) {
                    log.info("NUMBER : {}", number);
                    return Math.toIntExact(number += 1);
                }
            });

        return callableList;
    }


    /*@Autowired
    UserRepository userRepository;

    @Override
    @SneakyThrows
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void run(String... args) {

        Long uid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        User user = userRepository.save(new User(uid, UUID.randomUUID().toString(), false));

        ExecutorService threadPool = Executors.newFixedThreadPool(10); // Create a threadpool with 10 threads
        List<Callable<Optional<User>>> orderRequests = getOrderRequests(user.getId(), 20); // Create 20 tasks for placing 20 orders
        List<Future<Optional<User>>> results = threadPool.invokeAll(orderRequests); // Place those 20 orders simulteanously

        long successCount = results.stream().filter(Future::isDone).map(result -> {
            log.info("EXECUTING");
            try {
                return result.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }).filter(Optional::isPresent).count();

        log.info("SUCCESS : {}", successCount);
    }

    public List<Callable<Optional<User>>> getOrderRequests(Long uid, int count) {
        List<Callable<Optional<User>>> userReq = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            userReq.add(() -> userRepository.findById(uid));
        }
        return userReq;
    }*/
}
