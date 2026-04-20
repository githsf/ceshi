package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问路径
     */
    String upload(MultipartFile file);
} 