package com.qa.hobby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.qa.hobby.HobbyProjectApplication;
import com.qa.hobby.rest.PlayerController;


@SpringBootApplication
public class HobbyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HobbyProjectApplication.class, args);
//		ApplicationContext beanBag = SpringApplication.run(HobbyProjectApplication.class, args);
//
//		PlayerController controller = beanBag.getBean(PlayerController.class);
//
//		System.out.println(controller);
//
//		String message = beanBag.getBean(String.class);
//
//		System.out.println(message);

	}

}