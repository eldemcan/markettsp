package mp.consumer.com.cfc.dao;

import mp.producer.com.cfp.data.TradeMessageEntity;

import java.util.List;

public interface ConsumerTradeMessageDao {
      List<TradeMessageEntity> findAllMessages();
      boolean insertMessage(TradeMessageEntity tradeMessageEntity);
    List<TradeMessageEntity> findTradeMessagesByCountryCode(String countryCode);
    List<TradeMessageEntity> findByCurrencyFromIgnoreCase(String currencyFrom);
    List<TradeMessageEntity> findByCurrencyToIgnoreCase(String currencyTo);
    TradeMessageEntity findFirstByOrderByTimePlacedDesc() throws NullPointerException;

}
