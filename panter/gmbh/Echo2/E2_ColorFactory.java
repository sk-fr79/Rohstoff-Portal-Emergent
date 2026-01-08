package panter.gmbh.Echo2;

import java.text.DecimalFormat;
import java.util.HashMap;

import nextapp.echo2.app.Color;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_ColorFactory
{
	
	@SuppressWarnings("unchecked")
	public static Color get_COLOR(int iR, int iG, int iB) throws myException
	{
		
		if (iR<0 || iR>255 || iG<0 || iG>255 || iB<0 || iB>255)
		{
			throw new myException("Only Numbers 0-255 allowd for Color-RGB-Parts !!!");
		}
		
		
		//jetzt nachsehen, ob es bereits eine session-value __ICON_ARCHIV mit einer hashmap gibt
		HashMap<String, Color> oHash = null;
		if (bibALL.getSessionValue("__COLOR_ARCHIV") !=null)
		{
			oHash = (HashMap<String, Color>)bibALL.getSessionValue("__COLOR_ARCHIV");
		}
		else
		{
			oHash = new HashMap<String, Color>();
			bibALL.setSessionValue("__COLOR_ARCHIV", oHash);
		}

		DecimalFormat oDF = new DecimalFormat("000");
		
		String cHashKey = oDF.format(iR)+ oDF.format(iG)+ oDF.format(iB);
		

		if (oHash.containsKey(cHashKey))
		{
			return oHash.get(cHashKey);
		}
		else
		{
			Color color = new Color(iR, iG, iB);
			oHash.put(cHashKey, color);
			return color;
		}
	}

	
}
