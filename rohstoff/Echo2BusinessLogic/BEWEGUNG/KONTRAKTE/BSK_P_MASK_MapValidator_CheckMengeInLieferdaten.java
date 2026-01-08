package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


public class BSK_P_MASK_MapValidator_CheckMengeInLieferdaten extends XX_MAP_ValidBeforeSAVE
{

	/* Prueft die kontraktmenge gegen die mengen, die in den lieferterminen gesetzt wurden
	 * @see utilities.myEcho2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE#_doValidation(utilities.myEcho2.ListAndMask.E2_ComponentMAP, utilities.indep.dataTools.SQLMaskInputMAP, java.lang.String)
	 */
	public MyE2_MessageVector _doValidation(E2_ComponentMAP map, SQLMaskInputMAP inputMap, String cstatus_maske) throws myException
	{
		MyE2_MessageVector oMVRueck = new MyE2_MessageVector();
		
		
		//zuerst die tochterkomponente beschaffen
		BSK_P_MASK_Daughter_Liefertermin  oLiefertermine = (BSK_P_MASK_Daughter_Liefertermin)map.get__Comp(BSK__CONST.HASH_KEY_DAUGHTERTABLE_LIEFERTERMINE);
		
		Vector<E2_ComponentMAP>  vMaps = new Vector<E2_ComponentMAP>(); 
		vMaps.addAll(oLiefertermine.get_oNavigationList().get_vComponentMAPS());
		vMaps.addAll(oLiefertermine.get_oNavigationList().get_vComponentMAPS_NEW());
		
		double dSummeInLieferterminen = 0;
		for (E2_ComponentMAP oTestMap: vMaps)
		{
			if(!oLiefertermine.get_bMapIsMarkedToDelete(oTestMap))
			{
				dSummeInLieferterminen = dSummeInLieferterminen + oTestMap.get_DActualDBValue("ANZAHL", true, true, new Double(0)).doubleValue();
			}
		}

		
		double dMengeInPosition = map.get_DActualDBValue("ANZAHL", true, true, new Double(0)).doubleValue();
		
		
		if (dSummeInLieferterminen>dMengeInPosition)
		{
			oMVRueck.add(new MyE2_Alarm_Message(new MyE2_String("Die Liefermenge in den Lieferterminen ist grösser als die Kontraktmenge !")));
		}
		
		
		return oMVRueck;
	}

}
