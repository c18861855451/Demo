package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//全局日志对象
public class BaseLog {
    public Class clazz;
    public static Logger log;

    public BaseLog() {
        clazz = this.getClass();
        log = LoggerFactory.getLogger(clazz);
    }
}
