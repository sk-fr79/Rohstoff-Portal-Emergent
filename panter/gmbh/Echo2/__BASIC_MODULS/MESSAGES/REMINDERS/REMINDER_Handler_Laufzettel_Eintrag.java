package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS;

import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.bibDate;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_MessageHelper;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class REMINDER_Handler_Laufzettel_Eintrag extends REMINDER_Handler_Base {

	public REMINDER_Handler_Laufzettel_Eintrag( ) {
		super();

		m_KategorieKennung = E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE;
	}

	
	@Override
	protected void initReminder(){
		// SELECT von Kennung, UserID, TITEL,  Text, Folgedatum 
		
		String sSql = "" +
				" SELECT * FROM ( " +
				" 		SELECT 'LAUFZETTEL_EINTRAG#' || E.ID_LAUFZETTEL_EINTRAG  || '#' ||E.ID_USER_BEARBEITER || '#' || to_char(E.FAELLIG_AM,'yyyy-mm-dd') as WV_KENNUNG, " +
				"  		E.ID_LAUFZETTEL_EINTRAG  " +
				" 		FROM JT_LAUFZETTEL_EINTRAG E  " + 
				" 		WHERE E.ABGESCHLOSSEN_AM is null " +
				" 		AND nvl(E.GELOESCHT,'N') = 'N' " +
				" 		AND trunc(E.FAELLIG_AM,'DD') <= trunc(sysdate,'DD') " + 
				" 		AND E.SEND_NACHRICHT = 'Y' " +
				" 	) " +
				" WHERE WV_KENNUNG NOT IN ( " +
				"      SELECT DISTINCT nvl(N.WV_KENNUNG,'*') from JT_NACHRICHT N where N.WV_KENNUNG like 'LAUFZETTEL_EINTRAG%' " + 
				" ) " ;
		
				String [][] sArrDaten = new String[0][0];
				sArrDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
				
				// die gefundenen Daten in die DB einfügen für die Adressen
				this.initEntries(sArrDaten);

	}
	
	
	
	
	/**
	 * init-Entries schreibt die Messages sofort, ohne die Behandlung der Vaterklasse
	 * 
	 * @param sArrDaten
	 */
	private void initEntries(String[][] sArrDaten) {

		int j = 0;
		

		// Abbruch, wenn array leer
		if (sArrDaten == null || sArrDaten.length <= 0){
			return;
		}

		MESSAGE_Handler 	handler = new MESSAGE_Handler();
		String 				sIDUser = null; 
		RECORD_LAUFZETTEL 	recLZ 	= null;
		
		RECORD_LAUFZETTEL_EINTRAG rec = null;
		if (sArrDaten != null){
			// sonst den Vektor füllen
			for (int i = 0; i < sArrDaten.length; i++){
				rec = null;

				String wv_kennung 				= sArrDaten[i][0];
				String id_laufzettel_eintrag	= sArrDaten[i][1];
				
				// lesen "for Update" um den aktuellen DS zu sperren
				String sSql = "SELECT * FROM JT_LAUFZETTEL_EINTRAG WHERE ID_LAUFZETTEL_EINTRAG = "+ id_laufzettel_eintrag + " FOR UPDATE NOWAIT";
				
				try {
					// schmeisst eine Exception, falls der Eintrag nicht gelockt werden kann
					rec = new RECORD_LAUFZETTEL_EINTRAG(sSql);

					
					// prüfen, ob für diesen Record nicht schon ein Eintrag vorhanden ist.
					RECLIST_NACHRICHT rlNachricht = new RECLIST_NACHRICHT("WV_KENNUNG = '" + wv_kennung + "'", "" );
					if (rlNachricht.size() > 0){
						// lock aufheben
						bibDB.Rollback();
						// nächster Satz
						continue;
					}
					
					// den Laufzettel lesen
					recLZ = rec.get_UP_RECORD_LAUFZETTEL_id_laufzettel();
					
					// der User der Versendet sollte der User sein, der die Aufgabe angelegt hat und 
					// die User die benachrichtig werden sollen
					String idUserAngelegt = null;
					HashSet<String> hsKeys = new HashSet<String>();

					
					// Besitzer der Aufgabe
					if (rec.get_ID_USER_BESITZER_cUF() != null) {
						idUserAngelegt = rec.get_ID_USER_BESITZER_cUF();
						hsKeys.add(idUserAngelegt);
					} else {
						// TODO: mailsender = Systemuser
					}


					// Bearbeiter der Aufgabe
					if (rec.get_ID_USER_BEARBEITER_cUF() != null) {
						hsKeys.add(rec.get_ID_USER_BEARBEITER_cUF());
					}
					// Besitzer des Laufzettels
					if (recLZ.get_ID_USER_BESITZER_cUF() != null) {
						hsKeys.add(recLZ.get_ID_USER_BESITZER_cUF());
					}
					// Supervisor des Laufzettels
					if (recLZ.get_ID_USER_SUPERVISOR_cUF() != null) {
						hsKeys.add(recLZ.get_ID_USER_SUPERVISOR_cUF());
					}
					Vector<String> vUser = new Vector<String>();
					vUser.addAll(hsKeys);

					String sDate  			= bibDate.Date2StringISO(new Date());
					
					String sMessageText = "";
					WF_MessageHelper oMsgHlp = new WF_MessageHelper(recLZ, rec);
					sMessageText = oMsgHlp.getMessageText(true);
					
					// Nachricht als sofortnachricht
					boolean bSofort = bib_Settigs_Mandant.get_Workflow_Nachricht_as_Sofortnachricht();
					
					// generieren der Meldung
					MESSAGE_Entry msgEntry = new MESSAGE_Entry().
							set_Titel(new MyString("Laufzettel").CTrans()).
							set_Nachricht("Die Aufgabe von \n" + sMessageText + "\nsteht zur Bearbeitung an!")
							.set_vIdEmpfaenger(vUser)
							.set_Sofortanzeige(bSofort)
							.set_DtAnzeigeAb(sDate)
							.add_Target( new MESSAGE_Entry_Target(rec.get_ID_LAUFZETTEL_cUF(), E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, null, null))
							.set_Kategorie(m_KategorieKennung)
							.set_SendEmail(true)
							.set_KeyReminder(wv_kennung)
							.set_IDUserSender(idUserAngelegt)
							;

					
					// Attachments der Workflow-Aufgabe noch an die Nachricht anhängen:
					//
					RECLIST_ARCHIVMEDIEN_ext rlArchivmedienNachricht = new RECLIST_ARCHIVMEDIEN_ext(_DB.LAUFZETTEL_EINTRAG, rec.get_ID_LAUFZETTEL_EINTRAG_cUF(), null,null);
					Vector<FileWithSendName> vAttachments = new Vector<>();
					for(RECORD_ARCHIVMEDIEN recArchiv :rlArchivmedienNachricht.values()){
						msgEntry.add_Archivmedien(recArchiv);
					}
					
					handler.saveMessage(msgEntry);

					
				} catch (myException e) {
					e.printStackTrace();
				} finally {
					// ein Rollback, damit die Sperre aufgehoben wird
					bibDB.Rollback();
				}
								
			}
		}
		
	}




	
}
