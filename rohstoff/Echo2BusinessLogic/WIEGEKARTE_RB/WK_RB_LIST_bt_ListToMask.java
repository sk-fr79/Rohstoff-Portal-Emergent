 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_LIST_bt_ListToMask extends RB_BtV4_List2Mask  {
	
    public WK_RB_LIST_bt_ListToMask(boolean bEdit, RB_TransportHashMap  p_tpHashMap) {
        super(bEdit);
        if (bEdit ) {
        	this.setText(new MyString("Wiegekarte bearbeiten").CTrans()) ; 
        } else {
        	this.setText(new MyString("Wiegekarte anzeigen").CTrans()) ; 
        }
        this._setShapeStandardTextButton();
        
        
        this._setTransportHashMap(p_tpHashMap);
         
        this.add_GlobalValidator(bEdit?WK_RB_VALIDATORS.EDIT.getValidator():WK_RB_VALIDATORS.EDIT.getValidator());
        
        this.add_GlobalValidator(new XX_ActionValidator_NG() {
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				WK_RB_LIST_bt_ListToMask oThis = WK_RB_LIST_bt_ListToMask.this;
				
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (oThis.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated().size()==0) {
					mv._addAlarm(new MyE2_String("Sie m¸ssen mindestens eine Datenzeile ausw‰hlen !"));
				}
				return mv;
			}
		});
        
        
        
        this._addObjectAccessor( (o) -> {
        	o.getBtCancel()._setShapeStandardTextButton().setText(new MyString("Abbrechen").CTrans());
        	o.getBtSaveAndNext()._setShapeStandardTextButton().setText(new MyString("Speichern & Schlieﬂen").CTrans());
        	o.getBtSaveAndReopen()._setShapeStandardTextButton().setText(new MyString("Speichern").CTrans());
        });
        
        
        
    }



	@Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
		this.get_grid4MaskExternal()._clear();
    	this.getTransportHashMap()._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.GRID_4_MASK_EXTERNAL,   this.get_grid4MaskExternal());

        return new WK_RB_MASK_MaskModulContainer(this.getTransportHashMap());
    }
    
    
    @Override
    public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated());
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new WK_RB_MASK_DataObjectCollector(this.getTransportHashMap(),id,aktuellerStatus));
        }
        return collector;
    }
    
    
    @Override
    public MyE2_String generateTitelInfo4MaskWindow() throws myException {
    	MyE2_String bearbeiten = S.ms("Bearbeiten eines Datensatzes vom Typ: ").ut(WK_RB_CONST.WK_RB_NAMES.DATASET_NAME.getUserText().CTrans());
    	MyE2_String anzeigen  = S.ms("Anzeige eines Datensatzes vom Typ: ").ut(WK_RB_CONST.WK_RB_NAMES.DATASET_NAME.getUserText().CTrans());
    	
        return  this.isUsedToEdit()?bearbeiten:anzeigen;
    }
    
    @Override
    public MyE2_String generateMessagetextForSaveRecord() throws myException {
        return S.ms("Datensatzes vom Typ: ").ut(WK_RB_CONST.WK_RB_NAMES.DATASET_NAME.getUserText().CTrans()).t(" wurde gespeichert");
    }
    
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
     	WK_RB_LIST_bt_ListToMask oThis = WK_RB_LIST_bt_ListToMask.this;
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(oThis.getTransportHashMap().getRBModulContainerMask()) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				oThis.getTransportHashMap().getNavigationList()._RebuildSiteAndKeepMarkers("");
			}
		});
    	
        return v_rueck;
    }
    
    @Override
    public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
        return null;
    }
    
    @Override
    public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
        return new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(
        		this.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
    }
  
}
 
 
