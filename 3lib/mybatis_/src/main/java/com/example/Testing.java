package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.pojo.Category;
import com.example.pojo.Order;
import com.example.pojo.OrderItem;
import com.example.pojo.Product;

public class Testing {
	public static void main(String[] args) {
		InputStream inputStream = null;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			// simpleList(session);
			// simpleInsert(session);
			// simpleDelete(session);
			// simpleFindById(session);
			// simpleUpdate(session);
			// simpleListByName(session);
			// simpleListIdByName(session);
			// listOneToMany(session);
			// listManyToOne(session);
			// listManyToMany(session);
			// insertManyToMany(session);
			// deleteManyToMany(session);
			// deleteMulti(session);
			// listByIf(session);
			// listBywhere(session);
			// updateBySet(session);
			// listByTrimWhere(session);
			// updateByTrimSet(session);
			// listByWhenOtherwise(session);
			// listByForeach(session);
			listByBind(session);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	// simple list
	public static void simpleList(SqlSession session) {
		List<Category> cs = session.selectList("listCategory0");
		for (Category c : cs) {
			System.out.println(c.getName());
		}
	}

	// simple insert
	public static void simpleInsert(SqlSession session) {
		Category c = new Category();
		c.setName("new Category");
		session.insert("addCategory", c);
	}

	// simple delete
	public static void simpleDelete(SqlSession session) {
		Category c = new Category();
		c.setId(3);
		session.delete("deleteCategory", c);
	}

	// simple find by id
	public static void simpleFindById(SqlSession session) {
		Category c = session.selectOne("getCategory", 1);
		System.out.println(c.getName());
	}

	// simple update
	public static void simpleUpdate(SqlSession session) {
		Category c = session.selectOne("getCategory", 1);
		c.setName("update category");
		session.update("updateCategory", c);
	}

	// simple list by name
	public static void simpleListByName(SqlSession session) {
		List<Category> cs = session.selectList("listCategoryByName", "cat");
		for (Category c : cs) {
			System.out.println(c.getName());
		}
	}

	// simple list by id and name
	public static void simpleListIdByName(SqlSession session) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		params.put("name", "cat");
		List<Category> cs = session.selectList("listCategoryByIdAndName", params);
		for (Category c : cs) {
			System.out.println(c.getName());
		}
	}

	// list one to many
	public static void listOneToMany(SqlSession session) {
		List<Category> cs = session.selectList("listCategory2");
		for (Category c : cs) {
			System.out.println(c);
			List<Product> ps = c.getProducts();
			for (Product p : ps) {
				System.out.println("\t" + p);
			}
		}
	}

	// list many to one
	public static void listManyToOne(SqlSession session) {
		List<Product> ps = session.selectList("listProduct");
		for (Product p : ps) {
			System.out.println(p + " 对应的分类是 \t " + p.getCategory());
		}
	}

	// list many to many
	public static void listManyToMany(SqlSession session) {
		List<Order> os = session.selectList("listOrder");
		for (Order o : os) {
			System.out.println(o.getCode());
			List<OrderItem> ois = o.getOrderItems();
			for (OrderItem oi : ois) {
				System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(), oi.getProduct().getPrice(),
						oi.getNumber());
			}
		}
	}

	// insert many to many
	public static void insertManyToMany(SqlSession session) {
		Order o1 = session.selectOne("getOrder", 1);
		Product p6 = session.selectOne("getProduct", 6);
		OrderItem oi = new OrderItem();
		oi.setProduct(p6);
		oi.setOrder(o1);
		oi.setNumber(200);

		session.insert("addOrderItem", oi);
	}

	// delete many to many
	public static void deleteManyToMany(SqlSession session) {
		Order o1 = session.selectOne("getOrder", 1);
		Product p6 = session.selectOne("getProduct", 6);
		OrderItem oi = new OrderItem();
		oi.setProduct(p6);
		oi.setOrder(o1);
		session.delete("deleteOrderItem", oi);
	}

	// delete multi
	public static void deleteMulti(SqlSession session) {
		session.delete("deleteOrder", 1);
	}

	// list by if tag
	public static void listByIf(SqlSession session) {
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
	}

	// list by where tag
	public static void listBywhere(SqlSession session) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		params.put("price", "10");
		List<Product> ps2 = session.selectList("listProduct3", params);
		for (Product p : ps2) {
			System.out.println(p);
		}
	}

	// update by tag set
	public static void updateBySet(SqlSession session) {
		Product p = new Product();
		p.setId(6);
		p.setName("product xf");
		p.setPrice(99.99f);
		session.update("updateProduct", p);
	}

	// list by trim tag using where
	public static void listByTrimWhere(SqlSession session) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		params.put("price", "10");
		List<Product> ps2 = session.selectList("listProduct4", params);
		for (Product p : ps2) {
			System.out.println(p);
		}
	}

	// update by trim tag using set
	public static void updateByTrimSet(SqlSession session) {
		Product p = new Product();
		p.setId(6);
		p.setName("product bf");
		p.setPrice(9.99f);
		session.update("updateProduct2", p);
	}

	// list by when-otherwise tag
	public static void listByWhenOtherwise(SqlSession session) {
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("name","a");
		// params.put("price","10");
		List<Product> ps = session.selectList("listProduct5", params);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

	// list by foreach
	public static void listByForeach(SqlSession session) {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(3);
		ids.add(5);

		List<Product> ps = session.selectList("listProduct6", ids);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

	// list by bind
	public static void listByBind(SqlSession session) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "product");
		List<Product> ps = session.selectList("listProduct", params);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

}
