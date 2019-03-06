package com.brs.idm.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tiny lin
 * @date 2019/2/22
 */

@SpringBootApplication(scanBasePackages = {"com.brs.idm"})
@MapperScan("com.brs.idm.persistence.dao")
@EnableConfigurationProperties
@EnableTransactionManagement
public class BrsIdmApplication {
    public static void main(String[]args){
        SpringApplication.run(BrsIdmApplication.class,args);
    }
}
