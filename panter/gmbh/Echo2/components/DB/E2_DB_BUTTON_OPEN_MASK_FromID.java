package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_MaskSaveButton;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.exceptions.myExceptionForUser;


/*
 * eine universelle button-vorlage um in der Liste auf IDs aktive buttons zum maskenoeffnen
 * zu erzeugen. 
 */



public class E2_DB_BUTTON_OPEN_MASK_FromID extends MyE2_DB_Button implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{
	
	private 	String 								cLAST_STATUS = null;
	private     E2_BasicModuleContainer_MASK		oModulContainerMASK = null;     // das ist die Maske


	private    	String   							cBUTTONKENNER_EDIT = null;
	private    	String   							cBUTTONKENNER_VIEW = null;
	private    	String   							cMODULKENNER_CALLINGLIST = null;
	private     MyString 							cNameStringForMessages = null;
	
	private     XX_ActionValidator					oAuthValidatorEDIT = null;
	private     XX_ActionValidator					oAuthValidatorVIEW = null;
	
	private     Vector<XX_ActionAgent>				vActionAgentsAfterSave = new Vector<XX_ActionAgent>();
	private     Vector<XX_ActionAgent>				vActionAgentsAfterCancel = new Vector<XX_ActionAgent>();

	private     MyE2_String   			  			cMeldungFuerNichtGefunden = new MyE2_String("Ich kann den Datensatz nicht identifizieren !"); 
	
	private     Vector<X_ActionBeforeExecuteButton>  vActionsBeforeExecute = new Vector<X_ActionBeforeExecuteButton>();

	//wenn die fuehrende ID aus SQLFieldmap list und mask gleich ist, dann kann die Navilist automatisch aktualisiert werden, sonst nicht
	private     boolean     						bID_LIST_equals_ID_MASK = true;
	

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
	public E2_DB_BUTTON_OPEN_MASK_FromID(	SQLField 						oSQLField,
											E2_BasicModuleContainer_MASK 	ModulContainerMASK,
											MyString						NameStringForMessages,
											String 							MODULKENNER_CALLING_LIST,
											String 							BUTTONKENNER_EDIT,
											String 							BUTTONKENNER_VIEW) throws myException
	{
		super(oSQLField);
		this.oModulContainerMASK = ModulContainerMASK;
		this.cBUTTONKENNER_EDIT = BUTTONKENNER_EDIT;
		this.cBUTTONKENNER_VIEW = BUTTONKENNER_VIEW;
		this.cMODULKENNER_CALLINGLIST = MODULKENNER_CALLING_LIST;
		this.cNameStringForMessages = NameStringForMessages;
		
		this.add_oActionAgent(new ownActionAgent());

		/*
		 * globale validierungen einfuegen
		 */
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
		
		if (this.EXT().get_oComponentMAP() != null)
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null)
			{
				this.setEnabled(false);               // heist neueingabe
				return;
			}
		
		if (S.isFull(this.EXT().get_C_MERKMAL()) && this.cLAST_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.setEnabled(true);
		}
		else
		{
			this.setEnabled(false);
		}
	}


	
	public void set_cActualMaskValue(String cText) throws myException
	{
		this.set_Text("");   // totstellen
	}

	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_Button:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");
								
		String cTest = bibALL.ReplaceTeilString(bibALL.null2leer(cText),".","");

		this.setText(cText);
		
		if (bibALL.isInteger(cTest))   // nur eingeschaltet, wenn was da ist
			this.EXT().set_C_MERKMAL(cTest);
		else
			this.EXT().set_C_MERKMAL(null);
			
		this.cLAST_STATUS = cMASK_STATUS;
		
		this.setText(S.isFull(cText)?cText:"-");
		this.set_bEnabled_For_Edit(true);     // definierten zustand einschalten
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
	}

	
	/*
	 * globale validierer werden hier intern verwaltet,
	 * um die erlaubnis edit/view zu pruefen
	 */ 
	public void add_GlobalValidator(XX_ActionValidator oValid)
	{
		return;
	}

	public void add_GlobalValidator(Vector<XX_ActionValidator> oValid)
	{
		return;
	}

	

	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		E2_DB_BUTTON_OPEN_MASK_FromID oButtCopy = null;
		
		try
		{
			oButtCopy = new E2_DB_BUTTON_OPEN_MASK_FromID(
											this.EXT_DB().get_oSQLField(),
											this.oModulContainerMASK,
											this.cNameStringForMessages,
											this.cMODULKENNER_CALLINGLIST,
											this.cBUTTONKENNER_EDIT,
											this.cBUTTONKENNER_VIEW);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("E2_BUTTON_OPEN_MASK_FromID:get_Copy:Copy-Error!");
		}
		
		oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
		if (this.getIcon() != null)
			oButtCopy.setIcon(this.getIcon());
		
		if (this.get_oText() != null)
			oButtCopy.set_Text(this.get_oText());
		
		oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
		
		oButtCopy.setStyle(this.getStyle());
		oButtCopy.setInsets(this.getInsets());
		oButtCopy.get_vActionAgentsAfterCancel().addAll(this.get_vActionAgentsAfterCancel());
		oButtCopy.get_vActionAgentsAfterSave().addAll(this.get_vActionAgentsAfterSave());
		
		oButtCopy.get_vActionsBeforeExecute().addAll(this.vActionsBeforeExecute);
		
		oButtCopy.set_bID_LIST_equals_ID_MASK(this.get_bID_LIST_equals_ID_MASK());
		
		return oButtCopy;
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
	//2012-10-16: aenderung: der mask-save-button wird formal von 
	//private class maskButtonSaveEdit extends E2_MaskSaveButton abgeleitet, wegen evtl. vorhandener suchmethoden
	private class maskButtonSaveEdit extends E2_MaskSaveButton
	{
		
		public maskButtonSaveEdit()
		{
			super(E2_ResourceIcon.get_RI("save.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo)
			{

				E2_DB_BUTTON_OPEN_MASK_FromID oThis = E2_DB_BUTTON_OPEN_MASK_FromID.this;
				
				E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oThis.oModulContainerMASK.get_vCombinedComponentMAPs();

				try
				{
					//E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oModulContainerMASK,oThis.oNaviList);
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oModulContainerMASK,
							                   E2_DB_BUTTON_OPEN_MASK_FromID.this.bID_LIST_equals_ID_MASK?oThis.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to():null);

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
						
						oThis.oModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				}
				catch (myExceptionForUser ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
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

				E2_DB_BUTTON_OPEN_MASK_FromID oThis = E2_DB_BUTTON_OPEN_MASK_FromID.this;

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
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_DB_BUTTON_OPEN_MASK_FromID 	oThis = 					E2_DB_BUTTON_OPEN_MASK_FromID.this;
//			E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oThis.oModulContainerMASK.get_vCombinedComponentMAPs();
			E2_DB_BUTTON_OPEN_MASK_FromID 	oButton = 					(E2_DB_BUTTON_OPEN_MASK_FromID)bibE2.get_LAST_ACTIONEVENT().getSource();

			if (oButton.EXT().get_C_MERKMAL() != null && bibALL.isInteger(oButton.EXT().get_C_MERKMAL()))
			{
				String cID = oButton.EXT().get_C_MERKMAL();
				
				for (int i=0;i<oThis.vActionsBeforeExecute.size();i++)
				{
					oThis.vActionsBeforeExecute.get(i).do_Action(cID);
				}
				
				
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
						this.buildEditMask(oThis, cID);
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
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(E2_DB_BUTTON_OPEN_MASK_FromID.this.cMeldungFuerNichtGefunden), true);
			}
		}

	
	
		private void buildViewMask(E2_DB_BUTTON_OPEN_MASK_FromID oThis, String cID) throws myException
		{
//			oThis.oModulContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,cID);
			oThis.oModulContainerMASK.get_oRowForButtons().removeAll();
			
			oThis.oModulContainerMASK.get_oRowForButtons().add(	new E2_ComponentGroupHorizontal(0,
																	new maskButtonCancel(),
																	new Insets(0,2,4,2)));
			
			oThis.put_SpecialButtonsToRowForButtons_VIEW(oThis.oModulContainerMASK.get_oRowForButtons());

			oThis.oModulContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,cID);
			
			oThis.oModulContainerMASK.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);
			
			MyE2_String cHelp = new MyE2_String("Anzeigen Datensatz");
			MyE2_String cHelp2 = new MyE2_String("ID:");
			bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(cHelp.CTrans()+" ("+oThis.get_cNameStringForMessages().CTrans()+") "+cHelp2.CTrans()+" "+cID));
		}
		

		private void buildEditMask(E2_DB_BUTTON_OPEN_MASK_FromID oThis, String cID) throws myException
		{
			//oThis.oModulContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID);
			oThis.oModulContainerMASK.get_oRowForButtons().removeAll();
			
			oThis.oModulContainerMASK.get_oRowForButtons().add(	new E2_ComponentGroupHorizontal(0,
																	new maskButtonSaveEdit(),
																	new maskButtonCancel(),
																	new Insets(0,2,4,2)));

			oThis.put_SpecialButtonsToRowForButtons_EDIT(oThis.oModulContainerMASK.get_oRowForButtons());
			
			oThis.oModulContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID);
			
			oThis.oModulContainerMASK.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);
			MyE2_String cHelp = new MyE2_String("Bearbeiten Datensatz");
			MyE2_String cHelp2 = new MyE2_String("ID:");
			bibMSG.add_MESSAGE(new MyE2_Info_Message(cHelp.CTrans()+" ("+oThis.get_cNameStringForMessages().CTrans()+") "+cHelp2.CTrans()+" "+cID,false),true);
		}

		
	}
	
	
	
	// ueberschreiben, wenn noetig
	public void put_SpecialButtonsToRowForButtons_EDIT(MyE2_Row oRowForButtons)
	{
		if (oRowForButtons !=null)
			return;
	}
	
	public void put_SpecialButtonsToRowForButtons_VIEW(MyE2_Row oRowForButtons)
	{
		if (oRowForButtons !=null)
			return;
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

	
	
	
	public abstract class X_ActionBeforeExecuteButton
	{
		public abstract void do_Action(String cID) throws myException;
	}

	public Vector<X_ActionBeforeExecuteButton> get_vActionsBeforeExecute()
	{
		return vActionsBeforeExecute;
	}
	
	public String get_cBUTTONKENNER_EDIT() 
	{
		return cBUTTONKENNER_EDIT;
	}

	public String get_cBUTTONKENNER_VIEW() 
	{
		return cBUTTONKENNER_VIEW;
	}

	public boolean get_bID_LIST_equals_ID_MASK() 
	{
		return bID_LIST_equals_ID_MASK;
	}


	public void set_bID_LIST_equals_ID_MASK(boolean bID_LIST_equals_ID_MASK) 
	{
		this.bID_LIST_equals_ID_MASK = bID_LIST_equals_ID_MASK;
	}


	
}
