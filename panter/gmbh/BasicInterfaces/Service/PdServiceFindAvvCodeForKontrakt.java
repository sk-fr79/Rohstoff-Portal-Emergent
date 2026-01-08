/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 11.01.2021
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 11.01.2021
 * simuliert die AVV-Code-Findung auf dem Kontrakt-Druck, um diese in der Bildschirmliste anzuzeigen
 */
public class PdServiceFindAvvCodeForKontrakt {

	public enum EnumEkVkKontrakt implements IF_enum_4_db_specified<EnumEkVkKontrakt>{
		VORGANGSART_EK_KONTRAKT(myCONST.VORGANGSART_EK_KONTRAKT)
		,VORGANGSART_VK_KONTRAKT(myCONST.VORGANGSART_VK_KONTRAKT)
		;
		private String dbVal = null;

		private EnumEkVkKontrakt(String dbVal) {
			this.dbVal = dbVal;
		}

		@Override
		public String db_val() {
			return this.dbVal;
		}

		@Override
		public String user_text() {
			return this.dbVal;
		}

		@Override
		public EnumEkVkKontrakt[] get_Values() {
			return EnumEkVkKontrakt.values();
		}
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 11.01.2021
	 *
	 * @param en
	 * @param idAdresse
	 * @param idArtikelBez
	 * @return s AVV-code from ARTBEZ_LIEF / ARTIKEL or null
	 */
	public Rec22 getAvvCodeEvaluated(EnumEkVkKontrakt en, Long idAdresse, Long idArtikelBez) throws Exception {
		
		Rec22 recAvv = 		null;
		
		if (O.isNoOneNull(en,idAdresse,idArtikelBez) ) {
			String sEKVK= (en==EnumEkVkKontrakt.VORGANGSART_EK_KONTRAKT?"EK":"VK");
			SEL sel = new SEL(ARTIKELBEZ_LIEF.id_eak_code).FROM(_TAB.artikelbez_lief)
					.WHERE(new vgl(ARTIKELBEZ_LIEF.artbez_typ, sEKVK))
					.AND(new vgl(ARTIKELBEZ_LIEF.id_adresse,idAdresse.toString()))
					.AND(new vgl(ARTIKELBEZ_LIEF.id_artikel_bez,idArtikelBez.toString()))
					.AND(new VglNotNull(ARTIKELBEZ_LIEF.id_eak_code));
			
			RecList22 rl = new RecList22(_TAB.artikelbez_lief)._fill(sel);
							
			if (rl.size()==1) {
				recAvv = rl.get(0);
			} else {
				//dann aus dem artikelstamm
				Rec22 artikel = new Rec22(_TAB.artikel_bez)._fill_id(idArtikelBez).getUpRec2(ARTIKEL.id_artikel);
				if (en==EnumEkVkKontrakt.VORGANGSART_EK_KONTRAKT) {
					recAvv = artikel.get_up_Rec20(ARTIKEL.id_eak_code, EAK_CODE.id_eak_code, true);
				} else {
					recAvv = artikel.get_up_Rec20(ARTIKEL.id_eak_code_ex_mandant, EAK_CODE.id_eak_code, true);
				}
			}
		}
		return recAvv;
		
	}
	
	
}
