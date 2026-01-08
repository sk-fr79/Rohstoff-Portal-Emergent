package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class BANK_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public BANK_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_BANKENSTAMM_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		BANK_MASK_ComponentMAP oBANK_MASK_ComponentMAP = new BANK_MASK_ComponentMAP(this);
		
		this.INIT(oBANK_MASK_ComponentMAP, new BANK_MASK(oBANK_MASK_ComponentMAP), new Extent(1100), new Extent(700));
		
		this.add_SubTableComponentMAP(oBANK_MASK_ComponentMAP.get_oBANK_MASK_ZusatzMap());
	}
	
	
	
	
}
