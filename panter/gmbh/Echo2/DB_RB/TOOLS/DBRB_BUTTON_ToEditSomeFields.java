package panter.gmbh.Echo2.DB_RB.TOOLS;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.DB_RB.BASICS.DBRB_MASK;
import panter.gmbh.Echo2.DB_RB.BASICS.IF_DBRB_Component;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;


/**
 * abstrakte klasse, um schnellbearbeitungs-Knoepfe zu erstellen (z.B. bearbeitung einzelner Felder aus der liste heraus)
 * @author martin
 *
 */
public abstract class DBRB_BUTTON_ToEditSomeFields extends MyE2_ButtonInLIST {

	private MyE2_String 			cText4SaveButton = null;
	private MyE2_String 			cText4CancelButton = null;
	private MyE2IF__Component  		oComponent4UserInfo = null;
	private String    				cButtonKenner = null;
	private MyE2_String 			cFeldBeschriftung = null;
	private String    				cFieldName = null;

	private ownDB_MASK   			oActualMaskObject = null;
	private IF_DBRB_Component  		oComponent4Edit = null;
	
	private String   				cText4EmptyButton = null;

	public abstract MyRECORD_IF_RECORDS  	createRecord(String cID_LIST_UF)  throws myException;
    public abstract IF_DBRB_Component  		createComponent(DBRB_MASK oDBRB_Mask) throws myException;
    public abstract MyE2_Grid    			createGrid4PopupWindow(	MyE2_String 				cFeldBeschriftung, 
    																Component 					oComponent4Edit, 
    																Component 					oComponent4UserInfo, 
    																MyE2_Button 				btSAVE, 
    																MyE2_Button 				btCANCEL,
    																E2_BasicModuleContainer  	oContainer4Popup) throws myException;
    
    public abstract MyE2_MessageVector      makeAddonValidation(DBRB_BUTTON_ToEditSomeFields ownButton) throws myException;

    
    
	private XX_ActionAgent          oActionAddToSaveButton = null;
    private XX_ActionAgent          oActionAddToCancelButton = null;
    
    private boolean 				bSaveWithoutTriggerCheck = false;
    
    private E2_ComponentMAP   		oComponentMAP_to_Refresh = null;
    
	
//	public DBRB_BUTTON_ToEditSomeFields(String 	text4EmptyButton) throws myException {
//		super(text4EmptyButton, MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
//		this.cText4EmptyButton = text4EmptyButton;
//		
//		this.setIcon(E2_ResourceIcon.get_RI("edit_list.png"));
//		
//	}


	public DBRB_BUTTON_ToEditSomeFields(String 	text4EmptyButton, E2_MutableStyle oStyle) throws myException {
		super(text4EmptyButton, oStyle);
		this.cText4EmptyButton = text4EmptyButton;

		this.setIcon(E2_ResourceIcon.get_RI("edit_list.png"));
	
	}


	public String get_cFieldName() {
		return cFieldName;
	}
	public void set_cFieldName(String fieldName) {
		this.cFieldName = fieldName;
	}


	
	public void INIT_Button(	MyE2IF__Component 		component4UserInfo,
								MyE2_String 			text4SaveButton, 
								MyE2_String 			text4CancelButton,
								String 					buttonKenner,
								MyE2_String 			fieldBeschriftung,
								String 					fieldName) {
		this.oComponent4UserInfo = component4UserInfo;
		this.cText4SaveButton = text4SaveButton;
		this.cText4CancelButton = text4CancelButton;
		this.cButtonKenner = buttonKenner;
		this.cFeldBeschriftung = fieldBeschriftung;
		this.cFieldName = fieldName;
		
		//2014-06-12: buttonkenner ist erst hier bekannt, deshalb kann die autentifizierung auch erst hier erfolgen
		if (S.isFull(this.cButtonKenner)) {
			this.add_GlobalAUTHValidator_AUTO(this.cButtonKenner);
		}
	}
	
	
	

	public MyE2_Grid BaueMaskenGrid(MyRECORD_IF_RECORDS  recActualRecord, E2_BasicModuleContainer  oContainer4Popup) throws myException {
		
		this.oActualMaskObject = new ownDB_MASK(recActualRecord);
		this.oComponent4Edit   = this.createComponent(this.oActualMaskObject);
		this.oActualMaskObject.registerDB_Component(this.cFieldName, this.oComponent4Edit);
		
		this.oActualMaskObject.clearMask();
		this.oActualMaskObject.fillMask();
		
		this.oActualMaskObject.setAllDBComponentsEnabled();
		
		ownButtonSave 	btSAVE = 	new ownButtonSave(this.cText4SaveButton, oContainer4Popup);
		ownButtonCancel btCANCEL = 	new ownButtonCancel(this.cText4CancelButton, oContainer4Popup);
		
		return this.createGrid4PopupWindow(this.cFeldBeschriftung, (Component)oComponent4Edit, (Component)oComponent4UserInfo, btSAVE, btCANCEL, oContainer4Popup);
	}
	
	
	/**
	 * kann benutzt werden, wenn keine speziellen design-wuensche vorliegen
	 * @param cFeldBeschriftung
	 * @param oComponent4Edit
	 * @param oComponent4UserInfo
	 * @param oContainer4Popup
	 * @return 
	 */
	public MyE2_Grid get_STANDARD_GRID_4_POPUP(		MyE2_String	 				cFeldBeschriftung, 
													Component 					oComponent4Edit, 
													Component 					oComponent4UserInfo,
													MyE2_Button 				btSAVE, 
													MyE2_Button 				btCANCEL,
													E2_BasicModuleContainer  	oContainer4Popup) {
		
		MyE2_Grid oGridRueck =new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		if (oComponent4UserInfo != null) {
			//zeile 1
			oGridRueck.add((Component)oComponent4UserInfo,2,new Insets(2,5,2,5));
		}

		
		//zeile 2
		oGridRueck.add(new MyE2_Label(cFeldBeschriftung),1,E2_INSETS.I_2_2_2_2);
		oGridRueck.add((Component)oComponent4Edit,1,E2_INSETS.I_2_2_2_2);
		
		ApplicationInstance.getActive().setFocusedComponent((Component)oComponent4Edit);
		
		//zeile 3
		oGridRueck.add(new Separator(),2,E2_INSETS.I_2_2_2_2);

		//zeile 4
		oGridRueck.add(new E2_ComponentGroupHorizontal(0, btSAVE,E2_INSETS.I_0_0_0_0),1,E2_INSETS.I_2_2_2_2);
		oGridRueck.add(new E2_ComponentGroupHorizontal(0, btCANCEL,E2_INSETS.I_0_0_0_0),1,E2_INSETS.I_2_2_2_2);

		return oGridRueck;
	}
	
	
	
    public String get_cText4EmptyButton() {
		return cText4EmptyButton;
	}
    
	public void set_cText4EmptyButton(String cText4EmptyButton) {
		this.cText4EmptyButton = cText4EmptyButton;
	}

	public boolean get_bSaveWithoutTriggerCheck() {
		return bSaveWithoutTriggerCheck;
	}
	
	public void set_bSaveWithoutTriggerCheck(boolean saveWithoutTriggerCheck) {
		this.bSaveWithoutTriggerCheck = saveWithoutTriggerCheck;
	}

	
	public E2_ComponentMAP get_oComponentMAP_to_Refresh() {
		return oComponentMAP_to_Refresh;
	}
	
	public void set_oComponentMAP_to_Refresh(E2_ComponentMAP componentMAP_to_Refresh) {
		this.oComponentMAP_to_Refresh = componentMAP_to_Refresh;
	}
	

	private void refreshIfPossible() throws myException {
		
		if (this.EXT().get_oComponentMAP()!=null) {
			this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
		}
		if (this.oComponentMAP_to_Refresh!=null) {
			this.oComponentMAP_to_Refresh._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
		}
		
	}
	
	
	
    public XX_ActionAgent get_oActionAddToSaveButton()
	{
		return oActionAddToSaveButton;
	}
	public void set_oActionAddToSaveButton(XX_ActionAgent oActionAddToSaveButton)
	{
		this.oActionAddToSaveButton = oActionAddToSaveButton;
	}
	public XX_ActionAgent get_oActionAddToCancelButton()
	{
		return oActionAddToCancelButton;
	}
	public void set_oActionAddToCancelButton(XX_ActionAgent oActionAddToCancelButton)
	{
		this.oActionAddToCancelButton = oActionAddToCancelButton;
	}

	
	
	
	public class ownButtonSave extends MyE2_Button {

		private E2_BasicModuleContainer  oContainer4Popup = null;
		
		public ownButtonSave(MyString cText, E2_BasicModuleContainer container4Popup) {
			super(cText);
			this.oContainer4Popup = container4Popup;
			
			this.setToolTipText(new MyE2_String("Speichere den eingegebenen Wert").CTrans());
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					MyE2_MessageVector oMV= DBRB_BUTTON_ToEditSomeFields.this.oActualMaskObject.validateMask_Before_Save();
					
					if (oMV.get_bIsOK()) {
						DBRB_BUTTON_ToEditSomeFields.this.oActualMaskObject.setMaskValuesToRecord();
						String cSQL_4_Update = DBRB_BUTTON_ToEditSomeFields.this.oActualMaskObject.getSqlStatementSaveMask(oMV);
						if (oMV.get_bIsOK()) {
							if (DBRB_BUTTON_ToEditSomeFields.this.get_bSaveWithoutTriggerCheck()) {
								cSQL_4_Update= MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+cSQL_4_Update;
							}
								
							DEBUG.System_println(cSQL_4_Update);
							
							oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(bibALL.get_Vector(cSQL_4_Update),true));
								
								
							if (oMV.get_bIsOK()) {
								if (ownButtonSave.this.oContainer4Popup!=null) {
									ownButtonSave.this.oContainer4Popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
									DBRB_BUTTON_ToEditSomeFields.this.refreshIfPossible();
	
									bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der eingegebene Wert wurde gespeichert ...")));
	
								}
							} else {
								bibMSG.add_MESSAGE(oMV);
							}

							
						}
					} else {
						bibMSG.add_MESSAGE(oMV);
					}
					
				}
			});
			
			if (DBRB_BUTTON_ToEditSomeFields.this.oActionAddToSaveButton!=null) {
				this.add_oActionAgent(DBRB_BUTTON_ToEditSomeFields.this.oActionAddToSaveButton);
			}
			
		}
		
	}
	

	public class ownButtonCancel extends MyE2_Button {

		private E2_BasicModuleContainer  oContainer4Popup = null;
		
		public ownButtonCancel(MyString cText, E2_BasicModuleContainer container4Popup) {
			super(cText);
			this.oContainer4Popup = container4Popup;

			this.setToolTipText(new MyE2_String("Abbruch. Die Veränderungen werden verworfen.").CTrans());

			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					ownButtonCancel.this.oContainer4Popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					DBRB_BUTTON_ToEditSomeFields.this.refreshIfPossible();

					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Eingabe wurde abgebrochen ...")));
					
				}
			});
			
			if (DBRB_BUTTON_ToEditSomeFields.this.oActionAddToCancelButton!=null) {
				this.add_oActionAgent(DBRB_BUTTON_ToEditSomeFields.this.oActionAddToCancelButton);
			}

		}
		
	}
	

	
	
	
	
	
	
	private class ownDB_MASK extends DBRB_MASK {

		public ownDB_MASK(MyRECORD_IF_RECORDS  recActualRecord) throws myException {
			super(recActualRecord);
		}
     
		@Override
		public MyE2_MessageVector resetAllMaskSettings()	throws myException {
			DBRB_BUTTON_ToEditSomeFields.this.oComponent4Edit.set_bEnabled_For_Edit(true);
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector clearMask() throws myException {
			DBRB_BUTTON_ToEditSomeFields.this.oComponent4Edit.prepare_ContentForNew(true);
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector doMaskSettings_Before_Load() throws myException {
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector fillMask() throws myException {
			DBRB_BUTTON_ToEditSomeFields.this.oComponent4Edit.set_cActual_Formated_DBContent_To_Mask(this.getActualRecord4Mask());;
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector doMaskSettings_After_Load() throws myException {
			return new MyE2_MessageVector();
		}


		@Override
		public MyE2_MessageVector validateMask_Before_Save() throws myException {
			MyE2_MessageVector  oMVValidate = this.getActualRecord4Mask().check_NewValueForDatabase(
													DBRB_BUTTON_ToEditSomeFields.this.cFieldName,
													DBRB_BUTTON_ToEditSomeFields.this.oComponent4Edit.get_cActualDBValueFormated());
			
			MyE2_MessageVector  oMV_ValidADDON = DBRB_BUTTON_ToEditSomeFields.this.makeAddonValidation(DBRB_BUTTON_ToEditSomeFields.this);
			
			if (oMV_ValidADDON!=null) {
				oMVValidate.add_MESSAGE(oMV_ValidADDON);
			}
			return oMVValidate;
		}

		
		
		@Override
		public String getSqlStatementSaveMask(MyE2_MessageVector oMV)	throws myException {
			
			MyRECORD_IF_RECORDS recHelp = this.get_IF_RECORDS_ActualRecord4Mask();
			
			return ((MyRECORD)recHelp).get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS(true).
					get_CompleteUPDATEString(
							recHelp.get_TABLENAME(), 
							bibE2.cTO(),
							recHelp.get_PRIMARY_KEY_NAME()+"="+((MyRECORD)recHelp).get_UnFormatedValue(recHelp.get_PRIMARY_KEY_NAME()),
							null);
		}

		@Override
		public MyE2_MessageVector setMaskValuesToRecord() throws myException {
			return this.getActualRecord4Mask().set_NewValueForDatabase(
					DBRB_BUTTON_ToEditSomeFields.this.cFieldName,
					DBRB_BUTTON_ToEditSomeFields.this.oComponent4Edit.get_cActualDBValueFormated());
		}
		
	}








	public ownDB_MASK get_oActualMaskObject() {
		return this.oActualMaskObject;
	}
	
	
	public String get_ActualDBValue(String cHASHKey) throws myException{
		String cRueck = "";
		
		IF_DBRB_Component oComp = this.oActualMaskObject.get_hmDB_Components().get(cHASHKey);
		
		if (oComp == null) {throw new myException(this,"get_ActualDBValue:"+cHASHKey+": key not found !!");	}
		
		if (oComp instanceof MyE2IF__DB_Component) {
			
			cRueck = ((MyE2IF__DB_Component)oComp).get_cActualDBValueFormated();
			
		} else {
			throw new myException(this,"get_ActualDBValue:"+cHASHKey+": is no DB-Field !!");
		}
		
		return cRueck;
		
	}
	
}
