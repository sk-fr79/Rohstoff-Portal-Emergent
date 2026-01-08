package panter.gmbh.Echo2.Auswertungen;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.UserSettings.IF_Saveable_differentTypes;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class AW_CheckBox extends MyE2_CheckBox implements IF_Saveable_differentTypes{

	private String save_key = null;
	
	public AW_CheckBox(String p_save_key, Object cText ) {
		super(cText);
		this.save_key = p_save_key;
	}
	
	
	public AW_CheckBox(String p_save_key, boolean bIsSelected, boolean bSetDisabledFromBasic) {
		super(bIsSelected, bSetDisabledFromBasic);
		this.save_key = p_save_key;
	}



	@Override
	public String get_value_to_save() throws myException {
		return this.isSelected()?"Y":"N";
	}

	@Override
	public void restore_value(String value) throws myException {
		this.setSelected(S.NN(value).equals("Y"));
	}

	@Override
	public void set_component_to_status_not_saved() throws myException {
		this.setSelected(false);		
	}

	@Override
	public Component get_Comp() throws myException {
		return this;
	}

	@Override
	public void add_action(XX_ActionAgent agent) throws myException {
		this.add_oActionAgent(agent);
	}

	@Override
	public String get_component_key() throws myException {
		return this.save_key;
	}

	
	
}
