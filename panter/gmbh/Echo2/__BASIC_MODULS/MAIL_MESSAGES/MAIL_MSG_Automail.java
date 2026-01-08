package panter.gmbh.Echo2.__BASIC_MODULS.MAIL_MESSAGES;

/**
 * Hauptklasse zum ausführen der Automail-Funktion
 * @author manfred
 * @date   02.02.2012
 */
public class MAIL_MSG_Automail {

	MAIL_MSG_Handler_Base mailHandler = null;
	
	
	public MAIL_MSG_Automail() {
	
	}
	
	
	public void runAutomailGeneration(){
		
		// Laufzettel-Mails
		runMails( new MAIL_MSG_Handler_Laufzettel() );

		// weitere Mails
		
	}
	
	
	private void runMails(MAIL_MSG_Handler_Base messageHandler){
		messageHandler.generateAndSendMails();
	}
	
}
