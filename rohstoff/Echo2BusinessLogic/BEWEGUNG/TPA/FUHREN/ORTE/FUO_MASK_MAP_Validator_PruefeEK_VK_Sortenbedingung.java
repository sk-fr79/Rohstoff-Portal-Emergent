package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class FUO_MASK_MAP_Validator_PruefeEK_VK_Sortenbedingung extends  XX_MAP_ValidBeforeSAVE
{

	@Override
	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,  SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
	{
		MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
		
		//fuhren raussuchen
		RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE( 
			((SQLFieldForRestrictTableRange)oMap.get_oSQLFieldMAP().get_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_VPOS_TPA_FUHRE)).get_cRestictionValue_IN_DB_FORMAT()
			);
		
		//pruefung der ID_ARTIKELBEZ_EK aus der fuhre
		RECORD_ARTIKEL_BEZ  recArtBez_EK = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek();
		
		boolean bMustBeEqual = recFuhre.is_EK_VK_SORTE_LOCK_YES();
		
		if (bMustBeEqual)
		{
			int iArtBez = oMap.get_bdActualDBValue(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_ARTIKEL_BEZ, BigDecimal.ZERO, BigDecimal.ZERO).intValue();
			
			
			if (iArtBez>0)  //sonst sowieso fehler
			{
				RECORD_ARTIKEL_BEZ  recArtBez = new RECORD_ARTIKEL_BEZ(iArtBez);
				
				int iAnzahlStellen = bibALL.get_RECORD_MANDANT().get_ANR1_GLEICHHEIT_FUHRE_STELLEN_bdValue(new BigDecimal(2)).intValue();
				
				if (iAnzahlStellen>10) {iAnzahlStellen=10;}    //fehleingabe abblocken
				
				String ANR1_EK = recArtBez_EK.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+"                        ";
				String ANR1_ORT = recArtBez.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+"                        ";
				
				ANR1_EK = ANR1_EK.substring(0,iAnzahlStellen);
				ANR1_ORT = ANR1_ORT.substring(0,iAnzahlStellen);
				
				if (!ANR1_EK.equals(ANR1_ORT))
				{
					oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("EK-VK - Sortengleichheit in der Fuhre ist an und die Sortencodes unterscheiden sich (Betrachtungslänge: ",true,
																				""+iAnzahlStellen,false,
																				" Stellen)",true)));
				}
			}
		}
		
		return oMVRueck;
	}

}
