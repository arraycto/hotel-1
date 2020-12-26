package cn.ajiehome.management;

import cn.ajiehome.common.BaseBeanUtils;
import cn.ajiehome.common.utils.BodyUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("cn.ajiehome.management.*.mapper")
public class ManagementApplication extends BaseBeanUtils{
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }
}
