 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter.RR_CONST.RR_BUTTONS;


public class RR_LIST_bt_ListToMask extends RB_bt_List2Mask {
	
    private E2_NavigationList  naviList = null;

    public RR_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList) {
        super(bEdit);

        this.naviList = p_naviList;
        
        this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit?RR_BUTTONS.EDIT.db_val():RR_BUTTONS.VIEW.db_val()));
        this.add_GlobalValidator(new OwnValidator_is_something_selected());
        
        if(this.is_UsedToEdit()){
        	this.setToolTipText(new MyE2_String("Bearbeiten ein Reportgruppe").CTrans());
        }else{
        	this.setToolTipText(new MyE2_String("Anzeige ein Reportgruppe").CTrans());
        }
        
    }
    
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new RR_MASK_MaskModulContainer();
    }
    
    @Override
    public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new RR_MASK_DataObjectCollector(id,aktuellerStatus));
        }
        return collector;
    }
    
    @Override
    public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {

    	RECORD_REPORT_REITER rrr = (RECORD_REPORT_REITER) rb_ModulContainerMask.rb_FirstAndOnlyComponentMapCollector().get(new RB_KM(_TAB.report_reiter)).getRbDataObjectActual().get_RecORD();
    	String titleReiterName = rrr.get_REITERNAME_cF();
    	if(this.is_UsedToEdit()){
    		return new  MyE2_String("Bearbeiten von ", true, titleReiterName, false);
    	}else{
    		return new MyE2_String("Anzeigen von ", true, titleReiterName, false);
    	}
    }
    
    @Override
    public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
    	RECORD_REPORT_REITER rrr = (RECORD_REPORT_REITER) rb_ModulContainerMask.rb_FirstAndOnlyComponentMapCollector().get(new RB_KM(_TAB.report_reiter)).getRbDataObjectActual().get_RecORD();
    	String titleReiterName = rrr.get_REITERNAME_cF();
    	return new  MyE2_String(titleReiterName, false, " wurde gespeichert", true);
    }
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
        Vector<XX_ActionAgentWhenCloseWindow> closeActionVector = new Vector<XX_ActionAgentWhenCloseWindow>();
        closeActionVector.add(new ownActionRefreshList(this.rb_modulContainerMASK()));     
    	return closeActionVector;
    }
    
    private class OwnValidator_is_something_selected extends XX_ActionValidator {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) 	throws myException {
			Vector<String>  v_selected_ids = RR_LIST_bt_ListToMask.this.naviList.get_vSelectedIDs_Unformated();
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (v_selected_ids.isEmpty()) {
				if(RR_LIST_bt_ListToMask.this.is_UsedToEdit()){
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst etwas zum bearbeiten auswählen !")));
				}else{
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst etwas zum anzeigen auswählen !")));
				}
			}
			return mv;
		}
		
		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException {
			return null;
		}
    	
    }
    
    private class ownActionRefreshList extends XX_ActionAgentWhenCloseWindow{
    	
		public ownActionRefreshList(RB_ModuleContainerMASK p_maskPopup) {
			super(p_maskPopup);
			
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RR_LIST_bt_ListToMask.this.naviList.RefreshList();
			
		}
    	
    }
}
 
