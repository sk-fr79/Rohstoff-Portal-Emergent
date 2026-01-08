 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum EM2_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,EMAIL_SEND("EMAIL_SEND-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,EMAIL_SEND_EDIT( 	ENUM_VALID_THEME.EMAIL_SEND, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,EMAIL_SEND_VIEW( 	ENUM_VALID_THEME.EMAIL_SEND, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,EMAIL_SEND_NEW( 		ENUM_VALID_THEME.EMAIL_SEND, 	ENUM_VALID_BASICFUNCTION.NEW)
		,EMAIL_SEND_DELETE( 	ENUM_VALID_THEME.EMAIL_SEND, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,EMAIL_SEND_ATTACHMENT( 	ENUM_VALID_THEME.EMAIL_SEND, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.EMAIL_SEND_EDIT)
	,VIEW(ENUM_VALIDATION.EMAIL_SEND_VIEW)
	,NEW(ENUM_VALIDATION.EMAIL_SEND_NEW)
	,DELETE(ENUM_VALIDATION.EMAIL_SEND_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.EMAIL_SEND_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private EM2_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
