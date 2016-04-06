package mp.producer.com.cfp.facade.converter.impl;

import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.data.TradeMessageEntity;
import mp.producer.com.cfp.facade.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TradeMessageDataConverter implements Converter<TradeMessageData, TradeMessageEntity>
{

	@Override
	public TradeMessageEntity convert(TradeMessageData tradeMessageData)
	{
		return convert(tradeMessageData, new TradeMessageEntity());
	}

	@Override
	public TradeMessageEntity convert(TradeMessageData tradeMessageData, TradeMessageEntity tradeMessageEntity)
	{
		tradeMessageEntity.setUserId(tradeMessageData.getUserId());
		tradeMessageEntity.setAmountBuy(tradeMessageData.getAmountBuy());
		tradeMessageEntity.setCurrencyFrom(tradeMessageData.getCurrencyFrom());
		tradeMessageEntity.setCurrencyTo(tradeMessageData.getCurrencyTo());
		tradeMessageEntity.setRate(tradeMessageData.getRate());
		tradeMessageEntity.setAmountSell(tradeMessageData.getAmountSell());
		tradeMessageEntity.setTimePlaced(tradeMessageData.getTimePlaced());
		tradeMessageEntity.setOriginatingCountry(tradeMessageData.getOriginatingCountry());
		return tradeMessageEntity;
	}
}
