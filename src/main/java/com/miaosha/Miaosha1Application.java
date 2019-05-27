package com.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Miaosha1Application {

	@RequestMapping("/")
	@ResponseBody
	String hello(){
		return "123";
	}

	public static void main(String[] args) {
		SpringApplication.run(Miaosha1Application.class, args);
	}

}
