package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.indep.exceptions.myException;

public class RB_SelField_USER extends RB_selField {

	public RB_SelField_USER(boolean emtpy_in_front, Extent e_width, ENUM_USER_TYP... typ) throws myException {
		super();
		USER_SELECTOR_GENERATOR_NT  oSelUsers = new USER_SELECTOR_GENERATOR_NT(true,typ);
		String[][] arrayData = oSelUsers.get_selUsers(emtpy_in_front);
		String[][] arrayshadow = oSelUsers.get_notSelectedUsers();
	
		this._populate(arrayData);
		this._populateShadow(arrayshadow);

		this._width(e_width);
	}


}
