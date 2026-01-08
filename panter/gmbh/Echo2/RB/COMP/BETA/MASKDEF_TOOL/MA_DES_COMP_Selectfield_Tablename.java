package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_COMP_Selectfield_Tablename extends RB_SelectField {

	public MA_DES_COMP_Selectfield_Tablename(IF_Field p_iffield) throws myException {
		super( p_iffield
				,DB_META.get_TablesQuerySort_A_to_Z(bibE2.cTO(),true, true, true)
				,false 
				,false
				,new Extent(200)
				);
	}

}
