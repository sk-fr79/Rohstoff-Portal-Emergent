/**
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeigeMultiLine;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * wird als reines anzeigefeld definiert, keine uebergabe in die datenbank oder zurueck
 */
public class RQ_TextAnzeigeAllSQL extends RB_TextAnzeigeMultiLine {

	
	public static final  RB_KF KEY = (RB_KF)new RB_KF()._setKeyAndName("b41d86da-dc3d-11e8-9f8b-f2801f1b9fd1");

	
	
	public RQ_TextAnzeigeAllSQL(int iWidth) {
		super(iWidth);
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		this.setText("");
	}

	
	
	
}
