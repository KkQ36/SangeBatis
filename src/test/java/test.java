import com.kq.mybatis.io.Resources;
import com.kq.mybatis.session.SqlSession;
import com.kq.mybatis.session.SqlSessionFactory;
import com.kq.mybatis.session.SqlSessionFactoryBuilder;
import com.kq.mybatis.dao.UserDao;
import org.junit.Test;
import java.io.IOException;
import java.io.Reader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * @author kq
 * 2024-06-04 17:21
 **/
public class test {
    
    @Test
    public void testSqlSession() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        System.out.println(mapper.queryUserInfoById(1));
    }

    @Test
    public void testDriversGet() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println("Driver Name: " + driver.getClass().getName());
            System.out.println("Major Version: " + driver.getMajorVersion());
            System.out.println("Minor Version: " + driver.getMinorVersion());
            System.out.println("------------------------------");
        }
    }
}
