 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum WK_RB_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,WIEGEKARTE("WIEGEKARTE-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,WIEGEKARTE_EDIT( 	ENUM_VALID_THEME.WIEGEKARTE, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,WIEGEKARTE_VIEW( 	ENUM_VALID_THEME.WIEGEKARTE, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,WIEGEKARTE_NEW( 		ENUM_VALID_THEME.WIEGEKARTE, 	ENUM_VALID_BASICFUNCTION.NEW)
		,WIEGEKARTE_DELETE( 	ENUM_VALID_THEME.WIEGEKARTE, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,WIEGEKARTE_ATTACHMENT( 	ENUM_VALID_THEME.WIEGEKARTE, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.WK_RB_LIST_EDIT)
	,VIEW(ENUM_VALIDATION.WK_RB_LIST_VIEW)
	,NEW(ENUM_VALIDATION.WK_RB_LIST_NEW)
	,DELETE(ENUM_VALIDATION.WK_RB_LIST_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.WK_RB_LIST_ATTACHMENT)
	,STORNO(ENUM_VALIDATION.WK_RB_LIST_STORNO)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private WK_RB_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
