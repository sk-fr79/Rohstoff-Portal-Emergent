package panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS;

import java.util.StringTokenizer;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicy;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/*
 * spezielle jasper-Hash-Datei fuer reports-listen
 */
public class REP_JasperHASH extends E2_JasperHASH
{

	private RECORD_REPORT   		recReport = null;
	private MailSecurityPolicy   	SecPoliciy = null;	
	
	public REP_JasperHASH(String nameOfReport, JasperFileDef jasperFileDef, RECORD_REPORT RecReport)	throws myException
	{
		super(nameOfReport, jasperFileDef);
		this.recReport = RecReport;
		
		this.SecPoliciy = new ownMailSecPolicy(	this.recReport.is_ALLOW_EMAIL_FREE_YES(),
												this.recReport.is_ALLOW_EMAIL_SEARCH_CUSTOMER_YES(),
												this.recReport.is_ALLOW_EMAIL_EMPLOYES_YES(),
												false);
		
	}

	private class ownMailSecPolicy extends MailSecurityPolicy
	{

		public ownMailSecPolicy(	boolean allowNewEmptyAdress,
									boolean allowAddMailFromAdressSeach,
									boolean allowAddMailFromEployesPopup,
									boolean allowChangeMailAdresse)
		{
			super(allowNewEmptyAdress, allowAddMailFromAdressSeach,	allowAddMailFromEployesPopup, allowChangeMailAdresse);
		}
		
	}
	

	
	@Override
	protected MailBlock create_MailBlock() throws myException
	{
		return new ownMailBlock();
	}

	@Override
	public boolean get_bIsDesignedForMail() throws myException
	{
		return (this.recReport.is_ALLOW_EMAIL_EMPLOYES_YES() ||
				this.recReport.is_ALLOW_EMAIL_FREE_YES() ||
				this.recReport.is_ALLOW_EMAIL_SEARCH_CUSTOMER_YES() ||
				S.isFull(this.recReport.get_STATIC_MAIL_ADRESSES_cUF_NN("")));
	}

	
	
	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock() 	throws myException
		{
			super(REP_JasperHASH.this.SecPoliciy);
			
			String cMailListe = REP_JasperHASH.this.recReport.get_STATIC_MAIL_ADRESSES_cUF_NN("");
			if (S.isFull(cMailListe))
			{
				
				// jetzt die zieladressen in einen adress-vector legen
				StringTokenizer textTrenner = new StringTokenizer(" \n\r" + (cMailListe + " \n\r"), "\n\r");
				String		    cZeile = null;
				
				while (textTrenner.hasMoreElements())
				{
					cZeile = textTrenner.nextToken().trim();
					if (bibALL.isEmpty(cZeile))
						continue;
					
					/*
					 * jetzt die zeile noch trennen nach leerzeichen/kommas/semikolons
					 */
					cZeile = bibALL.ReplaceTeilString(cZeile," ",",");
					cZeile = bibALL.ReplaceTeilString(cZeile,";",",");
					cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
					cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
					cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
					
					Vector<String> vSeps = bibALL.TrenneZeile(cZeile,",");
					
					for (int i=0;i<vSeps.size();i++)
					{
						String cMailAdresse = ((String)vSeps.get(i)).trim();
						
						if (!bibALL.isEmpty(cMailAdresse))
						{
							this.ADD_NewTargetAdress(cMailAdresse);
						}
					}
				}
			}
			else
			{
				this.ADD_NewTargetAdress_interactivEmptyAdress();
			}
		}

		
		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdresse,this,new MyE2_String("Vordefinierte Adresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",this,new MyE2_String("Freie Adresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Belegschaftsadresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Firmenadresse"));
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(new MyE2_String(REP_JasperHASH.this.recReport.get_BUTTONTEXT_cF_NN("--")), MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll() throws myException
		{
			return new MyE2_String(REP_JasperHASH.this.recReport.get_BUTTONTEXT_cF_NN("--"));
		}

		@Override
		public String get_cBelegTyp() throws myException
		{
			return REP_JasperHASH.this.recReport.get_MODULE_KENNER_cUF_NN("<MODUL>")+"_"+REP_JasperHASH.this.recReport.get_BUTTON_AUTH_KENNER_cUF_NN("<AUTH>");
		}

		@Override
		public MyString get_cBelegTyp4User() throws myException
		{
			return new MyE2_String(REP_JasperHASH.this.recReport.get_MODULE_KENNER_cUF_NN("<MODUL>")+"_"+REP_JasperHASH.this.recReport.get_BUTTON_AUTH_KENNER_cUF_NN("<AUTH>"),false);
		}

		@Override
		public MyString get_cKommentar() throws myException
		{
			return new MyE2_String(REP_JasperHASH.this.recReport.get_BESCHREIBUNG_cUF_NN("<kein Kommentar>"),false);
		}

		@Override
		public String get_cModulInfo() throws myException
		{
			return REP_JasperHASH.this.recReport.get_MODULE_KENNER_cUF_NN("<MODUL>");
		}
	}
	
	
	
	private class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyString cInfoWieEntstanden = null;
		
		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo, MyString cAddon) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, new Boolean(S.isEmpty(mailAdress)));    //hier immer nur leere adressen editierbar, gesuchte sind nicht veraenderbar
			this.cInfoWieEntstanden = cAddon;
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.get_cComponentText(), MyE2_Label.STYLE_SMALL_ITALIC());
		}

		@Override
		public String get_cAdressInfo() throws myException
		{
			return "<ADRESSINFO>";
		}

		@Override
		public MyString get_cComponentText() throws myException
		{
			return this.cInfoWieEntstanden;
		}

		@Override
		public String get_cID_ADRESSE_EMPFAENGER() throws myException
		{
			return null;
		}
		
	}



	@Override
	public void doActionAfterCreatedFile() throws myException
	{
		
	}
	
	
}


