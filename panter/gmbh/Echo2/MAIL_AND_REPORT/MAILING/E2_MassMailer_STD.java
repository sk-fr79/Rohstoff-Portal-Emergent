package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/*
 * standard-massmailer ohne die moeglichkeit, neue mailblocks einzutragen
 */
public class E2_MassMailer_STD extends E2_MassMailer
{

	/**
	 * 
	 * @param mailerTextSelectorKENNER_BETREFF
	 * @param mailerTextSelectorKENNER_MAILBLOCK
	 * @param cnamensanteil_fuer_archiv
	 * @throws myException
	 */
	public E2_MassMailer_STD(	String 	mailerTextSelectorKENNER_BETREFF,
								String 	mailerTextSelectorKENNER_MAILBLOCK,
								String 	cnamensanteil_fuer_archiv) throws myException
	{
		super(mailerTextSelectorKENNER_BETREFF, mailerTextSelectorKENNER_MAILBLOCK,cnamensanteil_fuer_archiv, new MailSecurityPolicyAllowNothing());
	}

	
	
	/**
	 * 
	 * @param baseKenner
	 * @throws myException
	 */
	public E2_MassMailer_STD(	String 	baseKenner) throws myException
	{
		super(S.NN(baseKenner)+"_@BETREFF", S.NN(baseKenner)+"_@MAILTEXT",S.NN(baseKenner)+"_ARCHIV", new MailSecurityPolicyAllowNothing());
	}

	
	
	
	
	@Override
	public MailBlock build_MailBlock4Added_EmptyMails() throws myException
	{
		return null;
	}

	@Override
	public MailBlock build_MailBlock4Added_MitarbeiterMails() throws myException
	{
		return null;
	}

	@Override
	public MailBlock build_MailBlock4Added_SearchedMails() throws myException
	{
		return null;
	}

}
