package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing_but_EditAdress;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


/**
 * ein vector aus E2_MailEintragFuerMassMailer-objekten, die aus einem Vector von
 * Adress-IDs gebildet werden und adressen von Firmen und Mitarbeiten (auf wunsch) aufbauen
 */
public class V_MailBlock_FromAdresse extends MailBlock_Vector
{
	private Vector<String> 	vAdressIDs_Unformated = null;
	private boolean 		bBasisMailsEinbeziehen = true;
	private boolean 		bMitarbeiterEinbeziehen = true;
	private boolean       bLieferadressenEinbeziehen = true;
	
	
	Vector<String>  		vMailDublettenCheck = new Vector<String>();
	
	
	private String    		cMODUL_INFO = null;
	
	private boolean   		bAddEmptyMail_if_no_Mail_was_Found  = true;

	
	private Vector<String>  vBelegTypenZuSammeln = null;
	
	private boolean        bWaehleAlleAdressen = false;
	
	/**
	 * 
	 * @param vUnformatedAdressIDs
	 * @param bbasisMailsEinbeziehen
	 * @param bmitarbeiterEinbeziehen
	 * @param blieferadressenEinbeziehen
	 * @param vMailBelegtypen  (muss entweder NULL sein oder mit den gewuenschten mailtypen vorgelegt)
	 * @param eMailBelegtyp4User
	 * @param AddEmptyMail_if_no_Mail_was_Found
	 * @param MODUL_INFO
	 * @throws myException 
	 */
	public V_MailBlock_FromAdresse(	Vector<String> 		vUnformatedAdressIDs,
									boolean 			bbasisMailsEinbeziehen,
									boolean 			bmitarbeiterEinbeziehen,
									boolean 			blieferadressenEinbeziehen, 
									Vector<String> 		vMailBelegtypen,
									boolean  			AddEmptyMail_if_no_Mail_was_Found,
									String     			MODUL_INFO) throws myException
	{
		super();
		this.vAdressIDs_Unformated =				vUnformatedAdressIDs;
		this.bBasisMailsEinbeziehen =				bbasisMailsEinbeziehen;
		this.bMitarbeiterEinbeziehen=				bmitarbeiterEinbeziehen;
		this.bLieferadressenEinbeziehen=			blieferadressenEinbeziehen;
		this.vBelegTypenZuSammeln= 					vMailBelegtypen;
		this.bAddEmptyMail_if_no_Mail_was_Found = 	AddEmptyMail_if_no_Mail_was_Found;
		this.cMODUL_INFO = 							MODUL_INFO;		
		
		if (this.vBelegTypenZuSammeln != null)
		{
			//dann pruefen, ob nur die korrekten typen uebergeben wurden
			Vector<String>  vKorrekteTypen = new Vector<String>();
			vKorrekteTypen.addAll(myCONST.hmTypeAndUserTextEmailTypenReal.keySet());
			
			for (String cKEY: this.vBelegTypenZuSammeln) {
				if (!vKorrekteTypen.contains(cKEY)) {
					throw new myException(this,"Not Allowed MailType: <"+cKEY+">");
				}
			}
		} else {
			this.bWaehleAlleAdressen = true;
		}
		
		this.fillVector();
	}
	
	
	
	private void fillVector() throws myException
	{

		for (int i=0;i<this.vAdressIDs_Unformated.size();i++)
		{
			RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(this.vAdressIDs_Unformated.get(i));
			
			ownMailBlock oMailBlock  = new ownMailBlock(recAdresse);

			if (this.bWaehleAlleAdressen) {
				oMailBlock.add_VMailAdress4MailBlock(		
						new MailAdressSearcher(	this.vAdressIDs_Unformated.get(i),
								this.bBasisMailsEinbeziehen,
								this.bMitarbeiterEinbeziehen,
								this.bLieferadressenEinbeziehen,
								null,
								null,
								null, this.bAddEmptyMail_if_no_Mail_was_Found, null, null));  
			} else {
				for (String cMAILTYPE: this.vBelegTypenZuSammeln) {
					oMailBlock.add_VMailAdress4MailBlock(		
							new MailAdressSearcher(	this.vAdressIDs_Unformated.get(i),
									this.bBasisMailsEinbeziehen,
									this.bMitarbeiterEinbeziehen,
									this.bLieferadressenEinbeziehen,
									cMAILTYPE,
									new MyE2_String(myCONST.hmTypeAndUserTextEmailTypenReal.get(cMAILTYPE)),
									null, this.bAddEmptyMail_if_no_Mail_was_Found, null, null));  
				}
			}
			
			if (oMailBlock.get_v_MailAdress4MailBlock().size()>0)
			{
				this.add(oMailBlock);
			}
		}
	}



	
	private class ownMailBlock extends MailBlock
	{
		
		private MyE2_String  cString4Label = null;
		
		
		public ownMailBlock(RECORD_ADRESSE RecAdresse) throws myException
		{
			super(new MailSecurityPolicyAllowNothing_but_EditAdress());
			this.cString4Label = new MyE2_String(RecAdresse.get_NAME1_cUF_NN("")+" "+RecAdresse.get_ORT_cUF_NN(""),false);
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		public Component get_ComponentForMailerList()
		{
			return new MyE2_Label(this.cString4Label,MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll()
		{
			return this.cString4Label;
		}

		@Override
		public String get_cBelegTyp()
		{
			return "MULTI";
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return new MyE2_String("Multiselect");
		}

		@Override
		public MyString get_cKommentar()
		{
			return new MyE2_String("<kein Kommentar>");
		}

		@Override
		public String get_cModulInfo()
		{
			return V_MailBlock_FromAdresse.this.cMODUL_INFO;
		}
		
	}

	
	
	
	
}
