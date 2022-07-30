package com.example.demo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PageException {
    @ExceptionHandler(value = Exception.class)
    public  String getException(Exception ex){
    ex.printStackTrace();
    return "有问题";
    }
}
