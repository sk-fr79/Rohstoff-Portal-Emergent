package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum BGL_ENUM_REPORT_PARAMETER implements IF_enum_4_db {
	ID_ZAHLUNGSBEDINGUNGEN("(01) Zahlungsbedingungen")
	,E_PREIS("(02) Einzelpreis")
	,ID_TAX_RECHNUNG("(03) Steuer für Rechnungsbeleg")
	,ID_TAX_GUTSCHRIFT("(04) Steuer für Gutschriftsbeleg")
	,NAME_ANSPRECH_INTERN("(05) Name Ansprechpartner")
	,TEL_ANSPRECH_INTERN("(06) Telefonnummer Ansprechpartner")
	,FAX_ANSPRECH_INTERN("(07) Fax Ansprechpartner")
	,NAME_BEARBEITER_INTERN("(08) Name Bearbeiter intern")
	,TEL_BEARBEITER_INTERN("(09) Telefon Bearbeiter intern")
	,FAX_BEARBEITER_INTERN("(10) Fax Bearbeiter intern")
	,FORMULARTEXT_ANFANG("(11) Formulatext Anfang")
	,FORMULARTEXT_ENDE("(12) Formulatext Ende")
	,TEXT_AUSLANDSVERTRETUNG("(13) Auslandsvertretung Text") 
	,ID_VPOS_TPA_FUHRE("")
	;
	
	private String beschreibung="";
	private RB_KF key = null;
	
	private BGL_ENUM_REPORT_PARAMETER(String sBeschreibung)  {
		this.beschreibung = sBeschreibung;
		
		try {
			this.key = new RB_KF(this.name());
		} catch (myException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return this.beschreibung;
	}
	
	public RB_KF key() throws myException{
		return this.key;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(BGL_ENUM_REPORT_PARAMETER.values(), emptyPairInFront);
	}

}
