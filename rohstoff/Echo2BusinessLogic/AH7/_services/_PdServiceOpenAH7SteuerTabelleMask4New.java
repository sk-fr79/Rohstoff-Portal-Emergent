/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.O;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.AH7.AH7_Break4Popup_checkSperrFlag;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_KEY;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_MASK_DataObjectCollector;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_Mask_SearchStation;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Steuerdatei;

/**
 * @author martin
 *
 */
public class _PdServiceOpenAH7SteuerTabelleMask4New {
	
	
	private Long id_adresse_lager_start;
	private Long id_adresse_lager_ziel;

	public void show(Long id_adresse_lager_start, Long id_adresse_lager_ziel,XX_ActionAgent  agentAfterSaveing) throws myException {
		//sicherheitsabfrage, falls im hintergrund bereits ein datensatz gebaut wurde
		if (O.isOneNull(id_adresse_lager_start,id_adresse_lager_ziel) || id_adresse_lager_start<=0 || id_adresse_lager_ziel<=0) {
			throw new myException("ids MUST NOT be null or 0!");
		}
		
		Rec21_AH7_Steuerdatei rah = new Rec21_AH7_Steuerdatei(id_adresse_lager_start.toString(), id_adresse_lager_ziel.toString());
		if (rah.is_ExistingRecord()) {
			bibMSG.MV()._addAlarm("Achtung! Neueingabe abgebrochen. Es existiert bereits ein AH7-Steuerungs-Datensatz !");
		} else {
		
			this.id_adresse_lager_start = id_adresse_lager_start;
			this.id_adresse_lager_ziel = id_adresse_lager_ziel;
			if (id_adresse_lager_start==null || id_adresse_lager_ziel==null || id_adresse_lager_start.intValue()<=0 || id_adresse_lager_ziel.intValue()<=0) {
				throw new myException(this, "ids MUST not be null !!");
			}
			
			OwnAH7_MASK_MaskModulContainer mask = new OwnAH7_MASK_MaskModulContainer();
			mask.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(new AH7_MASK_DataObjectCollector());
			
			mask.get_oRowForButtons().removeAll();
			E2_Grid btGrid = new E2_Grid();
			mask.get_oRowForButtons().add(btGrid);
			
			RB_bt_maskSaveAndClose 	btSave = 	(RB_bt_maskSaveAndClose)new RB_bt_maskSaveAndClose(mask)._aaa(new RB_actionStandardSave(mask))._aaa(new RB_actionStandardClosePopup(mask));
			btSave.setBreak4PopupController(new AH7_Break4Popup_checkSperrFlag(mask));
			
			if (agentAfterSaveing!=null) {
				btSave.add_oActionAgent(agentAfterSaveing);
			}
			
			RB_bt_maskClose 		btCancel = 	(RB_bt_maskClose)new RB_bt_maskClose(mask)._aaa(new RB_actionStandardClosePopup(mask));
			
			//hier muss der abbruchvalidierer auf geaenderte maskefelder die im validierer gesetzen ausnahmen beruecksichtigen
			VEK<Pair<RB_K>> ausnahmen = new VEK<Pair<RB_K>>()
					._a(new Pair<RB_K>(new AH7_KEY(),AH7_STEUERDATEI.id_adresse_geo_start.fk()))
					._a(new Pair<RB_K>(new AH7_KEY(),AH7_STEUERDATEI.id_adresse_geo_ziel.fk()))
					._a(new Pair<RB_K>(new AH7_KEY(),AH7_STEUERDATEI.id_adresse_jur_start.fk()))
					._a(new Pair<RB_K>(new AH7_KEY(),AH7_STEUERDATEI.id_adresse_jur_ziel.fk()))
					; 
			btCancel.setBreak4PopupController(new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(mask.rb_ComponentMapCollector(new AH7_KEY()),ausnahmen)));
			
			mask.setButtonForClosingWindow(btCancel);

			
			btGrid._a(btSave, new RB_gld()._ins(3))._a(btCancel, new RB_gld()._ins(3));
			
			
			mask.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Erfassen einer AH7-Steuerungs-Relation ..."));
		}
	}
	
	
	private class OwnAH7_MASK_MaskModulContainer extends AH7_MASK_MaskModulContainer {

		public OwnAH7_MASK_MaskModulContainer() throws myException {
			super();
			this.getCompMapCollector().rb_get_ComponentMAP(new AH7_KEY()).registerSetterValidators(AH7_STEUERDATEI.id_ah7_steuerdatei, new OwnMaskSetAndValidFillOrte());
		}
		
		private class OwnMaskSetAndValidFillOrte extends RB_Mask_Set_And_Valid {

			/* (non-Javadoc)
			 * @see panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid#make_Interactive_Set_and_Valid(panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap, panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE, panter.gmbh.Echo2.ActionEventTools.ExecINFO)
			 */
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
					AH7_Mask_SearchStation stationStart = ((AH7_Mask_SearchStation)OwnAH7_MASK_MaskModulContainer.this.getCompMapCollector().rb_Comp(new AH7_KEY(), AH7_STEUERDATEI.id_adresse_geo_start));
					AH7_Mask_SearchStation stationZiel =  ((AH7_Mask_SearchStation)OwnAH7_MASK_MaskModulContainer.this.getCompMapCollector().rb_Comp(new AH7_KEY(), AH7_STEUERDATEI.id_adresse_geo_ziel));
					stationStart.rb_set_db_value_manual(id_adresse_lager_start.toString());
					stationStart.do_mask_settings_after_search(id_adresse_lager_start.toString(), true);
					
					stationZiel.rb_set_db_value_manual(id_adresse_lager_ziel.toString());
					stationZiel.do_mask_settings_after_search(id_adresse_lager_ziel.toString(), true);
					
					((AH7_Mask_SearchStation)OwnAH7_MASK_MaskModulContainer.this.getCompMapCollector().rb_Comp(new AH7_KEY(), AH7_STEUERDATEI.id_adresse_geo_start)).set_bEnabled_For_Edit(false);
					((AH7_Mask_SearchStation)OwnAH7_MASK_MaskModulContainer.this.getCompMapCollector().rb_Comp(new AH7_KEY(), AH7_STEUERDATEI.id_adresse_geo_ziel)).set_bEnabled_For_Edit(false);

				}
				return mv;
			}
			
		}
	}

	
}
