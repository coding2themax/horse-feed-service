package com.horsefeed.service;

import com.horsefeed.dao.HorseDao;
import com.horsefeed.model.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseService {

  @Autowired
  private HorseDao horseDao;

  public List<Horse> getAllHorses() {
    return horseDao.findAll();
  }

  public Horse getHorseById(Long id) {
    return horseDao.findById(id);
  }

  public Horse saveHorse(Horse horse) {
    validateHorse(horse);
    return horseDao.save(horse);
  }

  public void deleteHorse(Long id) {
    Horse horse = horseDao.findById(id);
    if (horse == null) {
      throw new IllegalArgumentException("Horse not found with id: " + id);
    }
    horseDao.deleteById(id);
  }

  public List<Horse> getHorsesByActivityLevel(Horse.ActivityLevel activityLevel) {
    return horseDao.findByActivityLevel(activityLevel);
  }

  public long getHorseCount() {
    return horseDao.count();
  }

  private void validateHorse(Horse horse) {
    if (horse.getName() == null || horse.getName().trim().isEmpty()) {
      throw new IllegalArgumentException("Horse name is required");
    }
    if (horse.getAge() == null || horse.getAge() <= 0) {
      throw new IllegalArgumentException("Horse age must be positive");
    }
    if (horse.getWeight() == null || horse.getWeight() <= 0) {
      throw new IllegalArgumentException("Horse weight must be positive");
    }
  }

  public boolean horseExists(Long id) {
    return horseDao.findById(id) != null;
  }
}
