package panter.gmbh.Echo2.RB.COMP.BETA;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.IF.IF_generate_RB_KF;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_TextField_With_Assist extends E2_Grid implements IF_RB_Component_Savable, IF_generate_RB_KF {

	private RB_KF key = null;

	private RB_TextField textField = null;

	private MyE2_Button assistant_bt = null;
	
	private MyE2EXT__Component  EXT = new MyE2EXT__Component(this) ;

	public RB_TextField_With_Assist(int width, boolean miniIcon) {
		super();
	

		E2_ResourceIcon icon = 			miniIcon? E2_ResourceIcon.get_RI("wizard_mini.png"): E2_ResourceIcon.get_RI("wizard.png");
		E2_ResourceIcon icon_disabled = miniIcon? E2_ResourceIcon.get_RI("wizard_mini__.png"): E2_ResourceIcon.get_RI("wizard__.png");
		
		textField = new RB_TextField(width);
		
		assistant_bt = new MyE2_Button(icon, icon_disabled);
		assistant_bt._aaa( new ownAssistActionAgent());

		this._clear();
		this._gld(new RB_gld()._ins(0,0,2,0))
		._a(textField)._a(assistant_bt);
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.textField.setText("");
		} else {
			this.textField.setText(((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),""));
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		textField.setText(valueFormated);

	}

	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;

	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return new Vector<RB_Validator_Component>();
	}

	@Override
	public void set_EXT(MyE2EXT__Component oEXT) {
		this.EXT = oEXT;
	}

	@Override
	public MyE2EXT__Component EXT() {
		return this.EXT;
	}

	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		textField.set_bEnabled_For_Edit(bEnabled);
		assistant_bt.set_bEnabled_For_Edit(bEnabled);
	}

	@Override
	public MyE2IF__Component ME() throws myException {
		return this;
	}

	@Override
	public Component C_ME() throws myException {
		return this;
	}

	@Override
	public void mark_Neutral() throws myException {
		textField.mark_Neutral();
	}

	@Override
	public void mark_MustField() throws myException {
		textField.mark_MustField();
	}

	@Override
	public void mark_Disabled() throws myException {
		textField.mark_Disabled();
	}

	@Override
	public void mark_FalseInput() throws myException {
		textField.mark_FalseInput();
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		textField.set_Alignment(align);
	}

	@Override
	public void setFocusTraversalParticipant(boolean bGetsFocus) {
		textField.setFocusTraversalParticipant(bGetsFocus);
	}

	@Override
	public RB_KF K(IF_Field field) throws myException {
		this.key = new RB_KF(field);
		return this.key;
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return textField.getText();
	}

	@Override
	public void setEnabled(boolean bEnabled) {
		try {
			this.set_bEnabled_For_Edit(bEnabled);
		} catch (myException e) {
			e.printStackTrace();
		}
	}
	
	public abstract String get_auto_complete_data() throws myException;

	public RB_TextField getTextField() {
		return textField;
	}

	public MyE2_Button getAssistant_Button() {
		return assistant_bt;
	}
	
	public void enable_assistant_button(boolean bDisable) throws myException{
		this.assistant_bt.set_bEnabled_For_Edit(bDisable);
	}

	private class ownAssistActionAgent extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(S.isEmpty(textField.getText())){
				textField.setText(get_auto_complete_data());
			}
		}
	}
}
