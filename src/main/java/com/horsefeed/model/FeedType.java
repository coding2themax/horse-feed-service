package com.horsefeed.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class FeedType {
  private Long id;

  @NotBlank(message = "Feed name is required")
  private String name;

  private String brand;

  @NotNull(message = "Calories per kg is required")
  @Positive(message = "Calories must be positive")
  private Double caloriesPerKg;

  @NotNull(message = "Protein percentage is required")
  @Positive(message = "Protein percentage must be positive")
  private Double proteinPercentage;

  @NotNull(message = "Fiber percentage is required")
  @Positive(message = "Fiber percentage must be positive")
  private Double fiberPercentage;

  @NotNull(message = "Fat percentage is required")
  @Positive(message = "Fat percentage must be positive")
  private Double fatPercentage;

  private String description;
  private LocalDateTime createdAt;

  // Constructors
  public FeedType() {
  }

  public FeedType(String name, String brand, Double caloriesPerKg, Double proteinPercentage,
      Double fiberPercentage, Double fatPercentage, String description) {
    this.name = name;
    this.brand = brand;
    this.caloriesPerKg = caloriesPerKg;
    this.proteinPercentage = proteinPercentage;
    this.fiberPercentage = fiberPercentage;
    this.fatPercentage = fatPercentage;
    this.description = description;
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

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Double getCaloriesPerKg() {
    return caloriesPerKg;
  }

  public void setCaloriesPerKg(Double caloriesPerKg) {
    this.caloriesPerKg = caloriesPerKg;
  }

  public Double getProteinPercentage() {
    return proteinPercentage;
  }

  public void setProteinPercentage(Double proteinPercentage) {
    this.proteinPercentage = proteinPercentage;
  }

  public Double getFiberPercentage() {
    return fiberPercentage;
  }

  public void setFiberPercentage(Double fiberPercentage) {
    this.fiberPercentage = fiberPercentage;
  }

  public Double getFatPercentage() {
    return fatPercentage;
  }

  public void setFatPercentage(Double fatPercentage) {
    this.fatPercentage = fatPercentage;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "FeedType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", brand='" + brand + '\'' +
        ", caloriesPerKg=" + caloriesPerKg +
        ", proteinPercentage=" + proteinPercentage +
        ", fiberPercentage=" + fiberPercentage +
        ", fatPercentage=" + fatPercentage +
        ", description='" + description + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }
}
