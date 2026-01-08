package panter.gmbh.Echo2;

import java.io.InputStream;
import java.util.HashMap;

import nextapp.echo2.app.ResourceImageReference;
import panter.gmbh.indep.bibALL;

public class E2_ResourceIcon extends ResourceImageReference
{
	private String cResourceName = null;
	
	
	private E2_ResourceIcon(String cBildName)
	{
		super("/resources/iconsng/"+cBildName);
		this.cResourceName = "/resources/iconsng/"+cBildName;
			
	}

	
	public ResourceImageReference get_GrayVersion()
	{
		ResourceImageReference oRueck = new ResourceImageReference("/resources/iconsng/leer.png");
		/*
		 * jetzt nachschauen, ob es eine resource
		 */
		Class clazz = getClass();
		String cTestGrayName = this.cResourceName.substring(0,this.cResourceName.length()-4)+"__"+this.cResourceName.substring(this.cResourceName.length()-4);
		
		InputStream is = clazz.getResourceAsStream(cTestGrayName);	
		
		if (is != null)
		{
			try
			{
				is.close();
			}
			catch (Exception ex) {};
			
			oRueck = new ResourceImageReference(cTestGrayName);
		}
		return oRueck;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static E2_ResourceIcon get_RI(String cBildName)
	{
		//jetzt nachsehen, ob es bereits eine session-value __ICON_ARCHIV mit einer hashmap gibt
		HashMap<String, E2_ResourceIcon> oHash = null;
		if (bibALL.getSessionValue("__ICON_ARCHIV") !=null)
		{
			oHash = (HashMap<String, E2_ResourceIcon>)bibALL.getSessionValue("__ICON_ARCHIV");
		}
		else
		{
			oHash = new HashMap<String, E2_ResourceIcon>();
			bibALL.setSessionValue("__ICON_ARCHIV", oHash);
		}

		E2_ResourceIcon oRueck = oHash.get(cBildName);
		
		if (oRueck==null)
		{
			oRueck = new E2_ResourceIcon(cBildName);
			oHash.put(cBildName, oRueck);
		}

		return oRueck;
		
	}

	
}
