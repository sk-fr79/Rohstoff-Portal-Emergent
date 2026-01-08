package panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicy;
import panter.gmbh.indep.exceptions.myException;

public class MMA_MassMailerStandard extends E2_MassMailer {

	public MMA_MassMailerStandard(MMA_BasicContainer oMotherContainer, MailSecurityPolicy  oSecPolicy) throws myException {
		super(	oMotherContainer.get_TEXT_KLEMMBRETT_KENNER_FUER_BETREFF(), 
				oMotherContainer.get_TEXT_KLEMMBRETT_KENNER_FUER_BETREFF(),
				oMotherContainer.get_TEXT_NAMENSANTEIL_FUER_ARCHIV(), 
				oSecPolicy);
	}

	@Override
	public MailBlock build_MailBlock4Added_EmptyMails() throws myException
	{
		return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Freie Maileingabe>"));
	}

	@Override
	public MailBlock build_MailBlock4Added_MitarbeiterMails()	throws myException
	{
		return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Mitarbeiteradresse>"));
	}

	@Override
	public MailBlock build_MailBlock4Added_SearchedMails() throws myException
	{
		return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Firmenadresse aus Suche>"));
	}

}
