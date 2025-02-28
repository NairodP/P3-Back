package com.chatop.location_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import com.chatop.location_app.model.Rental;

@Data
@Builder
@Schema(description = "Rental Request Data Transfer Object", name = "RentalRequest")
public class RentalRequestDto {
  @Schema(description = "Rental name", example = "Nice house")
  @NotNull(message = "Name cannot be null")
  @NotEmpty(message = "Name cannot be empty")
  @Size(max = Rental.NAME_MAX_SIZE)
  private String name;

  @Schema(description = "Rental surface in square meters", example = "100")
  @NotNull(message = "Surface cannot be null")
  private Integer surface;

  @Schema(description = "Rental price per month", example = "1000.0")
  @NotNull(message = "Price cannot be null")
  private Float price;

  @Schema(description = "Rental picture (optional)")
  private MultipartFile picture;

  @Schema(description = "Rental description", example = "This is a nice house")
  @NotNull(message = "Description cannot be null")
  @NotEmpty(message = "Description cannot be empty")
  @Size(max = Rental.DESC_MAX_SIZE)
  private String description;
}