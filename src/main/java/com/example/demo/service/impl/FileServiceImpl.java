package com.example.demo.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.demo.service.FileService;
import com.example.demo.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String uploadurl = null;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();

            String fileName = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().replaceAll("-","");

            String datePath = new DateTime().toString("yyyy/MM/dd");

            fileName = "note_image"+"/"+datePath+"/"+uuid+fileName;

            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            uploadurl = "https://"+bucketName+"."+endpoint+"/"+fileName;

            return uploadurl;

        }catch (IOException e) {
            return uploadurl;
        }
    }
}
