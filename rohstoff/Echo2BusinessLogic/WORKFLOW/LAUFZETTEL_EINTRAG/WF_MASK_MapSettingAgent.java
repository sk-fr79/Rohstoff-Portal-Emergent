package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.E2_AuswahlSelectorUsers;

public class WF_MASK_MapSettingAgent extends XX_MAP_SettingAgent {

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
	

	}

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
	{

		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY) )
		{
			((E2_AuswahlSelectorUsers)oMap.get__Comp_From_RealComponents(WF_CONST.HASH_LAUFZETTEL_BENUTZER_AUSWAHL)).setVisible(true);
			((MyE2_DB_SelectField)oMap.get__Comp_From_RealComponents("ID_USER_BEARBEITER")).setVisible(false);
			((MyE2_Button)oMap.get__Comp(WF_CONST.HASH_BUTTON_SET_WF_BEARBEITER_SELF)).setVisible(false);
			
			
			oMap.get__Comp(WF_CONST.HASH_BUTTON_CREATE_REMINDER).EXT().set_bDisabledFromInteractive(true);
			oMap.get__Comp(WF_CONST.HASH_BUTTON_CREATE_MESSAGE_TO_GROUP).EXT().set_bDisabledFromInteractive(true);
			
			
			// Beim Neuanlegen den Berichtstext verkleinern...
			((MyE2_DB_TextArea) oMap.get__Comp("BERICHT")).set_iRows(3);
			
			// nachricht senden
			((MyE2_DB_CheckBox)oMap.get__Comp_From_RealComponents("SEND_NACHRICHT")).setSelected(true);

			// default kadenz auf "nach Abschluss"
			MyE2_CheckBox oCBKadenzNachAbschluss = (MyE2_CheckBox)oMap.get__Comp(WF_CONST.HASH_CHECKBOX_KADENZ_NACH_ABSCHLUSS);
			((WF_MASK_ComponentMAP)oMap).getAgentWatchdogKadenz().setCheckbox(oCBKadenzNachAbschluss, true);
		}
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT)){

			((E2_AuswahlSelectorUsers)oMap.get__Comp_From_RealComponents(WF_CONST.HASH_LAUFZETTEL_BENUTZER_AUSWAHL)).setVisible(false);
			((MyE2_DB_SelectField)oMap.get__Comp_From_RealComponents("ID_USER_BEARBEITER")).setVisible(true);
			((MyE2_Button)oMap.get__Comp(WF_CONST.HASH_BUTTON_SET_WF_BEARBEITER_SELF)).setVisible(true);
			((MyE2_Button)oMap.get__Comp(WF_CONST.HASH_BUTTON_SET_WF_BEARBEITER_SELF)).set_bEnabled_For_Edit(true);;
			
			// ermitteln des Objetkes für die LaufzettelID
			@SuppressWarnings("unused")
			String cLaufzettelId =  ((MyE2_DB_TextField) oMap.get__Comp("ID_LAUFZETTEL")).get_cActualMaskValue();
			
			Long l = oMap.get_LActualDBValue("ID_LAUFZETTEL", true, true, null);
			RECORD_LAUFZETTEL oLaufzettel = new RECORD_LAUFZETTEL(l.toString());
			
			// alle felder sind erstmal gesperrt!!
			oMap.set_DisabledFromInteractiv_ALL(true);
			
			// wenn der Laufzettel abgeschlossen ist, dann darf nix mehr editiert werden!
			if (!oLaufzettel.get_ABGESCHLOSSEN_AM_cUF_NN("").isEmpty())
			{
				return;
			}
			
			// wenn der Laufzettel gelöscht ist, dann darf nix mehr editiert werden!
			if (oLaufzettel.get_GELOESCHT_cUF_NN("").equalsIgnoreCase("Y"))
			{
				return;
			}

			// Wenn der Laufzetteleintrag gelöscht ist, dann darf er auch nicht mehr editiert werden!
			String sDeleted = "";
			sDeleted = ((MyE2_DB_TextField)oMap.get__Comp("GELOESCHT")).get_cActualMaskValue();
			if (sDeleted.equalsIgnoreCase("Y"))
			{
				return;
			}
			
			String sAbgeschlossen = ((MyE2_DB_TextField)oMap.get__Comp("ABGESCHLOSSEN_AM")).get_cActualDBValueFormated();
			boolean bAbgeschlossen = sAbgeschlossen.length() > 0;
			
			
			// wenn der aktuelle Benuter 
			//	der Besitzer ist
			//  oder Superuser ist
			// 	oder Supervisor ist
			// dar er folgeden Felder bearbeiten!!
			String sIDUser = bibALL.get_ID_USER_FORMATTED();
			String sIDUserBesitzer = ((MyE2_DB_SelectField)oMap.get__Comp("ID_USER_BESITZER")).get_ActualWert();
			
			if (bibALL.get_ID_USER_FORMATTED().
					equalsIgnoreCase(((MyE2_DB_SelectField)oMap.get__Comp("ID_USER_BESITZER")).get_ActualWert())
				|| 
				bibALL.get_bIST_SUPERVISOR()
				||
				bibALL.get_ID_USER().equals(oLaufzettel.get_ID_USER_SUPERVISOR_cUF()))
			{
			
				if (bAbgeschlossen)
				{   // wenn der eintrag abgeschlossen war, dann nur den Abschliessen-rückgängig-Button aktivieren!
					oMap.get__Comp("ID_LAUFZETTEL_STATUS").EXT().set_bDisabledFromInteractive(false);
					// Auch der Knopf für die Verlinkung zu einem anderen Modul muss aktiv bleiben
					oMap.get__Comp(WF_CONST.HASH_LIST_CONNECTOR).EXT().set_bDisabledFromInteractive(false);
				}
				else
				{
					// Zusätzlich: der Supervisor darf auch den Besitzer ändern:
					if (bibALL.get_bIST_SUPERVISOR()){
						oMap.get__Comp("ID_USER_BESITZER").EXT().set_bDisabledFromInteractive(false);
					}
					
					oMap.get__Comp("PRIVAT").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("ID_LAUFZETTEL_PRIO").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp_From_RealComponents("ID_USER_BEARBEITER").EXT().set_bDisabledFromInteractive(false);
					((MyE2_Button)oMap.get__Comp(WF_CONST.HASH_BUTTON_SET_WF_BEARBEITER_SELF)).EXT().set_bDisabledFromInteractive(false);
					
					oMap.get__Comp("FAELLIG_AM").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("SEND_NACHRICHT").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("ID_USER_ABGESCHLOSSEN_VON").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("AUFGABE").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("BERICHT").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("ABGESCHLOSSEN_AM").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("ID_LAUFZETTEL_STATUS").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("KADENZ_NACH_ABSCHLUSS").EXT().set_bDisabledFromInteractive(false);
					
					oMap.get__Comp(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn()).EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp(WF_CONST.HASH_CHECKBOX_KADENZ_NACH_ABSCHLUSS).EXT().set_bDisabledFromInteractive(false);
					
					oMap.get__Comp("HASH_LIST_CONNECTOR").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp(WF_CONST.HASH_BUTTON_CREATE_REMINDER).EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp(WF_CONST.HASH_BUTTON_CREATE_MESSAGE_TO_GROUP).EXT().set_bDisabledFromInteractive(false);
					
					ApplicationInstance.getActive().setFocusedComponent((Component) oMap.get__Comp("AUFGABE"));
				}

			} 
			else if (bibALL.get_ID_USER_FORMATTED().equalsIgnoreCase(((MyE2_DB_SelectField)oMap.get__Comp_From_RealComponents("ID_USER_BEARBEITER")).get_ActualWert()))
			{
				if (bAbgeschlossen)
				{   // wenn der eintrag abgeschlossen war, dann nur den Abschliessen-rückgängig-Button aktivieren!
					oMap.get__Comp("ID_LAUFZETTEL_STATUS").EXT().set_bDisabledFromInteractive(false);
					// Auch der Knopf für die Verlinkung zu einem anderen Modul muss aktiv bleiben
					oMap.get__Comp(WF_CONST.HASH_LIST_CONNECTOR).EXT().set_bDisabledFromInteractive(false);
				}
				else
				{
					// wenn es der Bearbeiter ist...
					oMap.get__Comp("ID_LAUFZETTEL_STATUS").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("BERICHT").EXT().set_bDisabledFromInteractive(false);
//					oMap.get__Comp("ABGESCHLOSSEN_AM").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp("ID_LAUFZETTEL_STATUS").EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp(WF_CONST.HASH_BUTTON_CREATE_REMINDER).EXT().set_bDisabledFromInteractive(false);
					oMap.get__Comp(WF_CONST.HASH_BUTTON_CREATE_MESSAGE_TO_GROUP).EXT().set_bDisabledFromInteractive(false);
					
					ApplicationInstance.getActive().setFocusedComponent((Component) oMap.get__Comp("BERICHT"));
				}
			}
		}
		
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
