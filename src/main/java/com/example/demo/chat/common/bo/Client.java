package com.example.demo.chat.common.bo;

import lombok.Data;
import lombok.ToString;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.UUID;

/**
 * @ClassName Client
 * @Author CT
 * @Date 2022/5/31 11:03
 */
@Data
@ToString
public class Client {
    private String id = UUID.randomUUID().toString();
    private String alias = id;
    private AsynchronousSocketChannel asc;

}
