/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MASK_VALID;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EN_FS_Fields;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_Component_MASK_WeitereWaehrungen;

/**
 * @author martin
 *
 */
public class FS_ValidZusatzWaehrungen extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doValid(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doValid(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doValid(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doValid(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return this.doValid(oMAP, ActionType, oExecInfo, oInputMap);
	}

	
	private MyE2_MessageVector doValid(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		//aktuelle ID checken
		String id_waehrung_haupt = oMAP.get_cActualDBValueFormated(ADRESSE.id_waehrung.fn(),"-1");
		String id_waehrung_hauptUfs = "";
		MyLong lId = new MyLong(id_waehrung_haupt);
		if (lId.isOK()) {
			id_waehrung_hauptUfs = lId.get_cUF_LongString();
		}
		

		//die zusaetzlichen IDs
		FS_Component_MASK_WeitereWaehrungen zusatz = (FS_Component_MASK_WeitereWaehrungen)oMAP.get__Comp(EN_FS_Fields.MASK_FIELD_ZUSATZWAEHRUNGEN.name());
		VEK<String>  vIdsZusatz = zusatz.getIdsZusatzWaehrung();

		boolean alarm = (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION);
		
		if (vIdsZusatz.contains(id_waehrung_haupt) || vIdsZusatz.contains(id_waehrung_hauptUfs)) {
			if (alarm) {
				mv._addAlarm(S.ms("Die Hauptwährung ist bereits als Zusatzwährung erfasst. Zuerst die Zusatzwährung löschen !"));
			} else {
				mv._addWarn(S.ms("Die Hauptwährung ist bereits als Zusatzwährung erfasst. Zuerst die Zusatzwährung löschen !"));
			}
		}
		
		return mv;
	}
	
}
