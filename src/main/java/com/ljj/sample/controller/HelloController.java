package com.ljj.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final Logger  LOGGER=LoggerFactory.getLogger(HelloController.class);
	
	@GetMapping("/hello")
	public String hello() {
		
		LOGGER.info("接收到hello请求");
		
		return "Welcome to reactive world";
	}
	
}
