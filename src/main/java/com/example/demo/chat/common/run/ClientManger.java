package com.example.demo.chat.common.run;

import com.example.demo.chat.common.bo.Client;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientManger
 * @Author CT
 * @Date 2022/5/31 10:49
 */
public class ClientManger {
    //已连接客户端
    private static final Map<String, Client> map = new HashMap<>();

    /**
     * 添加新的客户端
     *
     * @param asc
     * @return
     */
    public static Client put(AsynchronousSocketChannel asc) {
        Client client = new Client();
        client.setAsc(asc);
        map.put(client.getAlias(), client);
        return client;
    }

    /**
     * 获取指定别名的客户端
     *
     * @param aliases
     * @return
     */
    public static AsynchronousSocketChannel[] get(String... aliases) {
        AsynchronousSocketChannel[] asynchronousSocketChannels = new AsynchronousSocketChannel[aliases.length];
        for (int i = 0; i < aliases.length; i++) {
            asynchronousSocketChannels[i] = map.get(aliases[i]).getAsc();
        }
        return asynchronousSocketChannels;
    }
}
