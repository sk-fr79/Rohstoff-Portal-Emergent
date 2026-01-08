package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Label;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.TestingDate;
import panter.gmbh.indep.mail.MailException;
import panter.gmbh.indep.mail.SendMail;
import panter.gmbh.indep.myVectors.VectorSingle;

public abstract class MailBlock
{

	public static E2_ResourceIcon 	ICON_LEER = 						E2_ResourceIcon.get_RI("empty.png");
	public static E2_ResourceIcon 	ICON_OK = 							E2_ResourceIcon.get_RI("ok.png");
	public static E2_ResourceIcon 	ICON_ERROR = 						E2_ResourceIcon.get_RI("error.png");
	public static E2_ResourceIcon	ICON_FOR_DOWNLOAD_INTERNALFILE = 	E2_ResourceIcon.get_RI("printer.png");
	public static E2_ResourceIcon	ICON_FOR_DOWNLOAD_INTERNALFILES = 	E2_ResourceIcon.get_RI("printer_popup.png");
	
	
	/*
	 * jeder mail-entrag kann noch eine zusaetzliche anzahl dateien
	 * mitbekommen, die individuell mit diesem mail-eintrag verschickt werden
	 * (zusaetzlich zu den dateien, die jeder mitgesendet bekommt (uploads im mass-mailer)
	 */
	private	Vector<FileWithSendName>	  vOwnFiles = new Vector<FileWithSendName>();	
	

	/*
	 * es kann fuer jeden maileintrag mehrere zieladressen geben.
	 * dazu wird hier ein vector gefuehrt, der im normalfall nur eine adresse beinhaltet
	 * es sind objekte vom typ E2_MailAdressTextFieldAndSendeStatus im vector
	 */
	private MailAdress4MailBlock_Vector  vMailAdress4MailBlock = new MailAdress4MailBlock_Vector();
	
	
	/*
	 * Ein schalter, der protokolliert, ob ein eintrag auch benutzt wurde.
	 * sorgt dafuer, dass nur korrekt benutzte maileintraege auch im protokoll
	 * verwendet werden 
	 */
	private boolean 				bWasSendEvenOneTimes = false;
	
	
	
	/*
	 * vector mit infos, die (falls ein wiedervorlage-Adress-info - Feld gesetzt wird)
	 * als ueberschrift in diese Info geschrieben werden
	 */
	private Vector<String> 			vInfos4Wiedervorlage = new Vector<String>();

	
	/*
	 * Vectoren mit sql-statements nach sendung der eMail (z.B. fuer protokoll-infos usw)
	 * Datenoperationen aus jasper-dateien werden in der E2_JasperHASH verarbeitet und nach einem mailvorgang auch ausgefuehrt  
	 */
	private Vector<String>   		vSQLAfterSending = new Vector<String>();
	
	
	/*
	 * Grid fuer das einblenden des mailblockes in die mail-maske
	 */
	private MyE2_Grid  				oGridForThisBlock = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	/*
	 * sicherheitspolicy fuer das bearbeiten der mail-liste
	 */
	private MailSecurityPolicy      oMailSecPolicy = null;

	
	/*
	 * falls der mailblock von einem E2_JasperHash - element herruehrt, muss dem Mailblock mitgeteilt werden, welcher jasperHash das ist
	 */
	private E2_JasperHASH           oJasperHash_this_comes_From = null;
	
	
	
	/*
	 * die drei buttons neu, suche, mitarbeiter
	 */
	private B__Button_ADD_NEW_MailAdress 		oButtAddNewMail = null;
	private B__Button_SEARCH_MailAdress 		oButtSearchMailAdress = null;
	private B__PopupMenue_MailAdress_OwnCompany oPopUpMitarbeiter = null;
	
	
	/**
	 * 
	 * @param mailSecurityPolicy
	 * @throws myException
	 */
	public MailBlock(MailSecurityPolicy  mailSecurityPolicy) throws myException
	{
		this.oMailSecPolicy = 		mailSecurityPolicy;
	}

	
	//abstrakte methoden
	public abstract String   	get_cModulInfo() throws myException;              		// info aus welchem modul der aufruf kommt
	public abstract MyString   	get_cKommentar() throws myException;					// frei definierbar
	public abstract String    	get_cBelegTyp() throws myException;						// Belegtyp
	public abstract MyString   	get_cBelegTyp4User() throws myException;				// Belegbeschreibung
	
	public abstract Component  	get_ComponentForMailerList() throws myException;		//anzeigekomponente fuer die E2_MassMailer - Liste
	public abstract MyString  	get_ComponentTextForProtokoll() throws myException;		//Text auf der anzeigekomponente

	protected abstract MailAdress4MailBlock  	build_MailAdress4MailBlock(String cMailAdresse,MailBlock OWN_MailBlock) throws myException;
	
	/*
	 * falls ein mailblock die moeglichkeit hat, adressen hinzuzufuegen, muss auch fuer die jeweiligen moeglichkeiten
	 * eine mailadress4mailblock-definition vorhanden sein
	 */
	protected abstract MailAdress4MailBlock 	build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException;
	protected abstract MailAdress4MailBlock 	build_MailAdress4MailBlock4Added_SearchedMail(String cMailAdress,MailBlock OWN_MailBlock) throws myException;
	protected abstract MailAdress4MailBlock 	build_MailAdress4MailBlock4Added_MitarbeiterMail(String cMailAdress,MailBlock OWN_MailBlock) throws myException;

	
	public void set_EnableAdd_Buttons(boolean benabled) throws myException
	{
		if (this.oButtAddNewMail != null) this.oButtAddNewMail.set_bEnabled_For_Edit(benabled);
		if (this.oButtSearchMailAdress != null) this.oButtSearchMailAdress.set_bEnabled_For_Edit(benabled);
		if (this.oPopUpMitarbeiter != null) this.oPopUpMitarbeiter.set_bEnabled_For_Edit(benabled);
	}
	

	/**
	 * 
	 * @param mailAdresse
	 * @throws myException
	 */
	public void ADD_NewTargetAdress(String mailAdresse) throws myException
	{
		this.vMailAdress4MailBlock.add(this.build_MailAdress4MailBlock(mailAdresse, this));
	}
	
	public void ADD_NewTargetAdress_interactivEmptyAdress() throws myException
	{
		this.vMailAdress4MailBlock.add(this.build_MailAdress4MailBlock4Added_EmptyMail(this));
	}

	public void ADD_MailAdress4MailBlock4Added_SearchedMail(String mailAdresse) throws myException
	{
		this.vMailAdress4MailBlock.add(this.build_MailAdress4MailBlock4Added_SearchedMail(mailAdresse,this));
	}

	public void ADD_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdresse) throws myException
	{
		this.vMailAdress4MailBlock.add(this.build_MailAdress4MailBlock4Added_MitarbeiterMail(mailAdresse,this));
	}

	
	public void add_VMailAdress4MailBlock(MailAdress4MailBlock_Vector VMailAdresses)
	{
		this.vMailAdress4MailBlock.addAll(VMailAdresses);
		for (MailAdress4MailBlock oMailAdress: VMailAdresses)
		{
			oMailAdress.set_oMailBlock_This_BelongsTo(this);
		}
	}
	
	
	/**
	 * anhaengen eines files, das nur an die definierten adressen versendet wird, zusammen mit evtl. hochgeladenen 
	 * dateien
	 */
	public void ADD_OwnAttachement(FileWithSendName oAttacheFile)
	{
		this.vOwnFiles.add(oAttacheFile);
	}
	
	
	/**
	 * @return s Downloadbutton (if there are ownfiles) with download-Action for the first file (or empty label)
	 */
	private Component get_DownloadComponent()
	{
		Component oCompRueck = new Label("");
		
		
		if (this.vOwnFiles.size()>0)
		{
			if (this.vOwnFiles.size()==1)
			{
				FileWithSendName ofile = (FileWithSendName)this.vOwnFiles.get(0);
				ownDownButton oButton = new ownDownButton(ofile,MailBlock.ICON_FOR_DOWNLOAD_INTERNALFILE);
				oCompRueck = oButton;
			}
			else
			{
				
				MyE2_PopUpMenue oPopUp = new MyE2_PopUpMenue(MailBlock.ICON_FOR_DOWNLOAD_INTERNALFILES,MailBlock.ICON_FOR_DOWNLOAD_INTERNALFILES,false);
				for (int i=0;i<this.vOwnFiles.size();i++)
				{
					oPopUp.addButton(new ownDownButton(this.vOwnFiles.get(i)), true);
					DEBUG.System_println(this.vOwnFiles.get(i).get_cNameWithPath(), "");
				}
				oCompRueck = oPopUp;
			}
			
		}
		return oCompRueck;
	}
	
	
	
	
	private class ownDownButton extends MyE2_Button
	{
		private FileWithSendName oFileToDownload = null;

		public ownDownButton(FileWithSendName fileToDownload, E2_ResourceIcon oIcon)
		{
			super(oIcon);
			oFileToDownload = fileToDownload;
			this.add_oActionAgent(new ownDownloadAction());
		}
		public ownDownButton(FileWithSendName fileToDownload)
		{
			super(fileToDownload.get_cNameFor_USER_EmailAttachment());
			oFileToDownload = fileToDownload;
			this.add_oActionAgent(new ownDownloadAction());
		}
		
		private class ownDownloadAction extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				FileWithSendName 	ofile = 	ownDownButton.this.oFileToDownload;
				try
				{
					ofile.Download_File();
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}


	
	/**
	 * setzt alle sendestatus-labels der mail-objekte auf status neutral
	 */
	public void setLabelNeutral()
	{
		for (int i=0;i<this.vMailAdress4MailBlock.size();i++)
		{
			MailAdress4MailBlock oMailObject =  (MailAdress4MailBlock)this.vMailAdress4MailBlock.get(i);
			oMailObject.setLabelNeutral();
		}
	}

	
	
	
	/**
	 * @return s true, wenn mindestens eine zieladresse ausgewaehlt ist
	 */
	public boolean get_b_EVEN_ONE_TargetAdress_isSelected()
	{
		for (int i=0;i<this.vMailAdress4MailBlock.size();i++)
		{
			MailAdress4MailBlock oMailObject =  (MailAdress4MailBlock)this.vMailAdress4MailBlock.get(i);
			if (oMailObject.get_oCheckBox_MailForSending().isSelected())
				return true;
		}
		return false;
	}


	
	/**
	 * @return s true, wenn mindestens eine zieladresse ausgewaehlt ist
	 */
	public boolean get_b_EVEN_ONE_TargetAdress_isSelected_and_has_TargetAdress()
	{
		for (int i=0;i<this.vMailAdress4MailBlock.size();i++)
		{
			MailAdress4MailBlock oMailObject =  (MailAdress4MailBlock)this.vMailAdress4MailBlock.get(i);
			if (oMailObject.get_oCheckBox_MailForSending().isSelected() && S.isFull(oMailObject.get_eMailAdresseZiel()))
				return true;
		}
		return false;
	}

	
	
	//NEU_09
	/**
	 * @return s true, wenn mindestens eine zieladresse NICHT ausgewaehlt ist
	 */
	public boolean get_b_EVEN_ONE_TargetAdress_isUnSelected()
	{
		for (int i=0;i<this.vMailAdress4MailBlock.size();i++)
		{
			MailAdress4MailBlock oMailObject =  (MailAdress4MailBlock)this.vMailAdress4MailBlock.get(i);
			if (!oMailObject.get_oCheckBox_MailForSending().isSelected())
				return true;
		}
		return false;
	}




	/*
	 * die dateien, die jedem einzelnen mail-eintrag mitgegeben werden (koennen)
	 */
	public Vector<FileWithSendName> get_vOwnFiles()
	{
		return this.vOwnFiles;
	}
	
	
	
	public MailAdress4MailBlock_Vector 				get_v_MailAdress4MailBlock() 				{	return vMailAdress4MailBlock;	}
	public boolean 								get_bWasSendEvenOneTimes()					{	return bWasSendEvenOneTimes;	}
	public void 								set_bWasSendEvenOneTimes(boolean wasUsed)	{	bWasSendEvenOneTimes = wasUsed;	}
	

	public Vector<String> get_vInfos4Wiedervorlage()
	{
		return vInfos4Wiedervorlage;
	}

	public Vector<String> get_vSQLAfterSending()
	{
		return vSQLAfterSending;
	}
	
	
	/**
	 * 
	 * @param oGridWithMails
	 * fuegt dem grid fuer eine MailAdress4MailEintrag eine zeile hinzu (mit 5 spalten)
	 */
	public void build_MailSendGrid() throws myException
	{
	
		this.oGridForThisBlock.removeAll();
		this.oGridForThisBlock.setSize(6);
		
		/*
		 * es gibt (manchmal) mehrere zieladressen fuer eine zu versendende mail
		 */
		Vector<MailAdress4MailBlock> vMailAdress4MailEintrag = this.get_v_MailAdress4MailBlock();

		this.add_PDF_From_JasperHASH_to_OwnFiles();
		
//		System.out.println("Blockgroesse: "+vMailAdress4MailEintrag.size());
		
		for (int k=0;k<vMailAdress4MailEintrag.size();k++)
		{
			MailAdress4MailBlock oMailAdress4MailEintrag = vMailAdress4MailEintrag.get(k);

			oGridForThisBlock.add(oMailAdress4MailEintrag.get_oCheckBox_MailForSending(), E2_INSETS.I_0_0_5_0);
			oGridForThisBlock.add(oMailAdress4MailEintrag.get_oTextFieldWithMailAdress(), E2_INSETS.I_0_0_5_0);

			oGridForThisBlock.add(oMailAdress4MailEintrag.get_oLabelSendeStatus(), E2_INSETS.I_0_0_5_0);

			/*
			 * in der ersten zeile den download-knopf fuer die mail-gebundenen anlagen einblenden
			 */
			oGridForThisBlock.add(k==0?this.get_DownloadComponent():new MyE2_Label(""), E2_INSETS.I_0_0_5_0);
			
			if (k==0)
			{
				oGridForThisBlock.add(new TitelBlockMitButtons(), E2_INSETS.I_0_0_5_0);   //falls im block auch moeglichkeiten existeren, etwas anzuhaengen, dann diese knopfleiste einblenden
			}
			else
			{
				oGridForThisBlock.add(new MyE2_Label(""), E2_INSETS.I_0_0_5_0);
			}

			oGridForThisBlock.add(new E2_ComponentGroupHorizontal(0,this.get_ComponentForMailerList(),oMailAdress4MailEintrag.get_ComponentForMailerList(),E2_INSETS.I_0_0_20_0), E2_INSETS.I_0_0_5_0);				
			
		}
		
	}
		

	private void add_PDF_From_JasperHASH_to_OwnFiles() throws myException
	{
		//zuerst nachsehen, ob der mailBlock von einem E2_JasperHash erzeugt wurde, wenn ja muss das temp-PDF hier angehaengt werden
		if (this.oJasperHash_this_comes_From!=null)
		{
			if (!this.vOwnFiles.contains(this.oJasperHash_this_comes_From.get_oTempFileWithSendeName()))
			{
				this.vOwnFiles.add(this.oJasperHash_this_comes_From.get_oTempFileWithSendeName());
			}
		}
	}

	
	
	/**
	 * 
	 * @param oGridWithMails
	 * fuegt dem grid fuer eine MailAdress4MailEintrag eine zeile hinzu (mit 5 spalten)
	 */
	public Component get_MailSendGrid() throws myException
	{
		
		this.build_MailSendGrid();
		
		MyE2_Row  oRow = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		oRow.add(oGridForThisBlock, E2_INSETS.I_1_1_1_1);
		
		return oRow;
		
	}

	public MailSecurityPolicy  get_oMailSecurityPolicy()
	{
		return this.oMailSecPolicy;
	}


	
	private class TitelBlockMitButtons extends MyE2_Row
	{

		public TitelBlockMitButtons() throws myException
		{
			super(MyE2_Row.STYLE_NO_BORDER());
			
			MailBlock oThis = MailBlock.this;
			
			if (oThis.oMailSecPolicy.bAllowNewEmptyAdress)
			{
				oThis.oButtAddNewMail = new B__Button_ADD_NEW_MailAdress(oThis);
				this.add(oThis.oButtAddNewMail,E2_INSETS.I_0_0_2_0);
			}
			if (oThis.oMailSecPolicy.bAllowAddMailFromAdressSeach)
			{
				oThis.oButtSearchMailAdress = new B__Button_SEARCH_MailAdress(oThis);
				this.add(oThis.oButtSearchMailAdress,E2_INSETS.I_0_0_2_0);
			}
			if (oThis.oMailSecPolicy.bAllowAddMailFromEployesPopup)
			{
				oThis.oPopUpMitarbeiter = new B__PopupMenue_MailAdress_OwnCompany(oThis);
				this.add(oThis.oPopUpMitarbeiter,E2_INSETS.I_0_0_2_0);
			}
		}
	}


	
	
	
	
	/**
	 * 
	 * @param vAddonFiles
	 * @param Absenderadresse
	 * @param Betreff
	 * @param Textblock
	 * @param bSchreibeWiedervorlage
	 * @param DatumWiedervorlage
	 * @param oSendeLog  (Wenn null, dann kein sendelog)
	 * @return s int[2], erster wert ist anzahl erfolgreich gesendet, zweiter wert ist anzahl Fehler
	 * @throws myException
	 * @throws IOException
	 */
	public int[] SendeMails(	Vector<FileWithSendName> vAddonFiles, 
								String 						Absenderadresse, 
								String 						Betreff, 
								String 						Textblock,
								boolean  					bSchreibeWiedervorlage,
								String   					DatumWiedervorlage,
								FileWriter  				oSendeLog,
								MyE2_Grid    				oGridForServerPushMessage) throws myException,IOException
	{
		
		int[] iRueck = new int[2];             //iRueck[0] = erfolgreiche sendungen
											   //iRueck[1] = fehlerSendungen
		iRueck[0]=0;
		iRueck[1]=0;
	
		// anlagenvector, zuerst eigene, dann upgeloadete
		Vector<FileWithSendName> vAttach = new Vector<FileWithSendName>();
		
		vAttach.addAll(this.get_vOwnFiles());
		vAttach.addAll(vAddonFiles);
		
	
		/*
		 * jetzt den inneren Mailadressenvector durchfraesen 
		 */
		for (MailAdress4MailBlock oMailAdress4MailBlock : this.get_v_MailAdress4MailBlock())
		{

			//falls mit derverpush, dann meldung generieren
			if (oGridForServerPushMessage != null)
			{
				oGridForServerPushMessage.removeAll();
				
				MyString cInfo1 = this.get_ComponentTextForProtokoll();
				if (cInfo1==null)
				{
					cInfo1 = new MyE2_String("");
				}
				
				if (oMailAdress4MailBlock.get_cComponentText()!=null)
				{
					cInfo1.addString(oMailAdress4MailBlock.get_cComponentText());
				}
				
				cInfo1.addTranslated(" --> "+oMailAdress4MailBlock.get_eMailAdresseZiel());
				
				oGridForServerPushMessage.add(new MyE2_Label(cInfo1));
			}
			
			/*
			 * es werden nur ausgefuellte zieladressen beruecksichtigt
			 */
			if (bibALL.isEmpty(oMailAdress4MailBlock.get_eMailAdresseZiel()))
				continue;

			/*
			 * und nur selektierte mailadressen
			 */
			if (!oMailAdress4MailBlock.get_oCheckBox_MailForSending().isSelected())
				continue;


			// nachsehen, ob das mailobjekt eine adress-id hat, wenn ja, wird diese in das logfile geschrieben
			String cID_ADRESS_Info = S.isFull(oMailAdress4MailBlock.get_cID_ADRESSE_EMPFAENGER())?"ID_ADRESS: "+oMailAdress4MailBlock.get_cID_ADRESSE_EMPFAENGER():"--";
				
			String cZielAdresse = oMailAdress4MailBlock.get_eMailAdresseZiel();
			
			// hier die mail versenden 
			try
			{
				
				SendMail  oMail = new SendMail(		bibALL.get_RECORD_MANDANT().get_MAILSERVER_cUF(), 
													bibALL.get_RECORD_MANDANT().get_MAILUSERNAME_cUF(), 
													bibALL.get_RECORD_MANDANT().get_MAILPASSWORD_cUF(), 
													Absenderadresse, 
													cZielAdresse, null, null,
													Betreff, 
													Textblock, 
													vAttach);

				oMail.sendViaStandardMail();
				
				if (oSendeLog != null)	oSendeLog.write("Empfänger: " + cZielAdresse + "\t" + "Sender: "+ Absenderadresse.trim() + "\t  Adress-ID:" +cID_ADRESS_Info+"\t"+this.get_ComponentTextForProtokoll()+"\t"+ "  **OK** " + "\r\n");
				if (oSendeLog != null)	oSendeLog.flush();

				oMailAdress4MailBlock.setLabelOK();
				
				//2014-10-22: vermerk schreiben, dass senden erflogreich war
				oMailAdress4MailBlock.set_bLastSendingWasSuccessfull(true);
				oMailAdress4MailBlock.set_cLastSendAdresse(Absenderadresse);
				// dieser Eintrag WAS USED !!! -> landet mit seinen anlagen im protokoll
				this.set_bWasSendEvenOneTimes(true);

				
				iRueck[0] ++;
				
			}
			catch (MailException ex)
			{
			
				if (oSendeLog != null)	oSendeLog.write("Empfänger: " + cZielAdresse + "\t" + "Sender: "+ Absenderadresse.trim() + "\t  Adress-ID:" +cID_ADRESS_Info+"\t"+this.get_ComponentTextForProtokoll()+"\t"+  "  **ERROR** " + "\r\n");
				if (oSendeLog != null)	oSendeLog.write("Fehler : ************************************************\n");
				if (oSendeLog != null)	oSendeLog.write(ex.getErrorMessage() + "\n\n");
				if (oSendeLog != null)	oSendeLog.write("*********************************************************\n\n");
				if (oSendeLog != null)	oSendeLog.flush();

				oMailAdress4MailBlock.setLabelError();
				
				iRueck[1] ++;
				
			}

			/*
			 * serverpush pruefen und auf abbruch reagieren
			 */
			if (oGridForServerPushMessage!=null && E2_ServerPushMessageContainer.get_LoopStopped())
			{
				break;
			}
			
		}
		
		//2013-01-15: dafuer sorgen, dass jede adress-id aus dem mailblock nur einen vermerk fuer das email bekommt
		VectorSingle  vAdressIDs = new VectorSingle();
		for (MailAdress4MailBlock oMailAdress4MailBlock : this.get_v_MailAdress4MailBlock())
		{
			if (S.isFull(oMailAdress4MailBlock.get_cID_ADRESSE_EMPFAENGER()))
			{
				vAdressIDs.add(oMailAdress4MailBlock.get_cID_ADRESSE_EMPFAENGER());
			}
		}
		
		/*
		 * wiedervorlage schreiben
		 */
		if (bSchreibeWiedervorlage)
		{
			this.SchreibeWiederVorlagen(DatumWiedervorlage,Betreff,Textblock,vAdressIDs);
		}

		
		return iRueck;
		
	}
	
	
	private void SchreibeWiederVorlagen(String cDatumWiederVorlage, String Betreff, String cMailTextBlock, Vector<String> vAdressIDs) throws myExceptionForUser,myException
	{

		Vector<String> vSQL = new Vector<String>();
		
		//2013-01-15: nur einen eintrag pro adresse in die wiedervorlage schreiben
		//for (MailAdress4MailBlock oMailAdress4MailBlock:this.get_v_MailAdress4MailBlock())
		for (String cID_ADRESS: vAdressIDs)
		{
		
			/*
			 * bei leerer adress-id im massenmailer keine info moeglich
			 */
//			if (bibALL.isEmpty(oMailAdress4MailBlock.get_cID_ADRESSE_EMPFAENGER()))
//				return;
			
			
			String cSQLBlockDatum = "NULL";
			if (!bibALL.isEmpty(cDatumWiederVorlage))
			{
				TestingDate oTD = new TestingDate(cDatumWiederVorlage);
			
				if (!oTD.testing())
					throw new myExceptionForUser("Falsches Datum angegeben für Wiedervorlage !!!");
				
				cSQLBlockDatum = oTD.get_ISO_DateFormat(true);
			}
			
	        String cHelpText = "Mailversand: Massenmail\n\n";

	        String cHBetreff = new MyE2_String("Betreff").CTrans();
	        
	        cHelpText+=cHBetreff+":" + S.NN(Betreff)+"\n\n";
	        
	        //2013-01-15: mail-block einfuegen in die Infotexte
	        if (S.isFull(cMailTextBlock))
	        {
	        	cHelpText += "-----------<Mail-Text>-----------\n";
	        	cHelpText += cMailTextBlock;
	        	cHelpText += "\n-----------<Mail-Text>-----------\n";
	        }
	        
	        // falls ueberschrift im E2_MailEintrag4MassMailer - object steht, dann das nehmen
	        if (this.get_vInfos4Wiedervorlage().size()>0)
	        {
	        	cHelpText = bibALL.Concatenate(this.get_vInfos4Wiedervorlage(), "\n", "")+"\n";
	        }
	        
	        
	        MySqlStatementBuilder oSQL_ZU_Kopf = new MySqlStatementBuilder();
	        oSQL_ZU_Kopf.addSQL_Paar("ID_ADRESSE_INFO","SEQ_ADRESSE_INFO.NEXTVAL",false);
	       // oSQL_ZU_Kopf.addSQL_Paar("ID_ADRESSE",oMailAdress4MailBlock.get_cID_ADRESSE_EMPFAENGER(),false);
	        oSQL_ZU_Kopf.addSQL_Paar("ID_ADRESSE",cID_ADRESS,false);
	        oSQL_ZU_Kopf.addSQL_Paar("TEXT",bibALL.get_LeftString(cHelpText,1999) ,true);
	        oSQL_ZU_Kopf.addSQL_Paar("DATUMEINTRAG","SYSDATE",false);
	        oSQL_ZU_Kopf.addSQL_Paar("DATUMEREIGNIS","SYSDATE",false);
	        oSQL_ZU_Kopf.addSQL_Paar("FOLGEDATUM",cSQLBlockDatum,false);
	        oSQL_ZU_Kopf.addSQL_Paar("KUERZEL",bibALL.get_KUERZEL(),true);

	        String SQL_INFO = "INSERT INTO "+bibE2.cTO()+".JT_ADRESSE_INFO "+oSQL_ZU_Kopf.get_cFieldsBlock(true)+" VALUES "+oSQL_ZU_Kopf.get_cValuesBlock(true);
	        
	        vSQL.add(SQL_INFO);
		}
		
        if (!bibDB.ExecSQL(vSQL,true))
        	throw new myExceptionForUser("Fehler beim Schreiben der Wiedervorlage-Notizen !!!");

	}


	public E2_JasperHASH get_oJasperHash_this_comes_From()
	{
		return oJasperHash_this_comes_From;
	}


	public void set_JasperHash_this_comes_From(	E2_JasperHASH jasperHash_this_comes_From)
	{
		this.oJasperHash_this_comes_From = jasperHash_this_comes_From;
	}

	
}
