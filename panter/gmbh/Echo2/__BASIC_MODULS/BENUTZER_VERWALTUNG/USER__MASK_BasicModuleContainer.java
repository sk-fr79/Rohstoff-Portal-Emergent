package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class USER__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public USER__MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_USERVERWALTUNG_MASKE.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		USER__MASK_ComponentMAP oUSER__MASK_ComponentMAP = new USER__MASK_ComponentMAP();
		
		this.INIT(oUSER__MASK_ComponentMAP, new USER__MASK(oUSER__MASK_ComponentMAP), new Extent(900), new Extent(650));
		
		//sonderfelder ohne mandant uebergeben
		this.get_vCombinedComponentMAPs().set_Array_DB_SonderFelder(bibALL.get_DB_ZusatzFelder(false, true, true, null, bibALL.get_KUERZEL()));
		
	}
	
	
}
