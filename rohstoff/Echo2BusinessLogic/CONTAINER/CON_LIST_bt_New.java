 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New_V2;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_CONTAINER;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.exceptions.myException;


public class CON_LIST_bt_New extends RB_bt_New_V2 {
    private E2_NavigationList  naviList = null;
    
    
    
    public CON_LIST_bt_New(E2_NavigationList p_naviList) {
        super();
        this.naviList = p_naviList;
        this.set_Text4MaskTitle(S.ms("Container neu anlegen..."));
        this.add_GlobalValidator(ENUM_VALIDATION.CONTAINER_NEW.getValidator());
           
    }
    
    
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
    	RB_ModuleContainerMASK container = new CON_MASK_MaskModulContainer();
    	return container;
    }
    
    
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
    	String id_Selected = null;

    	Vector<String> vIDs = ((CON_LIST_ComponentMap) naviList.get_oComponentMAP__REF()).getListSelector().getSelectorContainerTyp().get_SelectedValues();
    	if (vIDs != null && vIDs.size() == 1){
    		// id muss formatiert sein.
    		id_Selected =bibALL.convertID2FormattedID(vIDs.firstElement());
    	}

    	if (id_Selected == null){
    		if (naviList.get_vSelectedIDs_Unformated().size() == 1){
    			id_Selected = naviList.get_vSelectedIDs_Unformated().firstElement();
    			RECORD_CONTAINER cont = new RECORD_CONTAINER(id_Selected);
    			id_Selected = cont.get_ID_CONTAINERTYP_cF();
    		}
    	}
    	
        return new CON_MASK_DataObjectCollector(id_Selected);
    }
    
    
    @Override
    public void define_Actions_4_saveButton(RB_bt_New_V2 btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new ownActionRefreshNavilist(maskPopup));
    }
 
    
    @Override
    public void define_Actions_4_CloseButton(RB_bt_New_V2 btNewInList,    RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)    throws myException {
        bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
    }
    
    
    
    private class ownActionRefreshNavilist extends XX_ActionAgent {
        private RB_ModuleContainerMASK maskPopup = null;
        
        public ownActionRefreshNavilist(RB_ModuleContainerMASK p_maskPopup) {
            super();
            this.maskPopup = p_maskPopup;
        }
        
        
        @Override
        public void executeAgentCode(ExecINFO oExecInfo) throws myException {
            E2_NavigationList  f_naviList = CON_LIST_bt_New.this.naviList;
            
            String id_new = this.maskPopup.rb_ComponentMapCollector(new CON_KEY()).rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.container.n());
            f_naviList.ADD_NEW_ID_TO_ALL_VECTORS(id_new);
            
            f_naviList._REBUILD_ACTUAL_SITE("");
        }
    }
    
    
}
 
