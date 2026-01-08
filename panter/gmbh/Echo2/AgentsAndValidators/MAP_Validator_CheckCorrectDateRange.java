package panter.gmbh.Echo2.AgentsAndValidators;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.maggie.TestingDate;

public class MAP_Validator_CheckCorrectDateRange extends XX_MAP_ValidBeforeSAVE
{

	private String 		cHASH_KEY_STARTFIELD = null;
	private String 		cHASH_KEY_ENDFIELD = null;
	
	public MAP_Validator_CheckCorrectDateRange(String chash_key_startfield, String chash_key_endfield)
	{
		super();
		cHASH_KEY_STARTFIELD = chash_key_startfield;
		cHASH_KEY_ENDFIELD = chash_key_endfield;
	}

	public MyE2_MessageVector _doValidation( E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
	{
		MyE2_MessageVector vErrorsRueck = new MyE2_MessageVector();
		
		String cInputValueStart = 	(String)oInputMap.get(this.cHASH_KEY_STARTFIELD);
		String cInputValueEnd = 	(String)oInputMap.get(this.cHASH_KEY_ENDFIELD);

		TestingDate oTestStart = new TestingDate(cInputValueStart);
		TestingDate oTestEnd = new TestingDate(cInputValueEnd);
		
		if (!oTestStart.testing() | !oTestEnd.testing())
		{
			// dann wird nichts unternommen, weil die fehler anderswo in der maske abgefangen werden
		}
		else
		{
			if (oTestEnd.get_Calendar().before(oTestStart.get_Calendar()))
				vErrorsRueck.add_MESSAGE(new MyE2_Alarm_Message("Bitte beachten Sie die Datumsreihenfolge der Gültigkeit!"), false);
			
		}
			
		return vErrorsRueck;
	}

}
