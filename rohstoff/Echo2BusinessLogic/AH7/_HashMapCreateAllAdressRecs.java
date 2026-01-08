/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;

import java.util.HashMap;

import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 *
 * liesst alle relevanten adressen als Rec21_adresse ein
 *
 */
public class _HashMapCreateAllAdressRecs extends HashMap<_HashMapCreateAllAdressRecs.ADRESSKEY,Rec21_adresse> {

	public static enum ADRESSKEY {
		START_GEO
		,ZIEL_GEO
		,START_JUR
		,ZIEL_JUR
		,START_FREMDBESITZ
		,ZIEL_FREMDBESITZ
	}
	
	public _HashMapCreateAllAdressRecs(String idAdresseStartGeo, String idAdresseZielGeo) throws myException {
		super();
		
		MyLong lAdresseStartGeo = 	new MyLong(idAdresseStartGeo);
		MyLong lAdresseZielGeo = 	new MyLong(idAdresseZielGeo);
		
		if (lAdresseStartGeo.isNotOK() || lAdresseZielGeo.isNotOK()) {
			throw new myException(this,"One number is not correct : <"+S.NN(idAdresseStartGeo)+"> or <"+S.NN(idAdresseZielGeo)+">");
		}
		
		//alle durchgehen und vergleichen
		Rec21_adresse 	recAdresseStartGeo = (Rec21_adresse)new Rec21_adresse()._fill_id(lAdresseStartGeo.get_lValue());
		Rec21_adresse 	recAdresseStartJur = recAdresseStartGeo.__get_main_adresse();
		Rec21_adresse	recAdresseStartBesitzGeo = recAdresseStartGeo.getRec21LagerDrittBesitzer();
		
		Rec21_adresse 	recAdresseZielGeo =  (Rec21_adresse)new Rec21_adresse()._fill_id(lAdresseZielGeo.get_lValue());
		Rec21_adresse 	recAdresseZielJur =  recAdresseZielGeo.__get_main_adresse();
		Rec21_adresse	recAdresseZielBesitzGeo = recAdresseZielGeo.getRec21LagerDrittBesitzer();
		
		this.put(ADRESSKEY.START_JUR, 			recAdresseStartJur);
		this.put(ADRESSKEY.ZIEL_JUR, 			recAdresseZielJur);
		this.put(ADRESSKEY.START_GEO, 			recAdresseStartGeo);
		this.put(ADRESSKEY.ZIEL_GEO, 			recAdresseZielGeo);
		this.put(ADRESSKEY.START_FREMDBESITZ, 	recAdresseStartBesitzGeo);
		this.put(ADRESSKEY.ZIEL_FREMDBESITZ, 	recAdresseZielBesitzGeo);

		

	}

	
	
	
}
