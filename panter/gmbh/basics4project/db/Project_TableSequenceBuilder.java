package panter.gmbh.basics4project.db;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.E2_SequenceBuilder;
import panter.gmbh.indep.bibALL;

public class Project_TableSequenceBuilder extends E2_SequenceBuilder
{

	public Project_TableSequenceBuilder(String cTableName,  String cMODULKENNER)
	{
		super(	cTableName.substring(3), 
				"SELECT   NVL(MAX(ID_" + cTableName.substring(3) + "),0)+1 FROM " + bibE2.cTO() + "." + cTableName, 
				bibALL.get_MINMAL_TABLE_SEQUENZ(cTableName),
				true,
				cMODULKENNER);
	}

}
