package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FUHREN_PRUEFUNG_WARENAUSGANG_NUR_MIT_VK_KON extends XX_FUHREN_PRUEFUNG
{

	public FUHREN_PRUEFUNG_WARENAUSGANG_NUR_MIT_VK_KON(RECORD_VPOS_TPA_FUHRE recFuhre)
	{
		super(recFuhre);
	}

	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		RECORD_VPOS_TPA_FUHRE  recFuhre = this.get_recFuhre(); 

		try
		{
			
			String cID_ADRESSE_ZIEL=			recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("");
			String cID_ADRESSE_FREMDAUFTRAG=	recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("");
			String cID_VPOS_KON_VK=				recFuhre.get_ID_VPOS_KON_VK_cUF_NN("");
			
			boolean bSchalterOhneAbrechnung =	recFuhre.is_OHNE_ABRECHNUNG_YES();
			boolean bSchalterOhneVKKON =		recFuhre.is_KEIN_KONTRAKT_NOETIG_YES();
			boolean bSchalterAlteFuhre =		recFuhre.is_ALT_WIRD_NICHT_GEBUCHT_YES();
			boolean bWareneingang =     		bibALL.ReplaceTeilString(cID_ADRESSE_ZIEL,".","").equals(bibALL.get_ID_ADRESS_MANDANT());
			
			boolean bHatVKKon_InMaske = 		!bibALL.isEmpty(cID_VPOS_KON_VK);

			//fremdauftrag, wenn fremdadresse in der maske steht
			boolean bIstFremdauftrag =   		S.isFull(recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN(""));
			if (recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("").equals(bibALL.get_ID_ADRESS_MANDANT()))
			{
				bIstFremdauftrag = false;
			}
			
			//falls die fuhre als alt gekennzeichnet ist, dann wird kein VK-Kontrakt erlaubt
			if (bSchalterAlteFuhre)
			{
				if (bHatVKKon_InMaske)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Bei einer ALTEN Fuhre darf keine VK-Kontrakt-Pos angegeben sein !"), false);
					return oMV;
				}
			}
			
			
			if (S.isFull(cID_ADRESSE_FREMDAUFTRAG))
			{
				if (bHatVKKon_InMaske)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Bei einer Fremdauftragsfuhre darf keine VK-Kontrakt-Pos angegeben sein !"), false);
					return oMV;
				}
			}
			
			
			
			
			
			if (bWareneingang)
			{
				if (bHatVKKon_InMaske)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Bei einer Wareneingangsfuhre darf keine VK-Kontrakt-Pos angegeben sein !"), false);
					return oMV;
				}
			}
			else
			{
				if ((bSchalterOhneAbrechnung||bIstFremdauftrag) && bHatVKKon_InMaske)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Ist Schalter <keine Mengen-Transport-Position>/oder Fremdauftrag gesetzt (d.h. keine Warenabrechnung), dann darf keine VK-Kontrakt-Pos angegeben sein !"), false);
					return oMV;
				}
				
				if (!bSchalterOhneAbrechnung && ! (bHatVKKon_InMaske || bSchalterOhneVKKON || bIstFremdauftrag || bSchalterAlteFuhre))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Eine abrechenbare Warenausgangs-Transport-Position MUSS zu einem VK-Kontrakt zugeordnet werden !"), false);
					return oMV;
				}

				if (bSchalterOhneVKKON && bHatVKKon_InMaske)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Ist der Schalter <Kein VK-Kontrakt> gesetzt, dann darf kein VK-Kontrakt angegeben sein !"), false);
					return oMV;
				}

				
			}

		}
		catch (myException ex)
		{
			ex.printStackTrace();
			oMV.add_MESSAGE(ex.get_ErrorMessage(), false);
		}

		return oMV;
	}

}
