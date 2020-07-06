package mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class BasicTesting {
    public static SqlSession session = null;

    static {
        try {
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
            session = sf.openSession();
            session.delete("deleteAll");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        session.insert("add", new Category("new category1"));
        session.insert("add", new Category("new category2"));

        List<Category> cs = session.selectList("list");

        Category c = session.selectOne("getByName", "new category1");

        c.setName("update category1");
        session.update("update", c);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", 1);
        params.put("name", "update");
        List<Category> cs2 = session.selectList("getByIdAndName", params);
    }

}
