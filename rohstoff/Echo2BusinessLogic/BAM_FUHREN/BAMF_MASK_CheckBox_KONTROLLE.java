/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_ContentPane;
import panter.gmbh.Echo2.components.MyE2_MessageBoxSingleText;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.bibALL;

public class BAMF_MASK_CheckBox_KONTROLLE  extends XX_ActionAgent
{
	private E2_ComponentMAP oComponentMAP = null;
	
	public BAMF_MASK_CheckBox_KONTROLLE(E2_ComponentMAP componentMAP)
	{
		super();
		this.oComponentMAP = componentMAP;
	}


	public void executeAgentCode(ExecINFO oExecInfo)
	{
		/*
		 * die action setzt den benutzer auf die eigene ID und sperrt die user-auswahl
		 * kontrolle darf erst angeklickt werden, wenn behebung angeklickt war
		 */
		MyE2_DB_CheckBox    	oCheckUSER_BEHEBUNG = (MyE2_DB_CheckBox) this.oComponentMAP.get("ABGESCHLOSSEN_BEHEBUNG");

		MyE2_DB_SelectField 	oSelectUSER_KONTROLLE = (MyE2_DB_SelectField) this.oComponentMAP.get("ID_USER_KONTROLLE");
		MyE2_DB_CheckBox	 	oCheckKONTROLLE	 = (MyE2_DB_CheckBox)bibE2.get_LAST_ACTIONEVENT().getSource();
		MyE2_DB_TextField		oDatumKontrolle =  (MyE2_DB_TextField) this.oComponentMAP.get("DATUM_KONTROLLE");
		
		/*
		 * zuerst pruefen, ob behebung schon erledigt ist
		 */
		if (! oCheckUSER_BEHEBUNG.isSelected())
		{
			// bibALL.add_MESSAGE("Zuerst muss die Behebung quittiert sein !",bibE2.get_CurrSession());
			oCheckKONTROLLE.setSelected(false);
			(new E2_RecursiveSearchParent_ContentPane((Component)bibE2.get_LAST_ACTIONEVENT().getSource())).get_FoundPane().add(new MyE2_MessageBoxSingleText(new MyE2_String("Info ..."), 
					new MyE2_String("Bevor die Beanstandungsmeldung als abgeschlossen markiert wird, bitte zuerst die Behebung erledigen"),
					new MyE2_String("OK")));
			return;
		}
			

		
		
		try
		{
			if (oCheckKONTROLLE.isSelected())
			{
				String cUser_ID = bibALL.get_ID_USER_FORMATTED();
				oSelectUSER_KONTROLLE.set_ActiveValue(cUser_ID);
				oSelectUSER_KONTROLLE.EXT().set_bDisabledFromInteractive(true);
				oSelectUSER_KONTROLLE.set_bEnabled_For_Edit(true);
				
				String cSTATUS_MASKE = E2_ComponentMAP.STATUS_EDIT;
				if (this.oComponentMAP.get_oSQLFieldMAP() == null) cSTATUS_MASKE= E2_ComponentMAP.STATUS_NEW_EMPTY;

				if (bibALL.isEmpty(oDatumKontrolle.getText()))
						oDatumKontrolle.set_cActual_Formated_DBContent_To_Mask(bibALL.get_cDateNOW(), cSTATUS_MASKE, null);
				
				//die ausfuehrung der kontrolle fuehrt auch zur schliessung der weigermeldung
				((MyE2_DB_CheckBox)this.oComponentMAP.get__Comp("WM_GESPERRT")).setSelected(true);
				
				
			}
			else
			{
				oSelectUSER_KONTROLLE.EXT().set_bDisabledFromInteractive(false);
				oSelectUSER_KONTROLLE.set_bEnabled_For_Edit(true);
			}
		}
		catch (Exception ex)
		{
			oSelectUSER_KONTROLLE.setSelectedIndex(0);   // leer
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("BAM_ActionAgents:ActionAgent_CheckBox:doAction:Error Selection own USER-ID !",false)));
		}
		
		
	}
	
}