/**
 * panter.gmbh.Echo2.RB.COMP.TextListe
 * @author martin
 * @date 20.02.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe;

import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.indep.dataTools.IF_Field;

/**
 * @author martin
 * @date 20.02.2020
 *
 */
public enum TL_enumFieldWithSavedStatus {
	fontsize_titel_text(TEXT_LISTE.fontsize_titel_text,"10")
	,fontsize_aufzaehl_text(TEXT_LISTE.fontsize_aufzaehl_text,"10")
	,fontsize_lang_text(TEXT_LISTE.fontsize_lang_text,"10")

	,bold_titel_text(TEXT_LISTE.bold_titel_text,"N")
	,bold_aufzaehl_text(TEXT_LISTE.bold_aufzaehl_text,"N")
	,bold_lang_text(TEXT_LISTE.bold_lang_text,"N")
	
	,italic_titel_text(TEXT_LISTE.italic_titel_text,"N")
	,italic_aufzaehl_text(TEXT_LISTE.italic_aufzaehl_text,"N")
	,italic_lang_text(TEXT_LISTE.italic_lang_text,"N")

	,underline_titel_text(TEXT_LISTE.underline_titel_text,"N")
	,underline_aufzaehl_text(TEXT_LISTE.underline_aufzaehl_text,"N")
	,underline_lang_text(TEXT_LISTE.underline_lang_text,"N")

	
	;
	private IF_Field f;
	private String defaultVal=null;;
		
	
	private TL_enumFieldWithSavedStatus(IF_Field p_f, String p_defaultVal) {
		f=p_f;
		defaultVal = p_defaultVal;
	}


	public IF_Field getF() {
		return f;
	}


	public String getDefaultVal() {
		return defaultVal;
	}
	
	
	
}
