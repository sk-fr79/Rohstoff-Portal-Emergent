package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public class TR__ManipuliereMeldungWegenDubletten extends XX_MessageManipulator {

	@Override
	public MyE2_MessageVector get_Changed_MessageVector(MyE2_MessageVector oMV) throws myException {
		
		boolean bDublettenMeldungGefunden = false;
		
		if (oMV != null && oMV.size()>0) {
			for (MyE2_Message  oMSG: oMV) {
				if (oMSG.get_cMessage().COrig().toUpperCase().contains("IDX_HANDELSDEF_DUBLETTE")) {
					bDublettenMeldungGefunden = true;
				}
			}
		}
		
		if (bDublettenMeldungGefunden) {
			MyE2_MessageVector oMV_NEU = new MyE2_MessageVector();
			oMV_NEU.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde eine Handelsdefinition mit gleicher Ausgangswerte-Basis gefunden." +
					" Bitte autorisieren Sie die Dublettenspeicherung (Schalter: Mehrfache Basiswerte erlauben)!")));
			
			return oMV_NEU;
			
		}
		
		return oMV;
	}

}
