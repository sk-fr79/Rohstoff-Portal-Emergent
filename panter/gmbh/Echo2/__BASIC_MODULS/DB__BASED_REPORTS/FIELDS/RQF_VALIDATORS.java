 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;
  
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
  
public enum RQF_VALIDATORS {
	
	//hier werden die globalen validierer ersetzt fuer dieses modul
    /*
    *  in der validierer-enum folgende eintrage erfassen:
    *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
       ,REPORTING_QUERY_FIELD("REPORTING_QUERY_FIELD-Beschreibung")
    *  
    *  
    *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
    *  
     	,REPORTING_QUERY_FIELD_EDIT( 	ENUM_VALID_THEME.REPORTING_QUERY_FIELD, 		ENUM_VALID_BASICFUNCTION.EDIT)
		,REPORTING_QUERY_FIELD_VIEW( 	ENUM_VALID_THEME.REPORTING_QUERY_FIELD, 		ENUM_VALID_BASICFUNCTION.VIEW)
		,REPORTING_QUERY_FIELD_NEW( 		ENUM_VALID_THEME.REPORTING_QUERY_FIELD, 	ENUM_VALID_BASICFUNCTION.NEW)
		,REPORTING_QUERY_FIELD_DELETE( 	ENUM_VALID_THEME.REPORTING_QUERY_FIELD, 	ENUM_VALID_BASICFUNCTION.DELETE)
    * 
    */
	
	EDIT(ENUM_VALIDATION.REPORTING_QUERY_EDIT)
	,VIEW(ENUM_VALIDATION.REPORTING_QUERY_VIEW)
	,NEW(ENUM_VALIDATION.REPORTING_QUERY_NEW)
	,DELETE(ENUM_VALIDATION.REPORTING_QUERY_DELETE)
	;
	
	private ENUM_VALIDATION m_validation = null;
 
	private RQF_VALIDATORS(ENUM_VALIDATION validation) {
		this.m_validation = validation; 
	}
	
	public ENUM_VALIDATION getValidation() {
		return m_validation;
	}
 
	public E2_ButtonAUTHValidator getValidator() {
		return this.m_validation.getValidator();
	}
 
 
 
}
 
