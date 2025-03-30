package com.example.beat_api_sileo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.beat_api_sileo.domain.User.UserRegisterDTO;
import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public User createUser(UserRegisterDTO data) {

        String imgUrl = "";

        if(data.getProfilePictureUrl() != null) {
            imgUrl = this.uploadImg(data.getProfilePictureUrl());
        }

        User user = new User();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setSurname(data.getSurname());
        user.setDescription(data.getDescription());
        user.setBirthDate(new Date(data.getBirthDate()));
        user.setProfilePictureUrl(imgUrl);

        userRepository.save(user);

        return user;

    }

    private String uploadImg(MultipartFile multipartFile) {

        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultiPartToFile(multipartFile);
            amazonS3.putObject(bucketName,fileName, file);
            file.delete();
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Error uploading image");
            e.printStackTrace();
            return null;
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
