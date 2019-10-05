package com.hu.libraryManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 // 开启Swagger
@SpringBootApplication
public class LibraryManageStarter {
    public static void main(String[] args){
        SpringApplication.run(LibraryManageStarter.class, args);
    }
}
