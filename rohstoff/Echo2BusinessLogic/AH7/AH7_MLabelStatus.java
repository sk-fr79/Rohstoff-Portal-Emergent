/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class AH7_MLabelStatus extends RB_lab {

	/**
	 * @throws myException 
	 * 
	 */
	public AH7_MLabelStatus() throws myException {
		super();
		this._setStatus(AH7__ENUM_STATUSRELATION.UNDEF);
	}


	
	public AH7_MLabelStatus _setStatus(AH7__ENUM_STATUSRELATION status) 	throws myException {
		this.setText("");
		if (status==AH7__ENUM_STATUSRELATION.UNDEF) {
			this._icon(E2_ResourceIcon.get_RI("ampel-red.png"));
			this._ttt(status.user_text());
		} else if (status==AH7__ENUM_STATUSRELATION.INACTIVE) {
			this._icon(E2_ResourceIcon.get_RI("ampel-yellow.png"));
		} else if (status==AH7__ENUM_STATUSRELATION.ACTIVE) {
			this._icon(E2_ResourceIcon.get_RI("ampel-green.png"));
		}

		return this;
	}

	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		this.setText("");
		this._ttt("");
	}

}
