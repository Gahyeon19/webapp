//package com.example.filter_interceptor.config;
//
//import com.example.filter_interceptor.filter.LogFilter;
//import com.example.filter_interceptor.filter.LoginFilter;
//import com.example.filter_interceptor.interceptor.LogInterceptor;
//import com.example.filter_interceptor.interceptor.LoginInterceptor;
//import jakarta.servlet.FilterRegistration;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class SpringConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogInterceptor())
//                .addPathPatterns("/**")
//                .order(1);
////                .excludePathPatterns("/login", "/logout");
//
//        registry.addInterceptor(new LoginInterceptor())
//                .order(2)
//                .addPathPatterns("/**")    // 순서 상관 없음
//                .excludePathPatterns("/members/register", "/members/login", "/posts");  //login 했는지 물어보지 않아도 되는 페이지들
//    }
//}
//
//
//
//
////    LogFilter & LoginFilter
////@Configuration
////public class SpringConfig {
////    @Bean
////    public FilterRegistrationBean logfilterRB() {
////        FilterRegistrationBean frb = new FilterRegistrationBean(new LogFilter());   //생성자 주입
////        frb.addUrlPatterns("/*");
////        frb.setOrder(1);
////
////        return frb;
////    }
////
////    @Bean
////    public FilterRegistrationBean loginfilterRB() {
////        FilterRegistrationBean frb = new FilterRegistrationBean(new LoginFilter());
////        frb.addUrlPatterns("/members/register", "/members/login", "/members/logout");
////        frb.setOrder(2);
////
////        return frb;
////    }
////}