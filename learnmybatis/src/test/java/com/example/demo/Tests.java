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
import com.example.pojo.Order;
import com.example.pojo.OrderItem;
import com.example.pojo.Product;

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

	@Test
	public void listOntToMany() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			List<Category> cs = session.selectList("listCategory");
			for (Category c : cs) {
				System.out.println(c);
				List<Product> ps = c.getProducts();
				for (Product p : ps) {
					System.out.println("\t" + p);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void manytoone() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			List<Product> ps = session.selectList("listProduct");
			for (Product p : ps) {
				System.out.println(p + " 对应的分类是 \t " + p.getCategory());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void listmanytomany() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			List<Order> os = session.selectList("listOrder");
			for (Order o : os) {
				System.out.println(o.getCode());
				List<OrderItem> ois = o.getOrderItems();
				for (OrderItem oi : ois) {
					System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(), oi.getProduct().getPrice(),
							oi.getNumber());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}
	
	@Test
	public void manytomanyadd() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

	        Order o1 = session.selectOne("getOrder", 1);
	        Product p6 = session.selectOne("getProduct", 6);
	        OrderItem oi = new OrderItem();
	        oi.setProduct(p6);
	        oi.setOrder(o1);
	        oi.setNumber(200);
	 
	        session.insert("addOrderItem", oi);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	@Test
	public void manytomanyremove() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

	        Order o1 = session.selectOne("getOrder",1);
	        Product p6 = session.selectOne("getProduct",6);
			OrderItem oi = new OrderItem();
	        oi.setProduct(p6);
	        oi.setOrder(o1);
	        session.delete("deleteOrderItem", oi); 
	        
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}
	
	@Test
	public void deleteOrder() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			session.delete("deleteOrder",1);
	        
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}
	
	@Test
	public void findByIf() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			System.out.println("查询所有的");
			List<Product> ps = session.selectList("listProduct2");
			for (Product p : ps) {
				System.out.println(p);
			}

			System.out.println("模糊查询");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", "a");
			List<Product> ps2 = session.selectList("listProduct2", params);
			for (Product p : ps2) {
				System.out.println(p);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}
	
}
