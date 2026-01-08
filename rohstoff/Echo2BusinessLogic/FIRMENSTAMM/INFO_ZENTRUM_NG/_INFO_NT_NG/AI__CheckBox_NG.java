package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.exceptions.myException;

public class AI__CheckBox_NG extends MyE2_CheckBox implements IF_Saveable {

	private AI__Const_NG.BLOCK_TO_SHOW  zugehoerigerBlock = null;
	
	public AI__CheckBox_NG(Object cText, MyE2_String cToolTipText,	boolean bIsSelected, AI__Const_NG.BLOCK_TO_SHOW block) {
		super(cText, cToolTipText, bIsSelected, false);
		this.zugehoerigerBlock = block;
		this.setBorder(new Border(1, new E2_ColorBase(), Border.STYLE_NONE));
		this.setLineWrap(true);
	}

	public AI__Const_NG.BLOCK_TO_SHOW get_zugehoerigerBlock() {
		return zugehoerigerBlock;
	}

	@Override
	public String get_value_to_save() throws myException {
		return this.isSelected()?"Y":"N";
	}

	@Override
	public void restore_value(String value) throws myException {
		if (value.equals("Y")) {
			this.setSelected(true);
		} else {
			this.setSelected(false);
		}
	}

	@Override
	public void set_component_to_status_not_saved() throws myException {
		this.setSelected(true);
	}

	@Override
	public Component get_Comp() throws myException {
		return this;
	}

	@Override
	public void add_action(XX_ActionAgent agent) throws myException {
		super.add_oActionAgent(agent);
	}

}
