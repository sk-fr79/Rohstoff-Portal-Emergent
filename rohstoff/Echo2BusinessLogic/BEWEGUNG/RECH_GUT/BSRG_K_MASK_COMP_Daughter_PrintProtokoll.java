package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_Daughter_PrintProtokoll;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;



public class BSRG_K_MASK_COMP_Daughter_PrintProtokoll extends BS_K_Daughter_PrintProtokoll
{
	
	public BSRG_K_MASK_COMP_Daughter_PrintProtokoll(	SQLFieldMAP 			fieldMAP, 
											E2_ComponentMAP			ocomponentMAP_from_Mother,
											BS__SETTING   			oSetting) throws myException
	{
		super(fieldMAP,ocomponentMAP_from_Mother,oSetting);
	}
	
}
