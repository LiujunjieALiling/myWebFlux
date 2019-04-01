package com.ljj.sample.webclient;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebclientTimeTest {

	@Test
	public void webClientTestTime() throws InterruptedException {
		WebClient webClient = WebClient.create("http://localhost:8090"); // 1 创建WebClient对象并指定baseUrl；
		Mono<String> resp = webClient.get().uri("/time") // 2 GET
				.retrieve() // 3 异步地获取response信息
				.bodyToMono(String.class); // 4 将response body解析为字符串
		resp.subscribe(System.out::println); // 5 打印
		TimeUnit.SECONDS.sleep(1); // 6 由于是异步的，我们将测试线程sleep 1秒确保拿到response，也可以像前边的例子一样用CountDownLatch
	}

	@Test
	public void webClientTestTimes() {

		WebClient webClient = WebClient.create("http://localhost:8090");  // 1,使用create方式创建 webClient
		webClient.get()
		.uri("/times")
		.accept(MediaType.TEXT_EVENT_STREAM)  //请求头    添加文本流
		.retrieve() // 异步获取响应
		.bodyToFlux(String.class) // 响应转换为String字符串
		.doOnNext(System.out::println)//  等同于log()
		.take(10)// 获取前10个
		.blockLast();// 获取最后一个之前一直阻塞
		
	}

}
