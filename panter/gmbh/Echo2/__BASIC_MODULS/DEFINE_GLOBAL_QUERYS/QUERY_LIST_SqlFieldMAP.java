package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public QUERY_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_QUERY", "", false);

		this.initFields();
	}
	
}
