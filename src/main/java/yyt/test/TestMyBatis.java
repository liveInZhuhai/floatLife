package yyt.test;

import yyt.service.User_tService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hadoop on 16-8-2.
 */
public class TestMyBatis {
    private User_tService user_tService;
    private static Logger logger = Logger.getLogger(TestMyBatis.class);
    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
        user_tService = (User_tService) context.getBean("user_tServiceImpl");
    }

    @Test
    public void test1() {
        int user = user_tService.delete(2);
        System.out.println(user);
        // logger.info("值："+user.getUserName());
//        logger.info(JSON.toJSONString(user));

    }
//    public void addUser(){
//        User_t user_t = new User_t();
//        user_t.setAge(10);
//        user_t.setPassword("3333333");
//        user_t.setUsername("111111111111");
//        System.out.println(user_tService.insert(user_t));
//    }
}
