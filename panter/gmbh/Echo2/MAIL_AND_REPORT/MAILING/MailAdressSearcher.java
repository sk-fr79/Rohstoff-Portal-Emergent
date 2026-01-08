package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock_Vector;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SPECIAL;

public class MailAdressSearcher extends MailAdress4MailBlock_Vector
{
	private boolean 		bBasisMailsEinbeziehen=false;
	private boolean 		bMitarbeiterEinbeziehen=false;
	private boolean 		bLieferadressenEinbeziehen=false; 
	private String 			eMailBelegtyp=null;
	private MyE2_String     eMailBelegtyp4User = null;
	
	
//	private String[]  		cAllowedTypes = {myCONST.EMAIL_TYPE_VALUE_ANGEBOT,		myCONST.EMAIL_TYPE_VALUE_RECHNUNG,		myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT,
//											 myCONST.EMAIL_TYPE_VALUE_AUFT_BEST,	myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN,	myCONST.EMAIL_TYPE_VALUE_TRANSPORT,
//											 myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT,	myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT,	myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT,
//											 myCONST.EMAIL_TYPE_VALUE_MAHNUNG,		myCONST.EMAIL_TYPE_VALUE_FIBU ,  		myCONST.EMAIL_TYPE_VALUE_LAGER };   

	//2014-12-15: neue Typ dazu
	private String[]  		cAllowedTypes = {myCONST.EMAIL_TYPE_VALUE_ANGEBOT,		myCONST.EMAIL_TYPE_VALUE_RECHNUNG,		myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT,
											 myCONST.EMAIL_TYPE_VALUE_AUFT_BEST,	myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN,	myCONST.EMAIL_TYPE_VALUE_TRANSPORT,
											 myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT,	myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT,	myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT,
											 myCONST.EMAIL_TYPE_VALUE_MAHNUNG,		myCONST.EMAIL_TYPE_VALUE_FIBU ,  		myCONST.EMAIL_TYPE_VALUE_LAGER,
											 myCONST.EMAIL_TYPE_TEXT_BUCHHALTUNG_RE_GUT};   
	
	
	
	Vector<String>  		vMailDublettenCheck = new Vector<String>();

	private MyString        InfoStringZusatzFuerMailliste = null;

	private Boolean     	bFoundEmailsAreEditable = null;
	private Boolean     	bEmptyEmailsAreEditable = null;
	
	/**
	 * 
	 * @param cid_adresse
	 * @param basisMailsEinbeziehen
	 * @param mitarbeiterEinbeziehen
	 * @param lieferadressenEinbeziehen
	 * @param mailBelegtyp
	 * @param mailBelegtyp_fuer_User
	 * @param cInfoStringZusatzFuerMailliste
	 * @param bAddEmptyMailAdressWhen_no_MailAdressFound
	 * @param FoundEmailsAreEditable
	 * @param EmptyEmailsAreEditable
	 * @throws myException
	 */
	public MailAdressSearcher(		String 		cid_adresse,
									boolean 	basisMailsEinbeziehen, 
									boolean 	mitarbeiterEinbeziehen,
									boolean 	lieferadressenEinbeziehen, 
									String 		mailBelegtyp,
									MyE2_String mailBelegtyp_fuer_User,
									MyString 	cInfoStringZusatzFuerMailliste, 
									boolean     bAddEmptyMailAdressWhen_no_MailAdressFound, 
									Boolean 	FoundEmailsAreEditable, 
									Boolean 	EmptyEmailsAreEditable) throws myException
	{
		super();
		this.bBasisMailsEinbeziehen = basisMailsEinbeziehen;
		this.bMitarbeiterEinbeziehen = mitarbeiterEinbeziehen;
		this.bLieferadressenEinbeziehen = lieferadressenEinbeziehen;
		this.eMailBelegtyp = 		mailBelegtyp;
		this.eMailBelegtyp4User = 	mailBelegtyp_fuer_User;
		
		this.InfoStringZusatzFuerMailliste = cInfoStringZusatzFuerMailliste;
		
		this.bEmptyEmailsAreEditable = EmptyEmailsAreEditable;
		this.bFoundEmailsAreEditable = FoundEmailsAreEditable;
		
		if (S.isFull(mailBelegtyp))
		{
			if (!bibALL.get_VectorAusArray(this.cAllowedTypes).contains(mailBelegtyp))
			{
				throw new myException(this,"Not Allowed MailType !");
			}
		}

		
		//adressen suchen und in den mailblock einhaengen
		RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(cid_adresse);

		if (this.bBasisMailsEinbeziehen)
		{
			//zuerst die direkten mail-adressen
			for (int k=0;k<recAdresse.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
			{
				RECORD_EMAIL oMail = recAdresse.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
				if (S.isFull(oMail.get_EMAIL_cUF_NN("")))
				{
					if (this.bCheckMail(oMail))
					{
						this.add(new ownMailAdress4MailBlock(oMail.get_EMAIL_cUF_NN(""),recAdresse,recAdresse,this.bFoundEmailsAreEditable));
					}
				}
			}
		}
		
		
		if (this.bMitarbeiterEinbeziehen)
		{
			//dann die aktiven mitarbeiter-adresse einsammeln
			for (int i=0; i<recAdresse.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().size();i++)
			{
				RECORD_ADRESSE oAdresseMitarbeiter = recAdresse.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
				if (oAdresseMitarbeiter.is_AKTIV_YES())
				{
					//dann die Mitarbeiter mail-adressen
					for (int k=0;k<oAdresseMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
					{
						RECORD_EMAIL oMailMitarbeiter = oAdresseMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
						if (S.isFull(oMailMitarbeiter.get_EMAIL_cUF_NN("")))
						{
							if (this.bCheckMail(oMailMitarbeiter))
							{
								this.add(new ownMailAdress4MailBlock(oMailMitarbeiter.get_EMAIL_cUF_NN(""),recAdresse,oAdresseMitarbeiter,this.bFoundEmailsAreEditable));
							}
						}
					}
				}
			}
		}

		if (this.bLieferadressenEinbeziehen)
		{
			//dann die aktiven liefer-adresse einsammeln
			for (int i=0; i<recAdresse.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().size();i++)
			{
				RECORD_ADRESSE oAdresseLiefer = recAdresse.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer();
				if (oAdresseLiefer.is_AKTIV_YES())
				{
					//dann die Lieferadressen - mail-adressen
					for (int k=0;k<oAdresseLiefer.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
					{
						RECORD_EMAIL oMailLieferadresse = oAdresseLiefer.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
						if (S.isFull(oMailLieferadresse.get_EMAIL_cUF_NN("")))
						{
							if (this.bCheckMail(oMailLieferadresse))
							{
								this.add(new ownMailAdress4MailBlock(oMailLieferadresse.get_EMAIL_cUF_NN(""),recAdresse,oAdresseLiefer,this.bFoundEmailsAreEditable));
							}
						}
					}
				}
			}
		}

		
		if (this.size()==0 && bAddEmptyMailAdressWhen_no_MailAdressFound)
		{
			this.add(new ownMailAdress4MailBlock("",recAdresse,recAdresse,this.bEmptyEmailsAreEditable));
		}
		
		
		
	}

	
//	/**
//	 * 2014-01-14: recordadresse wird uebergeben und das system stellt selbst fest, welcher typ es ist (haupt, liefer oder mitarbeiteradresse)
//	 * @param cid_adresse
//	 * @param basisMailsEinbeziehen
//	 * @param mitarbeiterEinbeziehen
//	 * @param lieferadressenEinbeziehen
//	 * @param mailBelegtyp
//	 * @param mailBelegtyp_fuer_User
//	 * @param cInfoStringZusatzFuerMailliste
//	 * @param bAddEmptyMailAdressWhen_no_MailAdressFound
//	 * @param FoundEmailsAreEditable
//	 * @param EmptyEmailsAreEditable
//	 * @throws myException
//	 */
//	public MailAdressSearcher(		RECORD_ADRESSE	recAdresseWithMails,
//									boolean 	basisMailsEinbeziehen, 
//									boolean 	mitarbeiterEinbeziehen,
//									boolean 	lieferadressenEinbeziehen, 
//									String 		mailBelegtyp,
//									MyE2_String mailBelegtyp_fuer_User,
//									MyString 	cInfoStringZusatzFuerMailliste, 
//									boolean     bAddEmptyMailAdressWhen_no_MailAdressFound, 
//									Boolean 	FoundEmailsAreEditable, 
//									Boolean 	EmptyEmailsAreEditable) throws myException
//	{
//		super();
//		this.bBasisMailsEinbeziehen = basisMailsEinbeziehen;
//		this.bMitarbeiterEinbeziehen = mitarbeiterEinbeziehen;
//		this.bLieferadressenEinbeziehen = lieferadressenEinbeziehen;
//		this.eMailBelegtyp = 		mailBelegtyp;
//		this.eMailBelegtyp4User = 	mailBelegtyp_fuer_User;
//		
//		this.InfoStringZusatzFuerMailliste = cInfoStringZusatzFuerMailliste;
//		
//		this.bEmptyEmailsAreEditable = EmptyEmailsAreEditable;
//		this.bFoundEmailsAreEditable = FoundEmailsAreEditable;
//		
//		if (S.isFull(mailBelegtyp))
//		{
//			if (!bibALL.get_VectorAusArray(this.cAllowedTypes).contains(mailBelegtyp))
//			{
//				throw new myException(this,"Not Allowed MailType !");
//			}
//		}
//
//		
//		//hauptadresse beschaffen
//		RECORD_ADRESSE  recHauptadresse = recAdresseWithMails;
//		
//		if (recAdresseWithMails.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_MITARBEITER) {
//			recHauptadresse = recAdresseWithMails.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_mitarbeiter().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
//		} else if (recAdresseWithMails.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_LIEFERADRESSE) {
//			recHauptadresse = recAdresseWithMails.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
//		}
//		
//		
//		
//		
//		//adressen suchen und in den mailblock einhaengen
//		if (recAdresseWithMails.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_FIRMENINFO) {
//
//			if (this.bBasisMailsEinbeziehen)     //dann normal verhalten
//			{
//				//zuerst die direkten mail-adressen
//				for (int k=0;k<recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
//				{
//					RECORD_EMAIL oMail = recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
//					if (S.isFull(oMail.get_EMAIL_cUF_NN("")))
//					{
//						if (this.bCheckMail(oMail))
//						{
//							this.add(new ownMailAdress4MailBlock(oMail.get_EMAIL_cUF_NN(""),recAdresseWithMails,recAdresseWithMails,this.bFoundEmailsAreEditable));
//						}
//					}
//				}
//			}
//			
//			
//			if (this.bMitarbeiterEinbeziehen)
//			{
//				//dann die aktiven mitarbeiter-adresse einsammeln
//				for (int i=0; i<recAdresseWithMails.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().size();i++)
//				{
//					RECORD_ADRESSE oAdresseMitarbeiter = recAdresseWithMails.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
//					if (oAdresseMitarbeiter.is_AKTIV_YES())
//					{
//						//dann die Mitarbeiter mail-adressen
//						for (int k=0;k<oAdresseMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
//						{
//							RECORD_EMAIL oMailMitarbeiter = oAdresseMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
//							if (S.isFull(oMailMitarbeiter.get_EMAIL_cUF_NN("")))
//							{
//								if (this.bCheckMail(oMailMitarbeiter))
//								{
//									this.add(new ownMailAdress4MailBlock(oMailMitarbeiter.get_EMAIL_cUF_NN(""),recAdresseWithMails,oAdresseMitarbeiter,this.bFoundEmailsAreEditable));
//								}
//							}
//						}
//					}
//				}
//			}
//	
//			if (this.bLieferadressenEinbeziehen)
//			{
//				//dann die aktiven liefer-adresse einsammeln
//				for (int i=0; i<recAdresseWithMails.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().size();i++)
//				{
//					RECORD_ADRESSE oAdresseLiefer = recAdresseWithMails.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer();
//					if (oAdresseLiefer.is_AKTIV_YES())
//					{
//						//dann die Lieferadressen - mail-adressen
//						for (int k=0;k<oAdresseLiefer.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
//						{
//							RECORD_EMAIL oMailLieferadresse = oAdresseLiefer.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
//							if (S.isFull(oMailLieferadresse.get_EMAIL_cUF_NN("")))
//							{
//								if (this.bCheckMail(oMailLieferadresse))
//								{
//									this.add(new ownMailAdress4MailBlock(oMailLieferadresse.get_EMAIL_cUF_NN(""),recAdresseWithMails,oAdresseLiefer,this.bFoundEmailsAreEditable));
//								}
//							}
//						}
//					}
//				}
//			}
//
//		} else if (recAdresseWithMails.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_MITARBEITER) {
//			if (this.bMitarbeiterEinbeziehen) {
//				for (int k=0;k<recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
//				{
//					RECORD_EMAIL oMail = recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
//					if (S.isFull(oMail.get_EMAIL_cUF_NN("")))
//					{
//						if (this.bCheckMail(oMail))
//						{
//							this.add(new ownMailAdress4MailBlock(oMail.get_EMAIL_cUF_NN(""),recHauptadresse,recAdresseWithMails,this.bFoundEmailsAreEditable));
//						}
//					}
//				}
//			}
//		} else if (recAdresseWithMails.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_LIEFERADRESSE) {
//			if (this.bLieferadressenEinbeziehen) {
//				for (int k=0;k<recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
//				{
//					RECORD_EMAIL oMail = recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
//					if (S.isFull(oMail.get_EMAIL_cUF_NN("")))
//					{
//						if (this.bCheckMail(oMail))
//						{
//							this.add(new ownMailAdress4MailBlock(oMail.get_EMAIL_cUF_NN(""),recHauptadresse,recAdresseWithMails,this.bFoundEmailsAreEditable));
//						}
//					}
//				}
//			}
//		}
//		
//		if (this.size()==0 && bAddEmptyMailAdressWhen_no_MailAdressFound)
//		{
//			this.add(new ownMailAdress4MailBlock("",recHauptadresse,recAdresseWithMails,this.bEmptyEmailsAreEditable));
//		}
//		
//	}

	
	
	
	
	
	private boolean bCheckMail(RECORD_EMAIL oMail) throws myException
	{
		boolean bRueck = true;
		
		
		//2012-02-08: email-findung nach neuer art
		
		//alt via dropdown
//		if (S.isFull(this.eMailBelegtyp))
//		{
//			bRueck = false;
//			
//			if (oMail.get_TYPE_cUF_NN("").equals(myCONST.EMAIL_TYPE_VALUE_ALLTYPES) || 
//				oMail.get_TYPE_cUF_NN("").equals(this.eMailBelegtyp))
//			{
//				bRueck = true;
//			}
//		}

		//2012-02-08: neu mit dem qualifizierer
		if (S.isFull(this.eMailBelegtyp))
		{
			bRueck = false;
			
			Vector<String> vQualis = new RECORD_EMAIL_SPECIAL(oMail).get_vSelectedKeys();
			
			if (vQualis.contains(this.eMailBelegtyp))
			{
				bRueck = true;
			}
		}

		
		//jetzt noch den dubletten-check
		if (bRueck)
		{
			if (this.vMailDublettenCheck.contains(oMail.get_EMAIL_cUF_NN("").trim()))
			{
				bRueck = false;
			}
			else
			{
				this.vMailDublettenCheck.add(oMail.get_EMAIL_cUF_NN("").trim());
			}
		}
			
		
		return bRueck;
	}

	
	
	
	
	private class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyE2_String     cInfostring = null;
		private String          cID_ADRESSE_Firma = null;   
		
		public ownMailAdress4MailBlock(String mailAdress,RECORD_ADRESSE oHauptAdresse, RECORD_ADRESSE oRecAdresseEmail, Boolean bAllowEdit) throws myException
		{
			super(mailAdress, null, bAllowEdit);
			
			this.cID_ADRESSE_Firma = oHauptAdresse.get_ID_ADRESSE_cUF();
			
			String cBasis = oHauptAdresse.get_NAME1_cUF_NN("");
			String cTranslatedZusatzinfo = null;
			if (MailAdressSearcher.this.InfoStringZusatzFuerMailliste != null)
			{
				cTranslatedZusatzinfo = MailAdressSearcher.this.InfoStringZusatzFuerMailliste.CTrans();
			}
			
			if (oRecAdresseEmail.get_ADRESSTYP_lValue(new Long(-999))==myCONST.ADRESSTYP_FIRMENINFO)
			{
				this.cInfostring =new MyE2_String(cTranslatedZusatzinfo==null?"":cTranslatedZusatzinfo,false," ("+cBasis+")  ",false,"Firmen-Email",true);
			}
			if (oRecAdresseEmail.get_ADRESSTYP_lValue(new Long(-999))==myCONST.ADRESSTYP_MITARBEITER)
			{
				this.cInfostring =new MyE2_String(cTranslatedZusatzinfo==null?"":cTranslatedZusatzinfo,false,"("+cBasis+")  ",false,"Mitarbeiter-Email",true," : ",false,oRecAdresseEmail.get_VORNAME_cUF_NN("")+" "+oRecAdresseEmail.get_NAME1_cUF_NN(""),false);
			}
			if (oRecAdresseEmail.get_ADRESSTYP_lValue(new Long(-999))==myCONST.ADRESSTYP_LIEFERADRESSE)
			{
				this.cInfostring =new MyE2_String(cTranslatedZusatzinfo==null?"":cTranslatedZusatzinfo,false,"("+cBasis+")  ",false,"Lieferadresse-Email",true," : ",false,oRecAdresseEmail.get_NAME1_cUF_NN("")+" "+oRecAdresseEmail.get_ORT_cUF_NN(""),false);
			}
			
			//falls empty, dann wurde keine mailadresse zu einem belegtyp oder einer adresse gefunden
			if (S.isEmpty(mailAdress))
			{
				//2014-01-15: neuer aufbau fuer den eintrag bei leeren adressen
				MyE2_String cInfo = null;
				if (MailAdressSearcher.this.eMailBelegtyp4User != null)	{
					cInfo = new MyE2_String("("+cBasis+")  ",false," : ",false,"<keine pass. Mailadr. für",true,MailAdressSearcher.this.eMailBelegtyp4User.CTrans(),false,"> ",false);
				} else {
					cInfo = new MyE2_String("("+cBasis+")  ",false," : ",false,"<keine pass. Mailadr.> ",false);	
				}
				
				
				//vor 2014-01-15:
//				MyE2_String cInfo = new MyE2_String("<keine passende Mailadresse>");
//				if (MailAdressSearcher.this.eMailBelegtyp4User != null)
//				{
//					cInfo = new MyE2_String("<keine passende Mailadresse für",true,MailAdressSearcher.this.eMailBelegtyp4User.CTrans(),false,"> --> ",false);
//				}
//				cInfo.addUnTranslated(oHauptAdresse.get_NAME1_cUF_NN("")+" "+oHauptAdresse.get_ORT_cUF_NN(""));
				
				this.cInfostring = cInfo;
			}
			
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.cInfostring,MyE2_Label.STYLE_SMALL_ITALIC(),true);
		}

		@Override
		public String get_cAdressInfo() throws myException
		{
			return this.cInfostring.CTrans();
		}

		@Override
		public MyString get_cComponentText() throws myException
		{
			return this.cInfostring;
		}

		@Override
		public String get_cID_ADRESSE_EMPFAENGER() throws myException
		{
			return this.cID_ADRESSE_Firma;
		}
		
	}

	
	
	
}

