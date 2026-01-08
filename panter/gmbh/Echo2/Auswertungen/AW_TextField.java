package panter.gmbh.Echo2.Auswertungen;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.UserSettings.IF_Saveable_differentTypes;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class AW_TextField extends MyE2_TextField  implements IF_Saveable_differentTypes{

	private String save_key = null;
	
	public AW_TextField(String p_save_key,String cText, int iwidthPixel, int imaxInputSize) {
		super(cText, iwidthPixel, imaxInputSize);
		this.save_key = p_save_key;
	}

	@Override
	public String get_value_to_save() throws myException {
		return S.NN(this.getText());
	}

	@Override
	public void restore_value(String value) throws myException {
		this.setText(S.NN(value));
	}

	@Override
	public void set_component_to_status_not_saved() throws myException {
		this.setText("");
	}

	@Override
	public Component get_Comp() throws myException {
		return this;
	}

	@Override
	public void add_action(XX_ActionAgent agent) throws myException {
	}

	@Override
	public String get_component_key() throws myException {
		return this.save_key;
	}

}
