package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS;

import java.math.BigDecimal;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
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
public class FU_Set_And_Valid_VGL_verify_id_handelsdef extends XX_MAP_Set_And_Valid {

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
			long lID_HD = oMAP.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_HANDELSDEF, -1l, -1l);
			if (lID_HD>0) {
				//nur was zu pruefen, wenn die handelsdef erfasst ist
				RECORD_HANDELSDEF  	recHD = 			new RECORD_HANDELSDEF(lID_HD);
				
				RECORD_TAX  		recTAX_EK = null;
				RECORD_TAX  		recTAX_VK = null;
				
				BigDecimal 			bdEinzelpreisEK = 	oMAP.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE$EINZELPREIS_EK, BigDecimal.ZERO, BigDecimal.ZERO);
				BigDecimal 			bdEinzelpreisVK = 	oMAP.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE$EINZELPREIS_VK, BigDecimal.ZERO, BigDecimal.ZERO);
				
				if (S.isFull(recHD.get_ID_TAX_NEGATIV_QUELLE_cUF_NN("")) && bdEinzelpreisEK.compareTo(BigDecimal.ZERO)<0) {
					recTAX_EK = recHD.get_UP_RECORD_TAX_id_tax_negativ_quelle();
				} else {
					recTAX_EK = recHD.get_UP_RECORD_TAX_id_tax_quelle();
				}
				
				if (S.isFull(recHD.get_ID_TAX_NEGATIV_ZIEL_cUF_NN("")) && bdEinzelpreisVK.compareTo(BigDecimal.ZERO)<0) {
					recTAX_VK = recHD.get_UP_RECORD_TAX_id_tax_negativ_ziel();
				} else {
					recTAX_VK = recHD.get_UP_RECORD_TAX_id_tax_ziel();
				}
			
				oMV.add_MESSAGE(__FU_FUO_HelpClassValidHandelsdef.pruefe_HandelsDefGegenMaske(recHD, recTAX_EK, oMAP,true,
																								_DB.VPOS_TPA_FUHRE$ID_ADRESSE_START,
																								_DB.VPOS_TPA_FUHRE$STEUERSATZ_EK, 
																								_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_EK, 
																								_DB.VPOS_TPA_FUHRE$ID_TAX_EK, 
																								_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_IN, 
																								_DB.VPOS_TPA_FUHRE$TRANSIT_EK, 
																								VPOS_TPA_FUHRE.datum_aufladen.fn(),
																								"(Quelle)"));
				
				oMV.add_MESSAGE(__FU_FUO_HelpClassValidHandelsdef.pruefe_HandelsDefGegenMaske(recHD, recTAX_VK, oMAP,false,
																								_DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL,
																								_DB.VPOS_TPA_FUHRE$STEUERSATZ_VK, 
																								_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_VK, 
																								_DB.VPOS_TPA_FUHRE$ID_TAX_VK, 
																								_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_OUT, 
																								_DB.VPOS_TPA_FUHRE$TRANSIT_VK,
																								VPOS_TPA_FUHRE.datum_abladen.fn(),
																								"(Ziel)"));
				
			}
				
			
		}
		
		return oMV;
	}
	
	
	
	
}
