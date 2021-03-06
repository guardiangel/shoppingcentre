package com.felix.shoppingcentre;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.felix.shoppingcentre.mapper")
public class ShoppingCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCenterApplication.class, args);
	}

}
