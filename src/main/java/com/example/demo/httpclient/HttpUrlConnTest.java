package com.example.demo.httpclient;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class HttpUrlConnTest {
    @Test
    public void test() throws Exception {
        String url="https://www.baidu.com/";
        //创建url对象
        URL url1=new URL(url);
        //打开一个连接
        URLConnection urlConnection = url1.openConnection();
        //使用httpurl对象
        HttpURLConnection httpURLConnection=(HttpURLConnection) urlConnection;
        //设置请求方式
        httpURLConnection.setRequestMethod("GET");
        //设置请求头
        httpURLConnection.setRequestProperty("Accept-Charest","utf-8");
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
