package com.horsefeed.dao;

import com.horsefeed.model.FeedType;
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
public class FeedTypeDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final class FeedTypeRowMapper implements RowMapper<FeedType> {
    @Override
    public FeedType mapRow(ResultSet rs, int rowNum) throws SQLException {
      FeedType feedType = new FeedType();
      feedType.setId(rs.getLong("id"));
      feedType.setName(rs.getString("name"));
      feedType.setBrand(rs.getString("brand"));
      feedType.setCaloriesPerKg(rs.getDouble("calories_per_kg"));
      feedType.setProteinPercentage(rs.getDouble("protein_percentage"));
      feedType.setFiberPercentage(rs.getDouble("fiber_percentage"));
      feedType.setFatPercentage(rs.getDouble("fat_percentage"));
      feedType.setDescription(rs.getString("description"));

      if (rs.getTimestamp("created_at") != null) {
        feedType.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
      }

      return feedType;
    }
  }

  public List<FeedType> findAll() {
    String sql = "SELECT * FROM feed_types ORDER BY name";
    return jdbcTemplate.query(sql, new FeedTypeRowMapper());
  }

  public FeedType findById(Long id) {
    String sql = "SELECT * FROM feed_types WHERE id = ?";
    List<FeedType> feedTypes = jdbcTemplate.query(sql, new FeedTypeRowMapper(), id);
    return feedTypes.isEmpty() ? null : feedTypes.get(0);
  }

  public FeedType save(FeedType feedType) {
    if (feedType.getId() == null) {
      return insert(feedType);
    } else {
      update(feedType);
      return feedType;
    }
  }

  private FeedType insert(FeedType feedType) {
    String sql = "INSERT INTO feed_types (name, brand, calories_per_kg, protein_percentage, fiber_percentage, fat_percentage, description) VALUES (?, ?, ?, ?, ?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, feedType.getName());
      ps.setString(2, feedType.getBrand());
      ps.setDouble(3, feedType.getCaloriesPerKg());
      ps.setDouble(4, feedType.getProteinPercentage());
      ps.setDouble(5, feedType.getFiberPercentage());
      ps.setDouble(6, feedType.getFatPercentage());
      ps.setString(7, feedType.getDescription());
      return ps;
    }, keyHolder);

    feedType.setId(keyHolder.getKey().longValue());
    return feedType;
  }

  private void update(FeedType feedType) {
    String sql = "UPDATE feed_types SET name = ?, brand = ?, calories_per_kg = ?, protein_percentage = ?, fiber_percentage = ?, fat_percentage = ?, description = ? WHERE id = ?";
    jdbcTemplate.update(sql,
        feedType.getName(),
        feedType.getBrand(),
        feedType.getCaloriesPerKg(),
        feedType.getProteinPercentage(),
        feedType.getFiberPercentage(),
        feedType.getFatPercentage(),
        feedType.getDescription(),
        feedType.getId());
  }

  public void deleteById(Long id) {
    String sql = "DELETE FROM feed_types WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }

  public List<FeedType> findByBrand(String brand) {
    String sql = "SELECT * FROM feed_types WHERE brand = ? ORDER BY name";
    return jdbcTemplate.query(sql, new FeedTypeRowMapper(), brand);
  }

  public long count() {
    String sql = "SELECT COUNT(*) FROM feed_types";
    return jdbcTemplate.queryForObject(sql, Long.class);
  }
}
