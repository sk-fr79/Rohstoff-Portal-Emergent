/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_Break4Popup_checkSperrFlag;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_KEY;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_MASK_DataObjectCollector;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Steuerdatei;

/**
 * @author martin
 *
 */
public class _PdServiceOpenAH7SteuerTabelleMask4EditOrView {

	public void show(Long id_adresse_lager_start, Long id_adresse_lager_ziel,XX_ActionAgent  agentAfterSaveing, MASK_STATUS status) throws myException {
		//sicherheitsabfrage, falls im hintergrund bereits ein datensatz gebaut wurde
		if (O.isOneNull(id_adresse_lager_start,id_adresse_lager_ziel) || id_adresse_lager_start<=0 || id_adresse_lager_ziel<=0) {
			throw new myException("ids MUST NOT be null or 0!");
		}
		
		if (status != MASK_STATUS.EDIT && status != MASK_STATUS.VIEW) {
			throw new myException("only view and edit are allowed !");
		}
		
		Rec21_AH7_Steuerdatei rah = new Rec21_AH7_Steuerdatei(id_adresse_lager_start.toString(), id_adresse_lager_ziel.toString());
		if (! rah.is_ExistingRecord()) {
			bibMSG.MV()._addAlarm("Achtung! Bearbeitung abgebrochen! Der Datensatz existiert nicht mehr !");
		} else {
		
			AH7_MASK_MaskModulContainer mask = new AH7_MASK_MaskModulContainer();
			mask.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(new AH7_MASK_DataObjectCollector(rah.getUfs(AH7_STEUERDATEI.id_ah7_steuerdatei),status));
			
			mask.get_oRowForButtons().removeAll();
			E2_Grid btGrid = new E2_Grid();
			mask.get_oRowForButtons().add(btGrid);
			
			RB_bt_maskSaveAndClose 	btSave = 	(RB_bt_maskSaveAndClose)new RB_bt_maskSaveAndClose(mask)._aaa(new RB_actionStandardSave(mask))._aaa(new RB_actionStandardClosePopup(mask));
			btSave.setBreak4PopupController(new AH7_Break4Popup_checkSperrFlag(mask));

			if (agentAfterSaveing!=null) {
				btSave.add_oActionAgent(agentAfterSaveing);
			}
			
			RB_bt_maskClose 		btCancel = 	(RB_bt_maskClose)new RB_bt_maskClose(mask)._aaa(new RB_actionStandardClosePopup(mask));
			btCancel.setBreak4PopupController(new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(mask.rb_ComponentMapCollector(new AH7_KEY()),null)));

			mask.setButtonForClosingWindow(btCancel);
			
			if (status==MASK_STATUS.EDIT) {
				btGrid._a(btSave, new RB_gld()._ins(3))._a(btCancel, new RB_gld()._ins(3));
			} else {
				btGrid._a(btCancel, new RB_gld()._ins(3));
			}
			
			mask.CREATE_AND_SHOW_POPUPWINDOW(S.ms(status==MASK_STATUS.EDIT?"Bearbeiten einer AH7-Steuerungs-Relation ...":"Anzeige einer AH7-Steuerungs-Relation ..."));
		}
	}
	
	
	
}
