package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MaskSetter_POS_TYP;

/*
 * maske definieren
 */
public class KFIX_P_M_MAP_SETTING_AGENT extends XX_MAP_SettingAgent
{

	public void __doSettings_BEFORE(E2_ComponentMAP oMapVPOS_KON, String STATUS_MASKE) throws myException
	{
		HashMap<String, MyE2IF__Component> hmReal = oMapVPOS_KON.get_REAL_ComponentHashMap();
		
		String cID_VKOPF_KON = null;
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || 
			STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			
			// positionsnummern forsetzen
			cID_VKOPF_KON = ((SQLFieldForRestrictTableRange)oMapVPOS_KON.get_oSQLFieldMAP().get_("ID_VKOPF_KON")).get_cRestictionValue_IN_DB_FORMAT();
			
			
			//aenderung 2010-11-26: Definition der positionsnummern
			String cNextPos = bibDB.EinzelAbfrage("SELECT NVL(MAX(POSITIONSNUMMER)+1,1) FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE NVL(DELETED,'N')='N' AND ID_VKOPF_KON="+cID_VKOPF_KON);
			
			((MyE2IF__DB_Component)hmReal.get("POSITIONSNUMMER")).set_cActualMaskValue(cNextPos);

			
			// maske auf setzen, je nach geladenem positionstyp (auch bei kopie)
			String cActualPostyp = ((MyE2_DB_SelectField)hmReal.get("POSITION_TYP")).get_ActualWert();
			new BS_MaskSetter_POS_TYP(oMapVPOS_KON,cActualPostyp,new Vector<String>());
			
		}
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{

			
			// falls positionstyp <> artikel, dann epreis leer und disabled
			String cPOSTYP = oMapVPOS_KON.get_oInternalSQLResultMAP().get_FormatedValue("POSITION_TYP");
			
			// maske auf den jeweiligen positionstyp setzen
			new BS_MaskSetter_POS_TYP(oMapVPOS_KON,cPOSTYP,new Vector<String>());
			
			//jetzt pruefen, ob der kopf bereits abgeschlossen ist (dann darf man nur noch die Lagermengen und liefertermine veraendern)
			String cID_VPOS_KON = oMapVPOS_KON.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			RECORD_VPOS_KON 	recVPOS_KON = 	new RECORD_VPOS_KON(cID_VPOS_KON);
			RECORD_VKOPF_KON	recVKOPF_KON = 	recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon();

			boolean bKopfClosed = recVKOPF_KON.is_ABGESCHLOSSEN_YES();
			boolean bPosClosed =  recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES();
			
			if (bKopfClosed)
			{
				for (Entry<String, MyE2IF__Component> oEntry: oMapVPOS_KON.entrySet())
				{
					if (! (	oEntry.getKey().equals(KFIX___CONST.HASH_KEY_DAUGHTERTABLE_LAGERLIEFERUNG) ||
							oEntry.getKey().equals(KFIX___CONST.HASH_KEY_DAUGHTERTABLE_LIEFERTERMINE) ||
							oEntry.getKey().equals("BESTELLNUMMER")  ||
							oEntry.getKey().equals("MENGEN_TOLERANZ_PROZENT") ||
							oEntry.getKey().equals("ANZAHL") ||
							oEntry.getKey().equals("EINZELPREIS")))
					{
						oEntry.getValue().EXT().set_bDisabledFromInteractive(true);
					}
				}
			} 
			
			
			if (bPosClosed)    //dann sind auch die unterlisten zu
			{
				for (Entry<String, MyE2IF__Component> oEntry: oMapVPOS_KON.entrySet())
				{
					oEntry.getValue().EXT().set_bDisabledFromInteractive(true);
				}
			}
		}
		
		new BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER("KON",oMapVPOS_KON);
		
	}

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}
	
}
