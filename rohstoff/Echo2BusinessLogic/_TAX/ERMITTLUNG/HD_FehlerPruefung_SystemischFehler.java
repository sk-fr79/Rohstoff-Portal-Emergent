package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class HD_FehlerPruefung_SystemischFehler {

	private HD_FehlerBerichte   vRueck = new HD_FehlerBerichte();
	
	public HD_FehlerPruefung_SystemischFehler() throws myException {
		super();
		
		//zuerst pruefen, ob die mandantenadresse korrekt eingestellt ist
		//bibALL.get_RECORD_MANDANT().get_I
		if (S.isEmpty(bibALL.get_ID_ADRESS_MANDANT()))	{
			this.vRueck.add(new ownFehler(new MyE2_String("Fehler in Basiseinstellungen"), new MyE2_String("Mandanteneinstellungen"), new MyE2_String("Dem aktuellen Mandanten ist keine Eigenadresse zugeordnet !")));
		} else {
			RECORD_ADRESSE  recAdressMandant = new RECORD_ADRESSE(bibALL.get_ID_ADRESS_MANDANT());
			if (recAdressMandant.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_VORSTEUERABZUG_RC_INLAND_NO()) {
				this.vRueck.add(new ownFehler(new MyE2_String("Fehler in Basiseinstellungen"), new MyE2_String("Mandanteneinstellungen"), new MyE2_String("Die Mandantenadresse MUSS das Merkmal: <Vorsteuerabzug/Reverse-Charge-Teilnahme Inland> tragen")));
			}
		}
	}

	private class ownFehler extends HD_FehlerBericht
	{
		public ownFehler(MyString cFehlerIstWo,	MyString cFehlerBetrifftFeld, MyString cFehlerInfo) {
			super(true, cFehlerIstWo, cFehlerBetrifftFeld, cFehlerInfo);
		}
		
	}
	
	public HD_FehlerBerichte get_vFehlerSystemisch() {
		return vRueck;
	}


}
