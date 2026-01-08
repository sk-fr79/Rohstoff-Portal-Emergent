 
package rohstoff.businesslogic.bewegung2.list;
  
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.businesslogic.bewegung2.global.B2_DefaultFields;
import rohstoff.businesslogic.bewegung2.mask.B2_MaskDataObjectCollector;
import rohstoff.businesslogic.bewegung2.mask.B2_MaskModulContainer;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
  
public abstract class B2_ListBtListToMaskAbstract extends RB_BtV4_List2Mask  {
	
	public abstract VEK<String> getIdsSelected();
	
    public B2_ListBtListToMaskAbstract(boolean bEdit, RB_TransportHashMap  p_tpHashMap) {
        super(bEdit);
        this._setTransportHashMap(p_tpHashMap);
         
        this.add_GlobalValidator(bEdit?ENUM_VALIDATION.BG_TRANSPORT_EDIT.getValidator():ENUM_VALIDATION.BG_TRANSPORT_VIEW.getValidator());
        
        this._addValidator(()->{
        	MyE2_MessageVector mv = bibMSG.newMV();
        	if (getIdsSelected().size()==0) {
        		mv._addAlarm(S.ms("Sie muessen mindestens eine Warenbewegung auswählen !"));
        	}
        	return mv;
        });
        
    }
     
    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
        return new B2_MaskModulContainer(this.getTransportHashMap());
    }
    
    
    protected MyE2_String getTextForViewOnlyOnEdit() {
    	return S.ms("Mindestens ein auswählter Bewegungssatz ist storniert: Bearbeiten ist nicht möglich !");
    }
    
    
    @Override
    public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.getIdsSelected());
        
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()&&this.isEditAllowd()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new B2_MaskDataObjectCollector(this.getTransportHashMap(),id,aktuellerStatus));
        }
        return collector;
    }
    
    
    
    
    
    @Override
    public MyE2_String generateTitelInfo4MaskWindow() throws myException {
        return new MyE2_String(this.isUsedToEdit()?"Bearbeiten einer Warenbewegung":"Anzeige einer Warenbewegung");
    }
    
    @Override
    public MyE2_String generateMessagetextForSaveRecord() throws myException {
        return new MyE2_String("Warenbewegung wurde gespeichert");
    }
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
     	B2_ListBtListToMaskAbstract oThis = B2_ListBtListToMaskAbstract.this;
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
        		this.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), 
        		null
        		));
    }

	@Override
	protected boolean isEditAllowd() {
        
        //hier pruefen, stornierte gibt, wenn ja, dann meldung und readonly
        boolean editAllowed = true;
        try {
			for (String id: this.getIdsSelected()) {
				Rec21_bgVector rec = (Rec21_bgVector)new Rec21_bgVector()._fill_id(id);
				if (rec.isStorniert()) {
					editAllowed = false;
					if (this.isUsedToEdit()) {
						bibMSG.MV()._addWarn(getTextForViewOnlyOnEdit());
					}
					break;
				}
			}
		} catch (myException e) {
			e.printStackTrace();
			editAllowed=false;
		}
        
        return editAllowed;
	}
   
     
}
 
 
