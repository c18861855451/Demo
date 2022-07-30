package com.example.demo.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MutilClientDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 6666);
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            PrintStream ps = new PrintStream(bufferedOutputStream);
            System.out.println("请输入。。。");
            String s1 = scanner.nextLine();
            ps.println(s1);
            ps.flush();

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String s = br.readLine();
            System.out.println("服务器说"+s);
            ps.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

