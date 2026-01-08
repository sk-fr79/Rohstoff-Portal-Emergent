package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MANDANT_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public MANDANT_LIST_SqlFieldMAP() throws myException 
	{
		super("JD_MANDANT", "", false);

		this.initFields();
	}
	
}
