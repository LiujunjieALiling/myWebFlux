package com.ljj.sample.controller;

import java.time.Duration;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljj.sample.model.User;
import com.ljj.sample.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	private static final Logger  LOGGER=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/add")
	public Mono<User>  save(@RequestBody User user){
		
		LOGGER.info("新增用户信息,请求参数={}",user.toString());
		
		return userService.save(user);
	}
	
	
	@PostMapping("/find")
	public Mono<User>  findByName(@RequestBody  Map<String,Object> map){
		
		String name=(String)map.get("name");
		
		LOGGER.info("查询用户信息,请求参数={}",name);
		return userService.findByName(name);
	}
	
	@GetMapping(value="/findAll",produces=MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<User>  findAll(){
		
		LOGGER.info("查询全部信息==>");
		return userService.findAll().delayElements(Duration.ofSeconds(2));
	}
	
	@DeleteMapping("/{name}")
	public Mono<Long>  deleteByName(@PathVariable String name){
		
		LOGGER.info("删除用户信息,请求参数={}",name);
		
		return userService.deleteByName(name);
	}
	
	
	
}
