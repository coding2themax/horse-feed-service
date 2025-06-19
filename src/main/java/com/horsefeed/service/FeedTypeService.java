package com.horsefeed.service;

import com.horsefeed.dao.FeedTypeDao;
import com.horsefeed.model.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedTypeService {

  @Autowired
  private FeedTypeDao feedTypeDao;

  public List<FeedType> getAllFeedTypes() {
    return feedTypeDao.findAll();
  }

  public FeedType getFeedTypeById(Long id) {
    return feedTypeDao.findById(id);
  }

  public FeedType saveFeedType(FeedType feedType) {
    validateFeedType(feedType);
    return feedTypeDao.save(feedType);
  }

  public void deleteFeedType(Long id) {
    FeedType feedType = feedTypeDao.findById(id);
    if (feedType == null) {
      throw new IllegalArgumentException("Feed type not found with id: " + id);
    }
    feedTypeDao.deleteById(id);
  }

  public List<FeedType> getFeedTypesByBrand(String brand) {
    return feedTypeDao.findByBrand(brand);
  }

  public long getFeedTypeCount() {
    return feedTypeDao.count();
  }

  private void validateFeedType(FeedType feedType) {
    if (feedType.getName() == null || feedType.getName().trim().isEmpty()) {
      throw new IllegalArgumentException("Feed type name is required");
    }
    if (feedType.getCaloriesPerKg() == null || feedType.getCaloriesPerKg() <= 0) {
      throw new IllegalArgumentException("Calories per kg must be positive");
    }
    if (feedType.getProteinPercentage() == null || feedType.getProteinPercentage() < 0) {
      throw new IllegalArgumentException("Protein percentage must be non-negative");
    }
    if (feedType.getFiberPercentage() == null || feedType.getFiberPercentage() < 0) {
      throw new IllegalArgumentException("Fiber percentage must be non-negative");
    }
    if (feedType.getFatPercentage() == null || feedType.getFatPercentage() < 0) {
      throw new IllegalArgumentException("Fat percentage must be non-negative");
    }
  }

  public boolean feedTypeExists(Long id) {
    return feedTypeDao.findById(id) != null;
  }
}
