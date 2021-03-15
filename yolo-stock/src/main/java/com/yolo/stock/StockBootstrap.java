package com.yolo.stock;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-05-25 12:44
 */
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringBootApplication
@EnableScheduling
//@EnableAceCache
@EnableTransactionManagement
@MapperScan("com.yolo.stock.mapper")
@EnableSwagger2Doc
public class StockBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(StockBootstrap.class).run(args);
    }
}
