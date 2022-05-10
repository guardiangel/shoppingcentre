package com.felix.shoppingcentre;

import com.felix.shoppingcentre.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //allowed list
        List<String> patternList = new ArrayList<>();
        patternList.add("/bootstrap3/**");
        patternList.add("/css/**");
        patternList.add("/images/**");
        patternList.add("/js/**");
        patternList.add("/web/register.html");
        patternList.add("/web/login.html");
        patternList.add("/web/index.html");
        patternList.add("/web/product.html");
        patternList.add("/user/register");
        patternList.add("/user/login");
        patternList.add("/districts/**");
        patternList.add("/products/**");
        //add interceptor
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(patternList);
    }
}
