package com.horsefeed.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FeedingSchedule {
  private Long id;

  @NotNull(message = "Horse ID is required")
  private Long horseId;

  @NotNull(message = "Feed type ID is required")
  private Long feedTypeId;

  @NotNull(message = "Quantity is required")
  @Positive(message = "Quantity must be positive")
  private Double quantityKg;

  @NotNull(message = "Feeding time is required")
  private LocalTime feedingTime;

  private Frequency frequency;

  @NotNull(message = "Start date is required")
  private LocalDate startDate;

  private LocalDate endDate;
  private String notes;
  private LocalDateTime createdAt;

  // Additional fields for display (not stored in DB)
  private String horseName;
  private String feedTypeName;

  public enum Frequency {
    DAILY, WEEKLY, MONTHLY
  }

  // Constructors
  public FeedingSchedule() {
  }

  public FeedingSchedule(Long horseId, Long feedTypeId, Double quantityKg,
      LocalTime feedingTime, Frequency frequency, LocalDate startDate) {
    this.horseId = horseId;
    this.feedTypeId = feedTypeId;
    this.quantityKg = quantityKg;
    this.feedingTime = feedingTime;
    this.frequency = frequency;
    this.startDate = startDate;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getHorseId() {
    return horseId;
  }

  public void setHorseId(Long horseId) {
    this.horseId = horseId;
  }

  public Long getFeedTypeId() {
    return feedTypeId;
  }

  public void setFeedTypeId(Long feedTypeId) {
    this.feedTypeId = feedTypeId;
  }

  public Double getQuantityKg() {
    return quantityKg;
  }

  public void setQuantityKg(Double quantityKg) {
    this.quantityKg = quantityKg;
  }

  public LocalTime getFeedingTime() {
    return feedingTime;
  }

  public void setFeedingTime(LocalTime feedingTime) {
    this.feedingTime = feedingTime;
  }

  public Frequency getFrequency() {
    return frequency;
  }

  public void setFrequency(Frequency frequency) {
    this.frequency = frequency;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getHorseName() {
    return horseName;
  }

  public void setHorseName(String horseName) {
    this.horseName = horseName;
  }

  public String getFeedTypeName() {
    return feedTypeName;
  }

  public void setFeedTypeName(String feedTypeName) {
    this.feedTypeName = feedTypeName;
  }

  @Override
  public String toString() {
    return "FeedingSchedule{" +
        "id=" + id +
        ", horseId=" + horseId +
        ", feedTypeId=" + feedTypeId +
        ", quantityKg=" + quantityKg +
        ", feedingTime=" + feedingTime +
        ", frequency=" + frequency +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", notes='" + notes + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }
}
