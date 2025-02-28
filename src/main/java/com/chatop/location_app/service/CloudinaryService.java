package com.chatop.location_app.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

  private final Cloudinary cloudinary;

  public CloudinaryService(
          @Value("${CLOUD_NAME}") String cloudName,
          @Value("${CLOUD_CLE_API}") String apiKey,
          @Value("${CLOUD_SECRET}") String apiSecret) {

    this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
    ));
  }

  public Map uploadImage(MultipartFile file) throws IOException {
    return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
  }

  public String getImageUrl(String imageName) {
    return "https://res.cloudinary.com/" + cloudinary.config.cloudName + "/image/upload/v1/" + imageName;
  }
}
