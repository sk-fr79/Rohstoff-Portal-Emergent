package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FP__CREATE_JASPER_HASHES_4_FAHRPLAN
{
	
	private V_JasperHASH VJasperHash = new V_JasperHASH();
	
	/**
	 * 
	 * @param datumFahrplan  (in der Form 2001-12-31)
	 * @param vid_lkws		 (unformatiert)
	 * @throws myException
	 */
	public FP__CREATE_JASPER_HASHES_4_FAHRPLAN(String datumFahrplan, Vector<String> vid_lkws) throws myException
	{
		super();
		String cDateString = datumFahrplan;
		
		
		if (vid_lkws==null || vid_lkws.size()==0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Sie haben keine LKWs zum Fahrplandruck ausgewaehlt !!"));
			return;
		}
		
		
		//datum pruefen
		if (datumFahrplan.length()==10)
		{
			if (!(datumFahrplan.substring(4,5).equals("-") && datumFahrplan.substring(7,8).equals("-")))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Datum ist nicht korrekt !!"));
				return;
			}
		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Datum ist nicht korrekt !!"));
			return;
		}
		
		
		for (int k=0;k<vid_lkws.size();k++)
		{
			Vector<String> vSQLsForJasperHASH = new Vector<String>();
			
			String cID_LKW = (String)vid_lkws.get(k);

			
			RECLIST_VPOS_TPA_FUHRE recLISTFahrplan = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+
																				".JT_VPOS_TPA_FUHRE WHERE ID_MASCHINEN_LKW_FP="+cID_LKW+
																				" AND TO_CHAR(DAT_FAHRPLAN_FP,'yyyy-MM-dd')="+bibALL.MakeSql(datumFahrplan));
			
			/*
			 * die werte der id_fahrplanpos.bemerkung_fahrplan_start und bemerkung_fahrplan_ende 
			 * in die tabelle schreiben.
			 * es muss zwischen haupt- und lieferadressen unterschieden werden  
			 */
			// zuerst die startadressen
//			String cQuery1 = "SELECT ID_FAHRPLANPOS,ID_ADRESSE_START,ID_ADRESSE_ZIEL FROM "+bibE2.cTO()+".JT_FAHRPLANPOS "+
//								" WHERE JT_FAHRPLANPOS.ID_MASCHINEN_LKW="+cID_LKW+" AND CHAR(JT_FAHRPLANPOS.DAT_FAHRPLAN,ISO)='"+cDateString+"'";
//		
//			String[][] cAdressen =  oDB.EinzelAbfrageInArray(cQuery1,"");
			if (recLISTFahrplan.get_vKeyValues().size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error Quering List (1)",false));
			}
			else
			{
				
				for (int i=0;i<recLISTFahrplan.get_vKeyValues().size();i++)
				{
					if (recLISTFahrplan.get(i).is_FUHRE_KOMPLETT_NO())
					{
						throw new myException(this,"Error: Print impossible in unfullfilled Positions !");
					}
					
					if (recLISTFahrplan.get(i).is_DELETED_YES() || recLISTFahrplan.get(i).is_IST_STORNIERT_YES())
					{
						continue;
					}
					
					RECORD_ADRESSE recAdresseStart = recLISTFahrplan.get(i).get_UP_RECORD_ADRESSE_id_adresse_lager_start();
					RECORD_ADRESSE recAdresseZiel = recLISTFahrplan.get(i).get_UP_RECORD_ADRESSE_id_adresse_lager_start();
					
					vSQLsForJasperHASH.add("UPDATE JT_VPOS_TPA_FUHRE SET BEMERKUNG_START_FP="+bibALL.MakeSql(recAdresseStart.get_BEMERKUNG_FAHRPLAN_cUF())+" WHERE ID_VPOS_TPA_FUHRE="+recLISTFahrplan.get(i).get_ID_VPOS_TPA_FUHRE_cUF());
					vSQLsForJasperHASH.add("UPDATE JT_VPOS_TPA_FUHRE SET BEMERKUNG_ZIEL_FP="+bibALL.MakeSql(recAdresseZiel.get_BEMERKUNG_FAHRPLAN_cUF())+" WHERE ID_VPOS_TPA_FUHRE="+recLISTFahrplan.get(i).get_ID_VPOS_TPA_FUHRE_cUF());
					
				}
				
				
			}
			// ende aenderungen schreiben
			if (bibMSG.get_bIsOK())
			{
				this.VJasperHash.add(new ownJasperHash(cID_LKW,cDateString,vSQLsForJasperHASH));
			}
		}
		
	}
	
	
	private class ownJasperHash extends E2_JasperHASH
	{
		public ownJasperHash(String cID_LKW, String cDateString, Vector<String> vSQLStats)	throws myException
		{
			super("fahrplan",new JasperFileDef_PDF());
			this.set_cDownloadAndSendeNameStaticPart("fahrauftrag");
			this.put("id_maschinen_lkw",cID_LKW);
			this.put("dat_fahrplan",cDateString);
			this.set_vSQL_STATEMENTS_BEFORE_REPORT(vSQLStats);

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
			return new ownMailAdress4MailBlock("",this,new MyE2_String("Manuell angefügte Mitarbeiter-Adresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return null;
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


	public V_JasperHASH getVJasperHash()
	{
		return this.VJasperHash;
	}
	
	
}
