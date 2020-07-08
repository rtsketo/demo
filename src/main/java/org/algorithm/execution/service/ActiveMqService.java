package org.algorithm.execution.service;



import javax.jms.Queue;

import org.algorithm.execution.pojo.Stats;
import org.springframework.jms.core.JmsTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActiveMqService {
	private final JmsTemplate jmsTemplate;
	private final Queue queue;

	public void sendMessage(final Stats stats) {
		jmsTemplate.convertAndSend(queue, stats);
	}

}
