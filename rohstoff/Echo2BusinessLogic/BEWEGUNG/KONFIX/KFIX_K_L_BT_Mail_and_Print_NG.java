package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class KFIX_K_L_BT_Mail_and_Print_NG extends MyE2_Button
{


	public KFIX_K_L_BT_Mail_and_Print_NG(MyString cButtonText, E2_NavigationList navList,BS__SETTING setting, String MODUL_KENNER, boolean preview)
	{
		super(cButtonText);

		this.add_GlobalValidator(new KFIX_K_M_Selection_Validator(navList));

		this.add_IDValidator(new KFIX_K__Validator_BELEG_MENGEN_UND_PREISE_GEFUELLT_NG(setting.get_oVorgangTableNames()));
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VKOPF_KON","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Der Kontrakt wurde bereits gelöscht !")));


		this.add_oActionAgent(
				new KFIX_K_L_ActionAgent_Mail_Print_NG(
						new MyE2_String("Drucke / Maile Kontrakt"), 
						null, 
						navList, 
						null,
						setting, 
						"id_vkopf_kon", 
						MODUL_KENNER, 
						preview,
						true)
				);

		this.setToolTipText(new MyE2_String("Drucke oder Maile einen Kontrakt !").CTrans());

	}
}
