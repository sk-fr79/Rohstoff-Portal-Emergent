/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components
 * @author manfred
 * @date 07.07.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorForegroundFont;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 07.07.2020
 *
 */
public class WK_RB_cb_StrahlungGeprueft extends RB_cb {

	public WK_RB_cb_StrahlungGeprueft() {
		this._aaa(new cb_ActionAgent_Color());
	}

	public WK_RB_cb_StrahlungGeprueft(String text) {
		super(text);
		this._aaa(new cb_ActionAgent_Color());
	}

	public WK_RB_cb_StrahlungGeprueft(MyE2_String text) {
		super(text);
		this._aaa(new cb_ActionAgent_Color());
	}
	
	public WK_RB_cb_StrahlungGeprueft _checkStatus() {
		if (isSelected()) {
			setBackground(new E2_ColorBase());
			setForeground(new E2_ColorForegroundFont());
			_fo_plain();
		} else {
			setBackground(Color.RED);
			setForeground(Color.YELLOW);
			_fo_bold();
		}
		return this;
	}
	
//	@Override
//	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
//		// TODO Auto-generated method stub
//		super.rb_Datacontent_to_Component(dataObject);
//		_checkStatus();
//	}
	
//	
//	@Override
//	public void rb_set_db_value_manual(String valueFormated) throws myException {
//		super.rb_set_db_value_manual(valueFormated);
//		_checkStatus();
//	}
//	
	
	
	/**
	 * 
	 * @author manfred
	 * @date 07.07.2020
	 *
	 */
	private class cb_ActionAgent_Color extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_RB_cb_StrahlungGeprueft oThis = WK_RB_cb_StrahlungGeprueft.this;
			oThis._checkStatus();
		}
		
	}

}
