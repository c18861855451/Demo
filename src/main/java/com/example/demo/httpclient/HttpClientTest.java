package com.example.demo.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.io.FileOutputStreamDemo;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.pdfbox.contentstream.operator.text.ShowTextLineAndSpace;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {
    /**
     * 1无参请求
     * 2请求头
     * 3有参的情况需要urlencoder
     */
    @Test
    public void test() throws Exception {
        //可关闭的httpclient客户端，相当于你打开的一个浏览器
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String url = "https://www.baidu.com/";

//        //get请求传参
//        String pass="ab+a |22+a";
//        //做urlencoder，如果是浏览器的话，浏览器自动帮我们做了
//        pass= URLEncoder.encode(pass,StandardCharsets.UTF_8.name());

        //构造httpget请求对象
        HttpGet httpGet = new HttpGet(url);
        //添加请求头，解决httpclient被认为不是真人行为
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62");
        //防盗链，有网站图片爬不下来就加这个,值为该网站
        httpGet.addHeader("Referer", "https://www.baidu.com/");
        //响应
        CloseableHttpResponse response = null;
        try {
            //执行get请求
            response = aDefault.execute(httpGet);
            //代表本次请求的成功，失败的状态
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                //获取响应头
                Header[] allHeaders = response.getAllHeaders();
                for (Header header : allHeaders) {
                    System.out.println(header);
                }
                //获取响应结果
                HttpEntity entity = response.getEntity();
                //获取请求头
                Header contentType = entity.getContentType();
                System.out.println("contentType" + contentType);
                //对HttpEntity操作的工具类
                String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(s);
                //确保流关闭
                EntityUtils.consume(entity);
            } else {
                System.out.println("响应失败" + statusLine.getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aDefault != null) {
                try {

                    aDefault.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * 拉取图片
     */
    @Test
    public void test1() throws Exception {
        //可关闭的httpclient客户端，相当于你打开的一个浏览器
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String url = "https://fastly.jsdelivr.net/npm/@bootcss/www.bootcss.com@0.0.62/dist/img/expo.png";

//        //get请求传参
//        String pass="ab+a |22+a";
//        //做urlencoder，如果是浏览器的话，浏览器自动帮我们做了
//        pass= URLEncoder.encode(pass,StandardCharsets.UTF_8.name());

        //构造httpget请求对象
        HttpGet httpGet = new HttpGet(url);
        //添加请求头，解决httpclient被认为不是真人行为
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62");
        //防盗链，有网站图片爬不下来就加这个,值为该网站
        httpGet.addHeader("Referer", "https://www.baidu.com/");
        //响应
        CloseableHttpResponse response = null;
        try {
            //执行get请求
            response = aDefault.execute(httpGet);

            //获取响应结果
            HttpEntity entity = response.getEntity();
            //获取请求头
            Header contentType = entity.getContentType();
            //image/jpg image/jpeg image/png image/图片的后缀
            String value = contentType.getValue();
            String suffix = ".jpg";
            if (value.contains("jpg") || value.contains("jepg")) {
                suffix = ".jpg";
            } else if (value.contains("bmp") || value.contains("bitmap")) {
                suffix = ".bmp";
            } else if (value.contains("png")) {
                suffix = ".png";
            } else if (value.contains("gif")) {
                suffix = ".gif";
            }
            //对HttpEntity操作的工具类
            byte[] bytes = EntityUtils.toByteArray(entity);
            String path = "E:\\upload\\abc";
            String real = path + suffix;
            FileOutputStream fileOutputStream = new FileOutputStream(real);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            //确保流关闭
            EntityUtils.consume(entity);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aDefault != null) {
                try {

                    aDefault.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * 设置代理
     */
    @Test
    public void test3() {
        //可关闭的httpclient客户端，相当于你打开的一个浏览器
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String url = "https://fastly.jsdelivr.net/npm/@bootcss/www.bootcss.com@0.0.62/dist/img/expo.png";
        HttpGet httpGet = new HttpGet(url);
        String ip = "58.220.95.55";
        int port = 9400;
        HttpHost httpHost = new HttpHost(ip, port);
        //为每一个请求配置，覆盖全局默认请求配置,通过RequestConfig的custom()定制设置一个代理然后.build()构建一个对象
        RequestConfig.Builder builder = RequestConfig.custom().setProxy(httpHost);
        RequestConfig requestConfig = builder.build();
        httpGet.setConfig(requestConfig);
//        //添加请求头，解决httpclient被认为不是真人行为
//        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62");
//        //防盗链，有网站图片爬不下来就加这个,值为该网站
//        httpGet.addHeader("Referer", "https://www.baidu.com/");
        //响应
        CloseableHttpResponse response = null;
        try {
            //执行get请求
            response = aDefault.execute(httpGet);

            //获取响应结果
            HttpEntity entity = response.getEntity();
            //获取请求头
            Header contentType = entity.getContentType();
            //image/jpg image/jpeg image/png image/图片的后缀
            String value = contentType.getValue();
            String suffix = ".jpg";
            if (value.contains("jpg") || value.contains("jepg")) {
                suffix = ".jpg";
            } else if (value.contains("bmp") || value.contains("bitmap")) {
                suffix = ".bmp";
            } else if (value.contains("png")) {
                suffix = ".png";
            } else if (value.contains("gif")) {
                suffix = ".gif";
            }
            //对HttpEntity操作的工具类
            byte[] bytes = EntityUtils.toByteArray(entity);
            String path = "E:\\upload\\aaa";
            String real = path + suffix;
            FileOutputStream fileOutputStream = new FileOutputStream(real);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            //确保流关闭
            EntityUtils.consume(entity);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aDefault != null) {
                try {

                    aDefault.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    /**
     * 设置超时时间
     */
    @Test
    public void test4() throws Exception {
        //可关闭的httpclient客户端，相当于你打开的一个浏览器
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String url = "https://hub.xn--gzu630h.xn--kpry57d/alibaba/";

//        //get请求传参
//        String pass="ab+a |22+a";
//        //做urlencoder，如果是浏览器的话，浏览器自动帮我们做了
//        pass= URLEncoder.encode(pass,StandardCharsets.UTF_8.name());

        String ip = "58.220.95.55";
        int port = 9400;
        HttpHost httpHost = new HttpHost(ip, port);
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(httpHost)
                //毫秒，连接超时完成tcp三次握手的上限
                .setConnectTimeout(5000)
                //读取超时，表示从请求的网址处获得响应的数据时间
                .setSocketTimeout(3000)
                //指从连接池里面获取connection的超时时间
//                .setConnectionRequestTimeout(5000)
                .build();
        //构造httpget请求对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        //添加请求头，解决httpclient被认为不是真人行为
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62");
        //防盗链，有网站图片爬不下来就加这个,值为该网站
        httpGet.addHeader("Referer", "https://www.baidu.com/");
        //响应
        CloseableHttpResponse response = null;
        try {
            //执行get请求
            response = aDefault.execute(httpGet);
            //代表本次请求的成功，失败的状态
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                //获取响应头
                Header[] allHeaders = response.getAllHeaders();
                for (Header header : allHeaders) {
                    System.out.println(header);
                }
                //获取响应结果
                HttpEntity entity = response.getEntity();
                //获取请求头
                Header contentType = entity.getContentType();
                System.out.println("contentType" + contentType);
                //对HttpEntity操作的工具类
                String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(s);
                //确保流关闭
                EntityUtils.consume(entity);
            } else {
                System.out.println("响应失败" + statusLine.getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aDefault != null) {
                try {

                    aDefault.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * --发送application/x-www-from-urlencoded类型的post请求
     */
    @Test
    public void test5() throws Exception {
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String url = "https://hub.xn--gzu630h.xn--kpry57d/alibaba/";

        //构造httpget请求对象
        HttpPost httpPost = new HttpPost(url);
        //NameValuePair,input框里的name和输入的值构成了一个这样的对象
        List<NameValuePair> list = new ArrayList<>();
        //usename是标签名字，1111是值
        list.add(new BasicNameValuePair("usename", "1111"));
        list.add(new BasicNameValuePair("password", "1111"));
        //把参数集合设置到urlEncodedFormEntity
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
        httpPost.setEntity(urlEncodedFormEntity);
        //添加请求头，post 默认是application/x-www-from-urlencoded
        httpPost.addHeader("Content-type", "application/x-www-from-urlencoded;charset=utf-8");
        //响应
        CloseableHttpResponse response = null;
        try {
            //执行get请求
            response = aDefault.execute(httpPost);
            //代表本次请求的成功，失败的状态
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                //获取响应头
                Header[] allHeaders = response.getAllHeaders();
                for (Header header : allHeaders) {
                    System.out.println(header);
                }
                //获取响应结果
                HttpEntity entity = response.getEntity();
                //获取请求头
                Header contentType = entity.getContentType();
                System.out.println("contentType" + contentType);
                //对HttpEntity操作的工具类
                String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(s);
                //确保流关闭
                EntityUtils.consume(entity);
            } else {
                System.out.println("响应失败" + statusLine.getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aDefault != null) {
                try {

                    aDefault.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * 发送application/json类型的post请求
     */
    @Test
    public void test6() throws Exception {
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String url = "https://hub.xn--gzu630h.xn--kpry57d/alibaba/";

        //构造httpget请求对象
        HttpPost httpPost = new HttpPost(url);
        String json = "aaa";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", json);
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), Consts.UTF_8);
        //给entity设置内容类型
        stringEntity.setContentType(new BasicHeader("Content-type", "application/json; charset=utf-8"));
        //设置entity编码
        stringEntity.setContentEncoding(Consts.UTF_8.name());
        httpPost.setEntity(stringEntity);
        //添加请求头，post 默认是application/x-www-from-urlencoded
        httpPost.addHeader("Content-type", "application/x-www-from-urlencoded");
        //响应
        CloseableHttpResponse response = null;
        try {
            //执行get请求
            response = aDefault.execute(httpPost);
            //代表本次请求的成功，失败的状态
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                //获取响应头
                Header[] allHeaders = response.getAllHeaders();
                for (Header header : allHeaders) {
                    System.out.println(header);
                }
                //获取响应结果
                HttpEntity entity = response.getEntity();
                //获取请求头
                Header contentType = entity.getContentType();
                System.out.println("contentType" + contentType);
                //对HttpEntity操作的工具类
                String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(s);
                //确保流关闭
                EntityUtils.consume(entity);
            } else {
                System.out.println("响应失败" + statusLine.getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aDefault != null) {
                try {

                    aDefault.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * 发生multipart/from-data类型上传文件的post请求
     */
    @Test
    public void test7() throws Exception {
        CloseableHttpClient aDefault = HttpClients.createDefault();
        String url = "https://hub.xn--gzu630h.xn--kpry57d/alibaba/";

        //构造httpget请求对象
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        //设置编码
        multipartEntityBuilder.setCharset(Consts.UTF_8);
        multipartEntityBuilder.setContentType(ContentType.create("multipart/form-data", Consts.UTF_8));
        //设置浏览器模式
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        FileBody fileBody = new FileBody(new File("E:\\upload\\abc.png"));

        //对于普通表单字段如果含有中文的话不要通过addtextbody，否则乱码,text指的是输入的值
        StringBody stringBody = new StringBody("123", ContentType.create("text/plain", Consts.UTF_8));

        HttpEntity httpEntity = multipartEntityBuilder
                //上传文件
                .addPart("filename", fileBody)
                //上传文件，filename,标签名字
                .addBinaryBody("filename", new File("E:\\upload\\123.png"))
                //加参数
//                .addTextBody("usename", "123")
                .addPart("usename", stringBody)
                .build();

        //响应
        CloseableHttpResponse response = null;
        try {
            //执行get请求
            response = aDefault.execute(httpPost);
            //代表本次请求的成功，失败的状态
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                //获取响应头
                Header[] allHeaders = response.getAllHeaders();
                for (Header header : allHeaders) {
                    System.out.println(header);
                }
                //获取响应结果
                HttpEntity entity = response.getEntity();
                //获取请求头
                Header contentType = entity.getContentType();
                System.out.println("contentType" + contentType);
                //对HttpEntity操作的工具类
                String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(s);
                //确保流关闭
                EntityUtils.consume(entity);
            } else {
                System.out.println("响应失败" + statusLine.getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aDefault != null) {
                try {

                    aDefault.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * 测试https，配置httpclient绕过https安全认证
     */
    @Test
    public void test8() throws Exception {
        Registry<ConnectionSocketFactory> build = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                //自定义一个信任https方法
                .register("https", trusthttp())
                .build();
        //创建一个ConnectionManager对象
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager(build);
        //定制CloseableHttpClient对象
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(pool);

//配置好httpclient之后，通过builder方法来构建CloseableHttpClient对象
        CloseableHttpClient aDefault = httpClientBuilder.build();
        String url = "https://hub.xn--gzu630h.xn--kpry57d/alibaba/";

        //构造httpget请求对象
        HttpGet httpGet = new HttpGet(url);
        //响应
        CloseableHttpResponse response = null;
        try {
            //执行get请求
            response = aDefault.execute(httpGet);
            //代表本次请求的成功，失败的状态
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                //获取响应头
                Header[] allHeaders = response.getAllHeaders();
                for (Header header : allHeaders) {
                    System.out.println(header);
                }
                //获取响应结果
                HttpEntity entity = response.getEntity();
                //获取请求头
                Header contentType = entity.getContentType();
                System.out.println("contentType" + contentType);
                //对HttpEntity操作的工具类
                String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(s);
                //确保流关闭
                EntityUtils.consume(entity);
            } else {
                System.out.println("响应失败" + statusLine.getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aDefault != null) {
                try {

                    aDefault.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * 创建支持安全协议的工厂
     * @return
     * @throws Exception
     */
    private ConnectionSocketFactory trusthttp() throws Exception {
        //构建sslSSLContext对象
        SSLContextBuilder sslContextBuilder=new SSLContextBuilder();
        //加载信任证书
        sslContextBuilder.loadTrustMaterial(null, new TrustStrategy() {
            //信任策略
            //判断是否信任url直接返回true
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
//                return flase;
            }
        });
        SSLContext sslContext = sslContextBuilder.build();
        SSLConnectionSocketFactory sslConnectionSocketFactory=new SSLConnectionSocketFactory(sslContext,new String[]{
                "SSLv2Hello","SSLv3","TLSv1","TLSv1.1","TLSv1.2"
        },null, NoopHostnameVerifier.INSTANCE);
        return sslConnectionSocketFactory;
    }
}
