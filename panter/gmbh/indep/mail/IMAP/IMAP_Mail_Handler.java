package panter.gmbh.indep.mail.IMAP;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.util.MailSSLSocketFactory;

public class IMAP_Mail_Handler {

	
	// eMail-Adresse und Passwort
	private final String sMailAccount  ;
	private final String sMailPWD     ;
	private final String sMailHost ;
	private final String sProtocol ;
	private final String sPort;
	private final String sFolder ;
	
	private long         m_UID_start = 0;
	private int          m_FetchIDX = -1;
	
	private boolean      m_bInit = false;  // true, wenn alle noetigen Werte fuer die Anmeldung gesetzt sind
	private boolean      m_bConnected = false;

	
	
	private Store  			m_Store = null;
	private IMAPFolder 		m_CurrentFolder = null;
	private Message[] 		m_Messages = null;
	private Message         m_CurrentMessage = null;
	
	
	// für rekursive Textermittlung
	int m_iPartLevel = 0;
	String m_PartText = "";
	String m_PartHTML = "";
	
	
	/**
	 * Standard-Konstruktor für den Mail-handler
	 * 
	 * 
	 * @author manfred
	 * @date   06.03.2013
	 * @param mail_account
	 * @param mail_pwd
	 * @param host
	 * @param protocol
	 * @param folder
	 * @param port
	 * @throws myException
	 */
	public IMAP_Mail_Handler(String mail_account, String mail_pwd, String host, String protocol, String folder, String port) throws myException {
		sMailAccount = mail_account;
		sMailPWD = mail_pwd;
		sMailHost = host;
		sProtocol = protocol;
		sFolder = folder;
		sPort = port;
		
		// prüfen, ob alle Voraussetzungen gegeben sind, sonst exception
		if (sMailAccount == null) 	throw new myException("IMAP_Mail_Handler::Mail-Account ist nicht angegeben!");
		if (sMailPWD == null) 		throw new myException("IMAP_Mail_Handler::Mail-Account Passwort ist nicht angegeben!");
		if (sMailHost == null) 		throw new myException("IMAP_Mail_Handler::Mail-Host ist nicht angegeben!");
		if (sProtocol == null) 		throw new myException("IMAP_Mail_Handler::Mail-Protokoll ist nicht angegeben!");
		if (sFolder == null) 		throw new myException("IMAP_Mail_Handler::Mail-Folder ist nicht angegeben!");
		if (sPort == null) 			throw new myException("IMAP_Mail_Handler::Mail-Port ist nicht angegeben!");
		
		m_bInit = true;
	}

	
	
	/**
	 * Öffnet den Mail Account
	 * @author manfred
	 * @date   21.02.2013
	 * @throws GeneralSecurityException
	 * @throws NoSuchProviderException
	 * @throws myException
	 */
	private void open_store() throws GeneralSecurityException, NoSuchProviderException, myException {
		
		// den store erst mal schliessen falls offen
		close_store();

		
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
	
		Properties props = System.getProperties();
	    props.setProperty("mail.imaps.host" , sMailHost); 
	    props.setProperty("mail.store.protocol" , sProtocol );
	    props.setProperty("mail.imaps.port" , sPort);
	    props.setProperty("mail.smtp.ssl.enable", "true");
	    props.setProperty("mail.smtp.ssl.checkserveridentity", "true");
	    
	    props.put("mail.smtp.ssl.socketFactory", sf);
	    
//	    props.put("mail.smtp.ssl.enable", "true");
//	    props.put("mail.smtp.ssl.checkserveridentity", "true");
//	    props.put("mail.smtp.ssl.socketFactory", sf);
	      
	    Session session = Session.getDefaultInstance(props, null);

	    m_Store = session.getStore(sProtocol);
	    
	    try {
			m_Store.connect(sMailHost,sMailAccount, sMailPWD);
			
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new myException(	new MyString("Kann den Account auf dem Host nicht öffnen: ").CTrans() +  sMailHost + "::" + sMailAccount , e);
		}
	}


	
	/** 
	 * Schliesst den Mail-Account
	 * @author manfred
	 * @date   21.02.2013
	 */
	private void close_store(){
		if (m_Store != null && m_Store.isConnected()){
			try {
				m_Store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/** 
	 * Schliesst den Mail-Account
	 * @author manfred
	 * @date   21.02.2013
	 */
	private void close_folder(){
		if (m_CurrentFolder != null && m_CurrentFolder.isOpen()){
			try {
				m_CurrentFolder.close(false);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * öffnet die Mailbox 
	 * 
	 * @author manfred
	 * @date   22.02.2013
	 * @throws MessagingException
	 * @throws IOException
	 * @throws myException
	 */
	public void connect() throws MessagingException, IOException, myException {
	    try {
	    
	    	open_store();
	    	

	    } catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	    
	}

	
	
	/**
	 * Schliessen von Mailbox und Folder
	 * @author manfred
	 * @date   21.02.2013
	 */
	public void disconnect(){
		this.close_folder();
		this.close_store();
	}
	
	
	/**
	 * öffnet den Folder mit allen Meldungen
	 * und prefetched diese
	 * @author manfred
	 * @date   26.02.2013
	 * @return
	 */
	public boolean OpenFolder(){
		return OpenFolder(1);
	}
	
	/**
	 * Öffnet den Folder ab der UID die angegeben ist und prefetcht die Meldungen
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 * @throws MessagingException 
	 */
	public boolean OpenFolder(long uidStart) {
		boolean bRet = false;
		
		// den Folder schliessen
		close_folder();
		
		try {
			// den CurrentFolder ermitteln..
			m_CurrentFolder = getFolder(sFolder);
			
			if (uidStart < 1){
				uidStart = 1;
			}
			
			// und öffnen..
			m_CurrentFolder.open(Folder.READ_ONLY);
			m_Messages = m_CurrentFolder.getMessagesByUID(uidStart, UIDFolder.LASTUID);
			
			if (m_Messages.length > 0){
				FetchProfile fp = new FetchProfile();
				fp.add(FetchProfile.Item.ENVELOPE);
				fp.add(UIDFolder.FetchProfileItem.UID);
				fp.add("Message-ID");
				
				//prefetch
				m_CurrentFolder.fetch(m_Messages,fp);
				bRet = true;
			} 
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
        return bRet;
	}
	
	
	
	/**
	 * Setzt den internen Zeiger auf die nächste Message und 
	 * liest die Message aus dem Array der aller gefetchten Messages
	 * 
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public boolean fetchNextMessge(){
		boolean bRet = false;
		m_CurrentMessage = null;
		
		m_FetchIDX ++;
		
		if (m_Messages != null && m_Messages.length > m_FetchIDX){
			m_CurrentMessage = m_Messages[m_FetchIDX];
			bRet = true;
		}
		
		return bRet;
	}
	
	
	
	
	/**
	 * Öffnet den Folder 
	 * @author manfred
	 * @date   21.02.2013
	 * @param Foldername
	 * @return
	 * @throws MessagingException
	 */
	private IMAPFolder getFolder(String Foldername) throws MessagingException{
		IMAPFolder folder = null;
		if (m_Store != null && m_Store.isConnected()){
			folder = (IMAPFolder) m_Store.getFolder(Foldername);
		}
		
		return folder;
	}
	
	/**
	 * Liest die Folders des Stores aus
	 * @author manfred
	 * @date   19.02.2013
	 * @return
	 * @throws MessagingException 
	 */
	private IMAPFolder[] getFolders() throws MessagingException{
		IMAPFolder[] folders = null;
		
		if (m_Store != null && m_Store.isConnected()){
			folders = (IMAPFolder[]) m_Store.getDefaultFolder().list(sFolder);
		}
		
		return folders;
	}
	
	
	
	/**
	 * gibt einen Vector mit allen Foldernamen des Stores
	 * @author manfred
	 * @date   20.02.2013
	 * @return
	 * @throws MessagingException
	 */
	public Vector<String> getFolderNames() throws MessagingException {
		Vector<String> vFolders = new Vector<String>();
		Folder[] folders = null;
		
		if (m_Store != null && m_Store.isConnected()){
			folders = m_Store.getDefaultFolder().list("*");

			for (int i = 0; i < folders.length; i++){
				vFolders.add(folders[i].getName());
			}
			
		}
		
		return vFolders;
	}
	
	
	
	
	
	
	
	
//	private void prefetchMessages(IMAPFolder folder) throws MessagingException {
//		//  von einem Folder die ersten Meldungen lesen
//		try {
//			folder.open(Folder.READ_ONLY);
//		
//			  m_Messages = folder.getMessages();
//			
//	          FetchProfile fp = new FetchProfile();
//	          fp.add(FetchProfile.Item.ENVELOPE);
//	          fp.add("Message-ID");
//	          fp.add(UIDFolder.FetchProfileItem.UID);
//	          
//	          //add more headers, if need be
//	          folder.fetch(m_Messages,fp);
//			
//	          	
//		      System.out.println("No of Messages : " + folder.getMessageCount());
//		      System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
//		      for (int i=0; i < m_Messages.length; ++i) {
//
//		    	  Message msg = m_Messages[i];
//		    	  System.out.println("MESSAGE #" + (i + 1) + ":");
//		    	  long lUID = folder.getUID(msg);
//		    	  
//		    	  System.out.println("MsgNo:" + msg.getMessageNumber());
//		    	  System.out.println("UID:" + folder.getUID(msg));
//		    	  
//		    	  String[] header = msg.getHeader("Message-ID");
//		    	  System.out.println(header[0]);
//		    	  
//		    	  System.out.println(msg.getSubject());
//		      }       
//			
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} finally {
//			
//			if (folder.isOpen()){
//				folder.close(false);
//			}
//						
//		}
//
//	}
	
	
	
	
	/**
	 * PREFETCH!!!
	 * Liest die UID aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public Long getMailUID(){
		Long lRet = null;
		if (m_CurrentMessage == null && m_CurrentFolder != null) return lRet;

		try {
			lRet = m_CurrentFolder.getUID(m_CurrentMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return lRet;
	}
	
	
	/**
	 * PREFETCH !!
	 * Liest die MessageID aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public String getMailMessageID(){
		String sRet = null;
		if (m_CurrentMessage == null) return sRet;
		try {
			String[] header = m_CurrentMessage.getHeader("Message-ID");
	    	sRet = header[0];
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	
	
	/**
	 * Liest die 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public Date getMailSendDate(){
		Date dtRet =null;
		if (m_CurrentMessage == null) return dtRet;
		try {
			dtRet = m_CurrentMessage.getSentDate();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return dtRet;
	}
	
	
	
	/**
	 * Liest die 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public Date getMailReceiveDate(){
		Date dtRet =null;
		if (m_CurrentMessage == null) return dtRet;
		try {
			dtRet = m_CurrentMessage.getReceivedDate();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return dtRet;
	}
	
	
	/**
	 * Liest den Absender der Mail 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public String getMailFrom(){
		String sRet = "";
		if (m_CurrentMessage == null) return sRet;
		try {
			Address a_TO[] = m_CurrentMessage.getFrom();
			for (int i = 0; i < a_TO.length; i++){
				if (i > 0) { sRet += ";"; }
				
				if (a_TO[i] instanceof InternetAddress ){
					sRet =  ((InternetAddress) a_TO[i]).getAddress();
				} else {
					sRet = a_TO[i].toString();
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return sRet;
	}

	
	
	/**
	 * Liest die 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public String getMailRecipient(javax.mail.Message.RecipientType to){
		String sRet = null;
		if (m_CurrentMessage == null) return sRet;
		try {
			Address a_TO[] = m_CurrentMessage.getRecipients(to);
			if (a_TO != null){
				for (int i = 0; i < a_TO.length; i++){
					if (i > 0) {
						sRet += ";" + a_TO[i].toString();
					} else {
						sRet = a_TO[i].toString();
					}
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	
	/**
	 * Liest die
	 * TO-Liste aus und gibt sie ;-Separiert zurück 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public String getMailTo(){
		return getMailRecipient(RecipientType.TO);
	}
	
	/**
	 * Liest die
	 * CC-Liste aus und gibt sie ;-Separiert zurück 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public String getMailCC(){
		return getMailRecipient(RecipientType.CC);
	}
	
	
	
	/**
	 * Liest die 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public String getMailSubject(){
		String sRet = null;
		if (m_CurrentMessage == null) return sRet;
		try {
			sRet = m_CurrentMessage.getSubject();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	
	
	
	
	/**
	 * Liest die 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	public String getMailBodyText() {
		
		if (m_CurrentMessage == null) return null;

		m_PartText = "";
		m_PartHTML = "";
		m_iPartLevel = 0;
		
		try {
			dumpPart(m_CurrentMessage,false);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// den Plain Text zurückgeben, sonst den HTML
		return (m_PartText != null && !m_PartText.trim().equals("") ? m_PartText : m_PartHTML);
		
	}
	
	/**
	 * Liest die 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	public String getMailBodyHTML() {
		
		if (m_CurrentMessage == null) return null;

		m_PartText = "";
		m_PartHTML = "";
		m_iPartLevel = 0;
		
		try {
			dumpPart(m_CurrentMessage,false);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		// den HTML-Text zurückgeben
		return  m_PartHTML;
		
	}
	
	

	/**
	 * Rekursive Methode um die Texte aus der Mail zu extrahieren, die evtl.
	 * Geschachtelt vorliegen (durch Multipart)
	 * @author manfred
	 * @date   22.02.2013
	 * @param p
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	private void dumpPart(Part p, boolean bDumpEnvelope) throws MessagingException, IOException{
		
		// wenn der Part die Message ist, dann 
		if (bDumpEnvelope && p instanceof Message){
			if (m_iPartLevel > 1){
				dumpEnvelope((Message) p);
			}
		}
		
		/*
		 * Using isMimeType to determine the content type avoids
		 * fetching the actual content data until we need it.
		 */
		if (p.isMimeType("text/plain")) {
			if (m_iPartLevel > 1){
				add_to_text("---------------------------");
			}
			m_PartText += (String)p.getContent();
		
		} 
		if (p.isMimeType("text/html")) {
			if (m_iPartLevel > 1){
				add_to_text("---------------------------");
			}
			m_PartHTML += (String)p.getContent();

		} else if (p.isMimeType("multipart/*")) {
			
			Multipart mp = (Multipart)p.getContent();
			m_iPartLevel++;
			
			int count = mp.getCount();
			for (int i = 0; i < count; i++) {
				dumpPart(mp.getBodyPart(i),bDumpEnvelope);
			}
			
			m_iPartLevel--;
		} else if (p.isMimeType("message/rfc822")) {
			m_iPartLevel++;
			dumpPart((Part)p.getContent(),bDumpEnvelope);
			m_iPartLevel--;
		}
	}
	
		
	    
	/**
	 * Fügt eine Envelpe-Sektion in den Text ein
	 * @author manfred
	 * @date   22.02.2013
	 * @param m
	 * @throws MessagingException
	 */
	private void dumpEnvelope(Message m) throws MessagingException  {
		add_to_text("---------------------------");
		Address[] a;
		// FROM 
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++)
				add_to_text("FROM: " + a[j].toString());
		}
		
		// REPLY TO
		if ((a = m.getReplyTo()) != null) {
			for (int j = 0; j < a.length; j++)
				add_to_text("REPLY TO: " + a[j].toString());
		}
		
		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++) {
				add_to_text("TO: " + a[j].toString());
				InternetAddress ia = (InternetAddress)a[j];
				if (ia.isGroup()) {
					InternetAddress[] aa = ia.getGroup(false);
					for (int k = 0; k < aa.length; k++)
						add_to_text("  GROUP: " + aa[k].toString());
				}
			}
		}
		
		// SUBJECT
		add_to_text("SUBJECT: " + m.getSubject());
		
		// DATE
		Date d = m.getSentDate();
		add_to_text("SendDate: " + 	(d != null ? d.toString() : "UNKNOWN"));
		
	}

	
	
                                         
	/**
	 * 
	 * @author manfred
	 * @date   22.02.2013
	 * @param s
	 */
	private void add_to_text(String s) {
	   	if (true) {
	   		m_PartText += s + System.getProperty("line.separator");
	   		
	   	}
	}
	   	
	   	
	
	/**
	 * Liest die Anzahl der Anhänge 
	 * aus der aktuell gefetchten Message
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public int getMailAttachmentCount(){
		int count = 0;
		if (m_CurrentMessage == null) return count;
		
		
		MimeMultipart multipart;
		try {
			if (m_CurrentMessage instanceof MimeMessage ){
				Object messageContent = m_CurrentMessage.getContent();
				
				if (messageContent instanceof MimeMultipart){
					multipart = (MimeMultipart) m_CurrentMessage.getContent();
					
					for(int i = 0; i < multipart.getCount(); i++){
						MimeBodyPart bodyPart = (MimeBodyPart)multipart.getBodyPart(i);
						if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())){
							count++;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
	/**
	 * Speichert die Mail 
	 * @author manfred
	 * @date   25.02.2013
	 * @param filename
	 * @return
	 */
	public boolean saveMail(String filename){
		boolean bRet = false;
		if (m_CurrentMessage == null)return bRet;
		
		OutputStream out;
		try {
			out = new FileOutputStream(filename);
			m_CurrentMessage.writeTo(out);
			bRet = true;

			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
		
		return bRet;
	}
	
	
	/**
	 * Speichert den i-ten Anhang in vorgegebnem Dateinamen 
	 * aus der aktuell gefetchten Message
	 * 
	 * NB: 1 <= idx <= getMailAttachementCount() 
	 * 
	 * @author manfred
	 * @date   21.02.2013
	 * @return
	 */
	public boolean saveMailAttachment(long idx, String filename){
		boolean bRet = false;
		
		if (m_CurrentMessage == null)return bRet;
		
		int count = 0;
		
		MimeMultipart multipart;
		try {
			if (m_CurrentMessage instanceof MimeMessage ){
				Object messageContent = m_CurrentMessage.getContent();
				if (messageContent instanceof MimeMultipart){
					
					multipart = (MimeMultipart) m_CurrentMessage.getContent();
					
					for(int i = 0; i < multipart.getCount(); i++){
						MimeBodyPart bodyPart = (MimeBodyPart)multipart.getBodyPart(i);
						if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())){
							count++;
							
							if (count == idx){
								// speichern der Datei
								bodyPart.saveFile(filename);
								bRet = true;
								break;
							} 
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		return bRet;	
	}
	
	
	/**
	 * Gibt den Namen des idx-ten Anhangs zurück
	 * @author manfred
	 * @date   22.02.2013
	 * @param idx
	 * @return
	 */
	public String getMailAttachmentFilename(long idx){
		String sRet = "";
		
		if (m_CurrentMessage == null) return null;
		
		int count = 0;
		
		MimeMultipart multipart;
		try {
			if (m_CurrentMessage instanceof MimeMessage ){
				Object messageContent = m_CurrentMessage.getContent();
				if (messageContent instanceof MimeMultipart){
					
					multipart = (MimeMultipart) m_CurrentMessage.getContent();
					
					for(int i = 0; i < multipart.getCount(); i++){
						MimeBodyPart bodyPart = (MimeBodyPart)multipart.getBodyPart(i);
						if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())){
							count++;

							if (count == idx){
								// speichern der Datei
								sRet = bodyPart.getFileName();
								break;
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		return sRet;	
	}
	
	
	
	/**
	 * zur Sicherheit noch mal beim Finalize den Store und den Folder schliessen
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.disconnect();
	}
	
}




