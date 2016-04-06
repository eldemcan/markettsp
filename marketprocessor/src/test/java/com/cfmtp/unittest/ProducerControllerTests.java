package com.cfmtp.unittest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mp.CfmtpApplication;
import mp.producer.com.cfp.controller.ProducerController;
import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.facade.ProducerFacade;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Date;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CfmtpApplication.class)
@WebAppConfiguration
public class ProducerControllerTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ProducerControllerTests.class);

	MockMvc mockMvc;

	@InjectMocks
	ProducerController producerController;

	@Mock
	ProducerFacade producerFacade;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(producerController).build();

	}

	@Test
	public void TestFacadeStub() throws Exception{
		TradeMessageData tradeMessageData=new TradeMessageData();
		tradeMessageData.setUserId("88889");
		tradeMessageData.setAmountBuy((long) 747.10);
		tradeMessageData.setCurrencyFrom("EUR");
		tradeMessageData.setCurrencyTo("GBP");
		tradeMessageData.setRate((long) 0.7435);
		tradeMessageData.setAmountSell(1000);
		tradeMessageData.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData.setOriginatingCountry("GB");

		when(producerFacade.sendTradeMessageToMq(tradeMessageData)).thenReturn(true);

		assertTrue(producerFacade.sendTradeMessageToMq(tradeMessageData));


	}

	@Test
	public void ProducerControllerPostTest() throws Exception {

		Gson gson = new GsonBuilder()
				.setDateFormat("dd-MMM-yy hh:mm:ss").create();


		TradeMessageData tradeMessageData=new TradeMessageData();
		tradeMessageData.setUserId("88889");
		tradeMessageData.setAmountBuy((long) 747.10);
		tradeMessageData.setCurrencyFrom("EUR");
		tradeMessageData.setCurrencyTo("GBP");
		tradeMessageData.setRate((long) 0.7435);
		tradeMessageData.setAmountSell(1000);
		tradeMessageData.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData.setOriginatingCountry("GB");

		when(producerFacade.sendTradeMessageToMq(tradeMessageData)).thenReturn(true);

		assertTrue(producerFacade.sendTradeMessageToMq(tradeMessageData));


		String jsonRequest=gson.toJson(tradeMessageData);

		LOG.info(jsonRequest);
        mockMvc.perform(
                post("/api/insert/").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonRequest))
						.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString().contains("true");
        //.andExpect(content().string("true"));
	}

	@Test
	public void ProducerControllerPostTestFalse() throws Exception {

		Gson gson = new GsonBuilder()
				.setDateFormat("dd-MMM-yy hh:mm:ss").create();


		TradeMessageData tradeMessageData=new TradeMessageData();
		tradeMessageData.setUserId("88889");
		tradeMessageData.setAmountBuy((long) 747.10);
		tradeMessageData.setCurrencyFrom("EUR");
		tradeMessageData.setCurrencyTo("GBP");
		tradeMessageData.setRate((long) 0.7435);
		tradeMessageData.setAmountSell(1000);
		tradeMessageData.setTimePlaced(new Date("24-JAN-15 10:27:44"));
		tradeMessageData.setOriginatingCountry("GBBBB");

		when(producerFacade.sendTradeMessageToMq(tradeMessageData)).thenReturn(true);

		assertTrue(producerFacade.sendTradeMessageToMq(tradeMessageData));


		String jsonRequest=gson.toJson(tradeMessageData);

		LOG.info(jsonRequest);
		mockMvc.perform(
				post("/api/insert/").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonRequest))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString().contains("false");
	}

}
