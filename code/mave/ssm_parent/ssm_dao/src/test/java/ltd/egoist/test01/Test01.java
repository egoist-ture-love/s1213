package ltd.egoist.test01;

import ltd.egoist.dao.ItemsDao;
import ltd.egoist.pojo.Items;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test01
 * @Description TODO
 * @Date 2021/5/12 11:00
 * @Created by Lenovo
 */

public class Test01 {
    @Test
    public void method01(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        ItemsDao itemsDao = (ItemsDao) applicationContext.getBean("itemsDao");
        Items items = itemsDao.findById(1);
        System.out.println(items);

    }
}
