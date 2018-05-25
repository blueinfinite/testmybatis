import com.blueinfinite.App;
import com.blueinfinite.BeanTest;
import com.blueinfinite.config.ConfigDBInfo;
import com.blueinfinite.MessagePrinter;
import com.blueinfinite.mapper.DepartmentMapper;
import com.blueinfinite.mapper.CustomMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(App.class)
public class TestMybatis {
    @Autowired
    BeanTest beanTest;

    @Autowired
    MessagePrinter messagePrinter;

    @Autowired
    ConfigDBInfo configDBInfo;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

//    @Autowired
//    CustomMapper customMapper;

    @Test
    public void test_springhello() {
        messagePrinter.pringMessage();
    }

    @Test
    public void test_getconfiginfo() {
        System.out.println(configDBInfo.toString());
    }

    @Test
    public void test_mybatis() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CustomMapper customMapper = sqlSession.getMapper(CustomMapper.class);
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

        System.out.println(customMapper.getCustom(1604054));
        System.out.println(departmentMapper.getInfo(1149));

        sqlSession.close();
    }
}
