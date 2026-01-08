package panter.gmbh.indep.myVectors;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.indep.BigDecimalFactory;

public class VectorBigDecimal extends Vector<BigDecimal>
{

	public VectorBigDecimal()
	{
		super();
	}


	public BigDecimal get_Summe()
	{
		BigDecimal bdRueck = BigDecimalFactory.BigDecimal3Round(0);
		boolean    bMindestens_ein_wert = false;
		
//		bibALL.System_println("--->Start Summation");
//		bibALL.System_println("--->"+bdRueck);
		
		for (BigDecimal bd: this)
		{
			
			if (bd != null)
			{
			    BigDecimal bdRound = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
				
				bMindestens_ein_wert = true;
				bdRueck = bdRueck.add(bdRound);
			}
		}

		return bMindestens_ein_wert?bdRueck:null;
	}
	
	
	public boolean get_bHasEmptyValues()
	{
		boolean    bRueck = false;
		
		for (BigDecimal bd: this)
		{
			if (bd == null)
			{
				bRueck = true;
			}
		}

		return bRueck;
	}
	
	
	
	
}


