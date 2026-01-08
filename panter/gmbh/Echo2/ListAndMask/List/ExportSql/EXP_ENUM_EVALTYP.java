/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.ExportSql;

import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 *
 */
public enum EXP_ENUM_EVALTYP implements IF_enum_4_db_specified<EXP_ENUM_EVALTYP>{
	
	FIELDVAL("Feldwert","Einfache Feldwert-Übernahme")
	,SQLQUERY("Abfrage","Abfrage (wird als Subquery eingebettet), wobei mit #NAME# Felder aus dem aktuellen Datensatz in die Abfrage eingebaut werden können")
	,KONSTANTE("Konstante","Muss so formatiert sein, dass der Wert direkt in das Statement eingefügt werden kann (z.B.: 'Meier', 1002.34, SYSDATE")
	;
	
	private String userText = null;
	private String userTextLang = null;

	
	/**
	 * @param p_userText
	 */
	private EXP_ENUM_EVALTYP(String p_userText, String p_userTextLang) {
		this.userText = p_userText;
		this.userTextLang = p_userTextLang;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#db_val()
	 */
	@Override
	public String db_val() {
		return this.name();
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#user_text()
	 */
	@Override
	public String user_text() {
		return this.userText;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db_specified#get_Values()
	 */
	@Override
	public EXP_ENUM_EVALTYP[] get_Values() {
		return EXP_ENUM_EVALTYP.values();
	}


	public String getUserTextLang() {
		return userTextLang;
	}

	
	
}
