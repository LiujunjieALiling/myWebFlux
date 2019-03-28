package com.ljj.sample.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import reactor.core.publisher.Mono;

@Component
public class ActualHandle {

	
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
	
}
