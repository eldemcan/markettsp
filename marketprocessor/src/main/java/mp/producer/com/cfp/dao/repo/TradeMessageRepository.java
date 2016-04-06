package mp.producer.com.cfp.dao.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import mp.producer.com.cfp.data.TradeMessageEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by i308760 on 30/03/2016.
 */

@Repository(value = "tradeMessageRepository")
public interface TradeMessageRepository extends MongoRepository<TradeMessageEntity,String> {
}
