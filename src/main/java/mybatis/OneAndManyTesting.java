package mybatis;

import java.io.IOException;
import java.util.List;

import mybatis.pojo.Category;
import mybatis.pojo.Order;
import mybatis.pojo.OrderItem;
import mybatis.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OneAndManyTesting {
    public static SqlSession session = null;

    static {
        try {
            SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            session = sf.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // list one to many
        List<Category> cs = session.selectList("listOneToMany");
        // list many to one
        List<Product> ps = session.selectList("listProduct");
        // list many to many
        List<Order> os = session.selectList("listOrder");

    }

    // insert many to many
    public static void insertManyToMany() {
        Order o1 = session.selectOne("getOrder", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        oi.setNumber(200);

        session.insert("addOrderItem", oi);
    }

    // delete many to many
    public static void deleteManyToMany() {
        Order o1 = session.selectOne("getOrder", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        session.delete("deleteOrderItem", oi);
    }
}
