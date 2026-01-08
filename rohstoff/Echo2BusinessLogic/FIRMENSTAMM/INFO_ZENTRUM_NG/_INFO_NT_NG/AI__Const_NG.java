package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG;

import java.util.TreeMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;

public class AI__Const_NG {

	public enum BLOCK_TO_SHOW {
	 	 MITARBEITER("Mitarbeiter",					"Mitarbeiter der Firma anzeigen")
		,MITARBEITER_TELEFON("Telefon (MA)",		"Telefonnummern der Mitarbeiter anzeigen")
		,MITARBEITER_MAIL("eMail (MA)",				"Email-Adressen der Mitarbeiter anzeigen")
		,LIEFERADRESSEN("Lieferad.",				"Lieferadressen der Firma anzeigen")
		,LIEFERADRESSEN_TELEFON("Telefon (LA)",		"Telefonnummern der Lieferadressen anzeigen")
		,LIEFERADRESSEN_MAIL("eMail (LA)",			"Email-Adressen der Lieferadressen anzeigen")
		,TELEFON("Telefon (Firma)",					"Telefonnummern anzeigen, die direkt der Firma zugeordnet sind")
		,EMAIL("eMail (Firma)",						"Email-Adressen anzeigen, die direkt der Firma zugeordnet sind")
		,ZUSATZINFOS("Infos",						"Infos zur Firma anzeigen")
		,ZUSATZINFOS_ANLAGEN("Inf.Anl.",			"Dateien anzeigen, die zu den Zusatzinfos hochgeladen wurden")
		,MELDUNGEN("Meldungen",						"Systemmeldungen anzeigen, die zur Firma angelegt sind")
		,ANLAGEN("Firma-Anl.",						"Dateien anzeigen, die zur Hauptadresse hochgeladen wurden")
		,INAKTIVE_MITARBEITER_AUSBLENDEN(			"Inaktive Mitarb. ausbl.",	"Inaktive Mitarbeiter ausblenden")
		,INAKTIVE_LIEFERADRESSEN_AUSBLENDEN(		"Inaktive Liefer. ausbl.",	"Inaktive Lieferadressen ausblenden")
		,BESCHRIFTUNG("Beschriftung",				"Beschriftungen anzeigen")
		
		,SF_SETZE_FARBDIFFERENZ(                  		"Farbdiff.","Farbunterschied der Einrückungen definieren")
		,SF_SETZE_ABSTAND(                  			"Abstände","Abstände der einzelnen Infoblöcke definieren")

		;
	 	 
	 	private MyE2_String label_for_user = null; 
	 	private MyE2_String toolTipText = null;
	 	
	 	private BLOCK_TO_SHOW(String label4User, String langText) {
	 		this.label_for_user = new MyE2_String(label4User);
	 		this.toolTipText = new MyE2_String(langText);
	 	}
	 	 
		public MyE2_String label4User() {
			return this.label_for_user;
		}
		
		public MyE2_String toolTipText() {
			return toolTipText;
		}

	}

	
	
	public static TreeMap<BLOCK_TO_SHOW, IF_Saveable>  generate_AI_CheckBoxes() {
		TreeMap<BLOCK_TO_SHOW, IF_Saveable> hm = new TreeMap<AI__Const_NG.BLOCK_TO_SHOW, IF_Saveable>();
		
		for (BLOCK_TO_SHOW block: BLOCK_TO_SHOW.values()) {
			if (!block.name().startsWith("SF_")) {
				
			}
			hm.put(block, new AI__CheckBox_NG(block.label_for_user, block.toolTipText, false, block));
		}
		
		return hm;
	}
	
	
	
	
}
