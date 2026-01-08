 
package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum REP_VER_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,REPORT_LOG("REPORT_LOG-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,REPORT_LOG_EDIT( 	ENUM_VALID_THEME.REPORT_LOG, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,REPORT_LOG_VIEW( 	ENUM_VALID_THEME.REPORT_LOG, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,REPORT_LOG_NEW( 		ENUM_VALID_THEME.REPORT_LOG, 	ENUM_VALID_BASICFUNCTION.NEW)
		,REPORT_LOG_DELETE( 	ENUM_VALID_THEME.REPORT_LOG, 	ENUM_VALID_BASICFUNCTION.DELETE)
    * 
    */
	
	VIEW(ENUM_VALIDATION.REPORT_LOG_VIEW)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private REP_VER_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
