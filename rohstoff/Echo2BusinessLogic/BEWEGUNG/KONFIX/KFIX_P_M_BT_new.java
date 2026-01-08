package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.components.E2_ButtonMarker;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class KFIX_P_M_BT_new extends RB_bt_New {

	private String vorgangsArt = "";

	private Rec20 record_kopf = null;

	private Object parent = null;

	public KFIX_P_M_BT_new(String oModul, KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent, Rec20 recVkopf) {
		super();

		this.setToolTipText(new MyE2_String("Neue Position erstellen").CTrans());
		this.setStyle(MyE2_Button.StyleImageButtonNoBorders());

		this.record_kopf = recVkopf;

		this.parent = oParent;

		if(oModul.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_EK_KONTRAKT_LIST_NG.get_callKey())){
			
			this.vorgangsArt = myCONST.VORGANGSART_EK_KONTRAKT;
			
			this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_NEW.getValidator());
		
		}else if(oModul.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_VK_KONTRAKT_LIST_NG.get_callKey())){
			this.vorgangsArt = myCONST.VORGANGSART_VK_KONTRAKT;
			
			this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_NEW.getValidator());
		}
//		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(BSK_M_VK_BUTTONS.NEW.db_val()));
	}

	public KFIX_P_M_BT_new(String oModul, KFIX_K_M_masklist_position oParent, Rec20 recVkopf) {
		super();

		this.setToolTipText(new MyE2_String("Neue Position erstellen").CTrans());
		this.setStyle(MyE2_Button.StyleImageButtonNoBorders());

		this.record_kopf = recVkopf;

		this.parent = oParent;

		if(oModul.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_EK_KONTRAKT_LIST_NG.get_callKey())){
			
			this.vorgangsArt = myCONST.VORGANGSART_EK_KONTRAKT;
			
			this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_NEW.getValidator());

		}else if(oModul.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_VK_KONTRAKT_LIST_NG.get_callKey())){
			
			this.vorgangsArt = myCONST.VORGANGSART_VK_KONTRAKT;
			
			this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_NEW.getValidator());
		
		}
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		KFIX_P_M__MaskModulContainer container = new KFIX_P_M__MaskModulContainer(this.vorgangsArt, record_kopf);
		return container;
	}

	@Override
	public RB_DataobjectsCollector generate_DataObjects4New() throws myException {
		return new KFIX_P_M__DataObjectCollector(this.vorgangsArt, record_kopf);
	}

	@Override
	public void define_Actions_4_saveButton(RB_bt_New btNewInList,RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask,RB_ModuleContainerMASK maskPopup) throws myException {
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
		bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Eintrag wurde gespeichert.")));
			}
		});
		bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionRefreshNavilist(maskPopup));
		bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionRefreshMarkLastUsed());

	}

	@Override
	public void define_Actions_4_CloseButton(RB_bt_New btNewInList,RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup) throws myException {
		bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
		bt_Close.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Maske wurde abgebrochen ...")));
			}
		});
		bt_Close.add_oActionAgent(new ownActionRefreshNavilist(maskPopup));
		maskPopup.add_CloseActions(new ownCloseAction(maskPopup));
	}

	private class ownActionRefreshNavilist extends XX_ActionAgent {
		public ownActionRefreshNavilist(RB_ModuleContainerMASK p_maskPopup) {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(KFIX_P_M_BT_new.this.parent instanceof KFIX_K_L_EXPANDER_4_ComponentMAP_NG){
				KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent = (KFIX_K_L_EXPANDER_4_ComponentMAP_NG) KFIX_P_M_BT_new.this.parent;
				oParent.get_oNavigationList().Refresh_ComponentMAP(KFIX_P_M_BT_new.this.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon), E2_ComponentMAP.STATUS_VIEW);
			}else if (KFIX_P_M_BT_new.this.parent instanceof KFIX_K_M_masklist_position){
				KFIX_K_M_masklist_position oParent = (KFIX_K_M_masklist_position) KFIX_P_M_BT_new.this.parent;
				oParent.rb_set_db_value_manual(KFIX_P_M_BT_new.this.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon));
			}
		}
	}

	private class ownCloseAction extends XX_ActionAgentWhenCloseWindow{

		public ownCloseAction(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(KFIX_P_M_BT_new.this.parent instanceof KFIX_K_L_EXPANDER_4_ComponentMAP_NG){
				KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent = (KFIX_K_L_EXPANDER_4_ComponentMAP_NG) KFIX_P_M_BT_new.this.parent;
				oParent.get_oNavigationList().Refresh_ComponentMAP(KFIX_P_M_BT_new.this.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon), E2_ComponentMAP.STATUS_VIEW);
			}else if (KFIX_P_M_BT_new.this.parent instanceof KFIX_K_M_masklist_position){
				KFIX_K_M_masklist_position oParent = (KFIX_K_M_masklist_position) KFIX_P_M_BT_new.this.parent;
				oParent.rb_set_db_value_manual(KFIX_P_M_BT_new.this.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon));
			}
		}
	}
	

	private class ownActionRefreshMarkLastUsed extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(KFIX_P_M_BT_new.this.parent instanceof KFIX_K_L_EXPANDER_4_ComponentMAP_NG){

				KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent = (KFIX_K_L_EXPANDER_4_ComponentMAP_NG) KFIX_P_M_BT_new.this.parent;

				VEK<E2_ButtonMarker<Rec20>>  v_markers = oParent.get_v_markers();

				if (v_markers!=null) {
					//die groesste id suchen, die wird markiert
					long id_max = 0;
					for (E2_ButtonMarker<Rec20> m: v_markers) {
						long id = m.get_reference().get_myLong_dbVal(VPOS_KON.id_vpos_kon).get_lValue();
						if (id>id_max) {
							id_max=id;
						}
					}

					if (id_max>0) {
						for (E2_ButtonMarker<Rec20> m: v_markers) {
							long id = m.get_reference().get_myLong_dbVal(VPOS_KON.id_vpos_kon).get_lValue();
							if (id==id_max) {
								m._mark();
							}
						}
					}

				}
			}
		}
	}
}
