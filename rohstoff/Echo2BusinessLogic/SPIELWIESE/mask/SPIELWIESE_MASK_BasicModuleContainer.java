package rohstoff.Echo2BusinessLogic.SPIELWIESE.mask;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
//import panter.gmbh.Echo2.RB.BASICS.RB__MASK_KENNER.RB_MODUL_KENNER;
//import panter.gmbh.Echo2.RB.CONTROLLERS.RB_BT_SaveMask;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class SPIELWIESE_MASK_BasicModuleContainer extends RB_ModuleContainerMASK 
{

	public SPIELWIESE_MASK_BasicModuleContainer() throws myException
	{
		//IDEA: The HashMap of the Mask should be encapsulated in the Mask
	//	super(new SPIELWIESE_MASK_HashMap());

		SPIELWIESE_MASK mask = new SPIELWIESE_MASK(_DB.FIBU_KONTENREGEL_NEU);
	//	this.register_RB_MASK(mask);
		
		//TODO: The save Button does not work/is not displayed
	//	RB_BT_SaveMask sm = new RB_BT_SaveMask(this);
	//	mask.getGrid().add(sm);
	//	add(sm);

		//IDEA: Implemented getGrid, because components should be defined in the Mask
		this.add(mask.getGrid());
		

		//IDEA: change this to init();
	//	this.INIT(RB_MODUL_KENNER.POPUP_EMAIL_SEND, mask.getGrid(), false);
	}
	
	
}
