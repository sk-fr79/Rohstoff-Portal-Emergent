package panter.gmbh.Echo2.decisions;

import java.util.HashMap;
import java.util.UUID;

import panter.gmbh.indep.exceptions.myException;

/**
 * dieser container wird in der Session unter der UUID des ausloesenden actionelements gespeichert
 * er enthaelt wieder eine HashMap<UUID,FingerPrintPair> wo fuer jedes ausloesende element (kann ein ActionAgent oder ein Validator sein)
 * ein  FingerPrintPair hinterlegt und gespeichert wird. Das element ist immer dann als valid zu sehen, wenn beide fingerprints mit dem 
 * aktuellen fingerprint uebereinstimmen
 * @author martin
 *
 */
public class FingerPrintPairContainer extends HashMap<UUID, FingerPrintPair> {
	
	
	
	public FingerPrintPairContainer() {
		super();
	}

	/**
	 * wird ausgeloest vom aktuellen fehlerevent (Actionagent oder ActionValidator)
	 * @param uuidCallingObject ist hier die uuid eines actionAgents oder actionValidator des besagten klick-objekts
	 * @throws myException 
	 */
	public void set_FIRST_FingerPrint(UUID uuidCallingObject, FingerPrint oFingerPrint) throws myException {
		FingerPrintPair fPP = new FingerPrintPair();
		fPP.set_hmFingerprint1(oFingerPrint);
		this.put(uuidCallingObject, fPP);
	}

	/**
	 * Wird ausgeloest vom bestaetigungsbutton
	 * @param uuidCallingObject ist hier die uuid eines actionAgents oder actionValidator des besagten klick-objekts
	 * @throws myException
	 */
	public void set_SECOND_FingerPrint(UUID uuidCallingObject, FingerPrint oFingerPrint) throws myException {
		FingerPrintPair fPP = this.get(uuidCallingObject);
		
		if (fPP != null) {
			fPP.set_hmFingerprint2(oFingerPrint);

			//wenn diese beiden nicht gleich sind, dann wird nichts ausgeloest
			if (!fPP.get_ARE_Fingerprints_Equal()) {
				this.remove(uuidCallingObject);   //dann wieder auf null
			}
		}
	}
	
	
	/**
	 * sieht nach, ob es ein FingerPrintPair gibt zu diesem ActionAgent oder ActionValidator
	 * und vergleich diese, wenn ja
	 */
	public boolean COMPARE_SESSION_FingerPrints_With_Both_FingerPrints_in_Session(UUID uuidCallingObject, FingerPrint oFingerPrint) throws myException {
		boolean bRueck = false;
		
		FingerPrintPair fPP = this.get(uuidCallingObject);
		
		if (fPP != null) {

			bRueck = fPP.get_ARE_Fingerprints_Equal(oFingerPrint);
			//wenn diese beiden nicht gleich sind, dann wird nichts ausgeloest
			if (!bRueck) {
				this.remove(uuidCallingObject);   //dann wieder auf null
			}
		}

		return bRueck;
	}
	
	
	
}
