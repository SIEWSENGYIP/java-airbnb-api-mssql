package com.example.airbnb.repositories;

import java.util.List;

import com.example.airbnb.entities.Property;
import com.example.airbnb.mappers.PropertyRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * PropertyRepository
 */

 @Transactional
 @Repository
public class PropertyRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PropertyRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }

    public List<Property> getAllProperties() {
        String sql = "SELECT * FROM property";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    } 

    public List<Property> getPropertyByParams(String address, int numRooms, int price) {
      String sql = "SELECT * FROM property WHERE address LIKE ? AND numRooms = ? AND price = ?";
      RowMapper<Property> rowMapper = new PropertyRowMapper();
      return this.jdbcTemplate.query(sql, new Object[]{"%"+address+"%", numRooms, price}, rowMapper);

    }

    public List<Property> getBookedProperties() {
      String sql = "SELECT DISTINCT a.* FROM property a JOIN booking b ON a.id = b.property_id";
      RowMapper<Property> rowMapper = new PropertyRowMapper();
      return this.jdbcTemplate.query(sql, rowMapper);

    }

    public List<Property> getOrderedProperties() {
      String sql = "SELECT * FROM property ORDER BY price";
      RowMapper<Property> rowMapper = new PropertyRowMapper();
      return this.jdbcTemplate.query(sql, rowMapper);

    }
    

    public void createProperty(Property property){
      String sql = "INSERT INTO property VALUES (?,?,?,?,?)";
      this.jdbcTemplate.update(sql, property.getAddress(), property.getNumRooms(), property.getPrice(), property.getAllowSmoking(), property.getMaxGuestNum());

      sql = "SELECT id FROM property WHERE address = ?";
      int id = jdbcTemplate.queryForObject(sql, Integer.class, property.getAddress());

      property.setId(id);
  }


    public void updateProperty(int id, int price, boolean allowSmoking){
      String sql = "UPDATE property SET price = ?, allowSmoking = ? WHERE id = ?";
      this.jdbcTemplate.update(sql, price, allowSmoking, id);
  }

  public void deleteProperty(int id){
    String sql = "DELETE FROM property WHERE id = ?";
    this.jdbcTemplate.update(sql, id);
}
}