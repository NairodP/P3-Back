package com.chatop.location_app.controller;

import com.chatop.location_app.service.CloudinaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/files")
@Tag(name = "Images API", description = "Contains operations to upload and download images.")
public class ImgController {

  private final CloudinaryService cloudinaryService;

  public ImgController(CloudinaryService cloudinaryService) {
    this.cloudinaryService = cloudinaryService;
  }

  @Operation(summary = "Upload an image to Cloudinary")
  @PostMapping("/upload")
  public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
    try {
      Map<String, Object> uploadResult = cloudinaryService.uploadImage(file);
      return ResponseEntity.ok(uploadResult);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().body(Map.of("error", "Upload failed"));
    }
  }

  @Operation(summary = "Get an image URL from Cloudinary")
  @GetMapping("/{imageName}")
  public ResponseEntity<String> getImageUrl(
          @Parameter(description = "The public ID of the image", example = "sample")
          @PathVariable String imageName) {

    // Retourne l'URL sans vérifier si l'image existe réellement
    String imageUrl = cloudinaryService.getImageUrl(imageName);
    return ResponseEntity.ok(imageUrl);
  }
}
