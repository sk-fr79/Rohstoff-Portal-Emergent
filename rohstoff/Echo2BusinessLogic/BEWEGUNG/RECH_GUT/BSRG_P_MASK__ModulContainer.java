package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_P_MASK__ModulContainer extends BS_P_MASK_ModulContainer 
{
	
	private	BSRG_P_MASK__ComponentMAP 		oComponentMAP_Mask = 			null;

	private BS__SETTING   					Setting = null;
	
	public BSRG_P_MASK__ModulContainer(	BS__SETTING   				oSetting)  throws myException
	{
		super(oSetting.get_cMODULCONTAINER_POS_MASK_IDENTIFIER());
		
		this.Setting = oSetting; 
		
		this.oComponentMAP_Mask = 		new BSRG_P_MASK__ComponentMAP(oSetting,this);
		BSRG_P_MASK  oMask		= 		new BSRG_P_MASK(oComponentMAP_Mask,oSetting);
		
		this.INIT(oComponentMAP_Mask,oMask,new Extent(900),new Extent(600));
	}



	public BSRG_P_MASK__ComponentMAP get_oComponentMAP_Mask()
	{
		return oComponentMAP_Mask;
	}


	public BS__SETTING get_SETTING()
	{
		return this.Setting;
	}




	public E2_ComponentMAP get_ComponentMapMask() 
	{
		return this.oComponentMAP_Mask;
	}





	
}
