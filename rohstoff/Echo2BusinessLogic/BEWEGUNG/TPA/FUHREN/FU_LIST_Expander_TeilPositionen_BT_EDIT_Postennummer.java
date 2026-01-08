package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Button_TO_EditField_DirektEditAndSave;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public class FU_LIST_Expander_TeilPositionen_BT_EDIT_Postennummer extends 	MyE2_Button_TO_EditField_DirektEditAndSave 
{
	
	private UB_TextField  				otf_TEXT_Field = null;
	private RECORD_VPOS_TPA_FUHRE_ORT  	recVPOS_TPA_FUHRE_ORT = null;
	private E2_BasicModuleContainer     oBasicModuleContainer = null;
	
	
	public FU_LIST_Expander_TeilPositionen_BT_EDIT_Postennummer(RECORD_VPOS_TPA_FUHRE_ORT    VPOS_TPA_Fuhre_Ort)	throws myException 
	{
		super(VPOS_TPA_Fuhre_Ort.get_POSTENNUMMER_cUF_NN("<Posten-Nr.>"));
		this.setIcon(E2_ResourceIcon.get_RI("edit_list.png"));
		
		this.recVPOS_TPA_FUHRE_ORT = new RECORD_VPOS_TPA_FUHRE_ORT(VPOS_TPA_Fuhre_Ort);
		
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		this.add_GlobalAUTHValidator_AUTO("EDIT_POSTENNUMMER_FUO_IN_LISTE");
		
		this.setFont(new E2_FontPlain(-2));
		this.setLineWrap(true);
		
		this.otf_TEXT_Field = new UB_TextField("POSTENNUMMER", true, VPOS_TPA_Fuhre_Ort.get_POSTENNUMMER_cUF_NN(""),200,100);
		this.otf_TEXT_Field.set_StartValue(VPOS_TPA_Fuhre_Ort.get_POSTENNUMMER_cUF_NN(""));
		
		this.get_vUnboundDataFields().add(this.otf_TEXT_Field);
		this.set_cTitleForPopup(new MyE2_String("Bearbeiten der Postennummer / Externe Eingangs-/Ausgangs-Nummer ..."));
		
		this.get_vInfoTexts().add(new MyE2_String("Bitte geben Sie die neue Postennummer "));
		this.get_vInfoTexts().add(new MyE2_String("/ Externe Eingangs-/Ausgangs-Nummer für"));
		this.get_vInfoTexts().add(new MyE2_String("diesen Fuhrenort ein ... "));
		
		this.set_PopupWidthAndHeight(400, 220);
		
		this.add_GlobalValidator(new ownValidator());
		
		this.setToolTipText(new MyE2_String("Direkte Eingabe der Posten- / Externe Eingangs-/Ausgangsnummer (auch bei geschlossenen Fuhren)").CTrans());
		
	}

	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}
		
	}
	
	
	@Override
	public MyE2_Grid get_GridWithComponents() throws myException 
	{
		MyE2_Grid oGridHelp = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		oGridHelp.add(this.otf_TEXT_Field,E2_INSETS.I_0_0_0_0);
		return oGridHelp;
	}

	@Override
	public E2_BasicModuleContainer get_BasicModuleContainer() throws myException 
	{
		this.oBasicModuleContainer = new own_BasicModuleContainer();
		return this.oBasicModuleContainer ;
	}

	
	@Override
	public MyE2_Button get_ButtonToSave() throws myException 
	{
		MyE2_Button  oButtonOK = new MyE2_Button("Speichern");
		
		oButtonOK.add_oActionAgent(new ownActionAgentSave());
		
		return oButtonOK;
	}

	
	private class ownActionAgentSave extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FU_LIST_Expander_TeilPositionen_BT_EDIT_Postennummer oThis = FU_LIST_Expander_TeilPositionen_BT_EDIT_Postennummer.this;
			
			if (oThis.otf_TEXT_Field.get_bFieldHasChanged())
			{
				String cNewPostenNr = oThis.otf_TEXT_Field.get_cText();
				
				MyE2_MessageVector oMV=oThis.recVPOS_TPA_FUHRE_ORT.set_NEW_VALUE_POSTENNUMMER(cNewPostenNr);
				
				if (oMV.get_bIsOK())
				{
					oMV.add_MESSAGE(oThis.recVPOS_TPA_FUHRE_ORT.UPDATE(null, true));
					
					if (oMV.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die neue Postennummer wurde gespeichert!")));
						oThis.set_Text(S.isEmpty(cNewPostenNr)?"<Posten-Nr.>":cNewPostenNr);
						oThis.otf_TEXT_Field.set_StartValue(S.isEmpty(cNewPostenNr)?"":cNewPostenNr);
						oThis.recVPOS_TPA_FUHRE_ORT.REBUILD();
						
						FU_LIST_Expander_TeilPositionen_BT_EDIT_Postennummer.this.oBasicModuleContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
					else
					{
						bibMSG.add_MESSAGE(oMV);
					}
					
				}
				else
				{
					bibMSG.add_MESSAGE(oMV);
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es wurde nichts verändert !!")));
				FU_LIST_Expander_TeilPositionen_BT_EDIT_Postennummer.this.oBasicModuleContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			
		}
	}
	
	
	@Override
	public MyE2_Button get_ButtonToCancel() throws myException 
	{
		MyE2_Button oButtonCancel = new MyE2_Button(new MyE2_String("Abbruch"));
		oButtonCancel.add_oActionAgent(new XX_ActionAgent() 
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				FU_LIST_Expander_TeilPositionen_BT_EDIT_Postennummer.this.oBasicModuleContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		});
		
		return oButtonCancel;
	}

	
	
	private class own_BasicModuleContainer extends E2_BasicModuleContainer
	{

		public own_BasicModuleContainer() 
		{
			super();
		}
		
	}


	@Override
	public boolean check_StatusBeforePopup(E2_BasicModuleContainer oContainer,	Vector<MyE2_String> vInfoTexts, MyE2_Button oBtSave,MyE2_Button oBtCancel) throws myException 
	{
		return true;
	}
}
