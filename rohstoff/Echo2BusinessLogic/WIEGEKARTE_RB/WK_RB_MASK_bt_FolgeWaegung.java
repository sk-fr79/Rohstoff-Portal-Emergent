 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

  


public class WK_RB_MASK_bt_FolgeWaegung extends RB_BtV4_List2Mask {
	
	
	Long 					_idFolgeWiegekarteToOpen = null;
	
	RB_ModuleContainerMASK 	_mask2Close = null;
	
	/**
	 * @author manfred
	 * @date 10.05.2019
	 *
	 * @param bEdit
	 */
	public WK_RB_MASK_bt_FolgeWaegung( RB_TransportHashMap  p_tpHashMap  ) {
		super(true);
		
		this._setShapeStandardTextButton();
		this.setText(new MyString("Folgew‰gung...").CTrans());
		this._setTransportHashMap(p_tpHashMap._getCopyDeep());
        this.add_GlobalValidator(WK_RB_VALIDATORS.EDIT.getValidator());
        
        this._addObjectAccessor((o)->{
        	o.getBtCancel()._setShapeStandardTextButton().setText(new MyString("Abbrechen").CTrans());
        	o.getBtSaveAndNext()._setShapeStandardTextButton().setText(new MyString("Speichern & Schlieﬂen").CTrans());
        	o.getBtSaveAndReopen()._setShapeStandardTextButton().setText(new MyString("Speichern").CTrans());
        });
        
        
	}
	


    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
    	_mask2Close = this.getTransportHashMap().getRBModulContainerMask();
    	
    	this.getTransportHashMap()._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.GRID_4_MASK_EXTERNAL,   this.get_grid4MaskExternal());

    	// close old mask
    	this.getTransportHashMap()._setMaskStatusOnLoad(this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW);
    	this.getTransportHashMap()._setRBModulContainerMask(new WK_RB_MASK_MaskModulContainer(this.getTransportHashMap()));
    	_mask2Close.CLOSE_AND_DESTROY_POPUPWINDOW(true);
    	
        return this.getTransportHashMap().getRBModulContainerMask();
    }
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask#generateDataObjects4Mask(panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
		WK_RB_MASK_bt_FolgeWaegung oThis = WK_RB_MASK_bt_FolgeWaegung.this;
    	
		MyLong  id = new MyLong(_idFolgeWiegekarteToOpen);
		
		if (id.isOK()) {
	        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
	        RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
            collector.put(id.get_cUF_LongString(), new WK_RB_MASK_DataObjectCollector(oThis.getTransportHashMap(), id.get_cUF_LongString(),aktuellerStatus));
            return collector;
		} else {
			throw new myException("Error finding id to Edit");
		}

	}
	
	public 	WK_RB_MASK_bt_FolgeWaegung _setIdFolgewiegekarte (Long idFolgewiegkarte) {
		_idFolgeWiegekarteToOpen = idFolgewiegkarte;
		return this;
	}
	
    @Override
    public MyE2_String generateTitelInfo4MaskWindow() throws myException {
        return new MyE2_String(this.isUsedToEdit()?"Bearbeiten einer Folgew‰gung":"Anzeige einer Folgew‰gung");
    }
    
    
    @Override
    public MyE2_String generateMessagetextForSaveRecord() throws myException {
        return new MyE2_String("Folgew‰gung wurde gespeichert");
    }
    
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
     	WK_RB_MASK_bt_FolgeWaegung oThis = WK_RB_MASK_bt_FolgeWaegung.this;
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(oThis.getTransportHashMap().getRBModulContainerMask()) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				oThis.getTransportHashMap().getNavigationList()._RebuildSiteAndKeepMarkers("");
			}
		});
    	
        return v_rueck;
    }


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask#getBreak4PopupController4Save()
	 */
	@Override
	public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask#getBreak4PopupController4Cancel()
	 */
	@Override
	public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
		 return new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(
	        		this.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
	}
    
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		WK_RB_MASK_bt_FolgeWaegung copy= new WK_RB_MASK_bt_FolgeWaegung(this.getTransportHashMap());
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}
   


   
}
 
 
