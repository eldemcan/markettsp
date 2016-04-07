package mp.producer.com.cfp.dao.impl;

import mp.producer.com.cfp.dao.TradeMessageDao;
import mp.producer.com.cfp.dao.repo.TradeMessageRepository;
import mp.producer.com.cfp.data.TradeMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;


@Component
public class TradeMessageDaoImpl implements TradeMessageDao {

    @Autowired
    @Qualifier("tradeMessageRepository")
    TradeMessageRepository tradeMessageRepository;

    @Override
    public boolean insertTradeMessage(TradeMessageEntity tradeMessageEntity) {
        TradeMessageEntity insertedItem=tradeMessageRepository.insert(tradeMessageEntity);
        return true?insertedItem!=null:false;
    }
}
