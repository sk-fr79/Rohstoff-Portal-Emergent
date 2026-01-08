package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BST_P_MASK__ModulContainer extends BS_P_MASK_ModulContainer 
{
	
	private	BST_P_MASK__ComponentMAP 	oComponentMAP_Mask = null;
	private BST_K_MASK__ModulContainer 	oContainingModulContainerMask = null;
	private BS__SETTING			  		oSetting = null;

	
	public BST_P_MASK__ModulContainer(BST_K_MASK__ModulContainer ContainingModulContainerMask,boolean bAddOpenTPA_Button)  throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_TPA_POS_MASK);
		
		this.oSetting = new BS__SETTING(myCONST.VORGANGSART_TRANSPORT);
		
		this.oContainingModulContainerMask = ContainingModulContainerMask;
		
		this.oComponentMAP_Mask = 		new BST_P_MASK__ComponentMAP(bAddOpenTPA_Button);
		BST_P_MASK	oMask		  = 	new BST_P_MASK(oComponentMAP_Mask);
		
		this.INIT(oComponentMAP_Mask,oMask,new Extent(1000),new Extent(700));
		this.add_SubTableComponentMAP(this.oComponentMAP_Mask.get_oFU_MASK_ComponentMAP());
		
		this.set_iVerticalOffsetForTabbedPane(170);
	}



	public BS__SETTING get_SETTING()
	{
		return this.oSetting;
	}


	public E2_ComponentMAP get_ComponentMapMask() 
	{
		return this.oComponentMAP_Mask;
	}

	
	public BST_K_MASK__ModulContainer get_K_MASK__ModulContainer() 
	{
		return this.oContainingModulContainerMask;
	}

	
}
