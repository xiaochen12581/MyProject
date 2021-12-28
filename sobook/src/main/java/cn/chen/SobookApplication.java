package cn.chen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.chen.mapper")
public class SobookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SobookApplication.class, args);
    }

}
