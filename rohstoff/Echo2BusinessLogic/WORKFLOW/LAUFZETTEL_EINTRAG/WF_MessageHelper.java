package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class WF_MessageHelper {

	private RECORD_LAUFZETTEL m_Laufzettel = null;
	private RECORD_LAUFZETTEL_EINTRAG m_LaufzettelEintrag = null;
	
	private Vector<MyString> vSendedMails = null;
	private int iError = 0;
	
	
	public WF_MessageHelper(RECORD_LAUFZETTEL recLaufzettel, RECORD_LAUFZETTEL_EINTRAG recEintrag){
		vSendedMails = new Vector<MyString>();
		m_Laufzettel = recLaufzettel;
		m_LaufzettelEintrag = recEintrag;
	}
	
	
	
	
	
	/**
	 * Gibt einen Text zurück der den Eintrag des Laufzettels beschreibt
	 * Der Text kann beliebig, z.B. in eMail oder Messages benutzt werden
	 * @param recLaufzettel
	 * @param recEintrag
	 * @return
	 * @throws myException
	 */
	public String getMessageText() throws myException {
		return this.getMessageText(false);
	}
	
	/**
	 * Gibt einen Text zurück der den Eintrag des Laufzettels beschreibt
	 * Der Text kann beliebig, z.B. in eMail oder Messages benutzt werden
	 * Wenn bShortVersion == true, werden nur die Aufgaben und der Bericht, sowie der Termin zurückgegeben
	 * 
	 * 2017-12-04 Maximale Message-Länge darf nur 1900 Zeichen sein, sonst kann man die Nachricht nicht in der DB ablegen 
	 * 
	 * @param recLaufzettel
	 * @param recEintrag
	 * @param bShortVersion
	 * @return
	 * @throws myException
	 */
	public String getMessageText( boolean bShortVersion) throws myException {
		
		String sEmailTextKopf = ""; //m_sMailBodyText;
		String sEmailTextAufgabe = "";
		
		
	
		RECORD_USER 				recUser = null;
		RECLIST_LAUFZETTEL_PRIO 	reclistPrio = null;
		RECLIST_LAUFZETTEL_STATUS   reclistStatus = null;
		

		if (m_Laufzettel == null && m_LaufzettelEintrag == null) {
			return null;
		}

		
		// Status und Prio können alle gelesen werden, dann kann man die einzelnen per Index rausholen (leerer Where-Block)
		try {
			reclistPrio = new RECLIST_LAUFZETTEL_PRIO("", "");
			reclistStatus = new RECLIST_LAUFZETTEL_STATUS("","");
		} catch (myException e1) {
			// TODO Auto-generated catch block
			iError++;
			vSendedMails.add(new MyE2_String("** ERROR *** Priorität oder Status konnte nicht gelesen werden! "));
			e1.printStackTrace();
		}
		

		String sBericht = "";
		String sStatus = "";
		String sPrio = "";
		String sBearbeiter = "";
		String sBesitzer = "";
		String sAbgeschlossenVon = "";
		String sGeloescht = "";
		
		String sLaufzettelText = "";
		String sLaufzettelBesitzer = "";
		String sLaufzettelSupervisor = "";
		
		int lenKopf = 0;
		int lenAufgabe = 0;
		
		try {
			sLaufzettelText = S.NN(m_Laufzettel.get_TEXT_cUF());
			sLaufzettelBesitzer = S.NN(m_Laufzettel.get_ID_USER_BESITZER_cUF());
			sLaufzettelSupervisor = S.NN(m_Laufzettel.get_ID_USER_SUPERVISOR_cUF());
			
			if (!sLaufzettelBesitzer.equals(""))
			{
				recUser = new RECORD_USER(sLaufzettelBesitzer);
				sLaufzettelBesitzer = S.NN(recUser.get_VORNAME_cUF()) + " "
						+ S.NN(recUser.get_NAME1_cUF()) + " ("
						+ S.NN(recUser.get_EMAIL_cUF()) + ")";
			}

			if (!sLaufzettelSupervisor.equals(""))
			{
				recUser = new RECORD_USER(sLaufzettelSupervisor);
				sLaufzettelSupervisor = S.NN(recUser.get_VORNAME_cUF()) + " "
						+ S.NN(recUser.get_NAME1_cUF()) + " ("
						+ S.NN(recUser.get_EMAIL_cUF()) + ")";
			}
			
		} catch (myException e) {
			// TODO: handle exception
		}
		
		try {
		
			sGeloescht = m_LaufzettelEintrag.get_GELOESCHT_cUF_NN( "");
			sAbgeschlossenVon = m_LaufzettelEintrag.get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN( "");
			
			sBearbeiter = m_LaufzettelEintrag.get_ID_USER_BEARBEITER_cUF_NN( "");
			if (!sBearbeiter.equals(""))
			{
				recUser = new RECORD_USER(sBearbeiter);
				sBearbeiter = S.NN(recUser.get_VORNAME_cUF()) + " "
						+ S.NN(recUser.get_NAME1_cUF()) + " ("
						+ S.NN(recUser.get_NAME_cUF()) + ")";
			}

			
			sStatus = m_LaufzettelEintrag.get_ID_LAUFZETTEL_STATUS_cUF_NN("");
			if (!sStatus.equals(""))
			{
				RECORD_LAUFZETTEL_STATUS oRecStatus = reclistStatus.get(sStatus);
				sStatus = oRecStatus.get_STATUS_cUF_NN(new MyE2_String("Unbekannter Status").toString());
			}
			
			sPrio = m_LaufzettelEintrag.get_ID_LAUFZETTEL_PRIO_cUF_NN( "");
			if (!sPrio.equals(""))
			{
				RECORD_LAUFZETTEL_PRIO oRecPrio = reclistPrio.get(sPrio);
				sPrio = oRecPrio.get_PRIO_cUF_NN("-");
			}
			
			
			sBesitzer = m_LaufzettelEintrag.get_ID_USER_BESITZER_cUF_NN( "");
			if (!sBesitzer.equals(""))
			{
				recUser = new RECORD_USER(sBesitzer);
				sBesitzer = S.NN(recUser.get_VORNAME_cUF()) + " "
						+ S.NN(recUser.get_NAME1_cUF()) + " ("
						+ S.NN(recUser.get_EMAIL_cUF()) + ")";
				
			}

			//2012-02-21: die ids der laufzettel und eintraege immer mitgeben in den mails
			String cID_Laufzettel = "<id>";
			if (this.m_Laufzettel!=null)
			{
				cID_Laufzettel = "(" + this.m_Laufzettel.get_ID_LAUFZETTEL_cF_NN("-") + ")";
			}
			String cID_LaufzettelEintrag = "<id>";
			if (this.m_LaufzettelEintrag!=null)
			{
				cID_LaufzettelEintrag = "(" + this.m_LaufzettelEintrag.get_ID_LAUFZETTEL_EINTRAG_cF_NN("-")+ ")" ;
			}
			
			
			
			
			sEmailTextKopf += 	"\n\nLaufzettel: "+cID_Laufzettel+"\n" ;	
			sEmailTextKopf += 	(bShortVersion ? "" : "Besitzer: \t" + sLaufzettelBesitzer + "\n" );	
			sEmailTextKopf += 	(bShortVersion ? "" : "Supervisor: \t" + sLaufzettelSupervisor + "\n" );	
			sEmailTextKopf +=	"---------------------------------------------------------\n";	
			sEmailTextKopf +=	sLaufzettelText + "\n";	
			sEmailTextKopf +=	"---------------------------------------------------------\n\n";	
			sEmailTextKopf +=	"Bearbeiter: \t" + sBearbeiter + "\n";
			sEmailTextKopf +=   (bShortVersion ? "" : "Ersteller: \t" + sBesitzer  + "\n");	
			sEmailTextKopf +=	(bShortVersion ? "" : "Status: \t" + sStatus.trim() + "\n");
			sEmailTextKopf +=	(bShortVersion ? "" : "Priorität: \t" +sPrio.trim() + "\n");
			
			sEmailTextAufgabe +=	"Aufgabe: " +cID_LaufzettelEintrag + "\n";
			sEmailTextAufgabe +=	"Termin: \t" + m_LaufzettelEintrag.get_FAELLIG_AM_cF_NN( "-") + "\n";
			sEmailTextAufgabe +=	"---------------------------------------------------------\n";	
			sEmailTextAufgabe +=	m_LaufzettelEintrag.get_AUFGABE_cF_NN("") + "\n";
			sEmailTextAufgabe +=	"---------------------------------------------------------\n";	

			sBericht = m_LaufzettelEintrag.get_BERICHT_cF_NN( "");
			if (sBericht.trim().length() > 0)
			{
				sEmailTextAufgabe +=	"\nBericht\n";
				sEmailTextAufgabe +=	"---------------------------------------------------------\n";	
				sEmailTextAufgabe +=	sBericht + "\n";
				sEmailTextAufgabe +=	"---------------------------------------------------------\n";	
			}

			String sPrivat = "";
			if (m_LaufzettelEintrag.get_PRIVAT_cUF_NN( "N").equalsIgnoreCase("Y"))
			{
				sPrivat = "***** Eintrag ist Privat *****\n";
			}
			sEmailTextAufgabe +=	sPrivat;
			
			if (sGeloescht.equalsIgnoreCase("Y"))
			{
				sEmailTextAufgabe +=	"***** Eintrag ist gelöscht! *****" + "\n\n";
			}

			
			if (!sAbgeschlossenVon.equals(""))
			{
				recUser = new RECORD_USER(sAbgeschlossenVon);
				sAbgeschlossenVon = S.NN(recUser.get_VORNAME_cUF()) + " "
						+ S.NN(recUser.get_NAME1_cUF());
				
				sEmailTextAufgabe +=	"***** Eintrag wurde abgeschlossen von " + sAbgeschlossenVon + " am " 
				+ m_LaufzettelEintrag.get_ABGESCHLOSSEN_AM_cF_NN("-") +	" *****\n\n";
			}
			
			
			String sSendInfo = "";
			recUser = new RECORD_USER(bibALL.get_ID_USER());
			sSendInfo = S.NN(recUser.get_VORNAME_cUF()) + " "
						+ S.NN(recUser.get_NAME1_cUF()) + " ("
						+ S.NN(recUser.get_EMAIL_cUF()) + ")" + "\nTel:" + S.NN(recUser.get_TELEFON_cUF_NN("-"));

			// Absender-Infos...
			sEmailTextAufgabe += (bShortVersion ? "" :  "Die Meldung wurde verschickt von " + sSendInfo );  
								
			lenKopf = sEmailTextKopf.length();
			lenAufgabe = sEmailTextAufgabe.length();
			
			// Längen der Daten für die Nachrichten limitieren....
			if (lenKopf+lenAufgabe > 1900 ){
				if (lenAufgabe > 1900){
					lenAufgabe = 1900;
					lenKopf = 0;
				} else {
					lenKopf = 1900 - lenAufgabe;
				}
			}
			
			
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (lenKopf != sEmailTextKopf.length()){
			sEmailTextKopf = sEmailTextKopf.substring(0, lenKopf) + "....<- MELDUNG GEKÜRZT!!!\n\n"; 
		}
		if (lenAufgabe != sEmailTextAufgabe.length()){
			sEmailTextAufgabe = sEmailTextAufgabe.substring(0,lenAufgabe) + "....<- MELDUNG GEKÜRZT!!!\n\n";
		}
		
		String sEmailGesamt = sEmailTextKopf + sEmailTextAufgabe;
		
		return sEmailGesamt;
	}
	
	/**
	 * gibt eine Liste von IDs zurück, die aktuell noch mit dem Workflow beschäftigt sind
	 * @return
	 */
	public Vector<String> getListOfWFUser(boolean bFormattedIds){
		
		Vector<String> vIDs = new Vector<String>();
		
		try {
			RECLIST_LAUFZETTEL_EINTRAG reclist = m_Laufzettel.get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_laufzettel(
					" NVL(GELOESCHT,'N') = 'N' AND ID_USER_ABGESCHLOSSEN_VON IS NULL " , "", true);
			
			String id = null;
			for (RECORD_LAUFZETTEL_EINTRAG oRec : reclist.values()){
				
				id = bFormattedIds ? oRec.get_ID_USER_BESITZER_cF() : oRec.get_ID_USER_BESITZER_cUF() ;
				if (id != null && !vIDs.contains(id) ){
					vIDs.add(id);
				}
				
				id = bFormattedIds ? oRec.get_ID_USER_BEARBEITER_cF(): oRec.get_ID_USER_BEARBEITER_cUF();
				if (id != null && !vIDs.contains(id) ){
					vIDs.add(id);
				}
			}
			
			// dann noch der besitzer und Supervisor
			id = bFormattedIds ? m_Laufzettel.get_ID_USER_BESITZER_cF(): m_Laufzettel.get_ID_USER_BESITZER_cUF();
			if (id != null && !vIDs.contains(id) ){
				vIDs.add(id);
			}
			
			id = bFormattedIds ? m_Laufzettel.get_ID_USER_SUPERVISOR_cF():m_Laufzettel.get_ID_USER_SUPERVISOR_cUF();
			if (id != null && !vIDs.contains(id) ){
				vIDs.add(id);
			}
			
			
		} catch (myException e) {
			// 
		}
		
		return vIDs;
	}
	
}
