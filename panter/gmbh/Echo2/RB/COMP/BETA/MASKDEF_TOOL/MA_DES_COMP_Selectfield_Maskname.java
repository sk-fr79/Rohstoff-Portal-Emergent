package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_MASKNAME;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_COMP_Selectfield_Maskname extends RB_SelectField {
	
	public MA_DES_COMP_Selectfield_Maskname(IF_Field p_iffield) throws myException {
		super(
				p_iffield, 
				ENUM_MASKNAME.TEST.get_dd_Array(true)
				,false
				,new Extent(200)
				);
		;
	}
	
}
