package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_K_MASK__ModulContainer extends Project_BasicModuleContainer_MASK 
{
	public static int MASK_WIDTH = 1000;
	public static int MASK_HEIGHT = 700;
	
	
	private BS__SETTING				SETTING = null;
	
	private BSK_K_LIST__ModulContainer  oBSK_K_ModulContainerLIST = null;
	
	public BSK_K_MASK__ModulContainer(	BS__SETTING 				osetting, 
										BSK_K_LIST__ModulContainer 	bsk_K_ModulContainerLIST)  throws myException
	{
		super(osetting.get_cMODULCONTAINER_MASK_IDENTIFIER());
		this.SETTING = osetting;
		
		this.oBSK_K_ModulContainerLIST = bsk_K_ModulContainerLIST;
		
		BSK_K_MASK__ComponentMAP oComponentMAP = new BSK_K_MASK__ComponentMAP(this);
		BSK_K_MASK				oMask		  = new BSK_K_MASK(oComponentMAP);
		
		this.INIT(oComponentMAP,oMask,	new Extent(BSK_K_MASK__ModulContainer.MASK_WIDTH),
										new Extent(BSK_K_MASK__ModulContainer.MASK_HEIGHT));
		
	}


	public BS__SETTING get_SETTING()
	{
		return SETTING;
	}

	public BSK_K_LIST__ModulContainer get_oBSK_K_ModulContainerLIST()
	{
		return oBSK_K_ModulContainerLIST;
	}
	
}
