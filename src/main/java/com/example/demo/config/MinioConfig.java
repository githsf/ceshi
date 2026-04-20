package com.example.demo.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://objectstorageapi.hzh.sealos.run")
                .credentials("1ihspkqk", "lqkb8nb75w5s7x4k")
                .build();
    }
} 