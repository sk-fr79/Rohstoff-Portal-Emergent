package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowExcept_new_and_Change;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class V_JASPERHASH_AVV_BLATT extends V_JasperHASH
{
	
	public V_JASPERHASH_AVV_BLATT(String cID_VPOS_TPA_Fuhre) throws myException
	{
		super();
		
		RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_Fuhre);
		
		if (recFuhre.is_FUHRE_KOMPLETT_NO())
		{
			throw new myException("Only Complete Datasets are able to be printed (1)!!");
		}
		
		if (	recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel()==null 			|| 
				recFuhre.get_UP_RECORD_ADRESSE_id_adresse_start()==null		||
				recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel()==null		||
				recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_start()==null		||
				recFuhre.get_UP_RECORD_ARTIKEL_id_artikel() == null    	||
				recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek() == null  	||
				recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk() == null)
		{
			throw new myException("Only Complete Datasets are able to be printed (2)!!");
		}
		
		//falls die fuhre kein AVV-blatt mitfuehren darf, dann bleibt der vector leer
		if (recFuhre.is_PRINT_EU_AMTSBLATT_NO())
		{
			return;
		}
		
		
		E2_JasperHASH hmPrint = new ownJasperHASH(recFuhre);
		
		this.add(hmPrint);
		
	}
	
	
	
	private class ownJasperHASH extends E2_JasperHASH
	{

		public ownJasperHASH(RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
		{
			super("eu_amtsblatt_huelle_fahrplan", new JasperFileDef_PDF());
			this.put("id_vpos_tpa_fuhre",recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
			
			this.put("c_laendercode_versand",recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_start().get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cF());
			this.put("c_laendercode_ziel",recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cF());
			this.put("c_artikelbez1",recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ARTBEZ1_cUF());
			
			this.put("id_adresse_abfallerzeuger",recFuhre.get_UP_RECORD_ADRESSE_id_adresse_start().get_ID_ADRESSE_cUF());
			this.put("id_adresse_importeur",recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel().get_ID_ADRESSE_cUF());
			this.put("id_adresse_transportunternehmen",bibALL.get_ID_ADRESS_MANDANT());
			
			this.put("id_artikel",recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF());

			this.put("id_eak_code",recFuhre.get_ID_EAK_CODE_cUF_NN("0"));
			
			
			// streckendef. suchen
			String cID_STRECKEN_DEF = "1";    //heist eine strecke
			
			if (recFuhre.get_UP_RECORD_ADRESSE_id_adresse_start().get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()) || 
				recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel().get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
			{
				cID_STRECKEN_DEF = "0";            //streckendef wird nur noch als ja-nein-kriterium in der AVV-Blatt-info benutzt
			}

			this.put("id_strecken_def",cID_STRECKEN_DEF);

		}

		@Override
		protected MailBlock create_MailBlock() throws myException
		{
			return new ownMailBlock();
		}

		@Override
		public boolean get_bIsDesignedForMail()
		{
			return true;
		}
		
		@Override
		public void doActionAfterCreatedFile() throws myException
		{
		}
		
	}
	
	
	
	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock()	throws myException
		{
			super(new MailSecurityPolicyAllowExcept_new_and_Change());
			this.ADD_NewTargetAdress_interactivEmptyAdress();
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",OWN_MailBlock,"<leere Mailadresse>");
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",OWN_MailBlock,"<Mitarbeiter-Mail>");
		};

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}

		@Override
		public Component get_ComponentForMailerList()
		{
			return null;
		}

		@Override
		public MyString get_ComponentTextForProtokoll()
		{
			return null;
		}

		@Override
		public String get_cBelegTyp()
		{
			return null;
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return null;
		}

		@Override
		public MyString get_cKommentar()
		{
			return null;
		}

		@Override
		public String get_cModulInfo()
		{
			return null;
		}
		
	}
	
	
	
	private class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private String cKenner = null; 
		
		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo, String Kenner) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, null);
			this.cKenner = Kenner;
		}
		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.get_cComponentText(),MyE2_Label.STYLE_SMALL_ITALIC());
		}

		@Override
		public String get_cAdressInfo() throws myException 	{return null;}

		@Override
		public MyString get_cComponentText() throws myException
		{
			return new MyE2_String(this.cKenner);
		}

		@Override
		public String get_cID_ADRESSE_EMPFAENGER() throws myException	{return null;}
		
	}
	
}
