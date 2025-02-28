package com.chatop.location_app.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImgStorageService {

  @Value("${file.upload-dir}")
  private String uploadDir;

  public String storeImg(MultipartFile file) throws IOException {

    String imgName = UUID.randomUUID().toString() + ".png";

    Path filePath = Paths.get(uploadDir, imgName);

    Files.createDirectories(filePath.getParent());
    Files.copy(file.getInputStream(), filePath);

    return "/api/" + uploadDir.toString() + imgName;
  }

  public byte[] getFile(String imgName) throws IOException {
    Path filePath = Paths.get(uploadDir, imgName);
    return Files.readAllBytes(filePath);
  }
}
