package com.example.demo.service;

import com.example.demo.entity.File;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface FileService {
    List<File> all();

    String upload(MultipartFile file) throws IOException;

    void download(int id, HttpServletResponse response) throws IOException;
}
