package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST;

import java.util.LinkedHashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class test_bt_4_new_records extends E2_Button {

	/**
	 * @throws myException 
	 * 
	 */
	public test_bt_4_new_records() throws myException {
		super();
		
		this._t(new MyE2_String("Test neue Records ..."))
				._backDDark()
				._fo_bold()
				._aaa(new ownAction());
		
		
		
		
		
	}

	private class ownAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			Rec20 rec = new Rec20(_TAB.email_send);
			
			rec ._nv(EMAIL_SEND.betreff, "test", mv)
				._nv(EMAIL_SEND.sender_adress, "123@34.67", mv);
			   
			
			Rec20 rec_read = new Rec20(_TAB.email_send)._fill_id("1409");
			rec_read._nv(EMAIL_SEND.betreff, "test", mv)
					._nv(EMAIL_SEND.sender_adress, "123@34.67", mv);
			
			
			DEBUG.System_println(rec.get_sql_4_save(false,mv));
			DEBUG.System_println(rec_read.get_sql_4_save(false,mv));
			
			
			LinkedHashMap<String,String> hm_test = new LinkedHashMap<>();
			
			hm_test.put("ABC", "123");
			hm_test.put("XYZ", "4563");
			hm_test.put("ZXV", "111");
			hm_test.put("OPQ", "223");
					
			DEBUG.System_print(hm_test.keySet());
			
			
			DEBUG.System_print(hm_test.values());
			
		
			bibMSG.add_MESSAGE(mv);
			

	
		}
		
	}
	
	
}
