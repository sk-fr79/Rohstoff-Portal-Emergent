package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Map.Entry;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MASK_KOPF_WAEHRUNGSSETTER;

/*
 * maske definieren, sorgt dafuer, dass das editieren der positionen bei geschlossenen kontrakten moeglich ist,
 * dort regelt ein eigene MAP-Setting-Agent den rest
 */
public class BSK_K_MASK_MAP_SETTING_AGENT extends XX_MAP_SettingAgent
{

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			//jetzt pruefen, ob der kopf bereits abgeschlossen ist (dann darf man nur noch die Lagermengen und liefertermine veraendern)
			String cID_VKOPF_KON = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			String cKOPF_CLOSED = bibDB.EinzelAbfrage("SELECT NVL(ABGESCHLOSSEN,'N') FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_VKOPF_KON="+cID_VKOPF_KON);
			
			boolean bKopfClosed = false;
			
			boolean SchmeissException = true;
			if (cKOPF_CLOSED.equals("Y") || cKOPF_CLOSED.equals("N"))
			{
				bKopfClosed = (cKOPF_CLOSED.equals("Y"));
				SchmeissException = false;
			}

			if (SchmeissException)
				throw new myException(this,"Error: Testing Mask whether closed creats error !");
			
			
			if (bKopfClosed)
			{
				/*
				 * dann alle inaktiv ausser der tochtertabelle
				 */
				for (Entry<String, MyE2IF__Component> oEntry: oMap.entrySet())
				{
					if (! oEntry.getKey().equals(BSK__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS) )
					{
						oEntry.getValue().EXT().set_bDisabledFromInteractive(true);
					}
				}
			}
			
		}
		
		new BS_MASK_KOPF_WAEHRUNGSSETTER("KON",oMap);
		
	}

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}
	
}
