package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;


public class FSMS_Const {

	public static String FSMS_STATUS_SOLL = 	"SOLL";
	public static String FSMS_STATUS_IST = 	"IST";
	
	public static String[][] FSMS_Status_DD =
		{
			{"-",""},
			{"Soll", FSMS_Const.FSMS_STATUS_SOLL},
			{"Ist",  FSMS_Const.FSMS_STATUS_IST},
		};
	
	public static String  FSMS_MASK_KEY_SETZE_DATUM_UHRZEIT = "SETZE_DATUM_UHRZEIT";
	
	public static String FSMS_TYP_LIEFERANT = 	"LIEF";
	public static String FSMS_TYP_ABNEHMER = 	"ABN";
	
	public static String[][] FSMS_TYP_LIEF_ABN_DD =
		{
			{"-",""},
			{"Lieferant", FSMS_Const.FSMS_TYP_LIEFERANT},
			{"Abnehmer",  FSMS_Const.FSMS_TYP_ABNEHMER},
		};

	
}
