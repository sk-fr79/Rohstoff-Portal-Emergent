package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class ES_RB_MASK_ComponentMapCollector extends RB_ComponentMapCollector {

	public ES_RB_MASK_ComponentMapCollector(String table_base_name, String id_table) throws myException {
		super();
		
		this.registerComponent(new RB_KM(_TAB.email_send	), new ES_RB_MASK_ComponentMap(table_base_name, id_table));
	}

}
