package panter.gmbh.Echo2.decisions;

import java.util.HashMap;
import java.util.UUID;

import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibSES_keys4save;
import panter.gmbh.indep.exceptions.myException;

public class bibDECISIONS {

	
	

	/**
	 * die validierungschleifer wieder auf reset
	 * @param uuidButton
	 * @throws myException
	 */
	@SuppressWarnings("unchecked")
	public static void CLEAN_Component_Fingerprint_in_SESSION(UUID  uuidButton) throws myException {
		
	
		//basis-hashmap in der session pruefen und ggf. anlegen
		if (bibSES.getSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS)==null) {
			HashMap<UUID, FingerPrintPairContainer>  hmUUIDs = new HashMap<UUID, FingerPrintPairContainer>();
			bibSES.setSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS, hmUUIDs);
		}
		
		HashMap<UUID, FingerPrintPairContainer> hm_UUIDs = (HashMap<UUID, FingerPrintPairContainer>) 
				bibSES.getSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS);
		
		hm_UUIDs.clear();
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public static FingerPrintPairContainer GET_OR_CREATE_FingerPrintPairContainer(UUID  uuidButton) throws myException {
		
		//basis-hashmap in der session pruefen und ggf. anlegen
		if (bibSES.getSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS)==null) {
			HashMap<UUID, FingerPrintPairContainer>  hmUUIDs = new HashMap<UUID, FingerPrintPairContainer>();
			bibSES.setSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS, hmUUIDs);
		}
		
		HashMap<UUID, FingerPrintPairContainer> hm_UUIDs_StartingObject = (HashMap<UUID, FingerPrintPairContainer>) 
				bibSES.getSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS);

		//dann nachsehen, ob es fuer den besagten button der momentanen aktion einen Fingerprintcontainer gibt,
		//wenn nein, dann ist es einen neue aktion und alle "button" - referenzen werden geloescht.
		//Es wird ein FingerPrintContainer fuer den button angelegt 
		if (hm_UUIDs_StartingObject.get(uuidButton)==null) {
			hm_UUIDs_StartingObject.clear();       //alle referenzen auf vorige Klick-Objekte loeschen
			hm_UUIDs_StartingObject.put(uuidButton, new FingerPrintPairContainer());
		}
		
		
		FingerPrintPairContainer  fPC = hm_UUIDs_StartingObject.get(uuidButton);
		return fPC;
	
	}


	
	
	
	/**
	 * einchecken eines fingerprints-step1 der aktuellen maske um einen spaeteren vergleich auf aenderungen zu haben 
	 * @param uuidButton ist vom ausloesenden objekt, uuidActionEvent kann vom actionagent oder vom actionvalidator sein
	 * @throws myException 
	 */
	public static void SAVE_VALID_FingerPrint_TO_SESSION_STEP1(UUID  uuidButton, UUID uuidActionEvent, FingerPrint oFingerPrint) throws myException {
		FingerPrintPairContainer  fPC = bibDECISIONS.GET_OR_CREATE_FingerPrintPairContainer(uuidButton);
		fPC.set_FIRST_FingerPrint(uuidActionEvent, oFingerPrint);
	}

	
	
	
	

	/**
	 * einchecken eines fingerprints-step2 der aktuellen maske um einen spaeteren vergleich auf aenderungen zu haben 
	 * @param uuidButton
	 * @throws myException 
	 */
	@SuppressWarnings("unchecked")
	public static void SAVE_VALID_FingerPrint_TO_SESSION_STEP2(UUID  uuidButton, UUID uuidActionEvent,  FingerPrint oFingerPrint) throws myException {
		
		//nur sinnvoll, wenn es bereits einen fingerprintcontainer gibt
		HashMap<UUID, FingerPrintPairContainer> hm_UUIDs = (HashMap<UUID, FingerPrintPairContainer>) 
				bibSES.getSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS);

		if (hm_UUIDs == null) {
			throw new myException("System-Error: bbiDECISIONS: SAVE_VALID_WindowContentFingerPrint_TO_SESSION_STEP2: No HashMap<String,FingerPrintPairContainer> in Session !!");
		}
		
		if (hm_UUIDs.containsKey(uuidButton)) {
			FingerPrintPairContainer oFPC = hm_UUIDs.get(uuidButton);
			if (oFPC!=null) {
				oFPC.set_SECOND_FingerPrint(uuidActionEvent, oFingerPrint);
			}
		}
	}

	

	
	
	
	/**
	 * 
	 * @param uuidButton
	 * @return true, wenn der aktuelle Fingerprint mit beiden in der session hinterlegten fingerprints
	 *         uebereinstimmt
	 * @throws myException
	 */
	@SuppressWarnings("unchecked")
	public static boolean COMPARE_SESSION_FingerPrints_WithActualFingerPrint(UUID  uuidButton, UUID uuidActionEvent, FingerPrint oFingerPrintCompare) throws myException {
		boolean bRueck = false;
		
		
		if (bibSES.getSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS)!=null) {
		
			HashMap<UUID, FingerPrintPairContainer> hm_UUIDs = (HashMap<UUID, FingerPrintPairContainer>) 
					bibSES.getSessionValue(bibSES_keys4save.SES_KEY____UUID_HASHMAP_4_FINGERPRINTS);
	
			if (hm_UUIDs.containsKey(uuidButton)) {
				FingerPrintPairContainer oFPC = hm_UUIDs.get(uuidButton);
				
				if (oFPC != null) {
					bRueck = oFPC.COMPARE_SESSION_FingerPrints_With_Both_FingerPrints_in_Session(uuidActionEvent, oFingerPrintCompare);
				}
			}
		}
		
		return bRueck;
		
	}
	


	
	
	
}
