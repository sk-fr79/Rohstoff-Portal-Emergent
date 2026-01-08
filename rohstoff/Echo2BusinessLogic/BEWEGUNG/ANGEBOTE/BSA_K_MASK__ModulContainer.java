package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSA_K_MASK__ModulContainer extends Project_BasicModuleContainer_MASK 
{
	public static int MASK_WIDTH = 1000;
	public static int MASK_HEIGHT = 700;

	private BS__SETTING					SETTING = null;
	private MyE2_TabbedPane				oTabbedPaneForMask = new MyE2_TabbedPane(null);
	
	private BSA_K_LIST__ModulContainer   oBSA_K_ModulContainerLIST = null;
	
	public BSA_K_MASK__ModulContainer(BS__SETTING osetting, BSA_K_LIST__ModulContainer BSA_K_ModulContainerLIST)  throws myException
	{
		super(osetting.get_cMODULCONTAINER_MASK_IDENTIFIER());
		this.SETTING = osetting;

		oTabbedPaneForMask.set_bAutoHeight(true);

		this.oBSA_K_ModulContainerLIST = BSA_K_ModulContainerLIST;
		
		BSA_K_MASK__ComponentMAP oComponentMAP = new BSA_K_MASK__ComponentMAP(this);
		BSA_K_MASK				oMask		  = new BSA_K_MASK(oComponentMAP,this.oTabbedPaneForMask);
		
		this.INIT(oComponentMAP,oMask,new Extent(BSA_K_MASK__ModulContainer.MASK_WIDTH),new Extent(BSA_K_MASK__ModulContainer.MASK_HEIGHT));
		
	}

	public BS__SETTING get_SETTING()
	{
		return SETTING;
	}

	public MyE2_TabbedPane get_oTabbedPaneForMask()
	{
		return oTabbedPaneForMask;
	}

	public BSA_K_LIST__ModulContainer get_oBSA_K_ModulContainerLIST()
	{
		return oBSA_K_ModulContainerLIST;
	}
	
	
}
