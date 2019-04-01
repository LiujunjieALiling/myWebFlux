package com.ljj.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljj.sample.model.Event;
import com.ljj.sample.repository.EventRepository;

import reactor.core.publisher.Flux;


@Service
public class EventService {

	@Autowired
	private EventRepository  eventRepository;
	
	
	public Flux<Event>  pushEvents(Flux<Event> events){
		
		return eventRepository.insert(events);
	}
	
	
	public Flux<Event>  getEvents(){
		
		return eventRepository.findBy();
	}
	
}
