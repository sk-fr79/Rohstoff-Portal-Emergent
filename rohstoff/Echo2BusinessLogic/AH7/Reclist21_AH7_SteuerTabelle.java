/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;


import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.In;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class Reclist21_AH7_SteuerTabelle extends RecList21 {

	public Reclist21_AH7_SteuerTabelle() {
		super(_TAB.ah7_steuerdatei);
	}

	
	/**
	 * fuelle die RecList mit allen Steuertabelle-relationen, die in der fuhre/fuhrenorte vorkommen
	 * @param recFuhre
	 * @return
	 * @throws myException
	 */
	public Reclist21_AH7_SteuerTabelle _fillWithFuhre(RECORD_VPOS_TPA_FUHRE recFuhre) throws myException {
		if (recFuhre!=null) {
			RECLIST_VPOS_TPA_FUHRE_ORT   rlFoEK = 	recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
													new And(new vgl_YN(VPOS_TPA_FUHRE_ORT.deleted, false)).and(new vgl(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"EK")).s(), null, true);
			
			RECLIST_VPOS_TPA_FUHRE_ORT   rlFoVK = 	recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
													new And(new vgl_YN(VPOS_TPA_FUHRE_ORT.deleted, false)).and(new vgl(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"VK")).s(), null, true);
			
			Vector<String>  vIDAdresseStart  = new Vector<>();
			Vector<String>  vIDAdresseZiel   = new Vector<>();
			
			vIDAdresseStart.add(recFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-1"));
			vIDAdresseStart.addAll(rlFoEK.get_ID_ADRESSE_LAGER_hmString_UnFormated("-1").values());
			
			vIDAdresseZiel.add(recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("-1"));
			vIDAdresseZiel.addAll(rlFoVK.get_ID_ADRESSE_LAGER_hmString_UnFormated("-1").values());
			
			
			SEL sel = new SEL("*").FROM(_TAB.ah7_steuerdatei).WHERE(new In(AH7_STEUERDATEI.id_adresse_geo_start, vIDAdresseStart))
															 .AND(new In(AH7_STEUERDATEI.id_adresse_geo_ziel, vIDAdresseZiel));
			
			this._fill(sel.s());
		}
		return this;
	}
	
	
}
