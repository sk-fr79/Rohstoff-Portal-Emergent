package rohstoff.Echo2BusinessLogic.AH7.RELATION;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask_V3;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_Break4Popup_checkSperrFlag;

public class AH7_LIST_bt_ListToMask extends RB_bt_List2Mask_V3 {

	private E2_NavigationList  				naviList = null;
	private AH7_MASK_MaskModulContainer 	ah7_mask_modulContainer = null;
    
    public AH7_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList) throws myException {
        super(bEdit,p_naviList);
        this.naviList = p_naviList;
        
        this.add_GlobalValidator(bEdit?ENUM_VALIDATION.AH7_STEUERDATEI_EDIT.getValidator():ENUM_VALIDATION.AH7_STEUERDATEI_VIEW.getValidatorWithoutSupervisorPersilschein());
        
        this._addValidator( ()-> {
        	if (this.naviList.get_vSelectedIDs_Unformated().size()==0) {
        		return new MyE2_MessageVector()._addAlarm("Bitte mindestens einen Datensatz auswählen !");  
        	}
        	return new MyE2_MessageVector();
        });
        
        
    }

    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
    	this.ah7_mask_modulContainer = new AH7_MASK_MaskModulContainer();
        return this.ah7_mask_modulContainer;
    }
    
    @Override
    public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new AH7_MASK_DataObjectCollector(id,aktuellerStatus));
        }
        return collector;
    }
    
    @Override
    public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String(this.is_UsedToEdit()?"Bearbeiten einer Anhang 7-Relation zur Drucksteuerung":"Anzeige einer Anhang 7-Relation zur Drucksteuerung");
    }
    
    @Override
    public MyE2_String generate_Meldung4SaveRecord(    RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String("Anhang 7-Relation zur Drucksteuerung wurde gespeichert");
    }
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
    	
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(rb_ModulContainerMask) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				AH7_LIST_bt_ListToMask.this.get_NaviList()._REBUILD_ACTUAL_SITE("");
			}
		});
    	
        return v_rueck;
   }

	
	@Override
	public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
		//return new Break4PopupSave();
		return new AH7_Break4Popup_checkSperrFlag(ah7_mask_modulContainer);
		
	}

	@Override
	public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
		//return new Break4PopupCancel();
		//return new AH7_Break4Popup_checkSomethingChangedWhenClose(ah7_mask_modulContainer);
		return new E2_Break4PopupController()._registerBreak(
						new Break4MaskCloseWhenSomethingWasChanged(	this.ah7_mask_modulContainer.rb_ComponentMapCollector(new AH7_KEY())
																	,null));
	}

	
}
 
