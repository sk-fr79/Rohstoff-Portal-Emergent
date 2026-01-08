package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Map.Entry;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON_TRAKT;
import panter.gmbh.indep.exceptions.myException;

/*
 * maske definieren
 */
public class BSK_PK_MASK_MAP_SETTING_AGENT extends XX_MAP_SettingAgent
{

	public void __doSettings_BEFORE(E2_ComponentMAP oMapVPOS_KON_TRAKT, String STATUS_MASKE) throws myException
	{
		//zuerst die Haupt-Maske besorgen
		BSK_P_MASK__ComponentMAP  oMapVPOS_KON = (BSK_P_MASK__ComponentMAP)oMapVPOS_KON_TRAKT.get_E2_vCombinedComponentMAPs().get(0); 
		
		//wenn die position geschlossen ist, dann sind die felder von JT_VPOS_KON_TRAKT geschlossen.
		//bei gedrucktem Belegkopf sind sie noch editierbar
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			//jetzt pruefen, ob der kopf bereits abgeschlossen ist (dann darf man nur noch die Lagermengen und liefertermine veraendern)
			String cID_VPOS_KON = oMapVPOS_KON.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			RECORD_VPOS_KON 		recVPOS_KON =	 	new RECORD_VPOS_KON(cID_VPOS_KON);
			RECORD_VPOS_KON_TRAKT 	recVPOS_KON_TRAKT = recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0);
			
			//wenn die position geschlossen ist, dann kann nichts mehr in diesen feldern geaendert werden  
			if (recVPOS_KON_TRAKT.is_ABGESCHLOSSEN_YES())
			{
				for (Entry<String, MyE2IF__Component> oEntry: oMapVPOS_KON_TRAKT.entrySet())
				{
					oEntry.getValue().EXT().set_bDisabledFromInteractive(true);
				}
			}
		}
	}

	
	
	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}
	
}
