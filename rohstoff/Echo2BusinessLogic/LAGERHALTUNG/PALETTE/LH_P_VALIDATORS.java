 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum LH_P_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,LAGER_PALETTE("LAGER_PALETTE-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,LAGER_PALETTE_EDIT( 	ENUM_VALID_THEME.LAGER_PALETTE, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,LAGER_PALETTE_VIEW( 	ENUM_VALID_THEME.LAGER_PALETTE, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,LAGER_PALETTE_NEW( 		ENUM_VALID_THEME.LAGER_PALETTE, 	ENUM_VALID_BASICFUNCTION.NEW)
		,LAGER_PALETTE_DELETE( 	ENUM_VALID_THEME.LAGER_PALETTE, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,LAGER_PALETTE_ATTACHMENT( 	ENUM_VALID_THEME.LAGER_PALETTE, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.LAGER_PALETTE_EDIT)
	,VIEW(ENUM_VALIDATION.LAGER_PALETTE_VIEW)
	,NEW(ENUM_VALIDATION.LAGER_PALETTE_NEW)
	,DELETE(ENUM_VALIDATION.LAGER_PALETTE_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.LAGER_PALETTE_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private LH_P_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
