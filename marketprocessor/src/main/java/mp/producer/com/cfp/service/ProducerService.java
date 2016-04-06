package mp.producer.com.cfp.service;

import mp.producer.com.cfp.data.TradeMessageEntity;

/**
 * Created by i308760 on 30/03/2016.
 */
public interface ProducerService {
    boolean insertTradeMessage(TradeMessageEntity tradeMessageEntity);
}
