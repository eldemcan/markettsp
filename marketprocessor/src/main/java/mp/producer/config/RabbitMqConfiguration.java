package mp.producer.config;

import mp.consumer.com.cfc.event.IncomingTradeMessageEvent;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 * Created by i308760 on 30/03/2016.
 */

@Configuration
public class RabbitMqConfiguration
{
	@Autowired
	Environment environment;

	@Bean
	public ConnectionFactory connectionFactory()
	{
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(environment.getProperty("spring.rabbitmq.host"),
				Integer.parseInt(environment.getProperty("spring.rabbitmq.port")));
		connectionFactory.setUsername(environment.getProperty("spring.rabbitmq.username"));
		connectionFactory.setPassword(environment.getProperty("spring.rabbitmq.password"));
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin()
	{
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public AmqpTemplate amqpTemplate()
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setRoutingKey(environment.getProperty("spring.rabbitmq.queuename"));
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Queue myQueue()
	{
		return new Queue(environment.getProperty("spring.rabbitmq.queuename"));
	}

	@Bean(name = "incomingTradeMessageEvent")
	public IncomingTradeMessageEvent incomingTradeMessageEvent(){
		return new IncomingTradeMessageEvent();
	}

	@Bean
	public MessageConverter messageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public SimpleMessageListenerContainer listenerContainer() {
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory());
		listenerContainer.setQueues(myQueue());
		listenerContainer.setMessageConverter(new Jackson2JsonMessageConverter());
		listenerContainer.setMessageListener(incomingTradeMessageEvent());
		listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return listenerContainer;
	}



}
