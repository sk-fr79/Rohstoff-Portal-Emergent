 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4Mask_CheckWarnings4PopupAtSave;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask_V3;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_LIST_bt_ListToMask extends RB_bt_List2Mask_V3 {
	
    private E2_NavigationList  		naviList = null;
	private String 					modulKenner;
	private RB_ModuleContainerMASK	ownMaskController = null;
    
    public SD_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList, String modulKenner) {
    	
        super(bEdit,p_naviList);
        this.naviList = p_naviList;
		this.modulKenner = modulKenner;
        
        this.add_GlobalValidator(new XX_ActionValidator_NG() {
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (naviList.get_vSelectedIDs_Unformated().size()==0) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie müssen mindestens eine Datenzeile auswählen !")));
				}
				return mv;
			}
		});
        
        
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return ownMaskController=new SD_MASK_MaskModulContainer(modulKenner);
    }
    @Override
    public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new SD_MASK_DataObjectCollector(id,aktuellerStatus));
        }
        return collector;
    }
    @Override
    public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String(this.is_UsedToEdit()?"Bearbeiter einer Suchdefinition":"Anzeige einer Suchdefinition");
    }
    @Override
    public MyE2_String generate_Meldung4SaveRecord(    RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String("Suchdefinition wurde gespeichert");
    }
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
    	
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(rb_ModulContainerMask) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				SD_LIST_bt_ListToMask.this.get_NaviList()._REBUILD_ACTUAL_SITE("");
			}
		});
    	
        return v_rueck;
    }

    @Override
	public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
		return new OwnBreakControllerSave();
	}

	@Override
	public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
		return new OwnBreakControllerCancel();
	}
	
	
	private class OwnBreakControllerSave extends E2_Break4PopupController {
		public OwnBreakControllerSave() throws myException  {
			super();
			RB_ComponentMapCollector mapCol = ownMaskController.rb_FirstAndOnlyComponentMapCollector();
			this._registerBreak(new Break4Mask_CheckWarnings4PopupAtSave(mapCol));
		}
	}

	private class OwnBreakControllerCancel extends E2_Break4PopupController {
		public OwnBreakControllerCancel() throws myException  {
			super();
			RB_ComponentMapCollector mapCol = ownMaskController.rb_FirstAndOnlyComponentMapCollector();
			this._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(mapCol, null));
		}
	}

	
}
 
