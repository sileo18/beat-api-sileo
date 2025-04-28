package com.example.beat_api_sileo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
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
        try {

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            // Upload file to S3
            amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);

            // Return the file URL
            return amazonS3.getUrl(bucketName, file.getOriginalFilename()).toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

}
