package cn.chen;

import cn.chen.entity.Order;
import cn.chen.mapper.OrdMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class SobookApplicationTests {
    @Test
    public void test(){
        ConfigurableApplicationContext run = SpringApplication.run(SobookApplication.class);
        OrdMapper bean = run.getBean(OrdMapper.class);
        Order order = new Order();
        order.setPhone("12581");
        order.setAddr("地球");
        order.setUid(28);
        int i = bean.addOrd(order);
        System.out.println("i = " + i);
        System.out.println(order.getOrdid());
    }

    @Test
    void contextLoads() {
    }

}
