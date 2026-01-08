 
package rohstoff.Echo2BusinessLogic._TaxOld;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum TO_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,MWSTSCHLUESSEL("MWSTSCHLUESSEL-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,MWSTSCHLUESSEL_EDIT( 	ENUM_VALID_THEME.MWSTSCHLUESSEL, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,MWSTSCHLUESSEL_VIEW( 	ENUM_VALID_THEME.MWSTSCHLUESSEL, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,MWSTSCHLUESSEL_NEW( 		ENUM_VALID_THEME.MWSTSCHLUESSEL, 	ENUM_VALID_BASICFUNCTION.NEW)
		,MWSTSCHLUESSEL_DELETE( 	ENUM_VALID_THEME.MWSTSCHLUESSEL, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,MWSTSCHLUESSEL_ATTACHMENT( 	ENUM_VALID_THEME.MWSTSCHLUESSEL, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.MWSTSCHLUESSEL_OLD_EDIT)
	,VIEW(ENUM_VALIDATION.MWSTSCHLUESSEL_OLD_VIEW)
	,NEW(ENUM_VALIDATION.MWSTSCHLUESSEL_OLD_NEW)
	,DELETE(ENUM_VALIDATION.MWSTSCHLUESSEL_OLD_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.MWSTSCHLUESSEL_OLD_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private TO_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
