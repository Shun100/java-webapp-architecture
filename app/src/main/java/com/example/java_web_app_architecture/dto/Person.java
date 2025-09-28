package com.example.java_web_app_architecture.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {
  private int personId;

  @NotNull @NotEmpty @Size(min=1, max=15)
  private String personName;

  @NotNull @Min(20) @Max(100)
  private int age;

  @NotNull @NotEmpty
  private String gender;

  public Person() {};
  public Person(int personId, String personName, int age, String gender) {
    this.personId = personId;
    this.personName = personName;
    this.age = age;
    this.gender = gender;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }
}
