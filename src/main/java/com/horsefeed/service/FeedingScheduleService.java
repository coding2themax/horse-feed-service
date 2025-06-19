package com.horsefeed.service;

import com.horsefeed.dao.FeedingScheduleDao;
import com.horsefeed.model.FeedingSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedingScheduleService {

  @Autowired
  private FeedingScheduleDao feedingScheduleDao;

  @Autowired
  private HorseService horseService;

  @Autowired
  private FeedTypeService feedTypeService;

  public List<FeedingSchedule> getAllSchedules() {
    return feedingScheduleDao.findAll();
  }

  public FeedingSchedule getScheduleById(Long id) {
    return feedingScheduleDao.findById(id);
  }

  public List<FeedingSchedule> getSchedulesByHorseId(Long horseId) {
    return feedingScheduleDao.findByHorseId(horseId);
  }

  public FeedingSchedule saveSchedule(FeedingSchedule schedule) {
    validateSchedule(schedule);
    return feedingScheduleDao.save(schedule);
  }

  public void deleteSchedule(Long id) {
    FeedingSchedule schedule = feedingScheduleDao.findById(id);
    if (schedule == null) {
      throw new IllegalArgumentException("Feeding schedule not found with id: " + id);
    }
    feedingScheduleDao.deleteById(id);
  }

  public long getScheduleCount() {
    return feedingScheduleDao.count();
  }

  private void validateSchedule(FeedingSchedule schedule) {
    if (schedule.getHorseId() == null) {
      throw new IllegalArgumentException("Horse ID is required");
    }
    if (schedule.getFeedTypeId() == null) {
      throw new IllegalArgumentException("Feed type ID is required");
    }
    if (schedule.getQuantityKg() == null || schedule.getQuantityKg() <= 0) {
      throw new IllegalArgumentException("Quantity must be positive");
    }
    if (schedule.getFeedingTime() == null) {
      throw new IllegalArgumentException("Feeding time is required");
    }
    if (schedule.getStartDate() == null) {
      throw new IllegalArgumentException("Start date is required");
    }

    // Validate that horse and feed type exist
    if (!horseService.horseExists(schedule.getHorseId())) {
      throw new IllegalArgumentException("Horse not found with id: " + schedule.getHorseId());
    }
    if (!feedTypeService.feedTypeExists(schedule.getFeedTypeId())) {
      throw new IllegalArgumentException("Feed type not found with id: " + schedule.getFeedTypeId());
    }
  }

  public boolean scheduleExists(Long id) {
    return feedingScheduleDao.findById(id) != null;
  }
}
