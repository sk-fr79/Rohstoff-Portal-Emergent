package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class ADK_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public ADK_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_ADRESSKLASSE_DEF", "", false);
		
		this.add_SQLField(new SQLField(ADK_CONST.SQL_FIELD_NAMES.ID2), true);
		this.initFields();
	}
	
}
