package com.test.terminal_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@AllArgsConstructor
public class JsonController {
    private  final JsonService jsonService;

    @GetMapping("/print")
    public void printJson(@RequestBody String json)
    {
        try {
            log.info("printJson {}" , json);
            jsonService.printJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/findmax")
    public void findMax(@RequestBody String json)
    {
        try {
            log.info("findMaxValue {} " , json);
            jsonService.findMaxValue(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
