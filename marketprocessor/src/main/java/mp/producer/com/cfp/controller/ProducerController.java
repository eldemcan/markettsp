package mp.producer.com.cfp.controller;

import mp.producer.com.cfp.data.TradeMessageData;
import mp.producer.com.cfp.facade.ProducerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class ProducerController
{
	@Autowired
	ProducerFacade consumerFacade;

	private static final Logger LOG = LoggerFactory.getLogger(ProducerController.class);

	@RequestMapping("/insert")
	public String insertTradeMessage(@Valid @RequestBody TradeMessageData tradeMessageData, BindingResult bindingResult)
	{
		LOG.info("incoming request");
		LOG.info(tradeMessageData.getUserId());
		if (bindingResult.hasErrors())
		{
			LOG.error("Wrong input");
			return String.valueOf(false);
		}
		else
		{
			return String.valueOf(consumerFacade.sendTradeMessageToMq(tradeMessageData));
		}
	}

}
