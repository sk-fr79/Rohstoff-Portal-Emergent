package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;


public class RECORD_BEWEGUNG_VEKTOR_POS_ext extends RECORD_BEWEGUNG_VEKTOR_POS {

	public RECORD_BEWEGUNG_VEKTOR_POS_ext(RECORD_BEWEGUNG_VEKTOR_POS recordOrig) {
		super(recordOrig);
	}

	
	
	
	/**
	 * prueft den formal korrekten aufbau eines bewegungsvektors mit einem bestimmten pos-typ 
	 * @return
	 * @throws myException 
	 */
	public boolean is_correct_WE_HIDDEN_SEP() throws myException {
		boolean b_rueck = false;
		
		
		RECLIST_BEWEGUNG_ATOM rla = this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos();
		
		if (this.ufs(BEWEGUNG_VEKTOR_POS.pos_typ,"").equals(ENUM_VEKTORPOS_TYP.WE_HIDDEN_SEP.db_val())) {
			// wenn der vektor_pos -datensatz vom typ WE_HIDDEN_SEP  ist, dann muss die ausgangsstation von einem eigenlager zu einem Sortenwechsel-lager
			// fuehren, der zweite retour
			if (rla.size()==2) {
				Vector<RECORD_BEWEGUNG_ATOM> rl_sort = rla.GET_SORTED_VECTOR(bibVECTOR.get_Vector(BEWEGUNG_ATOM.id_bewegung_atom.fn()), true);
				if (new RECORD_BEWEGUNG_ATOM_ext(rl_sort.get(0)).is_atom_eigenlager_nach_sonderlager(ENUM_SONDERLAGER.SW) &&
					new RECORD_BEWEGUNG_ATOM_ext(rl_sort.get(1)).is_atom_sonderlager_nach_eigenlager(ENUM_SONDERLAGER.SW)) {				
					b_rueck = true;
				}
			}
		}
		return b_rueck; 
	}
	
	
	
	
	public RECORD_BEWEGUNG_ATOM  get_atom_lager_to_sw_lager() throws myException {
		
		if (this.is_correct_WE_HIDDEN_SEP()) {
			RECLIST_BEWEGUNG_ATOM rla = this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos();

			Vector<RECORD_BEWEGUNG_ATOM> rl_sort = rla.GET_SORTED_VECTOR(bibVECTOR.get_Vector(BEWEGUNG_ATOM.id_bewegung_atom.fn()), true);
			
			return rl_sort.get(0);
		}
		
		throw new myException(this,"this is not an WE_HIDDEN_SEP-VEKTOR_POS!");
	}
	
	
	public RECORD_BEWEGUNG_ATOM  get_atom_sw_lager_to_lager() throws myException {
		
		if (this.is_correct_WE_HIDDEN_SEP()) {
			RECLIST_BEWEGUNG_ATOM rla = this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos();

			Vector<RECORD_BEWEGUNG_ATOM> rl_sort = rla.GET_SORTED_VECTOR(bibVECTOR.get_Vector(BEWEGUNG_ATOM.id_bewegung_atom.fn()), true);
			
			return rl_sort.get(1);
		}
		
		throw new myException(this,"this is not an WE_HIDDEN_SEP-VEKTOR_POS!");
	}
	
	
	
	
	/**
	 * 
	 * @return kompletter sql-stack von station zu atom zu vektor um eine komplette vektor-positions mit atomen zu loeschen
	 * @throws myException
	 */
	public Vector<String>  sql_to_delete_vektor_pos_and_atom() throws myException {
		Vector<String> v_sql = new Vector<>();
		
		v_sql.add(this.get_DELETE_STATEMENT());
		
		for (RECORD_BEWEGUNG_ATOM at: this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos()) {
			v_sql.add(at.get_DELETE_STATEMENT());
			for (RECORD_BEWEGUNG_STATION st: at.get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_bewegung_atom()) {
				v_sql.add(st.get_DELETE_STATEMENT());
			}
		}
		
		DEBUG.System_print(bibVECTOR.sort_reverse(v_sql), true);
		
		
		return bibVECTOR.sort_reverse(v_sql);
	}
	
	
	/**
	 * prueft den formal korrekten aufbau eines bewegungsvektors mit einem bestimmten pos-typ 
	 * @return
	 * @throws myException 
	 */
	public RECORD_BEWEGUNG_ATOM_ext get_LL_START_ATOM() throws myException {
		
		RECLIST_BEWEGUNG_ATOM rl_atom = this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos(); 
		
		if (rl_atom.size()==1) {
		
			if (this.ufs(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.LL_MAIN.db_val())) {
				return new RECORD_BEWEGUNG_ATOM_ext(rl_atom.get(0));
			} else {
				throw new myException(this,"Error: Vektorpos is the false type for LL-left");
			}
		} else {
			throw new myException(this,"Error: LL_START-Vektorpos can only have one ATOM");
		}
	}

	/**
	 * prueft den formal korrekten aufbau eines bewegungsvektors mit einem bestimmten pos-typ 
	 * @return
	 * @throws myException 
	 */
	public RECORD_BEWEGUNG_ATOM_ext get_LL_ZIEL_ATOM() throws myException {
		RECLIST_BEWEGUNG_ATOM rl_atom = this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos(); 
		
		if (rl_atom.size()==1) {
		
			if (this.ufs(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.LL_MAIN.db_val())) {
				return new RECORD_BEWEGUNG_ATOM_ext(rl_atom.get(0));
			} else {
				throw new myException(this,"Error: Vektorpos is the false type for LL-right");
			}
		} else {
			throw new myException(this,"Error: LL_ZIEL-Vektorpos can only have one ATOM");
		}
	}


	
	/**
	 * prueft den formal korrekten aufbau eines bewegungsvektors mit einem bestimmten pos-typ 
	 * @return
	 * @throws myException 
	 */
	public RECORD_BEWEGUNG_ATOM_ext get_LG_START_ATOM() throws myException {
		
		RECLIST_BEWEGUNG_ATOM rl_atom = this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos(); 
		
		if (rl_atom.size()==1) {
		
			if (this.ufs(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.LG_MAIN.db_val())) {
				return new RECORD_BEWEGUNG_ATOM_ext(rl_atom.get(0));
			} else {
				throw new myException(this,"Error: Vektorpos is the false type for LL-left");
			}
		} else {
			throw new myException(this,"Error: LL_START-Vektorpos can only have one ATOM");
		}
	}

	
	
}
