package com.test.terminal_service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JsonManager {
    private final JsonService jsonService;

    public void handleCommand(String command, String filePath) throws Exception {
        switch (command) {
            case "print":
                jsonService.printJsonFromFile(filePath);
                break;
            case "findMax":
                jsonService.findMaxFromJsonFile(filePath);
                break;
            default:
                System.out.println("Invalid command");
        }
    }
}
