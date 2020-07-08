package com.example.demo.service;



import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.example.demo.pojo.Stats;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActiveMqService {
	private final JmsTemplate jmsTemplate;
	private final Queue queue;

	public void sendMessage(final Stats stats) {
		jmsTemplate.convertAndSend(queue, stats);
	}

}
