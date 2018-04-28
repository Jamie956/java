package com.example;

import java.util.List;

import org.junit.Test;

import com.example.entity.User;
import com.example.rowmapper.UserRowMapper;
import com.example.utils.JdbcUtils;

public class Jdbctesting {
	
	@Test
	public void test() {
		String sql = "SELECT id, name, email FROM user";
		UserRowMapper userRowMapper = new UserRowMapper();
		List<User> users = JdbcUtils.query(sql, userRowMapper);
		System.out.println(users);
	}
	
}
