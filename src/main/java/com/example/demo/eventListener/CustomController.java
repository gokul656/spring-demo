package com.example.demo.eventListener;

import com.example.demo.callablefuture.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class CustomController {
    @Autowired
    CustomSpringEventPublisher eventPublisher;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/get")
    public ResponseEntity<?> some(){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
