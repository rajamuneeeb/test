package com.test.terminal_service;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AppRunner implements CommandLineRunner {
    private final JsonManager jsonManager;

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java -jar your-app.jar <command> <json>");
            return;
        }

        String command = args[0];
        String json = args[1];

        jsonManager.handleCommand(command, json);
    }

}

