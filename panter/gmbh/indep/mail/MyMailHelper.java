package panter.gmbh.indep.mail;

import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;


public class MyMailHelper
{
	private String cSender_Email = null;
	private String cREAL_ReceiverEmail = null;
	private String cContentText = "";
	
	private Vector<String> ccMail = null; 
	private Vector<String> bccMail = null;
	private String Betreff =null;
	
	private String cSMPT_RELAY_SERVER = null;
	private String cSMPT_RELAY_LOGIN = null;
	private String cSMPT_RELAY_PASSWORD = null;
	
	
	private Vector<MyString> 	vFortgangMessage_MyStrings = 	new Vector<MyString>();  // meldungen fuer den sendevorgang
	private Vector<Boolean> 	vFortgangOK= 					new Vector<Boolean>();        // Booleans, ob der jeweilge vorgang ok war oder nicht
	
	// 2016-06-09 Attachments einfügen
	private Vector<FileWithSendName>   vAttachmentList = new Vector<>();

	
	
	/**
	 * @param senderEmail null not allowed
	 * @param receiverEmailREAL null not allowed
	 * @param betreff			null not allowed
	 * @param cMailText 		null not allowed
	 * @param ccmail 			null allowed
	 * @throws myException
	 */
	public MyMailHelper(String 			senderEmail, 
						String 			receiverEmailREAL, 
						String 			betreff, 
						String			cMailText, 
						Vector<String> 	ccmail)
	{
		super();
		Betreff = betreff;
		ccMail = ccmail;
		cContentText = cMailText;
		cREAL_ReceiverEmail = receiverEmailREAL;
		cSender_Email = senderEmail;
		
		/*
		 * die smtp-daten dazuladen
		 */
		this.cSMPT_RELAY_SERVER = 		bibALL.get_cMAILSERVER_FOR_SMTP_RELAYING();
		this.cSMPT_RELAY_LOGIN = 		bibALL.get_cMAILUSERNAME_FOR_SMTP_RELAYING();
		this.cSMPT_RELAY_PASSWORD = 	bibALL.get_cMAILPASSWORD_FOR_SMTP_RELAYING();

	}

	
	/**
	 * Fügt eine Liste von Attachments an, die mit der Mail verschickt werden sollen.
	 * @author manfred
	 * @date 09.06.2016
	 *
	 * @param vAttachments
	 * @return
	 */
	public MyMailHelper addAttachementlist(Vector<FileWithSendName> vAttachments){
		this.vAttachmentList.addAll(vAttachments);
		return this;
	}
	
	
	
	public boolean doSendWith_REAL_Adress() throws myException
	{
		return this._doSendMail(this.cREAL_ReceiverEmail);
	}
	

	
	private boolean _doSendMail(String cMailAdress) throws myException
	{
		
		if (bibALL.isEmpty(cMailAdress))
			throw new myException("MyMailHelper:_doSendMail:Empty mailadress is not ok !");
		
		boolean bRueck = false;
		this.vFortgangMessage_MyStrings.removeAllElements();
		this.vFortgangOK.removeAllElements();
		
		SendMail oSend = null;

		
		if (new MailAdressChecker(this.cSender_Email).isOK())
		{
			this.addInfoToVector(new MyString("Aktion: Prüfe Senderadresse: OK :").CTrans()+ " "+this.cSender_Email,true);
		}
		else
		{
			this.addInfoToVector(new MyString("Aktion: Prüfe Senderadresse: FEHLER: ").CTrans()+ " "+this.cSender_Email,false);
			return false;
		}

		
		if (new MailAdressChecker(cMailAdress).isOK())
		{
			this.addInfoToVector(new MyString("Aktion: Prüfe Mailadresse: OK :").CTrans()+ " "+cMailAdress,true);
		}
		else
		{
			this.addInfoToVector(new MyString("Aktion: Prüfe Mailadresse: FEHLER :").CTrans()+ " "+cMailAdress,false);
			return false;
		}
		
		try
		{
			
			
			
			oSend = new SendMail(	this.cSMPT_RELAY_SERVER,this.cSMPT_RELAY_LOGIN,this.cSMPT_RELAY_PASSWORD,
									this.cSender_Email,cMailAdress,this.ccMail,this.bccMail, this.Betreff,this.cContentText,this.vAttachmentList);
			
			this.addInfoToVector(new MyString("Aktion: Baue SendMail-Objekt: OK:"),true);
		}
		catch (MailException ex)
		{
			oSend = null;
			this.addInfoToVector(new MyString("Aktion: Baue SendMail-Objekt: FEHLER:").CTrans()+ex.getErrorMessage(),false);
		}

		if (oSend != null)
		{
			try
			{
				oSend.sendViaStandardMail();
				this.addInfoToVector(new MyString("Aktion: Sende Mail: OK").CTrans()+ " "+cMailAdress,true);
				bRueck = true;
			}
			catch (MailException ex)
			{
				oSend = null;
				this.addInfoToVector(new MyString("Aktion: Sende Mail: FEHLER").CTrans()+ " "+cMailAdress,false);
			}
		}
		
		return bRueck;
	}
	
	
	private void addInfoToVector(MyString cMessage, boolean bOK)
	{
		this.vFortgangMessage_MyStrings.add(cMessage);
		this.vFortgangOK.add(new Boolean(bOK));
	}
	private void addInfoToVector(String cMessage, boolean bOK)
	{
		this.vFortgangMessage_MyStrings.add(new MyString(cMessage,false));
		this.vFortgangOK.add(new Boolean(bOK));
	}




	public Vector<MyString> get_vFortgangMessages()		{		return vFortgangMessage_MyStrings;	}
	public Vector<Boolean> get_vFortgangOK()			{		return vFortgangOK;	}


	public String get_REAL_ReceiverEmail() {		return cREAL_ReceiverEmail;	}
	
	
}
