package rohstoff.Echo2BusinessLogic.MAIL_INBOX;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_INBOX;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class MAIL_INBOX_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		String sText = oUsedResultMAP.get_UnFormatedValue(RECORD_EMAIL_INBOX.FIELD__MESSAGE_TEXT);
		
		// Gesamte Mail als Tooltip anzeigen
		MyE2_DB_TextArea o_MessageText= (MyE2_DB_TextArea) oMAP.get__Comp_From_RealComponents(RECORD_EMAIL_INBOX.FIELD__MESSAGE_TEXT);
		o_MessageText.setToolTipText(sText);

		if (sText.length() > 200){
			sText = sText.substring(0, 200);
			o_MessageText.setText(sText + new MyE2_String("...<Abgeschnitten>").CTrans() );
		}

		// Button für die Anhänge
		MyE2_Row_EveryTimeEnabled oRowAnhaenge  = (MyE2_Row_EveryTimeEnabled) oMAP.get__Comp_From_RealComponents(MAIL_INBOX_Const.ROW_SHOW_ANHANG_LISTE);
		if (oRowAnhaenge != null){
			oRowAnhaenge.addAfterRemoveAll(new E2_ButtonUpDown_NavigationList_to_Archiv( oMAP, oMAP.get_oNavigationList_This_Belongs_to().get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE(),
														Archiver_CONST.MEDIENKENNER.INBOX_ANHANG.get_DB_WERT()) , E2_INSETS.I_2_0_2_0);
		}
		
		
		
		// LÖSCHMARKIERUNG
		if (S.NN(oUsedResultMAP.get_FormatedValue("DELETED")).equals("Y")) {
			oMAP.set_AllComponentsAsDeleted();
		}
		
		
	}

}
