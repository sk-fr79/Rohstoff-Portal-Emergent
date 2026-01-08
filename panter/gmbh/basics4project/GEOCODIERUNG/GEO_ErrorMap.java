package panter.gmbh.basics4project.GEOCODIERUNG;

import java.util.HashMap;
import java.util.Map;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class GEO_ErrorMap extends HashMap<ENUM_GEO_Error, VEK<String>> {
	
	private String beschreibung = null;
	
	public String getBeschreibung() {
		return beschreibung;
	}


	public GEO_ErrorMap(String p_beschreibung) {
		super();
		
		this.beschreibung = p_beschreibung;
		
		for (ENUM_GEO_Error en: ENUM_GEO_Error.values()) {
			this.put(en, 	new VEK<String>());
		}
		
//		this.put(ENUM_GEO_Error.ERROR_RECORD, 	new VEK<String>());
//		this.put(ENUM_GEO_Error.ERROR_PARSE, 	new VEK<String>());
//		this.put(ENUM_GEO_Error.ERROR_URL, 		new VEK<String>());
//		this.put(ENUM_GEO_Error.NO_COORDINATE, 	new VEK<String>());
//		this.put(ENUM_GEO_Error.OK, 			new VEK<String>());
	}

	
	public GEO_ErrorMap _add(ENUM_GEO_Error typ, String id)  throws myException {
		if (!bibALL.isLong(id)) {
			throw new myException("Error: Adress-ID is not a number !");
		}

		//jede id nur einmal aufnehmen
		if (!this.containsErrorForId(id)) {
			this.get(typ)._a(id);
		}
		
		return this;
	}
	
	

	
	/**
	 * sucht die statusmeldung der id heraus
	 * @param Id
	 * @return
	 */
	public ENUM_GEO_Error getStatusMeldung(String id) {
		ENUM_GEO_Error status = ENUM_GEO_Error.UNDEFINED_ERROR;
		
		for ( Map.Entry<ENUM_GEO_Error, VEK<String>> ent : this.entrySet()) {
			if (ent.getValue().contains(id)) {
				return ent.getKey();
			}
		}
		
		return status;
	}
	
	
//	public GEO_ErrorMap  _a_record_error(String error_message) {
//		this.get(ENUM_GEO_Error.ERROR_RECORD)._a(error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap  _a_record_error(VEK<String> v_error_message) {
//		this.get(ENUM_GEO_Error.ERROR_RECORD)._a(v_error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap  _a_parse_error(String error_message) {
//		this.get(ENUM_GEO_Error.ERROR_PARSE)._a(error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap _a_parse_error(VEK<String> v_error_message) {
//		this.get(ENUM_GEO_Error.ERROR_PARSE)._a(v_error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap _a_url_error(String error_message) {
//		this.get(ENUM_GEO_Error.ERROR_URL)._a(error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap _a_url_error(VEK<String> v_error_message) {
//		this.get(ENUM_GEO_Error.ERROR_URL)._a(v_error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap _a_no_coordinate(String error_message) {
//		this.get(ENUM_GEO_Error.NO_COORDINATE)._a(error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap _a_no_coordinate(VEK<String> v_error_message) {
//		this.get(ENUM_GEO_Error.NO_COORDINATE)._a(v_error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap _a_no_error(String no_error_message) {
//		this.get(ENUM_GEO_Error.OK)._a(no_error_message);
//		return this;
//	}
	
//	public GEO_ErrorMap _a_no_error(VEK<String> v_no_error_message) {
//		this.get(ENUM_GEO_Error.OK)._a(v_no_error_message);
//		return this;
//	}
//	
	
//	public VEK<String> _get_record_error(){
//		return this.get(ENUM_GEO_Error.ERROR_RECORD);
//	}
	
//	public VEK<String> _get_url_error(){
//		return this.get(ENUM_GEO_Error.ERROR_URL);
//	}
	
//	public VEK<String> _get_parse_error(){
//		return this.get(ENUM_GEO_Error.ERROR_PARSE);
//	}
	
//	public VEK<String> _get_no_coordinate(){
//		return this.get(ENUM_GEO_Error.NO_COORDINATE);
//	}
	
//	public VEK<String> _get_ok_coordinate(){
//		return this.get(ENUM_GEO_Error.OK);
//	}
	
	public boolean has_no_error() {
//		boolean no_error = false;
//		no_error = ! (this.get(ENUM_GEO_Error.ERROR_RECORD).size()>0);
//		no_error = ! (this._get_url_error().size()>0);
//		no_error = ! (this._get_parse_error().size()>0);
//		no_error = ! (this._get_no_coordinate().size()>0);
//		
//		return no_error;
		
		return !this.isContainingError();
	}
	
	
	public boolean isContainingError() {
		boolean bHasAtLeastOneError = false;
		
		for (ENUM_GEO_Error en: ENUM_GEO_Error.values()) {
			if (en.isErrorStatus()) {
				if (this.get(en).size()>0) {
					bHasAtLeastOneError = true;
					break;
				}
			}
		}
		return bHasAtLeastOneError;
	}

	
	
	
	
	public String resume() {
		String rueck = "";
		
		for (ENUM_GEO_Error en: ENUM_GEO_Error.values()) {
			if (en.isErrorStatus()) {
				rueck += en.user_text()+":"+this.get(en).size();
			}
		}

		
		
//		rueck = rueck + "Error in Record: " + this._get_record_error().size()   + "\n"; 
//		rueck = rueck + "Error in URL: " 	+ this._get_url_error().size()		+ "\n";
//		rueck = rueck + "Error in Parse: " 	+ this._get_parse_error().size()	+ "\n";
//		rueck = rueck + "Keine Koordinate:" + this._get_no_coordinate().size()	+ "\n";
//		rueck = rueck + "No error (OK): " 	+ this._get_ok_coordinate().size();
		
		return rueck;
	}
	
	
	/**
	 * 2018-02-02: martin
	 * @param map2add
	 * @return
	 */
	public GEO_ErrorMap _addAll(GEO_ErrorMap map2add) {
		for (ENUM_GEO_Error er: ENUM_GEO_Error.values()) {
			this.get(er).addAll(map2add.get(er));
		}
		
		return this;
	}
	

	/**
	 * 
	 * @param id
	 * @return true, when id is registered as an error in this map
	 */
	public boolean containsErrorForId(String id) {
		for (ENUM_GEO_Error en: ENUM_GEO_Error.values()) {
			if (en.isErrorStatus()) {
				if (this.get(en).contains(id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return true, when id is registered as an error in this map
	 */
	public boolean containsErrorForId(Long id) {
		return this.containsErrorForId(id.toString());
	}

	

}
