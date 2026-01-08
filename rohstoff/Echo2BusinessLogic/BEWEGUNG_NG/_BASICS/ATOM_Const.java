package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS;

import panter.gmbh.Echo2.E2_ResourceIcon;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_STATUS;

public class ATOM_Const {
	
	// Haupttypen für die Läger
	public static long ATOM_LagerTyp_Lager = 0;
	public static long ATOM_LagerTyp_Kunde = 1;
	public static long ATOM_LagerTyp_Strecke = 2;
	
	public static long ATOM_LagerTyp_Offlager = 3;
	
//	// Attribute für das Offlager
//	public static String ATOM_LagerAttrib_Mengenkorrektur 	= "MK";
//	public static String ATOM_LagerAttrib_KorrekturHand 	= "KH";
//	public static String ATOM_LagerAttrib_UmbuchungHand 	= "UH";
//	public static String ATOM_LagerAttrib_LagerLager 		= "LL";
//	public static String ATOM_LagerAttrib_Sortenwechsel 	= "SW";
//	public static String ATOM_LagerAttrib_Mixed 			= "MI";
	
	
//	public static String VEKTOR_VARIANTE_WE = "WE";
//	public static String VEKTOR_VARIANTE_WA = "WA";
//	public static String vEKTOR_VARIANTE_UMB_HAND = "UMB";
//	public static String VEKTOR_VARIANTE_S = "S";
//	public static String VEKTOR_VARIANTE_MIXED = "MI";
//	public static String VEKTOR_VARIANTE_LL = "LL";
//	public static String VEKTOR_VARIANTE_KORR_HAND = "KORR";
//	public static String VEKTOR_VARIANTE_MK = "MK";
//	public static String VEKTOR_VARIANTE_SW = "SW";
	
	
	// ---- 
	//public static String ATOM_LAGERKENNUNG_KorrekturHand 	= "KH";
	//public static String ATOM_LAGERKENNUNG_LagerLager 		= "LL";
	//public static String ATOM_LAGERKENNUNG_Mixed 			= "MI";
	//public static String ATOM_LAGERKENNUNG_Mengenkorrektur 	= "MK";
	//public static String ATOM_LAGERKENNUNG_Strecke 			= "STRECKE";
	//public static String ATOM_LAGERKENNUNG_Sortenwechsel 	= "SW";
	//public static String ATOM_LAGERKENNUNG_UmbuchungHand 	= "UH";
	
	public static String ATOM_Handbuchung_Leistungszeit = "23:58";
	
	
	public static E2_ResourceIcon Icon(ENUM_VEKTOR_STATUS status) {
		E2_ResourceIcon  icon = E2_ResourceIcon.get_RI("");
		
		switch (status) {
		case AKTIV:
			icon = E2_ResourceIcon.get_RI("fz_lkw_aktiv.png");
			break;
		case STORNIERT:
			icon = E2_ResourceIcon.get_RI("fz_lkw_storniert.png");
			break;
		case FINAL:
			icon = E2_ResourceIcon.get_RI("fz_lkw_final.png");
			break;
		case GEPLANT:
			icon = E2_ResourceIcon.get_RI("fz_lkw_geplant.png");
			break;
		}
		return icon;
	}
	


	
	
}
