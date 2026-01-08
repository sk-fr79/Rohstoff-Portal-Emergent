package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public abstract class ButtonActionAgentMULTIDELETE extends XX_ActionAgent
{
	private E2_NavigationList 	oNavigationList = null;

	private MyE2_String 		cNameOfAction = null;
	

	public ButtonActionAgentMULTIDELETE(	MyE2_String 		actionName,
											E2_NavigationList 	onavigationList)
	{
		super();
		this.oNavigationList = 	onavigationList;
		this.cNameOfAction = 	actionName;
	}

	
	public void executeAgentCode(ExecINFO oExecInfo) throws myException
	{
		Vector<String> vIDsToDelete = this.oNavigationList.get_vSelectedIDs_Unformated();
		
		if (vIDsToDelete.size() == 0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+new MyE2_String("  Bitte mindestens einen Datensatz zum Löschen auswählen !!").CTrans(),false),true);
		}
		else
		{
			
				// suchen, ob der button einen integritaetspruefer hat
			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(vIDsToDelete);
			
			/*
			 * jetzt die id-validierung
			 */
			if (bibMSG.get_bIsOK())
			{
				bibMSG.add_MESSAGE(this.CheckIdToDelete(vIDsToDelete));
				if (bibMSG.get_bIsOK())
				{
					E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
														new MyE2_String("Sicherheitsabfrage"),
														new MyE2_String("Löschen ?"),
														new MyE2_String(""),
														new MyE2_String("JA"),
														new MyE2_String("NEIN"),
														new Extent(300),
														new Extent(200)
														);
					oConfirm.set_oExtMINIMALWidth(new Extent(400));
					oConfirm.set_oExtMINIMALHeight(new Extent(200));
					
					oConfirm.set_ActionAgentForOK(new ownActionAgentConfirmDelete(vIDsToDelete,this.oNavigationList));
					oConfirm.show_POPUP_BOX();
				}
			}
		}
	}

	
	
	/*
	 * ueberschreib-methoden zum loeschen, einmal vor dem eigentlichen loeschvorgang, einmal nach dem eigentlichen loeschvorgang
	 * Zuerst SQL-Statemetnts, dann andere aktionen ()
	 */
	public abstract Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated) throws myException;
	public abstract Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) throws myException;
	public abstract void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException;
	public abstract void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException;
	
	/**
	 * @return s Vector of MyString-object with errormessages
	 * methode kann ueberschrieben werden, wenn eine pruefung auf erlaubnis erfolgen soll
	 */
	public abstract MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException;
	
	
	
	private class ownActionAgentConfirmDelete extends XX_ActionAgent
	{
		
		private Vector<String> 		vIDs_To_Delete = null;
		private E2_NavigationList 	oNaviList = null;
				
		public ownActionAgentConfirmDelete(Vector<String> vIds_to_delete, E2_NavigationList onaviList)
		{
			super();
			this.vIDs_To_Delete = vIds_to_delete;
			this.oNaviList = onaviList;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			
			try
			{
				Vector<String> vALL_SQL = new Vector<String>();

				for (int i=0;i<this.vIDs_To_Delete.size();i++)
				{
					String cID_To_Delete = (String)this.vIDs_To_Delete.get(i);
					vALL_SQL.addAll(bibALL.null2leerString(ButtonActionAgentMULTIDELETE.this.get_vSQL_Before_DELETE(cID_To_Delete)));
					vALL_SQL.addAll(this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_SQL_DELETESTACK_From_UNFORMATED_KEY(cID_To_Delete));
					vALL_SQL.addAll(bibALL.null2leerString(ButtonActionAgentMULTIDELETE.this.get_vSQL_After_DELETE(cID_To_Delete)));
				}
				
			
				// dann aktionen vorher ausfuehren (keine SQL-aktioen
				ButtonActionAgentMULTIDELETE.this.Execute_Before_DELETE(this.vIDs_To_Delete);
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vALL_SQL,true));
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Datensätze wurden gelöscht. IDs: "+bibALL.Concatenate(this.vIDs_To_Delete,",", null)), false);
					ButtonActionAgentMULTIDELETE.this.Execute_After_DELETE(this.vIDs_To_Delete);
					oNaviList.get_vActualID_Segment().removeAll(this.vIDs_To_Delete);
					oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();						// liste bauen
					oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen
				}
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("EditListButtonPanel:ownActionAgentDelete:Error:",false), false);
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}



	public E2_NavigationList get_navigationList() {
		return oNavigationList;
	}


	public MyE2_String get_nameOfAction() {
		return cNameOfAction;
	}
	

	

}
