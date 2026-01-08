package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import java.util.Vector;

import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class TransZahlenMitEinheit extends XX_Translate_Buttontext_to_SortText
{

	private Vector<String>  vTextMussWeg = bibALL.get_Vector("kg", "t", "cbm", "EUR", "$","SFR");
	
	@Override
	public String TRANSLATE(String cText) throws myException
	{
		
		if (this.bIstAlsLeerZuBetrachten(cText))
		{
			return "0";
		}
		
		String cText2 = cText;
		
		for (String cWeg: this.vTextMussWeg)
		{
			cText2 = bibALL.ReplaceTeilString(cText2, cWeg , "");
			cText2 = bibALL.ReplaceTeilString(cText2, cWeg.toUpperCase() , "");
			cText2 = bibALL.ReplaceTeilString(cText2, cWeg.toLowerCase() , "");
		}
		
		cText2 = cText2.trim();
		
		MyBigDecimal  oDate = new MyBigDecimal(cText2);
		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			return oDate.get_FormatedRoundedNumber(3);
		}
		
		
		//jetzt noch den fall: 01.01.11
		MyDate  oDate2 = new MyDate((cText2+"        ").substring(0,8).trim());
		if (oDate2.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			return oDate2.get_cUmwandlungsergebnis();
		}
		
		return "0";
	}

	
	private boolean bIstAlsLeerZuBetrachten(String cText)
	{
		if (cText.trim().startsWith("<") && cText.trim().endsWith(">"))
		{
			return true;
		}
		
		if (cText.trim().startsWith("--") || cText.trim().equals("-"))
		{
			return true;
		}
		
		
		return false;
	}

	
}
