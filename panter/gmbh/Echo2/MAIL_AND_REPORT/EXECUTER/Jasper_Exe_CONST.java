package panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER;

public class Jasper_Exe_CONST {
	/**
	 * hier wird eine globale enum gefuehrt, die alle im System benutzbaren 
	 * Executer-Typen definiert. Damit werden die moeglichen Einsprungpunkte klassifiziert
	 */
	public static enum EXECUTER_JUMPPOINTS 
	{
		JUMPPOINT_AFTER_CREATE_TEMPFILE,
		JUMPPOINT_AFTER_PRECESSING_MAILBLOCK_IN_MASSMAILER_SEND_METHOD,
		JUMPPOINT_AFTER_PROCESS_ALL_TEMPFILES,
	}


}
