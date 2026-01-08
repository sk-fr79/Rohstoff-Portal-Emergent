package panter.gmbh.basics4project.validation;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_VALID_BASICFUNCTION implements IF_enum_4_db{

	NEW("Neueingabe")
	,EDIT("Bearbeiten")
	,VIEW("Anzeigen")
	,DELETE("Löschen")
	,STORNO("Stornieren")
	,ATTACHMENT("Anlagen zufügen")
	,REQUERYLIST("Liste aktualisieren")
	,EXPORT_SQL("Listen als SQL-Statemtents exportieren")
	,EXPORT_XLS("Listen als Excel/CSV exportieren")
	,LIST_ITERATION("Listendurchlauf")
	,ZEIGE_ADDONS("Zusatzinfos")
	;
	
	private String userText=null;

	private ENUM_VALID_BASICFUNCTION(String userText) {
		this.userText = userText;
	}

	public String getUserText() {
		return userText;
	}

	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return userText;
	}

	@Override
	public String user_text_lang() {
		return userText;
	}


	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_VALID_BASICFUNCTION.values(), emptyPairInFront);
	}


	
	
}
