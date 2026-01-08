package panter.gmbh.Echo2.__BASIC_MODULS.MAIL_MESSAGES;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.exceptions.myException;


public class MAIL_MSG_Handler_Laufzettel extends MAIL_MSG_Handler_Base {

	@Override
	protected String getMailkenner() {
		return "LAUFZETTEL_EINTRAG";
	}	
	
	public MAIL_MSG_Handler_Laufzettel() {
		super();
	}
	
	
	@Override
	protected void generateMails() {
		// Standard für Warn-Zeitpunkt setzen, falls keiner in der Datenbank vorgegeben wurde
		if ( m_Erinnerung_vor < 0) {
			m_Erinnerung_vor = 20;
		}
		
		String sSqlLaufzettelIDs = 	" SELECT  LE.* " +
									" FROM " + bibE2.cTO() + ".JT_LAUFZETTEL_EINTRAG LE " + 
									" INNER JOIN  " + bibE2.cTO() + ".JT_LAUFZETTEL LZ  " + 
									"        ON LZ.ID_MANDANT = LE.ID_MANDANT " +  
									"        AND LZ.ID_LAUFZETTEL = LE.ID_LAUFZETTEL " + 
									" WHERE LZ.ABGESCHLOSSEN_AM is null " + 
									"   	AND LE.ABGESCHLOSSEN_AM is null " + 
									" 		AND nvl(LZ.GELOESCHT,'N') = 'N' " + 
									" 		AND nvl(LE.GELOESCHT,'N') = 'N' " + 
									" 		AND LE.FAELLIG_AM is not null " +
									" 		AND LE.FAELLIG_AM <= SYSDATE  + " + Integer.toString(m_Erinnerung_vor) ;

		
		Calendar cal = Calendar.getInstance();
		Calendar caltermin = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sMailIdentifier = null;
		MAIL_MSG_Base mail = null;
		
		
		Hashtable<String, String> htPlatzhalter;
		RECORD_LAUFZETTEL recLaufzettel = null;
		
		// alle möglichen Laufzettel-Einträge lesen
		RECLIST_LAUFZETTEL_EINTRAG recList;
		
		try {
			recList = new RECLIST_LAUFZETTEL_EINTRAG(sSqlLaufzettelIDs);
			
			// jetzt die einzelnen Einträge durchgehen und schauen, ob die Mail verschickt werden muss. 
			for (RECORD_LAUFZETTEL_EINTRAG lz: recList.values()){
				// nächsten Mailtermin ermitteln über Basisklasse
				
				try {
					// der eingetragene Termin
					caltermin.setTime(df.parse(lz.get_FAELLIG_AM_cUF()) );
					
					// errechnen des nächsten Maildatums anhand des Termins und der gesetzten Vor- bzw. Nachlaufzeiten
					cal = getDatumMailversand(caltermin);
					// wenn ein Datum für den Mailversand gefunden wurde
					if (cal != null){

						DEBUG.System_println("Nächster email-Termin: " + cal.getTime().toString(), DEBUG.DEBUG_FLAG_DIVERS1);
						
						String idAddress = ( lz.get_ID_USER_BEARBEITER_cUF() != null ? lz.get_ID_USER_BEARBEITER_cUF() : lz.get_ID_USER_BESITZER_cUF());
						boolean bKeinBearbeiter = (lz.get_ID_USER_BEARBEITER_cUF() == null) ;
						
						// prüfen, ob die Mail schon mal verschickt wurde
						// mail-ID ist das Datum
						sMailIdentifier = df.format(cal.getTime()) + "#" + idAddress;
						
						if (!isMailLogged(getMailkenner(), lz.get_ID_LAUFZETTEL_EINTRAG_cUF(), sMailIdentifier)){
							
							recLaufzettel = lz.get_UP_RECORD_LAUFZETTEL_id_laufzettel();
							
							// Platzhalter definieren
							resetPlatzhalter();
							
							setzePlatzhalter("#name_laufzettel_besitzer#",   getNameVonID(recLaufzettel.get_ID_USER_BESITZER_cUF_NN("") ));
							setzePlatzhalter("#name_laufzettel_supervisor#", getNameVonID(recLaufzettel.get_ID_USER_SUPERVISOR_cUF_NN("") ));
							setzePlatzhalter("#laufzettel_beschreibung#",recLaufzettel.get_TEXT_cUF_NN("") );
							setzePlatzhalter("#laufzettel_eintrag_aufgabe#",lz.get_AUFGABE_cUF_NN("") );
							setzePlatzhalter("#name_besitzer#", getNameVonID(lz.get_ID_USER_BESITZER_cUF_NN("") ));
							setzePlatzhalter("#name_bearbeiter#", getNameVonID(lz.get_ID_USER_BEARBEITER_cUF_NN("") ));
							setzePlatzhalter("#termin#", lz.get_FAELLIG_AM_cF_NN(""));
							
							setzePlatzhalter("id_laufzettel", lz.get_ID_LAUFZETTEL_cUF());
							setzePlatzhalter("id_laufzettel_eintrag", lz.get_ID_LAUFZETTEL_EINTRAG_cUF());
							
							// erzeuge eine Mail and den Bearbeiter
							mail = new MAIL_MSG_Base() ;
							
							mail.set_Address(eMailAdresseVonID(idAddress));
							
							mail.set_IDTable(lz.get_ID_LAUFZETTEL_EINTRAG_cUF());
							mail.set_MailKenner(getMailkenner());
							mail.set_Sender(eMailAdresseVonID(lz.get_ID_USER_BESITZER_cUF()) );
							mail.set_SendKey(sMailIdentifier);
							
							mail.set_Heading ( ersetzePlatzhalterIn(m_Betreff));
							mail.set_Text    ( ersetzePlatzhalterIn( m_Text) );
							
							// adressen vervollständigen
							if ( (m_Erinnerung_vor_cc || m_Erinnerung_nach_cc ) && !bKeinBearbeiter){
								mail.add_CC(eMailAdresseVonID(lz.get_ID_USER_BESITZER_cUF()));
								mail.add_CC(eMailAdresseVonID(recLaufzettel.get_ID_USER_BESITZER_cUF())) ;
								mail.add_CC(eMailAdresseVonID(recLaufzettel.get_ID_USER_SUPERVISOR_cUF())) ;
							}
							
							addMail(mail);
						}
						
					}
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
			}
		} catch (myException e1) {
			
			e1.printStackTrace();
		}
				
	}

			
	

	
	
	
	
	@Override
	protected void tagMail(String Mailkenner, String IDTable, String SendKey) {
		// hier muss man nichts mit dem Orginal-Satz anfangen
	}




	
	
	
}
