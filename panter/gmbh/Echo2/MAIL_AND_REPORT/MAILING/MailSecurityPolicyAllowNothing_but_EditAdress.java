package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

public class MailSecurityPolicyAllowNothing_but_EditAdress extends MailSecurityPolicy
{
	public MailSecurityPolicyAllowNothing_but_EditAdress()
	{
		super(false, false, false, true);
	}

}
