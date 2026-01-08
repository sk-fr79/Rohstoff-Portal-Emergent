package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

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
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdressSearcher;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowAll;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.Jasper_Exe_WriteEMailToPrintProtokoll;

public class BT_MASK_Mail_Print_WEIGER extends BUTTON_MAIL_AND_REPORT
{

	private BAMF_MASK_ModulContainer 	oContainerMASK = null;
	private E2_NavigationList 			oNavList = null;
	private Save_BAMF 					oSave = null;
	
	private boolean 					bViewOnly = false;

	/** 
	 * 
	 * @param containerMASK
	 * @param navList    (Muss die Navilist aus der FuhrenListe sein, bei anderen aufrufen null)
	 * @param ViewOnly
	 * @throws myException
	 */
	public BT_MASK_Mail_Print_WEIGER(	BAMF_MASK_ModulContainer 	containerMASK, 
										E2_NavigationList 			navList,
										boolean 					ViewOnly) throws myException
	{
		super("print_and_mail_WM.png", new MyE2_String("Druck/Mail Weigerung"), "PRINT_WEIGERUNG", "MAIL_WEIGERUNG",null);
		oContainerMASK = containerMASK;
		oNavList = navList;
		this.bViewOnly = ViewOnly;
		
		this.setToolTipText(new MyE2_String("Drucken und Mailen von Weigerungsmeldungen").CTrans());
		
		// nach den aktionen list un
		this.get_ownACTIONAGENT_MAIL_AND_REPORT().set_ActionAfterPrintOrMail(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (!BT_MASK_Mail_Print_WEIGER.this.bViewOnly)
				{
					// dann neu laden in den zustand edit (wie speichern und schliessen und wieder oeffnen
					oContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,oSave.get_cActualMaskID_Unformated());
					if (oNavList!=null) oNavList.Refresh_ComponentMAP(oSave.get_cActualMaskID_Unformated(),E2_ComponentMAP.STATUS_VIEW);
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
		return new E2_MassMailer_STD("FBAM_MAIL_KENNER_BETREFF","FBAM_MAIL_KENNER_TEXT","FBAM");
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
				this.oSave = new Save_BAMF(this.oContainerMASK,this.oNavList);
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
			super("fbam_fuhren_weigerung", new JasperFileDef_PDF());
			this.cID_FBAM = ID_FBAM;
			this.put("id_fbam",cID_FBAM);

			String cSQL_Pre = "UPDATE "+bibALL.get_TABLEOWNER()+".JT_FBAM SET BUCHUNGSNUMMER = "+
						"TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_FUHREN_BAM_NUMMER.NEXTVAL) "+
						" WHERE ID_FBAM="+this.cID_FBAM+" AND BUCHUNGSNUMMER IS NULL";
			
//			String cSQL_Pre1 = "UPDATE "+bibALL.get_TABLEOWNER()+".JT_FBAM SET " +
//									" WM_LETZTERDRUCK_DATUM = WM_DATUM, "+
//									" WM_LETZTERDRUCK_UHRZEIT = WM_UHRZEIT "+
//							" WHERE ID_FBAM="+this.cID_FBAM;
			
			
			String cSQL_Pre2 = "UPDATE "+bibALL.get_TABLEOWNER()+".JT_FBAM SET WM_DATUM = "+
							bibALL.MakeSql(bibALL.get_cDateNOWInverse("-"))+
							" WHERE ID_FBAM="+this.cID_FBAM+" AND WM_DATUM IS NULL";
			
			String cSQL_Pre3 = "UPDATE "+bibALL.get_TABLEOWNER()+".JT_FBAM SET WM_UHRZEIT= "+
							bibALL.MakeSql(bibALL.get_cTimeNow())+
						"	 WHERE ID_FBAM="+this.cID_FBAM+" AND WM_UHRZEIT IS NULL";
			
			
			this.set_vSQL_STATEMENTS_BEFORE_REPORT(bibALL.get_Vector(cSQL_Pre,cSQL_Pre2,cSQL_Pre3));

			/*
			* den vermerk in die liste schreiben
			*/
			
			//2014-10-24: fuer die mail-protokollierung die ID der Drucktabelle in die HasperHash schreiben
			String cID_Next_SEQ = bibDB.get_NextSequenceValueOfTable(_DB.FBAM_DRUCK);
			
			this.add_HASH_ID_DRUCKTABLE(cID_Next_SEQ);


			
			String cSQL_post1 = "INSERT INTO "+bibALL.get_TABLEOWNER()+".JT_FBAM_DRUCK (ID_FBAM_DRUCK,ID_FBAM,DRUCKDATUM,UHRZEIT,AUSGEFUEHRT_VON,FORMULAR) VALUES("+
																	cID_Next_SEQ+","+
																	this.cID_FBAM+","+
																	bibALL.MakeSql(bibALL.get_cDateNOWInverse("-"))+","+
																	bibALL.MakeSql(bibALL.get_cTimeNow())+","+
																	bibALL.MakeSql(bibALL.get_KUERZEL())+","+
																	bibALL.MakeSql(BAMF__CONST.FBAM_DRUCK_VERMERK_WEIGER)+")";

			String cSQL_post2 = "UPDATE "+bibALL.get_TABLEOWNER()+".JT_FBAM SET WM_GESPERRT = 'Y' WHERE ID_FBAM="+this.cID_FBAM;

			this.set_vSQL_STATEMENTS_AFTER_REPORT(bibALL.get_Vector(cSQL_post1,cSQL_post2));
			
			this.set_cDownloadAndSendeNameStaticPart("FUHREN_WEIGERMELDUNG_");
			this.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART("SELECT NVL(BUCHUNGSNUMMER,'-') FROM "+bibE2.cTO()+".JT_FBAM WHERE ID_FBAM="+this.cID_FBAM);
			
			//einen executer fuer das schreiben der mailadressen zu den printlog-dateien
			this.get_vExecuters().add(new Jasper_Exe_WriteEMailToPrintProtokoll(_DB.FBAM_DRUCK,
																				_DB.FBAM_DRUCK_EM,
																				_DB.FBAM_DRUCK_EM$ID_FBAM_DRUCK,
																				_DB.FBAM_DRUCK_EM$EMAIL_SEND,
																				_DB.FBAM_DRUCK_EM$EMAIL_RECEIVE));

			
			this.get_vExecuters().add(new __JASPER_EXEC_AppendBefundungsFotos(true));
			
			
		}

		
		
		@Override
		protected MailBlock create_MailBlock() throws myException
		{
			ownMailBlock oMailBlock = new ownMailBlock();
			
			//hier die mailadressen von lieferant/abnehmer/spediteur raussuchen
			String cID_ADRESSE_LIEFERANT = null;
			String cID_ADRESSE_ABNEHMER = null;
			String cID_ADRESSE_SPEDITEUR = null;
			
		
			RECORD_FBAM  recFBAM = new RECORD_FBAM(this.cID_FBAM);
			if (recFBAM.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre() != null)
			{
				RECORD_VPOS_TPA_FUHRE  recFuhre = recFBAM.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
				
				cID_ADRESSE_LIEFERANT = recFuhre.get_ID_ADRESSE_START_cUF_NN("");
				cID_ADRESSE_ABNEHMER = recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("");
				cID_ADRESSE_SPEDITEUR = recFuhre.get_ID_ADRESSE_SPEDITION_cUF_NN("");
				
				if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!=null)
				{
					cID_ADRESSE_SPEDITEUR = recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_ID_ADRESSE_cUF_NN("");
				}
			}

			if (S.isFull(cID_ADRESSE_LIEFERANT))
			{
				oMailBlock.add_VMailAdress4MailBlock(new MailAdressSearcher(
								cID_ADRESSE_LIEFERANT,
								true,
								true,
								true,
								myCONST.EMAIL_TYPE_VALUE_TRANSPORT,
								new MyE2_String("Transportauftrag"),
								new MyE2_String("LIEF"), true, null, null));
			}
			if (S.isFull(cID_ADRESSE_ABNEHMER))
			{
				oMailBlock.add_VMailAdress4MailBlock(new MailAdressSearcher(
								cID_ADRESSE_ABNEHMER,
								true,
								true,
								true,
								myCONST.EMAIL_TYPE_VALUE_TRANSPORT,
								new MyE2_String("Transportauftrag"),
								new MyE2_String("ABNEHM"), true, null, null));
			}
			
			if (S.isFull(cID_ADRESSE_SPEDITEUR))
			{
				oMailBlock.add_VMailAdress4MailBlock(new MailAdressSearcher(
								cID_ADRESSE_SPEDITEUR,
								true,
								true,
								true,
								myCONST.EMAIL_TYPE_VALUE_TRANSPORT,
								new MyE2_String("Transportauftrag"),
								new MyE2_String("SPED"), true, null, null));
			}
			
	
			if (oMailBlock.get_v_MailAdress4MailBlock().size()==0)
			{
				oMailBlock.ADD_NewTargetAdress("");
			}
			
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
			//weigermeldung ins archiv schreiben
			Archiver oArchiv = new Archiver("WEIGERUNGEN"+bibALL.get_cYearNow()+ bibALL.get_cMonthNow());
			
			RECORD_FBAM  recFBAM = new RECORD_FBAM(this.cID_FBAM);

			String cID_ADRESSE_EK = null;
			String cBUCHUNGSNUMMER = null;

			if (recFBAM.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre()!=null)
			{
				cID_ADRESSE_EK = recFBAM.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_ID_ADRESSE_START_cUF_NN("");
				cBUCHUNGSNUMMER = recFBAM.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_BUCHUNGSNR_FUHRE_cUF_NN("");
			}
			else
			{
				//weigermeldung nur den EK-Adressen zuschlagen
				if (recFBAM.get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort().get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
				{
					cID_ADRESSE_EK = recFBAM.get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort().get_ID_ADRESSE_cUF_NN("");
					cBUCHUNGSNUMMER = recFBAM.get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort().get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_BUCHUNGSNR_FUHRE_cUF_NN("");
				}
			}
			
			
			
			if (!bibALL.isEmpty(cID_ADRESSE_EK))
			{
				//2014-12-01: auf wunsch von RL abgeschaltet, bzw. auf Zusatzschalter im Mandanten gelegt
				if (bib_Settigs_Mandant.get_WEIGERMELDUNG_IN_KUNDENARCHIV()) {
					this.set_HM_ARCHIV_DATEINAME("WEIGERUNG_"+cBUCHUNGSNUMMER+"__ID_ADRESSE_"+cID_ADRESSE_EK+".pdf");
					String cNewName=oArchiv.copyFilenameToNextFree(this.get_HM_FILENAME_OF_TEMP_FILE(),this.get_HM_ARCHIV_DATEINAME());
					oArchiv.WriteFileInfoToArchiveTable(oArchiv.get_cSUB_DIR_IN_Archiv(),
														 cNewName,
														 this.get_HM_ARCHIV_DATEINAME(),
														 "Weigermeldung",
														 null,
														 null,
														 "ADRESSE",
														 cID_ADRESSE_EK,
														 "pdf",
														 Archiver_CONST.MEDIENKENNER.WEIGERMELDUNG.get_DB_WERT(),
														 null,
														 null,
														 null,null);
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Kopieren ins Archiv: AdressID des Lieferanten nicht gefunden oder Weigermeldung auf ABNEHMERSEITE !!"));
			}
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
			return new MyE2_String("Weigerung");
		}

		@Override
		public String get_cBelegTyp() throws myException
		{
			return "BBAM";
		}

		@Override
		public MyString get_cBelegTyp4User() throws myException
		{
			return new MyE2_String("Weigerung");
		}

		@Override
		public MyString get_cKommentar() throws myException
		{
			return new MyE2_String("Mail aus Weigerung");
		}

		@Override
		public String get_cModulInfo() throws myException
		{
			return "FBAM";
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
