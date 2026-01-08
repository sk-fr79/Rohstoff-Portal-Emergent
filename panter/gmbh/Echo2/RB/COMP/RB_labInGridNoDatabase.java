/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 29.05.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import nextapp.echo2.app.ResourceImageReference;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 29.05.2020
 *
 */
public class RB_labInGridNoDatabase extends RB_labInGrid implements IF_RbComponentWithOwnKey{

	private RB_KF ownKey = null;
	
	
	/**
	 * @author martin
	 * @date 29.05.2020
	 *
	 */
	public RB_labInGridNoDatabase() {
		super();
	}

	public RB_labInGridNoDatabase(RB_KF ownkey) {
		super();
		this.ownKey = ownkey;
		this._t(ownkey.FIELDNAME());
	}
	
	
	
	
	/**
	 * @author martin
	 * @date 29.05.2020
	 *
	 * @param text
	 */
	public RB_labInGridNoDatabase(String text) {
		super(text);
	}

	/**
	 * @author martin
	 * @date 29.05.2020
	 *
	 * @param text
	 */
	public RB_labInGridNoDatabase(MyE2_String text) {
		super(text);
	}

	/**
	 * @author martin
	 * @date 29.05.2020
	 *
	 * @param icon
	 */
	public RB_labInGridNoDatabase(ResourceImageReference icon) {
		super(icon);
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	@Override
	public RB_KF getFieldKey() {
		return ownKey;
	}

}
