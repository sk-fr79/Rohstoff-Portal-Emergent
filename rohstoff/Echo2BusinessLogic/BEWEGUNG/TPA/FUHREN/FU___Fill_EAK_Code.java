package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.utils.MyArtikelbezeichung_NG;


//hilfsklasse, um die abwicklung der vorgehensweise bei der eintragung eines eak-codes in die fuhre zu optimieren
public class FU___Fill_EAK_Code 
{

	public FU___Fill_EAK_Code(		MyArtikelbezeichung_NG 				ArtBezEK,
									MASK_COMPONENT_SEARCH_EAK_CODES		oFieldEAK_Code,
									boolean   							bLieferantIstMandant) throws myException 
	{
		super();
		
		if (ArtBezEK.get_ID_EAK_CODE()!=null && !ArtBezEK.get_ID_EAK_CODE().trim().equals("") && !ArtBezEK.get_ID_EAK_CODE().trim().equals("0") && !ArtBezEK.get_ID_EAK_CODE().trim().equals("-1"))
		{
			oFieldEAK_Code.set_cActualMaskValue(ArtBezEK.get_ID_EAK_CODE());
			oFieldEAK_Code.FillLabelWithDBQuery(ArtBezEK.get_ID_EAK_CODE());
		}
		else
		{
			//falls der artikel nicht gelistet ist, dann muss noch geschaut werden, ob im sortenstamm ein AVV-Code ex mandant eingetragen wurde
			if (ArtBezEK.get_bIstProdukt() || ArtBezEK.get_bIstDienstleistung() ||  ArtBezEK.get_bIstEndOfWaste())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Es handelt sich um eine Dienstleistung oder ein Produkt/EndOfWaste, deshalb ohne AVV-Code erlaubt"));
				oFieldEAK_Code.prepare_ContentForNew(false);
			}
			else
			{
				RECORD_ARTIKEL recArtikel = new RECORD_ARTIKEL(ArtBezEK.get_ID_ARTIKEL());

				if (bLieferantIstMandant)
				{
					if (S.isFull(recArtikel.get_ID_EAK_CODE_EX_MANDANT_cUF_NN("")))
					{
						oFieldEAK_Code.set_cActualMaskValue(recArtikel.get_ID_EAK_CODE_EX_MANDANT_cUF_NN(""));
						oFieldEAK_Code.FillLabelWithDBQuery(recArtikel.get_ID_EAK_CODE_EX_MANDANT_cUF_NN(""));
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung! Die Sorte ",true,recArtikel.get_ANR1_cUF_NN("<anr1>"),false," ist hat im Sortenstamm keinen AVV-Code ex Mandant !!",true)));
					}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung! Die Sorte ",true,recArtikel.get_ANR1_cUF_NN("<anr1>"),false,"ist beim Lieferanten nicht gelistet oder hat keinen AVV-Code !!",true)));
				}

			}
		}

		
		
	}

	
	
}
