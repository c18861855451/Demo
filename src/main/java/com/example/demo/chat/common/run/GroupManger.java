package com.example.demo.chat.common.run;

import com.example.demo.chat.common.bo.Client;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GroupManger
 * @Author CT
 * @Date 2022/5/31 15:29
 */
public class GroupManger {
    public static final String DEFAULT_GROUP_ID = "ABC";
    //已连接客户端
    /**
     * 群成员
     */
    private static final Map<String, List<AsynchronousSocketChannel>> groups = new HashMap<>();

    /**
     * 添加group新成员
     * @param group 群组编号
     * @param asc
     */
    public static void put(String group, AsynchronousSocketChannel asc) {
        List<AsynchronousSocketChannel> asynchronousSocketChannels = groups.get(group);//获取群成员
        if (asynchronousSocketChannels == null) {
            asynchronousSocketChannels = new ArrayList<>();
            groups.put(group, asynchronousSocketChannels);
        } else {
            asynchronousSocketChannels.add(asc);
            groups.put(group, asynchronousSocketChannels);
        }
    }

    public static AsynchronousSocketChannel[] get(String group) {
        List<AsynchronousSocketChannel> asynchronousSocketChannels = groups.get(group);
        return asynchronousSocketChannels.toArray(new AsynchronousSocketChannel[0]);
    }
}
