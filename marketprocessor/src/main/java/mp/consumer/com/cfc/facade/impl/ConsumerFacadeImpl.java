package mp.consumer.com.cfc.facade.impl;

import mp.consumer.com.cfc.facade.ConsumerFacade;
import mp.consumer.com.cfc.service.ConsumerService;
import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.data.TradeMessageEntity;
import mp.producer.com.cfp.facade.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerFacadeImpl implements ConsumerFacade
{

	public static TradeMessageData recentTradeMessageData;

	@Autowired
	@Qualifier(value = "tradeMessageEntityConverter")
	Converter<TradeMessageEntity, TradeMessageData> converter;

	@Autowired
	ConsumerService consumerService;


	@Override
	public List<TradeMessageData> findAllMessages()
	{
		List<TradeMessageData> tradeMessageDatas = new ArrayList<TradeMessageData>();
		consumerService.findAllMessages().forEach(
				tradeMessageEntity -> tradeMessageDatas.add(converter.convert(tradeMessageEntity)));

		return tradeMessageDatas;
	}

	@Override
	public List<TradeMessageData> findTradeMessagesByCountryCode(String countryCode)
	{
		List<TradeMessageData> tradeMessageDatas = new ArrayList<TradeMessageData>();
		consumerService.findTradeMessagesByCountryCode(countryCode).forEach(
				tradeMessageEntity -> tradeMessageDatas.add(converter.convert(tradeMessageEntity)));

		return tradeMessageDatas;
	}

	@Override
	public List<TradeMessageData> findByCurrencyFromIgnoreCase(String currencyFrom)
	{
		List<TradeMessageData> tradeMessageDatas = new ArrayList<TradeMessageData>();
		consumerService.findByCurrencyFromIgnoreCase(currencyFrom).forEach(
				tradeMessageEntity -> tradeMessageDatas.add(converter.convert(tradeMessageEntity)));

		return tradeMessageDatas;
	}

	@Override
	public List<TradeMessageData> findByCurrencyToIgnoreCase(String currencyTo)
	{
		List<TradeMessageData> tradeMessageDatas = new ArrayList<TradeMessageData>();
		consumerService.findByCurrencyToIgnoreCase(currencyTo).forEach(
				tradeMessageEntity -> tradeMessageDatas.add(converter.convert(tradeMessageEntity)));

		return tradeMessageDatas;
	}

	@Override
	public TradeMessageData findFirstByOrderByTimePlacedDesc() throws NullPointerException
	{
		return converter.convert(consumerService.findFirstByOrderByTimePlacedDesc());
	}

	@Override
	public TradeMessageData getRecentMessageData() {
		return recentTradeMessageData;
	}


}
