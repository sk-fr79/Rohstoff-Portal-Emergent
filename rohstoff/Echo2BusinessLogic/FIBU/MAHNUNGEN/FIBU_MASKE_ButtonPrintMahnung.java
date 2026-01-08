package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.ACTIONAGENT_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_ROOT;
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
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibFILE;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.pdfConcat;

public class FIBU_MASKE_ButtonPrintMahnung extends MyE2_Button 
{
	private FIBU_Container4Mahnung oMaskContainer = null;
	
	private V_JasperHASH vJasperHash = new V_JasperHASH();  
	
	public FIBU_MASKE_ButtonPrintMahnung(FIBU_Container4Mahnung MaskContainer) throws myException 
	{
		super(new MyE2_String("Speichern + Drucken"));
		this.oMaskContainer = MaskContainer;
		
		this.setToolTipText(new MyE2_String("Mahnung drucken, speichern und Maske schliessen").CTrans());
		
		this.add_oActionAgent(new ownActionAgent(new MyE2_String("Mahnung drucken")));

	}
	
	
	private  class ownActionAgent extends ACTIONAGENT_MAIL_AND_REPORT
	{

		private Vector<String>    			vIDs = new Vector<String>();

		
		public ownActionAgent(	MyE2_String 		fensterTitel) throws myException
		{
			super(fensterTitel, "PRINT_KONTO_MAHNUNG", "PRINT_KONTO_MAHNUNG",null,false);
			
			XX_ActionAgent oAgentCloseWindow = new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					FIBU_MASKE_ButtonPrintMahnung.this.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			};
			
			this.get_vActionAgentsExecuteAfterMail().add(oAgentCloseWindow);
			this.get_vActionAgentsExecuteAfterPrint().add(oAgentCloseWindow);
			
		}

		public boolean bACTION_BEFORE_START___TO_OVERWRITE() throws myException
		{
			FIBU_MASKE_ButtonPrintMahnung oThis = FIBU_MASKE_ButtonPrintMahnung.this;
			
			MyE2_MessageVector  oMV_Check = oThis.oMaskContainer.save_mask();
			
			if (oMV_Check.get_bIsOK())
			{
				return true;
			}
			else
			{
				bibMSG.add_MESSAGE(oMV_Check);
				return false;
			}
		}

		
		@Override
		public E2_MassMailer get_MassMailer() throws myException
		{
			E2_MassMailer oMassMailer = new E2_MassMailer_STD(	"FIBU_MAHNUNG_MAIL_BETREFF",
																"FIBU_MAHNUNG_MAIL_TEXT",
																"FIBU_MAHNUNG");
			
			return oMassMailer;
		}


		@Override
		public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
		{
			V_JasperHASH vRueck = new V_JasperHASH();
			
			this.vIDs = new Vector<String>();

			if (FIBU_MASKE_ButtonPrintMahnung.this.oMaskContainer.get_recMahnung()!=null)
			{
				vIDs.add(FIBU_MASKE_ButtonPrintMahnung.this.oMaskContainer.get_recMahnung().get_ID_MAHNUNG_cUF());
				for (int i=0;i<this.vIDs.size();i++)
				{
					vRueck.add(new ownJasperHASH(this.vIDs.get(i)));
				}
				
				FIBU_MASKE_ButtonPrintMahnung.this.vJasperHash.addAll(vRueck);
				
				return vRueck;
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mahnung kann erst gedruckt werden, wenn es einen gespeicherten Datensatz gibt !!")));
				return vRueck;
			}
		}


		
		private class ownJasperHASH extends E2_JasperHASH
		{
			private String cID_ADRESSE = null;
			
			public ownJasperHASH(String ID_MAHNUNG)	throws myException
			{
				super("FIBU_MAHNUNG", new JasperFileDef_PDF());

				RECORD_MAHNUNG recMahnung = FIBU_MASKE_ButtonPrintMahnung.this.oMaskContainer.get_recMahnung();
				
				this.cID_ADRESSE = recMahnung.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung().get(0).get_UP_RECORD_FIBU_id_fibu().get_ID_ADRESSE_cUF();
				
				this.put("id_mahnung",recMahnung.get_ID_MAHNUNG_cUF());

				this.set_bVorschauDruck(false);
				
				this.set_cDownloadAndSendeNameStaticPart("MAHNUNG"+"_"+recMahnung.get_MAHNSTUFE_cUF_NN("-")+"ADRESSE_"+this.cID_ADRESSE);
				
				//2012-02-13: aenderung um die archivdateien auf wunsch anhaengen zu koennen
				//this.get_vActionsAfterCreateTempFiles().add(new ownManipuliere_JasperHash());
				//2014-09-25: neue Jasper_Execute-mimik
				this.get_vExecuters().add(new Jasper_Execute_add_RG_Beleg());
				
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
												myCONST.EMAIL_TYPE_VALUE_MAHNUNG,
												new MyE2_String(myCONST.EMAIL_TYPE_TEXT_MAHNUNG),
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
				return true;           //wenn preview, dann keine mail
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
				return new MyE2_String("MAHNUNG");
			}

			@Override
			public String get_cBelegTyp() throws myException
			{
				return "MAHNUNG";
			}

			@Override
			public MyString get_cBelegTyp4User() throws myException
			{
				return new MyE2_String("MAHNUNG");
			}

			@Override
			public MyString get_cKommentar() throws myException
			{
				return new MyE2_String("Mail aus Mahnungsmodul");
			}

			@Override
			public String get_cModulInfo() throws myException
			{
				return FIBU_MASKE_ButtonPrintMahnung.this.oMaskContainer.get_oNaviList().get_oContainer_NaviList_BelongsTo().get_MODUL_IDENTIFIER();
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

//	/**
//	 * manipuliert die tempfiles in der jasperhash nach ausfuehrung
//	 * @author martin
//	 *
//	 */
//	private class ownManipuliere_JasperHash extends XX_ActionAfterCreateTempFiles
//	{
//
//		@Override
//		public MyE2_MessageVector doActionAfterCreatingTempFiles(E2_JasperHASH oJasperHash) throws myException
//		{
//			MyE2_MessageVector  oMV = new MyE2_MessageVector();
//			
//			FIBU_MASKE_ButtonPrintMahnung oThis = FIBU_MASKE_ButtonPrintMahnung.this;
//			
//			if (oThis.oMaskContainer.get_bPrintMahnungWithRechnungsArchivAnhang())
//			{
//				RECLIST_FIBU  recList = oThis.oMaskContainer.get_RecListFibu();
//				
//				Vector<String> vFilenamesOfArchiveFiles = new Vector<String>();
//				int iCountBelege= 0;
//				
//				for (RECORD_FIBU  recFibu: recList.values())
//				{
//					if (recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT) ||
//						recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG))
//					{
//						iCountBelege++;
//						
//						String cPfadZumArchiv = new __RECORD_FIBU_SPECIAL(recFibu).get__Pfad_Zu_Archivdatei();
//						
//						if (S.isEmpty(cPfadZumArchiv))
//						{
//							//wenn die archivdateien aufgerufen werden, dann muessen fuer alle rechnungen eine archivdatei vorhanden sein,
//							//sonst fehler
//							oMV.add_MESSAGE(new MyE2_Alarm_Message("Für mindestens einen Belegt innerhalb der gemahnten Belege ist kein Eintrag im Archiv vorhanden !!"));
////							return;
////							throw new myExceptionForUser(new MyE2_String("Für mindestens einen Belegt innerhalb der gemahnten Belege ist kein Eintrag im Archiv vorhanden !!"));
//						}
//						
//						if (S.isFull(cPfadZumArchiv))
//						{
//							if (new File(cPfadZumArchiv).exists())
//							{
//								vFilenamesOfArchiveFiles.add(cPfadZumArchiv);
//							}
//						}
//					}
//				} 
//				
//				//dann alles verhaengen und unter dem oThis.vJasperHash.get(0).get_HM_FILENAME_OF_TEMP_FILE() wieder abspeichern, fertig
//				Vector<String> vFileNames = new Vector<String>();
//				vFileNames.add(oJasperHash.get_HM_FILENAME_OF_TEMP_FILE());
//				vFileNames.addAll(vFilenamesOfArchiveFiles);
//				
//				pdfConcat  oConcater = new pdfConcat(vFileNames, bibE2.get_CurrSession());
//				
//				myTempFile  oMyFileMahnung_plus_Rechnungen = oConcater.baueZielDatei("komplette-download-mahnung-und-rechnungen");
//				oMyFileMahnung_plus_Rechnungen.set_bDeleteAtFinalize(true);
//				
//				//jetzt die mahnung im original-temp-file loeschen und durch das zusammengefasste file ersetzen
//				File oFileFromJasperHashMahnung = new File(oJasperHash.get_HM_FILENAME_OF_TEMP_FILE());
//				File oFileMahnung_plus_Rechnungen = new File(oMyFileMahnung_plus_Rechnungen.getFileName());
//				
//				oFileFromJasperHashMahnung.delete();
//
//				try
//				{
//					bibFILE.copyFile(oFileMahnung_plus_Rechnungen, oFileFromJasperHashMahnung);
//					
//					oFileMahnung_plus_Rechnungen = null;
//					oMyFileMahnung_plus_Rechnungen = null;
//				}
//				catch (IOException e)
//				{
//					e.printStackTrace();
//					throw new myException(this,e.getLocalizedMessage());
//				}
//			}
//			
//			return oMV;
//		}
//		
//	}
	
	
	
	//2014-09-25: neue implementierung mit der Jasper_Executer - mimik
	public class Jasper_Execute_add_RG_Beleg extends Jasper_Exe_ROOT {

		@Override
		public void EXECUTE(E2_JasperHASH oJasperHash, Object objZusatz, MyE2_MessageVector oMVRueck, Object oSammlerRueckgaben) throws myException {
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			FIBU_MASKE_ButtonPrintMahnung oThis = FIBU_MASKE_ButtonPrintMahnung.this;
			
			if (oThis.oMaskContainer.get_bPrintMahnungWithRechnungsArchivAnhang())
			{
				RECLIST_FIBU  recList = oThis.oMaskContainer.get_RecListFibu();
				
				Vector<String> vFilenamesOfArchiveFiles = new Vector<String>();
				
				for (RECORD_FIBU  recFibu: recList.values())
				{
					if (recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT) ||
						recFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG))
					{
						
						String cPfadZumArchiv = new __RECORD_FIBU_SPECIAL(recFibu).get__Pfad_Zu_Archivdatei();
						
						if (S.isEmpty(cPfadZumArchiv))
						{
							//wenn die archivdateien aufgerufen werden, dann muessen fuer alle rechnungen eine archivdatei vorhanden sein,
							//sonst fehler
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Für mindestens einen Belegt innerhalb der gemahnten Belege ist kein Eintrag im Archiv vorhanden !!"));
//							return;
//							throw new myExceptionForUser(new MyE2_String("Für mindestens einen Belegt innerhalb der gemahnten Belege ist kein Eintrag im Archiv vorhanden !!"));
						}
						
						if (S.isFull(cPfadZumArchiv))
						{
							if (new File(cPfadZumArchiv).exists())
							{
								vFilenamesOfArchiveFiles.add(cPfadZumArchiv);
							}
						}
					}
				} 
				
				//dann alles verhaengen und unter dem oThis.vJasperHash.get(0).get_HM_FILENAME_OF_TEMP_FILE() wieder abspeichern, fertig
				Vector<String> vFileNames = new Vector<String>();
				vFileNames.add(oJasperHash.get_HM_FILENAME_OF_TEMP_FILE());
				vFileNames.addAll(vFilenamesOfArchiveFiles);
				
				pdfConcat  oConcater = new pdfConcat(vFileNames);
				
				myTempFile  oMyFileMahnung_plus_Rechnungen = oConcater.baueZielDatei("komplette-download-mahnung-und-rechnungen");
				oMyFileMahnung_plus_Rechnungen.set_bDeleteAtFinalize(true);
				
				//jetzt die mahnung im original-temp-file loeschen und durch das zusammengefasste file ersetzen
				File oFileFromJasperHashMahnung = new File(oJasperHash.get_HM_FILENAME_OF_TEMP_FILE());
				File oFileMahnung_plus_Rechnungen = new File(oMyFileMahnung_plus_Rechnungen.getFileName());
				
				oFileFromJasperHashMahnung.delete();

				try
				{
					bibFILE.copyFile(oFileMahnung_plus_Rechnungen, oFileFromJasperHashMahnung);
					
					oFileMahnung_plus_Rechnungen = null;
					oMyFileMahnung_plus_Rechnungen = null;
				}
				catch (IOException e)
				{
					e.printStackTrace();
					throw new myException(this,e.getLocalizedMessage());
				}
			}
			
			bibMSG.add_MESSAGE(oMV);
			//return oMV;

			
		}

		@Override
		public EXECUTER_JUMPPOINTS get_JUMPMarker() {
			return EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_CREATE_TEMPFILE;
		}
		
	}
	
	
}
