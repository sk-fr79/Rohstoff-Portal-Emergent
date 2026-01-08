package rohstoff.Echo2BusinessLogic._4_ALL;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_MaskSaveButton;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public abstract class BT_OpenMaskByID extends MyE2_Button {

	//abstrakte
	public abstract MyE2_MessageVector 				do_AfterCreatedAndFilledMaskObject(E2_BasicModuleContainer_MASK oMaskContainer, String cID_MainTable_UF, String cEDITSTATUS) throws myException;
	public abstract E2_BasicModuleContainer_MASK  	get_BasicModuleContainer_MASK() throws myException;
	public abstract MyE2_MessageVector 				do_AfterPopupMask(E2_BasicModuleContainer_MASK oMaskContainer, String cID_MainTable_UF, String cEDITSTATUS) throws myException;
	
	private String cID_BASICTABLE_UF = null;
	private String cSTATUS_MASKE = null;
	


	private E2_ButtonAUTHValidator oValidEDIT = null;	//new E2_ButtonAUTHValidator(E2_MODULNAMES.MODUL_KENNER_PROGRAMM_WIDE,cBUTTONKENNER_EDIT);
	private E2_ButtonAUTHValidator oValidVIEW = null; //new E2_ButtonAUTHValidator(E2_MODULNAMES.MODUL_KENNER_PROGRAMM_WIDE,cBUTTONKENNER_VIEW);
	
	private E2_BasicModuleContainer_MASK 	oMaskContainer = null;
	

	private boolean   b_ShowSaveAndReload =true;
	
	private MyE2_String  c_Titel4Popup = new MyE2_String("Test");
	
	/**
	 * Methode setzt den Edit-Validierer für die Maske
	 * @return
	 */
	public abstract E2_ButtonAUTHValidator getValdiatorEdit();
	
	/**
	 * Methode setzt den View-Validierer für die Maske
	 * @return
	 */
	public abstract E2_ButtonAUTHValidator getValdiatorView();
	
	
	
	public BT_OpenMaskByID(MyString cText, E2_MutableStyle oStyle, MyString cToolTips, XX_ActionAgent oAgent) {
		super(cText, oStyle, cToolTips, oAgent);
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	
	public BT_OpenMaskByID(MyString cText, E2_MutableStyle oStyle) {
		super(cText, oStyle);
		
		this.add_oActionAgent(new ownActionAgent());
	}
	
	

	public BT_OpenMaskByID(ImageReference oImg, ImageReference oimgDisabled) {
		super(oImg, oimgDisabled);
		
		this.add_oActionAgent(new ownActionAgent());
	}





	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			BT_OpenMaskByID oThis = BT_OpenMaskByID.this;
			
			oThis.oMaskContainer = oThis.get_BasicModuleContainer_MASK();
			
			oThis.makeValidationAndOpenMask();
			
		}
		
	}
	

	private MyE2_MessageVector makeValidationAndOpenMask() throws myException {
		
		// holen der Validierer
		this.oValidEDIT = getValdiatorEdit();
		this.oValidVIEW = getValdiatorView();
		
		
		// Per default ist die Validierung OK, ausser es sind Validierer definiert.
		// Dann werden diese ausgewertet
		boolean bEditOK = true;
		boolean bViewOK = true;
		MyE2_MessageVector oMV_ValidEdit = new MyE2_MessageVector();
		MyE2_MessageVector oMV_ValidView = new MyE2_MessageVector();
		if (this.oValidEDIT != null) {
			 oMV_ValidEdit = this.valid_SingleGlobalValidator(this.oValidEDIT);
		}

		if (this.oValidVIEW != null){
			 oMV_ValidView = this.valid_SingleGlobalValidator(this.oValidVIEW);
		}
		 
		
		bEditOK = oMV_ValidEdit.get_bIsOK();
		bViewOK = oMV_ValidView.get_bIsOK();

		
		if (bEditOK)
		{
			this.buildMask(E2_ComponentMAP.STATUS_EDIT);
		}
		else if (bViewOK)
		{
			this.buildMask(E2_ComponentMAP.STATUS_VIEW);
		}
		else
		{
			bibMSG.add_MESSAGE(oMV_ValidEdit);
			bibMSG.add_MESSAGE(oMV_ValidView);
		}
		
		return oMV_ValidEdit;
	}
	
	private void buildMask(String cSTATUS) throws myException {
		
		this.oMaskContainer.get_oRowForButtons().removeAll();
		if (cSTATUS.equals(E2_ComponentMAP.STATUS_EDIT))  {
			this.oMaskContainer.get_oRowForButtons().add(new maskButtonSaveEdit(), E2_INSETS.I(0,2,10,2));
			if (this.b_ShowSaveAndReload) {
				this.oMaskContainer.get_oRowForButtons().add(new maskButtonSaveEditAndKeepOpen(), E2_INSETS.I(0,2,10,2));
			}
			this.oMaskContainer.get_oRowForButtons().add(new maskButtonCancel(), E2_INSETS.I(0,2,10,2));
		} else {
			this.oMaskContainer.get_oRowForButtons().add(new maskButtonCancel(), E2_INSETS.I(0,2,10,2));
		}
		
		
		E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskContainer.get_vCombinedComponentMAPs();
		vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,this.cID_BASICTABLE_UF);
		
		MyE2_MessageVector oMV =this.do_AfterCreatedAndFilledMaskObject(oMaskContainer,this.cID_BASICTABLE_UF,cSTATUS);
		
		if (oMV.get_bIsOK()) {
			oMaskContainer.CREATE_AND_SHOW_POPUPWINDOW(null,null,this.c_Titel4Popup);
		} 

		MyE2_MessageVector oMV2 =this.do_AfterPopupMask(oMaskContainer,this.cID_BASICTABLE_UF,cSTATUS);
		
		bibMSG.add_MESSAGE(oMV);
		bibMSG.add_MESSAGE(oMV2);
		
	}
	
	

	public String get_cID_BASICTABLE_UF() {
		return cID_BASICTABLE_UF;
	}

	public void set_cID_BASICTABLE_UF(String idBASICTABLE_UF) {
		this.cID_BASICTABLE_UF = idBASICTABLE_UF;
	}

	public String get_cSTATUS_MASKE() {
		return cSTATUS_MASKE;
	}

	public void set_cSTATUS_MASKE(String statusMASKE) {
		this.cSTATUS_MASKE = statusMASKE;
	}

	/*
	 * der save-button fuer die maske
	 */
	private class maskButtonSaveEdit extends E2_MaskSaveButton
	{
		public maskButtonSaveEdit()	{
			super(E2_ResourceIcon.get_RI("save.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo)
			{

				BT_OpenMaskByID oThis = BT_OpenMaskByID.this;
				
				E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oThis.oMaskContainer.get_vCombinedComponentMAPs();

				try
				{
					//E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oModulContainerMASK,oThis.oNaviList);
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oMaskContainer, null);

					oSaveMask.doSaveMask(false);
					if (bibMSG.get_bIsOK())
					{
						MyE2_String cHelp = new MyE2_String("Der aktuelle Datensatz ");
						MyE2_String cHelp2 = new MyE2_String("wurde gespeichert");
						bibMSG.add_MESSAGE(new MyE2_Info_Message(
								new MyE2_String(cHelp.CTrans()+" "+cHelp2.CTrans()+"   ID:"+ vCombined_E2_ComponentMaps.get_actual_UNFormated_ROWID_of_Leading_ComponentMAP(),false)),true);
						
						oThis.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
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

				BT_OpenMaskByID oThis = BT_OpenMaskByID.this;

				oThis.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Editieren abgebrochen ..."));
				
				try
				{
					oThis.oMaskContainer.get_vCombinedComponentMAPs().do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
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
	 * 2014-03-12: der save-button fuer die maske mit der moeglichkeit, die maske offen zu halten
	 */
	private class maskButtonSaveEditAndKeepOpen extends MyE2_Button
	{
		
		public maskButtonSaveEditAndKeepOpen()
		{
			super(E2_ResourceIcon.get_RI("save_edit.png"), true);
			this.setToolTipText(new MyE2_String("Maske speichern, anschließend weiterbearbeiten").CTrans());

			this.add_oActionAgent(new ownActionAgent2());

		}

		private class ownActionAgent2 extends XX_ActionAgent
		{

			public ownActionAgent2() 
			{
				super();
			}


			public void executeAgentCode(ExecINFO oExecInfo)
			{
				BT_OpenMaskByID oThis = BT_OpenMaskByID.this;

				try
				{
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oMaskContainer,	null);
					try
					{
						oSaveMask.doSaveMask(true);
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Der aktuelle Datensatz wurde gespeichert: ID "+oSaveMask.get_cActualMaskID_Unformated()), false);
						}
					}
					catch (myExceptionForUser exx)
					{
						exx.printStackTrace();
						bibMSG.add_MESSAGE(exx.get_ErrorMessage(), false);
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}


	public boolean get_bShowSaveAndReload() {
		return b_ShowSaveAndReload;
	}
	public void set_bShowSaveAndReload(boolean bShowSaveAndReload) {
		this.b_ShowSaveAndReload = bShowSaveAndReload;
	}
	public E2_BasicModuleContainer_MASK get_oMaskContainer() {
		return oMaskContainer;
	}
	public MyE2_String get_cTitel4Popup() {
		return this.c_Titel4Popup;
	}
	public void set_cTitel4Popup(MyE2_String Titel4Popup) {
		this.c_Titel4Popup = Titel4Popup;
	}
}
	