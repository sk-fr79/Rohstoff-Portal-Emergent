package panter.gmbh.indep.mail;

import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class SendMail_STD extends SendMail 
{

	public SendMail_STD(String empfaenger, Vector<String> ccMail,Vector<String> bccMail, String betreff, String Inhalt, Vector<FileWithSendName> AnlageListe) throws MailException, myException 
	{
		super(	bibALL.get_cMAILSERVER_FOR_SMTP_RELAYING(), 
				bibALL.get_cMAILUSERNAME_FOR_SMTP_RELAYING(), 
				bibALL.get_cMAILPASSWORD_FOR_SMTP_RELAYING(),
				bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("mail@testmail.de"),
				empfaenger,
				ccMail,
				bccMail,
				betreff, Inhalt, AnlageListe);
	}
	
	public SendMail_STD(String absender, String empfaenger, Vector<String> ccMail, Vector<String> bccMail, String betreff, String Inhalt, Vector<FileWithSendName> AnlageListe) throws MailException, myException 
	{
		super(	bibALL.get_cMAILSERVER_FOR_SMTP_RELAYING(), 
				bibALL.get_cMAILUSERNAME_FOR_SMTP_RELAYING(), 
				bibALL.get_cMAILPASSWORD_FOR_SMTP_RELAYING(),
				absender,
				empfaenger,
				ccMail,
				bccMail,
				betreff, Inhalt, AnlageListe);
	}

	
}
