package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

public class MailSecurityPolicyAllowAll extends MailSecurityPolicy
{
	public MailSecurityPolicyAllowAll()
	{
		super(true, true, true,true);
	}

}
