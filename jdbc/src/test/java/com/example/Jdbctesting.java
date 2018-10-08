package com.example;

import java.util.List;

import com.example.entity.User;
import com.example.rowmapper.UserRowMapper;
import com.example.utils.JdbcUtils;

public class Jdbctesting {
	
	public void queryTest() {
		String sql = "SELECT id, name, email FROM user";
		UserRowMapper userRowMapper = new UserRowMapper();
		List<User> users = JdbcUtils.query(sql, userRowMapper);
		System.out.println(users);
	}
	
	public void insertTest() {
		String sql = "INSERT INTO `user`(`id`, `name`, `email`) VALUES ('02', 'kio', 'kemail')";
		int result = JdbcUtils.update(sql);
		System.out.println(result);
	}
	
	public void updateTest() {
		String sql = "UPDATE `user` SET `name`='Dior',`email`='demail' WHERE `id`='02'";
		int result = JdbcUtils.update(sql);
		System.out.println(result);
	}
	
	public void deleteTest() {
		String sql = "DELETE FROM `user` WHERE `id`='02'";
		int result = JdbcUtils.update(sql);
		System.out.println(result);
	}
	
	public void queryWithPstmtTest() {
		String sql = "SELECT id, name, email FROM user";
		UserRowMapper userRowMapper = new UserRowMapper();
		List<User> users = JdbcUtils.queryWithPstmt(sql, userRowMapper);
		System.out.println(users);
	}
	
	public void inserWithPstmttTest() {
		String sql = "INSERT INTO `user`(`id`, `name`, `email`) VALUES (?, ?, ?)";
		Object[] param = { "02", "Nyb", "nemail" };
		int result = JdbcUtils.updateWithPstmt(sql, param);
		System.out.println(result);
	}
	
	public void updateWithPstmtTest() {
		String sql = "UPDATE `user` SET `name`=?,`email`=? WHERE `id`=?";
		Object[] param = { "KOs", "kemail", "01" };
		int result = JdbcUtils.updateWithPstmt(sql, param);
		System.out.println(result);
	}
	
	public void deleteWithPstmtTest() {
		String sql = "DELETE FROM `user` WHERE `id`=?";
		Object[] param = { "02" };
		int result = JdbcUtils.updateWithPstmt(sql, param);
		System.out.println(result);
	}
	
}
