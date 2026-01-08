/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung
 * @author martin
 * @date 27.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 27.09.2019
 *
 */
public class Rec21_DlpProfil extends Rec21 {

	/**
	 * @author martin
	 * @date 27.09.2019
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_DlpProfil() throws myException {
		super(_TAB.dlp_profil);
	}

	/**
	 * @author martin
	 * @date 27.09.2019
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_DlpProfil(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	@Override
	public Rec21_DlpProfil _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_DlpProfil _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	public Rec21_adresse getMatchingQuelle() throws myException {
		return new Rec21_adresse(this.get_up_Rec21(DLP_PROFIL.id_adresse_start,ADRESSE.id_adresse,true));
	}
	
	public Rec21_adresse getMatchingZiel() throws myException {
		return new Rec21_adresse(this.get_up_Rec21(DLP_PROFIL.id_adresse_ziel,ADRESSE.id_adresse,true));
	}

	
	public Rec21_artikel getMatchingSorte() throws myException {
		return new Rec21_artikel(this.get_up_Rec21(DLP_PROFIL.id_artikel,ARTIKEL.id_artikel,true));
	}

	
	
}
