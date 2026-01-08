package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Map.Entry;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.BUTTON_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;


public abstract class FP__BUTTON_PRINTFAHRPLAENE extends BUTTON_MAIL_AND_REPORT
{
	public FP__BUTTON_PRINTFAHRPLAENE(MyE2_String fensterTitel,String valid_tag_print, String valid_tag_mail,XX_ActionAgent actionAfterPrintOrMail)
	{
		super("print_fahrplan.png", fensterTitel, valid_tag_print, valid_tag_mail, actionAfterPrintOrMail);
	}


	@Override
	public E2_MassMailer get_MassMailer() throws myException
	{
		return new ownMassMailer();
	}
	
	public abstract void RefreshContainerBeforePrint() throws myException;

	
	protected V_JasperHASH build_JasperHash(String cDateString_YYYY_MM_DD,String cID_LKW) throws myException
	{
		// zuerst die startadressen
		
		RECLIST_VPOS_TPA_FUHRE reclistFuhre = new RECLIST_VPOS_TPA_FUHRE(
						"SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP="+cID_LKW+
						" AND TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_FAHRPLAN_FP,'YYYY-MM-DD')='"+cDateString_YYYY_MM_DD+"'");
		
		
		if (reclistFuhre.get_vKeyValues().size()==0)
		{
			throw new myExceptionForUser("Fehler: Der Fahrplan ist leer !");
		}
		
		for (Entry<String, RECORD_VPOS_TPA_FUHRE> oEntry:reclistFuhre.entrySet())
		{
			if (oEntry.getValue().is_FUHRE_KOMPLETT_NO())
			{
				throw new myExceptionForUser("Mindestens eine Fahrt ist noch nicht vollständig !");
			}
			
			/*
			 * die werte der id_fahrplanpos.bemerkung_fahrplan_start und bemerkung_fahrplan_ende 
			 * in die tabelle schreiben.
			 * es muss zwischen haupt- und lieferadressen unterschieden werden  
			 */
			String cFahrplanInfoStart = oEntry.getValue().get_UP_RECORD_ADRESSE_id_adresse_lager_start().get_BEMERKUNG_FAHRPLAN_cF_NN("");    //info aus dem lager-start
			if (S.isEmpty(cFahrplanInfoStart))
			{
				cFahrplanInfoStart = oEntry.getValue().get_UP_RECORD_ADRESSE_id_adresse_start().get_BEMERKUNG_FAHRPLAN_cF_NN("");        // info aus der haupt-adresse start
			}
			
			String cFahrplanInfoZiel = oEntry.getValue().get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_BEMERKUNG_FAHRPLAN_cF_NN("");    //info aus dem lager-ziel
			if (S.isEmpty(cFahrplanInfoStart))
			{
				cFahrplanInfoZiel = oEntry.getValue().get_UP_RECORD_ADRESSE_id_adresse_ziel().get_BEMERKUNG_FAHRPLAN_cF_NN("");        // info aus der haupt-adresse ziel
			}
			
			if (S.isFull(cFahrplanInfoStart) || S.isFull(cFahrplanInfoZiel))
			{
				if (S.isFull(cFahrplanInfoStart)) oEntry.getValue().set_NEW_VALUE_BEMERKUNG_START_FP(cFahrplanInfoStart);
				if (S.isFull(cFahrplanInfoZiel)) oEntry.getValue().set_NEW_VALUE_BEMERKUNG_ZIEL_FP(cFahrplanInfoZiel);
				oEntry.getValue().UPDATE(null,true);
			}
		}
		
		// liste neu einlesen, da beim drucken evtl. Map-Aenderungen bei einem
		// erneuten speichern gemerkt werden
		//FP__BUTTON_PRINTFAHRPLAENE.this.ModulContainer.get_oNavList()._REBUILD_ACTUAL_SITE("");
		this.RefreshContainerBeforePrint();
			
		V_JasperHASH  VJasperHash = new V_JasperHASH();
		VJasperHash.add(new ownJasperHash(cID_LKW,cDateString_YYYY_MM_DD));
		
		return VJasperHash;

	}
	
	
	
	private class ownJasperHash extends E2_JasperHASH
	{

		public ownJasperHash(String cID_LKW, String cDateString) throws myException
		{
			super("fahrplan", new JasperFileDef_PDF());
			this.put("id_maschinen_lkw",cID_LKW);
			this.put("dat_fahrplan",cDateString);
			this.set_cDownloadAndSendeNameStaticPart("FAHRPLAN_");
			
			String cSQL= "SELECT "+ 
					    " TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_FAHRPLAN_FP,'yyyy_MM_dd')||'__'||TRANSLATE(TRANSLATE(NVL(JT_MASCHINEN.KFZKENNZEICHEN,'-'),'.-@##*+~\\ßäöüÖÄÜ{}()[]/&%$§!<>|','                                '),' ','_')"+
					    " FROM "+ 
					    " "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+
					    " LEFT OUTER JOIN  "+bibE2.cTO()+".JT_MASCHINEN ON   (JT_MASCHINEN.ID_MASCHINEN = JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP) "+
					    " WHERE JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP="+cID_LKW+
							" AND TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_FAHRPLAN_FP,'YYYY-MM-DD')='"+cDateString+"'";
			
			this.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART(cSQL);
			
			
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

		public ownMailBlock() throws myException
		{
			super(new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed());
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
			return new ownMailAdress4MailBlock("",this,new MyE2_String("Manuell angefügte freie Adresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Manuell angefügte Mitarbeiter-Adresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("Manuell angefügte Firmen-Adresse"));
		}

		@Override
		public Component get_ComponentForMailerList()
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll());
		}

		@Override
		public MyString get_ComponentTextForProtokoll()
		{
			return new MyE2_String("Fahrplan");
		}

		@Override
		public String get_cBelegTyp()
		{
			return "FAHRPLAN";
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return new MyE2_String("Fahrplan/Fahrauftrag");
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

	
	private  class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyE2_String cStringForComponent = null;
		
		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyE2_String StringForComponent) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, null);
			this.cStringForComponent = StringForComponent; 
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.cStringForComponent);
		}

		@Override
		public String get_cAdressInfo() throws myException
		{
			return "<keine Adressinfo>";
		}

		@Override
		public MyString get_cComponentText() throws myException
		{
			return this.cStringForComponent;
		}

		@Override
		public String get_cID_ADRESSE_EMPFAENGER() throws myException
		{
			return null;
		}
		
	}

	
	private class ownMassMailer extends E2_MassMailer
	{

		public ownMassMailer() throws myException
		{
			super("MAIL_BETREFF_FAHRPLAN_MAIL", "MAIL_BLOCK_FAHRPLAN_MAIL",	"FAHRPLAN", new MailSecurityPolicyAllowNothing());
		}

		@Override
		public MailBlock build_MailBlock4Added_EmptyMails() throws myException
		{
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_MitarbeiterMails()	throws myException
		{
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_SearchedMails()	throws myException
		{
			return null;
		}
		
	}
	


	
}
