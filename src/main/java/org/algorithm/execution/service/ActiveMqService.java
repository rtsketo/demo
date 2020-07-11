package org.algorithm.execution.service;

import javax.jms.Queue;

import org.algorithm.execution.pojo.Stats;
import org.springframework.jms.core.JmsTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ActiveMqService {
	private final JmsTemplate jmsTemplate;
	private final Queue queue;

	public void sendMessage(final Stats stats) {
		log.debug("Sending " + stats + " to " + queue);
		jmsTemplate.convertAndSend(queue, stats);
		log.debug("Sent " + stats + " to " + queue);
	}

}
