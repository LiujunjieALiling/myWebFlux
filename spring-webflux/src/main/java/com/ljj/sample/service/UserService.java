package com.ljj.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljj.sample.model.User;
import com.ljj.sample.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	
	/**
	 *  保存或更新。
	 *  
	 *  1.onErrorResume进行错误处理
	 *  2.找到username重复的记录
	 *  3.拿到ID从而进行更新而不是创建
	 *  4.由于函数式为User -> Publisher，所以用flatMap
	 * @param user
	 * @return
	 */
	public Mono<User> save(User user) {

		return userRepository.save(user).onErrorResume(e -> userRepository.findByName(user.getName())).flatMap(origin ->

		{
			user.setId(origin.getId());
			return userRepository.save(user);
		});

	}

	public Flux<User> findAll() {

		return userRepository.findAll();
	}

	public Mono<User> findByName(String name) {

		return userRepository.findByName(name);
	}

	public Mono<Long> deleteByName(String name) {

		return userRepository.deleteByName(name);
	}

}
