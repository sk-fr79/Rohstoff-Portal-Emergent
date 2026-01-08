package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import java.util.Vector;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myCONST_ENUM.MAILDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SPECIAL;

public class SE_MailAdressSearcher
{
	private boolean 		bBasisMailsEinbeziehen=false;
	private boolean 		bMitarbeiterEinbeziehen=false;
	private boolean 		bLieferadressenEinbeziehen=false; 
	
	private VectorSingle	vMailfound = new VectorSingle();
	
	private MAILDEF  		mail_def = null;
	
	
	/**
	 * @param recAdresseWithMails
	 * @param basisMailsEinbeziehen
	 * @param mitarbeiterEinbeziehen
	 * @param lieferadressenEinbeziehen
	 * @throws myException
	 */
	public SE_MailAdressSearcher(	RECORD_ADRESSE				recAdresseWithMails,
									MAILDEF  					p_mail_def,
									boolean 					basisMailsEinbeziehen, 
									boolean 					mitarbeiterEinbeziehen,
									boolean 					lieferadressenEinbeziehen) throws myException	{
		super();
		
		this.mail_def = p_mail_def;
		this.bBasisMailsEinbeziehen = basisMailsEinbeziehen;
		this.bMitarbeiterEinbeziehen = mitarbeiterEinbeziehen;
		this.bLieferadressenEinbeziehen = lieferadressenEinbeziehen;
		
		//adressen suchen und in den mailblock einhaengen
		if (recAdresseWithMails.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_FIRMENINFO) {

			if (this.bBasisMailsEinbeziehen)  	{
				//zuerst die direkten mail-adressen
				for (int k=0;k<recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)		{
					RECORD_EMAIL oMail = recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
					if (S.isFull(oMail.get_EMAIL_cUF_NN(""))) 	{
						if (this.bCheckMail(oMail)) {
							this.vMailfound.add(oMail.get_EMAIL_cUF_NN(""));
						}
					}
				}
			}
			
			
			if (this.bMitarbeiterEinbeziehen)	{
				//dann die aktiven mitarbeiter-adresse einsammeln
				for (int i=0; i<recAdresseWithMails.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().size();i++)		{

					RECORD_ADRESSE oAdresseMitarbeiter = recAdresseWithMails.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
					if (oAdresseMitarbeiter.is_AKTIV_YES())		{
						//dann die Mitarbeiter mail-adressen
						for (int k=0;k<oAdresseMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)	{
							RECORD_EMAIL oMail = oAdresseMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
							if (S.isFull(oMail.get_EMAIL_cUF_NN(""))) {
								if (this.bCheckMail(oMail))	{
									this.vMailfound.add(oMail.get_EMAIL_cUF_NN(""));
								}
							}
						}
					}
				}
			}
	
			if (this.bLieferadressenEinbeziehen) 	{
				//dann die aktiven liefer-adresse einsammeln
				for (int i=0; i<recAdresseWithMails.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().size();i++)	{
					RECORD_ADRESSE oAdresseLiefer = recAdresseWithMails.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer();
					if (oAdresseLiefer.is_AKTIV_YES()) 	{
						//dann die Lieferadressen - mail-adressen
						for (int k=0;k<oAdresseLiefer.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)	{
							RECORD_EMAIL oMail = oAdresseLiefer.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
							if (S.isFull(oMail.get_EMAIL_cUF_NN("")))	{
								if (this.bCheckMail(oMail)) 	{
									this.vMailfound.add(oMail.get_EMAIL_cUF_NN(""));
								}
							}
						}
					}
				}
			}

		} else if (recAdresseWithMails.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_MITARBEITER) {
			if (this.bMitarbeiterEinbeziehen) {
				for (int k=0;k<recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++) {
					RECORD_EMAIL oMail = recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
					if (S.isFull(oMail.get_EMAIL_cUF_NN(""))) 	{
						if (this.bCheckMail(oMail))	{
							this.vMailfound.add(oMail.get_EMAIL_cUF_NN(""));
						}
					}
				}
			}
		} else if (recAdresseWithMails.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_LIEFERADRESSE) {
			if (this.bLieferadressenEinbeziehen) {
				for (int k=0;k<recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++) {
					RECORD_EMAIL oMail = recAdresseWithMails.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
					if (S.isFull(oMail.get_EMAIL_cUF_NN("")))	{
						if (this.bCheckMail(oMail))		{
							this.vMailfound.add(oMail.get_EMAIL_cUF_NN(""));
						}
					}
				}
			}
		}
	}

	
	
	private boolean bCheckMail(RECORD_EMAIL oMail) throws myException {
		Vector<String> vQualis = new RECORD_EMAIL_SPECIAL(oMail).get_vSelectedKeys();
		return vQualis.contains(this.mail_def.db_val());
	}



	public VectorSingle get_v_MailAdressesFound() {
		return vMailfound;
	}

	
}

