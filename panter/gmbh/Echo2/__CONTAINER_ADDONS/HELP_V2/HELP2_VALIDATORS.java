 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.dataTools.IF_Field;
  
public enum HELP2_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,HILFETEXT("HILFETEXT-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,HILFETEXT_EDIT( 	ENUM_VALID_THEME.HILFETEXT, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,HILFETEXT_VIEW( 	ENUM_VALID_THEME.HILFETEXT, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,HILFETEXT_NEW( 		ENUM_VALID_THEME.HILFETEXT, 	ENUM_VALID_BASICFUNCTION.NEW)
		,HILFETEXT_DELETE( 	ENUM_VALID_THEME.HILFETEXT, 	ENUM_VALID_BASICFUNCTION.DELETE)
    * 
    */
	
	EDIT(ENUM_VALIDATION.HILFETEXT_EDIT)
	,VIEW(ENUM_VALIDATION.HILFETEXT_VIEW)
	,NEW(ENUM_VALIDATION.HILFETEXT_NEW)
	,DELETE(ENUM_VALIDATION.HILFETEXT_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.HILFETEXT_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private HELP2_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
		
		IF_Field f = HILFETEXT.abschlussdatum;
		
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
