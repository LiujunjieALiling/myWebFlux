package com.ljj.sample.route;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ljj.sample.controller.ActualHandle;


@Configuration
public class RouteConfig {

	@Autowired
	private ActualHandle actualHandle;
	
	
	@Bean
	public  RouterFunction<ServerResponse> timeRouter(){
		
		return route(GET("/time"),request->actualHandle.time(request)).andRoute(GET("/date"), actualHandle::date);
	}
	
	
	
}
