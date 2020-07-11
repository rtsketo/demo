package org.algorithm.execution.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import javax.jms.Queue;

import org.algorithm.execution.pojo.Stats;
import org.algorithm.execution.service.ActiveMqService;
import org.algorithm.execution.service.AlgorithmService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class AlgorithmConfiguration {

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		//TODO check conversion with objectMapper -> JsonMessageConverter
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

		Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
		typeIdMappings.put("JMS_TYPE", Stats.class);

		converter.setTypeIdMappings(typeIdMappings);
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("JMS_TYPE");

		return converter;
	}

	@Bean
	public ActiveMqService activeMqService(JmsTemplate jmsTemplate) {
		return new ActiveMqService(jmsTemplate, queue());
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("DemoQueue");
	}

	@Bean
	public AlgorithmService algorithmService(JmsTemplate jmsTemplate) {
		return new AlgorithmService(activeMqService(jmsTemplate), Executors.newCachedThreadPool());
	}
}
