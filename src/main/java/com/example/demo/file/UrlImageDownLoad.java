package com.example.demo.file;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlImageDownLoad {
    public static void main(String[] args) {
        URL url;
//统一资源定位符
        {
            try {
                //图片链接地址
                url = new URL("https://t7.baidu.com/it/u=4072290848,1500811730&fm=193&f=GIF");
                //强转httpurl，图片是网上找的
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //获取输入流
                InputStream inputStream = httpURLConnection.getInputStream();
                //InputStream是抽象类，不能创建实例对象，BufferedInputStream是它子类
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                //创建输出流，定义一个输出地址和格式
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("D:\\xiaogou.jpg")));
                byte[] bytes = new byte[1024];
                int len = -1;
                while ((len = bufferedInputStream.read(bytes)) != -1) {
                    bufferedOutputStream.write(bytes, 0, len);
                    bufferedOutputStream.flush();
                }
                bufferedInputStream.close();
                bufferedOutputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
