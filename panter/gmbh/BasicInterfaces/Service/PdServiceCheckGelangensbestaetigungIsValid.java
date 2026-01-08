/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 20.08.2021
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.Echo2.Messaging.EnumMessageType;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22Land;

/**
 * @author martin
 * @date 20.08.2021
 *
 */
public class PdServiceCheckGelangensbestaetigungIsValid {

	public static enum EnumGelangensbestaetigungStatus {
		
		  GELANG_BEST_IS_VALID("", EnumMessageType.INFO)
		, GELANG_BEST_IS_VALID_FUHRE_FREMDE_HOHEIT("Gelangensbestätigung unter Vorbehalt, da die Fuhre unter Fremdverantwortung liegt !", EnumMessageType.WARNING)
		, GELANG_BEST_NOT_ALLOWED_FUHRE_FREMDWARE("Keine Gelangensbestätigung, da die Fuhre eine Fremdwarenfuhre ist !", EnumMessageType.ALARM)
		, GELANG_BEST_NOT_ALLOWED_FUHRE_UNVOLLSTAENDIG("Keine Gelangensbestätigung, da die Fuhre unvollständig ist (undefinierte Zuständigkeit) !", EnumMessageType.ALARM)
		, GELANG_BEST_NOT_ALLOWED_ZIELORT_KEIN_ERLAUBTES_LAND("Keine Gelangensbestätigung, da die Fuhre nicht in eine EU-Land (oder speziell definiertem Land) endet!", EnumMessageType.ALARM)
		, GELANG_BEST_NOT_ALLOWED_ZIEL_IN_DEUTSCHLAND("Keine Gelangensbestätigung, da die Fuhre in Deutschland endet!", EnumMessageType.ALARM)
		, GELANG_BEST_NOT_ALLOWED_UNBEKANNTER_FEHLER("Keine Gelangensbestätigung, Fehler unbekannt", EnumMessageType.ALARM)
		, GELANG_BEST_NOT_ALLOWED_FUHRENORT_IST_QUELLE("Keine Gelangensbestätigung, bei einem Fuhrenort kann nur ein Zielort eine Gelangesbestätigung haben !", EnumMessageType.ALARM)
		;
		
		
		private String   			message = null;
		private EnumMessageType  	messageType = null;

		/**
		 * @author martin
		 * @date 20.08.2021
		 *
		 * @param message
		 * @param isOk TODO
		 */
		private EnumGelangensbestaetigungStatus(String message, EnumMessageType messageType) {
			this.message = message;
			this.messageType = messageType;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @return the messageType
		 */
		public EnumMessageType getMessageType() {
			return messageType;
		}
	}
	
	
	
	public EnumGelangensbestaetigungStatus getStatusValidierung(Long  idAdresseGeoZiel, boolean fremdWare, String tpVerantwortung) {
		EnumGelangensbestaetigungStatus enumGelangensbestaetigungStatus = EnumGelangensbestaetigungStatus.GELANG_BEST_NOT_ALLOWED_UNBEKANNTER_FEHLER;
		try {
			if (fremdWare) {
				enumGelangensbestaetigungStatus = EnumGelangensbestaetigungStatus.GELANG_BEST_NOT_ALLOWED_FUHRE_FREMDWARE;
			} else {
				if (S.isEmpty(tpVerantwortung) || idAdresseGeoZiel==null || idAdresseGeoZiel.longValue()<=0) {
					enumGelangensbestaetigungStatus = EnumGelangensbestaetigungStatus.GELANG_BEST_NOT_ALLOWED_FUHRE_UNVOLLSTAENDIG;
				} else {
					Rec21_adresse geografischerZielOrt = new Rec21_adresse()._fill_id(idAdresseGeoZiel);
					
					Long idLandMandant = bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(-1l);

					if (geografischerZielOrt.getLongDbValue(ADRESSE.id_land).longValue()==idLandMandant.longValue()) {
						enumGelangensbestaetigungStatus = EnumGelangensbestaetigungStatus.GELANG_BEST_NOT_ALLOWED_ZIEL_IN_DEUTSCHLAND;
					} else {
						Rec22Land zielLandGeo = new Rec22Land()._fill_id(geografischerZielOrt.getLongDbValue(ADRESSE.id_land));
						if (zielLandGeo.isQualifiedForGelangensbestaetigung()) {
							if (tpVerantwortung.equals(TAX_CONST.TP_VERANTWORTUNG_MANDANT)) {
								enumGelangensbestaetigungStatus = EnumGelangensbestaetigungStatus.GELANG_BEST_IS_VALID;
							} else {
								enumGelangensbestaetigungStatus = EnumGelangensbestaetigungStatus.GELANG_BEST_IS_VALID_FUHRE_FREMDE_HOHEIT;
							}
						} else {
							enumGelangensbestaetigungStatus = EnumGelangensbestaetigungStatus.GELANG_BEST_NOT_ALLOWED_ZIELORT_KEIN_ERLAUBTES_LAND;
						}
					}
				}	
			}
			
		} catch (Exception e) {
			enumGelangensbestaetigungStatus = EnumGelangensbestaetigungStatus.GELANG_BEST_NOT_ALLOWED_UNBEKANNTER_FEHLER;
			e.printStackTrace();
		} 
		
		
		
		return enumGelangensbestaetigungStatus;
	}
	
	
	
	
}


