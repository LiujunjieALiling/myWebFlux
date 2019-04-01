package com.ljj.sample.webclient;

import java.time.Duration;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.ljj.sample.model.Event;

import reactor.core.publisher.Flux;

public class WebclientEventTest {


	/**
	 * 1. 声明速度为每秒一个Event元素的数据流，不加take的话表示无限个元素的数据流
	 * 2. 声明请求体的数据格式为application/stream+json；
	 * 3. body方法设置请求体的数据
	 */
	@Test
	public void testEvent() {
		
		// 准备数据
		Flux<Event> eventFlux=Flux.interval(Duration.ofSeconds(1)).map(s->new Event(System.currentTimeMillis(), "message-"+1)).take(5);
		
		
		WebClient  webClient=WebClient.builder().baseUrl("http://localhost:8090").build();
		
		webClient
		.post().uri("/event/push")
		.contentType(MediaType.APPLICATION_STREAM_JSON)
		.body(eventFlux,Event.class)
		.retrieve()
		.bodyToMono(Void.class)
		.block();
		
	}
	
}
