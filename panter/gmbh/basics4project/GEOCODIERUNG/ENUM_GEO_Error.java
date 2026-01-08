package panter.gmbh.basics4project.GEOCODIERUNG;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

public enum ENUM_GEO_Error implements IF_enum_4_db_specified<ENUM_GEO_Error>{
	OK("Fehlerfrei kodiert", false)
	,OK_ALREADY_EXISTING("Fehlerfrei kodiert (bereits vorhanden)",false)
	,ERROR_URL("URL-Fehler/Rückgabe nicht interpretierbar", true)
	,ERROR_RECORD("Fehlende Adress-Angabe", true)
	,NO_COORDINATE("Adresse konnte nicht gefunden werden", true)
	,NO_EXACT_LOCALISATION("Mehrere unzusammenhängende Adresse gefunden", true)
	,UNDEFINED_ERROR("Unbekannter Fehler", true)
	;

	
	
	private String userText = null;
	private boolean isErrorStatus = false;
	



	/**
	 * @param p_userText 
	 * @param p_isError 
	 * @param name
	 * @param ordinal
	 */
	private ENUM_GEO_Error(String p_userText, boolean p_isError) {
		this.userText=p_userText;
		this.isErrorStatus=p_isError;
	}


	
	
	@Override
	public String db_val() {
		return this.name();
	}
	
	
	@Override
	public String user_text() {
		return S.NN(this.userText, this.name());
	}
	
	
	@Override
	public ENUM_GEO_Error[] get_Values() {
		return ENUM_GEO_Error.values();
	}
	
	
	public boolean isErrorStatus() {
		return isErrorStatus;
	}


}
