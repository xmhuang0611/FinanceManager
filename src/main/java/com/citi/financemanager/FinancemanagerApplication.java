package com.citi.financemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FinancemanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancemanagerApplication.class, args);
    }

}
