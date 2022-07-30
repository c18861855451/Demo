package com.example.demo.socket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutilServerDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);//线程池
        {
            try {
                ServerSocket serverSocket = new ServerSocket(6666);
                System.out.println("服务已启动，等待连接。。。。");
                while (true) {
                    Socket accept = serverSocket.accept();
                    System.out.println(accept.getInetAddress().getHostAddress());//ip地址
                    executorService.execute(new UserThread(accept));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
 class UserThread implements Runnable {
    private Socket s;

    public UserThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream printStream = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
            String s = bufferedReader.readLine();
            System.out.println(s);
            printStream.println("echo" + s);
            printStream.flush();
            bufferedReader.close();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
