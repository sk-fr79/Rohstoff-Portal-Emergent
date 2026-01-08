package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

public class MailSecurityPolicy
{

	/*
	 * schalter, um die zusatz-mail-adressen zu aktivieren
	 */
	public boolean								bAllowNewEmptyAdress = true;
	public boolean								bAllowAddMailFromAdressSeach = true;
	public boolean								bAllowAddMailFromEployesPopup = true;
	
	
	/*
	 * variable bestimmt, ob eine aenderung der mailadressen moeglich sein soll
	 */
	public boolean 		 						bAllowChangeMailAdress = false;
	

	/**
	 * 
	 * @param allowNewEmptyAdress
	 * @param allowAddMailFromAdressSeach
	 * @param allowAddMailFromEployesPopup
	 * @param allowChangeMailAdresse
	 */
	public MailSecurityPolicy(	boolean allowNewEmptyAdress,	
								boolean allowAddMailFromAdressSeach, 
								boolean allowAddMailFromEployesPopup,
								boolean allowChangeMailAdresse)
	{
		super();
		this.bAllowNewEmptyAdress = 		allowNewEmptyAdress;
		this.bAllowAddMailFromAdressSeach = allowAddMailFromAdressSeach;
		this.bAllowAddMailFromEployesPopup = allowAddMailFromEployesPopup;
		this.bAllowChangeMailAdress = 		allowChangeMailAdresse;
	}

	
	

}
