package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Button_TO_EditField_DirektEditAndSave;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.exceptions.myException;

public class BSRG_K_LISTE_BT_Edit_RG_START_TEXT extends 	MyE2_Button_TO_EditField_DirektEditAndSave 
{
	
	private UB_TextArea  				otf_TEXT_Eingang = new UB_TextArea("FORMULARTEXT_ANFANG", true, "");
	private E2_NavigationList 			oNaviList   = null;
	private E2_BasicModuleContainer   	oBasicModuleContainer = null;

	private RECORD_VKOPF_RG             recVKOPF_RG = null;
	
	public BSRG_K_LISTE_BT_Edit_RG_START_TEXT(E2_NavigationList NaviList)	throws myException 
	{
		super(new MyE2_String("Bearbeite den Formular-Eingangstext"));
		
		this.add_GlobalAUTHValidator_AUTO("AENDERN_FORMULAR_EINGANGSTEXT");
		
		this.oNaviList = NaviList;
		
		//einstellungen wie im formular
		this.otf_TEXT_Eingang.set_iWidthPixel(600);
		this.otf_TEXT_Eingang.set_iRows(10);
		
		this.get_vUnboundDataFields().add(this.otf_TEXT_Eingang);
		this.set_cTitleForPopup(new MyE2_String("Bearbeiten des Formularanfangs ..."));
		
		this.get_vInfoTexts().add(new MyE2_String("Bitte geben Sie den neuen"));
		this.get_vInfoTexts().add(new MyE2_String("Formular-Anfangstext für diesen Beleg ein... "));
		
		this.set_PopupWidthAndHeight(640, 350);
	}

	@Override
	public MyE2_Grid get_GridWithComponents() throws myException 
	{
		MyE2_Grid oGridHelp = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		oGridHelp.add(this.otf_TEXT_Eingang,E2_INSETS.I_0_0_0_0);
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
			BSRG_K_LISTE_BT_Edit_RG_START_TEXT oThis = BSRG_K_LISTE_BT_Edit_RG_START_TEXT.this;
			
			if (oThis.otf_TEXT_Eingang.get_bFieldHasChanged())
			{
				MyE2_MessageVector oMV=oThis.recVKOPF_RG.set_NEW_VALUE_FORMULARTEXT_ANFANG(oThis.otf_TEXT_Eingang.get_cText());
				
				if (oMV.get_bIsOK())
				{
					oMV.add_MESSAGE(oThis.recVKOPF_RG.UPDATE(null, true));
					if (oMV.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Änderung wurde gespeichert ...")));
						oThis.oBasicModuleContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
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
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde nichts verändert !!")));
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
				BSRG_K_LISTE_BT_Edit_RG_START_TEXT.this.oBasicModuleContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
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
		Vector<String> vIDs = this.oNaviList.get_vSelectedIDs_Unformated();
		if (vIDs.size()!=1)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte genau EINEN Beleg auswählen !")));
			return false;
		}
		else
		{
			this.recVKOPF_RG = new RECORD_VKOPF_RG(vIDs.get(0));
			this.otf_TEXT_Eingang.set_StartValue(this.recVKOPF_RG.get_FORMULARTEXT_ANFANG_cUF_NN(""));
			return true;
		}
	}


	
}
