package panter.gmbh.Echo2.MAIL_AND_REPORT;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import echopointng.Separator;

public abstract class ACTIONAGENT_MAIL_AND_REPORT extends XX_ActionAgent
{

	private XX_ActionAgent          ActionAfterPrintOrMail = null;
	private MyE2_String             cFensterTitel = null;
	
	private String   				VALID_TAG_PRINT = null;
	private String   				VALID_TAG_MAIL = null;
	
	private XX_ActionAgent          ownActionStart_PrintPopup = null;
	private XX_ActionAgent          ownActionStart_Print = null;
	private XX_ActionAgent          ownActionStart_Mail = null;
	
	/**
	 * 2011-03-22: weitere actionagenten zur behandlung vn folgeaktionen nach druck oder mail
	 */
	private  Vector<XX_ActionAgent>   vActionAgentsExecuteAfterPrint = new Vector<XX_ActionAgent>();
	private  Vector<XX_ActionAgent>   vActionAgentsExecuteAfterMail = new Vector<XX_ActionAgent>();
	
	/*
	 * aenderung 2010-10-19: es muss hier uebergeben werden, ob es ein preview ist
	 */
	private boolean   				bIsPreview = false;
	
	
	/**
	 * 2015-05-08: weiterer schalter, der benutzt werden kann, um die auswahl zum mailen explizit zu unterdruecken,
	 *             z.B. wenn, wie in den Rechnungen/Gutschriften bestimmte kunden direkt angemailt werden
	 */
	private boolean    				bExplicitSupressMail = false;
	
	
	/*
	 * 2016-01-26: den closebutton unterdruecken, da beim komplett-stornieren sonst ein geschlossener beleg existiert, der
	 *             keinen eintrag im Druckprotokoll hat 
	 */
	private boolean    				b_suppress_window_close_button = false;
	
	
	/**
	 * 
	 * @param fensterTitel
	 * @param valid_tag_print
	 * @param valid_tag_mail
	 * @param actionAfterPrintOrMail
	 * @param bIsPreview
	 */
	public ACTIONAGENT_MAIL_AND_REPORT(	MyE2_String 	fensterTitel, 
										String 			valid_tag_print,
										String 			valid_tag_mail, 
										XX_ActionAgent 	actionAfterPrintOrMail,
										boolean         isPreview) 
	{
		cFensterTitel = fensterTitel;
		VALID_TAG_PRINT = valid_tag_print;
		VALID_TAG_MAIL = valid_tag_mail;
		ActionAfterPrintOrMail = actionAfterPrintOrMail;
		
		this.bIsPreview = isPreview;
	}

	@Override
	public void executeAgentCode(ExecINFO execInfo) throws myException
	{
		if (this.bACTION_BEFORE_START___TO_OVERWRITE())
		{
			new popUp_to_select_mail_or_print(execInfo);
		}
	}


	public boolean bACTION_BEFORE_START___TO_OVERWRITE() throws myException
	{
		return true;
	}
	
	

	public abstract V_JasperHASH 		get_VJasperHashMAP(ExecINFO execInfo) throws myException;
	public abstract E2_MassMailer  		get_MassMailer() throws myException;


	public Vector<XX_ActionAgent> get_vActionAgentsExecuteAfterPrint() 
	{
		return vActionAgentsExecuteAfterPrint;
	}

	public Vector<XX_ActionAgent> get_vActionAgentsExecuteAfterMail() 
	{
		return vActionAgentsExecuteAfterMail;
	}



	public XX_ActionAgent get_ActionAfterPrintOrMail()
	{
		return ActionAfterPrintOrMail;
	}

	public void set_ActionAfterPrintOrMail(XX_ActionAgent actionAfterPrintOrMail)
	{
		ActionAfterPrintOrMail = actionAfterPrintOrMail;
	}

	
	
	/*
	 * hier werden alle massendaten gehalten, damit der speicher wieder geraumt werden kann, nachdem
	 * der vorgang zuende ist
	 */
	private class popUp_to_select_mail_or_print extends E2_BasicModuleContainer
	{
		private V_JasperHASH 			VJasperHash = 			new V_JasperHASH();
		private E2_MassMailer    		MassMailer = 			null;
		
		private boolean   				bMailIsPossible = false;
		private boolean   				bStartDownload = false;
		private boolean  				bStartMail = false;
		
		private boolean    				bPrintOrMail = true;
		
		private MailBlock_Vector 		VMailBlock = null;

		
		private  MyE2_Button 			oButtonPrint = null;
		private  MyE2_Button	 		oButtonMail = null;

		
		
		public popUp_to_select_mail_or_print(ExecINFO execInfo) throws myException
		{
			super();

			ACTIONAGENT_MAIL_AND_REPORT oThis = ACTIONAGENT_MAIL_AND_REPORT.this;
			
			try
			{
				this.VJasperHash.addAll(ACTIONAGENT_MAIL_AND_REPORT.this.get_VJasperHashMAP(execInfo));
			}
			catch (myExceptionForUser ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				return;
			}
			
			
			if (bibMSG.get_bHasAlarms())
			{
				return;
			}
			
			//jetzt pruefen, ob alles da ist
			if (this.VJasperHash.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist nichts zu Drucken vorhanden !")));
				return;
			}
			
			
			//jetzt pruefen, ob ein Mailer moeglich ist (sind die jasperHashs dafuer vorbereitet)
			this.bMailIsPossible = true;
			for (E2_JasperHASH jasperHASH:this.VJasperHash)
			{
				if (!jasperHASH.get_bIsDesignedForMail())
				{
					this.bMailIsPossible = false;
					break;
				}
			}
			
			
			//2016-01-26: hier kann der schliessbutton unterdrueckt werden
			if (ACTIONAGENT_MAIL_AND_REPORT.this.b_suppress_window_close_button) {
				this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_STANDARD(false));
			}
			
			this.MassMailer = ACTIONAGENT_MAIL_AND_REPORT.this.get_MassMailer();
			
			// ... und gibt es einen MassMailer
			if (this.MassMailer == null)
			{
				this.bMailIsPossible = false;
			}
			
			
			MyE2_Grid  oGridBase = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());



			oGridBase.add(new MyE2_Label(new MyE2_String("Bitte wählen Sie ..."), MyE2_Label.STYLE_NORMAL_BOLD()),2,E2_INSETS.I_5_5_5_5);
			oGridBase.add(new Separator(),2, E2_INSETS.I_5_10_5_10);
			
			oButtonPrint = new MyE2_Button(new MyE2_String("Druck"));
			oButtonMail = new MyE2_Button(new MyE2_String("Mail"));
			
			if (ACTIONAGENT_MAIL_AND_REPORT.this.VALID_TAG_PRINT != null)
			{
				oButtonPrint.add_GlobalAUTHValidator_AUTO(ACTIONAGENT_MAIL_AND_REPORT.this.VALID_TAG_PRINT);
			}
			if (ACTIONAGENT_MAIL_AND_REPORT.this.VALID_TAG_MAIL != null)
			{
				oButtonMail.add_GlobalAUTHValidator_AUTO(ACTIONAGENT_MAIL_AND_REPORT.this.VALID_TAG_MAIL);
			}
			
			oButtonPrint.add_oActionAgent(oThis.ownActionStart_Print=new ownActionStartPrint());
			oButtonMail.add_oActionAgent(oThis.ownActionStart_Mail=new ownActionStartMail());
			
			//2011-03-22: weitere actionagents einschleussen
			for (int i=0;i<oThis.vActionAgentsExecuteAfterPrint.size();i++)
			{
				oButtonPrint.add_oActionAgent(oThis.vActionAgentsExecuteAfterPrint.get(i));
			}
			
			for (int i=0;i<oThis.vActionAgentsExecuteAfterMail.size();i++)
			{
				oButtonMail.add_oActionAgent(oThis.vActionAgentsExecuteAfterMail.get(i));
			}
			
			
			
			oGridBase.add(oButtonPrint,1, E2_INSETS.I_5_10_5_10);
			oGridBase.add(oButtonMail,1, E2_INSETS.I_5_10_5_10);
			
			this.add(oGridBase, E2_INSETS.I_5_5_5_5);
			
			if (bMailIsPossible)     //nur dann erfolgt die moeglichkeit zu waehlen, sonst wird direkt gedruckt
			{
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(300), new Extent(200), new MyE2_String("Druck-/Mail-Dialog"));
				this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(popUp_to_select_mail_or_print.this)
				{
					@Override
					public void executeAgentCode(ExecINFO eexecInfo)	throws myException
					{
						if (ACTIONAGENT_MAIL_AND_REPORT.this.ActionAfterPrintOrMail != null)
						{
							ACTIONAGENT_MAIL_AND_REPORT.this.ActionAfterPrintOrMail.executeAgentCode(eexecInfo);
						}
					}
				});

				
				if (ACTIONAGENT_MAIL_AND_REPORT.this.bExplicitSupressMail) {
					//dann hier den print-button direkt betaetigen
					popUp_to_select_mail_or_print.this.oButtonPrint.doActionPassiv();
				}
				
			}
			else
			{
				//gleich drucken
				//hier zuerst die globale validierung ausfuehren, da diese hier uebergangen wird
				bibMSG.add_MESSAGE(((E2_IF_Handles_ActionAgents)execInfo.get_MyActionEvent().getSource()).valid_GlobalValidation());

				if (bibMSG.get_bIsOK())
				{
					oThis.ownActionStart_Print=new ownActionStartPrint();
					oThis.ownActionStart_Print.ExecuteAgentCode(execInfo);
					
					//2011-03-22: weitere actionagents einschleussen-hier ausfuehren
					for (int i=0;i<oThis.vActionAgentsExecuteAfterPrint.size();i++)
					{
						oThis.vActionAgentsExecuteAfterPrint.get(i).executeAgentCode(execInfo);
					}

					
					if (ACTIONAGENT_MAIL_AND_REPORT.this.ActionAfterPrintOrMail != null)
					{
						ACTIONAGENT_MAIL_AND_REPORT.this.ActionAfterPrintOrMail.executeAgentCode(execInfo);
					}
				}
			}
			
		}
		
		

		private class ownActionStartPrint extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				
				popUp_to_select_mail_or_print.this.build_pdf_dokuments_and_mail_blocks(true,false);
			}
		}


			
		private class ownActionStartMail extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				popUp_to_select_mail_or_print.this.build_pdf_dokuments_and_mail_blocks(false,true);
			}
		}
	
		
		
		
		/**
		 * 
		 * @param StartDownload
		 * @param vMailBlock    wenn dies != null, dann wird gemailt
		 * @throws myException
		 */
		private void build_pdf_dokuments_and_mail_blocks(boolean StartDownload, boolean StartMail) throws myException
		{
			this.bStartDownload = 	StartDownload;
			this.bStartMail = 		StartMail;
			
			if ((this.bStartDownload && this.bStartMail) || (!this.bStartDownload && !this.bStartMail))
			{
				throw new myException(this,"Only one thing possible, startmail or startDownload");
			}
			if (this.bStartMail && !this.bMailIsPossible)
			{
				throw new myException(this,"Mail is not possible");
			}

			this.bPrintOrMail = StartDownload;
			
			this.VMailBlock = 		new MailBlock_Vector();
			
			if (this.VJasperHash.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine Dokumente erzeugt !")));
				return;
			}

			
			/*
			 * aenderung: 2010-10-19: wegen report-pipeline
			 */
			if (ACTIONAGENT_MAIL_AND_REPORT.this.bIsPreview)
			{
				this.VJasperHash.set_TYPE_PREVIEW();
			}
			else
			{
				if (this.bPrintOrMail)
				{
					this.VJasperHash.set_TYPE_PRINT();
				}
				else
				{
					this.VJasperHash.set_TYPE_MAIL();
				}
			}
			


			
			//wenn der mail-block mit uebergeben wird, dann zuerst die mailer-maske anzeigen (leer) damit das serverpush-fenster darueber liegt
			if (this.bStartMail)
			{
				//jetzt den mailer aufbauen
				this.MassMailer =  ACTIONAGENT_MAIL_AND_REPORT.this.get_MassMailer();
				this.MassMailer.CREATE_AND_SHOW_POPUPWINDOW(null,null,S.isEmpty(ACTIONAGENT_MAIL_AND_REPORT.this.cFensterTitel)?new MyE2_String("Mail ..."):ACTIONAGENT_MAIL_AND_REPORT.this.cFensterTitel);					
			}

			
			if (this.VJasperHash.size()>10)     //dann ein fortschrittspopup anzeigen lassen
			{
				new E2_ServerPushMessageContainer(new Extent(500),new Extent(200),new MyE2_String("Aufbereitung der Druckjobs ..."),true,true,false,1000)
				{
					@Override
					public void Run_Loop() throws myException
					{
						popUp_to_select_mail_or_print oThis = popUp_to_select_mail_or_print.this;
						
						this.get_oGridBaseForMessages().addAfterRemoveAll(new MyE2_Label(new MyE2_String("Datenbank-Vorbereitung für die Belege ...")));
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(oThis.VJasperHash.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS(),true));
						
			
						if (bibMSG.get_bIsOK())
						{
							//2012-02-13: die rueckgabe von oThis.VJasperHash.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS() wird ausgewertet
							if (oThis.VJasperHash.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(this, false, bPrintOrMail))
							{
								//2012-02-13: hier wird nochmals geprueft, ob alarm-messages vorhanden sind
								if (bibMSG.get_bIsOK())
								{
									if (oThis.bStartMail)
									{
										oThis.VMailBlock.addAll(oThis.VJasperHash.BUILD_AND_GET_V_MAIL_BLOCKS());
										
										//jetzt pruefen, ob alle MailBlocks in VMailBlocks ok sind
										if (oThis.VJasperHash.get_bAll_Have_MAILBLOCK_with_Minimum_One_MailAdresse())
										{
											oThis.MassMailer.baue_MailMaske(oThis.VMailBlock,bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),true,false,false,0,null);	
										}
										else
										{
											bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nicht alle Druckobjekte haben Mail-Informationen !!")));
										}
									}
									
									if (oThis.bStartDownload)
									{
										oThis.VJasperHash.DOWNLOAD_FILES(this.get_oGridBaseForMessages());
										
										if (bibMSG.get_bIsOK())
										{
											this.get_oGridBaseForMessages().addAfterRemoveAll(new MyE2_Label(new MyE2_String("..ich führe die Abschlussbefehle in der Datenbank aus")));
											bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(oThis.VJasperHash.get_vSQL_STACK_EXECUTE_AFTER_FROM_ALL_HASHMAPS(),true));
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
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(this.VJasperHash.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS(),true));

				if (bibMSG.get_bIsOK())
				{
					this.VJasperHash.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(null, false, bPrintOrMail);
					
					//2012-02-13: hier wird nochmals geprueft, ob alarm-messages vorhanden sind
					if (bibMSG.get_bIsOK())
					{
						if (this.bStartMail)
						{
							this.VMailBlock.addAll(this.VJasperHash.BUILD_AND_GET_V_MAIL_BLOCKS());
							//jetzt pruefen, ob alle MailBlocks in VMailBlocks ok sind
							if (this.VJasperHash.get_bAll_Have_MAILBLOCK_with_Minimum_One_MailAdresse())
							{
								this.MassMailer.baue_MailMaske(this.VMailBlock,bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),true,false,false,0,null);
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nicht alle Druckobjekte haben Mail-Informationen !!")));
							}
						}
						if (this.bStartDownload)
						{
							this.VJasperHash.DOWNLOAD_FILES(null);
							if (bibMSG.get_bIsOK())
							{
								bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(this.VJasperHash.get_vSQL_STACK_EXECUTE_AFTER_FROM_ALL_HASHMAPS(),true));
							}
						}
					}				
				}
			}
			
			popUp_to_select_mail_or_print.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			
		}
		
	}



	public XX_ActionAgent get_ownActionStart_PrintPopup()
	{
		return ownActionStart_PrintPopup;
	}


	public XX_ActionAgent get_ownActionStart_Print()
	{
		return ownActionStart_Print;
	}


	public XX_ActionAgent get_ownActionStart_Mail()
	{
		return ownActionStart_Mail;
	}

	public boolean get_bExplicitSupressMail() {
		return this.bExplicitSupressMail;
	}

	public void set_bExplicitSupressMail(boolean explicitSupressMail) {
		this.bExplicitSupressMail = explicitSupressMail;
	}

	public boolean is_suppress_window_close_button() {
		return b_suppress_window_close_button;
	}

	public void set_suppress_window_close_button(boolean b_suppress_window_close_button) {
		this.b_suppress_window_close_button = b_suppress_window_close_button;
	}


}
