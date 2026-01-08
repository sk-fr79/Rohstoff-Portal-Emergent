 
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum TX_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,TAX("TAX-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,TAX_EDIT( 	ENUM_VALID_THEME.TAX, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,TAX_VIEW( 	ENUM_VALID_THEME.TAX, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,TAX_NEW( 		ENUM_VALID_THEME.TAX, 	ENUM_VALID_BASICFUNCTION.NEW)
		,TAX_DELETE( 	ENUM_VALID_THEME.TAX, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,TAX_ATTACHMENT( 	ENUM_VALID_THEME.TAX, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.TAX_EDIT)
	,VIEW(ENUM_VALIDATION.TAX_VIEW)
	,NEW(ENUM_VALIDATION.TAX_NEW)
	,DELETE(ENUM_VALIDATION.TAX_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.TAX_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private TX_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
