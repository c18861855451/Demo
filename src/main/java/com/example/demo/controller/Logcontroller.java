package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
@RequestMapping("/log")
public class Logcontroller extends BaseLog{
//    private static final Logger log = LoggerFactory.getLogger(Logcontroller.class);

    @GetMapping("/logg")
    public String get() {
        log.info("1");
        return "1";
    }
}
