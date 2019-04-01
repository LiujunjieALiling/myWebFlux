package com.ljj.sample.webclient;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.ljj.sample.model.User;

public class WebclientUserTest {

	
    @Test
    public void webClientTestGetUser() throws InterruptedException {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090").build();   // 1  使用builder创建webClient对象；
        webClient.get().uri("/user/findAll") // 2  GET
        		.accept(MediaType.APPLICATION_STREAM_JSON) // 3  配置请求Header：Content-Type: application/stream+json
                .exchange()// 4 获取response信息，返回值为ClientResponse，retrive()可以看做是exchange()方法的“快捷版”；
                .flatMapMany(response -> response.bodyToFlux(User.class))  // 5  使用flatMapMany来将ClientResponse映射为Flux；
                .doOnNext(System.out::println)    // 6 只读地peek每个元素，然后打印出来，它并不是subscribe，所以不会触发流
                .blockLast();// 7 blockLast方法，顾名思义，在收到最后一个元素前会阻塞，响应式业务场景中慎用
        
    }
    
    
    
    
    
    
    
    
}
