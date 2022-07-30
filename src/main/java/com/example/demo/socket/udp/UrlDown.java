package com.example.demo.socket.udp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlDown {
    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/cao/tao.txt";
        URL url1 = new URL(url);
        //打开连接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("caotao.txt")));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(bytes)) != -1) {
            bufferedOutputStream.write(bytes, 0, len);
        }
        bufferedOutputStream.close();
        bufferedInputStream.close();
        inputStream.close();
        httpURLConnection.disconnect();
    }
}
