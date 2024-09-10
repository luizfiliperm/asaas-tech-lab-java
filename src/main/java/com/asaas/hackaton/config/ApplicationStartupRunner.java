package com.asaas.hackaton.config;

import com.asaas.hackaton.service.SetupService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements ApplicationRunner {

    private final SetupService setupService;

    public ApplicationStartupRunner(SetupService setupService) {
        this.setupService = setupService;
    }

    @Override
    public void run(ApplicationArguments args) {
        setupService.setup();
    }
}