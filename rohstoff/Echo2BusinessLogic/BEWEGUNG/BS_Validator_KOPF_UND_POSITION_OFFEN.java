package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.bibDB;

public class BS_Validator_KOPF_UND_POSITION_OFFEN extends XX_ActionValidator {

	private MyE2_String cError = new MyE2_String("Aktion ist verboten - ein/mehrere Vorgänge oder Positionen sind schon abgeschlossen !");
	
	private String 		cTABLE_NAME = null;
	
	public BS_Validator_KOPF_UND_POSITION_OFFEN(String TABLENAME) 
	{
		super();
		this.cTABLE_NAME = TABLENAME.toUpperCase();
	}

	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) 
	{
		return new MyE2_MessageVector();
	}

	
	public MyE2_MessageVector isValid(String cID_Unformated)
	{
		/*
		 * je nach tablename unterschiedliche abfragen
		 */
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		boolean bValid = false;
		
		
		if (this.cTABLE_NAME.equals("JT_VKOPF_STD") || 
			this.cTABLE_NAME.equals("JT_VKOPF_RG") || 
			this.cTABLE_NAME.equals("JT_VKOPF_KON") || 
			this.cTABLE_NAME.equals("JT_VKOPF_TPA"))
		{
			bValid = bibDB.EinzelAbfrage("SELECT   NVL(ABGESCHLOSSEN,'N') FROM "+bibE2.cTO()+"."+this.cTABLE_NAME+" WHERE ID_"+this.cTABLE_NAME.substring(3)+"="+cID_Unformated).equals("N");
		}
		else
		{
			// in der position gibt es nur fuer fuhren und kontrakte einen abgeschlossen-marker
			String cQuery = "";
			if (this.cTABLE_NAME.equals("JT_VPOS_TPA"))
			{
				cQuery = "SELECT   NVL(JT_VKOPF_TPA.ABGESCHLOSSEN,'N'),  NVL(JT_VPOS_TPA_FUHRE.ABGESCHLOSSEN,'N') "+
							" FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE "+
							" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA  AND "+
							" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA  AND "+
							" JT_VPOS_TPA.ID_VPOS_TPA = "+cID_Unformated;
				String[][] cAnswer = bibDB.EinzelAbfrageInArray(cQuery);
				
				if (cAnswer != null && cAnswer.length==1 && cAnswer[0][0].equals("N") && cAnswer[0][1].equals("N"))
					bValid = true;
			} 
			else if (this.cTABLE_NAME.equals("JT_VPOS_KON"))
			{
				cQuery = "SELECT   NVL(JT_VKOPF_KON.ABGESCHLOSSEN,'N'),  NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N') "+
							" FROM "+bibE2.cTO()+".JT_VKOPF_KON,"+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE "+
							" JT_VKOPF_KON.ID_VKOPF_KON = JT_VPOS_KON.ID_VKOPF_KON  AND "+
							" JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON  AND "+
							" JT_VPOS_KON.ID_VPOS_KON = "+cID_Unformated;
				String[][] cAnswer = bibDB.EinzelAbfrageInArray(cQuery);
				
				if (cAnswer != null && cAnswer.length==1 && cAnswer[0][0].equals("N") && cAnswer[0][1].equals("N"))
					bValid = true;
			}
			else if (this.cTABLE_NAME.equals("JT_VPOS_STD"))
			{
				cQuery = "SELECT   NVL(JT_VKOPF_STD.ABGESCHLOSSEN,'N') "+
							" FROM "+bibE2.cTO()+".JT_VKOPF_STD,"+bibE2.cTO()+".JT_VPOS_STD WHERE "+
							" JT_VKOPF_STD.ID_VKOPF_STD = JT_VPOS_STD.ID_VKOPF_STD AND "+
							" JT_VPOS_STD.ID_VPOS_STD = "+cID_Unformated;
				String[][] cAnswer = bibDB.EinzelAbfrageInArray(cQuery);
				
				if (cAnswer != null && cAnswer.length==1 && cAnswer[0][0].equals("N"))
					bValid = true;
			}
			else if (this.cTABLE_NAME.equals("JT_VPOS_RG"))
			{
				cQuery = "SELECT   NVL(JT_VKOPF_RG.ABGESCHLOSSEN,'N') "+
							" FROM "+bibE2.cTO()+".JT_VKOPF_RG,"+bibE2.cTO()+".JT_VPOS_RG WHERE "+
							" JT_VKOPF_RG.ID_VKOPF_RG = JT_VPOS_RG.ID_VKOPF_RG AND "+
							" JT_VPOS_RG.ID_VPOS_RG = "+cID_Unformated;
				String[][] cAnswer = bibDB.EinzelAbfrageInArray(cQuery);
				
				if (cAnswer != null && cAnswer.length==1 && cAnswer[0][0].equals("N"))
					bValid = true;
			}
			
		}
			
		if (!bValid)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(this.cError));
		}
		return oMV;
		
	}

}
