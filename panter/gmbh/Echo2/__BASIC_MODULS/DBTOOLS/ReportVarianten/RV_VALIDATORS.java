 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum RV_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,REP_VARIANTEN_LISTE("REP_VARIANTEN_LISTE-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,REP_VARIANTEN_LISTE_EDIT( 	ENUM_VALID_THEME.REP_VARIANTEN_LISTE, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,REP_VARIANTEN_LISTE_VIEW( 	ENUM_VALID_THEME.REP_VARIANTEN_LISTE, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,REP_VARIANTEN_LISTE_NEW( 		ENUM_VALID_THEME.REP_VARIANTEN_LISTE, 	ENUM_VALID_BASICFUNCTION.NEW)
		,REP_VARIANTEN_LISTE_DELETE( 	ENUM_VALID_THEME.REP_VARIANTEN_LISTE, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,REP_VARIANTEN_LISTE_ATTACHMENT( 	ENUM_VALID_THEME.REP_VARIANTEN_LISTE, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.REP_VARIANTEN_LISTE_EDIT)
	,VIEW(ENUM_VALIDATION.REP_VARIANTEN_LISTE_VIEW)
	,NEW(ENUM_VALIDATION.REP_VARIANTEN_LISTE_NEW)
	,DELETE(ENUM_VALIDATION.REP_VARIANTEN_LISTE_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.REP_VARIANTEN_LISTE_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private RV_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
