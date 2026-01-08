package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class ES_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public ES_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_EMAIL_SEND", "", false);
		
		this.add_SQLField(new SQLField(		_DB.EMAIL_SEND$ID_EMAIL_SEND, 
											ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND2, 
											new MyE2_String(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND2,false), bibE2.get_CurrSession()), false);
		
		this.add_SQLField(new SQLField(		_DB.EMAIL_SEND$ID_EMAIL_SEND, 
											ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND3, 
											new MyE2_String(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND3,false), bibE2.get_CurrSession()), false);
		
		this.add_SQLField(new SQLField(		_DB.EMAIL_SEND$ID_EMAIL_SEND, 
											ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND4, 
											new MyE2_String(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND4,false), bibE2.get_CurrSession()), false);
		
		this.add_SQLField(new SQLField(		_DB.EMAIL_SEND$ID_EMAIL_SEND, 
											ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND5, 
											new MyE2_String(ES_CONST.HASHKEY_FIELDNAME_ID_EMAIL_SEND5,false), bibE2.get_CurrSession()), false);
		this.initFields();
	}
	
}
