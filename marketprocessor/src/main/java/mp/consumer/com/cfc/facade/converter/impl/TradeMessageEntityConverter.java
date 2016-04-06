package mp.consumer.com.cfc.facade.converter.impl;

import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.data.TradeMessageEntity;
import mp.producer.com.cfp.facade.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TradeMessageEntityConverter implements Converter<TradeMessageEntity, TradeMessageData>
{
	@Override
	public TradeMessageData convert(TradeMessageEntity tradeMessageEntity)
	{

		return convert(tradeMessageEntity, new TradeMessageData());
	}

	@Override
	public TradeMessageData convert(TradeMessageEntity tradeMessageEntity, TradeMessageData tradeMessageData)
	{
		tradeMessageData.setUserId(tradeMessageEntity.getUserId());
		tradeMessageData.setAmountBuy(tradeMessageEntity.getAmountBuy());
		tradeMessageData.setCurrencyFrom(tradeMessageEntity.getCurrencyFrom());
		tradeMessageData.setCurrencyTo(tradeMessageEntity.getCurrencyTo());
		tradeMessageData.setRate(tradeMessageEntity.getRate());
		tradeMessageData.setAmountSell(tradeMessageEntity.getAmountSell());
		tradeMessageData.setTimePlaced(tradeMessageEntity.getTimePlaced());
		tradeMessageData.setOriginatingCountry(tradeMessageEntity.getOriginatingCountry());
		return tradeMessageData;
	}
}
