/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung
 * @author martin
 * @date 02.10.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.indep.S;

/**
 * @author martin
 * @date 02.10.2019
 *
 */
public enum ADL_STATUS_PROFIL {

	PROFIL_KORREKT("OK","Profil passt zur Ausgangsfuhre", Color.GREEN)
	,PROFIL_KORREKT_DEAKTIVIERT("OK-Inaktiv","Profil passt zur Ausgangsfuhre, wurde aber deaktiviert", new E2_ColorHelpBackground())
	,PROFIL_FALSCH_OHNE_FUHRE("Falsch","Profil passt nicht zur Ausgangsfuhre", new E2_ColorAlarm())
	,PROFIL_FALSCH_MIT_FUHRE("Falsch (Fuhre vorhanden)","Profil passt nicht zur Ausgangsfuhre", new E2_ColorAlarm())
	,FEHLERSTATUS("Unbekannt","Unbekannter Fehler !!", new E2_ColorAlarm())
	
	;
	
	private String toolTips = null;
	private String text = null;
	private Color  color = null;
	
 	private ADL_STATUS_PROFIL(String p_text, String p_toolTips, Color  p_color) {
		toolTips = p_toolTips;
		text = p_text;
		color = p_color;
	}
	
	
	public RB_lab getLabel() {
		RB_lab label = new RB_lab()._t(this.text)._ttt(S.ms(this.toolTips));
		return label;
	}

	public Color getBackColor() {
		return this.color;
	}
	
	
}
