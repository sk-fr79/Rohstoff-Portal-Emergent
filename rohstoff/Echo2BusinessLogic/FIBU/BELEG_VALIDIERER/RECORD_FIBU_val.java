package rohstoff.Echo2BusinessLogic.FIBU.BELEG_VALIDIERER;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_ENUMS.FIBU;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.exceptions.myException;


public class RECORD_FIBU_val extends RECORD_FIBU {

	public enum buchungstyp {
		ZAHLUNGSAUSGANG
		,ZAHLUNGSEINGANG
		,DRUCK_RECHNUNG
		,SCHECKDRUCK
		,DRUCK_GUTSCHRIFT
	}
	
	
	public RECORD_FIBU_val(RECORD_FIBU recordOrig) {
		super(recordOrig);
	}

	
	public BELEGTYP4VALID belegTyp() throws myException {
		
		if (this.ufs(FIBU.buchungstyp).equals(buchungstyp.ZAHLUNGSAUSGANG.name())) {
			return BELEGTYP4VALID.ZAHLUNGSAUSGANG;
		} else if (this.ufs(FIBU.buchungstyp).equals(buchungstyp.ZAHLUNGSEINGANG.name())) {
			return BELEGTYP4VALID.ZAHLUNGSEINGANG;
		} else if (this.ufs(FIBU.buchungstyp).equals(buchungstyp.SCHECKDRUCK.name())) {
			return BELEGTYP4VALID.SCHECK;
		} else if (this.ufs(FIBU.buchungstyp).equals(buchungstyp.DRUCK_RECHNUNG.name())) {
			RECORD_VKOPF_RG  rech = this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
			if (rech == null) {throw new myException("Error validing RECODRD_FIBU (1)"); }
		
			//feststellen, wieviele stornopositionen vorhanden sind
			int iCountStorno 			= 0;
			boolean b_positiv_endbetrag = this.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>=0;
			
			RECLIST_VPOS_RG  rl_pos = rech.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg();
			for (RECORD_VPOS_RG p: rl_pos) {
				if (p.is_DELETED_NO()) {
					if (p.l(VPOS_RG.id_vpos_rg_storno_vorgaenger, -1L).longValue()>0) {
						iCountStorno++;
					}
				}
			}
			
			if (iCountStorno==0) {
				if (b_positiv_endbetrag) {
					return BELEGTYP4VALID.RECH_POSITIV;
				} else {
					return BELEGTYP4VALID.RECH_NEGATIV;
				}
			} else {
				if (b_positiv_endbetrag) {
					return BELEGTYP4VALID.RECH_POSITIV_STORNO;
				} else {
					return BELEGTYP4VALID.RECH_NEGATIV_STORNO;
				}
			}

		} else if (this.ufs(FIBU.buchungstyp).equals(buchungstyp.DRUCK_GUTSCHRIFT.name())) {
			RECORD_VKOPF_RG  rech = this.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
			if (rech == null) {throw new myException("Error validing RECODRD_FIBU (2)"); }
		
			//feststellen, wieviele stornopositionen vorhanden sind
			int iCountStorno = 0;
			boolean b_positiv = this.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>=0;
			
			RECLIST_VPOS_RG  rl_pos = rech.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg();
			for (RECORD_VPOS_RG p: rl_pos) {
				if (p.is_DELETED_NO()) {
					if (p.l(VPOS_RG.id_vpos_rg_storno_vorgaenger, -1L).longValue()>0) {
						iCountStorno++;
					}
				}
			}
			
			if (iCountStorno==0) {
				if (b_positiv) {
					return BELEGTYP4VALID.GUT_POSITIV;
				} else {
					return BELEGTYP4VALID.GUT_NEGATIV;
				}
			} else {
				if (b_positiv) {
					return BELEGTYP4VALID.GUT_POSITIV_STORNO;
				} else {
					return BELEGTYP4VALID.GUT_NEGATIV_STORNO;
				}
			}
		} else {
			throw new myException("Error validing RECODRD_FIBU (3)");
		}
	}
	
}
