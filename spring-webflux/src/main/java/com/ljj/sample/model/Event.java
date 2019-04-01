package com.ljj.sample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event")// 1. 指定collection名为event；
public class Event {

    @Id
    private Long id;    // 2. 使用表示时间的long型数据作为ID
    private String message;
    
}
