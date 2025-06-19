package com.horsefeed.dao;

import com.horsefeed.model.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class HorseDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final class HorseRowMapper implements RowMapper<Horse> {
    @Override
    public Horse mapRow(ResultSet rs, int rowNum) throws SQLException {
      Horse horse = new Horse();
      horse.setId(rs.getLong("id"));
      horse.setName(rs.getString("name"));
      horse.setBreed(rs.getString("breed"));
      horse.setAge(rs.getInt("age"));
      horse.setWeight(rs.getDouble("weight"));

      String activityLevel = rs.getString("activity_level");
      if (activityLevel != null) {
        horse.setActivityLevel(Horse.ActivityLevel.valueOf(activityLevel));
      }

      if (rs.getTimestamp("created_at") != null) {
        horse.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
      }

      return horse;
    }
  }

  public List<Horse> findAll() {
    String sql = "SELECT * FROM horses ORDER BY name";
    return jdbcTemplate.query(sql, new HorseRowMapper());
  }

  public Horse findById(Long id) {
    String sql = "SELECT * FROM horses WHERE id = ?";
    List<Horse> horses = jdbcTemplate.query(sql, new HorseRowMapper(), id);
    return horses.isEmpty() ? null : horses.get(0);
  }

  public Horse save(Horse horse) {
    if (horse.getId() == null) {
      return insert(horse);
    } else {
      update(horse);
      return horse;
    }
  }

  private Horse insert(Horse horse) {
    String sql = "INSERT INTO horses (name, breed, age, weight, activity_level) VALUES (?, ?, ?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, horse.getName());
      ps.setString(2, horse.getBreed());
      ps.setInt(3, horse.getAge());
      ps.setDouble(4, horse.getWeight());
      ps.setString(5, horse.getActivityLevel() != null ? horse.getActivityLevel().name() : null);
      return ps;
    }, keyHolder);

    horse.setId(keyHolder.getKey().longValue());
    return horse;
  }

  private void update(Horse horse) {
    String sql = "UPDATE horses SET name = ?, breed = ?, age = ?, weight = ?, activity_level = ? WHERE id = ?";
    jdbcTemplate.update(sql,
        horse.getName(),
        horse.getBreed(),
        horse.getAge(),
        horse.getWeight(),
        horse.getActivityLevel() != null ? horse.getActivityLevel().name() : null,
        horse.getId());
  }

  public void deleteById(Long id) {
    String sql = "DELETE FROM horses WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }

  public List<Horse> findByActivityLevel(Horse.ActivityLevel activityLevel) {
    String sql = "SELECT * FROM horses WHERE activity_level = ? ORDER BY name";
    return jdbcTemplate.query(sql, new HorseRowMapper(), activityLevel.name());
  }

  public long count() {
    String sql = "SELECT COUNT(*) FROM horses";
    return jdbcTemplate.queryForObject(sql, Long.class);
  }
}
