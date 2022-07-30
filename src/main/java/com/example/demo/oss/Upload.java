package com.example.demo.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Upload {
    public String uploadFile(MultipartFile file) throws IOException {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。

        String accessKeyId = "LTAI5tMMBV7vZ1Qmqizc8axJ";
        String accessKeySecret = "PCoNzNvIYWsCiDoqh8moyGHtd6Lc8p";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "yuanhuo666";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
//        String objectName = "exampledir/exampleobject.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
//            String content = "Hello OSS";
            //获取文件流
            InputStream inputStream = file.getInputStream();
            //构建日期目录
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datepath = simpleDateFormat.format(new Date());
            //获取文件名
            String originalFilename = file.getOriginalFilename();
            String filename = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(originalFilename);//后缀
            String newname = filename + extension;
            String fileurl = datepath + "/" + newname;
            //文件上传阿里云服务器
            ossClient.putObject(bucketName, fileurl, inputStream);
            //图片地址
            return "https://" + bucketName + "." + endpoint + "/" + fileurl;
        } catch (
            OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (
            ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with OSS, "
                + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

}
