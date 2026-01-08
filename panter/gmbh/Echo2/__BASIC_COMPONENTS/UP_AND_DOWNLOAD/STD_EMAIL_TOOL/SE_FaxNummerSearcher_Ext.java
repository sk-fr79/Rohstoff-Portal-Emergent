package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import java.util.HashMap;

import panter.gmbh.basics4project.myCONST_ENUM.FAXNUMMER_DEF;
import panter.gmbh.basics4project.DB_ENUMS.QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATIONS_TYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MITARBEITER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class SE_FaxNummerSearcher_Ext {

	private FAXNUMMER_DEF faxNummerDefinition;
	private boolean mitarbeiterSuchen;
	private String idAdresse;
	private boolean showName;

	public SE_FaxNummerSearcher_Ext(String oIdAdresse, FAXNUMMER_DEF eFaxNummerDefinition, boolean bMitarbeiter, boolean bShowName) throws myException{
		this.faxNummerDefinition = eFaxNummerDefinition;
		this.mitarbeiterSuchen = bMitarbeiter;
		this.idAdresse = oIdAdresse;
		this.showName = bShowName;
		switch(faxNummerDefinition){
		case FIBU:
			searchFibuFaxNummer();
			break;
		case ALL:
			searchAllFaxNummer();
			break;
		case MAHNUNG:
			searchMahnungFaxNummer();
			break;
		}

	}

	private void searchFibuFaxNummer() throws myException{	
		if(mitarbeiterSuchen){

			RECORD_ADRESSE recaddr = new RECORD_ADRESSE(idAdresse);
			for (int i=0; i<recaddr.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().size();i++)		{

				RECORD_ADRESSE oAdresseMitarbeiter = recaddr.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
				RECORD_MITARBEITER mitarbeiterRec = recaddr.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get(i);

				if (oAdresseMitarbeiter.is_AKTIV_YES() && mitarbeiterRec.is_ASP_FIBU_YES())		{
					//dann die Mitarbeiter mail-adressen
					for (int k=0;k<oAdresseMitarbeiter.get_DOWN_RECORD_LIST_KOMMUNIKATION_id_adresse().get_vKeyValues().size();k++)	{
						RECORD_KOMMUNIKATION oNummer = oAdresseMitarbeiter.get_DOWN_RECORD_LIST_KOMMUNIKATION_id_adresse().get(k);
						if (S.isFull(getFaxNummer(oNummer))) {
							if (this.bCheckNummer(oNummer)){


								if(showName){
									this.vFaxNumberFound.add(
											getFaxNummer(oNummer) + "  ("+oAdresseMitarbeiter.get_NAME1_cUF_NN("") + " "+oAdresseMitarbeiter.get_VORNAME_cUF_NN("")+")"											
											);
								}else{
									this.vFaxNumberFound.add(getFaxNummer(oNummer));
								}
							}
						}
					}
				}
			}
		}

		searchAllFaxNummer();

	}

	private void searchMahnungFaxNummer() throws myException{
		RECLIST_KOMMUNIKATION recKommList = new RECLIST_KOMMUNIKATION(RECORD_KOMMUNIKATION.FIELD__ID_ADRESSE + " = " + idAdresse, "");

		HashMap<String, String> vQualifierFaxNummer = new RECLIST_QUALIFIER(QUALIFIER.datenbanktag + "='" + FAXNUMMER_DEF.MAHNUNG.getDatenbanktag()+"'", QUALIFIER.id_table.fieldName()).get_ID_TABLE_hmString_UnFormated("");

		for(RECORD_KOMMUNIKATION recKomm: recKommList){
			if(vQualifierFaxNummer.containsValue(recKomm.get_ID_KOMMUNIKATION_cUF_NN(""))){
				if(bCheckNummer(recKomm)){
					String faxNumber = getFaxNummer(recKomm);

					if(S.isFull(recKomm.get_WERT_SONSTIGE_cUF_NN(""))){
						faxNumber = faxNumber + (" (");
						faxNumber = faxNumber + (recKomm.get_WERT_SONSTIGE_cUF_NN(""));
						faxNumber = faxNumber + (")");
					}
					
					vFaxNumberFound.add(faxNumber);
				}
			}	
		}

		if(vFaxNumberFound.size()==0){
			searchAllFaxNummer();
		}
	}

	private String getFaxNummer(RECORD_KOMMUNIKATION recKomm) throws myException {
		StringBuffer faxNumber = new StringBuffer();
		faxNumber.append(recKomm.get_WERT_LAENDERVORWAHL_cUF() + " ");
		faxNumber.append(recKomm.get_WERT_VORWAHL_cUF() + " ");
		faxNumber.append(recKomm.get_WERT_RUFNUMMER_cUF());
		return faxNumber.toString();
	}

	private void searchAllFaxNummer() throws myException{

		RECLIST_KOMMUNIKATION recKommList = new RECLIST_KOMMUNIKATION(RECORD_KOMMUNIKATION.FIELD__ID_ADRESSE + " = " + idAdresse, "");

		for(RECORD_KOMMUNIKATION recKomm: recKommList){
			if(bCheckNummer(recKomm)){
				String faxNumber = getFaxNummer(recKomm);
				vFaxNumberFound.add(faxNumber + "  (Faxnummer der Firma)");
			}
		}
	}

	private boolean bCheckNummer(RECORD_KOMMUNIKATION oKom) throws myException {
		RECORD_KOMMUNIKATIONS_TYP recKomTyp = new RECORD_KOMMUNIKATIONS_TYP(RECORD_KOMMUNIKATIONS_TYP.FIELD__IST_FAX_NUMMER +"='Y'" );
		return oKom.get_ID_KOMMUNIKATIONS_TYP_cUF().equals(recKomTyp.get_ID_KOMMUNIKATIONS_TYP_cUF());
	}

	private VectorSingle	vFaxNumberFound = new VectorSingle();

	public VectorSingle get_vFax_number_found() {

		return vFaxNumberFound;
	}
}
