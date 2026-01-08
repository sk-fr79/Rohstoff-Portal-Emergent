package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_P_MASK__ModulContainer extends BS_P_MASK_ModulContainer 
{
	
	private BS__SETTING					SETTING = 						null;
	private	BSK_P_MASK__ComponentMAP 	oComponentMAP_Mask = 			null;

	
	
	public BSK_P_MASK__ModulContainer(BS__SETTING osetting)  throws myException
	{
		super(osetting.get_cMODULCONTAINER_POS_MASK_IDENTIFIER());
		this.SETTING = osetting;
		
		this.oComponentMAP_Mask = 		new BSK_P_MASK__ComponentMAP(osetting);
		BSK_P_MASK	oMask		  = 	new BSK_P_MASK(oComponentMAP_Mask,this.SETTING);
		
		this.INIT(oComponentMAP_Mask,oMask,new Extent(900),new Extent(650));
		
		this.add_SubTableComponentMAP(this.oComponentMAP_Mask.get_oVPOS_KON_TRAKT_ComponentMAP());
		
		this.set_iVerticalOffsetForTabbedPane(new Integer(190));
	}



	public BSK_P_MASK__ComponentMAP get_oComponentMAP_Mask()
	{
		return oComponentMAP_Mask;
	}
	
	
	
	public BS__SETTING get_SETTING()
	{
		return this.SETTING;
	}




	public E2_ComponentMAP get_ComponentMapMask() 
	{
		return this.oComponentMAP_Mask;
	}





	

	
}
