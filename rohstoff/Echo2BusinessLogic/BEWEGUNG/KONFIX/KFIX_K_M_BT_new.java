package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class KFIX_K_M_BT_new extends RB_bt_New {
   
	private boolean istFixiert = false;
	private VORGANGSART vorgangsArt = null;
	    
	public KFIX_K_M_BT_new(E2_NavigationList p_naviList, boolean bIstFixiertKontrakt, String modulidentifier) {
        super();
        
        this.istFixiert = bIstFixiertKontrakt;
        
        this.set_NaviList(p_naviList);
        
        if(modulidentifier.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_EK_KONTRAKT_LIST_NG.get_callKey())){
        	this.vorgangsArt = VORGANGSART.EK_KONTRAKT;
        	this.add_GlobalValidator(ENUM_VALIDATION.VKOPF_KON_EK_NEW.getValidator());
        }else if(modulidentifier.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_VK_KONTRAKT_LIST_NG.get_callKey())){
        	this.vorgangsArt = VORGANGSART.VK_KONTRAKT;
        	this.add_GlobalValidator(ENUM_VALIDATION.VKOPF_KON_VK_NEW.getValidator());
        }
        
        if(istFixiert){
        	this.setIcon(E2_ResourceIcon.get_RI("new_fixiert.png"));
        	 this.setToolTipText(new MyE2_String("Neuen Fixierungskontrakt eingeben").CTrans());
        }else{
        	this.setToolTipText(new MyE2_String("Neuen Kontrakt eingeben").CTrans());
        }
        this.setStyle(MyE2_Button.StyleImageButtonNoBorders());
    }
	
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        KFIX_K_M_MaskModulContainer container = new KFIX_K_M_MaskModulContainer(this.istFixiert, this.vorgangsArt, this.get_NaviList());
        return container;
    }
    
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New() throws myException {
        return new KFIX_K_M__DataObjectCollector(this.vorgangsArt);
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
        
        bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionRefreshNavilistAfterSave(maskPopup));
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
        maskPopup.add_CloseActions(new ownCloseAction(maskPopup));
    }
 
	private class ownCloseAction extends XX_ActionAgentWhenCloseWindow{

		public ownCloseAction(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_NavigationList f_naviList = KFIX_K_M_BT_new.this.get_NaviList();
			
			f_naviList._REBUILD_COMPLETE_LIST("");
		}
	}
    
    private class ownActionRefreshNavilistAfterSave extends XX_ActionAgent {
		private RB_ModuleContainerMASK maskPopup = null;

		public ownActionRefreshNavilistAfterSave(RB_ModuleContainerMASK p_maskPopup) {
			super();
			this.maskPopup = p_maskPopup;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_NavigationList f_naviList = KFIX_K_M_BT_new.this.get_NaviList();
			String id_new = this.maskPopup.rb_ComponentMapCollector(new RB_KM(_TAB.vkopf_kon)).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.vkopf_kon.n());
			f_naviList._REBUILD_ACTUAL_SITE(true, true,bibALL.get_Vector(id_new));
		}
	}


}
 
