package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;


/*
 * positionen-validator, der dafuer sorgt, dass 
 * positionen vom typ text den lagerfaktor 0 bekommen
 */
public class BSRG_P_MASK_MapValidatorSettingsBeforeSave extends XX_MAP_ValidBeforeSAVE
{

	public BSRG_P_MASK_MapValidatorSettingsBeforeSave()
	{
		super();
	}


	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
	
		
		if (!oInputMap.get("POSITION_TYP").equals(myCONST.VG_POSITION_TYP_ARTIKEL))
			oInputMap.put("LAGER_VORZEICHEN","0");

		
		
		
		return vRueck;
	}

	
	
}
