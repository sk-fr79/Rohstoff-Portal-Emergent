package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_RG_ext;


/**
 * Validierer prueft, ob die kunden-UST-ID im Kopfsatz mit einer der UST-IDs im Adress-Stamm uebereinstimmt 
 * @author martin
 *
 */
public class BSRG_K_LIST_BT_Mail_and_Print_Valid_Ableich_UST_ID extends XX_ActionValidator {

	
	
	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		return null;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_VKOPF_RG)	throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		RECORD_VKOPF_RG_ext     recVKOPF =    	new RECORD_VKOPF_RG_ext(cID_VKOPF_RG);
		RECORD_ADRESSE 			recAD = 		recVKOPF.get_UP_RECORD_ADRESSE_id_adresse();
		RECORD_FIRMENINFO 		recFI = 		recAD.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);

		String cID_UST_LKZ_FORMULAR = recVKOPF.get_UMSATZSTEUERLKZ_cUF_NN("");
		String cID_UST_ID_FORMULAR = recVKOPF.get_UMSATZSTEUERID_cUF_NN("");
		
		
		boolean bStimmtUeberein = false;
		
		// wenn die ust-id im kopf leer ist, wird nicht weiter geprueft, da dies woanders gecheckt wird
		if (!(S.isEmpty(cID_UST_LKZ_FORMULAR) && S.isEmpty(cID_UST_ID_FORMULAR))) {
			if (recFI.get_UMSATZSTEUERLKZ_cUF_NN("").equals(cID_UST_LKZ_FORMULAR) && 
				recFI.get_UMSATZSTEUERID_cUF_NN("").equals(cID_UST_ID_FORMULAR)) {
				
				bStimmtUeberein = true;
			} else {
				
				RECLIST_ADRESSE_UST_ID rlUST = recAD.get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse();
				for (RECORD_ADRESSE_UST_ID  oSTEUER: rlUST.values()) {
					if (oSTEUER.get_UMSATZSTEUERLKZ_cUF_NN("").equals(cID_UST_LKZ_FORMULAR) && 
						oSTEUER.get_UMSATZSTEUERID_cUF_NN("").equals(cID_UST_ID_FORMULAR)) {
						bStimmtUeberein = true;
					}		
				}
			}

			if (!bStimmtUeberein) {
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Im Belegkopf steht eine Kunden-UST-ID, " +
						"die in der Adresse des Kunden nicht vorhanden ist  (Kunde: ",true,
						recAD.get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$ORT)),false,")",false)));
			}
		}
		
		
		//2. pruefung: Formular ist leer, aber im stammsatz steht was
		/*
		 * wirkt bei nicht EU-Kunden als Warnung. Bei EU-Kunden kommt zusaetzlich noch ein Fehler
		 */
		String cUST_LKZ_BASIS_FIRMA = recFI.get_UMSATZSTEUERLKZ_cUF_NN("");
		String cUST_ID_BASIS_FIRMA = recFI.get_UMSATZSTEUERID_cUF_NN("");
		
		boolean bFormularIstLeer = S.isEmpty(cID_UST_LKZ_FORMULAR) && S.isEmpty(cID_UST_ID_FORMULAR);
		boolean bAdresseHauptNummerIstLeer = S.isEmpty(cUST_LKZ_BASIS_FIRMA) && S.isEmpty(cUST_ID_BASIS_FIRMA);
		
		if (bFormularIstLeer && !bAdresseHauptNummerIstLeer) {
			oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Im Belegkopf steht keine Kunden-UST-ID, " +
					"obwohl in der Adresse eine Basis-UST-ID vorhanden ist (Kunde: ",true,
					recAD.get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$ORT)),false,")",false)));
		}
		
		return oMV;
	}

}
