package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class GROOVY_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
 	
	public GROOVY_LIST_SqlFieldMAP() throws myException 
	{
		super(_DB.GROOVYSCRIPT, ":"+_DB.GROOVYSCRIPT$ERZEUGT_VON+":"+_DB.GROOVYSCRIPT$ERZEUGT_AM+":", false);
		this.initFields();
	}
	
}
