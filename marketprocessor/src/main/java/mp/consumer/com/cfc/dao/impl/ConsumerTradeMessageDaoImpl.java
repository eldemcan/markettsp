package mp.consumer.com.cfc.dao.impl;

import mp.consumer.com.cfc.dao.ConsumerTradeMessageDao;
import mp.consumer.com.cfc.dao.repo.ConsumerTradeMessageRepository;
import mp.producer.com.cfp.data.TradeMessageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

@Component
public class ConsumerTradeMessageDaoImpl implements ConsumerTradeMessageDao
{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("consumerTradeMessageRepository")
	ConsumerTradeMessageRepository consumerTradeMessageRepository;

	@Override
	public List<TradeMessageEntity> findAllMessages()
	{
		return consumerTradeMessageRepository.findAll().isEmpty() ? Collections.emptyList() : consumerTradeMessageRepository
				.findAll();
	}

	@Override
	public boolean insertMessage(TradeMessageEntity tradeMessageEntity) {
		return true?consumerTradeMessageRepository.insert(tradeMessageEntity)!=null:false;
	}

	@Override
	public List<TradeMessageEntity> findTradeMessagesByCountryCode(String countryCode)
	{
		return consumerTradeMessageRepository.findByOriginatingCountryIgnoreCase(countryCode).isEmpty() ? Collections.emptyList()
				: consumerTradeMessageRepository.findByOriginatingCountryIgnoreCase(countryCode);
	}

	@Override
	public List<TradeMessageEntity> findByCurrencyFromIgnoreCase(String currencyFrom)
	{
		return consumerTradeMessageRepository.findByCurrencyFromIgnoreCase(currencyFrom).isEmpty() ? Collections.emptyList()
				: consumerTradeMessageRepository.findByCurrencyFromIgnoreCase(currencyFrom);
	}

	@Override
	public List<TradeMessageEntity> findByCurrencyToIgnoreCase(String currencyTo)
	{
		return consumerTradeMessageRepository.findByCurrencyToIgnoreCase(currencyTo).isEmpty() ? Collections.emptyList()
				: consumerTradeMessageRepository.findByCurrencyToIgnoreCase(currencyTo);
	}

	@Override
	public TradeMessageEntity findFirstByOrderByTimePlacedDesc() throws NullPointerException
	{
		TradeMessageEntity tradeMessageEntity = consumerTradeMessageRepository.findFirstByOrderByTimePlacedDesc();
		if (tradeMessageEntity == null)
			throw new NullPointerException();
		else
			return tradeMessageEntity;
	}
}
