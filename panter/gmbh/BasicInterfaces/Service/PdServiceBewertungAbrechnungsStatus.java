/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 04.03.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.indep.O;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 04.03.2020
 *
 */
public class PdServiceBewertungAbrechnungsStatus {

	public enum ENUM_STATUS_UEBERGANG {
		FREMD
		,LAGER
		,GUTSCHRIFT
		,RECHNUNG
		,ALTE_FUHRE
		,UNDEFINDED
		;
	}

	
	public PdServiceBewertungAbrechnungsStatus() {
	}
	
	
	public ENUM_STATUS_UEBERGANG getStatusLeft(Long   besitzLeft,Long   besitzMid, Long   besitzRight) throws Exception {
		Rec21_adresse raLeft = new Rec21_adresse()._fill_id(besitzLeft);
		Rec21_adresse raMid = new Rec21_adresse()._fill_id(besitzMid);
		Rec21_adresse raRight = new Rec21_adresse()._fill_id(besitzRight);
		
		return this.getStatusLeft(raLeft, raMid, raRight);
	}


	public ENUM_STATUS_UEBERGANG getStatusRight(Long   besitzLeft,Long   besitzMid, Long   besitzRight) throws Exception {
		Rec21_adresse raLeft = new Rec21_adresse()._fill_id(besitzLeft);
		Rec21_adresse raMid = new Rec21_adresse()._fill_id(besitzMid);
		Rec21_adresse raRight = new Rec21_adresse()._fill_id(besitzRight);
		
		return this.getStatusRight(raLeft, raMid, raRight);
	}

	
	public ENUM_STATUS_UEBERGANG getStatusLeft(Rec21_adresse   besitzLeft,Rec21_adresse   besitzMid, Rec21_adresse   besitzRight) throws Exception {
		ENUM_STATUS_UEBERGANG status =	ENUM_STATUS_UEBERGANG.UNDEFINDED;
		if (O.isNoOneNull(besitzLeft,besitzMid, besitzRight)) {
			if (besitzLeft.isAdressOfMandant() && besitzMid.isAdressOfMandant()) {
				status= ENUM_STATUS_UEBERGANG.LAGER;
			} else if (!besitzLeft.isAdressOfMandant() && besitzMid.isAdressOfMandant()) {
				status= ENUM_STATUS_UEBERGANG.GUTSCHRIFT;
			} else if (besitzLeft.isAdressOfMandant() && !besitzMid.isAdressOfMandant()) {
				status= ENUM_STATUS_UEBERGANG.RECHNUNG;
			} else if (!besitzLeft.isAdressOfMandant() && !besitzMid.isAdressOfMandant()) {
				status= ENUM_STATUS_UEBERGANG.FREMD;
			}
		} else {
			status = ENUM_STATUS_UEBERGANG.UNDEFINDED;
		}
		return status;
	}


	public ENUM_STATUS_UEBERGANG getStatusRight(Rec21_adresse   besitzLeft,Rec21_adresse   besitzMid, Rec21_adresse   besitzRight) throws Exception {
		ENUM_STATUS_UEBERGANG status =	ENUM_STATUS_UEBERGANG.UNDEFINDED;
		if (O.isNoOneNull(besitzLeft,besitzMid, besitzRight)) {

			if (besitzMid.isAdressOfMandant() && besitzRight.isAdressOfMandant()) {
				status = ENUM_STATUS_UEBERGANG.LAGER;
			} else if (!besitzMid.isAdressOfMandant() && besitzRight.isAdressOfMandant()) {
				status= ENUM_STATUS_UEBERGANG.GUTSCHRIFT;
			} else if (besitzMid.isAdressOfMandant() && !besitzRight.isAdressOfMandant()) {
				status = ENUM_STATUS_UEBERGANG.RECHNUNG;
			} else if (!besitzMid.isAdressOfMandant() && !besitzRight.isAdressOfMandant()) {
				status = ENUM_STATUS_UEBERGANG.FREMD;			
			}
		} else {
			status = ENUM_STATUS_UEBERGANG.UNDEFINDED;
		}
		return status;
	}

	
}
