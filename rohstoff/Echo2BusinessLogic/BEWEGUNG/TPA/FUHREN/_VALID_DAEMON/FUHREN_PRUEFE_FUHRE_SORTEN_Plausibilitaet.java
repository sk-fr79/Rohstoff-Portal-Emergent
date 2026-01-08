package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;


/*
 * 2011-10-04:
 * neue plausibilitaet, verhindert ungleiche sorten in angebot/kontrakt/fuhre
 */
public class FUHREN_PRUEFE_FUHRE_SORTEN_Plausibilitaet extends XX_FUHREN_PRUEFUNG
{

	public FUHREN_PRUEFE_FUHRE_SORTEN_Plausibilitaet(RECORD_VPOS_TPA_FUHRE recVPOS_TPA_FUHRE)
	{
		super(recVPOS_TPA_FUHRE);
	}

	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		RECORD_VPOS_TPA_FUHRE recFuhre = this.get_recFuhre();

		//EK
		oMV.add_MESSAGE(this.pruefe_Kombination(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek(),
												recFuhre.get_UP_RECORD_VPOS_STD_id_vpos_std_ek(),
												recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek(),
												recFuhre.get_UP_RECORD_ARTIKEL_id_artikel(),
												true));

		oMV.add_MESSAGE(this.pruefe_Kombination(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk(),
												recFuhre.get_UP_RECORD_VPOS_STD_id_vpos_std_vk(),
												recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk(),
												recFuhre.get_UP_RECORD_ARTIKEL_id_artikel(),
												false));

		
		
		return oMV;
	}

	
	
	//2011-10-25: die pruefung von gleichheit artikel-bez auf gleichheit artikel abgemildert
	private MyE2_MessageVector  pruefe_Kombination(RECORD_VPOS_KON  recKon, RECORD_VPOS_STD recAngebot, RECORD_ARTIKEL_BEZ  recArtbez, RECORD_ARTIKEL recArtikel, boolean bEK)
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String cZusatztext = bEK?"(Ladeseite)":"(Abladeseite)";
		
		if (this.get_recFuhre()!=null)
		{
			if (this.get_recFuhre().get("ID_VPOS_TPA_FUHRE_ORT").get_FieldValueUnformated()!=null)
			{
				cZusatztext = "(Fuhrenort)";
			}
		}
		try
		{
			if (recArtikel == null || recArtbez == null)
			{
				//das wird sowieso nicht gespeichert
				return oMV;
			}
			
			if (recKon!=null && recAngebot != null)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nicht gleichzeitig ein Preis aus einem Kontrakt und einem Angebot kommen ! "+cZusatztext)));
				return oMV;
			}
			
//			if (recKon != null)
//			{
//				if (!recKon.get_ID_ARTIKEL_BEZ_cUF_NN("--").equals(recArtbez.get_ID_ARTIKEL_BEZ_cUF_NN("")))
//				{
//					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Artikelbezeichnung der Fuhre und des Kontrakts müssen übereinstimmen ! "+cZusatztext)));
//					return oMV;
//				}
//			}
//			else if (recAngebot != null)
//			{
//				if (!recAngebot.get_ID_ARTIKEL_BEZ_cUF_NN("--").equals(recArtbez.get_ID_ARTIKEL_BEZ_cUF_NN("")))
//				{
//					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Artikelbezeichnung der Fuhre und des Angebots müssen übereinstimmen ! "+cZusatztext)));
//					return oMV;
//				}
//			}
			//2011-10-25: die pruefung von gleichheit artikel-bez auf gleichheit artikel abgemildert
			if (recKon != null)
			{
				if (!recKon.get_ID_ARTIKEL_cUF_NN("--").equals(recArtbez.get_ID_ARTIKEL_cUF_NN("")))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Sorte der Fuhre und des Kontrakts müssen übereinstimmen ! "+cZusatztext)));
					return oMV;
				}
			}
			else if (recAngebot != null)
			{
				if (!recAngebot.get_ID_ARTIKEL_cUF_NN("--").equals(recArtbez.get_ID_ARTIKEL_cUF_NN("")))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Sorte der Fuhre und des Angebots müssen übereinstimmen ! "+cZusatztext)));
					return oMV;
				}
			}

			
			//auf der ladeseite muessen zusaetzlich noch Artikelbz und artikel stimmen
			if (bEK)
			{
				if (recArtbez != null && recArtikel!=null)
				{
					if (!recArtbez.get_ID_ARTIKEL_cUF_NN("-").equals(recArtikel.get_ID_ARTIKEL_cUF_NN("")))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Artikelbezeichnung der Fuhre und der Haupt-Artikel der Fuhre müssen übereinstimmen ! "+cZusatztext)));
						return oMV;
					}
				}
			}
			
		} 
		catch (myException e)
		{
			e.printStackTrace();
			oMV.add(e.get_ErrorMessage());
		}

		return oMV;
	}
	
}
