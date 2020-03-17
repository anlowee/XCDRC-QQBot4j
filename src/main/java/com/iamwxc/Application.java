package com.iamwxc;

import com.iamwxc.bot.seller.admin.ManageGoodListener;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
public class Application {

//    @Autowired
//    private BeanFactory factory;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
//
//        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//
//        ManageGoodListener bean = beanFactory.getBean(ManageGoodListener.class);
//
//        System.out.println(bean.getGoodDAO());



    }

//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return args -> {
//
//            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//            System.out.println(factory.getBean("goodDAO"));
//
//
//        };
//    }


}
