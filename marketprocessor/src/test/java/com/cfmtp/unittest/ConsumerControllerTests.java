package com.cfmtp.unittest;

import mp.CfmtpApplication;
import mp.consumer.com.cfc.controller.ConsumerController;
import mp.consumer.com.cfc.facade.ConsumerFacade;
import mp.producer.com.cfp.data.TradeMessageData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CfmtpApplication.class)
@WebAppConfiguration
public class ConsumerControllerTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ConsumerControllerTests.class);
	MockMvc mockMvc;

	@Mock
	ConsumerFacade consumerFacade;

    @InjectMocks
	ConsumerController consumerController;


	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(consumerController).build();
	}

	@Test
    public void PoolRecentMessage() throws Exception {

        when(consumerFacade.getRecentMessageData()).thenReturn(tradeMessageDataFactory());
        mockMvc.perform(get("/consumer/poolrecent").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId").exists());
    }


	@Test
	public void PoolAllMessages() throws Exception {

		TradeMessageData tradeMessageData = new TradeMessageData();
		tradeMessageData.setUserId("88889");
		tradeMessageData.setAmountBuy((long) 747.10);
		tradeMessageData.setCurrencyFrom("EUR");
		tradeMessageData.setCurrencyTo("GBP");
		tradeMessageData.setRate((long) 0.7435);
		tradeMessageData.setAmountSell(1000);
		tradeMessageData.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData.setOriginatingCountry("GB");

		TradeMessageData tradeMessageData1 = new TradeMessageData();
		tradeMessageData1.setUserId("1234");
		tradeMessageData1.setAmountBuy((long) 747.10);
		tradeMessageData1.setCurrencyFrom("EUR");
		tradeMessageData1.setCurrencyTo("GBP");
		tradeMessageData1.setRate((long) 0.7435);
		tradeMessageData1.setAmountSell(1000);
		tradeMessageData1.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData1.setOriginatingCountry("IE");

		List<TradeMessageData> tradeMessageDataList=new ArrayList<TradeMessageData>();
		tradeMessageDataList.add(tradeMessageData);
		tradeMessageDataList.add(tradeMessageData1);

		assertTrue(tradeMessageDataList.size()==2);

		when(consumerFacade.findAllMessages()).thenReturn(tradeMessageDataList);
		MvcResult mvcResult =mockMvc.perform(get("/consumer/poolall"))
				.andExpect(request().asyncStarted())
				.andDo(print())
				.andReturn();

		mockMvc.perform(asyncDispatch(mvcResult))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.*",hasSize(2)));
	}


	@Test
	public void PoolMessagesByCountry() throws Exception {

		TradeMessageData tradeMessageData = new TradeMessageData();
		tradeMessageData.setUserId("88889");
		tradeMessageData.setAmountBuy((long) 747.10);
		tradeMessageData.setCurrencyFrom("EUR");
		tradeMessageData.setCurrencyTo("GBP");
		tradeMessageData.setRate((long) 0.7435);
		tradeMessageData.setAmountSell(1000);
		tradeMessageData.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData.setOriginatingCountry("IE");

		TradeMessageData tradeMessageData1 = new TradeMessageData();
		tradeMessageData1.setUserId("1234");
		tradeMessageData1.setAmountBuy((long) 747.10);
		tradeMessageData1.setCurrencyFrom("EUR");
		tradeMessageData1.setCurrencyTo("GBP");
		tradeMessageData1.setRate((long) 0.7435);
		tradeMessageData1.setAmountSell(1000);
		tradeMessageData1.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData1.setOriginatingCountry("IE");

		List<TradeMessageData> tradeMessageDataList=new ArrayList<TradeMessageData>();
		tradeMessageDataList.add(tradeMessageData);
		tradeMessageDataList.add(tradeMessageData1);

		assertTrue(tradeMessageDataList.size()==2);

		when(consumerFacade.findTradeMessagesByCountryCode("IE")).thenReturn(tradeMessageDataList);
		MvcResult mvcResult =mockMvc.perform(get("/consumer/pool/{country}","IE"))
				.andExpect(request().asyncStarted())
				.andDo(print())
				.andReturn();

		mockMvc.perform(asyncDispatch(mvcResult))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.*",hasSize(2)));
	}




	//TODO: generate random data within constrains
	TradeMessageData tradeMessageDataFactory()
	{
		TradeMessageData tradeMessageData = new TradeMessageData();
		tradeMessageData.setUserId("88889");
		tradeMessageData.setAmountBuy((long) 747.10);
		tradeMessageData.setCurrencyFrom("EUR");
		tradeMessageData.setCurrencyTo("GBP");
		tradeMessageData.setRate((long) 0.7435);
		tradeMessageData.setAmountSell(1000);
		tradeMessageData.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData.setOriginatingCountry("GBBBB");

		return tradeMessageData;
	}


}
