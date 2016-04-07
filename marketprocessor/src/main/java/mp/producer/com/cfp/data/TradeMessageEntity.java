package mp.producer.com.cfp.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;



@Document
public class TradeMessageEntity
{
	@Id
	String userId;
	@Field
	String currencyFrom;
	@Field
	String currencyTo;
	@Field
	long amountSell;
	@Field
	long amountBuy;
	@Field
	long rate;
	@Field
	Date timePlaced;
	@Field
	String originatingCountry;

	public String getCurrencyFrom()
	{
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom)
	{
		this.currencyFrom = currencyFrom;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getCurrencyTo()
	{
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo)
	{
		this.currencyTo = currencyTo;
	}

	public long getAmountSell()
	{
		return amountSell;
	}

	public void setAmountSell(long amountSell)
	{
		this.amountSell = amountSell;
	}

	public long getAmountBuy()
	{
		return amountBuy;
	}

	public void setAmountBuy(long amountBuy)
	{
		this.amountBuy = amountBuy;
	}

	public long getRate()
	{
		return rate;
	}

	public void setRate(long rate)
	{
		this.rate = rate;
	}

	public Date getTimePlaced()
	{
		return timePlaced;
	}

	public void setTimePlaced(Date timePlaced)
	{
		this.timePlaced = timePlaced;
	}

	public String getOriginatingCountry()
	{
		return originatingCountry;
	}

	public void setOriginatingCountry(String originatingCountry)
	{
		this.originatingCountry = originatingCountry;
	}
}
