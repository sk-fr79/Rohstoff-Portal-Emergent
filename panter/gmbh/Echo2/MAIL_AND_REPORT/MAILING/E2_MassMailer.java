package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.filetransfer.UploadEvent;
import nextapp.echo2.app.filetransfer.UploadListener;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_Caller;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_Free;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_UploadSelect;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_PopUpAllNoneInvert_CheckBoxes;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_WithSelektorEASY;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.ZIP.myZipper;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionInFileHandling;
import panter.gmbh.indep.maggie.TestingDate;
import panter.gmbh.indep.myVectors.VectorString;
import echopointng.Separator;


/**
 * Massenmailer, aus activeGrid abgeleitet
 */
public abstract class E2_MassMailer extends E2_BasicModuleContainer implements UploadListener
{

	/*
	 *  hier werden die mailziele übergeben (als objecte vom typ MailBlock)
	 */
	private MailBlock_Vector					vMailBlock		   = new MailBlock_Vector();
	
	
	/*
	 * Vector fuer objekte vom typ E2_FileWithSendName
	 */
	private Vector<FileWithSendName> 			vAnlagenObjekte = new Vector<FileWithSendName>();
	
	/*
	 * Grid für die angabe der Absenderadresse, Mailblock, Anzeige des uploadselectors usw
	 */
	private MyE2_TextField 						textAbsenderAdresse = new MyE2_TextField("",250,200);
	private MyE2_TextField_WithSelektorEASY 	textBetreffzeile = null;
	private MyE2_TextArea_WithSelektorEASY  	textMailtext = null;

	
	private E2_DateBrowser						oDateWiederVorlage = new E2_DateBrowser();
	private MyE2_CheckBox						oCBSchreibeWiederVorlage = new MyE2_CheckBox(new MyE2_String("Adress-Info"));


	/*
	 * Sendebutton
	 */
	private MyE2_Button 						buttonSendeMails = null;

	/*
	 * basis-Column fuer alle komponenten
	 */
	private MyE2_Grid 							oBaseGrid = new MyE2_Grid(5,0);
	
	/*
	 * hilfs-col fuer einzelne elemente
	 */
	private MyE2_Column 						oColAttachementInfo = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
	private MyE2_Column 						oColUploadComponent = new MyE2_Column();
	
	private boolean 							bWriteArchiv = true;
	private boolean 							bShowAdressInfoBlock = true;

	/*
	 * der namensteil fuer das beim mailen zu erzeugenden zip-archiv
	 */
	private String 								cNAMENSANTEIL_Fuer_Archiv = null;
	
	
	/*
	 * vector, sammelt die buttons zum einfuegen neue mailadresse und suche mailadresse aus adressstamm,
	 * um diese inaktiv schalten zu koennen
	 */
	private	Vector<MyE2IF__Component> 			vButtonsWithSpecialFunctions = new Vector<MyE2IF__Component> (); 
	
	
	/*
	 * komponenten fuer die buttonzeile am unteren rand
	 */
	private	Vector<MyE2IF__Component> 			vAddonComponents = new Vector<MyE2IF__Component>(); 
	
	
	/*
	 * hier koennen noch actionagents untergebracht werden, die ausgeloest werden, wenn das fenster geschlossen wird
	 */
	private Vector<XX_ActionAgent> 			  	vActionAgentsAfterWindowClose = new Vector<XX_ActionAgent>();
	
	/*
	 * sicherheitspolicy fuer das bearbeiten der mail-liste
	 */
	private MailSecurityPolicy                  oMailSecPolicy = null;
	
	
	/*
	 * containerEx fuer die liste der emails
	 */
	private MyE2_ContainerEx 	 				oContainerEx = new MyE2_ContainerEx();


	private ownExpandAttachments    			oExpandBlockAttachments = null;
	private ownExpandMailBlock    				oExpandBlockMailText = null;

	

	private  MailBlock_Vector 						vMailBlockToSend = null;
	
	/**
	 * 
	 * @param cMASSMailerTextSelectorKENNER_BETREFF
	 * @param cMASSMailerTextSelectorKENNER_MAILBLOCK
	 * @param cNAMENSANTEIL_FUER_ARCHIV
	 * @param mailSecurityPolicy
	 * @throws myException
	 * 
	 * Baut einen BasicModuleContainer fuer das versenden von massenmails,
	 * das pane ist bereits fuer popup vordefiniert
	 * 
	 */
	public E2_MassMailer(	String 				cMASSMailerTextSelectorKENNER_BETREFF,
							String 				cMASSMailerTextSelectorKENNER_MAILBLOCK,
							String 				cNAMENSANTEIL_FUER_ARCHIV,
							MailSecurityPolicy  mailSecurityPolicy
												) throws myException
	{
		super();
		
		this.cNAMENSANTEIL_Fuer_Archiv = cNAMENSANTEIL_FUER_ARCHIV;

		this.oMailSecPolicy = mailSecurityPolicy;
	 
		this.textBetreffzeile = new MyE2_TextField_WithSelektorEASY(cMASSMailerTextSelectorKENNER_BETREFF,500);

		this.textMailtext = new MyE2_TextArea_WithSelektorEASY(cMASSMailerTextSelectorKENNER_MAILBLOCK,500,8);
		this.textMailtext.set_ZusatzText(bibALL.get_EMAIL_SIGNATUR(),false);
		this.textMailtext.get_oTextArea().setFont(new E2_FontPlain(-2));

		this.buttonSendeMails = new MyE2_Button(new MyE2_String("Versende ausgewählte eMails"));
		this.buttonSendeMails.setFont(new E2_FontBold(2));
		this.buttonSendeMails.setLineWrap(true);
		this.buttonSendeMails.add_oActionAgent(new ownActionAgentStartMails());
		
		
		//this.oContainerEx.setWidth(new Extent(800));
		this.oContainerEx.setWidth(new Extent(99,Extent.PERCENT));
		this.oContainerEx.setHeight(new Extent(230));
		
		/*
		 * die basis-komponente zuladen
		 */
		this.oBaseGrid.setWidth(new Extent(99,Extent.PERCENT));
		this.add(this.oBaseGrid);
		
		this.add_CloseActions(new ownActionForWindowClose_ErrorSQLStatements());        // fuehrt sql-fehlerstatements aus
		this.add_CloseActions(new ownActionForWindowClose_DeleteTempFiles());       	// loescht hochgeladene temp-files
		this.add_CloseActions(new ownActionForWindowClose_DoOtherActionAgents());		// fuehrt sonderaktionen durch
		
	}





	/**
	 * @param vmailZiele
	 * @param cAbsenderAdresse
	 * @param writeArchiv
	 * @param showAdressInfoBlock
	 * @param preSetInfoBlockCheckBox
	 * @param diffDaysWiedervorlage
	 * @param vAddOnComponentsForButtonList
	 * @throws myException
	 */
	public void baue_MailMaske(	MailBlock_Vector			vmailZiele,
								String 						cAbsenderAdresse,
								boolean 					writeArchiv,
								boolean 					showAdressInfoBlock,
								boolean 					preSetInfoBlockCheckBox,
								int  						diffDaysWiedervorlage,
								Vector<MyE2IF__Component>	vAddOnComponentsForButtonList) throws myException
	{

		this.vMailBlock.removeAllElements();
		
		
		//jetzt nur solche mailBloecke erfassen (falls sie aus eine E2_JasperHash hervorgehen)
		//die nicht durch z.B. eine Pipeline komplett umgeleitet wurden
		for (int i=0;i<vmailZiele.size();i++)
		{

			MailBlock  oMB = vmailZiele.get(i);
			if (oMB.get_oJasperHash_this_comes_From()!=null)
			{
				if (oMB.get_oJasperHash_this_comes_From().get_oTempFileWithSendeName()!=null)
				{
					this.vMailBlock.add(oMB);
				}
			}
			else
			{
				//mailbloecke, die ohne jasperhash kommen, werden immer mitverarbeitet
				this.vMailBlock.add(oMB);
			}
		}

		this.textAbsenderAdresse.setText(cAbsenderAdresse);		

		this.bWriteArchiv = writeArchiv;
		this.bShowAdressInfoBlock = showAdressInfoBlock;

		if (vAddOnComponentsForButtonList != null)
		{
			this.vAddonComponents.removeAllElements();
			this.vAddonComponents.addAll(vAddOnComponentsForButtonList);
		}

		
		this.oExpandBlockAttachments = new ownExpandAttachments();
		this.oExpandBlockMailText = new ownExpandMailBlock();

		
		/*
		 * wiedervorlagedatum definieren
		 */
		if (diffDaysWiedervorlage<=0)
		{
			this.oDateWiederVorlage.get_oDatumsFeld().setText("");
		}
		else
		{
			GregorianCalendar oDateHelp =new GregorianCalendar();
			oDateHelp = myDateHelper.Add_Day_To_Calendar(diffDaysWiedervorlage,oDateHelp);
			myDateHelper.Move_Calendar_ToMondayIfWeekend(oDateHelp);
			myDateHelper oDateHelperWiederVorlage = new myDateHelper(oDateHelp);
			this.oDateWiederVorlage.get_oDatumsFeld().setText(oDateHelperWiederVorlage.get_cDateFormatForMask());
			this.oDateWiederVorlage.DO_EvaluateActualText();
			/*
			 * fertig
			 */
		}
		
		this.oCBSchreibeWiederVorlage.setSelected(preSetInfoBlockCheckBox);
		
		
		this.paint_MailMask();

	}
	
	
	

	/**
	 * 2014-05-19: weitere mail-mask-fueller, mit uebergebenem Betreff und Text
	 * @param vmailZiele
	 * @param cAbsenderAdresse
	 * @param writeArchiv
	 * @param showAdressInfoBlock
	 * @param preSetInfoBlockCheckBox
	 * @param diffDaysWiedervorlage
	 * @param vAddOnComponentsForButtonList
	 * @param cBetreff
	 * @param cMailText
	 * @throws myException
	 */
	public void baue_MailMaske(	MailBlock_Vector			vmailZiele,
								String 						cAbsenderAdresse,
								boolean 					writeArchiv,
								boolean 					showAdressInfoBlock,
								boolean 					preSetInfoBlockCheckBox,
								int  						diffDaysWiedervorlage,
								Vector<MyE2IF__Component>	vAddOnComponentsForButtonList,
								String 						cBetreff,
								String  					cMailText) throws myException
	{
		
		this.baue_MailMaske(vmailZiele, cAbsenderAdresse, writeArchiv, showAdressInfoBlock, preSetInfoBlockCheckBox, diffDaysWiedervorlage, vAddOnComponentsForButtonList);
		this.textBetreffzeile.get_oTextField().setText(cBetreff);
		this.textMailtext.get_oTextArea().setText(cMailText);
	}
	
	
	
	
	
	
	public void paint_MailMask() throws myException
	{
		
		this.vButtonsWithSpecialFunctions.removeAllElements();
		
		Extent extOldHeight = this.oContainerEx.getHeight();
		
		/*
		 * vector vCheckBoxes fuer den switcher all/none/invert
		 */
		Vector<MyE2_CheckBox> vCheckBoxes = this.vMailBlock.get_vCheckBoxes();
		
		MyE2_Grid oGridWithMailAdresses = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridWithMailAdresses.setWidth(new Extent(99,Extent.PERCENT));
		
		oGridWithMailAdresses.add(new E2_PopUpAllNoneInvert_CheckBoxes(vCheckBoxes, true));
		oGridWithMailAdresses.add(new TitelBlockMitButtons());

		//jetzt fuer jeden maileintrag das grid fuellen
		Insets oIN = new Insets(0,1,0,0);
		for (int i=0; i<this.vMailBlock.size();i++)
		{
			oGridWithMailAdresses.add(this.vMailBlock.get(i).get_MailSendGrid(),2, oIN);
		}
		
		Insets IN_Left = new Insets(32,2,5,2);
		
		/*
		 * beginn aufbau der Maske
		 */
		this.oBaseGrid.removeAll();
		
		this.oBaseGrid.add(this.oExpandBlockAttachments,5,E2_INSETS.I_5_5_5_5);                 //attachment-block
		
		
		// zeile 2
		E2_ComponentGroupHorizontal  oCompGroup = null;
		
		if (this.bShowAdressInfoBlock)
		{
			oCompGroup = new E2_ComponentGroupHorizontal(0,		new MyE2_Label(new MyE2_String("Absender :")),
																this.textAbsenderAdresse,
																(S.isFull(bibALL.get_cTestMailAdresse())?new MyE2_Label("DEMOZIEL: "+bibALL.get_cTestMailAdresse(),MyE2_Label.STYLE_ERROR_BIG()):new MyE2_Label("")),
																this.oCBSchreibeWiederVorlage,
																new MyE2_Label("WV:"),
																this.oDateWiederVorlage,E2_INSETS.I_0_0_10_0);
		}
		else
		{
			oCompGroup = new E2_ComponentGroupHorizontal(0,		new MyE2_Label(new MyE2_String("Absender :")),
																this.textAbsenderAdresse,
																(S.isFull(bibALL.get_cTestMailAdresse())?new MyE2_Label("DEMOZIEL: "+bibALL.get_cTestMailAdresse(),MyE2_Label.STYLE_ERROR_BIG()):new MyE2_Label("")),
																E2_INSETS.I_0_0_10_0);
		}
		this.oBaseGrid.add(oCompGroup,5,IN_Left);	
		
		this.oBaseGrid.add(new Separator(),5,E2_INSETS.I_5_5_5_5);
			
		this.oContainerEx.removeAll();
		this.oContainerEx.add(oGridWithMailAdresses);
		
		this.oBaseGrid.add(oContainerEx,5,E2_INSETS.I_5_2_5_2);                            // die verteiler/sende-Liste

		this.oBaseGrid.add(new Separator(),5,E2_INSETS.I_5_5_5_5);
		
		this.oBaseGrid.add(this.oExpandBlockMailText,5,E2_INSETS.I_5_5_5_5);                 //attachment-block
		
		this.refresh_AttachmentInfo();
		
		this.oContainerEx.setHeight(extOldHeight);
		
	}


	
	
	private class ownExpandAttachments extends E2_ExpandableRow
	{

		public ownExpandAttachments() throws myException
		{
			super(new MyE2_String("Anlagen zu den Mails"), new Border(new Extent(1),new E2_ColorDDDark(),Border.STYLE_SOLID), new Border(new Extent(1),new E2_ColorDDDark(),Border.STYLE_SOLID));
			
			E2_MassMailer oThis = E2_MassMailer.this;
			
			E2_UploadSelect oUpload = new E2_UploadSelect(File.separator + bibALL.get_WEBROOTPATH() + File.separator  + bibALL.get_TEMPPATH() + File.separator, false);
			oUpload.setUploadListener(oThis);
			
			StringBuffer cInfosZumUpload = new StringBuffer();
			Vector<String> vAllowedEndungen = E2_UploadSelect.get_vErlaubteEndungen(cInfosZumUpload);
			oUpload.set_vErlaubtEndungen(vAllowedEndungen);

			oThis.oColUploadComponent.addAfterRemoveAll(oUpload,new Insets(0));
			
			MyE2_Column oColHelp = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
			oColHelp.add(new MyE2_Label(new MyE2_String("Anlagen zu den Mails")), E2_INSETS.I_10_0_10_0);
			oColHelp.add(new E2_ComponentGroupHorizontal(0,	oThis.oColUploadComponent,
															new MyE2_Label(new MyE2_String("Anlagen:")),
															oThis.oColAttachementInfo,E2_INSETS.I_10_0_10_0));
			this.add(oColHelp);
			
			// actionAgents, damit beim ein/ausklappen der ContainerEx veraendert wird
			this.get_oButtonOpen().add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					E2_MassMailer.this.oContainerEx.setHeight(new Extent(E2_MassMailer.this.oContainerEx.getHeight().getValue()-53));
				}
			});

			this.get_oButtonClose().add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					E2_MassMailer.this.oContainerEx.setHeight(new Extent(E2_MassMailer.this.oContainerEx.getHeight().getValue()+53));
				}
			});

			this.get_oButtonClose().doActionPassiv();
		}
	}
	

	
	
	
	
	private class ownExpandMailBlock extends E2_ExpandableRow
	{

		public ownExpandMailBlock() throws myException
		{
			super(new MyE2_String("Betreff und Mail-Text"), new Border(new Extent(1),new E2_ColorDDDark(),Border.STYLE_SOLID), new Border(new Extent(1),new E2_ColorDDDark(),Border.STYLE_SOLID));
			
			E2_MassMailer oThis = E2_MassMailer.this;
			
			MyE2_Grid   oGridHelp = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			

			//Zeile 1
			oGridHelp.add(new MyE2_Label(new MyE2_String("Betreff :")),1,E2_INSETS.I_0_0_0_10);		
			oGridHelp.add(oThis.textBetreffzeile,1,E2_INSETS.I_5_2_5_2);
			oGridHelp.add(new MyE2_Label(""),1,E2_INSETS.I_5_2_5_2);
			//Zeile 1 --Ende
			
			//Zeile 2
			oGridHelp.add(new MyE2_Label(new MyE2_String("Mailinhalt :")),1,E2_INSETS.I_0_0_0_10);	
			oGridHelp.add(oThis.textMailtext,1,E2_INSETS.I_5_2_5_2);
			
			E2_ComponentGroupHorizontal oHelpGroup = new E2_ComponentGroupHorizontal(0,oThis.buttonSendeMails,E2_INSETS.I_10_5_0_0);
			for (int i=0;i<oThis.vAddonComponents.size();i++)
			{
				oHelpGroup.add((Component)oThis.vAddonComponents.get(i),E2_INSETS.I_10_5_0_0);
			}
			oGridHelp.add(oHelpGroup,1,1,E2_INSETS.I_0_0_0_10,new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
			//Zeile 2 --Ende
			
			
			this.add(oGridHelp);
			
			// actionAgents, damit beim ein/ausklappen der ContainerEx veraendert wird
			this.get_oButtonOpen().add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					E2_MassMailer.this.oContainerEx.setHeight(new Extent(E2_MassMailer.this.oContainerEx.getHeight().getValue()-90));
				}
			});

			this.get_oButtonClose().add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					E2_MassMailer.this.oContainerEx.setHeight(new Extent(E2_MassMailer.this.oContainerEx.getHeight().getValue()+90));
				}
			});

		}
		
	}

	
	/**
	 * @param oextWidth
	 * @param oextHeight
	 * @param oTitle
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(Extent				oextWidth,
											Extent				oextHeight,
											MyE2_String 		oTitle) throws myException
	{
		this.CREATE_AND_SHOW_POPUPWINDOW(oextWidth,oextHeight,false,null,oTitle);
	}
	
	

	
	/*
	 * (non-Javadoc)
	 * @ueberschreiben der methode, um den split-pane mit den email-adresse an das fenster anzupassen
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(		Extent 					oWidth, 
													Extent 					oHeight,
													boolean 				bSplit,
													Extent    				oSplitPosition,
													MyE2_String 			oTitle) throws myException
	{
		super.CREATE_AND_SHOW_POPUPWINDOW(oWidth,oHeight,bSplit,oSplitPosition,oTitle);
		
		Extent ownHeight = this.get_oWindowPane().getHeight();
		int iOwnHeight = ownHeight.getValue();
		int iHeightEmailBlock = iOwnHeight-420;
		this.oContainerEx.setHeight(new Extent(iHeightEmailBlock));
//		this.oContainerEx.setBackground(Color.WHITE);
		//this.oContainerEx.setHeight(new Extent(100,Extent.PERCENT));
		
	}
	
	
	

	/*
	 * nach dem sendevorgang darf nur noch geschaut werden
	 */
	private void set_MailerInactiv()
	{
		try
		{
			this.textAbsenderAdresse.set_bEnabled_For_Edit(false);
			this.textBetreffzeile.set_bEnabled_For_Edit(false);
			this.textMailtext.set_bEnabled_For_Edit(false);
			this.buttonSendeMails.set_bEnabled_For_Edit(false);
//			this.oUpload.setVisible(false);
			this.oColAttachementInfo.setEnabled(false);                 // dann geht runterladen auch nicht mehr
			
			this.vMailBlock.set_EnabledMailObjects(false);   //buttons und eingabefelder inaktiv
			
			for (int i=0;i<this.vButtonsWithSpecialFunctions.size();i++)
			{
				this.vButtonsWithSpecialFunctions.get(i).set_bEnabled_For_Edit(false);
			}
		}
		catch (myException ex)
		{
			
		}
	}
	
	
	
	

	public void fileUpload(UploadEvent e)
	{
		
		E2_UploadSelect oUploadSelect = (E2_UploadSelect) e.getSource();
		
		try
		{
			oUploadSelect.doSaveFile(e,bibALL.get_SESSIONID(null) + "_",true);

			this.vAnlagenObjekte.add(	new FileWithSendName(
												oUploadSelect.get_cUploadPfad()+oUploadSelect.get_cStoringFileNameWithEndung_NoPath(),
												oUploadSelect.get_cUploadFileNameWithEndung_NoPath(),
												new JasperFileDef_Free(oUploadSelect.get_cEndung(),"application/"+oUploadSelect.get_cEndung(),new MyE2_String("Undefinierte Datei"))));

			this.refresh_AttachmentInfo();
		}
		catch (myExceptionInFileHandling ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ex.get_oMessage().CTrans(),false)));
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Hochladen der Datei:"));
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		this.showActualMessages();
	}





	private void refresh_AttachmentInfo()
	{
		MutableStyle oStyleInfoLabel = new MutableStyle();
		oStyleInfoLabel.setProperty(Label.PROPERTY_FONT,new E2_FontBold(2));
		oStyleInfoLabel.setProperty(Label.PROPERTY_FOREGROUND,Color.RED);

		MyE2_Column oColInner = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		
		this.oColAttachementInfo.removeAll();
		this.oColAttachementInfo.add(oColInner);
		
		if (this.vAnlagenObjekte.size() == 0)
		{
			oColInner.add(new MyE2_Label(new MyE2_String("KEINE"),oStyleInfoLabel));
			this.oColAttachementInfo.setBorder(new Border(new Extent(1),new E2_ColorDDark(),Border.STYLE_SOLID));
		}
		else
		{
			for (int i = 0; i < this.vAnlagenObjekte.size(); i++)
			{
				oColInner.add(new buttonAttachment((FileWithSendName)this.vAnlagenObjekte.get(i)));
			}
			this.oColAttachementInfo.setBorder(new Border(new Extent(1),new E2_ColorAlarm(),Border.STYLE_SOLID));
		}
	}


	
	private class buttonAttachment extends MyE2_Button
	{
		private FileWithSendName oFile = null; 
		public buttonAttachment(FileWithSendName ofile) 
		{
			super(ofile.get_cNameFor_USER_EmailAttachment());
			this.oFile = ofile;
			this.setBorder(new Border(new Extent(1),new E2_ColorBase(),Border.STYLE_SOLID));
			this.setBackground(new E2_ColorBase());
			this.setFont(new E2_FontItalic(-2));
			
			this.add_oActionAgent( new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) 
				{
					try
					{
						buttonAttachment.this.oFile.Download_File();
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
					}
				}
			});
			
			
		}
	}
	




	public void invalidFileUpload(UploadEvent e)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Hochladen der Datei:"));
		//oMV.add_MESSAGE(new MyE2_Alarm_Message(this.oUpload.get_cUploadFileNameWithEndung_NoPath(),false));

		bibMSG.add_MESSAGE(oMV);
		this.showActualMessages();
	}

	
	
	
	
	public void ADD_FileTo_Send(String cRealFileName, String cSendName) throws myException
	{
		if (bibALL.isEmpty(cRealFileName) || bibALL.isEmpty(cSendName))
			throw new myException("E2_ModuleContainer_PopUP_MassMailer:ADD_FileTo_Send:both filenames MUST NOT be empty");
		
	}
	

	

	/**
	 * @param oFilewithSendName
	 * Adds a mail-Attachment-object to a mail
	 */
	public void add_AnlagenObjekte(FileWithSendName oFilewithSendName) 
	{
		this.vAnlagenObjekte.add(oFilewithSendName);
	}
	
	
	
	

	public MailBlock_Vector get_MailBlockVector() 
	{
		return vMailBlock;
	}


	
	
	
	private class ownActionAgentStartMails extends XX_ActionAgent
	{
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_MassMailer oThis = E2_MassMailer.this;
			/*
			 * zuerst pruefen, ob ein Asender/ Alias / ein Text vorhanden ist 
			 */
			if (	oThis.textAbsenderAdresse.getText().trim().equals("") || 
					oThis.textBetreffzeile.get_oTextField().getText().trim().equals("") || 
					oThis.textMailtext.get_oTextArea().getText().trim().equals(""))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es müssen immer vorhanden sein: Absender, Betreff und Mailtext !!!"));
				return;
			}

			
			// wiedervorlage auf korrektes datum pruefen
			String cDatum= oThis.oDateWiederVorlage.get_oDatumsFeld().getText().trim();
			if (oThis.oCBSchreibeWiederVorlage.isSelected()  && ! bibALL.isEmpty(cDatum.trim()))
			{
				TestingDate oTD = new TestingDate(cDatum);
				if (!oTD.testing())
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte korrektes Wiedervorlagedatum eintragen !!!"));
					return;
				}
			}
			
			/*
			 * noetige Vectoren sammeln
			 */
			oThis.vMailBlockToSend = oThis.vMailBlock.get_V_MailBlock_WithActivCheckBox(true, true);

			if (oThis.vMailBlockToSend.size() == 0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler! Keine ausgewählten Zieladressen vorhanden !"));
			}
			else
			{
				if (oThis.vMailBlockToSend.size()<10)
				{
					oThis.starte_senden(oThis.vMailBlockToSend,null);
				}
				else
				{
					new E2_ServerPushMessageContainer(new Extent(500),new Extent(220),new MyE2_String("Versenden der Mails läuft ..."),true,true,true,1000)
					{
						@Override
						public void Run_Loop() throws myException
						{
							E2_MassMailer 				ooThis = E2_MassMailer.this;
							ooThis.starte_senden(ooThis.vMailBlockToSend, this.get_oGridBaseForMessages());
						}
						
						@Override
						public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
						{
						}

					};
				}
			}
		}
	}

	
	
	private void starte_senden(Vector<MailBlock> v_MailBlock,MyE2_Grid oGridForServerPushMessage) throws myException
	{
		
		// jetzt das mailing starten
		String 		cDateHelp = myDateHelper.FormatDateTimeKurz(new GregorianCalendar().getTime());
		cDateHelp = cDateHelp.substring(6, 8) + cDateHelp.substring(3, 5) + cDateHelp.substring(0, 2) + "__" + cDateHelp.substring(9);
		
		myZipper 		oZipper = 			null;
		FileWriter 		SendeLog = 			null;

		//variable fuer das zaehlen, wieviele geklappt haben in einer mail-sende-aktion
		int 								iGood = 			0;
		int 								iBad = 				0;

		
		
		try
		{
			String cSendeLogName = Archiver.CreateArchiver_Name_ohneEndung(this.cNAMENSANTEIL_Fuer_Archiv,"SERIE");
			cSendeLogName = bibALL.get_CompleteProtokollPath(true) + cSendeLogName;
			
			if (this.bWriteArchiv)
			{
				SendeLog = new FileWriter(cSendeLogName + ".txt", true);
				
				SendeLog.write("----------------BETREFF ----------------------\n");
				SendeLog.write(""+this.textBetreffzeile.get_oTextField().getText()+"\n");
				SendeLog.write("----------------END-BETREFF ----------------------\n");
				SendeLog.write("----------------MAIL-TEXT----------------------\n");
				SendeLog.write(""+this.textMailtext.get_oTextArea().getText()+"\n");
				SendeLog.write("----------------END-MAIL-TEXT----------------------\n\n");
				
				oZipper = new myZipper(new FileOutputStream(cSendeLogName + ".zip"));
			}


			for (int i = 0; i < v_MailBlock.size(); i++)
			{
				int[] iGoodBad = v_MailBlock.get(i).SendeMails(	this.vAnlagenObjekte, 
																this.textAbsenderAdresse.getText().trim(), 
																this.textBetreffzeile.get_oTextField().getText(), 
																this.textMailtext.get_oTextArea().getText(), 
																this.oCBSchreibeWiederVorlage.isSelected(), 
																this.oDateWiederVorlage.get_oDatumsFeld().getText().trim(), 
																SendeLog,
																oGridForServerPushMessage);
				
				iGood+= iGoodBad[0];
				iBad+=  iGoodBad[1];
				
				/*
				 * serverpush pruefen und auf abbruch reagieren
				 */
				if (oGridForServerPushMessage!=null && E2_ServerPushMessageContainer.get_LoopStopped())
				{
					break;
				}

			}

			
			if (this.bWriteArchiv && SendeLog != null && oZipper !=null)
			{
				// am schluss noch das sendelog anhängen
				// und die zipOut-datei schliessen
				// jetzt das pdf ins zip-archiv hängen
				SendeLog.close();
				oZipper.addFileToZip("sendelog.txt",new File(cSendeLogName + ".txt"));
				
				
				// jetzt die anlagen ins zip-archiv hängen (die anlagen fuer alle zielobjekte)
				for (int k = 0; k < this.vAnlagenObjekte.size(); k++)
				{
					FileWithSendName oFile = (FileWithSendName)this.vAnlagenObjekte.get(k);
					
					String cNameImZip = oFile.get_cNameFor_USER_EmailAttachment();
					cNameImZip = oZipper.get_NotDoubleFileName(cNameImZip);
					oZipper.addFileToZip(cNameImZip,oFile);
				}
				
				// nun die spezifischen anlagen
				for (int i = 0; i < v_MailBlock.size(); i++)
				{
					if (!v_MailBlock.get(i).get_bWasSendEvenOneTimes())
						continue;
					
					for (int k=0;k<v_MailBlock.get(i).get_vOwnFiles().size();k++)
					{
						FileWithSendName oFile = (FileWithSendName)v_MailBlock.get(i).get_vOwnFiles().get(k);
						
						String cNameImZip = oFile.get_cNameFor_USER_EmailAttachment();
						cNameImZip = oZipper.get_NotDoubleFileName(cNameImZip);
						oZipper.addFileToZip(cNameImZip,oFile);
					}
				}
				
			}

			
			// jetzt evtl. vorhandene sql-statements sammeln und ausfuehren 
			Vector<String> vSQL = new Vector<String>();
			
			//weiterer Vector aus den JasperExecCaller
			VectorString vSQL_ex_Jasper_Exe_Caller = new  VectorString();
			
			for (int i = 0; i < v_MailBlock.size(); i++)
			{
				MailBlock oMailBlock = (MailBlock)v_MailBlock.get(i);
				if (oMailBlock.get_bWasSendEvenOneTimes()) {

					//zuerst die sql-statements aus der JasperHash
					if (oMailBlock.get_oJasperHash_this_comes_From()!=null && oMailBlock.get_oJasperHash_this_comes_From().get_vSQL_STATEMENTS_AFTER_REPORT()!=null)
					{
						vSQL.addAll(oMailBlock.get_oJasperHash_this_comes_From().get_vSQL_STATEMENTS_AFTER_REPORT());
					}
					
					vSQL.addAll(oMailBlock.get_vSQLAfterSending());
				}
				
				//2014-10-23: Jasper_Executer ausfuehren
				if (oMailBlock.get_oJasperHash_this_comes_From()!=null) {
					new Jasper_Exe_Caller().ExecuterStart(	oMailBlock.get_oJasperHash_this_comes_From(), 
															oMailBlock, 
															Jasper_Exe_CONST.EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_PRECESSING_MAILBLOCK_IN_MASSMAILER_SEND_METHOD,
															null,
															vSQL_ex_Jasper_Exe_Caller);
				}

			}

			if (vSQL.size()>0 || vSQL_ex_Jasper_Exe_Caller.size()>0)
			{
				Vector<String> vSQL2 = new Vector<String>();
				vSQL2.addAll(vSQL);
				vSQL2.addAll(vSQL_ex_Jasper_Exe_Caller);
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL2,true));
				DEBUG.System_print(vSQL2);
			}
			// -------------------------------------------------------------------------------------------
			
			
			
			File delFile = new File(cSendeLogName + ".txt");
			delFile.delete();
			
			MyE2_String cMessageGood = new MyE2_String("Mail verschickt: ");
			MyE2_String cMessageBad = new MyE2_String("Mail fehlerhaft: ");
			
			if (iBad>0) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cMessageGood.CTrans()+iGood+"     //   "+cMessageBad.CTrans()+iBad,false)));
			} 	else {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(cMessageGood.CTrans()+iGood+"     //   "+cMessageBad.CTrans()+iBad,false)));
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();    //NEU_09
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("E2_MassMailer:ownActionAgentStartMails:Error sending mails",false)));
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(e.getLocalizedMessage(),false)));
		}

		// alle evtl. offenen dateien schliessen
		try
		{
			if (SendeLog != null)	SendeLog.close();
		}
		catch (Exception e)	{}

		
		try
		{
			if (oZipper != null)	oZipper.close();
		}
		catch (Exception e)	{}
		
		oZipper = null;
		
		if (iBad ==0 )  {   // falls fehler auftauchen, kann nochmal ein Teil gestartet werden
			this.set_MailerInactiv();
		}
		
	}





	private class TitelBlockMitButtons extends MyE2_Row
	{

		public TitelBlockMitButtons() throws myException
		{
			super(MyE2_Row.STYLE_NO_BORDER());
			
			E2_MassMailer oThis = E2_MassMailer.this;
			
			this.add(new MyE2_Label(new MyE2_String("Mailadresse"),MyE2_Label.STYLE_TITEL_NORMAL()),E2_INSETS.I_0_0_10_0);
			if (oThis.oMailSecPolicy.bAllowNewEmptyAdress)
			{
				A__Button_ADD_NEW_MailBlock oButt = new A__Button_ADD_NEW_MailBlock(oThis);
				this.add(oButt,E2_INSETS.I_0_0_5_0);
				oThis.vButtonsWithSpecialFunctions.add(oButt);
			}
			if (oThis.oMailSecPolicy.bAllowAddMailFromAdressSeach)
			{
				A__Button_SEARCH_MailBlock oButt = new A__Button_SEARCH_MailBlock(oThis);
				this.add(oButt,E2_INSETS.I_0_0_5_0);
				oThis.vButtonsWithSpecialFunctions.add(oButt);
			}
			if (oThis.oMailSecPolicy.bAllowAddMailFromEployesPopup)
			{
				A__PopupMenue_MailBlock_OwnCompany oPopUp = new A__PopupMenue_MailBlock_OwnCompany(oThis);
				this.add(oPopUp,E2_INSETS.I_0_0_5_0);
				oThis.vButtonsWithSpecialFunctions.add(oPopUp);
			}
		
		}
		
		
	}



	public Vector<XX_ActionAgent> get_vActionAgentsAfterWindowClose()
	{
		return vActionAgentsAfterWindowClose;
	}


	public MailSecurityPolicy  get_oMailSecurityPolicy()
	{
		return this.oMailSecPolicy;
	}


	/*
	 * action-agent beim schliessen, falls mails nicht versendet werden,
	 * warum auch immer, werden die vSQL_STATEMENTS_AFTER_REPORT_ERROR()-Statements ausgefuehrt
	 */
	private class ownActionForWindowClose_ErrorSQLStatements extends XX_ActionAgentWhenCloseWindow
	{
		public ownActionForWindowClose_ErrorSQLStatements()
		{
			super(E2_MassMailer.this);
		}

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_MassMailer oThis = E2_MassMailer.this;
			
			MailBlock_Vector  vMailBlockToCheck = oThis.vMailBlock;

			Vector<String> vSQL_After_NotSended = new Vector<String>();
			if (vMailBlockToCheck != null)
			{
				for (int i = 0; i < vMailBlockToCheck.size(); i++)
				{
					MailBlock oMailEintrag = vMailBlockToCheck.get(i);
					if ((!oMailEintrag.get_bWasSendEvenOneTimes()) && oMailEintrag.get_oJasperHash_this_comes_From() != null)
					{
						vSQL_After_NotSended.addAll(oMailEintrag.get_oJasperHash_this_comes_From().get_vSQL_STATEMENTS_AFTER_REPORT_ERROR());
					}
				}
			}
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_After_NotSended,true));
		}
		
	}
	
	
	/*
	 * action-agent beim schliessen, falls mails nicht versendet werden,
	 * warum auch immer, werden die vSQL_STATEMENTS_AFTER_REPORT_ERROR()-Statements ausgefuehrt
	 */
	private class ownActionForWindowClose_DeleteTempFiles extends XX_ActionAgentWhenCloseWindow
	{
		public ownActionForWindowClose_DeleteTempFiles()
		{
			super(E2_MassMailer.this);
		}

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			// nach dem senden wieder alle upload-dateien löschen
			// und die listen löschen
			for (int k = 0; k < E2_MassMailer.this.vAnlagenObjekte.size(); k++)
			{
				FileWithSendName oFile = (FileWithSendName)E2_MassMailer.this.vAnlagenObjekte.get(k);
				oFile.delete();
			}
			E2_MassMailer.this.vAnlagenObjekte.removeAllElements();
			
		}
		
	}
	
	
	
	/*
	 * action-agent beim schliessen, falls mails nicht versendet werden,
	 * warum auch immer, werden die vSQL_STATEMENTS_AFTER_REPORT_ERROR()-Statements ausgefuehrt
	 */
	private class ownActionForWindowClose_DoOtherActionAgents extends XX_ActionAgentWhenCloseWindow
	{
		public ownActionForWindowClose_DoOtherActionAgents()
		{
			super(E2_MassMailer.this);
		}

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
				
			for (int i=0;i<E2_MassMailer.this.vActionAgentsAfterWindowClose.size();i++)
			{
				((XX_ActionAgent)E2_MassMailer.this.vActionAgentsAfterWindowClose.get(i)).ExecuteAgentCode(new ExecINFO_OnlyCode());
			}
		}
		
	}
	

	public MyE2_ContainerEx get_oContainerEx() 
	{
		return oContainerEx;
	}

	
	
	/**
	 * 
	 * @return mailBlock fuer hinzugefuegte leere mail-adress-bloecke (nur noetig, wenn dies erlaubt ist)
	 * @throws myException
	 */
	public abstract MailBlock build_MailBlock4Added_EmptyMails() throws myException;

	
	/**
	 * 
	 * @return mailBlock fuer hinzugefuegte mail-adress-bloecke aus der suchfunktion (nur noetig, wenn dies erlaubt ist)
	 * @throws myException
	 */
	public abstract MailBlock build_MailBlock4Added_SearchedMails() throws myException;

	/**
	 * 
	 * @return mailBlock fuer hinzugefuegte mail-adress-bloecke aus der Mitarbeiter-Mail-Popup-Selektion (nur noetig, wenn dies erlaubt ist)
	 * @throws myException
	 */
	public abstract MailBlock build_MailBlock4Added_MitarbeiterMails() throws myException;
	
}