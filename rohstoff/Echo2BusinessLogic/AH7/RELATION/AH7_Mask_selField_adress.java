/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 *
 */
public class AH7_Mask_selField_adress extends RB_selField {

	public AH7_Mask_selField_adress() {
		super();
	}

	
	
	/**
	 * die komponente fuellen mit den 6 moeglichen adressen ausgehend von den 2 endpunkten des transports (incl. lager-drittbesitzer)
	 * @param id_adresse_start_geo (unformated)
	 * @param id_adresse_ziel_geo (informated)
	 * @return
	 * @throws myException
	 */
	public AH7_Mask_selField_adress _populateWith6Adresses(String id_adresse_start_geo, String id_adresse_ziel_geo) throws myException {

		this._clear();
		this._putEmptyValInFront();
		
		if (S.isAllFull(id_adresse_start_geo, id_adresse_ziel_geo)) {
			MyLong lstart = new MyLong(id_adresse_start_geo);
			MyLong lziel = new MyLong(id_adresse_ziel_geo);
			
			if (lstart.isOK() && lziel.isOK()) {
				Rec21_adresse  ra_start = new Rec21_adresse()._fill_id(lstart.get_lValue());
				Rec21_adresse  ra_start_jur = ra_start.__get_main_adresse();
				Rec21_adresse  ra_start_lager_besitz = ra_start.getRec21LagerDrittBesitzer();
				
				Rec21_adresse  ra_ziel = new Rec21_adresse()._fill_id(lziel.get_lValue());
				Rec21_adresse  ra_ziel_jur = ra_ziel.__get_main_adresse();
				Rec21_adresse  ra_ziel_lager_besitz = ra_ziel.getRec21LagerDrittBesitzer();
				
				Rec21_adresse  ra_mandant = new Rec21_adresse(new Rec21(_TAB.adresse)._fill_id(bibALL.get_ID_ADRESS_MANDANT()));
			
				this._addPair(new PairS(ra_start.get__FullNameAndAdress_flexible(", ")+" ... "+(S.ms("(Quelle geografisch)").CTrans())
										,ra_start.get_fs_dbVal(ADRESSE.id_adresse)));
				this._addPair(new PairS(ra_start_jur.get__FullNameAndAdress_flexible(", ")+" ... "+(S.ms("(Quellfirma juristisch)").CTrans())
										,ra_start_jur.get_fs_dbVal(ADRESSE.id_adresse)));
				
				if (ra_start_lager_besitz!=null) {
					this._addPair(new PairS(ra_start_lager_besitz.get__FullNameAndAdress_flexible(", ")+" ... "+(S.ms("(Drittbesitzer des Lagers)").CTrans())
							,ra_start_lager_besitz.get_fs_dbVal(ADRESSE.id_adresse)));
				}
				
				this._addPair(new PairS(ra_ziel.get__FullNameAndAdress_flexible(", ")+" ... "+(S.ms("(Ziel geografisch)").CTrans())
										,ra_ziel.get_fs_dbVal(ADRESSE.id_adresse)));
				this._addPair(new PairS(ra_ziel_jur.get__FullNameAndAdress_flexible(", ")+" ... "+(S.ms("(Zielfirma juristisch)").CTrans())
										,ra_ziel_jur.get_fs_dbVal(ADRESSE.id_adresse)));
	
				if (ra_ziel_lager_besitz!=null) {
					this._addPair(new PairS(ra_ziel_lager_besitz.get__FullNameAndAdress_flexible(", ")+" ... "+(S.ms("(Drittbesitzer des Lagers)").CTrans())
							,ra_ziel_lager_besitz.get_fs_dbVal(ADRESSE.id_adresse)));
				}

				this._addPair(new PairS(ra_mandant.get__FullNameAndAdress_flexible(", ")+" ... "+(S.ms("(***Mandant***)").CTrans())
										,ra_mandant.get_fs_dbVal(ADRESSE.id_adresse)));
				
			}
		}
		this._render();
		return this;
	}
	
	
}
