package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BS_Sel_Zahlungsbedingungen extends MyE2_DB_SelectField
{

	public BS_Sel_Zahlungsbedingungen(SQLFieldMAP oSQLFieldMAP) throws myException
	{
		super(	oSQLFieldMAP.get_("ID_ZAHLUNGSBEDINGUNGEN"),
				new E2_DropDownSettings( "JT_ZAHLUNGSBEDINGUNGEN", "KURZBEZEICHNUNG", "ID_ZAHLUNGSBEDINGUNGEN", "IST_STANDARD", true).getDD(),false);
		
		this.add_oActionAgent(new ownActionAgent());
		
	}


	/*
	 * action-agent, fuellt das textfeld mit dem wert der in der zahlungsbedingungs-tabelle steht
	 */
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_ComponentMAP  oMap = BS_Sel_Zahlungsbedingungen.this.EXT().get_oComponentMAP();
			
			BS_Sel_Zahlungsbedingungen.LeseID_Zahlungsbedingung_und_setze_korrellierendeFelder(oMap);
			
//			MyE2_DB_TextField  oTFZB = (MyE2_DB_TextField)oMap.get__Comp("ZAHLUNGSBEDINGUNGEN");
//			MyE2_DB_TextField  oTFZAHLTAGE = (MyE2_DB_TextField)oMap.get__Comp("ZAHLTAGE");
//			MyE2_DB_TextField  oTFFIXTAG = (MyE2_DB_TextField)oMap.get__Comp("FIXTAG");
//			MyE2_DB_TextField  oTFFIXMONAT = (MyE2_DB_TextField)oMap.get__Comp("FIXMONAT");
//			MyE2_DB_TextField  oTF_SKONTO_PROZENT = (MyE2_DB_TextField)oMap.get__Comp("SKONTO_PROZENT");
//			
//			Long lID_Zahlungsbed = oMap.get_LActualDBValue("ID_ZAHLUNGSBEDINGUNGEN", true, true, null);
//			
//			
//			if (lID_Zahlungsbed == null)
//			{
//				oTFZB.setText("");
//				//2012-05-16: alle felder muessen geleert werden
//				oTFZAHLTAGE.setText("");
//				oTFFIXTAG.setText("");
//				oTFFIXMONAT.setText("");
//				oTF_SKONTO_PROZENT.setText("");
//				//2012-05-16:
//				
//			}
//			else
//			{
//				RECORD_ZAHLUNGSBEDINGUNGEN oMZ = new RECORD_ZAHLUNGSBEDINGUNGEN(lID_Zahlungsbed);
//				
//				oTFZB.setText(oMZ.get_BEZEICHNUNG_cF_NN(""));
//				oTFZAHLTAGE.setText(oMZ.get_ZAHLTAGE_cF_NN(""));
//				oTFFIXTAG.setText(oMZ.get_FIXTAG_cF_NN(""));
//				oTFFIXMONAT.setText(oMZ.get_FIXMONAT_cF_NN(""));
//				oTF_SKONTO_PROZENT.setText(oMZ.get_SKONTO_PROZENT_cF_NN(""));
//			}
		}
	}
	
	
	
	
	//2012-05-16: das setzen der zahlungsbedinungsfelder als statische methode in allen positionssaetzen nutzbar
	public static void LeseID_Zahlungsbedingung_und_setze_korrellierendeFelder(E2_ComponentMAP  oMap) throws myException
	{
		MyE2_DB_TextField  oTFZB = (MyE2_DB_TextField)oMap.get__Comp("ZAHLUNGSBEDINGUNGEN");
		MyE2_DB_TextField  oTFZAHLTAGE = (MyE2_DB_TextField)oMap.get__Comp("ZAHLTAGE");
		MyE2_DB_TextField  oTFFIXTAG = (MyE2_DB_TextField)oMap.get__Comp("FIXTAG");
		MyE2_DB_TextField  oTFFIXMONAT = (MyE2_DB_TextField)oMap.get__Comp("FIXMONAT");
		MyE2_DB_TextField  oTF_SKONTO_PROZENT = (MyE2_DB_TextField)oMap.get__Comp("SKONTO_PROZENT");
		
		Long lID_Zahlungsbed = oMap.get_LActualDBValue("ID_ZAHLUNGSBEDINGUNGEN", true, true, null);
		
		if (lID_Zahlungsbed == null)
		{
			oTFZB.setText("");
			//2012-05-16: alle felder muessen geleert werden
			oTFZAHLTAGE.setText("");
			oTFFIXTAG.setText("");
			oTFFIXMONAT.setText("");
			oTF_SKONTO_PROZENT.setText("");
			//2012-05-16:
			
		}
		else
		{
			RECORD_ZAHLUNGSBEDINGUNGEN oMZ = new RECORD_ZAHLUNGSBEDINGUNGEN(lID_Zahlungsbed);
			
			oTFZB.setText(oMZ.get_BEZEICHNUNG_cF_NN(""));
			oTFZAHLTAGE.setText(oMZ.get_ZAHLTAGE_cF_NN(""));
			oTFFIXTAG.setText(oMZ.get_FIXTAG_cF_NN(""));
			oTFFIXMONAT.setText(oMZ.get_FIXMONAT_cF_NN(""));
			oTF_SKONTO_PROZENT.setText(oMZ.get_SKONTO_PROZENT_cF_NN(""));
		}
	}
	
}
