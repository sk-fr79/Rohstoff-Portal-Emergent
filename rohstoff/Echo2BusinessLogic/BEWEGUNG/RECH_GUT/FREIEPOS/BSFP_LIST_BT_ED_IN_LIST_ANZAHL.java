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
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForNumbers;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySQL_StatementUPDATE;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PreisCalculator;
import echopointng.KeyStrokeListener;

public class BSFP_LIST_BT_ED_IN_LIST_ANZAHL extends MyE2_DB_Button 
{

	public BSFP_LIST_BT_ED_IN_LIST_ANZAHL(SQLField osqlField) throws myException 
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

		private  String 								cAnzahl = 			null;
		private  UB_TextFieldForNumbers 				oFieldInputAnzahl = null;
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
					cAnzahl = 			oResult.get_FormatedValue("ANZAHL");
					oFieldInputAnzahl = new UB_TextFieldForNumbers("ANZAHL",3,false,cAnzahl,100,14);
					oButtonSave 	  = new MyE2_ButtonWithKey("Speichern",KeyStrokeListener.VK_RETURN);
					oButtonCancel 	  = new MyE2_ButtonWithKey("Abbruch",KeyStrokeListener.VK_ESCAPE);
					
					oButtonSave.add_oActionAgent(new ActionAgentSave());
					oButtonCancel.add_oActionAgent(new ActionAgentCancel());
					
					MyE2_Row   oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
					oRow.add(this.oFieldInputAnzahl,E2_INSETS.I_2_0_2_0);
					oRow.add(this.oButtonSave,E2_INSETS.I_2_0_2_0);
					oRow.add(this.oButtonCancel,E2_INSETS.I_2_0_2_0);
					
					this.oConfirmWindow = new E2_BasicModuleContainer();
					this.oConfirmWindow.set_bVisible_Row_For_Messages(true);
					
					this.oConfirmWindow.add(oRow, E2_INSETS.I_10_10_10_10);
					this.oConfirmWindow.CREATE_AND_SHOW_POPUPWINDOW(new Extent(350),new Extent(150),new MyE2_String("Menge eingeben ..."));
					ApplicationInstance.getActive().setFocusedComponent(this.oFieldInputAnzahl);
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
				
				oThis.oFieldInputAnzahl.mark_ErrorInput(oThis.oFieldInputAnzahl.get_MV_InputOK().get_bIsOK());
				
				try
				{
				
					if (oThis.oFieldInputAnzahl.get_MV_InputOK().get_bIsOK())
					{
						if (oThis.oFieldInputAnzahl.get_bFieldHasChanged())
						{
							
							BS_PreisCalculator oBSPC = new BS_PreisCalculator( 	oThis.oFieldInputAnzahl.getText(),
																				oThis.oResult.get_FormatedValue("EINZELPREIS"),
																				oThis.oResult.get_FormatedValue("MENGENDIVISOR"),
																				oThis.oResult.get_FormatedValue("WAEHRUNGSKURS"),
																				true);
							String cErgebnis = "0";
							if (oBSPC.get_dGesamtPreis() != null)
							{
								cErgebnis = MyNumberFormater.formatDez(oBSPC.get_dGesamtPreis().doubleValue(),2,false,'.',',');
							}
							
							String[][] cFelder = new String[2][2];
							cFelder[0][0]=oThis.oFieldInputAnzahl.get_cDBFieldName(); 	cFelder[0][1]=oThis.oFieldInputAnzahl.get_cInsertValuePart();
							cFelder[1][0]="GESAMTPREIS"; 								cFelder[1][1]=cErgebnis;
							
							MySQL_StatementUPDATE oSTMT = new MySQL_StatementUPDATE("JT_VPOS_RG","ID_VPOS_RG",oThis.cID_VPOS_RG,cFelder);

							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(	oSTMT.get_vSQL_StatementStrings(),
																			true));
							if (bibMSG.get_bIsOK())
							{
								bibMSG.add_MESSAGE(new MyE2_Info_Message("Neue Menge wurde gespeichert !"));
								oThis.oConfirmWindow.CLOSE_AND_DESTROY_POPUPWINDOW(false);
								oThis.oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,false,null);
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Speichern !!"));
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
