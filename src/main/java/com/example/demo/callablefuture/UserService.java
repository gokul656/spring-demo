package com.example.demo.callablefuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    ReentrantLock lock = new ReentrantLock();


    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(String uid) {
        return userRepository.findByUid(uid);
    }

    public User bookUser(String uid) {
        Optional<User> user = userRepository.findByUid(uid);
        if (user.isPresent() && !user.get().isBooked()) {
            user.get().setBooked(true);
            User savedUser = userRepository.save(user.get());
            return savedUser;
        }
        return null;
    }
}
