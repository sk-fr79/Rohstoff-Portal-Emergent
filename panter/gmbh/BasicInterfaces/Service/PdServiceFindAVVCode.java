/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 18.03.2019
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 18.03.2019
 *
 */
public class PdServiceFindAVVCode {
	

	public Rec21 getAVVCode(Rec21 adresse, Rec21 artikelBez, String artbezTypEkOrVK) throws myException {
		Rec21 ret = null;
		
		if (O.isNoOneNull(adresse,artikelBez, artbezTypEkOrVK)) {
			Rec21_adresse ra = new Rec21_adresse(adresse)._getMainAdresse();   
			
			And bed = new And(	new vgl(ARTIKELBEZ_LIEF.artbez_typ,artbezTypEkOrVK))
									.and(new vgl(ARTIKELBEZ_LIEF.id_artikel_bez,artikelBez.getUfs(ARTIKEL_BEZ.id_artikel_bez)))
									.and(new VglNotNull(ARTIKELBEZ_LIEF.id_eak_code))
									;
			
			RecList21 rl = ra.get_down_reclist21(ARTIKELBEZ_LIEF.id_adresse, bed.s(), null);
			
			if (rl.size()==1) {
				return rl.get(0).get_up_Rec21(EAK_CODE.id_eak_code);
			}
		}
		
		return ret;
	}
	
}
