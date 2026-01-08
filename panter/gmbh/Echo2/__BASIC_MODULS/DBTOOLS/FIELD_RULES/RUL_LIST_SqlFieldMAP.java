package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class RUL_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public RUL_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_FIELD_RULE", "", false);
		this.initFields();
	}
	
}
