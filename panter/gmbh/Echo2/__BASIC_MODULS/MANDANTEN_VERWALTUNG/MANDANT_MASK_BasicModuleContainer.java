package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MANDANT_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK {

	private MANDANT_MASK_ComponentMAP oMANDANT_MASK_ComponentMAP = null;
	
	public MANDANT_MASK_BasicModuleContainer() throws myException	{
		
		
		super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_MANDANTENVERWALTUNG_MASKE.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		oMANDANT_MASK_ComponentMAP = new MANDANT_MASK_ComponentMAP();
		
		this.INIT(oMANDANT_MASK_ComponentMAP,  new MANDANT_MASK(oMANDANT_MASK_ComponentMAP), new Extent(900), new Extent(650));
		
		//sonderfelder ohne mandant uebergeben
		this.get_vCombinedComponentMAPs().set_Array_DB_SonderFelder(bibALL.get_DB_ZusatzFelder(false, true, true, null, bibALL.get_KUERZEL()));
		
		this.set_oResizeHelper(new ownResizeHelper());

	}
	
	private class ownResizeHelper extends XX_BasicContainerResizeHelper {

		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			MANDANT_MASK_grid_enum_mandant_decision decisionComp = 
					(MANDANT_MASK_grid_enum_mandant_decision)oMANDANT_MASK_ComponentMAP.get(MANDANT_CONST.HASHKEY_MASK_MANDANT_DECISIONS);
			
			decisionComp.getContainerExForDecisions().setHeight(new Extent(ownContainer.get_oExtHeight().getValue()-270));
			decisionComp.getContainerExForDecisions().setWidth(new Extent(ownContainer.get_oExtWidth().getValue()-50));
		}
		
	}
	
	
	
}
