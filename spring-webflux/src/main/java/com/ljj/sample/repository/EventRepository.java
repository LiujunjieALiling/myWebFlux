package com.ljj.sample.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import com.ljj.sample.model.Event;

import reactor.core.publisher.Flux;

public interface EventRepository  extends ReactiveMongoRepository<Event, Long> {


	/**
	 * 1. @Tailable注解的作用类似与linux命令的tail命令，被注解的方法将发送无限流，需要注解在返回值为Flux这样的多个元素的Publisher的方法上。
	 * 2. findAll()是想要的方法，但是在ReactiveMongoRepository中我们够不着，所以使用findBy()代替
	 * @return
	 */
	@Tailable
	Flux<Event> findBy();

	
	
}
