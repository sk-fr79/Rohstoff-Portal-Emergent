package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_ActionAgent_Mail_Print;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_Validator_BELEG_PREISE_GEFUELLT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSA_K_LIST_BT_Mail_and_Print extends MyE2_Button
{
	private boolean bMitSummenblock = true;
	
	public BSA_K_LIST_BT_Mail_and_Print(MyString cButtonText, E2_NavigationList navList,BS__SETTING setting, String MODUL_KENNER, boolean preview, boolean MitSummenblock)
	{
		super(cButtonText);
		this.bMitSummenblock = MitSummenblock;
		this.add_IDValidator(new BS_K_Validator_BELEG_PREISE_GEFUELLT(setting.get_oVorgangTableNames()));
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VKOPF_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Das Angebot wurde bereits gelöscht !")));

		this.add_oActionAgent(
				new ownActionAgent(
						new MyE2_String("Drucke / Maile Angebot"), 
						null, 
						"angebot",
						navList, 
						null,
						setting, 
						"id_vkopf_std", 
						MODUL_KENNER, 
						preview));

		this.setToolTipText(new MyE2_String("Drucke/Maile Angebote").CTrans());
		
	}

	private class ownActionAgent extends BS_K_LIST_ActionAgent_Mail_Print
	{
		
		public ownActionAgent(	MyE2_String 		fensterTitel,	
								XX_ActionAgent 		actionAfterPrintOrMail, 
								String 				jasperFileName,
								E2_NavigationList 	navList, 
								Vector<String> 		ds_statt_Navilist,
								BS__SETTING 		setting, 
								String 				parameterNameOfHeadIDField,
								String 				MODUL_KENNER, 
								boolean 			preview)
		{
			super(fensterTitel, actionAfterPrintOrMail, jasperFileName, navList,
					ds_statt_Navilist, setting, parameterNameOfHeadIDField, MODUL_KENNER,
					preview, true);
			
		}

		@Override
		public void complete_JasperHASH(E2_JasperHASH jasperHASH)	throws myException
		{
			//aenderung 20101014: 
			jasperHASH.put("mit_summenblock", "N");

			if (bMitSummenblock)
			{
				jasperHASH.put("mit_summenblock", "Y");
			}
			
		}
		
		
		@Override
		public void manipulate_IDS_To_Print_From_Other_Source(Vector<String> vIDs)	throws myException 
		{
		}

		
	}
	
	

}
