package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.IF__BasicModuleContainer_PopUp_BeforeExecute_Container;
import panter.gmbh.Echo2.ActionEventTools.V_Single_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/*
 * eine universelle button-vorlage um in der Liste auf IDs aktive buttons zum maskenoeffnen
 * zu erzeugen. 
 */


public abstract class E2_BUTTON_OPEN_MASK_FromID extends MyE2_Button implements MyE2IF__Component, E2_IF_Copy
{
	
	private 	String 								cLAST_STATUS = null;
	
	private     E2_BasicModuleContainer_MASK		oModulContainerMASK = null;     // das ist die Maske
	private    	String   							cBUTTONKENNER_EDIT = null;
	private    	String   							cBUTTONKENNER_VIEW = null;
	private    	String   							cMODULKENNER_CALLINGLIST = null;
	private     MyString 							cNameStringForMessages = null;
	
	private     XX_ActionValidator					oAuthValidatorEDIT = null;
	private     XX_ActionValidator					oAuthValidatorVIEW = null;
	
	
	private     Vector<XX_ActionAgent> 				vActionAgentsAfterSave = new Vector<XX_ActionAgent>();
	private     Vector<XX_ActionAgent> 				vActionAgentsAfterCancel = new Vector<XX_ActionAgent>();
	
	
	private      MyE2_Button  						oButtonSaveMask = 	new maskButtonSaveEdit();
	private      MyE2_Button  						oButtonCancelMask = new maskButtonCancel();
	
	private      MyE2_String   			  			cMeldungFuerNichtGefunden = new MyE2_String("Ich kann den Datensatz nicht identifizieren !"); 
	

	public E2_BUTTON_OPEN_MASK_FromID(		E2_BasicModuleContainer_MASK 	ModulContainerMASK,
											MyString						NameStringForMessages,
											String 							MODULKENNER_CALLING_LIST,
											String 							BUTTONKENNER_EDIT,
											String 							BUTTONKENNER_VIEW) throws myException
	{
		super("-");
		
		
		this.oModulContainerMASK = ModulContainerMASK;
		this.cBUTTONKENNER_EDIT = BUTTONKENNER_EDIT;
		this.cBUTTONKENNER_VIEW = BUTTONKENNER_VIEW;
		this.cMODULKENNER_CALLINGLIST = MODULKENNER_CALLING_LIST;
		this.cNameStringForMessages = NameStringForMessages;
		this.add_oActionAgent(new ownActionAgent());

		if (! bibALL.isEmpty(this.cMODULKENNER_CALLINGLIST))
		{
			if (this.cBUTTONKENNER_VIEW != null)
				this.oAuthValidatorVIEW = new E2_ButtonAUTHValidator(this.cMODULKENNER_CALLINGLIST,cBUTTONKENNER_VIEW);
	
			if (this.cBUTTONKENNER_EDIT != null)
				this.oAuthValidatorEDIT = new E2_ButtonAUTHValidator(this.cMODULKENNER_CALLINGLIST,cBUTTONKENNER_EDIT);
		}
		else
		{
			if (this.cBUTTONKENNER_VIEW != null)
				this.oAuthValidatorVIEW = new E2_ButtonAUTHValidator_AUTO(cBUTTONKENNER_VIEW);
	
			if (this.cBUTTONKENNER_EDIT != null)
				this.oAuthValidatorEDIT = new E2_ButtonAUTHValidator_AUTO(cBUTTONKENNER_EDIT);
			
		}
	}

	
	/*
	 * wird nur enabled, wenn der letzte status view ist und das merkmal nicht null,
	 * in MERKMAL steht die fuer die Maske relevante ID 
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setVisible(false);
		
		if (this.EXT().get_oComponentMAP() != null)
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null)
			{
				this.setEnabled(false);               // heist neueingabe
				return;
			}
		
		if (this.EXT().get_C_MERKMAL()!=null && S.NN(this.cLAST_STATUS).equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.setVisible(true);
			this.setEnabled(true);
		}
		else
			this.setEnabled(false);
	}



	
	/*
	 * globale validierer werden hier intern verwaltet,
	 * um die erlaubnis edit/view zu pruefen
	 */ 
	public void add_GlobalValidator(XX_ActionValidator oValid)
	{
		return;
	}

	public void add_GlobalValidator(Vector<XX_ActionValidator> vValid)
	{
		return;
	}



	public String get_cLAST_STATUS()
	{
		return cLAST_STATUS;
	}


	public E2_BasicModuleContainer_MASK  get_oBasicModulContainer_MASK()
	{
		return this.oModulContainerMASK;
	}


	public MyString get_cNameStringForMessages()
	{
		return cNameStringForMessages;
	}


	public String get_cMODULKENNER_CALLINGLIST()
	{
		return cMODULKENNER_CALLINGLIST;
	}

	
	/*
	 * der save-button fuer die maske
	 */
	private class maskButtonSaveEdit extends MyE2_Button  implements 
														IF__BasicModuleContainer_PopUp_BeforeExecute_Container
	{
		
		public maskButtonSaveEdit()
		{
			super(E2_ResourceIcon.get_RI("save.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_BUTTON_OPEN_MASK_FromID oThis = E2_BUTTON_OPEN_MASK_FromID.this;
				
				E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oThis.oModulContainerMASK.get_vCombinedComponentMAPs();

				E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oModulContainerMASK,null);
				oSaveMask.doSaveMask(false);
				if (bibMSG.get_bIsOK())
				{
					// jetzt die anderen actionagents ausfuehren
					if (oThis.vActionAgentsAfterSave.size()>0)
					{
						for (int i=0;i<oThis.vActionAgentsAfterSave.size();i++)
						{
							((XX_ActionAgent)oThis.vActionAgentsAfterSave.get(i)).ExecuteAgentCode(new ExecINFO_OnlyCode());
						}
					}
					
					MyE2_String cHelp = new MyE2_String("Der aktuelle Datensatz ");
					MyE2_String cHelp2 = new MyE2_String("wurde gespeichert");
					bibMSG.add_MESSAGE(new MyE2_Info_Message(
							new MyE2_String(cHelp.CTrans()+"("+oThis.cNameStringForMessages.CTrans()+") "+cHelp2.CTrans()+"   ID:"+ vCombined_E2_ComponentMaps.get_actual_UNFormated_ROWID_of_Leading_ComponentMAP(),false)),true);
					
					vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
					oThis.oModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			}
		}



		@Override
		public V_Single_BasicModuleContainer_PopUp_BeforeExecute get_vE2_BasicModuleContainer_PopUp_BeforeExecute() 	throws myException
		{
			V_Single_BasicModuleContainer_PopUp_BeforeExecute  vRueck = new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
			
			
			//nachschauen, auf welcher maske der button liegt
			E2_RecursiveSearchParent_BasicModuleContainer oRecSearch = new E2_RecursiveSearchParent_BasicModuleContainer(this);
			
			if (oRecSearch.get_First_FoundContainer() instanceof E2_BasicModuleContainer_MASK)
			{
				E2_vCombinedComponentMAPs vMaps = ((E2_BasicModuleContainer_MASK) oRecSearch.get_First_FoundContainer()).get_vCombinedComponentMAPs();
				
				for (int i=0;i<vMaps.size();i++)
				{
					if (vMaps.get(i) instanceof IF__BasicModuleContainer_PopUp_BeforeExecute_Container)
					{
						vRueck.addAll(((IF__BasicModuleContainer_PopUp_BeforeExecute_Container)vMaps.get(i)).get_vE2_BasicModuleContainer_PopUp_BeforeExecute());
					}
				}
			}
			return vRueck;
		}
	}
	

	
	private class maskButtonCancel extends MyE2_Button
	{
		
		
		public maskButtonCancel()
		{
			super(E2_ResourceIcon.get_RI("cancel.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		
		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{

				E2_BUTTON_OPEN_MASK_FromID oThis = E2_BUTTON_OPEN_MASK_FromID.this;

				oThis.oModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				
				
				// jetzt die anderen actionagents ausfuehren
				if (oThis.vActionAgentsAfterCancel.size()>0)
				{
					for (int i=0;i<oThis.vActionAgentsAfterCancel.size();i++)
					{
						((XX_ActionAgent)oThis.vActionAgentsAfterCancel.get(i)).ExecuteAgentCode(new ExecINFO_OnlyCode());
					}
				}
				
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Editieren abgebrochen ..."));
				try
				{
					oThis.oModulContainerMASK.get_vCombinedComponentMAPs().do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("maskButtonCancelMask:ownActionAgent:doAction:"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}
	}


	
	/*
	 * ActionAgent fuer den button an sich
	 */
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_BUTTON_OPEN_MASK_FromID 	oThis = 						E2_BUTTON_OPEN_MASK_FromID.this;
			E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oThis.oModulContainerMASK.get_vCombinedComponentMAPs();
			E2_BUTTON_OPEN_MASK_FromID 	oButton = 						(E2_BUTTON_OPEN_MASK_FromID)bibE2.get_LAST_ACTIONEVENT().getSource();

			if (oButton.EXT().get_C_MERKMAL() != null)
			{
				String cID = oButton.EXT().get_C_MERKMAL();
				
				MyE2_MessageVector oMV_ValidEdit = null;
				if (oThis.oAuthValidatorEDIT != null)
				{
					oMV_ValidEdit = oButton.valid_SingleGlobalValidator(oThis.oAuthValidatorEDIT);
				}
				
				MyE2_MessageVector oMV_ValidView = null;
				if (oThis.oAuthValidatorVIEW != null)
				{
					oMV_ValidView = oButton.valid_SingleGlobalValidator(oThis.oAuthValidatorVIEW);
				}
				
				boolean bEditOK = (oMV_ValidEdit!=null) && oMV_ValidEdit.get_bIsOK();
				boolean bViewOK = (oMV_ValidView!=null) && oMV_ValidView.get_bIsOK();
	
				if (bEditOK)
				{
					MyE2_MessageVector vValid_id_Validation = oButton.valid_IDValidation(bibALL.get_Vector(cID));
					if (vValid_id_Validation.get_bIsOK())
					{
						vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID);
						
						oThis.oModulContainerMASK.get_oRowForButtons().removeAll();
						
						oThis.oModulContainerMASK.get_oRowForButtons().add(	new E2_ComponentGroupHorizontal(0,
																				E2_BUTTON_OPEN_MASK_FromID.this.oButtonSaveMask,
																				E2_BUTTON_OPEN_MASK_FromID.this.oButtonCancelMask,
																				new Insets(0,2,4,2)));

						oThis.put_SpecialButtonsToRowForButtons_EDIT(oThis.oModulContainerMASK.get_oRowForButtons());
						
						oThis.oModulContainerMASK.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);
						
						MyE2_String cHelp = new MyE2_String("Bearbeiten Datensatz");
						MyE2_String cHelp2 = new MyE2_String("ID:");
						bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(cHelp.CTrans()+" ("+oThis.get_cNameStringForMessages().CTrans()+") "+cHelp2.CTrans()+" "+cID));
					}
					else
					{
						// dann automatisch view-modus
						bibMSG.add_MESSAGE(vValid_id_Validation);
						this.buildViewMask(oThis,cID);
					}
				}
				else if (bViewOK)
				{
					this.buildViewMask(oThis,cID);
				}
				else
				{
					if (oMV_ValidEdit!=null) bibMSG.add_MESSAGE(oMV_ValidEdit);
					if (oMV_ValidView!=null) bibMSG.add_MESSAGE(oMV_ValidView);
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Berechtigungssituation ist nicht definiert !!"));
					}
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(E2_BUTTON_OPEN_MASK_FromID.this.cMeldungFuerNichtGefunden), true);
			}
		}

		
		private void buildViewMask(E2_BUTTON_OPEN_MASK_FromID oThis, String cID) throws myException
		{
			oThis.oModulContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,cID);
			
			oThis.oModulContainerMASK.get_oRowForButtons().removeAll();
			
			oThis.oModulContainerMASK.get_oRowForButtons().add(	new E2_ComponentGroupHorizontal(0,
																	new maskButtonCancel(),
																	new Insets(0,2,4,2)));
			
			oThis.put_SpecialButtonsToRowForButtons_VIEW(oThis.oModulContainerMASK.get_oRowForButtons());
			
			oThis.oModulContainerMASK.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);

			MyE2_String cHelp = new MyE2_String("Anzeigen Datensatz");
			MyE2_String cHelp2 = new MyE2_String("ID:");
			bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(cHelp.CTrans()+" ("+oThis.get_cNameStringForMessages().CTrans()+") "+cHelp2.CTrans()+" "+cID));

			
		}
		
		
	}
	
	
	
	// ueberschreiben, wenn noetig
	public abstract void put_SpecialButtonsToRowForButtons_EDIT(MyE2_Row oRowForButtons) throws myException;
	
	public abstract  void put_SpecialButtonsToRowForButtons_VIEW(MyE2_Row oRowForButtons) throws myException;


	public Vector<XX_ActionAgent> get_vActionAgentsAfterCancel()
	{
		return vActionAgentsAfterCancel;
	}


	public Vector<XX_ActionAgent> get_vActionAgentsAfterSave()
	{
		return vActionAgentsAfterSave;
	}


	public String get_cBUTTONKENNER_EDIT() 
	{
		return cBUTTONKENNER_EDIT;
	}


	public String get_cBUTTONKENNER_VIEW() 
	{
		return cBUTTONKENNER_VIEW;
	}


	public MyE2_Button get_oButtonSaveMask()
	{
		return oButtonSaveMask;
	}


	public MyE2_Button get_oButtonCancelMask()
	{
		return oButtonCancelMask;
	}


	public MyE2_String get_cMeldungFuerNichtGefunden()
	{
		return cMeldungFuerNichtGefunden;
	}


	public void set_cMeldungFuerNichtGefunden(MyE2_String meldungFuerNichtGefunden)
	{
		cMeldungFuerNichtGefunden = meldungFuerNichtGefunden;
	}

	
	
}
