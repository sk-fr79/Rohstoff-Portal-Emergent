 
package rohstoff.businesslogic.bewegung2.lager;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum B2_LAG_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,BG_ATOM("BG_ATOM-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,BG_ATOM_EDIT( 	ENUM_VALID_THEME.BG_ATOM, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,BG_ATOM_VIEW( 	ENUM_VALID_THEME.BG_ATOM, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,BG_ATOM_NEW( 		ENUM_VALID_THEME.BG_ATOM, 	ENUM_VALID_BASICFUNCTION.NEW)
		,BG_ATOM_DELETE( 	ENUM_VALID_THEME.BG_ATOM, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,BG_ATOM_ATTACHMENT( 	ENUM_VALID_THEME.BG_ATOM, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.BG_ATOM_EDIT)
	,VIEW(ENUM_VALIDATION.BG_ATOM_VIEW)
	,NEW(ENUM_VALIDATION.BG_ATOM_NEW)
	,DELETE(ENUM_VALIDATION.BG_ATOM_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.BG_ATOM_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private B2_LAG_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
