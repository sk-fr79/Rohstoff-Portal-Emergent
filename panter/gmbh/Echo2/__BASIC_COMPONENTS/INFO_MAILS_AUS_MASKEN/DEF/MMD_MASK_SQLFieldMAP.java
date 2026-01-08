package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MMD_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public MMD_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_MAIL_AUS_MASK", "", false);

		this.initFields();
	}

}
