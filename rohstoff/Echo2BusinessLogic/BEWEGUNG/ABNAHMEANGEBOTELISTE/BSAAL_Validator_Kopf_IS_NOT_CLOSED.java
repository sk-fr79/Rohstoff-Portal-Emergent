package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.bibDB;

public class BSAAL_Validator_Kopf_IS_NOT_CLOSED extends XX_ActionValidator {

	private MyE2_String cError = new MyE2_String("Bearbeitung ist verboten, ein/mehrere Vorgänge sind schon abgeschlossen !");
	
	public BSAAL_Validator_Kopf_IS_NOT_CLOSED() 
	{
		super();
	}

	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) 
	{
		return new MyE2_MessageVector();
	}

	public MyE2_MessageVector isValid(String cID_Unformated)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		String cSQL = "SELECT  NVL(JT_VKOPF_STD.ABGESCHLOSSEN,'N') FROM " +
							" "+bibE2.cTO()+".JT_VPOS_STD ,"+bibE2.cTO()+".JT_VKOPF_STD " +
							" WHERE JT_VKOPF_STD.ID_VKOPF_STD = JT_VPOS_STD.ID_VKOPF_STD " +
							" AND JT_VPOS_STD.ID_VPOS_STD="+cID_Unformated;
		
		String cRueck = bibDB.EinzelAbfrage(cSQL).toUpperCase();
		
		
		if (!cRueck.equals("N"))
			oMV.add_MESSAGE(new MyE2_Alarm_Message(this.cError));
		
		return oMV;
	}

}
