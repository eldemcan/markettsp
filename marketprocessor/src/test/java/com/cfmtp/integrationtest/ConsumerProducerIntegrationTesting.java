package com.cfmtp.integrationtest;

import mp.CfmtpApplication;
import mp.consumer.com.cfc.dao.repo.ConsumerTradeMessageRepository;
import mp.consumer.com.cfc.facade.ConsumerFacade;
import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.data.TradeMessageEntity;
import mp.producer.com.cfp.facade.ProducerFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CfmtpApplication.class)
@WebIntegrationTest("server.port:0")
public class ConsumerProducerIntegrationTesting
{
	private static final Logger LOG = LoggerFactory.getLogger(ConsumerProducerIntegrationTesting.class);

	@Autowired
	ProducerFacade producerFacade;

	@Autowired
	ConsumerFacade consumerFacade;

	@Autowired
	ConsumerTradeMessageRepository consumerTradeMessageRepository;

	@Test
	public void sendingReceiving() throws InterruptedException
	{
		TradeMessageData tradeMessageData = new TradeMessageData();
		tradeMessageData.setUserId("50697");
		tradeMessageData.setAmountBuy((long) 747.10);
		tradeMessageData.setCurrencyFrom("EUR");
		tradeMessageData.setCurrencyTo("GBP");
		tradeMessageData.setRate((long) 0.7435);
		tradeMessageData.setAmountSell(1000);
		tradeMessageData.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData.setOriginatingCountry("IE");

		//delete inserted message first
		synchronized (consumerTradeMessageRepository)
		{
			consumerTradeMessageRepository.delete("50697");
		}

		LOG.info("deleting entity");
		producerFacade.sendTradeMessageToMq(tradeMessageData);
		Thread.sleep((long) 4000.0);
		TradeMessageData receivedtradeMessageData = consumerFacade.getRecentMessageData();
		assertEquals(tradeMessageData.getUserId(), receivedtradeMessageData.getUserId());

	}

	@Test
	public void testfindMessageByCountryName()
	{

		String country = "TestCountry" + System.currentTimeMillis();

		TradeMessageEntity tradeMessageEntity = new TradeMessageEntity();
		tradeMessageEntity.setUserId("10293");
		tradeMessageEntity.setAmountBuy((long) 747.10);
		tradeMessageEntity.setCurrencyFrom("EUR");
		tradeMessageEntity.setCurrencyTo("GBP");
		tradeMessageEntity.setRate((long) 0.7435);
		tradeMessageEntity.setAmountSell(1000);
		tradeMessageEntity.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageEntity.setOriginatingCountry(country);

		synchronized (consumerTradeMessageRepository)
		{
			consumerTradeMessageRepository.delete("10293");
		}
		consumerTradeMessageRepository.insert(tradeMessageEntity);
		List<TradeMessageData> tradeMessageDataList = consumerFacade.findTradeMessagesByCountryCode(country);
		assertTrue(tradeMessageDataList.size() == 1);
		assertTrue(tradeMessageDataList.get(0).getUserId().equals(tradeMessageEntity.getUserId()));

	}

	@Test
	public void testfindAllMessages()
	{

		TradeMessageEntity tradeMessageEntity = new TradeMessageEntity();
		tradeMessageEntity.setUserId("49586");
		tradeMessageEntity.setAmountBuy((long) 747.10);
		tradeMessageEntity.setCurrencyFrom("EUR");
		tradeMessageEntity.setCurrencyTo("GBP");
		tradeMessageEntity.setRate((long) 0.7435);
		tradeMessageEntity.setAmountSell(1000);
		tradeMessageEntity.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageEntity.setOriginatingCountry("IE");

		synchronized (consumerTradeMessageRepository)
		{
			consumerTradeMessageRepository.delete("49586");
		}
		consumerTradeMessageRepository.insert(tradeMessageEntity);
		List<TradeMessageData> tradeMessageDataList = consumerFacade.findAllMessages();
		assertThat(tradeMessageDataList.size(), greaterThanOrEqualTo(1));
		List<String> userIdList = new ArrayList<String>();
		tradeMessageDataList.forEach(item -> userIdList.add(item.getUserId()));
		assertThat(userIdList, hasItem(tradeMessageEntity.getUserId()));
	}

}
