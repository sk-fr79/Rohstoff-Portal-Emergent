/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 15.03.2019
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import static panter.gmbh.basics4project.DB_ENUMS._TAB.adresse;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN.Check_RC_Status_Sorte;

/**
 * @author martin
 * @date 15.03.2019
 *
 */
public class PdServiceCheckIfSorteIsRC {

	/**
	 * 
	 * @param cID_ARTIKEL_BEZ
	 * @param cID_ADRESSE_LAGER
	 * @return
	 * @throws myException
	 */
	public boolean isRC(Long idArtBez, Long idAdresseGeo) throws myException {
		
		boolean bSteuerViaHandelsdef = 		__RECORD_MANDANT_ZUSATZ.IS__Value("STEUERERMITTLUNG_MIT_HANDELSDEF", "N", "N");

		boolean ret=false ;
		if (O.isNoOneNull(idArtBez,idAdresseGeo)) {
			Rec21 ad = new Rec21(_TAB.adresse)._fill_id(idAdresseGeo);
			
			Rec21 art = new Rec21(_TAB.artikel_bez)._fill_id(idArtBez).get_up_Rec21(ARTIKEL.id_artikel);
			
			if (!bSteuerViaHandelsdef) {
				Rec21 recZollTarif = art.get_up_Rec21(ARTIKEL.id_artikel).get_up_Rec21(ZOLLTARIFNUMMER.id_zolltarifnummer);
				if (recZollTarif!=null && recZollTarif.is_ExistingRecord()) {
					ret = recZollTarif.is_yes_db_val(ZOLLTARIFNUMMER.reverse_charge);
				}
			} else {
				if (ad.getLongDbValue(ADRESSE.id_land)!=null) {
				
					SEL sel = new SEL("COUNT(*)").FROM(_TAB.land_rc_sorten).WHERE(new vglParam(LAND_RC_SORTEN.id_land)).AND(new vglParam(LAND_RC_SORTEN.id_artikel));
					
					
					SqlStringExtended sql = new SqlStringExtended(sel.s())._addParameters(new VEK<ParamDataObject>()
																		._a(new Param_Long("",ad.getLongDbValue(ADRESSE.id_land)))
																		._a(new Param_Long("",art.getIdLong()))
																		); 
					//DEBUG._print("land: "+ad.getLongDbValue(ADRESSE.id_land).toString()+"  Artikel: "+art.getIdLong());
					
					
					String[][] erg = bibDB.EinzelAbfrageInArray(sql);
					if (erg.length>0 && !erg[0][0].equals("0")) {
						ret = true;
					}
				}
			}
		}
		
		return ret;
	}

	
}
