package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.rowmapper.RowMapper;

public class JdbcUtils {
	private static final String URL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&useSSL=false";
	private static final String NAME = "root";
	private static final String PASSWORD = "123456";
	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";

	static {
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connection to mysql
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Close all
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
		close(rs);
		close(stmt);
		close(conn);
	}
	
	/**
	 * Close Statement and Connection
	 * @param stmt
	 * @param conn
	 */
	public static void closeAll(Statement stmt, Connection conn) {
		close(stmt);
		close(conn);
	}
	
	/**
	 * Close ResultSet
	 * 
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close Statement
	 * 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close Connection
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Query
	 * @param sql
	 * @param rowMapper
	 * @return
	 */
	public static <T> List<T> query(String sql, RowMapper<T> rowMapper) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<T> result = new ArrayList<T>();
			while (rs.next()) {
				T t = rowMapper.getEntity(rs);
				result.add(t);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	/**
	 * 
	 * Update ( insert, update, delete )
	 */
	public static int update(String sql) {
		Connection conn = getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(stmt, conn);
		}
		return 0;
	}
	
	/**
	 * Query With Pstmt
	 * @param sql
	 * @param rowMapper
	 * @return
	 */
	public static <T> List<T> queryWithPstmt(String sql, RowMapper<T> rowMapper) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			rs = pstmt.executeQuery();
			List<T> result = new ArrayList<T>();
			while (rs.next()) {
				T t = rowMapper.getEntity(rs);
				result.add(t);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll(rs, pstmt, conn);
		}
		return null;
	}
	
	/**
	 * Update With Pstmt
	 * @param sql
	 * @param param
	 * @return
	 */
	public static int updateWithPstmt(String sql, Object[] param) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if(null != param && param.length>0){
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i+1, param[i]);
				}
			}
			int count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}
		return 0;
	}
	
}
