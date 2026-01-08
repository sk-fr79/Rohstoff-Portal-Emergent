package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_SelectField_DPI extends RB_SelectField implements IF_RB_Component_Savable{

	public SCAN_SelectField_DPI(SQLField osqlField) throws myException {
		super(osqlField);
		this.populateCombobox(SCAN_CONST.SCAN_DPI.DPI150.get_dd_Array(false),null, null, true, false);
		this.setWidth(new Extent(80));
	}
	
	public SCAN_SelectField_DPI(SQLField osqlField, int width) throws myException {
		super(osqlField);
		this.populateCombobox(SCAN_CONST.SCAN_DPI.DPI150.get_dd_Array(false),null, null, true, false);
		this.setWidth(new Extent(width));
	}
}
