package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_MaskSaveButton;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;


/*
 * eine universelle button-vorlage um in der Liste auf IDs aktive buttons zum maskenoeffnen
 * zu erzeugen. 
 */



public abstract class RB_bt_open_adresse_fromID extends MyE2_Button implements MyE2IF__Component, E2_IF_Copy {
	
	private     MyString 								cNameStringForMessages = null;
	
	private     Vector<XX_ActionValidator_NG>      		v_AuthValidatorEDIT = new Vector<>();
	private     Vector<XX_ActionValidator_NG>			v_AuthValidatorVIEW = new Vector<>();
	
	private     Vector<XX_ActionAgent>					vActionAgentsAfterSave = new Vector<XX_ActionAgent>();
	private     Vector<XX_ActionAgent>					vActionAgentsAfterCancel = new Vector<XX_ActionAgent>();

	private     MyE2_String   			  				cMeldungFuerNichtGefunden = new MyE2_String("Ich kann den Datensatz nicht identifizieren !"); 
	
	private     Vector<X_ActionBeforeExecuteButton>  	vActionsBeforeExecute = new Vector<X_ActionBeforeExecuteButton>();

	private     FS_ModulContainer_MASK      			adress_mask = null;
	
	
	
	//ABSTRACTS
	public abstract String find_id_adresse_uf()  throws myException;  		
	public abstract void   add_something_to_maskbutton_row(MyE2_Row maskButtonRow, boolean statusEdit) throws myException;
	//---------
	

	/**
	 * @param oSQLField
	 * @param ModulContainerMASK
	 * @param NameStringForMessages
	 * @param MODULKENNER_CALLING_LIST
	 * @param BUTTONKENNER_EDIT
	 * @param BUTTONKENNER_VIEW
	 * @param oNaviList
	 * @throws myException
	 */
	public RB_bt_open_adresse_fromID(MyString NameStringForMessages) throws myException
	{
		super(E2_ResourceIcon.get_RI("edit_list3.png"));
		this.cNameStringForMessages = NameStringForMessages;
		
		this.add_oActionAgent(new ownActionAgent());
	}

	

	public Object get_Copy(Object ob) throws myExceptionCopy	{
		throw new myExceptionCopy("E2_BUTTON_OPEN_MASK_FromID:get_Copy:Copy-Error!");
	}


	public MyString get_cNameStringForMessages() {
		return cNameStringForMessages;
	}

	
	/*
	 * der save-button fuer die maske
	 */
	private class maskButtonSaveEdit extends E2_MaskSaveButton {
		
		public maskButtonSaveEdit()	{
			super(E2_ResourceIcon.get_RI("save.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent	{

			public void executeAgentCode(ExecINFO oExecInfo){

				RB_bt_open_adresse_fromID oThis = RB_bt_open_adresse_fromID.this;
				
				E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oThis.adress_mask.get_vCombinedComponentMAPs();

				try 		{
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.adress_mask,null);

					oSaveMask.doSaveMask(false);
					if (bibMSG.get_bIsOK()) {
						
						// jetzt die anderen actionagents ausfuehren
						if (oThis.vActionAgentsAfterSave.size()>0)	{
							for (int i=0;i<oThis.vActionAgentsAfterSave.size();i++)	{
								((XX_ActionAgent)oThis.vActionAgentsAfterSave.get(i)).ExecuteAgentCode(new ExecINFO_OnlyCode());
							}
						}
	
						MyE2_String cHelp = new MyE2_String("Der aktuelle Datensatz ");
						MyE2_String cHelp2 = new MyE2_String("wurde gespeichert");
						bibMSG.add_MESSAGE(new MyE2_Info_Message(
								new MyE2_String(cHelp.CTrans()+"("+oThis.cNameStringForMessages.CTrans()+") "+cHelp2.CTrans()+"   ID:"+ vCombined_E2_ComponentMaps.get_actual_UNFormated_ROWID_of_Leading_ComponentMAP(),false)),true);
						
						oThis.adress_mask.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				}
				catch (myExceptionForUser ex) {
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				} 
				catch (myException ex) {
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}
	

	
	private class maskButtonCancel extends MyE2_Button {
		
		public maskButtonCancel() {
			super(E2_ResourceIcon.get_RI("cancel.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		
		private class ownActionAgent extends XX_ActionAgent {

			public void executeAgentCode(ExecINFO oExecInfo) throws myException	{

				RB_bt_open_adresse_fromID oThis = RB_bt_open_adresse_fromID.this;

				oThis.adress_mask.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				
				// jetzt die anderen actionagents ausfuehren
				if (oThis.vActionAgentsAfterCancel.size()>0) {
					for (int i=0;i<oThis.vActionAgentsAfterCancel.size();i++)	{
						((XX_ActionAgent)oThis.vActionAgentsAfterCancel.get(i)).ExecuteAgentCode(new ExecINFO_OnlyCode());
					}
				}
				
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Editieren abgebrochen ..."));
				try	{
					oThis.adress_mask.get_vCombinedComponentMAPs().do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
				} catch (myException ex) {
					ex.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("maskButtonCancelMask:ownActionAgent:doAction:"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}
	}


	

	
	
	/*
	 * ActionAgent fuer den button an sich
	 */
	private class ownActionAgent extends XX_ActionAgent {
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
			RB_bt_open_adresse_fromID 	oThis = RB_bt_open_adresse_fromID.this;
			oThis.adress_mask = new FS_ModulContainer_MASK();

			String cID = oThis.find_id_adresse_uf();
				
			if (S.isFull(cID)) {
			
				for (int i=0;i<oThis.vActionsBeforeExecute.size();i++)	{
					oThis.vActionsBeforeExecute.get(i).do_Action(cID);
				}
				
			
				MyE2_MessageVector oMV_ValidEdit = new MyE2_MessageVector();
				if (oThis.v_AuthValidatorEDIT.size()!=0)	{
					for (XX_ActionValidator_NG v: oThis.v_AuthValidatorEDIT) {
						oMV_ValidEdit.add_MESSAGE(v.isValid(null));
					}
				}
					
				MyE2_MessageVector oMV_ValidView = new MyE2_MessageVector();;
				if (oThis.v_AuthValidatorVIEW.size()!=0)	{
					for (XX_ActionValidator_NG v: oThis.v_AuthValidatorVIEW) {
						oMV_ValidView.add_MESSAGE(v.isValid(null));
					}
				}
					
				boolean bEditOK = oMV_ValidEdit.get_bIsOK();
				boolean bViewOK = oMV_ValidView.get_bIsOK();
		
				if (bEditOK) {
					this.buildEditMask(oThis, cID);
				} else {
					if (bViewOK) {
						this.buildViewMask(oThis,cID);
					} 
				}
				
				if (!bEditOK) {
					bibMSG.add_MESSAGE(oMV_ValidEdit);
				}
				if (!bViewOK) {
					bibMSG.add_MESSAGE(oMV_ValidEdit);
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(RB_bt_open_adresse_fromID.this.cMeldungFuerNichtGefunden), true);
			}
		}

	
		private void buildViewMask(RB_bt_open_adresse_fromID oThis, String cID) throws myException	{
			oThis.adress_mask.get_oRowForButtons().removeAll();
			
			oThis.adress_mask.get_oRowForButtons().add(	new E2_ComponentGroupHorizontal(0,
																	new maskButtonCancel(),
																	new Insets(0,2,4,2)));
			
			oThis.add_something_to_maskbutton_row(oThis.adress_mask.get_oRowForButtons(), false);

			oThis.adress_mask.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,cID);
			
			oThis.adress_mask.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);
			
			MyE2_String cHelp = new MyE2_String("Anzeigen Adresse ");
			MyE2_String cHelp2 = new MyE2_String("ID:");
			bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(cHelp.CTrans()+" ("+oThis.get_cNameStringForMessages().CTrans()+") "+cHelp2.CTrans()+" "+cID));
		}
		

		private void buildEditMask(RB_bt_open_adresse_fromID oThis, String cID) throws myException	{
			oThis.adress_mask.get_oRowForButtons().removeAll();
			
			oThis.adress_mask.get_oRowForButtons().add(	new E2_ComponentGroupHorizontal(0,new maskButtonSaveEdit(),	new maskButtonCancel(),	new Insets(0,2,4,2)));

			oThis.add_something_to_maskbutton_row(oThis.adress_mask.get_oRowForButtons(), true);
			
			oThis.adress_mask.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID);
			
			oThis.adress_mask.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);
			MyE2_String cHelp = new MyE2_String("Bearbeiten Adresse");
			MyE2_String cHelp2 = new MyE2_String("ID:");
			bibMSG.add_MESSAGE(new MyE2_Info_Message(cHelp.CTrans()+" ("+oThis.get_cNameStringForMessages().CTrans()+") "+cHelp2.CTrans()+" "+cID,false),true);
		}

		
	}
	
	
	


	public Vector<XX_ActionAgent> get_vActionAgentsAfterCancel()
	{
		return vActionAgentsAfterCancel;
	}


	public Vector<XX_ActionAgent> get_vActionAgentsAfterSave()
	{
		return vActionAgentsAfterSave;
	}
	
	public MyE2_String get_cMeldungFuerNichtGefunden()
	{
		return cMeldungFuerNichtGefunden;
	}


	public void set_cMeldungFuerNichtGefunden(MyE2_String meldungFuerNichtGefunden)
	{
		cMeldungFuerNichtGefunden = meldungFuerNichtGefunden;
	}

	
	public abstract class X_ActionBeforeExecuteButton {
		public abstract void do_Action(String cID) throws myException;
	}

	public Vector<X_ActionBeforeExecuteButton> get_vActionsBeforeExecute() {
		return vActionsBeforeExecute;
	}
	
	
	public Vector<XX_ActionValidator_NG> get_vAuthValidatorEDIT() {
		return this.v_AuthValidatorEDIT;
	}
	
	public Vector<XX_ActionValidator_NG> get_vAuthValidatorVIEW() {
		return this.v_AuthValidatorVIEW;
	}

	
}
