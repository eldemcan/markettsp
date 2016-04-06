package mp.producer.com.cfp.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Created by i308760 on 30/03/2016.
 */
public class TradeMessageData
{
	@NotNull
	@Length(max = 10)
	String userId;

	@NotNull
	@Length(min = 1, max = 3)
	String currencyFrom;

	@NotNull
	@Length(min = 1, max = 3)
	String currencyTo;

	@NotNull
	long amountSell;

	@NotNull
	long amountBuy;

	@NotNull
	long rate;

	@NotNull
	@JsonFormat(pattern = "dd-MMM-yy hh:mm:ss")
	Date timePlaced;

	@NotNull
	@JsonProperty(value = "originatingCountry")
	@Length(min = 1, max = 3,message = "country can't have more then 3 characters")
	String originatingCountry;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getCurrencyFrom()
	{
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom)
	{
		this.currencyFrom = currencyFrom;
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
