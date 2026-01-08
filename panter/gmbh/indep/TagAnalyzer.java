package panter.gmbh.indep;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public class TagAnalyzer 
{
	
	/*
	 * class sucht einen satz von tag in einem string und gibt die eingeschlossenen werte zurueck
	 * Der original stringbuffer wird gesaeubert
	 */
	public StringBuffer 			cCleanFeldName = null;
	public HashMap<String, String>  hmRueck = new HashMap<String, String>();
	
	public TagAnalyzer(StringBuffer StringWithTags, Vector<String> vKeys) throws myException 
	{
		super();
		
		StringBuffer strHelp = new StringBuffer(StringWithTags);
		
		//jetzt die tags durchgehen
		for (int i=0;i<vKeys.size();i++)
		{
			strHelp = this.readValue(strHelp, vKeys.get(i));
		}
		
		this.cCleanFeldName = strHelp;
	}
	
	
	private StringBuffer readValue(StringBuffer cTempString, String cHash) throws myException
	{
		String cLeft = S.get_cWertInStringCode(cTempString, cHash);
		if (cLeft!=null)
		{
			this.hmRueck.put(cHash, cLeft);
		}
		else
		{
			this.hmRueck.put(cHash, "");
		}
		return cTempString;
	}

	
}
