package panter.gmbh.indep.dataTools.ORACLE;


import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;


public class MyDBToolBox_ORA extends MyDBToolBox
{

	public MyDBToolBox_ORA() throws myException
	{
		super(	bibALL.get_oracle_classname(),
				bibALL.get_oracle_jdbcstring(),
				bibALL.get_oracle_login(),
				bibALL.get_oracle_password(),
				DB_META.DB_ORA);
	}

}
