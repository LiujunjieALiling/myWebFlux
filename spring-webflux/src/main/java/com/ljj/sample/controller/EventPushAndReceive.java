package com.ljj.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljj.sample.model.Event;
import com.ljj.sample.service.EventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 数据在Http上双向无限流动
 * @author 刘俊杰
 *
 */
@RestController
@RequestMapping("/event")
public class EventPushAndReceive {

	private static final Logger LOGGER=LoggerFactory.getLogger(EventPushAndReceive.class);
	
	
	@Autowired
	private EventService  eventService;
	
	
	/**
	 * 1. 指定传入的数据是application/stream+json，与getEvents方法的区别在于这个方法是consume这个数据流
	 * 2. pushEvents 返回的是保存成功的记录的Flux，但我们不需要，使用then方法表示“忽略数据元素，只返回一个完成信号”
	 * @param events
	 * @return
	 */
	@PostMapping(value = "/push", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<Void> pushEvents(@RequestBody Flux<Event>  events){
		
		LOGGER.info("数据流推送===>");
		
		return eventService.pushEvents(events).then();
	}

	/**
	 * GET方法的无限发出数据流的Endpoint，所以返回结果是一个Flux&lt;MyEvent&gt;，
	 * 不要忘了注解上produces = MediaType.APPLICATION_STREAM_JSON_VALUE
	 * 
	 * @return
	 */
	@GetMapping(value = "/get", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Event> getEvents(){
		
		LOGGER.info("数据流获取===>");
		
		return eventService.getEvents();
	}
	
}
