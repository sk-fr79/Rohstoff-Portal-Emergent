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
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public abstract class ButtonActionAgentSingleDELETE extends XX_ActionAgent
{
	private E2_NavigationList 	oNavigationList = null;
	private MyE2_String 		cNameOfAction = null;
	

	
	public ButtonActionAgentSingleDELETE(	MyE2_String 		actionName,
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
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+new MyE2_String("  Bitte genau einen Datensatz zum Löschen auswählen !!").CTrans(),false),true);
		}
		else if (vIDsToDelete.size() > 1)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+new MyE2_String("  Bitte nur einen Datensatz zum Löschen auswählen !!").CTrans(),false),true);
		}
		else
		{

			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(vIDsToDelete);
		
			if (bibMSG.get_bIsOK())
			{	
				bibMSG.add_MESSAGE(this.CheckIdToDelete((String)vIDsToDelete.get(0)));
				
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

					
					oConfirm.set_ActionAgentForOK(new ownActionAgentConfirmDelete((String)vIDsToDelete.get(0),this.oNavigationList));
					oConfirm.show_POPUP_BOX();
				}
			}
		}
	}

	
	
	/*
	 * ueberschreib-methoden zum loeschen, einmal vor dem eigentlichen loeschvorgang, einmal nach dem eigentlichen loeschvorgang
	 * Zuerst SQL-Statemetnts, dann andere aktionen ()
	 */
	public abstract Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated);
	public abstract Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated);
	
	public abstract void Execute_Before_DELETE(String cID_toDeleteUnformated) throws myException;
	public abstract void Execute_After_DELETE(String cID_toDeleteUnformated) throws myException;
	public abstract MyE2_MessageVector CheckIdToDelete(String cID_UnformatedToDelete);
	
	
	
	private class ownActionAgentConfirmDelete extends XX_ActionAgent
	{
		
		private String 				cID_To_Delete = null;
		private E2_NavigationList 	oNaviList = null;
		
		public ownActionAgentConfirmDelete(String id_to_delete, E2_NavigationList onaviList)
		{
			super();
			this.cID_To_Delete = id_to_delete;
			this.oNaviList = onaviList;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
				
			try
			{
				Vector<String> vALL_SQL = new Vector<String>();
				vALL_SQL.addAll(ButtonActionAgentSingleDELETE.this.get_vSQL_Before_DELETE(this.cID_To_Delete));
				vALL_SQL.addAll(this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_SQL_DELETESTACK_From_UNFORMATED_KEY(this.cID_To_Delete));
				vALL_SQL.addAll(ButtonActionAgentSingleDELETE.this.get_vSQL_After_DELETE(this.cID_To_Delete));
			
				// dann aktionen vorher ausfuehren (keine SQL-aktioen
				ButtonActionAgentSingleDELETE.this.Execute_Before_DELETE(this.cID_To_Delete);
				
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vALL_SQL,true));
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Datensatz wurde gelöscht. ID: "+cID_To_Delete), false);
					ButtonActionAgentSingleDELETE.this.Execute_After_DELETE(this.cID_To_Delete);
					oNaviList.get_vActualID_Segment().remove(cID_To_Delete);
					oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();						// liste bauen
					oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen
				}
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("EditListButtonPanel:ownActionAgentDelete:Error:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	

	

}
