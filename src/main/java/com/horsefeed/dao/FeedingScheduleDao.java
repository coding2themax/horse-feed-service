package com.horsefeed.dao;

import com.horsefeed.model.FeedingSchedule;
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
public class FeedingScheduleDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final class FeedingScheduleRowMapper implements RowMapper<FeedingSchedule> {
    @Override
    public FeedingSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
      FeedingSchedule schedule = new FeedingSchedule();
      schedule.setId(rs.getLong("id"));
      schedule.setHorseId(rs.getLong("horse_id"));
      schedule.setFeedTypeId(rs.getLong("feed_type_id"));
      schedule.setQuantityKg(rs.getDouble("quantity_kg"));

      if (rs.getTime("feeding_time") != null) {
        schedule.setFeedingTime(rs.getTime("feeding_time").toLocalTime());
      }

      String frequency = rs.getString("frequency");
      if (frequency != null) {
        schedule.setFrequency(FeedingSchedule.Frequency.valueOf(frequency));
      }

      if (rs.getDate("start_date") != null) {
        schedule.setStartDate(rs.getDate("start_date").toLocalDate());
      }

      if (rs.getDate("end_date") != null) {
        schedule.setEndDate(rs.getDate("end_date").toLocalDate());
      }

      schedule.setNotes(rs.getString("notes"));

      if (rs.getTimestamp("created_at") != null) {
        schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
      }

      // Include horse and feed type names if available (for joined queries)
      try {
        schedule.setHorseName(rs.getString("horse_name"));
        schedule.setFeedTypeName(rs.getString("feed_type_name"));
      } catch (SQLException e) {
        // These columns might not be present in all queries
      }

      return schedule;
    }
  }

  public List<FeedingSchedule> findAll() {
    String sql = "SELECT fs.*, h.name as horse_name, ft.name as feed_type_name " +
        "FROM feeding_schedules fs " +
        "JOIN horses h ON fs.horse_id = h.id " +
        "JOIN feed_types ft ON fs.feed_type_id = ft.id " +
        "ORDER BY fs.feeding_time";
    return jdbcTemplate.query(sql, new FeedingScheduleRowMapper());
  }

  public FeedingSchedule findById(Long id) {
    String sql = "SELECT fs.*, h.name as horse_name, ft.name as feed_type_name " +
        "FROM feeding_schedules fs " +
        "JOIN horses h ON fs.horse_id = h.id " +
        "JOIN feed_types ft ON fs.feed_type_id = ft.id " +
        "WHERE fs.id = ?";
    List<FeedingSchedule> schedules = jdbcTemplate.query(sql, new FeedingScheduleRowMapper(), id);
    return schedules.isEmpty() ? null : schedules.get(0);
  }

  public List<FeedingSchedule> findByHorseId(Long horseId) {
    String sql = "SELECT fs.*, h.name as horse_name, ft.name as feed_type_name " +
        "FROM feeding_schedules fs " +
        "JOIN horses h ON fs.horse_id = h.id " +
        "JOIN feed_types ft ON fs.feed_type_id = ft.id " +
        "WHERE fs.horse_id = ? " +
        "ORDER BY fs.feeding_time";
    return jdbcTemplate.query(sql, new FeedingScheduleRowMapper(), horseId);
  }

  public FeedingSchedule save(FeedingSchedule schedule) {
    if (schedule.getId() == null) {
      return insert(schedule);
    } else {
      update(schedule);
      return schedule;
    }
  }

  private FeedingSchedule insert(FeedingSchedule schedule) {
    String sql = "INSERT INTO feeding_schedules (horse_id, feed_type_id, quantity_kg, feeding_time, frequency, start_date, end_date, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, schedule.getHorseId());
      ps.setLong(2, schedule.getFeedTypeId());
      ps.setDouble(3, schedule.getQuantityKg());
      ps.setTime(4, java.sql.Time.valueOf(schedule.getFeedingTime()));
      ps.setString(5, schedule.getFrequency() != null ? schedule.getFrequency().name() : null);
      ps.setDate(6, java.sql.Date.valueOf(schedule.getStartDate()));
      ps.setDate(7, schedule.getEndDate() != null ? java.sql.Date.valueOf(schedule.getEndDate()) : null);
      ps.setString(8, schedule.getNotes());
      return ps;
    }, keyHolder);

    Number key = keyHolder.getKey();
    if (key != null) {
      schedule.setId(key.longValue());
    }
    return schedule;
  }

  private void update(FeedingSchedule schedule) {
    String sql = "UPDATE feeding_schedules SET horse_id = ?, feed_type_id = ?, quantity_kg = ?, feeding_time = ?, frequency = ?, start_date = ?, end_date = ?, notes = ? WHERE id = ?";
    jdbcTemplate.update(sql,
        schedule.getHorseId(),
        schedule.getFeedTypeId(),
        schedule.getQuantityKg(),
        java.sql.Time.valueOf(schedule.getFeedingTime()),
        schedule.getFrequency() != null ? schedule.getFrequency().name() : null,
        java.sql.Date.valueOf(schedule.getStartDate()),
        schedule.getEndDate() != null ? java.sql.Date.valueOf(schedule.getEndDate()) : null,
        schedule.getNotes(),
        schedule.getId());
  }

  public void deleteById(Long id) {
    String sql = "DELETE FROM feeding_schedules WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }

  public long count() {
    String sql = "SELECT COUNT(*) FROM feeding_schedules";
    Long count = jdbcTemplate.queryForObject(sql, Long.class);
    return count != null ? count : 0L;
  }
}
