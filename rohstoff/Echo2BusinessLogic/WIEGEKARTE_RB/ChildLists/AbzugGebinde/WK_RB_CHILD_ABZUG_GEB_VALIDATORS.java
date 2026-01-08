 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum WK_RB_CHILD_ABZUG_GEB_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,WIEGEKARTE_ABZUG_GEB("WIEGEKARTE_ABZUG_GEB-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,WIEGEKARTE_ABZUG_GEB_EDIT( 	ENUM_VALID_THEME.WIEGEKARTE_ABZUG_GEB, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,WIEGEKARTE_ABZUG_GEB_VIEW( 	ENUM_VALID_THEME.WIEGEKARTE_ABZUG_GEB, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,WIEGEKARTE_ABZUG_GEB_NEW( 		ENUM_VALID_THEME.WIEGEKARTE_ABZUG_GEB, 	ENUM_VALID_BASICFUNCTION.NEW)
		,WIEGEKARTE_ABZUG_GEB_DELETE( 	ENUM_VALID_THEME.WIEGEKARTE_ABZUG_GEB, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,WIEGEKARTE_ABZUG_GEB_ATTACHMENT( 	ENUM_VALID_THEME.WIEGEKARTE_ABZUG_GEB, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.WK_RB_CHILD_ABZUG_GEBINDE_EDIT)
	,VIEW(ENUM_VALIDATION.WK_RB_CHILD_ABZUG_GEBINDE_VIEW)
	,NEW(ENUM_VALIDATION.WK_RB_CHILD_ABZUG_GEBINDE_NEW)
	,DELETE(ENUM_VALIDATION.WK_RB_CHILD_ABZUG_GEBINDE_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.WK_RB_CHILD_ABZUG_GEBINDE_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private WK_RB_CHILD_ABZUG_GEB_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
