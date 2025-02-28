package com.chatop.location_app.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
  Integer id;
  String name;
  String email;
  String created_at;
  String updated_at;
}