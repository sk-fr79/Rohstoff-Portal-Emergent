package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;


public class RECORD_BEWEGUNG_VEKTOR_ext extends RECORD_BEWEGUNG_VEKTOR {

	public RECORD_BEWEGUNG_VEKTOR_ext(RECORD_BEWEGUNG_VEKTOR recordOrig) {
		super(recordOrig);
	}

	

	/**
	 * sammelt die vektor-pos-werte, die in der maske dargestellt werden
	 * @return
	 * @throws myException
	 */
	public RECLIST_BEWEGUNG_VEKTOR_POS get_mask_relevant_vektor_pos() throws myException {
		RECLIST_BEWEGUNG_VEKTOR_POS rl_pos = this.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor(null,BEWEGUNG_VEKTOR_POS.posnr.fn(),true);
		
		RECLIST_BEWEGUNG_VEKTOR_POS rl_pos_rueck = new RECLIST_BEWEGUNG_VEKTOR_POS();
		
		for (String id: rl_pos.get_vKeyValues()) {
			RECORD_BEWEGUNG_VEKTOR_POS vp = rl_pos.get(id);
			
			if (ENUM_VEKTORPOS_TYP.is_pos_typ_4_mask(vp.ufs(BEWEGUNG_VEKTOR_POS.pos_typ))) {
				rl_pos_rueck.ADD(vp, false);
			}
		}
		return rl_pos_rueck;
	}
	
	

	/**
	 * gibt den loeschstack aller automatisch zu loeschenden "zwischendatensaetze" zurueck
	 * @return
	 * @throws myException
	 */
	public Vector<String>  get_sql_deleteStatements_of_automatic_saved_datasets() throws myException {
		
		Vector<String>  v_rueck = new Vector<>();
		
		RECLIST_BEWEGUNG_VEKTOR_POS  list_pos = this.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor();
			
		for (RECORD_BEWEGUNG_VEKTOR_POS rec_pos: list_pos) {
			if (ENUM_VEKTORPOS_TYP.is_autoDelete_at_saveCycle(rec_pos)) {
				for (RECORD_BEWEGUNG_ATOM atom: rec_pos.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos()) {
					for (RECORD_BEWEGUNG_STATION  stat: atom.get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_bewegung_atom()) {
						v_rueck.add(stat.get_DELETE_STATEMENT());
					}
					v_rueck.add(atom.get_DELETE_STATEMENT());
				}
				v_rueck.add(rec_pos.get_DELETE_STATEMENT());
			}
		}
		return v_rueck;
		
	}
	

	
	
	
}
