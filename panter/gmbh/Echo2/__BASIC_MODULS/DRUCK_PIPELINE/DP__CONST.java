package panter.gmbh.Echo2.__BASIC_MODULS.DRUCK_PIPELINE;

public class DP__CONST 
{
	
	public static String PP_VERARBEITUNG_TYP_STANDARD=			"NORMAL";
	public static String PP_VERARBEITUNG_TYP_STANDARD_4_user=	"Standard-Verarbeitung";

	public static String PP_VERARBEITUNG_TYP_DIREKTDRUCK=		"DIREKTDRUCK";
	public static String PP_VERARBEITUNG_TYP_DIREKTDRUCK_4_user="Direkt System-Drucker";

	public static String PP_VERARBEITUNG_TYP_EMAIL=				"EMAIL";
	public static String PP_VERARBEITUNG_TYP_EMAIL_4_user=		"Kontroll-Mail";

	public static String PP_VERARBEITUNG_TYP_ARCHIV=			"ARCHIV";
	public static String PP_VERARBEITUNG_TYP_ARCHIV_4_user=		"Archivierung";
	
	
	public static String[][] PP_ARRAY_4_DROPDOWN =  
	{
		{" - ",""},
		{DP__CONST.PP_VERARBEITUNG_TYP_STANDARD_4_user,		DP__CONST.PP_VERARBEITUNG_TYP_STANDARD},
		{DP__CONST.PP_VERARBEITUNG_TYP_DIREKTDRUCK_4_user,	DP__CONST.PP_VERARBEITUNG_TYP_DIREKTDRUCK},
		{DP__CONST.PP_VERARBEITUNG_TYP_EMAIL_4_user,		DP__CONST.PP_VERARBEITUNG_TYP_EMAIL},
		{DP__CONST.PP_VERARBEITUNG_TYP_ARCHIV_4_user,		DP__CONST.PP_VERARBEITUNG_TYP_ARCHIV}
	}; 

	
}
