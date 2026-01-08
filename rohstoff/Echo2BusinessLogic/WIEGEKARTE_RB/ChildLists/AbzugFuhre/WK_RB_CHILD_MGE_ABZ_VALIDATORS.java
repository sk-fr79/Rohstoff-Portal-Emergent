 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum WK_RB_CHILD_MGE_ABZ_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,WIEGEKARTE_MGE_ABZ("WIEGEKARTE_MGE_ABZ-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,WIEGEKARTE_MGE_ABZ_EDIT( 	ENUM_VALID_THEME.WIEGEKARTE_MGE_ABZ, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,WIEGEKARTE_MGE_ABZ_VIEW( 	ENUM_VALID_THEME.WIEGEKARTE_MGE_ABZ, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,WIEGEKARTE_MGE_ABZ_NEW( 		ENUM_VALID_THEME.WIEGEKARTE_MGE_ABZ, 	ENUM_VALID_BASICFUNCTION.NEW)
		,WIEGEKARTE_MGE_ABZ_DELETE( 	ENUM_VALID_THEME.WIEGEKARTE_MGE_ABZ, 	ENUM_VALID_BASICFUNCTION.DELETE)
		,WIEGEKARTE_MGE_ABZ_ATTACHMENT( 	ENUM_VALID_THEME.WIEGEKARTE_MGE_ABZ, 	ENUM_VALID_BASICFUNCTION.ATTACHMENT)
    * 
    */
	
	EDIT(ENUM_VALIDATION.WK_RB_CHILD_MGE_ABZ_EDIT)
	,VIEW(ENUM_VALIDATION.WK_RB_CHILD_MGE_ABZ_VIEW)
	,NEW(ENUM_VALIDATION.WK_RB_CHILD_MGE_ABZ_NEW)
	,DELETE(ENUM_VALIDATION.WK_RB_CHILD_MGE_ABZ_DELETE)
	,ATTACHMENT(ENUM_VALIDATION.WK_RB_CHILD_MGE_ABZ_ATTACHMENT)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private WK_RB_CHILD_MGE_ABZ_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
