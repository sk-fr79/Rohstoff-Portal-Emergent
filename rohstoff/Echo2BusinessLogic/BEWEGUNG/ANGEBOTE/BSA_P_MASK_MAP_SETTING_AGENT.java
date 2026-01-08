package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MaskSetter_POS_TYP;

/*
 * maske definieren
 */
public class BSA_P_MASK_MAP_SETTING_AGENT extends XX_MAP_SettingAgent
{

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
		HashMap<String, MyE2IF__Component> hmReal = oMap.get_REAL_ComponentHashMap();
		
		
		String cID_VKOPF_STD = null;
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || 
			STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{

				
			// positionsnummern forsetzen
			cID_VKOPF_STD = ((SQLFieldForRestrictTableRange)oMap.get_oSQLFieldMAP().get_("ID_VKOPF_STD")).get_cRestictionValue_IN_DB_FORMAT();
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String cCount = oDB.EinzelAbfrage("SELECT COUNT(*)+1 FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_VKOPF_STD="+cID_VKOPF_STD);
			bibALL.destroy_myDBToolBox(oDB);
			
			((MyE2IF__DB_Component)hmReal.get("POSITIONSNUMMER")).set_cActualMaskValue(cCount);

			// maske auf setzen, je nach geladenem positionstyp (auch bei kopie)
			String cActualPostyp = ((MyE2_DB_SelectField)hmReal.get("POSITION_TYP")).get_ActualWert();
			new BS_MaskSetter_POS_TYP(oMap,cActualPostyp,
					bibALL.get_Vector(BSA__CONST.HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH,BSA__CONST.HASH_KEY_POSITION_MASK_SET_NEXT_MONTH,null,null));
			this.makeZusatzSettingsFuerWurmfortsatz(oMap,cActualPostyp);
			
		}
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			// falls positionstyp <> artikel, dann epreis leer und disabled
			String cPOSTYP = oMap.get_oInternalSQLResultMAP().get_FormatedValue("POSITION_TYP");
			
			// maske auf den jeweiligen positionstyp setzen
			new BS_MaskSetter_POS_TYP(oMap,cPOSTYP,
					bibALL.get_Vector(BSA__CONST.HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH,BSA__CONST.HASH_KEY_POSITION_MASK_SET_NEXT_MONTH,null,null));
			this.makeZusatzSettingsFuerWurmfortsatz(oMap,cPOSTYP);
		}
		
		new BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER("STD",oMap);
		
	}
	
	
	private void makeZusatzSettingsFuerWurmfortsatz(E2_ComponentMAP oMap,String cActualPostyp)
	{
		// zuerst nachsehen, ob es aus der maske kommt
		E2_vCombinedComponentMAPs vMAPS = oMap.get_E2_vCombinedComponentMAPs();
		if (vMAPS != null)
		{
			for (int i=0;i<vMAPS.size();i++)
			{
				if (vMAPS.get(i) instanceof BSA_PA_MASK_ComponentMAP)
				{
					
					BSA_PA_MASK_ComponentMAP oZusatzMAP = (BSA_PA_MASK_ComponentMAP)vMAPS.get(i);
					
					if (cActualPostyp.equals(myCONST.VG_POSITION_TYP_ZUSATZTEXT))
					{
						((MyE2IF__Component) oZusatzMAP.get("GUELTIG_VON")).EXT().set_bDisabledFromInteractive(true);
						((MyE2IF__Component) oZusatzMAP.get("GUELTIG_BIS")).EXT().set_bDisabledFromInteractive(true);
					}
					else
					{
						((MyE2IF__Component) oZusatzMAP.get("GUELTIG_VON")).EXT().set_bDisabledFromInteractive(false);
						((MyE2IF__Component) oZusatzMAP.get("GUELTIG_BIS")).EXT().set_bDisabledFromInteractive(false);
					}
					
					break;
				}
				
				
			}
		}
		
		
	}
	

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}
	
}
