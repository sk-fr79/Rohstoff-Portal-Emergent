/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE
 * @author manfred
 * @date 10.05.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * Button um die Edit/View - Maske neu aufzubauen von der bestehenden Edit/View-Maske 
 * (Click auf Subgrid öffnet die Maske neu mit den jeweiligen Daten)
 * @author manfred
 * @date 10.05.2019
 *
 */
public class WF_SIMPLE_BT_MaskToMask extends RB_BtV4_List2Mask{

	String _idLaufzettelEintrag = null;
	RB_ModuleContainerMASK _mask2Close = null;
	
	/**
	 * @author manfred
	 * @date 10.05.2019
	 *
	 * @param bEdit
	 */
	public WF_SIMPLE_BT_MaskToMask(boolean bEdit, RB_TransportHashMap  p_tpHashMap, String id_LZE ,RB_ModuleContainerMASK mask2Close ) {
		super(bEdit);
		this._idLaufzettelEintrag = id_LZE;
		this._mask2Close = mask2Close;
		
		this._setTransportHashMap(p_tpHashMap);
        this.add_GlobalValidator(bEdit?WF_SIMPLE_VALIDATORS.EDIT.getValidator():WF_SIMPLE_VALIDATORS.EDIT.getValidator());
	}
	


    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
    	// close old mask
    	this.getTransportHashMap()._setMaskStatusOnLoad(this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW);
    	this.getTransportHashMap()._setRBModulContainerMask(new WF_SIMPLE_MASK_MaskModulContainer(this.getTransportHashMap()));
    	_mask2Close.CLOSE_AND_DESTROY_POPUPWINDOW(true);
    	
        return this.getTransportHashMap().getRBModulContainerMask();
    }
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask#generateDataObjects4Mask(panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
		WF_SIMPLE_BT_MaskToMask oThis = WF_SIMPLE_BT_MaskToMask.this;
    	
		MyLong  id = new MyLong(_idLaufzettelEintrag);
		
		if (id.isOK()) {
	        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
	        RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
            collector.put(id.get_cUF_LongString(), new WF_SIMPLE_MASK_DataObjectCollector(oThis.getTransportHashMap(), id.get_cUF_LongString(),aktuellerStatus));
            return collector;
		} else {
			throw new myException("Error finding id to Edit");
		}

	}

	
	
    @Override
    public MyE2_String generateTitelInfo4MaskWindow() throws myException {
        return new MyE2_String(this.isUsedToEdit()?"Bearbeiten eines Laufzettel-Eintrags":"Anzeige eines Laufzettel-Eintrags");
    }
    
    
    
    @Override
    public MyE2_String generateMessagetextForSaveRecord() throws myException {
        return new MyE2_String("Laufzettel-Eintrag wurde gespeichert");
    }
    
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
     	WF_SIMPLE_BT_MaskToMask oThis = WF_SIMPLE_BT_MaskToMask.this;
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(oThis.getTransportHashMap().getRBModulContainerMask()) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				oThis.getTransportHashMap().getNavigationList()._RebuildSiteAndKeepMarkers("");
			}
		});
    	
        return v_rueck;
    }



//	/* (non-Javadoc)
//	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#rb_set_belongs_to(java.lang.Object)
//	 */
//	@Override
//	public void rb_set_belongs_to(RB_ComponentMap obj) throws myException {
//		// TODO Auto-generated method stub
//		
//	}


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
		WF_SIMPLE_BT_MaskToMask copy= new WF_SIMPLE_BT_MaskToMask(this.isUsedToEdit(), this.getTransportHashMap(),this._idLaufzettelEintrag,this._mask2Close);
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}
   
    	
	

}
