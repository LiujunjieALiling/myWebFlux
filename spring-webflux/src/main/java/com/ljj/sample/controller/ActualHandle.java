package com.ljj.sample.controller;


import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * webflux简单使用
 * @author 刘俊杰
 *
 */
@Component
public class ActualHandle {

	private static final Logger  LOGGER=LoggerFactory.getLogger(HelloController.class);
	
	/**
	 * 获取时间
	 * @param serverRequest
	 * @return
	 */
	public Mono<ServerResponse>  time(ServerRequest serverRequest){
		
		return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is "+new SimpleDateFormat("HH:mm:ss").format(new Date())),String.class);
	}
	
	/**
	 * 获取日期
	 * @param serverRequest
	 * @return
	 */
	public Mono<ServerResponse>  date(ServerRequest serverRequest){
		
		return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is "+new SimpleDateFormat("yyyyMMdd").format(new Date())),String.class);
	}
	
	/**
	 * 每隔1秒过去一次服务器时间
	 * <p>
	 * 1.MediaType.TEXT_EVENT_STREAM表示Content-Type为text/event-stream，即SSE 
	 * 2.利用interval生成每秒一个数据的流
	 * 
	 * </p>
	 * @param serverRequest
	 * @return
	 */
	public Mono<ServerResponse> times(ServerRequest serverRequest){
		
		LOGGER.info("【响应式编程】请求服务器时间，每隔一秒推送===>");
		
		return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Flux.interval(Duration.ofSeconds(1)).map(s->new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date())),String.class);
	}
	
	
}
