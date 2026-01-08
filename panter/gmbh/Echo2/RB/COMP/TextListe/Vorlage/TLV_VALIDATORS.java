 
package panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum TLV_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,TEXT_LISTE_VORLAGE("TEXT_LISTE_VORLAGE-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,TEXT_LISTE_VORLAGE_EDIT( 	ENUM_VALID_THEME.TEXT_LISTE_VORLAGE, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,TEXT_LISTE_VORLAGE_VIEW( 	ENUM_VALID_THEME.TEXT_LISTE_VORLAGE, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,TEXT_LISTE_VORLAGE_NEW( 		ENUM_VALID_THEME.TEXT_LISTE_VORLAGE, 	ENUM_VALID_BASICFUNCTION.NEW)
		,TEXT_LISTE_VORLAGE_DELETE( 	ENUM_VALID_THEME.TEXT_LISTE_VORLAGE, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,TEXT_LISTE_VORLAGE_ATTACHMENT( 	ENUM_VALID_THEME.TEXT_LISTE_VORLAGE, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.TEXT_LISTE_VORLAGE_EDIT)
	,VIEW(ENUM_VALIDATION.TEXT_LISTE_VORLAGE_VIEW)
	,NEW(ENUM_VALIDATION.TEXT_LISTE_VORLAGE_NEW)
	,DELETE(ENUM_VALIDATION.TEXT_LISTE_VORLAGE_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.TEXT_LISTE_VORLAGE_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private TLV_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
