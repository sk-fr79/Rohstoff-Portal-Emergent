package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EK_VK_BEZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class FUHREN_PRUEFUNG_KONTRAKT_ZUORDNUNG extends XX_FUHREN_PRUEFUNG
{

	public FUHREN_PRUEFUNG_KONTRAKT_ZUORDNUNG(	RECLIST_VPOS_TPA_FUHRE rec_ListFuhre)
	{
		super(rec_ListFuhre);
	}

	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
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
			oMV.add_MESSAGE(new MyE2_Warning_Message("Die Kontraktzuordnung kann nicht geprüft werden, da sowohl EK-, als auch VK-seitig mehrere Orte vorliegen !"));
		}
		else
		{
		
			for (int i=0;i<reclistFuhre.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE recFuhre = reclistFuhre.get(i);
				
				long lID_VPOS_KON_EK = recFuhre.get_ID_VPOS_KON_EK_lValue(new Long(-1)).longValue();
				long lID_VPOS_KON_VK = recFuhre.get_ID_VPOS_KON_VK_lValue(new Long(-1)).longValue();
				
				if (lID_VPOS_KON_EK>0 && lID_VPOS_KON_VK>0)
				{
					if (!Check_relation_vorhanden(lID_VPOS_KON_EK, lID_VPOS_KON_VK))
					{
						if (recFuhre.is_EK_VK_ZUORD_ZWANG_YES())
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte nur Kontraktpositionen einsetzen, die eine Zuordnung haben (Fuhrenmaske) !"));
						}
						else
						{
							oMV.add_MESSAGE(new MyE2_Warning_Message("Sie haben Kontraktpositionen verwendet, die keine Zuordnung haben !"));
						}
					}
				}
			}
		}
		
		return oMV;
	}

	
	
	private boolean Check_relation_vorhanden(long lID_VPOS_KON_EK, long lID_VPOS_KON_VK) throws myException
	{
		if (lID_VPOS_KON_EK>0 && lID_VPOS_KON_VK>0)
		{
			RECLIST_EK_VK_BEZUG recList = new RECLIST_EK_VK_BEZUG("SELECT * FROM "
													+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+lID_VPOS_KON_EK+" " +
															" AND ID_VPOS_KON_VK="+lID_VPOS_KON_VK);
			return (recList.get_vKeyValues().size()>0);
		}
		return true;
	}

}
