package com.example.demo.service.impl;

import com.example.demo.service.FileService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioClient minioClient;

    private static final String BUCKET_NAME = "1ihspkqk-zxyf";
    private static final String ENDPOINT = "http://objectstorageapi.hzh.sealos.run";

    @Override
    public String upload(MultipartFile file) {
        try {
            // 检查存储桶是否存在
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (!found) {
                // 创建存储桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            }

            // 生成文件名
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + 
                originalFilename.substring(originalFilename.lastIndexOf("."));

            // 上传文件
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );

            // 生成文件访问路径
            return ENDPOINT + "/" + BUCKET_NAME + "/" + fileName;
            
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败：" + e.getMessage());
        }
    }
} 