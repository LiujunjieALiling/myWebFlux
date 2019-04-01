package com.ljj.sample.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;

import com.ljj.sample.model.Event;

/**
 * 1. 对于复杂的Bean只能通过Java Config的方式，这也是为什么Spring3之后官方推荐这种配置方式的原因
 * 2. MongoOpeations提供对MongoDB的操作方法，由Spring注入的mongo实例，直接可以使用。
 * 3. CommandLineRunner也是一个函数式接口，其实例可以用lambda表达；
 * 4. 如果有，先删除collection，生产环境慎用这种操作
 * 5. 创建一个记录个数为10的capped的collection，容量满了之后，新增的记录会覆盖最旧的
 * 
 * maxDocuments: 限制了记录条数
 * size: 限制容量且是必须定义的  (因为MongoDB不像关系型数据库有严格的列和字段大小定义)
 * @author 刘俊杰
 *
 */
@Configuration
public class CommandLineRunnerConfig{
	
	@Bean // 1
	public CommandLineRunner initData(MongoOperations mongo) {// 2
		
		return  (String ... args)->{ // 3
			
			mongo.dropCollection(Event.class); // 4
			mongo.createCollection(Event.class,CollectionOptions.empty().maxDocuments(200).size(100000).capped()); // 5
		};
		
	}
	
}
