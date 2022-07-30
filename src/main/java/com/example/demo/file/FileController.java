package com.example.demo.file;

import com.example.demo.entity.File;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class FileController {
    @Autowired
    FileService fileService;
    @RequestMapping("/data")
    public List<File> query() throws Exception {
        if(true){
            throw  new Exception();
        }
       return fileService.all();
    }

    @RequestMapping("/upload")
    /**
     * MultipartFile 这个对象是审springmvc提供的文件上传的接收的类
     * 他是底层自动会去和HttpServletRequest request中的request.getInputStream()融合
     * 从而达到文件上传
     * 原理就是request.getInputStream()
     */
    public String upload(MultipartFile file) throws IOException {
       return fileService.upload(file);
    }

    @RequestMapping("/download")
    public void download(int  id, HttpServletResponse response) throws IOException {
        fileService.download(id,response);
    }
}


