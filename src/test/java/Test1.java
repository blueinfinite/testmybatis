import com.blueinfinite.App;
import com.blueinfinite.BeanTest;
import com.blueinfinite.ConfigDBInfo;
import com.blueinfinite.MessagePrinter;
import com.blueinfinite.mapper.Class2Mapper;
import com.blueinfinite.mapper.CustomMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(App.class)
public class Test1 {
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
    public void springtest() {
        messagePrinter.pringMessage();

        System.out.println(configDBInfo.toString());

        SqlSession sqlSession=sqlSessionFactory.openSession();
        CustomMapper customMapper = sqlSession.getMapper(CustomMapper.class);
        Class2Mapper class2Mapper = sqlSession.getMapper(Class2Mapper.class);
        System.out.println(customMapper.getCustom(1604054));
        System.out.println(class2Mapper.getInfo(1149));





    }
}
