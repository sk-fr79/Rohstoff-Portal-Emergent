package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW_WithoutAutoTooltip;
import panter.gmbh.indep.exceptions.myException;


/**
 * Abgeleitete Dropdown-Klasse für die Rekursive Suche in den Selektoren
 * @author manfred
 *
 */
public class WF_HEAD_LIST_Selection_User_DropDown extends
		Component_USER_DROPDOWN_NEW_WithoutAutoTooltip {

	
	public WF_HEAD_LIST_Selection_User_DropDown(boolean bFormatedIDs,Integer iWidth) throws myException {
		super(bFormatedIDs, iWidth);
		
	}

}
