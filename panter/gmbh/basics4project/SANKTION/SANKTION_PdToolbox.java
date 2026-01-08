package panter.gmbh.basics4project.SANKTION;

import panter.gmbh.basics4project.ENUM_REGISTRY;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public class SANKTION_PdToolbox extends MyDBToolBox{



	public SANKTION_PdToolbox() throws myException {
		super(new MyConnection(
				(String) bibALL.getSessionValue("CLASSNAME")
				,ENUM_REGISTRY.SANKTION_JDBC_STRING.getStringValue()
				,ENUM_REGISTRY.SANKTION_JDBC_LOGIN.getStringValue()
				,ENUM_REGISTRY.SANKTION_JDBC_PWD.getStringValue()
				,DB_META.DB_ORA
				)
		);
		
		
		
		
		
	}



}
