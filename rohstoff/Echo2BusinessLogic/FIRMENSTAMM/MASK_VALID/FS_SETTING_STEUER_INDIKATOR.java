package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MASK_VALID;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_MASKEN_INDIKATOR;


/**
 * map-setter fuer die aktualisierung des steuer-status-indikators auf der ersten seite
 * @author martin
 *
 */
public class FS_SETTING_STEUER_INDIKATOR extends XX_MAP_Set_And_Valid {

	
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(		E2_ComponentMAP oMAP, 
																			int 			ActionType, 
																			ExecINFO 		oExecInfo,
																			SQLMaskInputMAP oInputMap) throws myException {

		return this.Referesh_Indikator(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(	E2_ComponentMAP oMAP, 
																			int 			ActionType, 
																			ExecINFO 		oExecInfo,
																			SQLMaskInputMAP oInputMap) throws myException {
		return this.Referesh_Indikator(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(			E2_ComponentMAP oMAP, 
																			int ActionType, 
																			ExecINFO oExecInfo,
																			SQLMaskInputMAP oInputMap) throws myException {	
		return this.Referesh_Indikator(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(			E2_ComponentMAP oMAP, 
																			int ActionType, 
																			ExecINFO oExecInfo,
																			SQLMaskInputMAP oInputMap) throws myException {	
		return this.Referesh_Indikator(oMAP, ActionType, oExecInfo, oInputMap);
	}

	
	private MyE2_MessageVector Referesh_Indikator(	E2_ComponentMAP oMAP, 
													int ActionType, 
													ExecINFO oExecInfo,
													SQLMaskInputMAP oInputMap) throws myException{
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION || ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
			E2_ComponentMAP  oMAP_ADRESSE = null;
			
			for (E2_ComponentMAP  map: oMAP.get_E2_vCombinedComponentMAPs()) {
				if (map.get_oSQLFieldMAP().get_cMAIN_TABLE().equals(_DB.ADRESSE)) {
					oMAP_ADRESSE = map;
				}
			}

			
			((__FS_MASKEN_INDIKATOR) oMAP_ADRESSE.get__Comp(FS_CONST.MASK_FIELD_COMP_ANZEIGE_STEUER_STATUS)).REFRESH_ANZEIGE();
		}
		
		return new MyE2_MessageVector();
		
	}
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(	E2_ComponentMAP oMAP, 
																			int ActionType, 
																			ExecINFO oExecInfo,
																			SQLMaskInputMAP oInputMap) throws myException {	
		return this.Referesh_Indikator(oMAP, ActionType, oExecInfo, oInputMap);
	}

}
