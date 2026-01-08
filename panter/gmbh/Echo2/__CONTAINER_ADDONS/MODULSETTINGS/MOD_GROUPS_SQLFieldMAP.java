package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class MOD_GROUPS_SQLFieldMAP extends Project_SQLFieldMAP
{

	public MOD_GROUPS_SQLFieldMAP(String cMODULE_KENNER_LIST_BELONGS_TO) throws myException
	{
		super("JT_COLLECTION_DEF",":MODULE_KENNER:",false);
		
		
		if (bibALL.isEmpty(cMODULE_KENNER_LIST_BELONGS_TO))
			throw new myException("MV_SQLFieldMapLIST:Constructor:Parameter cMODUL_KENNER is a MUST");
		
		String cHelp = bibALL.MakeSql(cMODULE_KENNER_LIST_BELONGS_TO);
		
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_COLLECTION_DEF","MODULE_KENNER","MODULE_KENNER",new MyE2_String("Modulkenner"),cHelp,bibE2.get_CurrSession()), false);
		
		this.initFields();
	}

}
