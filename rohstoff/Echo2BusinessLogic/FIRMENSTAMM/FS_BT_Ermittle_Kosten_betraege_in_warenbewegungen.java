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

public class FS_BT_Ermittle_Kosten_betraege_in_warenbewegungen extends MyE2_Button {

	private Vector<String>  vID_ADRESSEN = new Vector<String>();
	
	private E2_NavigationList   oNaviList = null;
	
	public FS_BT_Ermittle_Kosten_betraege_in_warenbewegungen(E2_NavigationList  naviList) {
		super(new MyE2_String("Kostenrelationen: Kosten zu Warenbewegungen aufbauen"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		this.oNaviList = naviList;
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.LIST_VALID_ERMITTLE_KOSTEN);

	}
 
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FS_BT_Ermittle_Kosten_betraege_in_warenbewegungen oThis = FS_BT_Ermittle_Kosten_betraege_in_warenbewegungen.this;
			
			//zuerst die auswahlbox anzeigen: welcher adressbereich soll genutzt werden ?
			new ownPopupWaehleGewuenschteAdressen().ShowPopup(oThis.oNaviList, new MyE2_String("Bitte wählen Sie den gewünschten Adressenbereich"));
		}			
	}
	 
	private class ownPopupWaehleGewuenschteAdressen extends POPUP_QueryInList_SELECTED_SEITE_ALL {
	
			@Override
			public void do_when_ok_is_clicked(Vector<String> vIDs) 	throws myException {
				
				//Adressen merken und weiter mit der Frage der Modalitaeten der Kostenermittlung
				FS_BT_Ermittle_Kosten_betraege_in_warenbewegungen oThis = FS_BT_Ermittle_Kosten_betraege_in_warenbewegungen.this;
				
				oThis.vID_ADRESSEN.removeAllElements();
				oThis.vID_ADRESSEN.addAll(vIDs);
				
				new TP_BerechneKostenSaetze(oThis.vID_ADRESSEN);
			}
			
		}
		
	
	
	
}
