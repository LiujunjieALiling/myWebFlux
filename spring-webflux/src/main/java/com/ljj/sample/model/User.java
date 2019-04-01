package com.ljj.sample.model;

import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

	@Id
	private String id;
	
	@Indexed(unique = true) // 注解属性username为索引，并且不能重复
	private String name;
	
	private String phone;
	
	private String email;
	
	private Date birthday;
	
	
}
