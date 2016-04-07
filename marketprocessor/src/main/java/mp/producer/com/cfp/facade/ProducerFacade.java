package mp.producer.com.cfp.facade;

import mp.producer.com.cfp.data.TradeMessageData;

public interface ProducerFacade {
    boolean  sendTradeMessageToMq(final TradeMessageData tradeMessageData);
}
