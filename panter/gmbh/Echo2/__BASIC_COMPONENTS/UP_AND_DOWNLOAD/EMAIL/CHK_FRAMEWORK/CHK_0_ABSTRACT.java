package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.IF_STATUS;
import panter.gmbh.indep.exceptions.myException;

public abstract class CHK_0_ABSTRACT {
	
	public abstract IF_STATUS 		check_status(MyE2_MessageVector mv_sammler) throws myException;
	public abstract MyE2_String  	get_Description();
	
}
