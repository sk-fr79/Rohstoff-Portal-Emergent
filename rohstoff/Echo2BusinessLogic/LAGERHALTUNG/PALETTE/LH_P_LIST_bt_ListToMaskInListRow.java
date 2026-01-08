
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;


import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class LH_P_LIST_bt_ListToMaskInListRow extends RB_BtV4_List2Mask {

	public LH_P_LIST_bt_ListToMaskInListRow(boolean bEdit, RB_TransportHashMap  p_tpHashMap) throws myException {
		super(bEdit);
		this._setTransportHashMap(p_tpHashMap);
//Fehler führt beim neuladen der liste zu einer falschen MASK_STATUS-situation 2020-09-14//		this.getTransportHashMap()._setMaskStatusOnLoad(this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW);
		
		//2020-09-14: saveAndReopen ausgeblendet
		this._setShowSaveAndReOpenButton(false);
		
		this._addGlobalValidator(new ownValidator());
		this.add_GlobalValidator(bEdit?LH_P_VALIDATORS.EDIT.getValidator():LH_P_VALIDATORS.VIEW.getValidator());

	}
	@Override
	public RB_ModuleContainerMASK generateMaskContainer() throws myException {
		
		this.getTransportHashMap()._setRBModulContainerMask(new LH_P_MASK_MaskModulContainer(this.getTransportHashMap()));
		return this.getTransportHashMap().getRBModulContainerMask();
	}

	private class ownValidator extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();

			SQLResultMAP  oSqlResultMap = LH_P_LIST_bt_ListToMaskInListRow.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
			
			boolean is_edit 			= LH_P_LIST_bt_ListToMaskInListRow.this.isUsedToEdit();
			boolean is_hand_ausgebucht	= oSqlResultMap.get_FormatedValue(LAGER_PALETTE.ausbuchung_hand.fn(), "N").equals("Y");
			String idVposAusbuchung 	= oSqlResultMap.get_FormatedValue(LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fn(), "");

			if( (S.isFull(idVposAusbuchung) || is_hand_ausgebucht) && is_edit) {
				bibMSG.MV()._addAlarm("Diese Palette kann nicht editiert werden, sie ist schon ausgebucht !");
			}

			return mv;
		}

	}

	@Override
	public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {

		LH_P_LIST_bt_ListToMaskInListRow oThis = LH_P_LIST_bt_ListToMaskInListRow.this;

		if (this.EXT().get_oComponentMAP()!=null) {
		long  id_palette = 0;
		SQLResultMAP oIntSqlMap = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
		if(this.getTransportHashMap().getLeadingMaskKey().get_db_table() == _TAB.lager_palette_box) {
			id_palette = oIntSqlMap.get_LActualDBValue(LAGER_PALETTE_BOX.id_lager_palette.tnfn(), true);
			}else if(this.getTransportHashMap().getLeadingMaskKey().get_db_table() == _TAB.lager_palette) {
				
				id_palette = Long.parseLong(oIntSqlMap.get_cUNFormatedROW_ID());
		}

			if (id_palette>0) {
				RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
				RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
				collector.put(""+id_palette, new LH_P_MASK_DataObjectCollector(oThis.getTransportHashMap(), ""+id_palette,aktuellerStatus));
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
		return new MyE2_String(this.isUsedToEdit()?"Bearbeiten einer Palette":"Anzeige einer Palette");
	}

	@Override
	public MyE2_String generateMessagetextForSaveRecord() throws myException {
		return new MyE2_String("Palette wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
		Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
		LH_P_LIST_bt_ListToMaskInListRow oThis = LH_P_LIST_bt_ListToMaskInListRow.this;
		v_rueck.add(new XX_ActionAgentWhenCloseWindow(oThis.getTransportHashMap().getRBModulContainerMask()) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				oThis.getTransportHashMap().getNavigationList()._REBUILD_ACTUAL_SITE("");
			}
		});

		return v_rueck;
	}

	@Override
	public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
		//return new E2_Break4PopupController()._registerBreak(new LH_P_Break4PopUp_BoxAenderung(this.getTransportHashMap()));
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
		LH_P_LIST_bt_ListToMaskInListRow copy = null;
		try {
			copy = new LH_P_LIST_bt_ListToMaskInListRow(this.isUsedToEdit(), this.getTransportHashMap());
			copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		} catch (myException e) {
			e.printStackTrace();
		}
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


