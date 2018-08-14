package com.example.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.entity.User;

public class UserRowMapper implements RowMapper<User> {
	public User getEntity(ResultSet rs) throws SQLException {
		User user = new User();
		user.setID(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;
	}
}