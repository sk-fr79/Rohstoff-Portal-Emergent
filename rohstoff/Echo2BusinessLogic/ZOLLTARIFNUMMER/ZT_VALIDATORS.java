 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum ZT_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,ZOLLTARIFNUMMER("ZOLLTARIFNUMMER-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,ZOLLTARIFNUMMER_EDIT( 	ENUM_VALID_THEME.ZOLLTARIFNUMMER, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,ZOLLTARIFNUMMER_VIEW( 	ENUM_VALID_THEME.ZOLLTARIFNUMMER, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,ZOLLTARIFNUMMER_NEW( 		ENUM_VALID_THEME.ZOLLTARIFNUMMER, 	ENUM_VALID_BASICFUNCTION.NEW)
		,ZOLLTARIFNUMMER_DELETE( 	ENUM_VALID_THEME.ZOLLTARIFNUMMER, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,ZOLLTARIFNUMMER_ATTACHMENT( 	ENUM_VALID_THEME.ZOLLTARIFNUMMER, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.ZOLLTARIFNUMMER_EDIT)
	,VIEW(ENUM_VALIDATION.ZOLLTARIFNUMMER_VIEW)
	,NEW(ENUM_VALIDATION.ZOLLTARIFNUMMER_NEW)
	,DELETE(ENUM_VALIDATION.ZOLLTARIFNUMMER_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.ZOLLTARIFNUMMER_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private ZT_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
