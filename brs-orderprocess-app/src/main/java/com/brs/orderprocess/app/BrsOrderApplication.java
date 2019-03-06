package com.brs.orderprocess.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tiny lin
 * @date 2019/2/22
 */
@SpringBootApplication(scanBasePackages = {"com.brs.idm.common","com.brs.idm.service","com.brs.idm.rest","com.brs.orderprocess","com.brs.orderinfo","com.brs.periodical"})
@MapperScan(basePackages = "com.brs.*.persistence.dao")
@EnableConfigurationProperties
@EnableTransactionManagement
public class BrsOrderApplication {
    public static void main(String[]args){
        SpringApplication.run(BrsOrderApplication.class,args);
    }
}