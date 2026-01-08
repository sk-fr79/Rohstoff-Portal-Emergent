 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum WF_SIMPLE_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,LAUFZETTEL_EINTRAG("LAUFZETTEL_EINTRAG-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,LAUFZETTEL_EINTRAG_EDIT( 	ENUM_VALID_THEME.LAUFZETTEL_EINTRAG, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,LAUFZETTEL_EINTRAG_VIEW( 	ENUM_VALID_THEME.LAUFZETTEL_EINTRAG, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,LAUFZETTEL_EINTRAG_NEW( 		ENUM_VALID_THEME.LAUFZETTEL_EINTRAG, 	ENUM_VALID_BASICFUNCTION.NEW)
		,LAUFZETTEL_EINTRAG_DELETE( 	ENUM_VALID_THEME.LAUFZETTEL_EINTRAG, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,LAUFZETTEL_EINTRAG_ATTACHMENT( 	ENUM_VALID_THEME.LAUFZETTEL_EINTRAG, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.LAUFZETTEL_EINTRAG_EDIT)
	,VIEW(ENUM_VALIDATION.LAUFZETTEL_EINTRAG_VIEW)
	,NEW(ENUM_VALIDATION.LAUFZETTEL_EINTRAG_NEW)
	,DELETE(ENUM_VALIDATION.LAUFZETTEL_EINTRAG_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.LAUFZETTEL_EINTRAG_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private WF_SIMPLE_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
