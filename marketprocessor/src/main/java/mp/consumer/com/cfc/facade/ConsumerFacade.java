package mp.consumer.com.cfc.facade;



import mp.producer.com.cfp.data.TradeMessageData;

import java.util.List;

public interface ConsumerFacade {

    List<TradeMessageData> findAllMessages();
    List<TradeMessageData> findTradeMessagesByCountryCode(String countryCode);
    List<TradeMessageData> findByCurrencyFromIgnoreCase(String currencyFrom);
    List<TradeMessageData> findByCurrencyToIgnoreCase(String currencyTo);
    TradeMessageData findFirstByOrderByTimePlacedDesc() throws NullPointerException;
    TradeMessageData getRecentMessageData();

}
