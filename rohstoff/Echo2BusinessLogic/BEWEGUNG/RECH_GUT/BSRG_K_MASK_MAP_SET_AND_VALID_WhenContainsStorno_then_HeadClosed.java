package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * validierer, der dafuer sorgt, dass bei einer Rechnung/Gutschrift die Kopfmaske 
 * teilweise geschlossen wird, wenn es mindestens eine S2-Position gibt
 * @author martin
 *
 */
public class BSRG_K_MASK_MAP_SET_AND_VALID_WhenContainsStorno_then_HeadClosed extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(
													E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,
													SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(
													E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,
													SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(
													E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,
													SQLMaskInputMAP oInputMap) throws myException {
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
			if (oMAP.get_oInternalSQLResultMAP() != null) {
				String cID_VKOPF_RG = oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				RECLIST_VPOS_RG  rlPOS = new RECLIST_VPOS_RG(
											_DB.VPOS_RG+"."+_DB.VPOS_RG$ID_VKOPF_RG+"="+cID_VKOPF_RG+
											" AND ("+_DB.VPOS_RG$ID_VPOS_RG_STORNO_VORGAENGER+" IS NOT NULL "+
											" OR "+_DB.VPOS_RG$ID_VPOS_RG_STORNO_NACHFOLGER+" IS NOT NULL) ",
											""
											);
				if (rlPOS.get_vKeyValues().size()>0) {
					oMAP.set_ActiveADHOC(_DB.VKOPF_RG$ID_ADRESSE, false, false);
					oMAP.set_ActiveADHOC(_DB.VKOPF_RG$NAME1, false, false);
					oMAP.set_ActiveADHOC(_DB.VKOPF_RG$STRASSE, false, false);
					oMAP.set_ActiveADHOC(_DB.VKOPF_RG$HAUSNUMMER, false, false);
					oMAP.set_ActiveADHOC(_DB.VKOPF_RG$LAENDERCODE, false, false);
					oMAP.set_ActiveADHOC(_DB.VKOPF_RG$PLZ, false, false);
					oMAP.set_ActiveADHOC(_DB.VKOPF_RG$ORT, false, false);
				}
			}
			
		}
		return new MyE2_MessageVector();
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(
													E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,
													SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(
													E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,
													SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

}
