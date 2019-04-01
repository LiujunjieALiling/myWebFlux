package com.ljj.sample.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.ljj.sample.model.User;

import reactor.core.publisher.Mono;

/**
 * mongodb
 * 1.ReactiveCrudRepository的泛型分别是User和ID的类型；
 * 2.ReactiveCrudRepository已经提供了基本的增删改查的方法，根据业务需要，我们增加四个方法
 * @author 刘俊杰
 *
 */
public interface UserRepository extends ReactiveCrudRepository<User, String>{

    Mono<User> findByName(String name);
    Mono<Long> deleteByName(String name);
    
}
