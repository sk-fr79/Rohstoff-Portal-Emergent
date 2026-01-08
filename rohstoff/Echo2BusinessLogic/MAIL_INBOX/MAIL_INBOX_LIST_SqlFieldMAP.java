package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_INBOX;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MAIL_INBOX_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public MAIL_INBOX_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_EMAIL_INBOX", "", false);
		
		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:"
				, true, "JT_EMAIL_INBOX.ID_ADRESSE_ZUGEORDNET = JT_ADRESSE.ID_ADRESSE", "", 
				bibALL.get_HashMap("ADDRESS_INFO",  " NVL2(JT_ADRESSE.ID_ADRESSE,NVL(JT_ADRESSE.NAME1,'') ||' ' " +
												" || NVL(JT_ADRESSE.NAME2,'') || ', ' " +
												" || NVL(JT_ADRESSE.PLZ,'')|| ' ' " +
												" || NVL(JT_ADRESSE.ORT,'') " +
												" || ' (' || NVL(JT_ADRESSE.ID_ADRESSE,'') || ')'  ,'-' ) "));
		this.add_JOIN_Table("JD_USER", 
				"JD_USER", 
				SQLFieldMAP.LEFT_OUTER, 
				""
				, true, "JT_EMAIL_INBOX.ID_USER_GELESEN = JD_USER.ID_USER", "", 
				bibALL.get_HashMap("VERARBEITET_VON_INFO",  " NVL2(JD_USER.NAME1,JD_USER.NAME1,'')  " +
												" || NVL2(JD_USER.VORNAME,', ' || JD_USER.VORNAME,'')  " +
												" || ' ' || NVL2(JD_USER.NAME,' (' || JD_USER.NAME || ')','-') "));
		
		this.add_ORDER_SEGMENT(RECORD_EMAIL_INBOX.FIELD__DATE_RECEIVED + " DESC" );
		this.initFields();
	}
	
}
