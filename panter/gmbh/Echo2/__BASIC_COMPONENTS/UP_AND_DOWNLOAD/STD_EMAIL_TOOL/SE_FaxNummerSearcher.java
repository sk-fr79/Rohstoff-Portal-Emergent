package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATIONS_TYP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class SE_FaxNummerSearcher
{

	
	private VectorSingle	vFaxNumberFound = new VectorSingle();

	/**
	 * @param recAdresseWithMails
	 * @param basisMailsEinbeziehen
	 * @param mitarbeiterEinbeziehen
	 * @param lieferadressenEinbeziehen
	 * @throws myException
	 */
	public SE_FaxNummerSearcher(String p_idAdresse) throws myException	{
		super();

		RECLIST_KOMMUNIKATION recKommList = new RECLIST_KOMMUNIKATION(RECORD_KOMMUNIKATION.FIELD__ID_ADRESSE + " = " + p_idAdresse, "");
		
		for(RECORD_KOMMUNIKATION recKomm: recKommList){
			if(bCheckNummer(recKomm)){
				StringBuffer faxNumber = new StringBuffer();
				faxNumber.append(recKomm.get_WERT_LAENDERVORWAHL_cUF() + " ");
				faxNumber.append(recKomm.get_WERT_VORWAHL_cUF() + " ");
				faxNumber.append(recKomm.get_WERT_RUFNUMMER_cUF());
				vFaxNumberFound.add(faxNumber.toString());
			}
		}
				
	}

	
	
	private boolean bCheckNummer(RECORD_KOMMUNIKATION oKom) throws myException {
		RECORD_KOMMUNIKATIONS_TYP recKomTyp = new RECORD_KOMMUNIKATIONS_TYP(RECORD_KOMMUNIKATIONS_TYP.FIELD__IST_FAX_NUMMER +"='Y'" );
		return oKom.get_ID_KOMMUNIKATIONS_TYP_cUF().equals(recKomTyp.get_ID_KOMMUNIKATIONS_TYP_cUF());
	}



	public VectorSingle get_vFax_number_found() {
		
		return vFaxNumberFound;
	}

	
}

