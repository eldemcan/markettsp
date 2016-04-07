package mp.producer.com.cfp.service;

import mp.producer.com.cfp.data.TradeMessageEntity;

public interface ProducerService {
    boolean insertTradeMessage(TradeMessageEntity tradeMessageEntity);
}
