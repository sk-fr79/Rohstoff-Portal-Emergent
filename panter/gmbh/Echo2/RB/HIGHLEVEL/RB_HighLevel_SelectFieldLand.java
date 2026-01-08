package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.LAND_SelektorQuery;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class RB_HighLevel_SelectFieldLand extends RB_SelectField {

	public RB_HighLevel_SelectFieldLand(IF_Field field, boolean emtpy_in_front, Extent e_width) throws myException {
		super(field);
		this.set_ListenInhalt(new LAND_SelektorQuery().get_dropDownList4DataComponent(true), false);
		this.setWidth(e_width);
	}

}
