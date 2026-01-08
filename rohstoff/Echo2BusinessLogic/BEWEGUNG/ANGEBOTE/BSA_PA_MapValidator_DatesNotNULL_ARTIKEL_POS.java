package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SelectField_POSITIONSTYP;


/*
 * positionen-validator, der dafuer sorgt, dass wenn es eine artikelposition ist,
 * die Felder GUELTIG_VON und GUELTIG_BIS gefuellt werden muessen  
 */
public class BSA_PA_MapValidator_DatesNotNULL_ARTIKEL_POS extends XX_MAP_ValidBeforeSAVE
{

	public BSA_PA_MapValidator_DatesNotNULL_ARTIKEL_POS()
	{
		super();
	}

	
	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		

		// der map-vektor, wo diese map zugehoert
		E2_vCombinedComponentMAPs VMAPS = oMap.get_E2_vCombinedComponentMAPs(); 
		
		// daraus die erste map
		E2_ComponentMAP oMAP_Base = (E2_ComponentMAP)VMAPS.get(0);
		// daraus der POSITIONSTYP-Selektor
		try
		{
			BS_SelectField_POSITIONSTYP oTYP = (BS_SelectField_POSITIONSTYP)oMAP_Base.get__Comp("POSITION_TYP");
			if (oTYP.get_ActualWert().equals(myCONST.VG_POSITION_TYP_ZUSATZTEXT))
			{
				// alles ok
				return oMV;
			}
		}
		catch (myException ex)
		{
			oMV.add_MESSAGE(ex.get_ErrorMessage(), false);
			return oMV;
		}
		
			
		// wenn der beide datumsfelder gefuellt sind, dann ist alles ok, falls fehleingaben, wird das anderweitig geprueft
		String cGueltigVON = (String)oInputMap.get("GUELTIG_VON");
		String cGueltigBIS = (String)oInputMap.get("GUELTIG_BIS");
		if ( bibALL.isEmpty(cGueltigVON) || bibALL.isEmpty(cGueltigBIS))   
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte füllen Sie den Gültigkeitsbereich aus !!"), false);
		}

		return oMV;
	}
	
	
	
}
