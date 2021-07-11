package com.inflearn.springbootwithjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootWithJpaApplication {

    public static void main(String[] args) {

        Hello hello = new Hello();
        hello.setData("df");
        String data = hello.getData();
        System.out.println("data=" + data);

        SpringApplication.run(SpringbootWithJpaApplication.class, args);
    }

}
