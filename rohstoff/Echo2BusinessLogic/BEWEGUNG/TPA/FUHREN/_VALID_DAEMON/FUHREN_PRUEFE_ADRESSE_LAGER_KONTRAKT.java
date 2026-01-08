package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FUHREN_PRUEFE_ADRESSE_LAGER_KONTRAKT extends XX_FUHREN_PRUEFUNG
{

	public FUHREN_PRUEFE_ADRESSE_LAGER_KONTRAKT(RECORD_VPOS_TPA_FUHRE recVPOS_TPA_FUHRE)
	{
		super(recVPOS_TPA_FUHRE);
	}

	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		RECORD_VPOS_TPA_FUHRE recFuhre = this.get_recFuhre();

		oMV.add_MESSAGE(this.pruefe_Kombination(recFuhre.get_ID_ADRESSE_START_cUF_NN(""),
												recFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN(""),
												recFuhre.get_ID_VPOS_KON_EK_cUF_NN(""),
												"EK"));

		oMV.add_MESSAGE(this.pruefe_Kombination(recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN(""),
												recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(""),
												recFuhre.get_ID_VPOS_KON_VK_cUF_NN(""),
												"VK"));

		
		
		return oMV;
	}

	
	
	private MyE2_MessageVector  pruefe_Kombination(String cID_ADRESSE, String cID_ADRESSE_LAGER, String cID_VPOS_KON, String cEK_VK)
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String cHelp = "Fuhre, Bereich ABHOLORT  :";
		if (cEK_VK.equals("VK"))
		{
			cHelp = "(Fuhre, Bereich LIEFERORT)";
		}
	
		try
		{
			//1. Die id_adressen muessen korrekt sein
			if (S.isEmpty(cID_ADRESSE) || S.isEmpty(cID_ADRESSE_LAGER))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cHelp+"Es MÜSSEN sowohl eine korrekte Adresse als auch eine korrekte Lageradresse angegeben werden !")));
			}
			else
			{
				RECORD_ADRESSE mapAdresse = 		new RECORD_ADRESSE(cID_ADRESSE);
				
				if (mapAdresse.get_ADRESSTYP_lValue(new Long(0)).intValue()!=myCONST.ADRESSTYP_FIRMENINFO)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cHelp+"Sie haben eine Lageradresse in das Feld der Hauptadresse geschrieben !")));
				}
				else
				{
					
					if (!cID_ADRESSE.equals(cID_ADRESSE_LAGER))
					{
						if (!mapAdresse.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get_ID_ADRESSE_LIEFER_hmString_UnFormated("").containsValue(cID_ADRESSE_LAGER))
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cHelp+"Die angegebene Lageradresse gehört nicht zu Hauptadresse !")));
						}
					}

				}
				
			}
			
			//jetzt die kontrakt-pruefung, passt diese zur adresse
			if (oMV.get_bIsOK())
			{
				if (!S.isEmpty(cID_VPOS_KON))
				{
					if (bibALL.get_ID_ADRESS_MANDANT().equals(cID_ADRESSE))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cHelp+"Es darf keinen Kontrakt bei Eigenlager geben !!")));
					}
					else
					{
						
						RECORD_VPOS_KON mapVPOS_KON = new RECORD_VPOS_KON(cID_VPOS_KON);
						
						if (!mapVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_cUF().equals(cID_ADRESSE))
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cHelp+"Der angegebene Kontrakt gehört nicht zur Adresse !!")));
						}
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
