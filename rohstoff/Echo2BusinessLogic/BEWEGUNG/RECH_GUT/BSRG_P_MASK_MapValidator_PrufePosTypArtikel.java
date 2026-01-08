package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


/*
 * positionen-validator, der dafuer sorgt, dass 
 * positionen vom typ text den lagerfaktor 0 bekommen
 */
public class BSRG_P_MASK_MapValidator_PrufePosTypArtikel extends XX_MAP_ValidBeforeSAVE
{

	public BSRG_P_MASK_MapValidator_PrufePosTypArtikel()
	{
		super();
	}


	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		String cPOS_TYP 		= oMap.get_cActualDBValueFormated("POSITION_TYP");
		
		if (!cPOS_TYP.equals(myCONST.VG_POSITION_TYP_ARTIKEL))
		{
			vRueck.add(new MyE2_Alarm_Message(new MyE2_String("Nur Positionstyp ARTIKEL ist zugelassen")));
			return vRueck;
		}
		
		return vRueck;
	}

	
	
}
