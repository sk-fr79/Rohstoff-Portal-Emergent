package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class MANDANT_MASK_MapSettingAgent extends XX_MAP_SettingAgent {

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
	

	}

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
	{
		RECORD_MANDANT  recMandant = new  RECORD_MANDANT(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF());
		
		((MyE2_TextField)oMap.get__Comp(MANDANT_MASK_ComponentMAP.COLORLABEL1)).setBackground(
				new Color(
						recMandant.get_COLOR_RED_lValue(new Long(0)).intValue(),
						recMandant.get_COLOR_GREEN_lValue(new Long(0)).intValue(),
						recMandant.get_COLOR_BLUE_lValue(new Long(0)).intValue()));

		((MyE2_TextField)oMap.get__Comp(MANDANT_MASK_ComponentMAP.COLORLABEL2)).setBackground(
				new Color(
						recMandant.get_COLOR_POPUP_TITEL_RED_lValue(new Long(0)).intValue(),
						recMandant.get_COLOR_POPUP_TITEL_GREEN_lValue(new Long(0)).intValue(),
						recMandant.get_COLOR_POPUP_TITEL_BLUE_lValue(new Long(0)).intValue()));
		

		((MyE2_TextField)oMap.get__Comp(MANDANT_MASK_ComponentMAP.COLORLABEL3)).setBackground(
				new Color(
						recMandant.get_COLOR_MASK_HIGHLIGHT_RED_lValue(new Long(0)).intValue(),
						recMandant.get_COLOR_MASK_HIGHLIGHT_GREEN_lValue(new Long(0)).intValue(),
						recMandant.get_COLOR_MASK_HIGHLIGHT_BLUE_lValue(new Long(0)).intValue()));
		
		((MyE2_TextField)oMap.get__Comp(MANDANT_MASK_ComponentMAP.COLORLABEL4)).setBackground(
				new Color(
						recMandant.get_COLOR_BACKTEXT_RED_lValue(new Long(0)).intValue(),
						recMandant.get_COLOR_BACKTEXT_GREEN_lValue(new Long(0)).intValue(),
						recMandant.get_COLOR_BACKTEXT_BLUE_lValue(new Long(0)).intValue()));
		
		
		//20170810: hier wird der pruefalgorythmus aufgerufen, der checkt, ob alle zusatzfeldnamen in der datenbank stehen
		try {
			ENUM_MANDANT_ZUSATZ_FELDNAMEN.write_all_new();
		} catch (Exception e) {
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Error checking new MANDANT_ZUSATZ_FELDNAMEN")));
		}
		
		
	}
	
//	private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
//	{
//		for (int i=0;i<cFieldList.length;i++)
//		{
//			oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
//		}
//	}

}
