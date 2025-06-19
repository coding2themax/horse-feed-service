package com.horsefeed.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class Horse {
  private Long id;

  @NotBlank(message = "Horse name is required")
  private String name;

  private String breed;

  @NotNull(message = "Age is required")
  @Positive(message = "Age must be positive")
  private Integer age;

  @NotNull(message = "Weight is required")
  @Positive(message = "Weight must be positive")
  private Double weight;

  private ActivityLevel activityLevel;
  private LocalDateTime createdAt;

  public enum ActivityLevel {
    LOW, MODERATE, HIGH
  }

  // Constructors
  public Horse() {
  }

  public Horse(String name, String breed, Integer age, Double weight, ActivityLevel activityLevel) {
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.weight = weight;
    this.activityLevel = activityLevel;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public ActivityLevel getActivityLevel() {
    return activityLevel;
  }

  public void setActivityLevel(ActivityLevel activityLevel) {
    this.activityLevel = activityLevel;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Horse{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", breed='" + breed + '\'' +
        ", age=" + age +
        ", weight=" + weight +
        ", activityLevel=" + activityLevel +
        ", createdAt=" + createdAt +
        '}';
  }
}
