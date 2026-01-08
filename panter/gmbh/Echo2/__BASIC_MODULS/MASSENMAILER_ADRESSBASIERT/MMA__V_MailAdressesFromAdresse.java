package panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdressSearcherNG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class MMA__V_MailAdressesFromAdresse extends MailAdress4MailBlock_Vector {

	/**
	 * 
	 * @param cID_ADRESSE
	 * @param IncludeBasisAdresse
	 * @param IncludeMitarbeiteradresse
	 * @param IncludeLieferadresse
	 * @param AddEmptyAdressWhenNothingFound
	 * @param vMailTypen
	 * @throws myException
	 */
	public MMA__V_MailAdressesFromAdresse(		String 	cID_ADRESSE, 
												boolean IncludeBasisAdresse, 
												boolean IncludeMitarbeiteradresse,
												boolean IncludeLieferadresse, 
												boolean AddEmptyAdressWhenNothingFound,
												Vector<String> vMailTypen) throws myException {
		super();
		
		RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(cID_ADRESSE);
		
		//feststellen, welche Adressen ueberhaupt moeglich sind, abhaengig von der Adress-ID
		if (recAdresse.get_ADRESSTYP_lValue(-1L).intValue()!=myCONST.ADRESSTYP_FIRMENINFO) {
			IncludeBasisAdresse = false;
		}
		if (recAdresse.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_MITARBEITER) {
			IncludeLieferadresse = false;
		}
		if (recAdresse.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_LIEFERADRESSE) {
			IncludeMitarbeiteradresse = false;
		}
		
		HashMap<String,MyString>  hmTypen = null;
		
		if (vMailTypen != null) {
			 hmTypen = new HashMap<String, MyString>();
			 
			 for (String cTYP: vMailTypen) {
				 hmTypen.put(cTYP, new MyString(myCONST.hmTypeAndUserTextEmailTypenReal.get(cTYP)));
			 }
		}
		
		this.addAll(new MailAdressSearcherNG(	recAdresse,
												IncludeBasisAdresse,
												IncludeMitarbeiteradresse,
												IncludeLieferadresse,
												hmTypen,
												null, 
												AddEmptyAdressWhenNothingFound, 
												null, 
												true));  
	}

}
