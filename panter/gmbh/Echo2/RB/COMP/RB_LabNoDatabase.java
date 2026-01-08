/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 22.08.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import nextapp.echo2.app.ResourceImageReference;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 22.08.2019
 * Label fuer masken-settings (bekommt nichts aus der datenbank, kann aber in die RB_ComponentMap registriert werden)
 */
public class RB_LabNoDatabase extends RB_lab {

	/**
	 * @author martin
	 * @date 22.08.2019
	 *
	 */
	public RB_LabNoDatabase() {
	}

	/**
	 * @author martin
	 * @date 22.08.2019
	 *
	 * @param text
	 */
	public RB_LabNoDatabase(String text) {
		super(text);
	}

	/**
	 * @author martin
	 * @date 22.08.2019
	 *
	 * @param text
	 */
	public RB_LabNoDatabase(MyE2_String text) {
		super(text);
	}

	/**
	 * @author martin
	 * @date 22.08.2019
	 *
	 * @param icon
	 */
	public RB_LabNoDatabase(ResourceImageReference icon) {
		super(icon);
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	
	
	
}
