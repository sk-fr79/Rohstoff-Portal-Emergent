package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSA_P_MASK__ModulContainer extends BS_P_MASK_ModulContainer 
{
	
	private BS__SETTING						SETTING = null;
	private	 BSA_P_MASK__ComponentMAP 		oComponentMAP_Mask = null;
	
	
	public BSA_P_MASK__ModulContainer(BS__SETTING osetting)  throws myException
	{
		super(osetting.get_cMODULCONTAINER_POS_MASK_IDENTIFIER());
		this.SETTING = osetting;
		
		this.oComponentMAP_Mask = 		new BSA_P_MASK__ComponentMAP(this.SETTING);
		BSA_P_MASK  oMask		= 		new BSA_P_MASK(oComponentMAP_Mask);
		
		this.INIT(oComponentMAP_Mask,oMask,new Extent(900),new Extent(600));
		this.add_SubTableComponentMAP(this.oComponentMAP_Mask.get_oComponentMAP_ZusatzfelderAngebot());
		
	}



	public BS__SETTING get_SETTING()
	{
		return SETTING;
	}


	public BSA_P_MASK__ComponentMAP get_oComponentMAP_Mask() 
	{
		return oComponentMAP_Mask;
	}


	public E2_ComponentMAP get_ComponentMapMask() 
	{
		return this.oComponentMAP_Mask;
	}


	
}
