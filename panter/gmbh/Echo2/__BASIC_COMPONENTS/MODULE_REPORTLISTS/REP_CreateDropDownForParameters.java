package panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS;

import java.util.Vector;

import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class REP_CreateDropDownForParameters 
{
	

	
	
	// baut ein selectfield nach den vorgaben (falls select-abfragen vorhanden)
	public static  MyE2_SelectField build_oSelectFieldUserInput(String cDropDownDef, String cDefaultValString) throws myException
	{

		MyE2_SelectField oSelRueck = null;
		
		String[][] cArray = REP_CreateDropDownForParameters.get_DropDownArray(cDropDownDef);
		if (cArray != null)
		{
			oSelRueck = new MyE2_SelectField();
			oSelRueck.set_ListenInhalt(cArray,false);

			if (! bibALL.isEmpty(cDefaultValString))
			{
				for (int i=0;i<cArray.length;i++)
				{
					if (cArray[i][1].equals(cDefaultValString))
					{
						oSelRueck.setSelectedIndex(i);
					}
				}
			}

		}
		return oSelRueck;
	}

	
	
	/**
	 * 2013-10-11: auch checkbox fuer die auswahl der parameter moeglich
	 * @param cDropDownDef  !!! muss dann #CHECKBOX# sein
	 * @param cDefaultValString
	 * @return
	 * @throws myException
	 */
	public static MyE2_CheckBox  buildCheckBox(String cDropDownDef, String cDefaultValString) throws myException {
		MyE2_CheckBox  oCBRueck = null;
		
		if (cDropDownDef.trim().equals("#CHECKBOX#")) {
			oCBRueck = new MyE2_CheckBox();

			oCBRueck.setSelected(false);
			if (cDefaultValString.toUpperCase().trim().equals("Y")) {
				oCBRueck.setSelected(true);
			}
		}

		
		return oCBRueck;
	}
	
	
	
	
	
	/**
	 * DIE DROP-DOWN-Definition ist entweder eine array-definition, z.B. |Anzeige1:Wert1|Anzeige2:Wert2|Anzeige3:Wert3|Anzeige3:Wert3|
	 * oder eine select anzeige,wert - abfrage
	 */
	public static String[][] get_DropDownArray(String cDropDownDef)
	{
		String[][] cRueck = null;
		String cSelString = bibALL.null2leer(cDropDownDef).trim();
		if (!bibALL.isEmpty(cSelString))
		{
			try
			{
				
				if (cSelString.startsWith("|"))
				{
					Vector<String> vSeparat1 = bibALL.TrenneZeile(cSelString,"|");
					if (vSeparat1.size()>0)
					{
						String[][] cHelp = new String[vSeparat1.size()][2];
						
						for (int i=0;i<vSeparat1.size();i++)
						{
							String cInnen = (String)vSeparat1.get(i);
							Vector<String> vInnen = bibALL.TrenneZeile(cInnen,":");
							if (vInnen.size()==2)
							{
								cHelp[i][0]=(String)vInnen.get(0);
								cHelp[i][1]=(String)vInnen.get(1);
							}
						}
						// pruefen, ob alle gefuellt sind
						boolean bLeer=false;
						for (int i=0;i<cHelp.length;i++)
							for (int k=0;k<2;k++)
								if (cHelp[i][k] == null)
									bLeer=true;
						
						if (!bLeer)
							cRueck = cHelp;
						
					}
				}
				else if (cSelString.toUpperCase().startsWith("SELECT"))
				{
					// query
					String[][] cHelp = bibDB.EinzelAbfrageInArray(bibReplacer.ReplaceSysvariablesInStrings(cSelString),"");
					if (cHelp != null && cHelp.length>0 && cHelp[0].length==2)
					{
						cRueck = cHelp;
					}
				}
			}
			catch (Exception ex)
			{
				cRueck=null;
			}
			
			
		}
		return cRueck;
		
	}

}
