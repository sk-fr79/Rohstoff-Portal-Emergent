package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class WF_HEAD_MASK_MapSettingAgent extends XX_MAP_SettingAgent {

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
	

	}

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
	{

		// nur der Besitzer darf auch den Bearbeiter ändern
		// und ein Superuser natürlich...
		// alle anderen werden gesperrt!
		if (!bibALL.get_ID_USER_FORMATTED().
				equalsIgnoreCase(((MyE2_DB_SelectField)oMap.get__Comp("ID_USER_BESITZER")).get_ActualWert())
			&& 
			!bibALL.get_bIST_SUPERVISOR())
		{
				// supervisor
				oMap.get__Comp("ID_USER_SUPERVISOR").EXT().set_bDisabledFromInteractive(true);
				// privat 
				oMap.get__Comp("PRIVAT").EXT().set_bDisabledFromInteractive(true);
				
		}
		
		
		String sAbgeschlossen = ((MyE2_DB_TextField)oMap.get__Comp("ABGESCHLOSSEN_AM")).get_cActualDBValueFormated();
		boolean bAbgeschlossen = sAbgeschlossen.length() > 0;
		
		
		if (bAbgeschlossen)
		{   // wenn der eintrag abgeschlossen war, dann nur den Abschliessen-rückgängig-Button aktivieren!
			oMap.set_All_DB_ComponentsAsDisableFromInteractive();
			oMap.set_All_NONDB_ComponentsAsDisableFromInteractive();
			oMap.get__Comp("ID_LAUFZETTEL_STATUS").EXT().set_bDisabledFromInteractive(false);
		}

	

		
		
		/*
		 * beispiele fuer inhaltabhaengige erlaubnis, etwas zu bearbeiten
		 *
		String[] cAllFieldsEditable = {"GENERIERUNGSDATUM","ABLAUFDATUM","ABSCHLUSSDATUM","AUFGABEKURZ",
									"AUFGABENTEXT","ANTWORTKURZ","ANTWORTTEXT","ERLEDIGT","ID_USER",
									WF_HEAD_MASK_BasicModuleContainer.HASHVALUE_WF_HEAD_TEILNEHMER,}; 
		
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
		
		if (oUser.get_StatusWF_HEAD_SUPERVISOR().equals("0") || oUser.get_StatusWF_HEAD_SUPERVISOR().equals("1"))
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
			if (oUser.get_StatusWF_HEAD_SUPERVISOR().equals("0"))
			{
				oMap.get__Comp("ID_USER").EXT().set_bDisabledFromInteractive(true);
			}
		}
		*/

		
	}
	
	@SuppressWarnings("unused")
	private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
	{
		for (int i=0;i<cFieldList.length;i++)
		{
			oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
		}
	}

}
