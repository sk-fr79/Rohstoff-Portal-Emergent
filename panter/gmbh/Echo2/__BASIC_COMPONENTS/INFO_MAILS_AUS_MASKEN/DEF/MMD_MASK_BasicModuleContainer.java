package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class MMD_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public MMD_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_MAIL_AUS_MASK_DEF__MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		MMD_MASK_ComponentMAP oMMD_MASK_ComponentMAP = new MMD_MASK_ComponentMAP();
		
		this.INIT(oMMD_MASK_ComponentMAP, new MMD_MASK(oMMD_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
