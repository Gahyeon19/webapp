package com.example.filter_interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class FilterInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterInterceptorApplication.class, args);
    }

}
