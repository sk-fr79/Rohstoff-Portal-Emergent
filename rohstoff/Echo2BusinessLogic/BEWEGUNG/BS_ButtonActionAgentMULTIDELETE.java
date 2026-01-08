package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/*
 * spezieller actionagent fuer das loeschen via SET DELETED='Y'
 */
public abstract class BS_ButtonActionAgentMULTIDELETE extends XX_ActionAgent
{
	private E2_NavigationList 	oNavigationList = null;
	private MyE2_TextArea      oTFDelGrund = new MyE2_TextArea( "",200,200,6);
	
	private String   	 		cTABLE_NAME = "";
	private boolean 			bReloadAfterDelete = false;
	
	
	private Vector<XX_ActionAgent>  vWeitereActionsAfterDeleteOK = new Vector<XX_ActionAgent>();
	
	
	public BS_ButtonActionAgentMULTIDELETE( E2_NavigationList onavigationList, String cTableName, boolean ReloadAfterDelete)
	{
		super();
		this.oNavigationList = 	onavigationList;
		this.cTABLE_NAME = cTableName;
		this.bReloadAfterDelete = ReloadAfterDelete;
	}

	
	public void executeAgentCode(ExecINFO oExecInfo) throws myException
	{
		Vector<String> 		vIDsToDelete = 	this.oNavigationList.get_vSelectedIDs_Unformated();
		MyE2_Button oOwnButton = 	(MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
		
		this.oTFDelGrund.setText("");    // leermachen
		
		
		if (vIDsToDelete.size() == 0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens einen Datensatz zum Löschen auswählen !!",true),true);
		}
		else
		{
			
			/*
			 * jetzt die id-validierung
			 */
			bibMSG.add_MESSAGE(oOwnButton.valid_IDValidation(vIDsToDelete));
			
			if (bibMSG.get_bIsOK())
			{	
				bibMSG.add_MESSAGE(this.CheckIdToDelete(vIDsToDelete));
				if (bibMSG.get_bIsOK())
				{
				
					E2_ComponentGroupHorizontal oGroup = new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Lösch-Grund: ")),this.oTFDelGrund, E2_INSETS.I_0_10_10_10);
					
					E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(	new MyE2_String("Sicherheitsabfrage"),
																						new MyE2_String("Löschen ?"),
																						null,
																						oGroup,
																						new ValidInputText(),
																						new MyE2_String("JA"),
																						new MyE2_String("NEIN"),
																						new Extent(400),
																						new Extent(300));
					
					oConfirm.set_oExtMINIMALHeight(new Extent(300));
					oConfirm.set_oExtMINIMALWidth(new Extent(450));
					
					oConfirm.set_ActionAgentForOK(new ownActionAgentConfirmDelete(vIDsToDelete,this.oNavigationList));
					
					//falls weitere aktionen dazukommen
					for (XX_ActionAgent oAgent: this.vWeitereActionsAfterDeleteOK)
					{
						oConfirm.add_ActionAgentForOK_AfterCloseEvent(oAgent);
					}
					
					oConfirm.show_POPUP_BOX();
				}
			}
		}
	}

	
	
	/**
	 * @return s Vector of MyString-object with errormessages
	 * methode kann ueberschrieben werden, wenn eine pruefung auf erlaubnis erfolgen soll
	 */
	public abstract MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete);
	
	
	/*
	 * zusatzmethode, mit der nochmal gecheckt wird, welche loeschvorgange sich noch ergeben;
	 * Die Methode kann ueberschrieben werden
	 */
	public abstract void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException;
	
	
	

	
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
			
			/*
			 * evtl. die integritaetschecker ausfuehren
			 */
			BS_ButtonActionAgentMULTIDELETE oThis = BS_ButtonActionAgentMULTIDELETE.this;
			
			try
			{
				
				Vector<String> vALL_SQL = new Vector<String>();

				for (int i=0;i<this.vIDs_To_Delete.size();i++)
				{
					BS_DELETER oDel = new BS_DELETER(	
											oThis.cTABLE_NAME,
											(String)this.vIDs_To_Delete.get(i),
											oThis.oTFDelGrund.getText().trim());
					vALL_SQL.addAll(oDel.get_vDeleteStatements());
				}
				
				oThis.PruefeWeiterLoeschungen(this.vIDs_To_Delete,vALL_SQL,oThis.oTFDelGrund.getText().trim());
				
				
				// dann aktionen vorher ausfuehren (keine SQL-aktioen
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vALL_SQL,true));
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Datensätze wurden gelöscht. IDs: "+bibALL.Concatenate(this.vIDs_To_Delete,",", null)));
					if (oThis.bReloadAfterDelete)
					{
						oNaviList._REBUILD_COMPLETE_LIST("");
					}
					else
					{
						oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();							// liste bauen
						oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen
					}
				}
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BS_ButtonActionAgentMULTIDELETE:ownActionAgentDelete:Error:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	

	/*
	 * validator fuer den ok-knopf im popup-fenster, verhindert, dass ohne Eingabe eines Grundes geloescht wird
	 */
	private class ValidInputText extends XX_ActionValidator
	{
	
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
		{
			MyE2_MessageVector  vRueck = new MyE2_MessageVector();
			String cText = bibALL.null2leer(BS_ButtonActionAgentMULTIDELETE.this.oTFDelGrund.getText()).trim();
			if (cText.equals("") || cText.length()>200)
				vRueck.add_MESSAGE(new MyE2_Alarm_Message("Bitte einen Grund angeben (<= 200 Zeichen) !!!"), false);
			
			return vRueck;
		}

		public MyE2_MessageVector isValid(String cID)
		{
			return new MyE2_MessageVector();
		}

	}


	public Vector<XX_ActionAgent> get_vWeitereActionsAfterDeleteOK()
	{
		return vWeitereActionsAfterDeleteOK;
	}




}
