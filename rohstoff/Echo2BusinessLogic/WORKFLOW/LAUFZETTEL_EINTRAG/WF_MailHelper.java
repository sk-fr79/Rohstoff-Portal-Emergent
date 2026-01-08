/**
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.Vector;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MyMailHelper;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

/**
 * 
 * Klasse zum versenden von Mails Es können einzelmails (einzelne
 * Laufzettel-Einträge) und Massenmails (Ein ganzer Laufzettel ) verschickt
 * werden.
 * 
 * @author manfred
 * 
 */
public class WF_MailHelper {
	private String m_sLaufzettelId = null;
	private String m_sLaufzettelEintrag = null;

	private boolean m_bMailWhenDeleted = false;  // momentan immer false, wird nicht von aussen gesetzt!!
	
	private boolean m_bMailWhenClosed = true;

	private String m_sMailBetreff = "";
	private String m_sMailBodyText = "";

	/**
	 * Konstruktor für das Versenden von Mails an alle Einträge
	 * 
	 * @param laufzettelId
	 */
	public WF_MailHelper(String laufzettelId) {
		this(laufzettelId, null);
	}

	/**
	 * Konstruktor für das Versenden an genau einen Empfänger
	 * 
	 * @param laufzettelId
	 * @param laufzettelEintrag
	 */
	public WF_MailHelper(String laufzettelId, String laufzettelEintrag) {
		m_sLaufzettelId = laufzettelId;
		m_sLaufzettelEintrag = laufzettelEintrag;
		this.set_MailWhenClosed(false);
		this.set_MailWhenDeleted(false);
	}

	/**
	 * @return the m_sLaufzettelId
	 */
	public String get_LaufzettelId() {
		return m_sLaufzettelId;
	}

	/**
	 * @param laufzettelId
	 *            the m_sLaufzettelId to set
	 */
	public void set_LaufzettelId(String laufzettelId) {
		m_sLaufzettelId = laufzettelId;
	}

	/**
	 * @return the m_sLaufzettelEintrag
	 */
	public String get_LaufzettelEintrag() {
		return m_sLaufzettelEintrag;
	}

	/**
	 * @param laufzettelEintrag
	 *            the m_sLaufzettelEintrag to set
	 */
	public void set_LaufzettelEintrag(String laufzettelEintrag) {
		m_sLaufzettelEintrag = laufzettelEintrag;
	}

	/**
	 * @return the m_bMailWhenDeleted
	 */
	public boolean is_MailWhenDeleted() {
		return m_bMailWhenDeleted;
	}

	/**
	 * @param mailWhenDeleted
	 *            the m_bMailWhenDeleted to set
	 */
	public void set_MailWhenDeleted(boolean mailWhenDeleted) {
		m_bMailWhenDeleted = mailWhenDeleted;
	}

	/**
	 * @return the m_bMailWhenClosed
	 */
	public boolean is_MailWhenClosed() {
		return m_bMailWhenClosed;
	}

	/**
	 * @param mailWhenClosed
	 *            the m_bMailWhenClosed to set
	 */
	public void set_MailWhenClosed(boolean mailWhenClosed) {
		m_bMailWhenClosed = mailWhenClosed;
	}

	/**
	 * @return the m_sMailBetreff
	 */
	public String get_MailBetreff() {
		return m_sMailBetreff;
	}

	/**
	 * @param mailBetreff
	 *            the m_sMailBetreff to set
	 */
	public void set_MailBetreff(String mailBetreff) {
		m_sMailBetreff = mailBetreff;
	}

	/**
	 * @return the m_sMailBodyText
	 */
	public String get_MailBodyText() {
		return m_sMailBodyText;
	}

	/**
	 * @param mailBodyText
	 *            the m_sMailBodyText to set
	 */
	public void set_MailBodyText(String mailBodyText) {
		m_sMailBodyText = mailBodyText;
	}

	/***
	 * Liest die Einträge und extrahiert die Informationen für die Mail.
	 * 
	 * @return
	 */
	public void SendMail()  throws myException
	{
		boolean bMultiMail = false;
		
		int iOK = 0;
		int iError = 0;
		Vector<MyString> vSendedMails   = new Vector<MyString>();

 
		RECORD_LAUFZETTEL 			recLaufzettel = null;
		RECLIST_LAUFZETTEL_EINTRAG 	reclistLaufzettelEintraege = null;
		RECORD_USER 				recUser = null;
		
		
		// ein Vektor mit einzelnen Mails...
		Vector<MyMailHelper> mails = new Vector<MyMailHelper>();
		

		if (S.NN(m_sLaufzettelId).trim().equals("")
				&& S.NN(m_sLaufzettelEintrag).trim().equals("")) {
			return ;
		}

		// wenn kein Laufzetteleintrag vorhanden ist, dann müssen alle
		// Laufzettel-Einträge des Laufzettels verschickt werden!
		if (S.NN(m_sLaufzettelEintrag).trim().equals("")) {
			bMultiMail = true;
		}

		if (bMultiMail) {

			try {
				recLaufzettel = new RECORD_LAUFZETTEL(m_sLaufzettelId);
				reclistLaufzettelEintraege = recLaufzettel.get_DOWN_RECORD_LIST_LAUFZETTEL_EINTRAG_id_laufzettel();
			} catch (myException e) {
				recLaufzettel = null;
				reclistLaufzettelEintraege = null;

				iError++;
				vSendedMails.add(new MyE2_String("** ERROR *** Multi-Mail: Laufzettel oder Einträge konnten nicht geladen werden! "));
				
				e.printStackTrace();
			}
		} 
		else
		{
			try {
				recLaufzettel = new RECORD_LAUFZETTEL(m_sLaufzettelId);
				reclistLaufzettelEintraege = new RECLIST_LAUFZETTEL_EINTRAG("ID_LAUFZETTEL_EINTRAG = " + m_sLaufzettelEintrag, "");
			} catch (myException e) 
			{
				reclistLaufzettelEintraege = null;
				recLaufzettel = null;

				iError++;
				vSendedMails.add(new MyE2_String("** ERROR *** Single-Mail: Laufzettel oder Eintrag konnte nicht geladen werden! "));
				
				e.printStackTrace();
			}
		}

		
		// die einzelnen Mails durchlaufen und die Mail-Objekte anlegen
		if (reclistLaufzettelEintraege != null &&  !reclistLaufzettelEintraege.isEmpty()) // get_bMapHasRecords())
		{
			String sSender = "";
			
			try
			{
				sSender = bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("");
			} 
			catch (myException e1)
			{
				e1.printStackTrace();
			}
			
			String sEmail = "";
			String sBearbeiter = "";
			String sAbgeschlossenVon = "";
			String sGeloescht = "";
		
			
			// Alle IDs der Laufzetteleinträge lesen...
			for(RECORD_LAUFZETTEL_EINTRAG oRec : reclistLaufzettelEintraege.values())
			{
				try {
			
					sEmail = "";
					sGeloescht = oRec.get_GELOESCHT_cUF_NN( "");
					sAbgeschlossenVon = oRec.get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN( "");
					
					boolean bVersendeKeineGeschlossenen = !m_bMailWhenClosed;
					
					// prüfen, ob abgeschlossene Einträge verschickt werden sollen
					if (bMultiMail && bVersendeKeineGeschlossenen && S.isFull(sAbgeschlossenVon))
					{
						continue;
					}
					// prüfen, ob gelöschte Objekte verschickt werden sollen
					if (bMultiMail && !m_bMailWhenDeleted && sGeloescht.equalsIgnoreCase("Y"))
					{
						continue;
					}
					
					sBearbeiter = oRec.get_ID_USER_BEARBEITER_cUF_NN( "");
					if (!sBearbeiter.equals(""))
					{
						recUser = new RECORD_USER(sBearbeiter);
						sEmail = S.NN(recUser.get_EMAIL_cUF());
						
						// keine Email-Adresse: überspringen 
						if (sEmail.trim().length() == 0)
						{
							iError++;
							vSendedMails.add(new MyE2_String("*** eMail-Adresse fehlt: Der Bearbeiter hat keine Email-Adresse! -> " + sBearbeiter));
						    continue;
						}
					} 
					else
					{
						// wenn kein bearbeiter angegeben ist, dann auch keine Email schreiben
						continue;
					}
		
					
					// nachdem wir alle Daten gelesen haben, noch die Email erstellen...
					String sEmailText = m_sMailBodyText;
					
					
					//
					// Laufzettel-Text ermitteln
					//
					WF_MessageHelper oMsg = new WF_MessageHelper(recLaufzettel, oRec);
					String sLaufzettelText  = oMsg.getMessageText();
					
					sEmailText += sLaufzettelText;
					
					// Mailobjekt 
					MyMailHelper oMail = new MyMailHelper(
														sSender,
														sEmail,
														new MyE2_String(m_sMailBetreff).CTrans(),
														sEmailText,null);
					
					// Attachments der Workflow-Aufgaben noch anhängen:
					//
					RECLIST_ARCHIVMEDIEN_ext rlArchivmedienNachricht = new RECLIST_ARCHIVMEDIEN_ext(_DB.LAUFZETTEL_EINTRAG, oRec.get_ID_LAUFZETTEL_EINTRAG_cUF(), null,null);
					Vector<FileWithSendName> vAttachments = new Vector<>();
					for(RECORD_ARCHIVMEDIEN recArchiv :rlArchivmedienNachricht.values()){
						String sDateiname = recArchiv.get_DATEINAME_VALUE_FOR_SQLSTATEMENT();
						String sPfad      = recArchiv.get_PFAD_VALUE_FOR_SQLSTATEMENT();
						RECORD_ARCHIVMEDIEN_ext recExt = new RECORD_ARCHIVMEDIEN_ext(recArchiv);
						vAttachments.addElement(new FileWithSendName(recExt.get__cCompletePathAndFileName(), recExt.get_DATEINAME_Orig(), JasperFileDef.findFileDef(recExt.get_DATEINAME_Orig())));
					}
					oMail.addAttachementlist(vAttachments);
					
		
					mails.add(oMail);
					
										
				} catch (myException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			/*
			 * jetzt senden
			 */
			
			for (int i=0;i< mails.size();i++)
			{
				boolean bOk=false ;
				try 
				{
					bOk = ((MyMailHelper)mails.get(i)).doSendWith_REAL_Adress();
				} 
				catch (myException e) 
				{
					// TODO Auto-generated catch block
					bOk = false;
					e.printStackTrace();
				}				
				
				if (bOk)
				{
						iOK++;
						vSendedMails.add(new MyE2_String("** OK ***        "+((MyMailHelper)mails.get(i)).get_REAL_ReceiverEmail(),false));
				}
				else
				{
					iError++;
					vSendedMails.add(new MyE2_String("** ERROR ***  "+((MyMailHelper)mails.get(i)).get_REAL_ReceiverEmail(),false));
				}

			}
		}
		
		if (iError>0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(new MyE2_String("Verschickte Mails: ").CTrans()+iOK+"   --  "+new MyE2_String("Fehlerhafte Mails: ").CTrans()+iError));
		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Verschickte Mails: ").CTrans()+iOK ));
		}

		
		E2_ConfirmBasicModuleContainer oMessageBox = new E2_ConfirmBasicModuleContainer(
				new MyE2_String("Mail-Status"),
				new MyE2_String("Liste der Adressen"),
				vSendedMails,
				new MyE2_String("OK"), 
				null,
				new Extent(500),
				new Extent(500));

		oMessageBox.show_POPUP_BOX();

	}
	
	
	
	/**
	 * Schickt eine Statusmail zum Owner der Aufgabe.
	 * 
	 * @author manfred
	 * @date   03.11.2015
	 * @param bShortVersion TODO
	 *
	 */
	@Deprecated
	public void SendStatusMailToOwnerAndSupervisor(boolean bShortVersion){
		
		int 						iOK = 0;
		int 						iError = 0;
		Vector<MyString> 			vSentMails   = new Vector<MyString>();
 
		RECORD_LAUFZETTEL 			recLaufzettel = null;
		RECORD_LAUFZETTEL_EINTRAG 	recLaufzettelEintrag = null;
		
		RECORD_USER 				recUser = null;
		
		String 						sIDEntryOwner = "";
		String 						sEmailEntryOwner = "";
		
		String 						sIDWorkflowOwner = "";

		String 						sIDWorkflowSupervisor = "";
		String 						sEmailWorkflowSupervisor = "";
		
		MyMailHelper				oMail = null;
		
		
		if (S.NN(m_sLaufzettelId).trim().equals("")
				|| S.NN(m_sLaufzettelEintrag).trim().equals("")) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Laufzetteleintrag konnte nicht versandt werden."));
			return ;
		}


		// 
		// Laufzettel und Eintrag
		//
		try {
			recLaufzettel = new RECORD_LAUFZETTEL(m_sLaufzettelId);
			recLaufzettelEintrag = new RECORD_LAUFZETTEL_EINTRAG(m_sLaufzettelEintrag);
		} catch (myException e) 
		{
			recLaufzettelEintrag = null;
			recLaufzettel = null;
			iError++;
			vSentMails.add(new MyE2_String("** ERROR *** Laufzettel-Eintrag konnte nicht geladen werden! "));
		}

		//
		// Sender (der aktuelle Bearbeiter)
		//
		String sSender = "";
		try
		{
			sSender = bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("");
		} 
		catch (myException e1)
		{
			//
		}
		
		

		//
		// Besitzer und Supervisor
		//
		try {
			// den Besitzer der aufgabe ermitteln
			sEmailEntryOwner = "";
			sIDEntryOwner = recLaufzettelEintrag.get_ID_USER_BESITZER_cUF_NN( "");
			if (!sIDEntryOwner.equals(""))
			{
				recUser = new RECORD_USER(sIDEntryOwner);
				sEmailEntryOwner = S.NN(recUser.get_EMAIL_cUF());
					
				// keine Email-Adresse: überspringen 
				if (sEmailEntryOwner.trim().length() == 0)
				{
					iError++;
					vSentMails.add(new MyE2_String("*** eMail-Adresse fehlt: Der Besitzer hat keine Email-Adresse! -> " + sIDEntryOwner));
				}
			} 
				
			
			//
			// den Supervisor des Laufzettels ermitteln, wenn leer, dann den Besitzer des Laufzettels
			//
			sIDWorkflowSupervisor 	= recLaufzettel.get_ID_USER_SUPERVISOR_cUF_NN( "");
			sIDWorkflowOwner 		= recLaufzettel.get_ID_USER_BESITZER_cUF_NN("");

			if (!bibALL.isEmpty(sIDWorkflowSupervisor))
			{
				recUser = new RECORD_USER(sIDWorkflowSupervisor);
				sEmailWorkflowSupervisor = S.NN(recUser.get_EMAIL_cUF());
			}
				
			// keine Email-Adresse des supervisors -> email des Besitzers lesen 
			if (bibALL.isEmpty(sEmailWorkflowSupervisor))
			{
				recUser = new RECORD_USER(sIDWorkflowOwner);
				sEmailWorkflowSupervisor = S.NN(recUser.get_EMAIL_cUF());
			}
			
			// Supervisor oder Besitzer des Laufzettels als CC einfügen
			Vector<String>  vCC = null;
			if (!bibALL.isEmpty(sEmailWorkflowSupervisor)){
				vCC = new Vector<String>();
				vCC.add(sEmailWorkflowSupervisor);
			}
			
			
			// keine Mail an sich selbst verschicken
			// 1. aus den VCCs raus, falls drin
			if (vCC.contains(bibALL.get_ID_USER())){
				vCC.remove(bibALL.get_ID_USER());
			}
			// 2. falls man selber der empfänger ist, den empfänger mit dem 1. aus dem VCC ersetzen. 
			// Falls keiner mehr da ist, dann keine email schicken!!
			if (sEmailEntryOwner.equals(bibALL.get_ID_USER())){
				if (vCC.size() > 0){
					sEmailEntryOwner = vCC.remove(0);
				} else {
					// es gibt weder email-Empfänger noch VCC-Empfänger!
					return;
				}
			}
			
			
			// nachdem wir alle Daten gelesen haben, noch die Email erstellen...
			String sEmailText = m_sMailBodyText;
				
			//
			// Laufzettel-Text ermitteln
			//
			WF_MessageHelper oMsg = new WF_MessageHelper(recLaufzettel, recLaufzettelEintrag);
			String sLaufzettelText  = oMsg.getMessageText(bShortVersion);
			
			sEmailText += sLaufzettelText;
			
			// Mailobjekt 
			oMail = new MyMailHelper(
											sSender,
											sEmailEntryOwner,
											new MyE2_String(m_sMailBetreff).CTrans(),
											sEmailText,
											vCC);
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Senden
		boolean bOk=false ;
		try 
		{
			bOk = oMail.doSendWith_REAL_Adress();
		} 
		catch (myException e) 
		{
			// TODO Auto-generated catch block
			bOk = false;
			e.printStackTrace();
		}				
		
		if (bOk)
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Status-Mail verschickt.").CTrans()));
		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Mail konnte nicht verschickt werden.").CTrans()));
		}
	}
	

}
