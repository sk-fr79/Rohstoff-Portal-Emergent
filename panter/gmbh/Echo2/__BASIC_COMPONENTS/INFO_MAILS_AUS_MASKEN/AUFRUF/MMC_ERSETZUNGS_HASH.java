package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import java.util.HashMap;
import java.util.LinkedHashMap;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.exceptions.myException;

public abstract class MMC_ERSETZUNGS_HASH extends HashMap<String, String>{
	
	public static String STATUS_HASHKEY_NEW = "STATUS_NEW";
	public static String STATUS_HASHKEY_EDIT = "STATUS_EDIT";
	public static String STATUS_HASHKEY_VIEW = "STATUS_VIEW";
	public static String STATUS_HASHKEY_UNDEF = "STATUS_UNDEF";
	
	
	//die hash muss immer vor dem klick auf den ausfuehrungs-button neu gefuellt werden. 
	public void CleanAndFillHashMap() throws myException {
		this.clear();
		this.putAll(this.get_hmMAP_KEY_ERSETZUNGS_TABELLE());
		this.putAll(this.get_hmSTANDARD_FIELDS());
		this.putAll(this.get_hmGROOVY_FIELDS());
	}
	
	
	public abstract HashMap<String, String>  get_hmMAP_KEY_ERSETZUNGS_TABELLE() throws myException;

	//hier wird der Masken-Status-Wert uebergeben (werte siehe oben statische variable).
	public abstract String  				get_cKEY_MASK_STATUS() throws myException;

	public boolean   get_bMask_STATUS_NeuErfassung() throws myException {
		return this.get_cKEY_MASK_STATUS().equals(MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_NEW);
	}
	
	public boolean   get_bMask_STATUS_Edit() throws myException {
		return this.get_cKEY_MASK_STATUS().equals(MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_EDIT);
	}
	
	public boolean   get_bMask_STATUS_Anzeige() throws myException {
		return this.get_cKEY_MASK_STATUS().equals(MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_VIEW);
	}
	
	public boolean  get_bMask_STATUS_Undefiniert() throws myException {
		return this.get_cKEY_MASK_STATUS().equals(MMC_ERSETZUNGS_HASH.STATUS_HASHKEY_UNDEF);
	}
	
	
	public HashMap<String, String>  get_hmSTANDARD_FIELDS() throws myException {
		//statische werte eintragen
		HashMap<String, String>  hmRueck = new HashMap<String, String>();
		
		hmRueck.put("SYS_USERNAME",bibALL.get_RECORD_USER().get_NAME_cUF_NN(""));
		hmRueck.put("SYS_KUERZEL",bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN(""));
		hmRueck.put("SYS_USERID",bibALL.get_RECORD_USER().get_ID_USER_cUF_NN(""));
		
		hmRueck.put("SYS_MANDANT_ID",bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF_NN(""));
		hmRueck.put("SYS_ID_ADRESSE_MANDANT",bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""));
		hmRueck.put("SYS_REPORTDATE",bibALL.get_cDateNOW());
		
		return hmRueck;
	}

	
	public HashMap<String, String>  get_hmGROOVY_FIELDS() throws myException {

		HashMap<String, String>  hmRueck = new HashMap<String, String>();
		
		//hier werden noch weitere sys-variable aus der groovy-definitionstabelle erzeugt
		LinkedHashMap<String, String[]> lhmGroovy = bibReplacer.get_ListOfReplaceFieldsOnlyGroovy_WITHOUT_PARAMETERS(false);
		for (String cHash: lhmGroovy.keySet()) {
			hmRueck.put(cHash,lhmGroovy.get(cHash)[1]);
		}
		
		return hmRueck;
	}
	
	
	/**
	 * uebersetzt die text mit hashes in neuen texte mit den ersetzungen
	 * @param cOriginalText
	 * @return
	 * @throws myException
	 */
	public String get_Ersetzungstext(String cOriginalText) throws myException {
		String cRueck = cOriginalText;
		
		for (String cHash: this.keySet()) {
			if (cHash.startsWith("#") && cHash.endsWith("#")) {
				cRueck = cRueck.replace(cHash, this.get(cHash));
			} else {
				cRueck = cRueck.replace("#"+cHash+"#", this.get(cHash));
			}
		}
		return cRueck;
	}

	
}
