package com.example.demo.mfa.config;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.time.NtpTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AuthenticatorConfig {

    @Bean
    public CodeVerifier codeVerifier() throws UnknownHostException {
        CodeGenerator codeGenerator = new DefaultCodeGenerator(HashingAlgorithm.SHA1);
        TimeProvider timeProvider = new NtpTimeProvider("pool.ntp.org");

        return new DefaultCodeVerifier(codeGenerator, timeProvider);
    }
}