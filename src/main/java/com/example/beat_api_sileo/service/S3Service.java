package com.example.beat_api_sileo.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public String uploadFile(MultipartFile file) {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        try {
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), null);
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar arquivo ao S3", e);
        }
    }

}
