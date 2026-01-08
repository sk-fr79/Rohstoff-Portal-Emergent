package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MODUL__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public MODUL__LIST_SqlFieldMAP() throws myException 
	{
		super("JD_SERVLETS", "", false);
		
		this.initFields();
	}
	
}
