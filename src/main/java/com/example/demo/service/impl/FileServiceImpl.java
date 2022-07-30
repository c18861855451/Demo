package com.example.demo.service.impl;

import com.example.demo.dao.FileDao;
import com.example.demo.entity.File;
import com.example.demo.service.FileService;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Value("${filepath}")
    private String filepath;
    @Autowired
    FileDao fileDao;

    @Override
    public List<File> all() {
        return fileDao.all();
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();//原始名字
        String extension = FilenameUtils.getExtension(originalFilename);//后缀
        Date date = new Date();
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date) + "." + extension;//新名字
        long size = file.getSize();//文件大小
        String contentType = file.getContentType();//文件类型
        //如果只是上传图片的
        if (!contentType.equals("image/png")|!contentType.equals("image/jpg")|!contentType.equals("image/gif")) {
            return "文件类型不对";
        }
        String path = this.filepath;//配置定义地址
        String path1 = new SimpleDateFormat("yyyy-MM-dd").format(date) + "/" + newFileName;//文件的相对路径
        String dir = path + new SimpleDateFormat("yyyy-MM-dd").format(date);//文件上级路径
        java.io.File newFile = new java.io.File(dir);
        if (!newFile.getParentFile().exists()) { //判断文件父目录是否存在
            newFile.getParentFile().mkdir();
        }
        if (!newFile.isDirectory()) { // 如果文件夹不存在就新建
            newFile.mkdirs();
        }
        file.transferTo(new java.io.File(dir, newFileName));//父级路径，文件名称=文件具体地址
        File file1 = new File();
        file1.setCreateTime(date);
        file1.setExt(extension);
        file1.setNewName(newFileName);
        file1.setOldName(originalFilename);
        file1.setPath(path1);
        file1.setSize(String.valueOf(size));
        file1.setType(contentType);
        int save = fileDao.save(file1);
        if (save > 0) {
            return "ok";
        } else {
            return "flase";
        }
    }

    @Override
    public void download(int id, HttpServletResponse response) throws IOException {
        File file = fileDao.selectById(id);
        String realpath = this.filepath + file.getPath();
        FileInputStream is = new FileInputStream(new java.io.File(realpath));
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getOldName(), "utf8"));
        //  response.setHeader("Content-Disposition", "inline;fileName=" + URLEncoder.encode(file.getOldName(),"utf8"));//在线预览
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }
}
