package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.indep.exceptions.myException;

public interface CHK_IF_BELEG {
	
	public boolean 					is_Closed() throws myException;
	public String  					get_TABLEBASENAME();
	public String  					get_ID() throws myException;
	
	public void 					RollBackToStatus_Orig(MyE2_MessageVector mv_protokoll) throws myException;
	
	public RECORD_EMAIL_SEND     	get_RECORD_EMAIL_SEND_withOriginal() throws myException;
	
	public MyE2_String   			get_BelegInfo() throws myException;
	
	
}
