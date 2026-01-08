package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_Daughter_PrintProtokoll;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;



public class BSA_K_MASK_COMP_Daughter_PrintProtokoll extends BS_K_Daughter_PrintProtokoll
{
	
	public BSA_K_MASK_COMP_Daughter_PrintProtokoll(	SQLFieldMAP 			fieldMAP, 
											E2_ComponentMAP			ocomponentMAP_from_Mother,
											BS__SETTING   			oSetting) throws myException
	{
		super(fieldMAP,ocomponentMAP_from_Mother,oSetting);
	}
	
}
