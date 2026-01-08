package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * maskenvalidierer, der sicherstellt, dass kein Schrottsorten 
 * @author martin
 *
 */
public class BSRG_P_MASK__ComponentMAP_VALID_SET_SCHROTT extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,
																		SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.get_Check_Neuerfassung_Schrottsorte(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

	
	private MyE2_MessageVector get_Check_Neuerfassung_Schrottsorte(E2_ComponentMAP oMAP, int ActionType) throws myException {
		MyE2_MessageVector oMVRueck =  new MyE2_MessageVector();
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
				
			long idArtikel = oMAP.get_LActualDBValue(_DB.VPOS_RG$ID_ARTIKEL, -1l, -1l);
			
			if (bib_Settigs_Mandant.IS__Value("RECHNUNGSPOSITION_MANUELL_SCHROTTSORTE_VERBIETEN", "N","N")) {
				if (idArtikel>0) {
					RECORD_ARTIKEL  recArtikel = new RECORD_ARTIKEL(idArtikel);
					
					if (!(recArtikel.is_DIENSTLEISTUNG_YES() || recArtikel.is_IST_PRODUKT_YES() || recArtikel.is_END_OF_WASTE_YES())) {
						oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Manuelle Erfassung ist nur für Produkte/EndOfWaste und Dienstleistungen erlaubt !")));
					}
					
				}
			}
		}
		
		return oMVRueck;
	}
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

	
}
