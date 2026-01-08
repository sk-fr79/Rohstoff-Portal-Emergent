package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.DB_RB.BASICS.DBRB_MASK;
import panter.gmbh.Echo2.DB_RB.BASICS.IF_DBRB_Component;
import panter.gmbh.Echo2.DB_RB.BASICS.COMPONENTS.MyE2_DBRB_TextField;
import panter.gmbh.Echo2.DB_RB.TOOLS.DBRB_BUTTON_ToEditSomeFields;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FU_LIST_Expander_TeilPositionen_BT_EDIT_WIEGEKARTE extends DBRB_BUTTON_ToEditSomeFields{

	
	private String			 	cID_VPOS_TPA_FUHRE_ORT = null;
	
	public FU_LIST_Expander_TeilPositionen_BT_EDIT_WIEGEKARTE(RECORD_VPOS_TPA_FUHRE_ORT recOrt, E2_ComponentMAP oMAP) throws myException {
		super(recOrt.get_WIEGEKARTENKENNER_cUF_NN("<wiegekarte>"), MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		this.set_cFieldName(_DB.VPOS_TPA_FUHRE_ORT$WIEGEKARTENKENNER);
		this.set_oComponentMAP_to_Refresh(oMAP);
		
		
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		
		this.setFont(new E2_FontPlain(-2));
		this.setForeground(Color.BLACK);

		if (this.getText().equals("<wiegekarte>")) {
			this.setFont(new E2_FontItalic(-2));
			this.setForeground(Color.DARKGRAY);
		}
		 
		
		MyE2_Label oLabelInfo = null;
		
		this.cID_VPOS_TPA_FUHRE_ORT = recOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
				 				
		this.INIT_Button(		oLabelInfo, 
								new MyE2_String("Speichern"), 
								new MyE2_String("Abbruch"), 
								"WIEGEKENNUNG_AUS_LISTE_ERFASSEN", 
								new MyE2_String("Wiegekarte: "), 
								this.get_cFieldName());
		
		this.setToolTipText(new MyE2_String("Wiegekarte der des Fuhrenzusatzortes erfassen").CTrans());
		
		this.set_bSaveWithoutTriggerCheck(true);
		
		this.add_oActionAgent(new ownActionAgent());
	}

	


	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ownBasicModuleContainer  oBasicContainer = new ownBasicModuleContainer();
			
			MyE2_Grid oGrid = FU_LIST_Expander_TeilPositionen_BT_EDIT_WIEGEKARTE.this.BaueMaskenGrid(
					new RECORD_VPOS_TPA_FUHRE_ORT(FU_LIST_Expander_TeilPositionen_BT_EDIT_WIEGEKARTE.this.cID_VPOS_TPA_FUHRE_ORT), oBasicContainer);
			
			oBasicContainer.add(oGrid, E2_INSETS.I_2_2_2_2);
			
			oBasicContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(530), new Extent(160), new MyE2_String("Wiegekarte erfassen ..."));
		}
	}
	
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer {
	}
	
	@Override
	public MyRECORD_IF_RECORDS createRecord(String cID_LIST_FUO_UF) throws myException {
		return new RECORD_VPOS_TPA_FUHRE_ORT(cID_LIST_FUO_UF);
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		throw new myExceptionCopy("FU_LIST_Expander_TeilPositionen_BT_EDIT_WIEGEKARTE: no implemented get_Copy()");
	}


	@Override
	public IF_DBRB_Component createComponent(DBRB_MASK oDBRB_Mask) 	throws myException {
		return new MyE2_DBRB_TextField(oDBRB_Mask, this.get_cFieldName().toUpperCase(), true, 300, 15, false);
	}




	@Override
	public MyE2_Grid createGrid4PopupWindow(MyE2_String cFeldBeschriftung, Component oComponent4Edit, Component oComponent4UserInfo, MyE2_Button btSAVE, MyE2_Button btCANCEL, E2_BasicModuleContainer oContainer4Popup) throws myException {
		return this.get_STANDARD_GRID_4_POPUP(cFeldBeschriftung, oComponent4Edit, oComponent4UserInfo,btSAVE,btCANCEL, oContainer4Popup);	
	}


	@Override
	public MyE2_MessageVector makeAddonValidation(DBRB_BUTTON_ToEditSomeFields ownButton) throws myException {
		return null;
	}
	
}
