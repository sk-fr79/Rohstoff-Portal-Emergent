/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;

/**
 * @author martin
 *
 */
public class AH7P_LIST_bt_SQLExport extends E2_ButtonToCreate_SQL_ExportStatements {


	/**
	 * @param NaviList
	 */
	public AH7P_LIST_bt_SQLExport(E2_NavigationList NaviList) {
		super(NaviList);
		this.add_GlobalValidator(ENUM_VALIDATION.AH7_PROFIL_EXPORT_SQL.getValidatorWithoutSupervisorPersilschein());
		
	}
	
	
	
}
