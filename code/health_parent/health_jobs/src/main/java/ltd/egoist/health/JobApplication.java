package ltd.egoist.health;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Classname JobApplication
 * @Description TODO
 * @Date 2021/5/25 11:23
 * @Created by Lenovo
 */
public class JobApplication {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath: spring-jobs.xml");
        System.in.read();
    }
}
