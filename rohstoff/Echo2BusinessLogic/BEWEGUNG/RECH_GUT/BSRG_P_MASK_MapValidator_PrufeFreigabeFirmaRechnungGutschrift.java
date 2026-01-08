package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


/**
 * 2012-08-07: validierung von positionen: wird eine id_adresse erfasst (erfassung innerhalb der freien positionen)
 *             dann muss geprueft werden, ob die firma die Freigabe fuer rechnungen und gutschriften hat
 * 
 * 
 */
public class BSRG_P_MASK_MapValidator_PrufeFreigabeFirmaRechnungGutschrift extends XX_MAP_ValidBeforeSAVE
{

	public BSRG_P_MASK_MapValidator_PrufeFreigabeFirmaRechnungGutschrift()
	{
		super();
	}


	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		String cPOS_TYP 		= oMap.get_cActualDBValueFormated("POSITION_TYP");
		
		if (cPOS_TYP.equals(myCONST.VG_POSITION_TYP_ARTIKEL))
	 	{
			int iLagerVorzeichen = oMap.get_bdActualDBValue("LAGER_VORZEICHEN", BigDecimal.ZERO,BigDecimal.ZERO).intValue();
			
			long lID_ADRESSE = oMap.get_LActualDBValue("ID_ADRESSE", 0l, 0l);
			
			if (lID_ADRESSE>0)
			{
				RECORD_ADRESSE recADRESSE = new RECORD_ADRESSE(lID_ADRESSE);
				if (iLagerVorzeichen==1 && recADRESSE.is_WARENEINGANG_SPERREN_YES())
				{
					vRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Adresse ",true,recADRESSE.get_NAME1_cUF_NN("<name1>"),false," hat einen Sperrvermerk bei Wareneingang !",true)));
				}
				else if (iLagerVorzeichen==-1 && recADRESSE.is_WARENAUSGANG_SPERREN_YES())
				{
					vRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Adresse ",true,recADRESSE.get_NAME1_cUF_NN("<name1>"),false," hat einen Sperrvermerk bei Warenausgang !",true)));
				}
			}
			
			return vRueck;
		}
		
		return vRueck;
	}

	
	
}
