package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_K_MASK__ModulContainer extends Project_BasicModuleContainer_MASK 
{
	public static int MASK_WIDTH = 1000;
	public static int MASK_HEIGHT = 700;

	private BS__SETTING					SETTING = null;
	private MyE2_TabbedPane				oTabbedPaneForMask = new MyE2_TabbedPane(null);

	private BSRG_K_MASK__ComponentMAP 	oBSRG_K_ComponentMAP_Mask = null;
	
	
	private BSRG_K_LIST__ModulContainer   oBSRG_K_ModulContainerLIST = null;
	
	public BSRG_K_MASK__ModulContainer(BS__SETTING osetting, BSRG_K_LIST__ModulContainer BSRG_K_ModulContainerLIST)  throws myException
	{
		super(osetting.get_cMODULCONTAINER_MASK_IDENTIFIER());
		
		this.SETTING = 						osetting;
		this.oBSRG_K_ModulContainerLIST = 	BSRG_K_ModulContainerLIST;
		
		this.oBSRG_K_ComponentMAP_Mask = new BSRG_K_MASK__ComponentMAP(this);
		
		BSRG_K_MASK			oMask		  = new BSRG_K_MASK(this.oBSRG_K_ComponentMAP_Mask,this.oTabbedPaneForMask);
		
		this.INIT(this.oBSRG_K_ComponentMAP_Mask,oMask,new Extent(BSRG_K_MASK__ModulContainer.MASK_WIDTH),new Extent(BSRG_K_MASK__ModulContainer.MASK_HEIGHT));
	}



	public BS__SETTING get_SETTING()
	{
		return SETTING;
	}

	public void set_SETTING(BS__SETTING oSetting)
	{
		this.SETTING = oSetting;
		this.set_MODUL_IDENTIFIER(oSetting.get_cMODULCONTAINER_MASK_IDENTIFIER());
	}

	public BSRG_K_LIST__ModulContainer get_oBSRG_K_ModulContainerLIST()
	{
		return oBSRG_K_ModulContainerLIST;
	}


	
}
