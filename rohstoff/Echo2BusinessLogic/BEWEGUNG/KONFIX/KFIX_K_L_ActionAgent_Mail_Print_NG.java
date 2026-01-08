package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.ACTIONAGENT_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdressSearcher;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.Jasper_Exe_WriteEMailToPrintProtokoll;

public class KFIX_K_L_ActionAgent_Mail_Print_NG extends ACTIONAGENT_MAIL_AND_REPORT
{

	//2014-12-16: neuer key fuer JasperHash, uebernimmt ueberall die jeweilige beleg-kopf-id
	public static String     			HASHKEY_ACT_HEAD_ID = 							"HASHKEY_ACT_HEAD_ID@@";
	public static String     			HASHKEY_ACT_WAS_FINISHED_BEFORE_PRINT = 		"HASHKEY_ACT_WAS_FINISHED_BEFORE_PRINT@@";
	public static String     			HASHKEY_ACT_COUNTER_DRUCKZAEHLER_BEFORE_PRINT = "HASHKEY_ACT_COUNTER_DRUCKZAEHLER_BEFORE_PRINT@@";
	public static String     			HASHKEY_ACT_BELEGTYPE = 						"HASHKEY_ACT_BELEGTYPE@@";

	private E2_NavigationList 			oNavList = null;
	private Vector<String>   			vIDs_Statt_Navilist = null;          //sollen die druckids direkt uebergeben werden, dann werden die Navilist-haekchen ignoriert
	private boolean 					bPreview = false;

	private BS__SETTING  				oSetting = null;

	private String 	  					cParameterNameOfHeadIDField = null;

	private String   					cMODULKENNER = null;

	private boolean  					bMake_ID_Validation = true;

	private Vector<String>    			vIDs = new Vector<String>();


	//	//ein HASHKEY fuer die E2_JasperHASH, um  die Archiv-ID zu transportieren, die gebraucht wird, damit das archiv-file
	//	//dem Eintrag im Archivtable zugeordnet werden kann--
	//	public static String  				HASH_ID_ARCHIVTABLE = "HASH_ID_ARCHIVTABLE";



	public KFIX_K_L_ActionAgent_Mail_Print_NG(	MyE2_String 		fensterTitel, 
			XX_ActionAgent 		actionAfterPrintOrMail,
			E2_NavigationList 	navList,
			Vector<String> 		vIDs_statt_Navilist, 
			BS__SETTING 		setting,
			String 				parameterNameOfHeadIDField,
			String 				MODUL_KENNER, 
			boolean 			preview, 
			boolean 			Make_ID_Validation)
	{
		super(fensterTitel, "PRINT_"+setting.get_cBELEGNAME()+(preview?"_PREVIEW":""), "MAIL_"+setting.get_cBELEGNAME(),actionAfterPrintOrMail,preview);

		oNavList = 						navList;
		vIDs_Statt_Navilist = 			vIDs_statt_Navilist;
		oSetting = 						setting;
		cParameterNameOfHeadIDField = 	parameterNameOfHeadIDField;
		bPreview = 						preview;

		this.cMODULKENNER = 			MODUL_KENNER;
		this.bMake_ID_Validation = 		Make_ID_Validation;

		this.set_ActionAfterPrintOrMail(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (KFIX_K_L_ActionAgent_Mail_Print_NG.this.oNavList!=null)
				{
					KFIX_K_L_ActionAgent_Mail_Print_NG.this.oNavList._REBUILD_ACTUAL_SITE(null);
				}
			}
		});


	}

	public  void complete_JasperHASH(E2_JasperHASH oJasperHASH) throws myException{}

	public  void manipulate_IDS_To_Print_From_Other_Source(Vector<String> vIDs) throws myException{}

	@Override
	public E2_MassMailer get_MassMailer() throws myException
	{
		E2_MassMailer oMassMailer = new E2_MassMailer_STD(	KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cTEXTKENNER_POPUP_MAILBETREFF(),
				KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cTEXTKENNER_POPUP_MAILBLOCK(),
				KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cBELEGNAME());

		oMassMailer.get_vActionAgentsAfterWindowClose().add(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (KFIX_K_L_ActionAgent_Mail_Print_NG.this.oNavList != null)
				{
					KFIX_K_L_ActionAgent_Mail_Print_NG.this.oNavList._REBUILD_ACTUAL_SITE(null);
				}
			}
		});


		return oMassMailer;
	}


	public Vector<String> fill_IDs_To_Print() throws myException
	{
		Vector<String> vRueck = new Vector<String>();

		if (this.vIDs_Statt_Navilist!=null)
		{
			vRueck.addAll(this.vIDs_Statt_Navilist);
		}
		else if (KFIX_K_L_ActionAgent_Mail_Print_NG.this.oNavList!=null)
		{
			vRueck.addAll(KFIX_K_L_ActionAgent_Mail_Print_NG.this.oNavList.get_vSelectedIDs_Unformated());
		}

		//jetzt noch die zusatz-manipulation ueber die abstrakte klasse (z.N. druck ueber die maske)
		this.manipulate_IDS_To_Print_From_Other_Source(vRueck);

		return vRueck;
	}


	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
	{

		V_JasperHASH vRueck = new V_JasperHASH();

		this.vIDs = new Vector<String>();

		this.vIDs.addAll(this.fill_IDs_To_Print());

		//		if (this.vIDs_Statt_Navilist!=null)
		//		{
		//			this.vIDs.addAll(this.vIDs_Statt_Navilist);
		//		}
		//		else 
		//		{
		//			this.vIDs.addAll(BS_K_LIST_ActionAgent_Mail_Print.this.oNavList.get_vSelectedIDs_Unformated());
		//		}
		//
		//		//jetzt noch die zusatz-manipulation ueber die abstrakte klasse (z.N. druck ueber die maske)
		//		this.manipulate_IDS_To_Print_From_Other_Source(this.vIDs);


		if (this.bMake_ID_Validation)
		{
			bibMSG.add_MESSAGE(execInfo.make_ID_Validation(this.vIDs));
		}

		if (bibMSG.get_bHasAlarms())
		{
			return vRueck;
		}


		if (this.vIDs.size()==0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens einen Vorgang auswählen !"));
			return vRueck;
		}

		for (int i=0;i<this.vIDs.size();i++)
		{
			vRueck.add(new ownJasperHASH(this.vIDs.get(i)));
		}

		return vRueck;
	}



	private class ownJasperHASH extends E2_JasperHASH
	{

		private String cID_VKOPF = null;
		private String cID_ADRESSE = null;

		public ownJasperHASH(String ID_KOPF)	throws myException
		{
			super();
			String jasper_filename = "";

			boolean is_fixierung_kontrakt = new Rec20(_TAB.vkopf_kon)._fill_id(ID_KOPF).is_yes_db_val(VKOPF_KON.ist_fixierung);

			if(is_fixierung_kontrakt){
				jasper_filename="kontrakt_fix";
			}else{
				jasper_filename="kontrakt";
			}

			this._init(jasper_filename, new JasperFileDef_PDF());

			BS__SETTING o_Setting = KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting;

			this.cID_VKOPF = ID_KOPF;
			this.put(KFIX_K_L_ActionAgent_Mail_Print_NG.this.cParameterNameOfHeadIDField,cID_VKOPF);

			String cKTable = o_Setting.get_oVorgangTableNames().get_cVKOPF_TAB();
			String cKID = o_Setting.get_oVorgangTableNames().get_cVKOPF_PK();
			String cPTable = o_Setting.get_oVorgangTableNames().get_cVPOS_TAB();

			// die id_Adresse wird nur fuer den folgenden query nach den korrekten mail-adressen gebraucht
			MyRECORD recVorgang = new MyRECORD(cKTable,ID_KOPF);

			this.cID_ADRESSE = recVorgang.get_UnFormatedValue("ID_ADRESSE");

			this.put("ID_ADRESSE",recVorgang.get_UnFormatedValue("ID_ADRESSE"));

			boolean bWarAbgeschlossen = S.NN(recVorgang.get_UnFormatedValue("ABGESCHLOSSEN")).equals("Y");

			String cDruckZaehler = S.NN(recVorgang.get_UnFormatedValue("DRUCKZAEHLER"),"0").trim();


			//2014-12-16: die jeweilige kopf-id in die JasperHashes eintragen, ebenso den abschluss-status vor dem druck
			this.put(KFIX_K_L_ActionAgent_Mail_Print_NG.HASHKEY_ACT_HEAD_ID, ID_KOPF);
			this.put(KFIX_K_L_ActionAgent_Mail_Print_NG.HASHKEY_ACT_WAS_FINISHED_BEFORE_PRINT, bWarAbgeschlossen?"Y":"N");
			this.put(KFIX_K_L_ActionAgent_Mail_Print_NG.HASHKEY_ACT_COUNTER_DRUCKZAEHLER_BEFORE_PRINT, cDruckZaehler);
			this.put(KFIX_K_L_ActionAgent_Mail_Print_NG.HASHKEY_ACT_BELEGTYPE, oSetting.get_cBELEGTYP());
			//2014-12-16: ende


			Vector<String> vSQL_Pre = new Vector<String>();
			Vector<String> vSQL_Post = new Vector<String>();
			Vector<String> vSQL_Post_after_error = new Vector<String>();

			String cBuchungsvorsatz = o_Setting.get_cBuchungsNrVorsatz();
			if (S.isFull(cBuchungsvorsatz))
			{
				cBuchungsvorsatz = bibALL.MakeSql(cBuchungsvorsatz);
			}


			vSQL_Pre.add("UPDATE "+bibE2.cTO()+"."+cKTable+" SET DRUCKDATUM=SYSDATE WHERE DRUCKDATUM IS NULL AND "+cKID+"="+ID_KOPF);
			vSQL_Pre.add("UPDATE "+bibE2.cTO()+"."+cKTable+" SET " +o_Setting.get_SQL_UPDATE_Block_Fuer_Buchungsnummer()+
					" WHERE BUCHUNGSNUMMER IS NULL AND "+cKID+"="+ID_KOPF);


			//erst nach erfolgreichem verschicken wird abgeschlossen


			/*
			 * aenderung am 2010-09-23: Bei rechnungen/gutschriften wird der schalter abgeschlossen vor dem druck gesetzt,
			 * damit das kontoblatt korrekt rauskommt
			 */

			if(! is_fixierung_kontrakt){
				String cSetAbgeschlossen = "UPDATE "+bibE2.cTO()+"."+o_Setting.get_oVorgangTableNames().get_cVKOPF_TAB()+
						" SET ABGESCHLOSSEN='Y' " +
						" WHERE "+
						cKID+"="+ID_KOPF;

				if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_RECHNUNG) || oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_GUTSCHRIFT))
				{
					vSQL_Pre.add(cSetAbgeschlossen);
				}
				else
				{
					vSQL_Post.add(cSetAbgeschlossen);
				}
			}


			//			vSQL_Post.add("UPDATE "+bibE2.cTO()+"."+o_Setting.get_oVorgangTableNames().get_cVKOPF_TAB()+
			//											" SET ABGESCHLOSSEN='Y' " +
			//											" WHERE "+
			//											cKID+"="+ID_KOPF);

			//2012-02-29: protokoll listet auch geloeschte positionen in der positionszahl - korrigiert
			String cAnzahl = bibDB.EinzelAbfrage("select count(*) from "+bibE2.cTO()+"."+cPTable+" WHERE "+cKID+"="+ID_KOPF+" AND NVL(DELETED,'N')='N'");
			String cGesamtpreis = bibDB.EinzelAbfrage("SELECT SUM(  NVL(GESAMTPREIS_FW,0)) FROM  "+bibE2.cTO()+"."+cPTable+
					" WHERE NVL(DELETED,'N')='N' AND " +
					" POSITION_TYP="+bibALL.MakeSql(myCONST.VG_POSITION_TYP_ARTIKEL)+" AND " +
					cKID+"="+ID_KOPF);


			//2010-09-23: fuer die archivierungsfunktion werden archivmedien - eintraege geschrieben (falls gewuenscht)
			Vector<String> vSeqs = bibALL.get_SEQUENCE_VECT_VALUES("SEQ_"+cKTable.substring(3)+"_DRUCK", 1);

			//2010-11-17: die archiveintraege werden vor dem druck geschrieben, damit eine archivierung moeglich ist,
			//            und der benutzer wird ins archiv geschrieben
			vSQL_Pre.add("INSERT INTO "+bibE2.cTO()+"."+cKTable+"_DRUCK ("+cKID+"_DRUCK,"+cKID+",POSITIONEN,GESAMT_NETTO,DRUCKDATUM,KUERZEL)" +
					"  VALUES (" +vSeqs.get(0)+ ","+ID_KOPF+"," +cAnzahl+","+cGesamtpreis+
					",SYSDATE,"+bibALL.get_RECORD_USER().get_KUERZEL_VALUE_FOR_SQLSTATEMENT()+")");


			//2010-09-23: fuer die archivierungsfunktion werden archivmedien - eintraege geschrieben (falls gewuenscht)
//						this.set_ARCHIV_ID_TABLE(vSeqs.get(0));
//						this.set_ARCHIV_TABLENAME((cKTable+"_DRUCK").substring(3));  //ohne das fuehrende JT_   

			this.add_HASH_ID_DRUCKTABLE(vSeqs.get(0));


			this.set_bVorschauDruck(KFIX_K_L_ActionAgent_Mail_Print_NG.this.bPreview);


			//aenderung 2010-12-02: Druckzaehler hochsetzen
			vSQL_Post.add("UPDATE "+bibE2.cTO()+"."+cKTable+" SET DRUCKZAEHLER=(NVL(DRUCKZAEHLER,0)+1) WHERE "+cKID+"="+ID_KOPF);


			//			if (!bWarAbgeschlossen)
			//			{
			//				//2011-11-17: nach einer "vorgetaeuschten" email-sendung konnte ein beleg gedruckt werden, ohne die rechnung als
			//				//            abgeschlossen zu deklarieren (download aus der mail-liste). Dies wird jetzt verhindert
			////				vSQL_Post_after_error.add("UPDATE "+bibE2.cTO()+"."+o_Setting.get_oVorgangTableNames().get_cVKOPF_TAB()+
			////													" SET ABGESCHLOSSEN='N' " +
			////													" WHERE "+
			////													cKID+"="+ID_KOPF);
			//			}

			if (!KFIX_K_L_ActionAgent_Mail_Print_NG.this.bPreview)
			{
				this.set_vSQL_STATEMENTS_BEFORE_REPORT(vSQL_Pre);
				this.set_vSQL_STATEMENTS_AFTER_REPORT(vSQL_Post);
				this.set_vSQL_STATEMENTS_AFTER_REPORT_ERROR(vSQL_Post_after_error);
			}

			this.set_cDownloadAndSendeNameStaticPart(oSetting.get_cBELEGNAME()+"_");
			this.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART("SELECT NVL(BUCHUNGSNUMMER,'-') FROM "+bibE2.cTO()+"."+cKTable+" WHERE "+cKID+"="+this.cID_VKOPF);

			//falls noch was fehlt, kann es hier reigeschrieben weden
			KFIX_K_L_ActionAgent_Mail_Print_NG.this.complete_JasperHASH(this);


			//2014-10-23: einen executer fuer das schreiben der mailadressen zu den printlog-dateien
			this.get_vExecuters().add(new Jasper_Exe_WriteEMailToPrintProtokoll(cKTable+"_DRUCK",
					cKTable+"_DRUCK_EM",
					cKID+"_DRUCK",
					"EMAIL_SEND",
					"EMAIL_RECEIVE"));

		}

		@Override
		protected MailBlock create_MailBlock() throws myException
		{
			ownMailBlock oMailBlock = new ownMailBlock();

			oMailBlock.add_VMailAdress4MailBlock(
					new MailAdressSearcher(	this.cID_ADRESSE,
							true,
							true,
							true,
							KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cVORGANGSART(),
							new MyE2_String(KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cVORGANGSART4User()),
							null, 
							true, 
							new Boolean(false), 
							new Boolean(true))
					);

			return oMailBlock;
		}

		@Override
		public boolean get_bIsDesignedForMail() throws myException
		{
			return !KFIX_K_L_ActionAgent_Mail_Print_NG.this.bPreview;           //wenn preview, dann keine mail
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
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdresse,OWN_MailBlock,new MyE2_String("Vorhandene eMail"),new Boolean(false));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock("",OWN_MailBlock,new MyE2_String("Freie eMail"),new Boolean(true));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Mitarb.-eMail"),new Boolean(false));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Firmen-eMail"),new Boolean(false));
		}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll() throws myException
		{
			return new MyE2_String(KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cBELEGNAME());
		}

		@Override
		public String get_cBelegTyp() throws myException
		{
			return KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cBELEGNAME();
		}

		@Override
		public MyString get_cBelegTyp4User() throws myException
		{
			return new MyE2_String(KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cBELEGNAME());
		}

		@Override
		public MyString get_cKommentar() throws myException
		{
			return new MyE2_String("Mail aus "+KFIX_K_L_ActionAgent_Mail_Print_NG.this.oSetting.get_cBELEGNAME());
		}

		@Override
		public String get_cModulInfo() throws myException
		{
			return KFIX_K_L_ActionAgent_Mail_Print_NG.this.cMODULKENNER;
		}
	}



	private  class ownMailAdress4MailBlock extends MailAdress4MailBlock
	{
		private MyE2_String cStringForComponent = null;

		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyE2_String StringForComponent, Boolean bAllowEdit) throws myException
		{
			super(mailAdress, MailBlockThisBelongsTo, bAllowEdit);
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
