package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS;

import java.math.BigDecimal;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.__FU_FUO_HelpClassValidHandelsdef;


/**
 * validierobjekt fuer die pruefung, ob eine gefundene Handelsdefinition mit der in der Fuhre eingetragene Ergebnis-Lage uebereinstimmt
 * @author martin
 *
 */
public class FUO_Set_And_Valid_VGL_verify_id_handelsdef extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return innerePruefung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return innerePruefung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return innerePruefung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return innerePruefung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}

	
	private MyE2_MessageVector innerePruefung(E2_ComponentMAP oMAP, int iActionType) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (iActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
			//zuerst pruefen, ob es einen maskenwert im id_handelsdef gibt
			long lID_HD = oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_HANDELSDEF, -1l, -1l);
			if (lID_HD>0) {
				
				//zuerst feststellen, ob es ein EK- oder VK-Ort ist, damit klar ist, welche Seite der Handelsdef verglichen werden muss
				boolean bEK_ORT = oMAP.get_oSQLFieldMAP().get_hmRestrictionFieldValues().get(_DB.VPOS_TPA_FUHRE_ORT$DEF_QUELLE_ZIEL).equals("'EK'");
				
				
				//nur was zu pruefen, wenn die handelsdef erfasst ist
				RECORD_HANDELSDEF  	recHD = new RECORD_HANDELSDEF(lID_HD);
				
				RECORD_TAX  		recTAX= null;
				
				BigDecimal 			bdEinzelpreis = oMAP.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$EINZELPREIS, BigDecimal.ZERO, BigDecimal.ZERO);
				if (bEK_ORT) {
					if (S.isFull(recHD.get_ID_TAX_NEGATIV_QUELLE_cUF_NN("")) && bdEinzelpreis.compareTo(BigDecimal.ZERO)<0) {
						recTAX = recHD.get_UP_RECORD_TAX_id_tax_negativ_quelle();
					} else {
						recTAX = recHD.get_UP_RECORD_TAX_id_tax_quelle();
					}
				} else {
					if (S.isFull(recHD.get_ID_TAX_NEGATIV_ZIEL_cUF_NN("")) && bdEinzelpreis.compareTo(BigDecimal.ZERO)<0) {
						recTAX = recHD.get_UP_RECORD_TAX_id_tax_negativ_ziel();
					} else {
						recTAX = recHD.get_UP_RECORD_TAX_id_tax_ziel();
					}
				}
			
				oMV.add_MESSAGE(__FU_FUO_HelpClassValidHandelsdef.pruefe_HandelsDefGegenMaske(recHD, recTAX, oMAP,bEK_ORT,
																								_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE,
																								_DB.VPOS_TPA_FUHRE_ORT$STEUERSATZ, 
																								_DB.VPOS_TPA_FUHRE_ORT$EU_STEUER_VERMERK, 
																								_DB.VPOS_TPA_FUHRE_ORT$ID_TAX, 
																								_DB.VPOS_TPA_FUHRE_ORT$INTRASTAT_MELD, 
																								_DB.VPOS_TPA_FUHRE_ORT$TRANSIT, 
																								VPOS_TPA_FUHRE_ORT.datum_lade_ablade.fn(),
																								"(Zusatzort)"));
			}
		}
		
		return oMV;
	}
	
	
	
	
}
