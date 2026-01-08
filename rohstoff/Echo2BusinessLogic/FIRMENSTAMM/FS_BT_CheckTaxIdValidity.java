package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.POPUP_QueryInList_SELECTED_SEITE_ALL;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE.TP_BerechneKostenSaetze;
import rohstoff.Echo2BusinessLogic._TAX.TAX__CheckForeignTaxIds;

public class FS_BT_CheckTaxIdValidity extends MyE2_Button {

	private Vector<String>  addressIds = new Vector<String>();
	
	private E2_NavigationList   oNaviList = null;
	
	public FS_BT_CheckTaxIdValidity(E2_NavigationList  naviList) {
		super(new MyE2_String("Checke USt-ID auf Gültigkeit"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		this.oNaviList = naviList;
		this.add_oActionAgent(new OpenSelectionPopup());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.LIST_VALID_ERMITTLE_KOSTEN);

	}

	/** 
	 * Shows a popup in which the current selection can be made more precise or extended to all
	 * visible / all records at all of the list 
	 */
	private class OpenSelectionPopup extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new SelectAdresses().ShowPopup(oNaviList, new MyE2_String("Bitte wählen Sie den gewünschten Adressenbereich"));
		}			
	}
	 
	private class SelectAdresses extends POPUP_QueryInList_SELECTED_SEITE_ALL {
		@Override
		public void do_when_ok_is_clicked(Vector<String> ids) 	throws myException {
			addressIds.removeAllElements();
			addressIds.addAll(ids);
			new TAX__CheckForeignTaxIds(addressIds);
		}
	}
}
