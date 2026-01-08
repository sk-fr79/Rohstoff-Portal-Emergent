/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO__CONST;

/**
 * @author martin
 *
 */
public class FUO_Set_And_Valid_AnzeigeFremdWaehrung extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doSettings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doSettings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doSettings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doSettings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doSettings(oMAP, ActionType, oExecInfo, oInputMap);
	}

	
	private MyE2_MessageVector doSettings(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) {
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION || ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION || ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_UNBOUND_KLICK_ACTION) {
			Warenbewegung_InfoBlockFremdWaehrung infoGrid = (Warenbewegung_InfoBlockFremdWaehrung)oMAP.get(FUO__CONST.FIELDNAME_INFOBOCK_WAEHRUNGEN);
			
			infoGrid.fill();
		}
		
		return new MyE2_MessageVector();
	}
	

	//fue manuellen aufruf aus der maske heraus (aktion nach adress-suche)
	public void setManual(E2_ComponentMAP oMAP) {
		Warenbewegung_InfoBlockFremdWaehrung infoGrid = (Warenbewegung_InfoBlockFremdWaehrung)oMAP.get(FU___CONST.FIELDNAME_INFOBOCK_WAEHRUNGEN);
		infoGrid.fill();
	}
	
	
}
