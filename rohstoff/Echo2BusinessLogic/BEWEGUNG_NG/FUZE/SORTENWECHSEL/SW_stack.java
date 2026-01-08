package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.SORTENWECHSEL;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public class SW_stack extends Vector<SW_position> {

	private BigDecimal  bd_summe = new BigDecimal(0);
	
	/**
	 * 
	 */
	public SW_stack() {
		super();
	}

	
	public SW_stack _add(SW_position pos) throws myException {
		if (!pos.is_checked()) {
			throw new myException(this,"SW_position MUST be checked !");
		}
		if (!pos.mv().get_bIsOK()) {
			throw new myException(this,"SW_position contains errors!");
		}

		this.bd_summe.add(pos.get_bd_menge_formated().get_bdWert());
		
		this.add(pos);
		
		return this;
	}
	
	
	/**
	 * 
	 * @param posnummer_start
	 * @param id_adresse_lager_uf
	 * @param id_adresse_besitzer_uf
	 * @param id_bewegung_vektor_uf
	 * @param e_preis_formated
	 * @return
	 * @throws myException
	 */
	public Vector<String> generate_sql_save_stack(	int 		posnummer_start 
													,String 	id_adresse_lager_uf 
													,String 	id_adresse_besitzer_uf
													,String     id_bewegung_vektor_uf
													,String     e_preis_formated
													) throws myException {
		Vector<String> v_sql = new Vector<>();
		
		for (SW_position p: this) {
			v_sql.addAll(p.generate_sql_save_stack(""+(posnummer_start++), id_adresse_lager_uf, id_adresse_besitzer_uf, id_bewegung_vektor_uf, e_preis_formated));
		}
		
		return v_sql;
	}
	
	
	public boolean check_umbuchungs_menge_gegen_original_menge(BigDecimal bd_orig) {
		if (this.bd_summe.compareTo(bd_orig)>=0) {
			return false;
		}
		return true;
	}
	
	
}
