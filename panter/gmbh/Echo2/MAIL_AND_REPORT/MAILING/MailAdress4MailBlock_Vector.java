package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.util.Vector;

import panter.gmbh.indep.S;

public class MailAdress4MailBlock_Vector extends Vector<MailAdress4MailBlock>
{

//	//sorgt dafuer, dass jede zieladresse nur einmal mitgenommen wird
//	private Vector<String>  vMailAdresses = new Vector<String>();
	
	public MailAdress4MailBlock_Vector()
	{
		super();
	}


	/*
	 * die add-methode ueberschreiben, damit, falls eine neue mailadresse ankommt, 
	 * bisher aber nur eine einzige, leere adresse vorhanden ist, diese ersetzt wird.
	 * 
	 */
	public boolean add(MailAdress4MailBlock newMailAdress)
	{
		
		//falls volle adresse zu leerer adresse dazukommt, dann ersetzen
		if (S.isFull(newMailAdress.get_eMailAdresseZiel()) && this.size()==1 && S.isEmpty(this.get(0).get_eMailAdresseZiel()))
		{
			this.removeAllElements();
		}
		
		if (S.isEmpty(newMailAdress.get_eMailAdresseZiel()))
		{
			//leere adressen werden beliebig viele angefuegt
			return super.add(newMailAdress);
		}
		else
		{
			//volle werden nur angefuegt, wenn sie nicht schon da sind
			if (!this.is_BereitsVorhanden(newMailAdress))
			{
				return super.add(newMailAdress);
			}
			else
			{
				return false;
			}
		}
//		if (!this.vMailAdresses.contains(newMailAdress.get_eMailAdresseZiel()))
//		{
//			this.vMailAdresses.add(newMailAdress.get_eMailAdresseZiel());
//			return super.add(newMailAdress);
//		}
//		else
//		{
//			return false;
//		}
	}
	
	
	
	public boolean addAll(MailAdress4MailBlock_Vector  vMailAdress4MailBlock)
	{
		for (MailAdress4MailBlock oMailAdress4MailBlock: vMailAdress4MailBlock)
		{
			this.add(oMailAdress4MailBlock);
		}
		return true;
	}
	
	
	private boolean is_BereitsVorhanden(MailAdress4MailBlock newMailAdress)
	{
		boolean bRueck = false;
		
		for (int i=0;i<this.size();i++)
		{
			if (this.get(i).get_eMailAdresseZiel().trim().equals(newMailAdress.get_eMailAdresseZiel().trim()))
			{
				bRueck = true;
				break;
			}
		}
		
		
		
		return bRueck;
	}
	
	
	
	
	
}
