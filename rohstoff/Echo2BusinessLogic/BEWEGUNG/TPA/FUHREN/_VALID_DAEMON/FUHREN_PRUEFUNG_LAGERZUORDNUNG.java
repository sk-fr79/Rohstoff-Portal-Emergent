package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FUHREN_PRUEFUNG_LAGERZUORDNUNG extends XX_FUHREN_PRUEFUNG
{

	public FUHREN_PRUEFUNG_LAGERZUORDNUNG(RECLIST_VPOS_TPA_FUHRE recListFuhre)
	{
		super(recListFuhre);
	}

	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		//zuerst feststellen ob mehrfache links/rechtsseitige existieren
		RECLIST_VPOS_TPA_FUHRE reclistFuhre = this.get_recListFuhre();
		
		int iCountEK_Orte = 0;
		int iCountVK_Orte = 0;
		
		for (int i=0;i<reclistFuhre.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_TPA_FUHRE recFuhre = reclistFuhre.get(i);
			
			if (recFuhre.is_DELETED_NO())
			{
				if (recFuhre.get_UnFormatedValue("DEF_QUELLE_ZIEL", "").equals("EK"))
				{
					iCountEK_Orte++;
				}
				if (recFuhre.get_UnFormatedValue("DEF_QUELLE_ZIEL", "").equals("VK"))
				{
					iCountVK_Orte++;
				}
			}
		}

		if (iCountEK_Orte >0 && iCountVK_Orte>0)
		{
			oMV.add_MESSAGE(new MyE2_Warning_Message("Die Lagerzuordnung kann nicht geprüft werden, da sowohl EK-, als auch VK-seitig mehrere Orte vorliegen !"));
		}
		else
		{
		
			for (int i=0;i<reclistFuhre.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE recFuhre = reclistFuhre.get(i);
				
				long lID_VPOS_KON_EK = 		recFuhre.get_ID_VPOS_KON_EK_lValue(new Long(-1));
				long lID_VPOS_KON_VK = 		recFuhre.get_ID_VPOS_KON_EK_lValue(new Long(-1));
				long lID_ADRESSE_START = 	recFuhre.get_ID_ADRESSE_START_lValue(new Long(-1));
				long lID_ADRESSE_ZIEL = 	recFuhre.get_ID_ADRESSE_ZIEL_lValue(new Long(-1));
				long lID_ADRESSE_LAGER_START = 	recFuhre.get_ID_ADRESSE_LAGER_START_lValue(new Long(-1));
				long lID_ADRESSE_LAGER_ZIEL = 	recFuhre.get_ID_ADRESSE_LAGER_ZIEL_lValue(new Long(-1));
				

				//jetzt die moeglichen kombinationen pruefen
				if (lID_VPOS_KON_EK>0 && lID_ADRESSE_ZIEL==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-1))) 
				{
					if (new RECLIST_VPOS_KON_LAGER(	"SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON="+lID_VPOS_KON_EK+
													" AND ID_ADRESSE_LAGER="+lID_ADRESSE_LAGER_ZIEL).get_vKeyValues().size()==0)
					{
						MyE2_String cHelp = new MyE2_String("Bei der EK-Kontraktposition ",true,""+lID_VPOS_KON_EK,false," ist keine Menge für das Lager ",true,""+lID_ADRESSE_LAGER_ZIEL,false," vorgesehen !",true);
						oMV.add_MESSAGE(new MyE2_Warning_Message(cHelp));
					}
				}
				
				if (lID_VPOS_KON_VK>0 && lID_ADRESSE_START==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-1))) 
				{
					if (new RECLIST_VPOS_KON_LAGER(	"SELECT * FROM "+bibE2.cTO()+".ID_VPOS_KON_LAGER WHERE ID_VPOS_KON="+lID_VPOS_KON_VK+
													" AND ID_ADRESSE_LAGER="+lID_ADRESSE_LAGER_START).get_vKeyValues().size()==0)
					{
						MyE2_String cHelp = new MyE2_String("Ber der VK-Kontraktposition ",true,""+lID_VPOS_KON_VK,false," ist keine Menge aus dem Lager ",true,""+lID_ADRESSE_LAGER_START,false," vorgesehen !",true);
						oMV.add_MESSAGE(new MyE2_Warning_Message(cHelp));
					}
				}
			}
		}
		
		
		return oMV;
	}

}
