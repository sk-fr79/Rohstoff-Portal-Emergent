package panter.gmbh.basics4project.SANKTION;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class SANKTION_Treffer {

	private ENUM_SANKTION_Ergebnis_typ treffer_typ;

	private String treffer_worter = new String();

	private String sanktion_treffer_worter = new String();


	public ENUM_SANKTION_Ergebnis_typ getTreffer_typ() {
		return this.treffer_typ;
	}

	public String getTreffer_worter() {
		return this.treffer_worter;
	}

	public String getSanktion_treffer_worter() {
		return sanktion_treffer_worter;
	}


	public SANKTION_Treffer _add_treffer_typ(ENUM_SANKTION_Ergebnis_typ e_treffer_typ) {
		this.treffer_typ = e_treffer_typ;
		return this;
	}

	public SANKTION_Treffer _add_treffer_worter(VEK<String> p_treffer_worter) {
		StringBuffer buff = new StringBuffer();
		
		List<Object> tmp = Arrays.stream(p_treffer_worter.toArray()).distinct().collect(Collectors.toList());

		for( Object treff_wort : tmp) {
			if(S.isFull((String)treff_wort)) {
				buff.append(",").append((String)treff_wort);
			}
		}
		buff.deleteCharAt(0);
		this.treffer_worter = buff.toString();
		return this;
	}

	public SANKTION_Treffer _add_sanktion_treffer_worter(VEK<String> p_sanktion_treffer_worter) {
		StringBuffer buff = new StringBuffer();
		for(String treff_wort : p_sanktion_treffer_worter) {
			if(S.isFull(treff_wort)) {
				buff.append(",").append(treff_wort);
			}
		}
		buff.deleteCharAt(0);
		this.sanktion_treffer_worter = buff.toString();
		return this;
	}

	public boolean isOk() throws myException{
		return (treffer_typ == ENUM_SANKTION_Ergebnis_typ.OK)?true:false;
	}

	/**
	 * @return string for the sha256 with value of sanktion database
	 * @throws myException
	 */
	public String get_sdb_erg_4_hashwert() throws myException{
		StringBuffer buff = new StringBuffer();
		for(String wort : sanktion_treffer_worter.split(",")) {
			buff.append(wort);
		}
		return buff.toString();
	}



}
