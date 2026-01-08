/**
 * panter.gmbh.Echo2.basic_tools.emailv2
 * @author martin
 * @date 17.02.2021
 * 
 */
package panter.gmbh.basics4project;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_Enum4db;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 17.02.2021
 *
 */
public enum ENUM_EmailType implements IF_Enum4db<ENUM_EmailType>{
	
	MAILVERSAND_WAAGE_STORNO("Stornoversand einer Wiegekarte"),
	MAILVERSAND_WAAGE_UVV_INVALID("UVV-Datum eines Containers abgelaufen."),
	MAILVERSAND_WAAGE_TARA_KORR("Tara-Gewicht eines Containers wurde geändert."),
	MAILVERSAND_CONTAINER_MEHRFACH_BUCHUNG("Ein Container wurde mehrfach in den Containerzyklus bei Eingangsverwiegung eingebucht.")
	
	
	;
	
	
	private String userText = null;

	private ENUM_EmailType(String userText) {
		this.userText=userText;
	}
	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return S.NN(userText,this.name());
	}

	@Override
	public ENUM_EmailType[] get_Values() {
		return ENUM_EmailType.values();
	}

	@Override
	public VEK<String> getHelpTextVek() {
		return null;
	}
	
	

}
