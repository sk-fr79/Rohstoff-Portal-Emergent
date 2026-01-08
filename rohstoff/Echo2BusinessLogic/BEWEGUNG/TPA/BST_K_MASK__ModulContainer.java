package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BST_K_MASK__ModulContainer extends Project_BasicModuleContainer_MASK 
{
	
	private BST_K_LIST__ModulContainer  		oBST_K_ModulContainerLIST = null;
	private BS__SETTING   					oSetting = null;
	
	
	public BST_K_MASK__ModulContainer( 	BST_K_LIST__ModulContainer 	bst_K_ModulContainerLIST,
										boolean  					bAddOpenTPA_Button)  throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_TPA_MASK);
		
		this.oBST_K_ModulContainerLIST = bst_K_ModulContainerLIST;
		this.oSetting = new BS__SETTING(myCONST.VORGANGSART_TRANSPORT);
		
		BST_K_MASK__ComponentMAP oComponentMAP = new BST_K_MASK__ComponentMAP(this,bAddOpenTPA_Button);
		BST_K_MASK				oMask		  = new BST_K_MASK(oComponentMAP);
		
		this.INIT(oComponentMAP,oMask,new Extent(1050),new Extent(750));
	}


	public BST_K_LIST__ModulContainer get_oBST_K_ModulContainerLIST()
	{
		return oBST_K_ModulContainerLIST;
	}


	public BS__SETTING get_oSetting()
	{
		return oSetting;
	}


}
