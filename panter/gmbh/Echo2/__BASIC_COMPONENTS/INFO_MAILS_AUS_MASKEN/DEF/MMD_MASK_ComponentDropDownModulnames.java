package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_MODULENAMES_DROPDOWN;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class MMD_MASK_ComponentDropDownModulnames extends DB_Component_MODULENAMES_DROPDOWN {

	public MMD_MASK_ComponentDropDownModulnames(SQLField osqlField) throws myException {
		super(osqlField, new Extent(300), new E2_FontPlain());
	}

}
