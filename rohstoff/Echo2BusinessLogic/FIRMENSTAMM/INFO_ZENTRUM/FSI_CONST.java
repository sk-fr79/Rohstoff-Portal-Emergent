package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM;

import java.util.Vector;

public class FSI_CONST
{
	public static String      KON_BUCHNUMMER = 		"KON_BUCHNUMMER";
	public static String      KON_FR_BEST_NUMMER = 	"KON_FR_BEST_NUMMER";
	public static String      KON_DRUCKDATUM = 		"KON_DRUCKDATUM";
	public static String      KON_GUELTIG = 		"KON_GUELTIG";
	public static String      KON_MENGE = 			"KON_MENGE";
	public static String      KON_FU_PLAN_MENGE = 	"KON_FU_PLAN_MENGE";
	public static String      KON_FU_LADE_MENGE = 	"KON_FU_LADE_MENGE";
	public static String      KON_REST_MENGE = 		"KON_REST_MENGE";

	public static String      KON_RECH_GUT = 		"KON_RECH_GUT";
	public static String      KON_ANR1_2 = 			"KON_ANR1_2";
	public static String      KON_ARTBEZ1 = 		"KON_ARTBEZ1";
	public static String      KON_ARTBEZ2 = 		"KON_ARTBEZ2";
	public static String      KON_PREIS = 			"KON_PREIS";
	

	public static Vector<String> vKONTRAKT_SORTERS = new Vector<String>();
	static
	{
		
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_BUCHNUMMER);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_FR_BEST_NUMMER);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_DRUCKDATUM);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_GUELTIG);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_MENGE);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_FU_PLAN_MENGE);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_FU_LADE_MENGE);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_REST_MENGE);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_RECH_GUT);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_ANR1_2);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_ARTBEZ1);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_ARTBEZ2);
		vKONTRAKT_SORTERS.add(FSI_CONST.KON_PREIS);
	}
	
	
	
}
