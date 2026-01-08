package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.exceptions.myException;

public class TransKurzesDatum extends XX_Translate_Buttontext_to_SortText
{

	
	@Override
	public String TRANSLATE(String cText) throws myException
	{
		String cRueck = "01.01.1900";
		
		if (!this.bIstAlsLeerZuBetrachten(cText))
		{
			boolean bFound = false;
			
			MyDate  oDate = new MyDate(cText);
			if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
			{
				cRueck = oDate.get_cUmwandlungsergebnis();
				bFound = true;
			}
			
			if (!bFound)
			{
				//jetzt noch den fall: 01.01.11
				MyDate  oDate2 = new MyDate((cText+"       ").substring(0,8).trim());
				if (oDate2.get_cErrorCODE().equals(MyDate.ALL_OK))
				{
					cRueck = oDate2.get_cUmwandlungsergebnis();
					bFound = true;
				}
			}
		}		
		
		//System.out.println("Alt: "+cText+"    ---->    neu: "+cRueck);
		
		return cRueck;
	}

	
	private boolean bIstAlsLeerZuBetrachten(String cText)
	{
		if (cText.trim().startsWith("<") && cText.trim().endsWith(">"))
		{
			return true;
		}
		
		if (cText.trim().startsWith("-"))
		{
			return true;
		}
		
		
		return false;
	}
	
}
