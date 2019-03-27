package com.ljj.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;


@RestController
public class ReactorController {

	private static final Logger  LOGGER=LoggerFactory.getLogger(ReactorController.class);
	
	@GetMapping("/reactive")
	public Mono<String> hello() {
		
		LOGGER.info("【响应式编程】接收请求===>");
		
		return Mono.just("Welcome to reactive world");
	}
	
}
