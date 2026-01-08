
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;


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
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

public class LH_LIST_COMP_detailblock_bt_List2Mask extends RB_BtV4_List2Mask {
	
	private String 		fuhre_id 	= "";
	private VEK<String> palettenIds = new VEK<String>();
	
	public LH_LIST_COMP_detailblock_bt_List2Mask(boolean bEdit, RB_TransportHashMap  p_tpHashMap, String fuhre_id, VEK<String> vPalettenIds) {
		super(bEdit);
		
		this._setTransportHashMap(p_tpHashMap);
		
		this.add_GlobalValidator(bEdit?LH_VALIDATORS.EDIT.getValidator():LH_VALIDATORS.VIEW.getValidator());
		
		this.fuhre_id 		= fuhre_id;
		this.palettenIds._a(vPalettenIds);
	}
	
	@Override
	public RB_ModuleContainerMASK generateMaskContainer() throws myException {
		
		this.getTransportHashMap()._setToExtender(LH_CONST.LH_EXTENDER.LH_FUHRE_ID, fuhre_id);
		this.getTransportHashMap()._setToExtender(LH_CONST.LH_EXTENDER.LH_EINZELPALETTE_LISTE, palettenIds);
		this.getTransportHashMap()._setMaskStatusOnLoad(this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW);

		if (this.EXT().get_oComponentMAP()!=null) {
			MyLong id_box = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			

			RB_TransportHashMap trp_hm = this.getTransportHashMap();

			trp_hm._setToExtender(LH_CONST.LH_EXTENDER.LH_ID_BOXNUMMER, id_box.get_cUF_LongString());
			
			this.getTransportHashMap()._setRBModulContainerMask(new LH_MASK_MaskModulContainer(this.getTransportHashMap()));
			
			return this.getTransportHashMap().getRBModulContainerMask();
		} else {
			throw new myException("Error:  no containing E2_ComponentMAP");
		}
	}


	@Override
	public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {

		LH_LIST_COMP_detailblock_bt_List2Mask oThis = LH_LIST_COMP_detailblock_bt_List2Mask.this;	

		if (this.EXT().get_oComponentMAP()!=null) {
			MyLong  id = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());

			if (id.isOK()) {
				RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
				RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
				collector.put(id.get_cUF_LongString(), new LH_MASK_DataObjectCollector(oThis.getTransportHashMap(), id.get_cUF_LongString(),aktuellerStatus));
				return collector;
			} else {
				throw new myException("Error finding id to Edit");
			}
		} else {
			throw new myException("Error:  no containing E2_ComponentMAP");
		}
	}


	@Override
	public MyE2_String generateTitelInfo4MaskWindow() throws myException {
		MyLong boxNr = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_FormatedValue(LAGER_BOX.boxnummer.fn()));
		
		return S.ms((this.isUsedToEdit()?"Bearbeiten Box Nr."+boxNr.get_cUF_LongString():"Anzeige Box Nr."+boxNr.get_cUF_LongString()));
	}

	@Override
	public MyE2_String generateMessagetextForSaveRecord() throws myException {
		return new MyE2_String("Box Inhalt wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
		Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
		LH_LIST_COMP_detailblock_bt_List2Mask oThis = LH_LIST_COMP_detailblock_bt_List2Mask.this;
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


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}


	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		LH_LIST_COMP_detailblock_bt_List2Mask copy= new LH_LIST_COMP_detailblock_bt_List2Mask(this.isUsedToEdit(), this.getTransportHashMap(), fuhre_id, palettenIds);
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}

	/**
	 * spezielle methode zum ein permanent enables object doch noch disablen zu koennen
	 * @param enabled
	 * @throws myException
	 */
	public void setEnabledForEditOfSuperClass(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
	}



}


