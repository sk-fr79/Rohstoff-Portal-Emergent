package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.util.HashMap;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.enumtools.if_enum_helpers;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_KON_EXT.CLEARINGTAGS;;

public class BSKC__CONST
{
	public static String HASH_LABEL_LOCKED = 				"HASH_LABEL_LOCKED";
	public static String HASH_BUTTON_ZUORDNUNG = 			"HASH_BUTTON_ZUORDNUNG";

	public static String HASH_LABEL_OFFEN_GESCHLOSSEN = 	"HASH_LABEL_OFFEN_GESCHLOSSEN";

	
	public static E2_ResourceIcon LABEL_EMPTY = E2_ResourceIcon.get_RI("empty20.png")	;
	public static E2_ResourceIcon LABEL_POSITION_LOCKED  = 		E2_ResourceIcon.get_RI("warnschild_16.png");
	public static E2_ResourceIcon LABEL_POSITION_UNLOCKED  = 	E2_ResourceIcon.get_RI("warnschild_grau_16.png");

	
	public static String HASH_MENGE_SUMME_GEGENSEITE = 			"HASH_MENGE_SUMME_GEGENSEITE";
	public static String HASH_MENGE_SUMME_LAGER = 				"HASH_MENGE_SUMME_LAGER";
//	public static String HASH_R_MENGE_SUMME_ZUORDNUNG = 		"HASH_R_MENGE_SUMME_ZUORDNUNG";
//	public static String HASH_R_MENGE_SUMME_DIFFERENZ = 		"HASH_MENGE_SUMME_DIFFERENZ";
//	
//	public static String HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE = 	"HASH_SUMME_EK_VK_FUHREN_PLANMENGE";
//	public static String HASH_R_SUMME_EK_VK_FUHREN_REALMENGE = 	"HASH_SUMME_EK_VK_FUHREN_REALMENGE";
//	public static String HASH_R_SUMME_RECH_GUT_POS  = 			"HASH_R_SUMME_RECH_GUT_POS";
//	public static String HASH_R_SUMME_LAGER_IN_FUHRE  = 		"HASH_R_SUMME_LAGER_IN_FUHRE";
	
	public static String HASH_BUTTON_LOCK_UNLOCK_POS  = 		"HASH_BUTTON_LOCK_UNLOCK_POS";
	
	//2012-10-26: sprungbutton vom vertragsclearing zur fuhre
	public static String HASH_BUTTON_JUMP_TO_FUHRE  = 			"HASH_BUTTON_JUMP_TO_FUHRE";
	
	//bei der spaltenfilterung sind diese varianten aktiv
	public static enum filterType {
		blank(new E2_ColorBase(),	"undefiniert")
		,green(Color.GREEN, 		"95% - 105% (grün)")
		,yellow(Color.YELLOW, 		"85% - 115% (gelb)")
		,red(Color.RED,    			"<85% oder > 115% (rot)")
		;
		
		private Color   color = null;
		private String  text4cb = null;
		
		private filterType(Color col, String  p_text4cb) {
			this.color = 	col;
			this.text4cb = 	p_text4cb;
		}
		public Color getColor() {
			return color;
		}
		public String getText4cb() {
			return text4cb;
		}
	}
	

	
	//keys der filterspalten
	public static enum FILTERCOLUMN implements if_enum_helpers<FILTERCOLUMN>{
		HASH_R_SUMME_LAGER_IN_FUHRE("Lag.schon in Fuhre",			CLEARINGTAGS.MengeLagerInFuhren_echt_oder_plan_netto, 	CLEARINGTAGS.MengeLager,true, "Menge der Lagerzuordnungen, die bereits in einer Fuhre stehen (Lade/Ablademenge oder wenn diese noch leer ist, Planmenge)")
		,HASH_R_MENGE_SUMME_ZUORDNUNG("??s+Lager(Plan)",			CLEARINGTAGS.GesamteMengeZuordnung, 					CLEARINGTAGS.MengeKontrakt,true, "Menge aller Lager- und Kontrakt-Zuordnungen zu dieser Kontraktposition")
		,HASH_R_MENGE_SUMME_DIFFERENZ("Fehl-/Übermenge",			CLEARINGTAGS.RestMengeNochOffen_in_der_Planung, 		CLEARINGTAGS.MengeKontrakt,false, "Menge aller Lager- und Kontrakt-Zuordnungen zu dieser Kontraktposition (Differenz zur Erfüllung)")
		,HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE("Fhr.-Mge Plan/Echt",	CLEARINGTAGS.MengeFuhreGesamtPlan_oder_Echt_netto, 		CLEARINGTAGS.MengeKontrakt,true,"Menge zu dieser Kontraktposition, die in einer Fuhre oder Fuhrenort auftaucht (Lade/Ablade- oder Planmenge falls leer)")
		,HASH_R_SUMME_EK_VK_FUHREN_REALMENGE("Fhr.-Mge Echt",		CLEARINGTAGS.MengeFuhreGesamt_Echt_netto, 				CLEARINGTAGS.MengeKontrakt,true,"Menge zu dieser Kontraktposition, die in einer Fuhre oder Fuhrenort auftaucht NUR LADE/ABLADE-MENGE, Planmengen werden nicht berücksichtigt")
		,HASH_R_SUMME_RECH_GUT_POS("Menge abgerechnet",				CLEARINGTAGS.Menge_RG_Positionen_netto, 				CLEARINGTAGS.MengeKontrakt,true,"Menge zu dieser Kontraktposition, die bereits abgerechnet wurde")
		;
		
		
		private String 			titleText = null;
		private CLEARINGTAGS  	mengeCalc = null;
		private CLEARINGTAGS  	mengeBasis = null;
		private boolean         positivRechnen = true;
		private String          tooltips4headerComponents = null;
		
		
		private FILTERCOLUMN(String p_titleText, CLEARINGTAGS p_mengeCalc, CLEARINGTAGS p_mengeBasis, boolean p_positivRechnen, String p_tooltips4headerComponents) {
			this.titleText=			p_titleText;
			this.mengeCalc = 		p_mengeCalc;
			this.mengeBasis = 		p_mengeBasis;
			this.positivRechnen = 	p_positivRechnen;
			this.tooltips4headerComponents = p_tooltips4headerComponents;
		}
		
		/**
		 * 
		 * @param listTyp (EK oder VK)
		 * @return
		 */
		public String getTitleText(String listTyp) {
			if (panter.gmbh.indep.S.isEmpty(listTyp)) {
				return titleText;
			}
			return bibALL.ReplaceTeilString(this.titleText, "??", listTyp.equals("EK")?"VK":"EK");
		}

		public CLEARINGTAGS getMengeCalc() {
			return mengeCalc;
		}
		public CLEARINGTAGS getMengeBasis() {
			return mengeBasis;
		}
		public boolean isPositivRechnen() {
			return positivRechnen;
		}
		
		
		public filterType getFilterType(HashMap<CLEARINGTAGS, Double> clearingInfos) {
			
			double gesamtWert = clearingInfos.get(this.mengeBasis);
			double dWertFuerDarstellung  = clearingInfos.get(this.mengeCalc);
			
			return BSKC__CONST.pruefeEinstufung(dWertFuerDarstellung, gesamtWert, !this.isPositivRechnen());
		}

		public String getTooltips4headerComponents() {
			return this.tooltips4headerComponents;
		}
		
	}


	
	public static BSKC__CONST.filterType pruefeEinstufung(double ddWert, double dGesamtwert, boolean bVerhaeltnisFarbe_umdrehen) {
		
		filterType typRet = filterType.blank;

		if (dGesamtwert != 0) {
			double verhaeltnis = 	ddWert/dGesamtwert;

			if (bVerhaeltnisFarbe_umdrehen)	{
				verhaeltnis = Math.abs(1-verhaeltnis);
			}
			
			if (verhaeltnis>=0.95 && verhaeltnis<=1.05)  {
				typRet = filterType.green;
            } else if (verhaeltnis>=0.85 && verhaeltnis<=1.15) {
            	typRet = filterType.yellow;
            } else  {
            	typRet = filterType.red;
            }
		}
		
		return typRet;
	}
	
	
}
