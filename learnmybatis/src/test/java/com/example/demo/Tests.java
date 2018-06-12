package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.example.pojo.Category;

public class Tests {

	@Test
	public void list() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			List<Category> cs = session.selectList("listCategory");
			for (Category c : cs) {
				System.out.println(c.getName());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void add() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			Category c = new Category();
			c.setName("new Category");
			session.insert("addCategory", c);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void remove() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			Category c = new Category();
			c.setId(3);
			session.delete("deleteCategory", c);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void findById() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			Category c = session.selectOne("getCategory", 1);
			System.out.println(c.getName());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void update() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			Category c = session.selectOne("getCategory", 1);
			c.setName("update category");
			session.update("updateCategory", c);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void findByName() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			List<Category> cs = session.selectList("listCategoryByName", "cat");
			for (Category c : cs) {
				System.out.println(c.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void findByIdAndName() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", 1);
			params.put("name", "cat");
			List<Category> cs = session.selectList("listCategoryByIdAndName", params);
			for (Category c : cs) {
				System.out.println(c.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

}
