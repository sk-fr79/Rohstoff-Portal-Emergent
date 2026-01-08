package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MaskSetter_POS_TYP;

/*
 * maske definieren
 */
public class BST_P_MASK_MAP_SETTING_AGENT extends XX_MAP_SettingAgent
{

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
		HashMap<String, MyE2IF__Component> hmReal = oMap.get_REAL_ComponentHashMap();
		
		String cID_VKOPF_TPA = null;
		
		
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || 
			STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			
			// positionsnummern forsetzen
			cID_VKOPF_TPA = ((SQLFieldForRestrictTableRange)oMap.get_oSQLFieldMAP().get_("ID_VKOPF_TPA")).get_cRestictionValue_IN_DB_FORMAT();
			
			String cCount = bibDB.EinzelAbfrage("SELECT COUNT(*)+1 FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_VKOPF_TPA="+cID_VKOPF_TPA);
			
			((MyE2IF__DB_Component)hmReal.get("POSITIONSNUMMER")).set_cActualMaskValue(cCount);

			
			// bei neueingabe ist der positionstyp offen
			((MyE2_DB_SelectField)hmReal.get("POSITION_TYP")).EXT().set_bDisabledFromInteractive(false);
			
			
			// maske auf setzen, je nach geladenem positionstyp (auch bei kopie)
			String cActualPostyp = ((MyE2_DB_SelectField)hmReal.get("POSITION_TYP")).get_ActualWert();
			new BS_MaskSetter_POS_TYP(oMap,cActualPostyp,new Vector<String>());
			
		}
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{

			// bei edit ist der positionstyp geschlossen
			((MyE2_DB_SelectField)hmReal.get("POSITION_TYP")).EXT().set_bDisabledFromInteractive(true);

			
			// falls positionstyp <> artikel, dann epreis leer und disabled
			String cPOSTYP = oMap.get_oInternalSQLResultMAP().get_FormatedValue("POSITION_TYP");
			
			// maske auf den jeweiligen positionstyp setzen
			new BS_MaskSetter_POS_TYP(oMap,cPOSTYP,new Vector<String>());
		}
		
		
		new BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER("TPA",oMap);

		
		
		//jetzt alles zusperren, falls der kopf abgeschlossen ist
		if (!oMap.get_bIs_Neueingabe())
		{
			RECORD_VPOS_TPA  recVPos = new RECORD_VPOS_TPA(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			if (recVPos.get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().is_ABGESCHLOSSEN_YES())
			{
				oMap.set_bDisabledFromInteractive(true, null, false, false);
			}
		}

		
		
	}

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}
	
}
