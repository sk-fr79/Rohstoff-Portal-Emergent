package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;

public class LC_MASK_MapSettingAgent extends XX_MAP_SettingAgent {

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
	

	}

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
	{
		/*
		 * beispiele fuer inhaltabhaengige erlaubnis, etwas zu bearbeiten
		 *
		String[] cAllFieldsEditable = {"GENERIERUNGSDATUM","ABLAUFDATUM","ABSCHLUSSDATUM","AUFGABEKURZ",
									"AUFGABENTEXT","ANTWORTKURZ","ANTWORTTEXT","ERLEDIGT","ID_USER",
									LC_MASK_BasicModuleContainer.HASHVALUE_LC_TEILNEHMER,}; 
		
		String[] cFieldsEditable0 = {"ABSCHLUSSDATUM","ANTWORTKURZ","ANTWORTTEXT"}; 
		myUser  oUser = new myUser(bibALL.get_ID_USER(bibE2.get_CurrSession()),bibE2.get_CurrSession());

		
		String cIDUserInMaske =((MyE2_DB_SelectField)oMap.get__Comp("ID_USER")).get_ActualWert();
		DotFormatterGermanFixed oDF = new DotFormatterGermanFixed(cIDUserInMaske);
		if (!oDF.doFormat())
		{
			throw new myException(this,":Error Finding ID_USER from Mask-Field !!");
		}
		cIDUserInMaske = oDF.getStringUnFormated();
		
		// alles auf start
		this.do_Disable(oMap, cAllFieldsEditable, false);
		
		if (oUser.get_StatusLC_SUPERVISOR().equals("0") || oUser.get_StatusLC_SUPERVISOR().equals("1"))
		{
			// fuer 0 - kandidaten im editmode nur eigene todos erlaubt
			if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
			{
				if (! cIDUserInMaske.equals(bibALL.get_ID_USER(bibE2.get_CurrSession())))
				{
					this.do_Disable(oMap, cAllFieldsEditable, true);
					this.do_Disable(oMap, cFieldsEditable0, false);
				}
			}

			// fuer user typ 0 ist der besitzer immer gesperrt
			if (oUser.get_StatusLC_SUPERVISOR().equals("0"))
			{
				oMap.get__Comp("ID_USER").EXT().set_bDisabledFromInteractive(true);
			}
		}
		*/

		
	}
	
	private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
	{
		for (int i=0;i<cFieldList.length;i++)
		{
			oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
		}
	}

}
