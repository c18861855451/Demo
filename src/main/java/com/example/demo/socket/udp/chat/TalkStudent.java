package com.example.demo.socket.udp.chat;

public class TalkStudent {
    public static void main(String[] args) {
        //开启两个线程
         new Thread(new TalkSend(8055, "127.0.0.1",9999 )).start();
         new Thread(new TalkRecevice(8888,"老师说")).start();
    }
}
