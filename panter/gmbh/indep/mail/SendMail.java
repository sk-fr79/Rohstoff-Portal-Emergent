package panter.gmbh.indep.mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;



public class SendMail
{
    private String mailServerName;
    private String smtpUser;
    private String smtpPassword;
    private Address from = null;
    private Address to;
    private Address[] cc = null;
    private Address[] bcc = null;
    private String subject;
    private String text;
    private Vector<FileWithSendName> attachment;

    String cSenderAsText = null;
    String cEmpfaengerAsText = null;
    Vector<String> vCCMailAsText = null;
 
    Integer mailSmtpConnectionTimeout = 5000;
    Integer mailSmtpTimeout = 5000;
    
    
    

    /**
     * Create a SendMail object in order to send an e-mail with or without attachments. The attachments are specified by the File objects.
     *
     * @param relayServerName smtp mail server hostname (IP address)
     * @param smtpBenutzer user name (on smtp mail server) - OPTIONAL
     * @param smtpPasswort user password (on smtp mail server) - OPTIONAL
     * @param Absender mail sender ('from')
     * @param Empfaenger mail destination ('to')
     * @param ccMail carbon copy mail destination ('Cc') (vector of string)
     * @param bccMail blind carbon copy mail destination ('Bcc') (vector of string)
     * @param Betreff mail subject
     * @param Inhalt mail text
     * @param AnlageListe attached files (vector of File object)
     * @throws MailException occurs when parameters are wrong.
     */
    public SendMail(		String relayServerName, 
    						String smtpBenutzer, 
    						String smtpPasswort, 
    						String Absender, 
    						String empfaenger, 
    						Vector<String> p_ccMail, 
    						Vector<String> p_bccMail,
    						String betreff, 
    						String Inhalt, 
    						Vector<FileWithSendName> AnlageListe)
        throws MailException    {
        
    	//die vektoren uebergeben, damit diese im testfall nicht geaendert werden
    	Vector<String> ccMail = null;
    	Vector<String> bccMail = null;
    	
    	if (p_ccMail!=null) {
    		ccMail=new Vector<>(p_ccMail);
    	}
    	if (p_bccMail!=null) {
    		bccMail=new Vector<>(p_bccMail);
    	}
    	
    	String Empfaenger = null;
    	String Betreff = null;
    	
    	
    	//
    	
    	/*
    	 * falls in der web.xml eine demo-Mail-Adresse eingetragen ist, hier diese zum versenden benutzen
    	 */
    	if (S.isFull(bibALL.get_cTestMailAdresse()))   	{
    		Empfaenger = bibALL.get_cTestMailAdresse();
    		Betreff    = S.NN(betreff)+"   <original an "+empfaenger+">";
    		
    		// Im Testmodus CC-Mails umschreiben!!!
    		if (ccMail!=null) {
    			Vector<String>  v_back = new Vector<>(ccMail);
    			ccMail.clear();
    			//es werden nur 3 adressen umgesetzt, der rest wird im test ignoriert
    			for (int i=0;i<v_back.size();i++) {
    				if (i==0) {
    					ccMail.add(bibALL.get_cTestMailAdresse_cc1());
    					Betreff = Betreff+"  , CC1 original "+v_back.get(0)+" --> "+bibALL.get_cTestMailAdresse_cc1();
    				}
    				if (i==1) {
    					ccMail.add(bibALL.get_cTestMailAdresse_cc2());
    					Betreff = Betreff+"  , CC2 original "+v_back.get(1)+" --> "+bibALL.get_cTestMailAdresse_cc2();
    				}
    				if (i==2) {
    					ccMail.add(bibALL.get_cTestMailAdresse_cc3());
    					Betreff = Betreff+"  , CC3 original "+v_back.get(2)+" --> "+bibALL.get_cTestMailAdresse_cc3();
    				}
    				if (i>=3) {
    					Betreff = Betreff+"  , CC original "+v_back.get(i)+" --> ignoriert";
    				}
    			}
    		}
    		// Im Testmodus BCC-Mails umschreiben!!!
    		if (bccMail!=null) {
    			Vector<String>  v_back = new Vector<>(bccMail);
    			bccMail.clear();
    			//es werden nur 3 adressen umgesetzt, der rest wird im test ignoriert
    			for (int i=0;i<v_back.size();i++) {
       				if (i==0) {
       					bccMail.add(bibALL.get_cTestMailAdresse_cc1());
    					Betreff = Betreff+"  , BCC1 original "+v_back.get(0)+" --> "+bibALL.get_cTestMailAdresse_cc1();
       				}
    				if (i==1) {
    					bccMail.add(bibALL.get_cTestMailAdresse_cc2());
    					Betreff = Betreff+"  , BCC2 original "+v_back.get(1)+" --> "+bibALL.get_cTestMailAdresse_cc2();
    				}
    				if (i==2) {
    					bccMail.add(bibALL.get_cTestMailAdresse_cc3());
    					Betreff = Betreff+"  , BCC3 original "+v_back.get(2)+" --> "+bibALL.get_cTestMailAdresse_cc3();
    				}
    			}
    		}
    		
    		
    		//jetzt noch zeitstempel zufuegen, damit die testmailadressen immer als einzelmail auftauchen
    		Betreff = Betreff+ "   ("+bibALL.get_cTimeNowExact()+")";
    		
    	}
    	else
    	{
    		Empfaenger = empfaenger;
    		Betreff = betreff;
    	}
    		
    	
    	
    	
    	this.cEmpfaengerAsText = Empfaenger;
    	this.cSenderAsText = Absender;
    	this.vCCMailAsText = ccMail;
    	
    	
        //smtp server name test
        if ((relayServerName == null) || (relayServerName.trim().length() == 0))
        {
            throw new MailException(MailConst.SMTP_SERVER_ERROR_NO_SERVER_NAME);
        }

        mailServerName = relayServerName;

        //smptp user name and password
        if ((smtpBenutzer == null) || (smtpBenutzer.trim().length() == 0))
        {
            //  throw new MailException(MailConst.SMTP_USER_ERROR);
            smtpUser = null;
        }
        else
        {
            smtpUser = smtpBenutzer.trim();
        }

        if ((smtpPasswort == null) || (smtpPasswort.trim().length() == 0))
        {
            //  throw new MailException(MailConst.SMTP_PASSWORD_ERROR);
            smtpPassword = null;
        }
        else
        {
            smtpPassword = smtpPasswort.trim();
        }

        //absender test
        if ((Absender == null) || (Absender.trim().length() == 0))
        {
            throw new MailException(MailConst.ABSENDER_ERROR_NO_ABSENDER);
        }

        try
        {
            from = new InternetAddress(Absender);
        }
        catch (AddressException e)
        {
            throw new MailException(MailConst.ABSENDER_ERROR_WRONG_ABSENDER_FORMAT);
        }

        //empfaenger test
        if ((Empfaenger == null) || (Empfaenger.trim().length() == 0))
        {
            throw new MailException(MailConst.EMPFAENGER_ERROR_NO_EMPFAENGER);
        }

        try
        {
            to = new InternetAddress(Empfaenger);
        }
        catch (AddressException e)
        {
            throw new MailException(MailConst.EMPFAENGER_ERROR_WRONG_EMPFAENGER);
        }

        //cc test
        if ((ccMail != null) && (ccMail.size() > 0))
        {
            cc = new Address[ccMail.size()];

            for (int i = 0; i < ccMail.size(); i++)
            {
                if (ccMail.get(i) == null)
                {
                    throw new MailException(MailConst.CC_EMPFAENGER_ERROR_NO_CC_EMPFAENGER + " (poz:" + (i + 1) + ")");
                }

                if (!ccMail.get(i).getClass().toString().equals("class java.lang.String"))
                {
                    throw new MailException(MailConst.CC_EMPFAENGER_ERROR_WRONG_CC_EMPFAENGER_DATA_TYPE + " (poz:" + (i + 1) + ")");
                }

                if (((String) ccMail.get(i)).trim().length() == 0)
                {
                    throw new MailException(MailConst.CC_EMPFAENGER_ERROR_NO_CC_EMPFAENGER + " (poz:" + (i + 1) + ")");
                }

                try
                {
                    cc[i] = new InternetAddress((String) ccMail.get(i));
                }
                catch (AddressException e)
                {
                    throw new MailException(MailConst.CC_EMPFAENGER_ERROR_WRONG_CC_EMPFAENGER);
                }
            }
        }
        else
        {
            cc = null;
        }

        //2016-09-12: neue moeglich bcc hinzuzufuegen
        //bcc-test
        if ((bccMail != null) && (bccMail.size() > 0))
        {
            this.bcc = new Address[bccMail.size()];

            for (int i = 0; i < bccMail.size(); i++)
            {
                if (bccMail.get(i) == null)
                {
                    throw new MailException(MailConst.BCC_EMPFAENGER_ERROR_NO_BCC_EMPFAENGER + " (poz:" + (i + 1) + ")");
                }

                if (!bccMail.get(i).getClass().toString().equals("class java.lang.String"))
                {
                    throw new MailException(MailConst.BCC_EMPFAENGER_ERROR_WRONG_BCC_EMPFAENGER_DATA_TYPE + " (poz:" + (i + 1) + ")");
                }

                if (((String) bccMail.get(i)).trim().length() == 0)
                {
                    throw new MailException(MailConst.BCC_EMPFAENGER_ERROR_NO_BCC_EMPFAENGER + " (poz:" + (i + 1) + ")");
                }

                try
                {
                    bcc[i] = new InternetAddress((String) bccMail.get(i));
                }
                catch (AddressException e)
                {
                    throw new MailException(MailConst.BCC_EMPFAENGER_ERROR_WRONG_BCC_EMPFAENGER);
                }
            }
        }
        else
        {
            bcc = null;
        }

        
        
        
        
        //subject test
        if ((Betreff == null) || (Betreff.trim().length() == 0))
        {
            throw new MailException(MailConst.BETREFF_ERROR_NO_BETREFF);
        }

        subject = Betreff;

        //text + attachment test
        if (((Inhalt == null) || (Inhalt.trim().length() == 0)) && ((AnlageListe == null) || (AnlageListe.size() == 0)))
        {
            throw new MailException(MailConst.INHALT_ATTACHMENT_ERROR);
        }

        if (Inhalt == null)
        {
            text = "";
        }
        else
        {
            text = Inhalt.trim();
        }

        if (AnlageListe != null)
        {
            for (int i = 0; i < AnlageListe.size(); i++)
            {
                if (AnlageListe.get(i) == null)
                {
                    throw new MailException(MailConst.ANLAGE_ERROR_NO_ANLAGE2 + " (poz:" + (i + 1) + ")");
                }

                
                /*
                 * aenderung am 16.06.2006, damit auch abgeleitete file-typen zugelassen werden ..
                 */
                if (!(AnlageListe.get(i) instanceof java.io.File))
                {
                    throw new MailException(MailConst.ANLAGE_ERROR_WRONG_ANLAGE_DATA_TYPE2 + " (poz:" + (i + 1) + ")");
                }

                //check the file existence
                File f = (File) AnlageListe.get(i);

                if (!f.exists())
                {
                    throw new MailException(MailConst.ANLAGE_ERROR_WRONG_FILENAME2 + " ('" + f.getName() + "')");
                }

                if (((File) AnlageListe.get(i)).length() == 0)
                {
                    throw new MailException(MailConst.ANLAGE_ERROR_NO_ANLAGE2 + " (poz:" + (i + 1) + ")");
                }
            }
        }

        attachment = AnlageListe;
    }


    
    
    // senden von mails mit einem beliebigen mail-konto und autentifizierung
    public void sendViaStandardMail() throws MailException
    {
        FileDataSource fds = null;

        String cInfo = "";

//        String encoding 		= "8bit";
//        String charset 		= "iso-8859-15";
        String encoding 		= "quoted-printable";				
        String charset 			= "utf-8";
        String contentType 		= "text/plain";
        String subjectEncoding 	= "Q";
        
        
        try
        {
            //mail session
            Properties properties = new Properties();

            properties.put("mail.smtp.host", this.mailServerName);
            properties.put("mail.smtp.auth", "true");
            
            // Test
//            properties.put("mail.smtp.connectiontimeout", "5000");
//            properties.put("mail.smtp.timeout", "5000");
            // test - ende

            Integer iConnectionTimeout = this.mailSmtpConnectionTimeout;
            Integer iTimeout = this.mailSmtpTimeout;
            
            //jetzt evtl. differierende Werte aus den settings holen
            try {
				iConnectionTimeout = Integer.parseInt(ENUM_MANDANT_CONFIG.EMAIL_SMTP_CONNECTION_TIMEOUT.getCheckedValue());
				iTimeout = Integer.parseInt(ENUM_MANDANT_CONFIG.EMAIL_SMTP_TIMEOUT.getCheckedValue());
			} catch (Exception e) {
				e.printStackTrace();
	            iConnectionTimeout = this.mailSmtpConnectionTimeout;
	            iTimeout = this.mailSmtpTimeout;
			}
            
            
            
            //2020-11-25: sendmail-timeout-parameter veraendert
            properties.put("mail.smtp.connectiontimeout", iConnectionTimeout.toString());
            properties.put("mail.smtp.timeout",  iTimeout.toString());

            
            
            Authenticator Auth = new SendMail.SMTPAuthenticator(this.smtpUser, this.smtpPassword);

            Session session = Session.getInstance(properties, Auth);

            // create a message
            MimeMessage msg = new MimeMessage(session);

            //from
            msg.setFrom(from);

            //to + cc
            msg.addRecipient(Message.RecipientType.TO, to);

            if (cc != null)
            {
                msg.addRecipients(Message.RecipientType.CC, cc);
            }


            //2016-09-13: bcc hinzugefuegt
            if (bcc != null)
            {
                msg.addRecipients(Message.RecipientType.BCC, bcc);
            }

            
            // Message-Header setzen
            msg.setHeader("Content-Type", contentType + "; charset=" + charset);
            msg.setHeader("Content-Transfer-Encoding", encoding);
            
            
            //subject mit dem charset codieren
//          msg.setSubject(subject);
            msg.setSubject(MimeUtility.encodeText(subject, charset, subjectEncoding));

            
            //date
            msg.setSentDate(new Date());

            // create the Multipart
            Multipart mp = new MimeMultipart();

            //create and fill the first message part, the mail text
            if ((text != null) && (text.trim().length() > 0))
            {
                MimeBodyPart mbp_text = new MimeBodyPart();
                
                // text mit dem vorgegebenen charset codieren
//              mbp_text.setText(text);
                mbp_text.setText(text, charset);
                
                mp.addBodyPart(mbp_text);
            }

            
            //attachments
            if ((attachment != null) && (attachment.size() > 0))
            {
                MimeBodyPart mbp = null;

                for (int i = 0; i < attachment.size(); i++)
                {
                    // create and fill the i-th message part
                    mbp = new MimeBodyPart();

                    fds = new FileDataSource((File) attachment.get(i));
                    mbp.setDataHandler(new DataHandler(fds));

                    
                    //nachsehen, ob im E2_FileWithSendName ein unterschiedlicher sendename versteckt ist, wenn ja, dann unter diesem namen versenden
                    if (S.isFull(attachment.get(i).get_cNameFor_USER_EmailAttachment()))
                    {
                    	mbp.setFileName(attachment.get(i).get_cNameFor_USER_EmailAttachment());
                    }
                    else
                    {
                    	mbp.setFileName(attachment.get(i).getName());
                    }
                    
                    mp.addBodyPart(mbp);
                }
            }

            // add the Multipart to the message
            msg.setContent(mp);
            Transport.send(msg);
            this.write_Global_Email_Protokoll(true);
        }
        catch (NoSuchProviderException e)
        {
        	e.printStackTrace();
            this.write_Global_Email_Protokoll(false);
            throw new MailException(MailConst.NO_SUCH_PROVIDER_EXCEPTION + " (" + e.getLocalizedMessage() + ')');
        }
        catch (MessagingException e)
        {
        	e.printStackTrace();
            this.write_Global_Email_Protokoll(false);
            throw new MailException(MailConst.MESSAGING_EXCEPTION + " <" + cInfo + "> " + " (" + e.getLocalizedMessage() + ')');
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            this.write_Global_Email_Protokoll(false);
            throw new MailException("FEHLER !!! " + " (" + e.getLocalizedMessage() + ')');
        }
    }

    
    //PROTOKOLL
    private void write_Global_Email_Protokoll(boolean bOK) 
    {
    	MyE2_MessageVector  oMV = new MyE2_MessageVector();
    	
    	String cAlleEmpfaenger = this.cEmpfaengerAsText+"\n";
    	if (this.vCCMailAsText != null && this.vCCMailAsText.size()>0)
    	{
	    	for (int i=0;i<this.vCCMailAsText.size();i++)
	    	{
	    		cAlleEmpfaenger += S.NN(this.vCCMailAsText.get(i))+"\n";
	    	}
    	}
    	//fuer jedes File eine Datenbankzeile
    	String cJAHR_MONAT = bibALL.get_cDateNOWInverse("").substring(0,6);
    	

    	try
		{
			if (this.attachment!=null && this.attachment.size()>0)
			{
				Archiver oArchiver = new Archiver("EMAILAUSGANG_"+cJAHR_MONAT);

				// K O P I E R T   alle attachments und gibt den archive-komplettnamen zurueck
				for (int i=0;i<this.attachment.size();i++)
				{
					String cQuelle = this.attachment.get(i).get_cNameWithPath();
					String cZiel  = oArchiver.get_cCompleteArchivePath()+File.separator+oArchiver.copyFilenameToNextFree(cQuelle, this.attachment.get(i).get_cNameFor_USER_EmailAttachment());
					String cMimeType = this.attachment.get(i).get_oJasperFileDef().MimeType;
					
					MySqlStatementBuilder oInsert = new MySqlStatementBuilder();
					oInsert.addSQL_Paar("ID_EMAIL_PROTOKOLL", "SEQ_EMAIL_PROTOKOLL.NEXTVAL", false);
					oInsert.addSQL_Paar("ID_TABLE", "0", false);
					oInsert.addSQL_Paar("TABLENAME", "<UNDEFINED>", true);
					oInsert.addSQL_Paar("EMAIL_BETREFF", this.subject, true);
					oInsert.addSQL_Paar("EMAIL_TEXT", this.text, true);
					oInsert.addSQL_Paar("USER_KUERZEL", bibALL.get_KUERZEL(), true);
					oInsert.addSQL_Paar("ANHANG_ARCHIV_NAME", cZiel, true);
					oInsert.addSQL_Paar("MIME_TYPE", cMimeType, true);
					oInsert.addSQL_Paar("ERFOLGREICH_UEBERGEBEN", bOK?"Y":"N", true);
					oInsert.addSQL_Paar("PROGRAMM_MODUL", "<UNDEFINED>", true);
					oInsert.addSQL_Paar("VERWALTUNGSINFO", "<UNDEFINED>", true);
					oInsert.addSQL_Paar("DATUM", "SYSDATE", false);
					oInsert.addSQL_Paar("ABSENDER_ADRESSE", this.cSenderAsText, true);
					oInsert.addSQL_Paar("EMPFAENGER_ADRESSE", cAlleEmpfaenger, true);
					
					if (!bibDB.ExecSQL(oInsert.get_CompleteInsertString("JT_EMAIL_PROTOKOLL", bibE2.cTO()), true))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Schreiben des Protokolls:"));
						oMV.add_MESSAGE(new MyE2_Alarm_Message(oInsert.get_CompleteInsertString("JT_EMAIL_PROTOKOLL", bibE2.cTO()),false));
					}
					
				}
			}
		} 
    	catch (myException e)
		{
    		oMV.add_MESSAGE(e.get_ErrorMessage());
			e.printStackTrace();
		}
    	bibMSG.add_MESSAGE(oMV);
    }
    
    
    
    
    public String get_toAdress()
    {
        if (this.to != null)
        {
            return this.to.toString();
        }
        else
        {
            return "@@@ERROR@@@";
        }
    }

    // übergibt die cc-mail-adresse als textliste mit beliebigem trenner
    public String get_ccAdressList(String cTrenner)
    {
        String cRueck = "";

        if (this.cc != null)
        {
            if (this.cc.length > 0)
            {
                for (int i = 0; i < this.cc.length; i++)
                {
                    if (i == (this.cc.length - 1))
                    {
                        cRueck += this.cc[i].toString();
                    }
                    else
                    {
                        cRueck += (this.cc[i].toString() + cTrenner);
                    }
                }
            }
        }

        return cRueck;
    }


    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        String SmtpUser;
        String SmtpPassword;

        public SMTPAuthenticator(String smtpuser, String smtppassword)
        {
            this.SmtpUser = smtpuser;
            this.SmtpPassword = smtppassword;
        }

        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(this.SmtpUser, this.SmtpPassword);
        }
    }
}
