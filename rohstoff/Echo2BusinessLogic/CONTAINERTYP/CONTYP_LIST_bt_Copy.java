 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import java.util.Vector;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_CopySingular_V2;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.AT_CONST.TRI_BUTTONS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.CONTYP_CONST.CONTYP_BUTTONS;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
public class CONTYP_LIST_bt_Copy extends RB_bt_CopySingular_V2 {
	private E2_NavigationList  naviList = null;
    public CONTYP_LIST_bt_Copy(E2_NavigationList p_naviList) {
        super();
        this.naviList = p_naviList;
        this.add_GlobalValidator(ENUM_VALIDATION.CONTAINERTYP_NEW.getValidator());
    
         /*
         *  in der validierer-enum folgende eintrage erfassen:
         *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
            ,CONTAINERTYP("CONTAINERTYP-Beschreibung")
         *  
         *  
         *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
         *  
          	,CONTAINERTYP_EDIT( 	ENUM_VALID_THEME.CONTAINERTYP, 		ENUM_VALID_BASICFUNCTION.EDIT)
			,CONTAINERTYP_VIEW( 	ENUM_VALID_THEME.CONTAINERTYP, 		ENUM_VALID_BASICFUNCTION.VIEW)
			,CONTAINERTYP_NEW( 		ENUM_VALID_THEME.CONTAINERTYP, 	ENUM_VALID_BASICFUNCTION.NEW)
			,CONTAINERTYP_DELETE( 	ENUM_VALID_THEME.CONTAINERTYP, 	ENUM_VALID_BASICFUNCTION.DELETE)
         * 
         */
    
        
        
    }
	
	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new CONTYP_MASK_MaskModulContainer();
	}
	@Override
	public RB_DataobjectsCollector generate_DataObjects4Edit() throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());
        RB_DataobjectsCollector_V2 collector =null;
        if (v_ids.size()==1) {
            collector = new CONTYP_MASK_DataObjectCollector(v_ids.get(0),RB__CONST.MASK_STATUS.NEW_COPY);
        } else {
        	collector=null;
        	bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie müssen einen einzelnen Datensatz zum Kopieren auswählen !")));
        }
        return collector;
	}
	@Override
	public void define_Actions_4_saveButton(RB_bt_CopySingular_V2 btEditSingular, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
		bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionSave(maskPopup));
	}
	@Override
	public void define_Actions_4_CloseButton(RB_bt_CopySingular_V2 btEditSingular, RB_bt_maskClose bt_Close,	RB_ModuleContainerMASK maskPopup) throws myException {
		this.naviList._REBUILD_ACTUAL_SITE("");
	}
	
	
	private class ownActionSave extends XX_ActionAgent {
		private RB_ModuleContainerMASK maskPopup = null;
		
		/**
		 * @param p_maskPopup
		 */
		public ownActionSave(RB_ModuleContainerMASK p_maskPopup) {
			super();
			this.maskPopup = p_maskPopup;
		}
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			CONTYP_LIST_bt_Copy oThis = CONTYP_LIST_bt_Copy.this;
			
			MyE2_MessageVector  oMV = oThis.get_MaskContainer().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true);
			if (oMV.get_bIsOK()) {
				//feststellen, welche id neu geschrieben wurde
				Rec20 r_new = ((RB_Dataobject_V2)oThis.get_MaskContainer().rb_FirstAndOnlyComponentMapCollector()
																			.rb_Actual_DataobjectCollector()
																			.rb_hm_DataObjects()
																			.get(new RB_KM(_TAB.containertyp)))
																			.rec20().get_rec_after_save_new();
				oThis.naviList.ADD_NEW_ID_TO_ALL_VECTORS(r_new.get_key_value());
				oThis.naviList._REBUILD_ACTUAL_SITE(r_new.get_key_value());
				if (oMV.get_bIsOK()) {
					this.maskPopup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			}
			
			bibMSG.add_MESSAGE(oMV);
		}
	}
	
} 
