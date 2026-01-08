package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_DATASEARCH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_K_LIST_DATASEARCH extends BS_K_LIST_DATASEARCH
{

	public BSK_K_LIST_DATASEARCH(	E2_NavigationList 	oNaviList, 
									BS__SETTING			SETTING,
									String 				MODULE_KENNER) throws myException 	{
		super(oNaviList, SETTING, MODULE_KENNER," AND NVL("+VKOPF_KON.ist_fixierung.tnfn()+",'N')='N'");
		
		
		//suche nach textliste dazufuegen

		String queryText = "SELECT KK.ID_VKOPF_KON FROM JT_VKOPF_KON KK WHERE " + 
							" (KK.ID_VKOPF_KON IN ( SELECT ID_TABLE FROM JT_TEXT_LISTE WHERE UPPER(TITEL_TEXT) LIKE UPPER('%#WERT#%') OR  UPPER(AUFZAEHL_TEXT) LIKE UPPER('%#WERT#%') OR  UPPER(LANG_TEXT) LIKE UPPER('%#WERT#%'))) "
							+" AND "
							+"(KK.VORGANG_TYP='"+SETTING.get_cBELEGTYP()+"'"+" AND NVL(KK.IST_FIXIERUNG,'N')='N')";
		
		this.add_SearchElement(queryText,new MyE2_String("Suche in Textliste"));

		
	}

}
