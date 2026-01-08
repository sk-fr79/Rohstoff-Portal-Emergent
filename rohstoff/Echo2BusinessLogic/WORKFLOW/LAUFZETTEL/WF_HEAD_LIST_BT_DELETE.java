package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;

import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class WF_HEAD_LIST_BT_DELETE extends MyE2_Button /*implements DS_IF_components4decision*/
{

	/**
	 * 
	*/
	private static final long serialVersionUID = -9140619536610757108L;

	private E2_NavigationList m_navList = null;
	private deleteActionAgent oDeleteActionAgent = null;

	
	public WF_HEAD_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));

		this.m_navList = onavigationList;
		
		// decision-Agent
//		this.add_oActionAgent(new cYesNoDecisionAction(this));
		oDeleteActionAgent = (deleteActionAgent) new deleteActionAgent(new MyE2_String("Löschen von Laufzetteln"),onavigationList,"GELOESCHT","JT_LAUFZETTEL","ID_LAUFZETTEL")
																	.setMessageText("Es werden alle Aufgaben als gelöscht markiert.");
		this.add_oActionAgent(oDeleteActionAgent);

		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,"LOESCHE_WF_HEAD"));
		
		
		// neuer Validierer, zum prüfen, dass nur der Besitzer oder der Supervisor den Datensatz bearbeiten darf
		this.add_IDValidator(new XX_ActionValidator()
		{

			@Override
			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
			{
				return null;
			}

			@Override
			protected MyE2_MessageVector isValid(String unformated)	throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				String cID_USER = bibALL.get_ID_USER();
				
				// nur Besitzer oder Supervisor dürfen löschen
				RECORD_LAUFZETTEL oMAP_LZ = new RECORD_LAUFZETTEL(unformated);
				if (!oMAP_LZ.get_ID_USER_BESITZER_cUF().equals(cID_USER) 
						&& !bibALL.null2leer(oMAP_LZ.get_ID_USER_SUPERVISOR_cUF()).equals(cID_USER)
						&& !bibALL.get_bIST_SUPERVISOR())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Löschen darf nur der Besitzer oder Supervisor!")));
				}
				
				// es darf nur gelöscht werden, wenn 
				// alle Einträge gelöscht sind
				// oder alle ungelöschten Einträge keine Kadenz haben
				RECLIST_LAUFZETTEL_EINTRAG oEintraege = oMAP_LZ.get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_laufzettel(" NVL(GELOESCHT,'N') = 'N' " +
																													  " AND ID_USER_ABGESCHLOSSEN_VON is null " +
																													  " AND KADENZ_NACH_ABSCHLUSS is not null ", "", false);
				if (oEintraege.size() > 0){
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Laufzettel kann nicht gelöscht werden, da noch Aufgaben mit Wiedervorlage nach Abschluss aktiv sind.\n" +
							"Leeren Sie das Feld für die Wiedervorlage nach Abschluss.")));
				}
				
				if (oMAP_LZ.get_GELOESCHT_cUF_NN("N").equals("Y")){
					
					oDeleteActionAgent.setHeadingText("Wiederherstellen des Laufzettels");
					oDeleteActionAgent.setMessageText("Es werden alle Laufzettel-Aufgaben wieder hergestellt.");
				} else {
					oDeleteActionAgent.setHeadingText("Löschen von Laufzetteln");
					oDeleteActionAgent.setMessageText("Es werden alle Aufgaben als gelöscht markiert.");
				}
				
				return oMV;
			}
		});
	}
	
	
	
	
	
	private class deleteActionAgent extends ButtonActionAgent_TOGGLE_Y_N
	{
		
		
		String sID = null;
		String sTable = null;
		public deleteActionAgent(MyE2_String actionName,
				E2_NavigationList onavigationList, String FieldNAME_YES_NO,
				String TABLENAME, String ID_TABLENAME) {
			super(actionName, onavigationList, FieldNAME_YES_NO, TABLENAME, ID_TABLENAME);
			
			sID = ID_TABLENAME;
			sTable = TABLENAME;
		}


		
		
		
		@Override
		public MyE2_MessageVector CheckIdToToggle(Vector unformatedToDelete) {
			return null;
		}

		@Override
		public void Execute_After_TOGGLE(Vector ds_toToggleUnformated) throws myException {
		}

		@Override
		public void Execute_Before_TOGGLE(Vector ds_toToggleUnformated) throws myException {
		}

		@Override
		public Vector<String> get_vSQL_After_TOGGLE(String toggleUnformated, String newValue) throws myException {
			return null;
		}

		@Override
		public Vector<String> get_vSQL_Before_TOGGLE(String toggleUnformated, String newValue) throws myException {
			String sSql = "UPDATE " + LAUFZETTEL_EINTRAG.fullTabName() + " SET " + LAUFZETTEL_EINTRAG.geloescht.fn() + " = '"+ newValue +"' " +
						  " WHERE " + LAUFZETTEL_EINTRAG.id_laufzettel.fn() + " = " + toggleUnformated;
			return bibVECTOR.get_Vector(sSql);
		}
	
	}


	
	
	
}
