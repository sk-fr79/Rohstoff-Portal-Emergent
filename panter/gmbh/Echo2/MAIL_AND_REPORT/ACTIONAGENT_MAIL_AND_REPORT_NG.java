package panter.gmbh.Echo2.MAIL_AND_REPORT;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public abstract class ACTIONAGENT_MAIL_AND_REPORT_NG extends XX_ActionAgent
{

	
	//section abstracts
	public abstract V_JasperHASH 				get_VJasperHashMAP(ExecINFO execInfo) throws myException;
	public abstract E2_MassMailer  			get_MassMailer() throws myException;
	public abstract boolean  					make_bActionBeforeStart(ExecINFO execInfo) throws myException;	
	
	//methode, um eine eigene subclass extends E2_BasicModuleContainer zu generieren, die eine separate groesse haben kann
	public abstract E2_BasicModuleContainer	build_Container4Popup(ExecINFO execInfo) throws myException;

	//je eine methode, um die einzelnen JasperHashes weiter zu differenzieren, je nach typ: print, mail, preview
	public abstract MyE2_MessageVector			MakeAddonSettings_and_PreChecks_4_Print(E2_JasperHASH oJasperHash) throws myException;
	public abstract MyE2_MessageVector   		MakeAddonSettings_and_PreChecks_4_Mail(E2_JasperHASH oJasperHash) throws myException;
	public abstract MyE2_MessageVector   		MakeAddonSettings_and_PreChecks_4_Preview(E2_JasperHASH oJasperHash) throws myException;
	public abstract int   					get_iWidth_4_Popup() throws myException;
	public abstract int   					get_iHeight_4_Popup() throws myException;
	//--end-- section abstracts
	
	
	private MyE2_String             	cFensterTitel = null;
	private String   					VALID_TAG_PRINT = null;
	private String   					VALID_TAG_MAIL = null;
	private String   					VALID_TAG_PREVIEW = null;
	
	
	/**
	 * 2011-03-22: weitere actionagenten zur behandlung vn folgeaktionen nach druck oder mail
	 */
	private  Vector<XX_ActionAgent>   vActionAgentsExecuteAfterPrint = new Vector<XX_ActionAgent>();
	private  Vector<XX_ActionAgent>   vActionAgentsExecuteAfterMail = new Vector<XX_ActionAgent>();
	private  Vector<XX_ActionAgent>   vActionAgentsExecuteAfterPreview = new Vector<XX_ActionAgent>();
	
	
	private V_JasperHASH 				VJasperHash = 		null;
	private E2_MassMailer    			MassMailer = 		null;
	
	private boolean   					bMailIsPossible = 	false;
	private boolean   					bStartPrint = 		false;
	private boolean  					bStartMail = 		false;
	private boolean  					bStartPreview = 	false;
	
	private MailBlock_Vector 			VMailBlock = null;

	private E2_BasicModuleContainer 	oContainerStartPrintOrMail = null;
	
	
	//wird benutzt, um das neue TAG E2_JasperHASH.get_bActiv() auszuwerten
	private  V_JasperHASH 				VJasperHash_REDU = null;             
	
	/**
	 * 
	 * @param fensterTitel
	 * @param valid_tag_print
	 * @param valid_tag_mail
	 * @param actionAfterPrintOrMail
	 * @param bIsPreview
	 */
	public ACTIONAGENT_MAIL_AND_REPORT_NG(		MyE2_String 	fensterTitel, 
												String 			valid_tag_print,
												String 			valid_tag_mail, 
												String  		valid_tag_preview) 
	{
		this.cFensterTitel = 			fensterTitel;
		this.VALID_TAG_PRINT = 			valid_tag_print;
		this.VALID_TAG_MAIL = 			valid_tag_mail;
		this.VALID_TAG_PREVIEW = 		valid_tag_preview;
		
		if (S.isEmpty(cFensterTitel)) {
			this.cFensterTitel=new MyE2_String("Bitte wählen ...");
		}
	}

	@Override
	public void executeAgentCode(ExecINFO execInfo) throws myException
	{
		if (this.make_bActionBeforeStart(execInfo))
		{
			ACTIONAGENT_MAIL_AND_REPORT_NG oThis = ACTIONAGENT_MAIL_AND_REPORT_NG.this;
			//zuerst die umgebung definieren und bewerten
			if (oThis.definiereObjekte(execInfo)) {
				oThis.oContainerStartPrintOrMail = ACTIONAGENT_MAIL_AND_REPORT_NG.this.build_Container4Popup(execInfo);
				oThis.oContainerStartPrintOrMail.CREATE_AND_SHOW_POPUPWINDOW(new Extent(this.get_iWidth_4_Popup()), new Extent(this.get_iWidth_4_Popup()), oThis.cFensterTitel);
			}
		}
	}


	



	public Vector<XX_ActionAgent> get_vActionAgentsExecuteAfterPrint() 
	{
		return vActionAgentsExecuteAfterPrint;
	}

	public Vector<XX_ActionAgent> get_vActionAgentsExecuteAfterMail() 
	{
		return vActionAgentsExecuteAfterMail;
	}



	
	
	
	private boolean  definiereObjekte(ExecINFO execInfo) throws myException {
		//zuerst die vorbereitungen treffen 
		boolean bRueck = true;
		this.VJasperHash= new V_JasperHASH();
		
		try
		{
			this.VJasperHash.addAll(ACTIONAGENT_MAIL_AND_REPORT_NG.this.get_VJasperHashMAP(execInfo));
			
			if (bibMSG.get_bHasAlarms()) {
				return false;
			}
			
			//jetzt pruefen, ob alles da ist
			if (this.VJasperHash.size()==0)	{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist nichts zu Drucken vorhanden !")));
				return false;
			}
			
			//jetzt pruefen, ob ein Mailer moeglich ist (sind die jasperHashs dafuer vorbereitet)
			this.bMailIsPossible = true;
			for (E2_JasperHASH jasperHASH:this.VJasperHash) {
				if (!jasperHASH.get_bIsDesignedForMail()) {
					this.bMailIsPossible = false;
					break;
				}
			}
			
			
			this.MassMailer = this.get_MassMailer();
			
			// ... und gibt es einen MassMailer
			if (this.MassMailer == null) 	{
				this.bMailIsPossible = false;
			}
			
			return bRueck;
			
		}
		catch (myExceptionForUser ex) {
			bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			return false;
		}
		
	}
	


	/**
	 * hiermit koennen diverse standard-einstellungen fuer die mail- und print-definition uebergeben werden.
	 * !!!Muss explizit von der rufenden Einheit aufgerufen werden
	 * @param oButtonPrint
	 * @param oButtonMail
	 * @throws myException
	 */
	public void PrepareStandardPrintAndMailButton(MyE2_Button oButtonPrint, MyE2_Button oButtonMail, MyE2_Button oButtonPreview) throws myException {
		if (oButtonPrint != null) {
			
			if (this.VALID_TAG_PRINT != null) 	{
				oButtonPrint.add_GlobalAUTHValidator_AUTO(ACTIONAGENT_MAIL_AND_REPORT_NG.this.VALID_TAG_PRINT);
			}

			oButtonPrint.add_oActionAgent(new ownActionStartPrint());
			
			for (int i=0;i<this.vActionAgentsExecuteAfterPrint.size();i++) {
				oButtonPrint.add_oActionAgent(this.vActionAgentsExecuteAfterPrint.get(i));
			}

		}
		
		if (oButtonMail != null) {
			
			if (ACTIONAGENT_MAIL_AND_REPORT_NG.this.VALID_TAG_MAIL != null) {
				oButtonMail.add_GlobalAUTHValidator_AUTO(ACTIONAGENT_MAIL_AND_REPORT_NG.this.VALID_TAG_MAIL);
			}
			
			oButtonMail.add_oActionAgent(new ownActionStartMail());
			
			for (int i=0;i<this.vActionAgentsExecuteAfterMail.size();i++) {
				oButtonMail.add_oActionAgent(this.vActionAgentsExecuteAfterMail.get(i));
			}
		}

		
		if (oButtonPreview != null) {
			
			if (ACTIONAGENT_MAIL_AND_REPORT_NG.this.VALID_TAG_PREVIEW != null) {
				oButtonPreview.add_GlobalAUTHValidator_AUTO(ACTIONAGENT_MAIL_AND_REPORT_NG.this.VALID_TAG_PREVIEW);
			}
			
			oButtonPreview.add_oActionAgent(new ownActionStartPreview());
			
			for (int i=0;i<this.vActionAgentsExecuteAfterPreview.size();i++) {
				oButtonPreview.add_oActionAgent(this.vActionAgentsExecuteAfterPreview.get(i));
			}
		}
	

		
	}
	
	



	private class ownActionStartPrint extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			ACTIONAGENT_MAIL_AND_REPORT_NG.this.build_pdf_dokuments_and_mail_blocks(true,false,false);
			if (bibMSG.get_bIsOK()) {
				ACTIONAGENT_MAIL_AND_REPORT_NG.this.oContainerStartPrintOrMail.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
	}


	private class ownActionStartPreview extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			ACTIONAGENT_MAIL_AND_REPORT_NG.this.build_pdf_dokuments_and_mail_blocks(false,false,true);
			if (bibMSG.get_bIsOK()) {
				ACTIONAGENT_MAIL_AND_REPORT_NG.this.oContainerStartPrintOrMail.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
	}


	
		
	private class ownActionStartMail extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			if (ACTIONAGENT_MAIL_AND_REPORT_NG.this.bMailIsPossible) {
				ACTIONAGENT_MAIL_AND_REPORT_NG.this.build_pdf_dokuments_and_mail_blocks(false,true,false);
				if (bibMSG.get_bIsOK()) {
					ACTIONAGENT_MAIL_AND_REPORT_NG.this.oContainerStartPrintOrMail.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mailen ist hier nicht möglich !!")));
			}
		}
	}


	

	/**
	 * 
	 * @param StartPrint
	 * @param StartMail
	 * @param bStartPreview  (nur eines von dreien darf true sein)
	 * @throws myException
	 */
	private void build_pdf_dokuments_and_mail_blocks(boolean StartPrint, boolean StartMail, boolean StartPreview) throws myException
	{
		this.bStartPrint = 	StartPrint;
		this.bStartMail = 	StartMail;
		this.bStartPreview = StartPreview;
		
		int iPrint = 	this.bStartPrint?1:0;
		int iMail =		this.bStartMail?1:0;
		int iPreview = 	this.bStartPreview?1:0;
		
		if (iPrint+iMail+iPreview!=1) 	{
			throw new myException(this,"Only one thing possible, mail, print or preview");
		}
		if (this.bStartMail && !this.bMailIsPossible) 	{
			throw new myException(this,"Mail is not possible");
		}

		this.VMailBlock = 		new MailBlock_Vector();
		
		
		
		if (this.VJasperHash.size()==0) 	{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine Dokumente erzeugt !")));
			return;
		}

		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		//zuerst die vorbereitung laufen lassen
		if (this.bStartPreview) {
			this.VJasperHash.set_TYPE_PREVIEW();
			for (E2_JasperHASH  oJasperHash: this.VJasperHash) { 
				oMV.add_MESSAGE(this.MakeAddonSettings_and_PreChecks_4_Preview(oJasperHash));
				oJasperHash.set_bVorschauDruck(true);
			}
			
		} else 	{
			if (this.bStartPrint)	{
				this.VJasperHash.set_TYPE_PRINT();
				for (E2_JasperHASH  oJasperHash: this.VJasperHash)	{ 
					oMV.add_MESSAGE(this.MakeAddonSettings_and_PreChecks_4_Print(oJasperHash)); 
				}
			} else {
				this.VJasperHash.set_TYPE_MAIL();
				for (E2_JasperHASH  oJasperHash: this.VJasperHash) { 
					oMV.add_MESSAGE(this.MakeAddonSettings_and_PreChecks_4_Mail(oJasperHash)); 
				}
			}
		}
		
		if (oMV.get_bHasAlarms()) {
			bibMSG.add_MESSAGE(oMV);
			return;
		} else if (oMV.get_bHasWarnings()) {
			bibMSG.add_MESSAGE(oMV);
		}
		
		//dann das inaktiv-tag in den jasperhashes auswerten
		this.VJasperHash_REDU = new V_JasperHASH();
		for (E2_JasperHASH  oHash: this.VJasperHash) {
			if (oHash.get_bActive()) {
				this.VJasperHash_REDU.add(oHash);
			}
		}

		if (this.VJasperHash_REDU.size()==0) 	{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine Dokumente erzeugt / keine aktiven Dokumente gefunden !")));
			return;
		}

		
		//wenn der mail-block mit uebergeben wird, dann zuerst die mailer-maske anzeigen (leer) damit das serverpush-fenster darueber liegt
		if (this.bStartMail)
		{
			//jetzt den mailer aufbauen
			this.MassMailer =  this.get_MassMailer();
			this.MassMailer.CREATE_AND_SHOW_POPUPWINDOW(null,null,S.isEmpty(this.cFensterTitel)?new MyE2_String("Mail ..."):this.cFensterTitel);					
		}

		
		if (this.VJasperHash_REDU.size()>10)     //dann ein fortschrittspopup anzeigen lassen
		{
			new E2_ServerPushMessageContainer(new Extent(500),new Extent(200),new MyE2_String("Aufbereitung der Druckjobs ..."),true,true,false,1000)
			{
				@Override
				public void Run_Loop() throws myException
				{
					ACTIONAGENT_MAIL_AND_REPORT_NG oThis = ACTIONAGENT_MAIL_AND_REPORT_NG.this;
					
					this.get_oGridBaseForMessages().addAfterRemoveAll(new MyE2_Label(new MyE2_String("Datenbank-Vorbereitung für die Belege ...")));
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(oThis.VJasperHash_REDU.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS(),true));
					
		
					if (bibMSG.get_bIsOK())
					{
						//2012-02-13: die rueckgabe von oThis.VJasperHash.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS() wird ausgewertet
						if (oThis.VJasperHash_REDU.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(this, false, (oThis.bStartPrint||oThis.bStartPreview)))
						{
							//2012-02-13: hier wird nochmals geprueft, ob alarm-messages vorhanden sind
							if (bibMSG.get_bIsOK())
							{
								if (oThis.bStartMail)
								{
									oThis.VMailBlock.addAll(oThis.VJasperHash_REDU.BUILD_AND_GET_V_MAIL_BLOCKS());
									
									//jetzt pruefen, ob alle MailBlocks in VMailBlocks ok sind
									if (oThis.VJasperHash_REDU.get_bAll_Have_MAILBLOCK_with_Minimum_One_MailAdresse())
									{
										oThis.MassMailer.baue_MailMaske(oThis.VMailBlock,bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),true,false,false,0,null);	
									}
									else
									{
										bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nicht alle Druckobjekte haben Mail-Informationen !!")));
									}
								} else {
								
									oThis.VJasperHash_REDU.DOWNLOAD_FILES(this.get_oGridBaseForMessages());
									
									if (bibMSG.get_bIsOK())
									{
										this.get_oGridBaseForMessages().addAfterRemoveAll(new MyE2_Label(new MyE2_String("..ich führe die Abschlussbefehle in der Datenbank aus")));
										bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(oThis.VJasperHash_REDU.get_vSQL_STACK_EXECUTE_AFTER_FROM_ALL_HASHMAPS(),true));
									}
								}
							}
						}
					}
				}
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}

			};
		}
		else
		{
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(this.VJasperHash_REDU.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS(),true));

			if (bibMSG.get_bIsOK())
			{
				this.VJasperHash_REDU.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(null, false,  (this.bStartPrint||this.bStartPreview));
				
				//2012-02-13: hier wird nochmals geprueft, ob alarm-messages vorhanden sind
				if (bibMSG.get_bIsOK())
				{
					if (this.bStartMail)
					{
						this.VMailBlock.addAll(this.VJasperHash_REDU.BUILD_AND_GET_V_MAIL_BLOCKS());
						//jetzt pruefen, ob alle MailBlocks in VMailBlocks ok sind
						if (this.VJasperHash_REDU.get_bAll_Have_MAILBLOCK_with_Minimum_One_MailAdresse())
						{
							this.MassMailer.baue_MailMaske(this.VMailBlock,bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),true,false,false,0,null);
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nicht alle Druckobjekte haben Mail-Informationen !!")));
						}
					} else 	{
						this.VJasperHash_REDU.DOWNLOAD_FILES(null);
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(this.VJasperHash_REDU.get_vSQL_STACK_EXECUTE_AFTER_FROM_ALL_HASHMAPS(),true));
						}
					}
				}				
			}
		}
	}
	
}
