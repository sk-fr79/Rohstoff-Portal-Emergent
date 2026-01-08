/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION;

import java.util.HashMap;

import panter.gmbh.BasicInterfaces.Service.PdServiceHandleSanktionsChecks;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.SANKTION.SANKTION_Ergebnisse;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class FS_CheckAdresseAfterSearch {
	
	public MyE2_MessageVector checkAdressId(Long idAdresse) {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		
		try {
			if (ENUM_MANDANT_DECISION.SANKTIONS_CHECK_ADRESS_AT_LOAD_IN_ALL_MODULES.is_YES_FromSession()) {
				if (idAdresse==null) {
					mv._addAlarm("Adresse kann nicht auf Sanktionen geprüft werden. Adresse unbekannt !");
				} else {

					@SuppressWarnings("unchecked")
					VEK<Long>  vAllAdressIdsFromMandant = new VEK<Long>()._a(bibSES.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null))
															._a((VEK<Long>) ENUM_MANDANT_SESSION_STORE.VECTOR_ALL_DELIVERYADDRESSES_FROM_MANDANT.getValueFromSession())
															._a((VEK<Long>) ENUM_MANDANT_SESSION_STORE.VECTOR_ALL_EMPLOYES_FROM_MANDANT.getValueFromSession());

					if ( 	vAllAdressIdsFromMandant.contains(idAdresse) && ENUM_MANDANT_DECISION.SANKTIONS_CHECK_ADRESS_EXCLUDE_ADRESS_MANDANT.is_YES_FromSession()) {
						
						return mv;    // dann passiert nichts

					} else {
						PdServiceHandleSanktionsChecks  sanktionService = new PdServiceHandleSanktionsChecks()
								._send_meldung(true).initWithAdresses(new VEK<String>()._a(idAdresse.toString()));
						
						//HashMap<String, SANKTION_Ergebnisse> testResult = sanktionService.get_check_result();
						
						//20181010: sanktionsdb-fehler mit resultierendem, leerem  sanktionService.get_check_result() einkalkulieren
						if (sanktionService.get_check_result().size()>0) {   //2018-09-28: abfagen der moeglichkeit, dass kein ergebniss zurueckkommt (wegen Datenbankfehler z.B.) 

							SANKTION_Ergebnisse ergebnissDerPruefung = sanktionService.get_check_result().get(idAdresse.toString());
							
							if (ergebnissDerPruefung!=null && ergebnissDerPruefung.containsSanktion() && !(sanktionService.has_freigabe(idAdresse.toString()))) {
								mv._addAlarm("Adresse wurde in der Sanktionsdatenbank gefunden ! Verwendung ist nicht freigegeben ! Es wurde eine Systemnachricht verschickt !");
							}
						}
					}
				}
			}
		} catch (myException e) {
			e.printStackTrace();
			mv.add(e.get_ErrorMessage());
		}
		
		
		return mv;
	}
	
	
}
