package mp.producer.com.cfp.facade;

import mp.producer.com.cfp.data.TradeMessageData;

/**
 * Created by i308760 on 30/03/2016.
 */
public interface ProducerFacade {
    boolean  sendTradeMessageToMq(final TradeMessageData tradeMessageData);
}
