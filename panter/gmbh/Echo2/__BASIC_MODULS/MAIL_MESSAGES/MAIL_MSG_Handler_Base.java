package panter.gmbh.Echo2.__BASIC_MODULS.MAIL_MESSAGES;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT_MAIL_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT_MAIL_SETTINGS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT_MAIL_SETTINGS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/**
 * Basisklasse des Handlers der die automatischen Mail-Messages verarbeitet
 * @author manfred
 * @date   31.01.2012
 */
public abstract class MAIL_MSG_Handler_Base {

	// default-Settings
	protected int 		m_Erinnerung_vor 		= -1;
	protected  boolean 	m_Erinnerung_vor_cc 	= true;
	protected int 		m_Erinnerung_nach 		= 0;
	protected boolean 	m_Erinnerung_nach_repeat = false;
	protected boolean 	m_Erinnerung_nach_cc 	= false;
	
	protected String  	m_Betreff = "Fehler bei der Zuweisung der automatischen Mails.";
	protected String  	m_Text = "Bitte kontaktieren Sie den Administrator. " ;
	
	protected Hashtable<String, String> m_htErsetzungsliste = null;
	
	
	// Vector mit den zu verschickenden Mails
	protected Vector<MAIL_MSG_Base> m_vMails = null;
	
	protected RECORD_NACHRICHT_MAIL_SETTINGS m_oSettings = null; 
	
	// Liste aller User für die Email-Adresse
	protected RECLIST_USER      m_reclistUser = null;
	
	/**
	 * Standard-Konstruktor
	 * @author manfred
	 * @date   31.01.2012
	 */
	public MAIL_MSG_Handler_Base() {
		m_vMails = new Vector<MAIL_MSG_Base>();
		m_htErsetzungsliste = new Hashtable<String, String>();
		
		// alle User lesen
		try {
			m_reclistUser = new RECLIST_USER("","");
		} catch (myException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		// Ermitteln der Settings für die Mailings
		RECLIST_NACHRICHT_MAIL_SETTINGS Settings = null;
		try {

			if (this.getMailkenner() != null){
				Settings = new RECLIST_NACHRICHT_MAIL_SETTINGS("UPPER(mail_kenner) = '" + this.getMailkenner().toUpperCase().trim() + "'" ,"");	
				if (Settings.size() >= 1){
					m_oSettings = Settings.get(0);
				} else {
					// Standardeinstellung laden
					Settings = new RECLIST_NACHRICHT_MAIL_SETTINGS("UPPER(mail_kenner) = 'DEFAULT'" );
					if (Settings.size() >= 1){
						m_oSettings = Settings.get(0);
					}				
				}
			}
			
			if (m_oSettings != null){
				// übernehmen der werte in die lokalen Variablen
				m_Erinnerung_vor 		= m_oSettings.get_ERINNERUNG_VOR_lValue((long) 0).intValue();
				m_Erinnerung_vor_cc 	= m_oSettings.get_ERINNERUNG_VOR_cUF_NN("N").equalsIgnoreCase("Y") ?  true : false;
				m_Erinnerung_nach 		= m_oSettings.get_ERINNERUNG_NACH_lValue((long) 0).intValue();
				m_Erinnerung_nach_repeat = m_oSettings.get_ERINNERUNG_NACH_REPEAT_cUF_NN("N").equalsIgnoreCase("Y") ?  true : false;;
				m_Erinnerung_nach_cc 	= m_oSettings.get_ERINNERUNG_NACH_CC_cUF_NN("N").equalsIgnoreCase("Y") ?  true : false;;
				
				m_Betreff 				= m_oSettings.get_BETREFF_cUF_NN("Automatische Mail. Kein Betreff im Setting angegeben");
				m_Text		 			= m_oSettings.get_TEXT_cUF_NN("Automatische Mail. Kein Text im Setting angegeben. Modul: " + getMailkenner() );
			}
			
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	

	/**
	 * Fügt eine Mail zum Vector der zu verschickenden Mails dazu
	 * @author manfred
	 * @date   31.01.2012
	 * @param mail
	 */
	protected void addMail (MAIL_MSG_Base mail){
		m_vMails.add(mail);
	}
	
	
	/**
	 * Verschickt die Mails die im Vector abgelegt sind
	 * setzt einen Tag, wenn nötig und loged die Mail wenn gewünscht
	 * 
	 * @author manfred
	 * @date   31.01.2012
	 */
	private void sendMails(){
		
		for (MAIL_MSG_Base mail : m_vMails){
			
			// verschicken der Mail
			mail.sendMail();
			mail.logMail();
			
			// zurückschreiben, dass die Mail schon verschickt wurde
			tagMail(mail.get_MailKenner(), mail.get_IDTable(), mail.get_SendKey() );
			
		}
	}
	
	/**
	 * true, wenn der berechnete Datumswert nach dem Termin liegt.
	 * @author manfred
	 * @date   27.04.2012
	 * @param calTerminAufgabe
	 * @param calBerechnet
	 * @return
	 */
	public long terminUeberfaelligInTagen(Calendar calTerminAufgabe, Calendar calTerminBerechnet) {
		boolean bRet = false;
		long time = calTerminBerechnet.getTime().getTime() - calTerminAufgabe.getTime().getTime() ;
		long days = Math.round( (double)time / (24. * 60.*60.*1000.) );
		return days;
	}
	
	
	
	/**
	 * Hauptroutine zum generieren und verschicken der automatischen Mails
	 * @author manfred
	 * @date   31.01.2012
	 */
	public void generateAndSendMails(){
		
		// generieren der Mails
		this.generateMails();
		
		// verschicken und loggen der Mails
		this.sendMails();
		
	}

	
	/**
	 * Ermittelt den Tag, der bezogen auf den aktuellen Tag, der letzte war, an dem gemailt werden soll
	 * die Vorlaufzeit, Nachlaufzeit und Wiederholungsfrequenz bei Nachlaufzeit wird aus der Basisklasse gesetzt
	 * @author manfred
	 * @date   26.04.2012
	 * @param termin
	 * @return
	 */
	protected Calendar getDatumMailversand(Calendar termin){
		return getDatumMailversand(termin, m_Erinnerung_vor, m_Erinnerung_nach, m_Erinnerung_nach_repeat);
	}
	
	
	/**
	 * Ermittelt den Tag, der bezogen auf den aktuellen Tag, der letzte war, an dem gemailt werden soll  
	 * @author manfred
	 * @date   01.02.2012
	 * @param termin
	 * @param Vorlaufzeit
	 * @param Nachlaufzeit
	 * @param bWiederholung
	 */
	protected Calendar getDatumMailversand(Calendar termin, int Vorlaufzeit, int Nachlaufzeit, boolean bWiederholung){
		//
		// die verschiedenen Datumswerte
		//
		
		// der nächste gefundene Termin
		Calendar naechsterTermin = null;
		
		
		// das aktuelle Datum ohne Zeit
		Calendar datum = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE),0,0,0  );

		// berechnung des Warntermins
		// der Temin der Warnungsmail
		Calendar terminWarnung = new GregorianCalendar();
		terminWarnung.setTime(termin.getTime());
		terminWarnung.add(Calendar.DATE, Vorlaufzeit * -1) ;
		
		
		
		// der letzte Termin der Wiederholungsmails
		Calendar terminWiederholung = new GregorianCalendar();
		terminWiederholung.setTime(termin.getTime());
		
		
		//
		// Nachlaufzeit ermitteln
		//
		
		// minimales Wiederholdatum ist das Termindatum
		terminWiederholung.setTime(termin.getTime());

		long seconds = datum.getTimeInMillis() - termin.getTimeInMillis();
		long days = Math.round((double)seconds / (24. * 60. * 60. * 1000.) );
		if (days > 0){
			if (Nachlaufzeit > 0){
				// letzter Nachmeldetermin in der Nachlaufzeit
				long multiplikator = days / Nachlaufzeit;
				
				// falls keine Wiederholung der Meldung nach der ersten Nachlaufzeit gewünscht ist, dann nur den termin für die 1. Meldung generieren
				if (multiplikator > 1 && !bWiederholung ){
					multiplikator = 1;
				}
				
				// Wiederholungstermin berechnen
				terminWiederholung.add(Calendar.DATE, (int)(Nachlaufzeit * multiplikator));
			}
		}

		
		
		// prüfen welcher termin der nächste ist, vom spätesten bis zum frühesten...
		naechsterTermin = new GregorianCalendar();
		if (terminWiederholung.compareTo(datum) <= 0) {
			naechsterTermin.setTime(terminWiederholung.getTime());
		} else if (termin.compareTo(datum) <= 0){
			naechsterTermin.setTime(datum.getTime());
		} else if (terminWarnung.compareTo(datum) <=0 ){
			naechsterTermin.setTime(terminWarnung.getTime());
		} else {
			// kein Termin gefunden, der kleiner oder gleich dem aktuellen Datum ist. -> null
			
			naechsterTermin = null;
		}
		
		return naechsterTermin;
	}

	
	/**
	 * prüft, ob die Mail schon einmal verschickt wurde. true wenn ja, false, wenn nein
	 * @author manfred
	 * @date   02.02.2012
	 * @param MailKenner
	 * @param ID_Table
	 * @param SendKey
	 * @return
	 */
	protected boolean isMailLogged (String MailKenner, String ID_Table, String SendKey ){
		boolean bRet = false;
		
		// entweder Sendkey oder ID_Table muss != null sein
		if (bibALL.isEmpty(ID_Table) && bibALL.isEmpty(SendKey) ){
			return bRet;
		}
		
		try {
			 
			String sWhere = 	"UPPER(MAIL_KENNER) = UPPER('" + MailKenner +  "') ";
			
			if (ID_Table != null) {
				sWhere += " AND ID_TABLE = '" + ID_Table +  "' ";
			}
			
			if (SendKey != null){
				sWhere += " AND IDENTIFIER =  '" + SendKey +  "' " ;
			}
			
			
			RECLIST_NACHRICHT_MAIL_LOG oList = new RECLIST_NACHRICHT_MAIL_LOG(sWhere ,"");
			
			if (oList.size() > 0){
				bRet = true;
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return bRet;
	}
	
	
	/**
	 * Erzeugt aus einem Array von IDs einen Vector aus Zeichen-separierten Listen in der vorgegebenen Länge 
	 * @author manfred
	 * @date   02.02.2012
	 * @param arrIDs
	 * @param indexOfArray
	 * @param start
	 * @param lengthOfSegment
	 * @return
	 */
	protected Vector<String> getVectorOfCommaSeparatedLists(String[][] arrIDs, int indexOfArray,  int lengthOfSegment){

		Vector<String> vRet = new Vector<String>();
		
		int nArrLen = arrIDs.length;

		// Anfangskriterien prüfen
		if (nArrLen <= 0){
			return vRet;
		}

		// anzahl der Segmente
		String sSegment = null;
		int nSegments = nArrLen / lengthOfSegment;
//		int nRest = nArrLen % lengthOfSegment;
		
		for (int i = 0; i <= nSegments; i++){
			sSegment = concatenate_strings(arrIDs, indexOfArray, i*lengthOfSegment, lengthOfSegment, ",");
			if (sSegment != null){
				vRet.add(sSegment);
			}
		}
		
		return vRet;
	}
	
	
	
	protected String concatenate_strings(String[][] arrIDs, int indexOfArray, int start, int length, String trenner){
		String sRet = null;
		
		// Anfangskriterien prüfen
		if (arrIDs.length  < start){
			return sRet;
		}
		if (arrIDs.length <= 0){
			return sRet;
		}

		StringBuilder sbWhere = new StringBuilder();
		
		int iEnd = start + length;
		if (iEnd > arrIDs.length){
			iEnd = arrIDs.length;
		}
		
		for (int i= start ; i< iEnd; i++)
		{
			// IN-Block schreiben
			sbWhere.append(arrIDs[i][indexOfArray]);
			sbWhere.append(trenner);
		}

		// letzen Trenner wegnehmen
		if (trenner != null && sbWhere.lastIndexOf(trenner)>0){
			sbWhere.delete(sbWhere.lastIndexOf(trenner), sbWhere.length()-1);
		}

		sRet = sbWhere.toString();
		
		return sRet;
	}

	
	
	/**
	 * löscht die Inhalte und Keys der Platzhalter
	 * @author manfred
	 * @date   26.04.2012
	 */
	protected void resetPlatzhalter(){
		m_htErsetzungsliste.clear();
	}
	
	/** 
	 * fügt einen aktuellen Platzhalterwert  
	 * @author manfred
	 * @date   26.04.2012
	 * @param key
	 * @param value
	 */
	protected String setzePlatzhalter(String key, String value){
		return m_htErsetzungsliste.put(key,value);
	}
	
	
	/**
	 * Ersetzt die Daten der Platzhalter mit den eigentlichen werten
	 * @author manfred
	 * @date   02.02.2012
	 * @param Text
	 * @param Ersetzungsstrings
	 * @return
	 */
	protected String ersetzePlatzhalterIn(String Text){
		String sTextNew = Text;
		
		for (String sPlatzhalter: m_htErsetzungsliste.keySet()){
			sTextNew = sTextNew.replaceAll(sPlatzhalter, m_htErsetzungsliste.get(sPlatzhalter));
		}
		return sTextNew;
	}
	
	
	/**
	 * Ermittelt die Email-Adresse eines Benutzers
	 * @author manfred
	 * @date   02.02.2012
	 * @param id
	 * @return
	 */
	protected String eMailAdresseVonID(String id ){
		String sRet = null;
		try {
			if (m_reclistUser != null && !bibALL.isEmpty(id)){
				RECORD_USER rec = m_reclistUser.get(id);
				if (rec != null){
					sRet = rec.get_EMAIL_cUF();
				}
			}
		} catch (myException e) {
			sRet = null;
		}
		
		return sRet;
	}

	/**
	 * Ermittelt die Email-Adresse eines Benutzers
	 * @author manfred
	 * @date   02.02.2012
	 * @param id
	 * @return
	 */
	protected String getNameVonID(String id ){
		String sRet = "";
		try {
			if (m_reclistUser != null && !bibALL.isEmpty(id)){
				RECORD_USER rec = m_reclistUser.get(id);
				if (rec != null){
					sRet = rec.get_VORNAME_cUF() + " " + rec.get_NAME1_cF();
				}
			}
		} catch (myException e) {
			sRet = "";
		}
		
		return sRet;
	}
	protected abstract void generateMails();
	
	protected abstract void tagMail (String Mailkenner, String IDTable, String SendKey);
	
	
	/**
	 * muss den Mail-Kenner für den das Modul zuständig ist, zurückgeben
	 * @author manfred
	 * @date   31.01.2012
	 * @return
	 */
	protected abstract String getMailkenner();
	
	
}
