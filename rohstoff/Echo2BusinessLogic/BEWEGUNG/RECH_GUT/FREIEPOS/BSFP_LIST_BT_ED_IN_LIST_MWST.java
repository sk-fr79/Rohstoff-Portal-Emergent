package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldWithSelectorForNumbers;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.My_MWSTSaetze;
import echopointng.KeyStrokeListener;

public class BSFP_LIST_BT_ED_IN_LIST_MWST extends MyE2_DB_Button 
{

	public BSFP_LIST_BT_ED_IN_LIST_MWST(SQLField osqlField) throws myException 
	{
		super(osqlField);
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_IDValidator(new BSFP_VALID_ListenAenderungen());

	}

	
	/*
	 * actionAgent zum anzeigen eines eingabepopup-fensters zur eingabe eines einzelpreises
	 * bei einer freien position. Wird einem MyE2_DB_Button zugeordnet
	 */
	private class ownActionAgent extends XX_ActionAgent
	{

		private  String 								cMWSt = 			null;
		private  UB_TextFieldWithSelectorForNumbers 	oFieldInputMWSt = null;
		private  MyE2_ButtonWithKey 					oButtonSave 	  = null;
		private  MyE2_ButtonWithKey 					oButtonCancel 	  = null;
		private  String  								cID_VPOS_RG 	  = null; 
		
		private  E2_BasicModuleContainer  				oConfirmWindow = 	null; 
		private  E2_ComponentMAP 						oMap = null;
		private  SQLResultMAP 							oResult = null;   
		
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyE2_DB_Button oButton = (MyE2_DB_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			this.cID_VPOS_RG = oButton.get_cActualRowID();
			
			this.oMap = oButton.EXT().get_oComponentMAP();
			
			this.oResult = oMap.get_oInternalSQLResultMAP();
			
			try
			{
				bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(this.cID_VPOS_RG));
				
				if (bibMSG.get_bIsOK())
				{
					cMWSt = 			oResult.get_FormatedValue("STEUERSATZ");
					
					// jetzt die moeglichen mwst-saetze in das popup-fenster einbauen
					My_MWSTSaetze      oMWST = new My_MWSTSaetze(oResult.get_UnFormatedValue("ID_ADRESSE"),null); 
					
					oFieldInputMWSt =  new UB_TextFieldWithSelectorForNumbers("STEUERSATZ",false,cMWSt,3,oMWST.get_MWST_DropDownArray_AdressMWST(true),"--?--");
					oFieldInputMWSt.get_oPopUp().get_oContainerEx().setWidth(new Extent(80));
					oFieldInputMWSt.get_oPopUp().get_oContainerEx().setHeight(new Extent(100));
					
					oButtonSave 	  = new MyE2_ButtonWithKey("Speichern",KeyStrokeListener.VK_RETURN);
					oButtonCancel 	  = new MyE2_ButtonWithKey("Abbruch",KeyStrokeListener.VK_ESCAPE);
					
					oButtonSave.add_oActionAgent(new ActionAgentSave());
					oButtonCancel.add_oActionAgent(new ActionAgentCancel());
					
					if (bibMSG.get_bIsOK())
					{
						MyE2_Row   oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
						oRow.add(this.oFieldInputMWSt,E2_INSETS.I_2_0_2_0);
						oRow.add(this.oButtonSave,E2_INSETS.I_2_0_2_0);
						oRow.add(this.oButtonCancel,E2_INSETS.I_2_0_2_0);
						
						this.oConfirmWindow = new E2_BasicModuleContainer();
						this.oConfirmWindow.set_bVisible_Row_For_Messages(true);
						
						this.oConfirmWindow.add(oRow, E2_INSETS.I_10_10_10_10);
						this.oConfirmWindow.CREATE_AND_SHOW_POPUPWINDOW(new Extent(350),new Extent(150),new MyE2_String("Steuer eingeben ..."));
						ApplicationInstance.getActive().setFocusedComponent(this.oFieldInputMWSt.get_oTextField());
					}
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				ex.printStackTrace();
			}
			
		}



		private class ActionAgentSave extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownActionAgent oThis = ownActionAgent.this;
				
				MyE2_MessageVector  oMV = oThis.oFieldInputMWSt.get_MV_InputOK();
				
				oThis.oFieldInputMWSt.mark_ErrorInput(oMV.get_bIsOK());
				
				try
				{
				
					if (oMV.get_bIsOK())
					{
						if (oThis.oFieldInputMWSt.get_bFieldHasChanged())
						{
								
							String cSQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_RG SET "+
											oThis.oFieldInputMWSt.get_cUpdatePart()+
											" WHERE ID_VPOS_RG="+oThis.cID_VPOS_RG;
							
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(bibALL.get_Vector(cSQL),
																		true));
							
							if (bibMSG.get_bIsOK())
							{
								bibMSG.add_MESSAGE(new MyE2_Info_Message("Neuer Steuersatz wurde gespeichert !"));
								oThis.oConfirmWindow.CLOSE_AND_DESTROY_POPUPWINDOW(false);
								oThis.oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,false,null);
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Speichern !!"));
								bibMSG.add_ALARMMESSAGE_Vector_Untranslated(bibALL.get_Vector(cSQL));
							}
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Es ist nichts geändert worden !"));
							oThis.oConfirmWindow.CLOSE_AND_DESTROY_POPUPWINDOW(false);
						}
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Falsche Eingabe ..."));
						return;
						
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
			
		}

		
		private class ActionAgentCancel extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownActionAgent oThis = ownActionAgent.this;
				oThis.oConfirmWindow.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}


	}

}
