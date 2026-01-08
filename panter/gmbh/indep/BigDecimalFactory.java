package panter.gmbh.indep;

import java.math.BigDecimal;

public class BigDecimalFactory
{
	public static BigDecimal BigDecimal3Round(double Value)
	{
		BigDecimal oRueck = new BigDecimal(Value);
		oRueck=oRueck.setScale(3,BigDecimal.ROUND_HALF_UP);
		return oRueck;
	}

	public static BigDecimal BigDecimal3Round(Double Value)
	{
		if (Value==null)
			return null;
		
		BigDecimal oRueck = new BigDecimal(Value.doubleValue());
		oRueck=oRueck.setScale(3,BigDecimal.ROUND_HALF_UP);
		return oRueck;
	}

}
