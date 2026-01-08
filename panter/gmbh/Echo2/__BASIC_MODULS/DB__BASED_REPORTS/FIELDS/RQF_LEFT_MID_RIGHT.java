/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS
 * @author martin
 * @date 06.11.2018
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;

import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

public enum RQF_LEFT_MID_RIGHT implements IF_enum_4_db_specified<RQF_LEFT_MID_RIGHT> {
	LEFT("Linksbündig")
	,MID("Zentriert")
	,RIGHT("Rechtsbündig")
	;

	
	private String m_userText = ""; 
	
	private RQF_LEFT_MID_RIGHT(String userText) {
		this.m_userText = userText;
	}

	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return this.m_userText;
	}

	@Override
	public RQF_LEFT_MID_RIGHT[] get_Values() {
		return RQF_LEFT_MID_RIGHT.values();
	}
	
	public Alignment getAlignment() {
		Alignment al = null;
		
		switch (this) {
		case LEFT:
			al = new Alignment(Alignment.LEFT, Alignment.CENTER);
			break;

		case MID:
			al = new Alignment(Alignment.CENTER, Alignment.CENTER);
			break;

		case RIGHT:
			al = new Alignment(Alignment.RIGHT, Alignment.CENTER);
			break;
		}
		
		return al;
	}
	
}