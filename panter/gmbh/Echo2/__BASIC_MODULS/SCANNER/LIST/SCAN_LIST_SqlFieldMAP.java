package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public SCAN_LIST_SqlFieldMAP() throws myException 
	{
		super(_TAB.scanner_settings.n(), "", false);
		
		this.initFields();
	}
	
}
