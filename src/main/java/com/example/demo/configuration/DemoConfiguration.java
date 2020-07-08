package com.example.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.jms.*;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.example.demo.pojo.Stats;
import com.example.demo.service.ActiveMqService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableJms
public class DemoConfiguration {

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(jacksonJmsMessageConverter());
		// You could still override some of Boot's default if necessary.
		return factory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
//		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//		converter.setTargetType(MessageType.TEXT);
//		converter.setTypeIdPropertyName("_type");
//		return converter;
//
//		return new MessageConverter() {
//
//			@Override public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
//				ObjectMapper objectMapper = new ObjectMapper();
//				try {
//					String value =objectMapper.writeValueAsString(object);
//					Message message = new Me
//					return
//				} catch (JsonProcessingException e) {
//					e.printStackTrace();
//				}
//				return "";
//			}
//
//			@Override public Object fromMessage(Message message) throws JMSException, MessageConversionException {
//
//				ObjectMapper objectMapper = new ObjectMapper();
//				try {
//					return objectMapper.readValue(message.getBody(String.class), Stats.class);
//				} catch (JsonProcessingException e) {
//					e.printStackTrace();
//				}
//				return "";
//			}
//		};

		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

		Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
		typeIdMappings.put("JMS_TYPE", Stats.class);

		converter.setTypeIdMappings(typeIdMappings);
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("JMS_TYPE");

		return converter;
	}

	@Bean
	public ActiveMqService activeMqService(JmsTemplate jmsTemplate){
		return new ActiveMqService(jmsTemplate,queue());
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("DemoQueue");
	}
}
