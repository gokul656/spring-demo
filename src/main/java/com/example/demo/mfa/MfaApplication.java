package com.example.demo.mfa;

import dev.samstevens.totp.code.CodeVerifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class MfaApplication implements CommandLineRunner {

    private final String SECRET = "MRLG****YXVOQDC";
    private final CodeVerifier authenticator;

    public static void main(String[] args) {
        SpringApplication.run(MfaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("IS VERIFIED :: {}", authenticator.isValidCode(SECRET, "043950"));
    }
}
