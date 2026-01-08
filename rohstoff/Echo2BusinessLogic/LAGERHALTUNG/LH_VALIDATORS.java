 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum LH_VALIDATORS {
	
	EDIT(		ENUM_VALIDATION.LAGER_BOX_EDIT)
	,VIEW(		ENUM_VALIDATION.LAGER_BOX_VIEW)
	,NEW(		ENUM_VALIDATION.LAGER_BOX_NEW)
	,DELETE(	ENUM_VALIDATION.LAGER_BOX_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.LAGER_BOX_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private LH_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
