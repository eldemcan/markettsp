package mp.producer.com.cfp.facade.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.data.TradeMessageEntity;
import mp.producer.com.cfp.facade.ProducerFacade;
import mp.producer.com.cfp.facade.converter.Converter;
import mp.producer.com.cfp.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



@Component
public class ProducerFacadeImpl implements ProducerFacade
{

	private static final Logger LOG = LoggerFactory.getLogger(ProducerFacadeImpl.class);

	@Autowired
	ProducerService producerService;

	@Autowired
	AmqpTemplate amqpTemplate;

	@Autowired
	@Qualifier(value = "tradeMessageDataConverter")
	Converter<TradeMessageData, TradeMessageEntity> converter;

	@Override
	public boolean sendTradeMessageToMq(final TradeMessageData tradeMessageData)
	{
		LOG.info("sending message to mq server");

		try {
			amqpTemplate.convertAndSend(tradeMessageData);
			return true;
		}
		catch (AmqpException exception){
			boolean result=producerService.insertTradeMessage(converter.convert(tradeMessageData));
			LOG.error(exception.getMessage());
			LOG.info("missing data recorded:"+result);
			return false;
		}
	}

}
