package com.example.java_web_app_architecture.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Person(
  int personId,

  @NotNull @NotEmpty @Size(min=1, max=15)
  String personName,

  @Min(20) @Max(100)
  int age,

  @NotNull @NotEmpty
  String gender
) {}
