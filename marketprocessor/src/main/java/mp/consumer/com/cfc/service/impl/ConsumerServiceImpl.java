package mp.consumer.com.cfc.service.impl;


import com.mongodb.MongoException;
import mp.consumer.com.cfc.dao.ConsumerTradeMessageDao;
import mp.consumer.com.cfc.service.ConsumerService;
import mp.producer.com.cfp.data.TradeMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService
{

	@Autowired
	ConsumerTradeMessageDao consumerTradeMessageDao;

	@Override
	public List<TradeMessageEntity> findAllMessages()
	{
		return consumerTradeMessageDao.findAllMessages();
	}

	@Override
	public List<TradeMessageEntity> findTradeMessagesByCountryCode(String countryCode)
	{
		return consumerTradeMessageDao.findTradeMessagesByCountryCode(countryCode);
	}

	@Override
	public List<TradeMessageEntity> findByCurrencyFromIgnoreCase(String currencyFrom)
	{
		return consumerTradeMessageDao.findByCurrencyFromIgnoreCase(currencyFrom);
	}

	@Override
	public List<TradeMessageEntity> findByCurrencyToIgnoreCase(String currencyTo)
	{
		return consumerTradeMessageDao.findByCurrencyToIgnoreCase(currencyTo);
	}

	@Override
	public TradeMessageEntity findFirstByOrderByTimePlacedDesc() throws NullPointerException
	{
		return consumerTradeMessageDao.findFirstByOrderByTimePlacedDesc();
	}

	@Override
	public boolean insertTradeMessage(TradeMessageEntity tradeMessageEntity) throws MongoException {
		return consumerTradeMessageDao.insertMessage(tradeMessageEntity);
	}
}
