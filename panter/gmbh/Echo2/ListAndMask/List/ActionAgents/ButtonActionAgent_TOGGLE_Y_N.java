package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer.XX_BuildAddonComponents_4_Dialog;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public abstract class ButtonActionAgent_TOGGLE_Y_N extends XX_ActionAgent
{
	private E2_NavigationList 			oNavigationList = null;


	private MyE2_String 				cNameOfAction = null;
	
	private String 						cFieldName_YES_NO = null;
	private String 						cTableName = 		null;
	private String 						cIDTableName = 		null;
	
	private String 						cNEW_Value = null;
	
	private Vector<XX_ActionAgent>   	vZusatzAgents = new Vector<XX_ActionAgent>();
	
	private XX_BuildAddonComponents_4_Dialog   oAddonDialogBuilder = null;


	private String 						cMessageText = "";
	
	public ButtonActionAgent_TOGGLE_Y_N(	MyE2_String 		actionName,
											E2_NavigationList 	onavigationList,
											String    			FieldNAME_YES_NO,
											String    			TABLENAME,
											String 				ID_TABLENAME)
	{
		super();
		this.oNavigationList = 		onavigationList;
		this.cNameOfAction = 		actionName;
		this.cFieldName_YES_NO = 	FieldNAME_YES_NO;
		this.cTableName = 			TABLENAME;
		this.cIDTableName = 		ID_TABLENAME;
	}

	
	public E2_NavigationList get_oNavigationList()
	{
		return this.oNavigationList;
	}
	
	public void executeAgentCode(ExecINFO oExecInfo)  throws myException
	{
		Vector<String> vIDsToToggle = new Vector<String>();
		
		if (this.oNavigationList != null)
		{
			vIDsToToggle.addAll(this.oNavigationList.get_vSelectedIDs_Unformated());
		}
		else
		{
			vIDsToToggle.addAll(this.get_IDS_FOR_Toggle());
		}
		
		
		if (vIDsToToggle.size() == 0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+new MyE2_String("  Bitte mindestens einen Datensatz auswählen !!").CTrans(),false));
		}
		else
		{
			
			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(vIDsToToggle);
			
			if (bibMSG.get_bIsOK())
			{
			
				Vector<String> vIDs_ForSQL_Statemtents = new Vector<String>();
				// jetzt zuerst evtl. ids umsetzen 
				try
				{
					for (int i=0;i<vIDsToToggle.size();i++)
					{
						vIDs_ForSQL_Statemtents.add(this.Create_ID_FOR_UpdateStatement((String)vIDsToToggle.get(i)));
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Übersetzen der IDs!"));
				}

				if (bibMSG.get_bIsOK())
				{
					// zuerst den einheitlichen status der umschaltfelder pruefen
					String 			cSQL = 	"SELECT DISTINCT(  NVL("+this.cFieldName_YES_NO+",'N') ) FROM "+bibE2.cTO()+"."+this.cTableName+
													" WHERE "+this.cIDTableName+" IN ("+bibALL.ConcatenateWithoutException(vIDs_ForSQL_Statemtents,",","-1")+")";
					
					String[][] cInfo = bibDB.EinzelAbfrageInArray(cSQL);
					
					if (cInfo == null || cInfo.length==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error Quering Status of Togglefield ...",false));
					}
					else if (cInfo.length != 1)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Änderung ist nur möglich, wenn alle Datensätze den gleichen Ausgangswert haben !"));
					}
					else
					{
						if (bibMSG.get_bIsOK())
						{
							this.cNEW_Value = "Y";
							if (cInfo[0][0].toUpperCase().equals("Y"))
								this.cNEW_Value = "N";
							
							
							
							
							if (bibMSG.get_bIsOK())
							{
								E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
																	new MyE2_String("Sicherheitsabfrage:"),
																	this.cNameOfAction,
																	new MyE2_String(cMessageText),
																	this.oAddonDialogBuilder,
																	new MyE2_String("JA"),
																	new MyE2_String("NEIN"),
																	new Extent(300),
																	new Extent(400));
								
								
								Vector<XX_ActionAgent>   vActions = new Vector<XX_ActionAgent>();
								vActions.add(new ownActionAgent_Toggle(vIDs_ForSQL_Statemtents,this.oNavigationList));
								vActions.addAll(this.vZusatzAgents);
								
								oConfirm.set_ActionAgentForOK(vActions);
								oConfirm.show_POPUP_BOX();
							}
						}
					}
				}
			}
		}
	}

	
	/*
	 * methode, um fuer aktionen, wo keine navilist uebergeben wird, trotzdem ids zu erzeugen
	 * zusammenzufuheren (normalerweise ist es die gleiche, kann aber unterschieden sein
	 */
	public Vector<String> get_IDS_FOR_Toggle() throws myException
	{
		return new Vector<String>();
	}
	

	public Vector<XX_ActionAgent> get_vZusatzAgents() 
	{
		return vZusatzAgents;
	}
	
	
	/**
	 * setzten des Zusatz-Meldungstextes, der im Dialog angezeigt wird
	 * @author manfred
	 * @date 14.07.2016
	 *
	 * @param sMessage
	 * @return
	 */
	public ButtonActionAgent_TOGGLE_Y_N setMessageText(String sMessage){
		this.cMessageText = sMessage;
		return this;
	}
	
	/**
	 * Setzen der Überschrift des Lösche-Buttons
	 * @author manfred
	 * @date 14.07.2016
	 *
	 * @param sHeading
	 * @return
	 */
	public ButtonActionAgent_TOGGLE_Y_N setHeadingText(String sHeading){
		this.cNameOfAction = new MyE2_String(sHeading);
		return this;
	}
	
	
	/*
	 * ueberschreib-methoden zum loeschen, einmal vor dem eigentlichen loeschvorgang, einmal nach dem eigentlichen loeschvorgang
	 * Zuerst SQL-Statemetnts, dann andere aktionen ()
	 */
	public abstract Vector<String> get_vSQL_Before_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException;
	public abstract Vector<String> get_vSQL_After_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException;
	public abstract  void Execute_Before_TOGGLE(Vector<String> vIDs_toToggleUnformated) throws myException;
	public abstract void Execute_After_TOGGLE(Vector<String> vIDs_toToggleUnformated) throws myException;
	
		
	
	/**
	 * @return s Vector of MyString-object with errormessages
	 * methode kann ueberschrieben werden, wenn eine pruefung auf erlaubnis erfolgen soll
	 */
	public abstract MyE2_MessageVector CheckIdToToggle(Vector<String> vID_UnformatedToDelete);
	
	
	
	/*
	 * methode, um die id aus der liste mit der id, die geaendert wird,
	 * zusammenzufuheren (normalerweise ist es die gleiche, kann aber unterschieden sein
	 */
	public String Create_ID_FOR_UpdateStatement(String cID_AUS_LISTE) throws myException
	{
		return cID_AUS_LISTE;
	}
	

	
	private class ownActionAgent_Toggle extends XX_ActionAgent
	{
		
		private Vector<String>   	vIDs_For_SQL   = null;
		private E2_NavigationList 	oNaviList = null;
		
		
		public ownActionAgent_Toggle(Vector<String> vIDs_ForSQL, E2_NavigationList onaviList)
		{
			super();
			this.vIDs_For_SQL   = vIDs_ForSQL;
			this.oNaviList = onaviList;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			
			/*
			 * evtl. die integritaetschecker ausfuehren
			 */
			ButtonActionAgent_TOGGLE_Y_N oThis = ButtonActionAgent_TOGGLE_Y_N.this;
			
			try
			{
				Vector<String> vALL_SQL = new Vector<String>();

				for (int i=0;i<this.vIDs_For_SQL.size();i++)
				{
					String 	cID_For_SQL = (String)this.vIDs_For_SQL.get(i);
					
					String 	cSQL = 	"UPDATE "+bibE2.cTO()+"."+oThis.cTableName+" SET "+oThis.cFieldName_YES_NO+"='"+oThis.cNEW_Value+"'"+
													" WHERE "+oThis.cIDTableName+"="+cID_For_SQL;

					vALL_SQL.addAll(bibALL.null2leerString(oThis.get_vSQL_Before_TOGGLE(cID_For_SQL,oThis.cNEW_Value)));
					vALL_SQL.add(cSQL);
					vALL_SQL.addAll(bibALL.null2leerString(oThis.get_vSQL_After_TOGGLE(cID_For_SQL,oThis.cNEW_Value)));
				}
				
			
				// dann aktionen vorher ausfuehren (keine SQL-aktioen
				ButtonActionAgent_TOGGLE_Y_N.this.Execute_Before_TOGGLE(this.vIDs_For_SQL);

				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vALL_SQL,true));
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Datensätze wurden geändert. IDs: "+bibALL.Concatenate(this.vIDs_For_SQL,",", null)), false);
					ButtonActionAgent_TOGGLE_Y_N.this.Execute_After_TOGGLE(this.vIDs_For_SQL);
					if (this.oNaviList!=null)
					{
						oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();						// liste bauen
						oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);						// anzeigen
					}
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("ButtonActionAgent_TOGGLE_Y_N:ownActionAgent_Toggle:Error:",false), false);
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}

	public void set_oAddonDialogBuilder(XX_BuildAddonComponents_4_Dialog oAddonDialogBuilder)
	{
		this.oAddonDialogBuilder = oAddonDialogBuilder;
	}




}
