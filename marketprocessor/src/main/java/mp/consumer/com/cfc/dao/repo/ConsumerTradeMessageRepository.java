package mp.consumer.com.cfc.dao.repo;


import mp.producer.com.cfp.data.TradeMessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "consumerTradeMessageRepository")
public interface ConsumerTradeMessageRepository extends MongoRepository<TradeMessageEntity,String> {
    List<TradeMessageEntity> findByOriginatingCountryIgnoreCase(String countryCode);
    List<TradeMessageEntity> findByCurrencyFromIgnoreCase(String currencyFrom);
    List<TradeMessageEntity> findByCurrencyToIgnoreCase(String currencyTo);
    TradeMessageEntity findFirstByOrderByTimePlacedDesc();

}
