package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.BUTTON_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowAll;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.Jasper_Exe_WriteEMailToPrintProtokoll;

public class BT_MASK_Mail_Print_BAMB extends BUTTON_MAIL_AND_REPORT
{

	private BAMB_MASK_ModulContainer 	oContainerMASK = null;
	private E2_NavigationList 			oNavList = null;
	private Save_BAMB 					oSave = null;
	
	private boolean 					bViewOnly = false;

	public BT_MASK_Mail_Print_BAMB(	BAMB_MASK_ModulContainer 	containerMASK, 
									E2_NavigationList 			navList,
									boolean 					ViewOnly) throws myException
	{
		super("print_and_mail.png", new MyE2_String("Druck/Mail Beanstandungen"), "PRINT_BAM", "MAIL_BAM",null);
		oContainerMASK = containerMASK;
		oNavList = navList;
		this.bViewOnly = ViewOnly;
		
		this.setToolTipText(new MyE2_String("Drucken und Mailen von Beanstandungsmeldungen").CTrans());
		
		// nach den aktionen list un
		this.get_ownACTIONAGENT_MAIL_AND_REPORT().set_ActionAfterPrintOrMail(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (!BT_MASK_Mail_Print_BAMB.this.bViewOnly)
				{
					// dann neu laden in den zustand edit (wie speichern und schliessen und wieder oeffnen
					oContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,oSave.get_cActualMaskID_Unformated());
					oNavList.Refresh_ComponentMAP(oSave.get_cActualMaskID_Unformated(),E2_ComponentMAP.STATUS_VIEW);
				}
				else
				{
					oContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,
													oContainerMASK.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				}
			}
			
		});
		
		
	}

	@Override
	public E2_MassMailer get_MassMailer() throws myException
	{
		return new E2_MassMailer_STD("BBAM_MAIL_KENNER_BETREFF","BBAM_MAIL_KENNER_TEXT","BBAM");
	}

	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
	{
		
		V_JasperHASH vRueck = new V_JasperHASH();
		
		try
		{
			// zuerst speichern, dann, wenn speichern klappt, drucken
			if (!this.bViewOnly)
			{
				this.oSave = new Save_BAMB(this.oContainerMASK,this.oNavList);
				if (oSave.get_bISOK())
				{
					vRueck.add(new ownJasperHASH(oSave.get_cActualMaskID_Unformated()));
				}
			}
			else
			{
				//im anzeigemodus wird nicht gespeichert
				vRueck.add(new ownJasperHASH(oContainerMASK.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()));
			}
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}

		return vRueck;
	}


	
	private class ownJasperHASH extends E2_JasperHASH
	{

		private String cID_FBAM = null;
		
		public ownJasperHASH(String ID_FBAM)	throws myException
		{
			super("bbam_meldung", new JasperFileDef_PDF());
			this.cID_FBAM = ID_FBAM;
			this.put("id_fbam",cID_FBAM);

			String cSQL_Pre = "UPDATE "+bibE2.cTO()+".JT_FBAM SET BUCHUNGSNUMMER = "+
									"TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_FUHREN_BAM_NUMMER.NEXTVAL) "+
									" WHERE ID_FBAM="+cID_FBAM+" AND BUCHUNGSNUMMER IS NULL";
			this.set_vSQL_STATEMENTS_BEFORE_REPORT(bibALL.get_Vector(cSQL_Pre));

			
			//2014-10-24: fuer die mail-protokollierung die ID der Drucktabelle in die HasperHash schreiben
			String cID_Next_SEQ = bibDB.get_NextSequenceValueOfTable(_DB.FBAM_DRUCK);
			
			this.add_HASH_ID_DRUCKTABLE(cID_Next_SEQ);
			
			
			/*
			* den vermerk in die liste schreiben
			*/
			String cSQL_post = "INSERT INTO "+bibALL.get_TABLEOWNER()+".JT_FBAM_DRUCK (ID_FBAM_DRUCK,ID_FBAM,DRUCKDATUM,UHRZEIT,AUSGEFUEHRT_VON,FORMULAR) VALUES("+
																						cID_Next_SEQ+","+
																						cID_FBAM+","+
																						bibALL.MakeSql(bibALL.get_cDateNOWInverse("-"))+","+
																						bibALL.MakeSql(bibALL.get_cTimeNow())+","+
																						bibALL.MakeSql(bibALL.get_KUERZEL())+","+
																						bibALL.MakeSql(BAMB_MASK_ModulContainer.FBAM_DRUCK_VERMERK_BAM)+")";
			
			this.set_vSQL_STATEMENTS_BEFORE_REPORT(bibALL.get_Vector(cSQL_post));

			this.set_cDownloadAndSendeNameStaticPart("BEANSTANDUNGSMELDUNG_");
			this.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART("SELECT NVL(BUCHUNGSNUMMER,'-') FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_FBAM="+this.cID_FBAM);
			
			
			//einen executer fuer das schreiben der mailadressen zu den printlog-dateien
			this.get_vExecuters().add(new Jasper_Exe_WriteEMailToPrintProtokoll(_DB.FBAM_DRUCK,
																				_DB.FBAM_DRUCK_EM,
																				_DB.FBAM_DRUCK_EM$ID_FBAM_DRUCK,
																				_DB.FBAM_DRUCK_EM$EMAIL_SEND,
																				_DB.FBAM_DRUCK_EM$EMAIL_RECEIVE));

			
		}

		@Override
		protected MailBlock create_MailBlock() throws myException
		{
			ownMailBlock oMailBlock = new ownMailBlock();
			oMailBlock.ADD_NewTargetAdress("");
			
			return oMailBlock;
		}

		@Override
		public boolean get_bIsDesignedForMail() throws myException
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
			super(new MailSecurityPolicyAllowAll());
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdresse,OWN_MailBlock,new MyE2_String("Frei eingegebe MailAdresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",OWN_MailBlock,new MyE2_String("Frei eingegebe MailAdresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Mitarbeiter-MailAdresse"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Firmen-MailAdresse"));
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll() throws myException
		{
			return new MyE2_String("Betriebs-BAM");
		}

		@Override
		public String get_cBelegTyp() throws myException
		{
			return "BBAM";
		}

		@Override
		public MyString get_cBelegTyp4User() throws myException
		{
			return new MyE2_String("Betriebs-BAM");
		}

		@Override
		public MyString get_cKommentar() throws myException
		{
			return new MyE2_String("Mail aus Betriebs-BAM");
		}

		@Override
		public String get_cModulInfo() throws myException
		{
			return "BBAM";
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
			return new MyE2_Label(this.cStringForComponent,MyE2_Label.STYLE_SMALL_ITALIC());
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
	
	
}
