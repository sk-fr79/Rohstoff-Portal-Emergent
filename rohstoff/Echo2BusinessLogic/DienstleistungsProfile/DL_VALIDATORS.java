 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum DL_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,DIENSTLEISTUNG_PROFIL("DIENSTLEISTUNG_PROFIL-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,DIENSTLEISTUNG_PROFIL_EDIT( 	ENUM_VALID_THEME.DIENSTLEISTUNG_PROFIL, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,DIENSTLEISTUNG_PROFIL_VIEW( 	ENUM_VALID_THEME.DIENSTLEISTUNG_PROFIL, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,DIENSTLEISTUNG_PROFIL_NEW( 		ENUM_VALID_THEME.DIENSTLEISTUNG_PROFIL, 	ENUM_VALID_BASICFUNCTION.NEW)
		,DIENSTLEISTUNG_PROFIL_DELETE( 	ENUM_VALID_THEME.DIENSTLEISTUNG_PROFIL, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,DIENSTLEISTUNG_PROFIL_ATTACHMENT( 	ENUM_VALID_THEME.DIENSTLEISTUNG_PROFIL, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.DIENSTLEISTUNG_PROFIL_EDIT)
	,VIEW(ENUM_VALIDATION.DIENSTLEISTUNG_PROFIL_VIEW)
	,NEW(ENUM_VALIDATION.DIENSTLEISTUNG_PROFIL_NEW)
	,DELETE(ENUM_VALIDATION.DIENSTLEISTUNG_PROFIL_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.DIENSTLEISTUNG_PROFIL_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private DL_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
