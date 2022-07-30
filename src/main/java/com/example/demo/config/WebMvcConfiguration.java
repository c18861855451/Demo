package com.example.demo.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //即使打包运行，只要访问urk路径，也会直接找图片存放的地址
        //url路径直接映射图片存储地址
        String uploadpath="图片存储地址";
        //图片映射地址
        String imagepath="/image/**";
        //localhost:8080/image/图片存储地址/图片名字
        registry.addResourceHandler(imagepath).addResourceLocations("file:"+uploadpath);
    }
}
