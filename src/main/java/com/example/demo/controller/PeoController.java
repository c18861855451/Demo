package com.example.demo.controller;


import com.example.demo.entity.Peo;
import com.example.demo.service.PeoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PeoController {
    @Autowired
    PeoService peoService;
    @RequestMapping("/peo")
    public Peo query()  {
        return peoService.selectById();
    }
}
