package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_MASK_MapSettingAgent extends XX_MAP_SettingAgent {

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
	}

	

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
		// zuerst reset
		String[] cFieldsStandard = {"AKTIV","BEENDET","PROJEKTBEGIN","PROJEKTBESCHREIBUNG",
									"PROJEKTDEADLINE","PROJEKTNAME","WIEDERVORLAGE",
									"ID_PROJEKTGEWICHT","ID_PROJEKTSTATUSVARIANTE","ID_USER",
									PROJEKT_LIST_BasicModuleContainer.NAME_OF_FIRMEN_DAUGHTER,
									PROJEKT_LIST_BasicModuleContainer.NAME_OF_MITARBEITER_DAUGHTER}; 
		
		// reset
		this.do_Disable(oMap, cFieldsStandard, false);
		
		
		if (!bibALL.get_bIST_SUPERVISOR())
		{
			// bei nicht supervisor wird der benutzer gesperrt (voreinstellung ist bindend)
			oMap.get__Comp("ID_USER").EXT().set_bDisabledFromBasic(true);
			oMap.get__Comp("ID_USER").set_bEnabled_For_Edit(false);
			
			String cUserInList = ""+((MyE2IF__DB_Component) oMap.get__Comp("ID_USER")).EXT_DB().get_LActualDBValue(true, false, new Long(0)).intValue();
			
			// der nicht-besitzer und nicht-admin darf nichts aendern ausser seinen fortgangsinfos
			if (!cUserInList.equals(bibALL.get_ID_USER()))
			{
				this.do_Disable(oMap, cFieldsStandard, true);
			}
			
		}
		
	}

	
	private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
	{
		for (int i=0;i<cFieldList.length;i++)
		{
			oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
		}
	}

}
