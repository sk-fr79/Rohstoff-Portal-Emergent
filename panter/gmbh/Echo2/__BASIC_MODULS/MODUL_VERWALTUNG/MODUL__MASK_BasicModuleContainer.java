package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MODUL__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public MODUL__MASK_BasicModuleContainer() throws myException
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_MODUL_MANAGER_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		MODUL__MASK_ComponentMAP oMODUL__MASK_ComponentMAP = new MODUL__MASK_ComponentMAP();
		
		this.INIT(oMODUL__MASK_ComponentMAP, new MODUL__MASK(oMODUL__MASK_ComponentMAP), new Extent(900), new Extent(650));
		
		//sonderfelder ohne mandant uebergeben
		this.get_vCombinedComponentMAPs().set_Array_DB_SonderFelder(bibALL.get_DB_ZusatzFelder(false, true, true, null, bibALL.get_KUERZEL()));

		
	}
	
	
}
