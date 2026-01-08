package panter.gmbh.indep.mail;


/**
 * klasse prueft eine mailadresse auf plausibilitaet
 * 1. keine Leerzeichen enthalten
 * 2. exact 1 ADD - zeichen enthalten
 * 3. rechts vom add muss mindestens 1 punkt stehen
 */
public class MailAdressChecker
{
	boolean bIsOk = false;
	
	public MailAdressChecker(String cMailAdress)
	{
		if (cMailAdress != null)
		{
			if (!cMailAdress.trim().equals(""))
			{
				String cTest = cMailAdress.trim();
				
				int iCountADD = 0;
				int iCountDotsAfterAdd = 0;
				int iCountSpaces = 0;
				
				boolean bIsAfterADD = false;
				
				for (int i=0,iSize=cTest.length();i<iSize;i++)
				{
					if (cMailAdress.charAt(i)=='@')
					{
						bIsAfterADD = true;
						iCountADD ++;
					}
					if (cMailAdress.charAt(i)==' ')
					{
						iCountSpaces ++;
					}
					if (bIsAfterADD)
					{
						if (cMailAdress.charAt(i)=='.')
						{
							iCountDotsAfterAdd ++;
						}
					}
				}
				
				if (iCountADD == 1 && iCountSpaces==0 && iCountDotsAfterAdd>=1)
					this.bIsOk = true;
				
			}
		}
	}
	
	public boolean isOK()
	{
		return this.bIsOk;
	}
	
	
}
