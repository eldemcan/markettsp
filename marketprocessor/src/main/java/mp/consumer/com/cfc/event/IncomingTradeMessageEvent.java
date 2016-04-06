package mp.consumer.com.cfc.event;

import com.mongodb.MongoException;
import mp.consumer.com.cfc.facade.impl.ConsumerFacadeImpl;
import mp.consumer.com.cfc.service.ConsumerService;
import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.data.TradeMessageEntity;
import mp.producer.com.cfp.facade.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "incomingTradeMessageEvent")
public class IncomingTradeMessageEvent implements MessageListener
{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Converter<TradeMessageData, TradeMessageEntity> converter;

	@Autowired
	private ConsumerService consumerService;

	private MessageConverter messageConverter=new Jackson2JsonMessageConverter();

	@Override
	public void onMessage(Message message)
	{
		LOG.info("message incoming... ");
		TradeMessageData tradeMessageData = (TradeMessageData) messageConverter.fromMessage(message);
		LOG.info("Saving incoming message");
		try {
			consumerService.insertTradeMessage(converter.convert(tradeMessageData));
			ConsumerFacadeImpl.recentTradeMessageData = tradeMessageData;
		}
		catch (MongoException exception){
			LOG.error(exception.getMessage());
		}
	}

}
