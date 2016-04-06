package com.cfmtp.unittest;

import mp.CfmtpApplication;
import mp.consumer.com.cfc.facade.converter.impl.TradeMessageEntityConverter;
import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.data.TradeMessageEntity;
import mp.producer.com.cfp.facade.converter.Converter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CfmtpApplication.class)
@WebAppConfiguration
public class ConverterTests {

    Converter<TradeMessageEntity, TradeMessageData> converter = new TradeMessageEntityConverter();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TradeMessageEntityConverterTest() {
        TradeMessageEntity tradeMessageEntity = new TradeMessageEntity();
        tradeMessageEntity.setUserId("88889");
        tradeMessageEntity.setAmountBuy((long) 747.10);
        tradeMessageEntity.setCurrencyFrom("EUR");
        tradeMessageEntity.setCurrencyTo("GBP");
        tradeMessageEntity.setRate((long) 0.7435);
        tradeMessageEntity.setAmountSell(1000);
        tradeMessageEntity.setTimePlaced(new Date("24-JAN-15 10:27:44"));
        tradeMessageEntity.setOriginatingCountry("GB");

        TradeMessageData tradeMessageData = converter.convert(tradeMessageEntity);

        assertEquals(tradeMessageData.getUserId(), tradeMessageEntity.getUserId());
        assertEquals(tradeMessageData.getAmountBuy(), tradeMessageEntity.getAmountBuy());
        assertEquals(tradeMessageData.getCurrencyFrom(), tradeMessageEntity.getCurrencyFrom());
        assertEquals(tradeMessageData.getAmountSell(), tradeMessageEntity.getAmountSell());
        assertEquals(tradeMessageData.getAmountBuy(), tradeMessageEntity.getAmountBuy());
        assertEquals(tradeMessageData.getCurrencyTo(), tradeMessageEntity.getCurrencyTo());
        assertEquals(tradeMessageData.getOriginatingCountry(), tradeMessageEntity.getOriginatingCountry());
        assertEquals(tradeMessageData.getRate(), tradeMessageEntity.getRate());
        assertEquals(tradeMessageData.getTimePlaced(), tradeMessageEntity.getTimePlaced());

    }


}
