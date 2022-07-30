package com.example.demo.socket.udp.chat;

public class TalkTeacher {
    public static void main(String[] args) {
        //开启两个线程
        new Thread(new TalkSend(5555, "127.0.0.1",8888 )).start();
        new Thread(new TalkRecevice(9999,"学生说")).start();
    }
}
