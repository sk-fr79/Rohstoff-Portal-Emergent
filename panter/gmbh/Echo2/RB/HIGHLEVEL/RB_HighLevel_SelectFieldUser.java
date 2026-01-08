package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class RB_HighLevel_SelectFieldUser extends RB_SelectField {

	public RB_HighLevel_SelectFieldUser(IF_Field field, boolean emtpy_in_front, Extent e_width, ENUM_USER_TYP... typ) throws myException {
		super(field);
		USER_SELECTOR_GENERATOR_NT  oSelUsers = new USER_SELECTOR_GENERATOR_NT(true,typ);
		String[][] arrayData = oSelUsers.get_selUsers(emtpy_in_front);
		String[][] arrayshadow = oSelUsers.get_notSelectedUsers();
	
		this.set_ListenInhalt(arrayData, false);
		this.set_odataToViewShadow(new dataToView(arrayshadow, false, bibE2.get_CurrSession()));
		this.setWidth(e_width);
	}

	public RB_HighLevel_SelectFieldUser(IF_Field field, boolean formated_value, boolean emtpy_in_front, Extent e_width, ENUM_USER_TYP... typ) throws myException {
		super(field);
		USER_SELECTOR_GENERATOR_NT  oSelUsers = new USER_SELECTOR_GENERATOR_NT(formated_value,typ);
		String[][] arrayData = oSelUsers.get_selUsers(emtpy_in_front);
		String[][] arrayshadow = oSelUsers.get_notSelectedUsers();
	
		this.set_ListenInhalt(arrayData, false);
		this.set_odataToViewShadow(new dataToView(arrayshadow, false, bibE2.get_CurrSession()));
		this.setWidth(e_width);
	}
	
	@Override
	public  RB_HighLevel_SelectFieldUser  _aaa(XX_ActionAgent agent) {
		this.add_oActionAgent(agent);
		return this;
	}
	
	
	@Override
	public  RB_HighLevel_SelectFieldUser  _aaaV(Vector<XX_ActionAgent> v_agents) {
		for (XX_ActionAgent agent: v_agents) {
			this.add_oActionAgent(agent);
		}
		return this;
	}

}
