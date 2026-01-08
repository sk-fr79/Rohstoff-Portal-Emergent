package rohstoff.Echo2BusinessLogic._4_ALL;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MASCHINEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.exceptions.myException;

public class PruefeEigeneLKW {

	private RECLIST_MASCHINEN  rlMaschinen = null;
	private Vector<String>     vEigeneKennzeichen = new Vector<String>();
	
	public PruefeEigeneLKW() throws myException {
		super();
		this.rlMaschinen = bibSES.get_EIGENE_LKW(false);
		this.vEigeneKennzeichen = this.get_NormalizedKennzeichenVector();
	}
	
	public Vector<String> get_NormalizedKennzeichenVector() throws myException {
		Vector<String> vRueck = new Vector<String>();
		
		for (RECORD_MASCHINEN recMash: this.rlMaschinen.values() ) {
			String cKennzeichen = this.cleanKennzeichen(recMash.get_KFZKENNZEICHEN_cUF_NN("").trim());
			if (S.isFull(cKennzeichen)) {
				vRueck.add(cKennzeichen);
			}
		}
		
		return vRueck;
	}

	
	private String cleanKennzeichen(String KennzeichenOrig) {
		return bibTEXT.CleanStringKeepOnlyCharList(KennzeichenOrig, "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜäöü0123456789", true);
	}
	
	
	public boolean is_Eigener_LKW(String cPruefNummer) {
		String cPruefnummer = this.cleanKennzeichen(cPruefNummer);
		
		return this.vEigeneKennzeichen.contains(cPruefnummer);
		
	}
	
	public boolean is_Fremd_LKW(String cPruefNummer) {
		return !this.is_Eigener_LKW(cPruefNummer);
	}

}
