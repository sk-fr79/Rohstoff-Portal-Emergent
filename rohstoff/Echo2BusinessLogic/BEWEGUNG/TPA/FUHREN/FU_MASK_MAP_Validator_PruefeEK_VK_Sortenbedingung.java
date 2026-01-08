package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_MAP_Validator_PruefeEK_VK_Sortenbedingung extends  XX_MAP_ValidBeforeSAVE
{

	@Override
	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,  SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
	{
		MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
		
		
		//pruefung der ID_ARTIKELBEZ_EK
		int iArtbezEK = oMap.get_bdActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL_BEZ_EK, BigDecimal.ZERO, BigDecimal.ZERO).intValue();
		int iArtbezVK = oMap.get_bdActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL_BEZ_VK, BigDecimal.ZERO, BigDecimal.ZERO).intValue();
		
		boolean bMustBeEqual = oMap.get_cActualDBValueFormated(RECORD_VPOS_TPA_FUHRE.FIELD__EK_VK_SORTE_LOCK, "N").equals("Y");
		
		if (bMustBeEqual)
		{
			if (iArtbezEK>0 && iArtbezVK>0)
			{
				RECORD_ARTIKEL_BEZ  recArtBez_EK = new RECORD_ARTIKEL_BEZ(iArtbezEK);
				RECORD_ARTIKEL_BEZ  recArtBez_VK = new RECORD_ARTIKEL_BEZ(iArtbezVK);
				
				int iAnzahlStellen = bibALL.get_RECORD_MANDANT().get_ANR1_GLEICHHEIT_FUHRE_STELLEN_bdValue(new BigDecimal(2)).intValue();
				
				if (iAnzahlStellen>10) {iAnzahlStellen=10;}    //fehleingabe abblocken
				
				String ANR1_EK = recArtBez_EK.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+"                        ";
				String ANR1_VK = recArtBez_VK.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+"                        ";
				
				ANR1_EK = ANR1_EK.substring(0,iAnzahlStellen);
				ANR1_VK = ANR1_VK.substring(0,iAnzahlStellen);
				
				if (!ANR1_EK.equals(ANR1_VK))
				{
					oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("EK-VK - Sortengleichheit ist an und die Sortencodes unterscheiden sich (Betrachtungslänge: ",true,
																				""+iAnzahlStellen,false,
																				" Stellen)",true)));
				}
				
				
				if (oMVRueck.get_bIsOK())
				{
					//nach fuhrenorten sehem
					if (oMap.get_bIs_Edit())   //nur dann kann es fuhrenorte geben
					{
						RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
						
						RECLIST_VPOS_TPA_FUHRE_ORT  reclistOrte = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(JT_VPOS_TPA_FUHRE_ORT."+RECORD_VPOS_TPA_FUHRE_ORT.FIELD__DELETED+",'N')='N'", null, true);

						for (int i=0;i<reclistOrte.get_vKeyValues().size();i++)
						{
							RECORD_VPOS_TPA_FUHRE_ORT  recOrt = reclistOrte.get(i);
							
							String ANR1_ORT =  recOrt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+"                        ";
							ANR1_ORT = ANR1_ORT.substring(0,iAnzahlStellen);
							
							if (!ANR1_EK.equals(ANR1_ORT))
							{
								oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("EK-VK - Sortengleichheit ist an und die Sortencodes in einem Zusatzort unterscheiden sich (Betrachtungslänge: ",true,
																							""+iAnzahlStellen,false,
																							" Stellen)",true)));
							}

							if (oMVRueck.get_bHasAlarms())
							{
								break;
							}
						}
						
					}
					
				}
			}
		}
		
		return oMVRueck;
	}

}
