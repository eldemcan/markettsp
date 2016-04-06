package mp.consumer.com.cfc.controller;

import mp.consumer.com.cfc.facade.ConsumerFacade;
import mp.consumer.com.cfc.facade.impl.ConsumerFacadeImpl;
import mp.producer.com.cfp.data.TradeMessageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController
{

	@Autowired
	ConsumerFacade consumerFacade;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String testMethod()
	{
		return "Test OK";
	}

	@RequestMapping(value = "/poolrecent", method = RequestMethod.GET)
	@ResponseBody
	public TradeMessageData poolRecentMessage()
	{
		return consumerFacade.getRecentMessageData();
	}

	@RequestMapping(value = "/poolall", method = RequestMethod.GET)
	@ResponseBody
	//TODO: pagination in futurue for large volume of data
	public DeferredResult<List<TradeMessageData>> poolAllMessages()
	{
		DeferredResult<List<TradeMessageData>> results = new DeferredResult<List<TradeMessageData>>();
		results.setResult(consumerFacade.findAllMessages());
		return results;
	}

	@RequestMapping("/pool/{country}")
	@ResponseBody
	public DeferredResult<List<TradeMessageData>> poolMessagesCountry(@PathVariable String country)
	{
		DeferredResult<List<TradeMessageData>> results = new DeferredResult<List<TradeMessageData>>();
		results.setResult(consumerFacade.findTradeMessagesByCountryCode(country));
		return results;
	}


}
