package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class FS_BT_LIST_SHOW_ADRESS_UEBERSICHT_NT extends MyE2_Button
{
	private E2_NavigationList	oNaviList = 	null;
	
	
	public FS_BT_LIST_SHOW_ADRESS_UEBERSICHT_NT(E2_NavigationList navilist)
	{
		super(E2_ResourceIcon.get_RI("info.png"),true);
		
		this.oNaviList = navilist;
		
		this.setToolTipText(new MyE2_String("Übersicht über die gewählten Adressen anzeigen").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,"ANZEIGE_ADRESSUEBERSICHT"));

	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			FS_BT_LIST_SHOW_ADRESS_UEBERSICHT_NT oThis = FS_BT_LIST_SHOW_ADRESS_UEBERSICHT_NT.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			if (vIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte markieren Sie mindestens eine Adresse für die Übersicht !!"));
				return;
			}
			
			MyE2_String oStringTitel = new MyE2_String("Adressen-Übersicht ... Anzahl: ");
			oStringTitel.addUnTranslated(""+vIDs.size());
			
			try
			{
				new FS_WINDOW_ADRESS_UEBERSICHT_NT(vIDs);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Anzeigen der Übersicht ..."));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
	}

	
	
}
