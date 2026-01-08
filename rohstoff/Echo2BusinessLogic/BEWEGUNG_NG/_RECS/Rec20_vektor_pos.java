package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS;

import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;

public class Rec20_vektor_pos extends Rec20 {
	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_vektor_pos(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	
	
	/**
	 * @throws myException
	 */
	public Rec20_vektor_pos() throws myException {
		super(_TAB.bewegung_vektor_pos);
	}

	
	
	
	public Vector<Rec20_atom>  __get_all_rec20_atom() throws myException {
		Vector<Rec20_atom> v_rueck = new Vector<>();
		
		RecList20 rl_atom = this.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, new vgl_YN(BEWEGUNG_ATOM.deleted,false).s(), null);
			
		//jetzt die atome, die vom physikalischen in das umbuchungslager gehen
		for (Rec20 r_atom: rl_atom) {
			v_rueck.add(new Rec20_atom(r_atom));
		}
		return v_rueck;
	}
	
	
//
//	/**
//	 * prueft den formal korrekten aufbau eines bewegungsvektors mit einem bestimmten pos-typ 
//	 * @return
//	 * @throws myException 
//	 */
//	public Rec20_atom get_LL_START_ATOM() throws myException {
//		RecList20 rl_atom = this.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, null); 
//		if (rl_atom.size()==1) {
//			if (this.get_ufs_dbVal(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.LL_MAIN_LEFT.db_val())) {
//				return new Rec20_atom(rl_atom.get(0));
//			} else {
//				throw new myException(this,"Error: Vektorpos is the false type for LL-left");
//			}
//		} else {
//			throw new myException(this,"Error: LL_START-Vektorpos can only have one ATOM");
//		}
//	}

//	/**
//	 * prueft den formal korrekten aufbau eines bewegungsvektors mit einem bestimmten pos-typ 
//	 * @return
//	 * @throws myException 
//	 */
//	public Rec20_atom get_LL_ZIEL_ATOM() throws myException {
//		RecList20 rl_atom = this.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, null); 
//		if (rl_atom.size()==1) {
//			if (this.get_ufs_dbVal(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.LL_MAIN_RIGHT.db_val())) {
//				return new Rec20_atom(rl_atom.get(0));
//			} else {
//				throw new myException(this,"Error: Vektorpos is the false type for LL-left");
//			}
//		} else {
//			throw new myException(this,"Error: LL_ZIEL-Vektorpos can only have one ATOM");
//		}
//	}
//

	/**
	 * prueft den formal korrekten aufbau eines bewegungsvektors mit einem bestimmten pos-typ 
	 * @return
	 * @throws myException 
	 */
	public Rec20_atom get_ST_START_ATOM() throws myException {
		RecList20 rl_atom = this.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, null); 
		if (rl_atom.size()==1) {
			if (this.get_ufs_dbVal(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT.db_val())) {
				return new Rec20_atom(rl_atom.get(0));
			} else {
				throw new myException(this,"Error: Vektorpos is the false type for ST-left");
			}
		} else {
			throw new myException(this,"Error: ST_START-Vektorpos can only have one ATOM");
		}
	}

	/**
	 * prueft den formal korrekten aufbau eines bewegungsvektors mit einem bestimmten pos-typ 
	 * @return
	 * @throws myException 
	 */
	public Rec20_atom get_ST_ZIEL_ATOM() throws myException {
		RecList20 rl_atom = this.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, null); 
		if (rl_atom.size()==1) {
			if (this.get_ufs_dbVal(BEWEGUNG_VEKTOR_POS.pos_typ).equals(ENUM_VEKTORPOS_TYP.ST_MAIN_RIGHT.db_val())) {
				return new Rec20_atom(rl_atom.get(0));
			} else {
				throw new myException(this,"Error: Vektorpos is the false type for ST-left");
			}
		} else {
			throw new myException(this,"Error: ST_ZIEL-Vektorpos can only have one ATOM");
		}
	}


	
	

}
