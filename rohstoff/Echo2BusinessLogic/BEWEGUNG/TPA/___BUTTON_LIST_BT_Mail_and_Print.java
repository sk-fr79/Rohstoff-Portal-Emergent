package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.HashMap;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing_but_EditAdress;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_HelpButtonWithHelpWindow;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_Decision_CheckKreditVersicherung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN.__FU_Validator_FuhreErlaubt;

public class ___BUTTON_LIST_BT_Mail_and_Print extends MyE2_Button implements DS_IF_components4decision
{

	private ___Sammler_ID_VPOS_TPA_FUHRE 	oID_Sammler = 			null;
	private Vector<String>  				vID_VPOS_TPA_FUHRE = 	new Vector<String>();
	
	private String   						cMODULNAME = null;
	
	
	
	public ___BUTTON_LIST_BT_Mail_and_Print(___Sammler_ID_VPOS_TPA_FUHRE ID_Sammler,String  MODULNAME)
	{
		super(E2_ResourceIcon.get_RI("print_and_mail.png"),true);
		
		this.cMODULNAME = MODULNAME;
		
		this.oID_Sammler = ID_Sammler;
		
		//2014-11-12: neuer validierer einfuegen
		//this.add_GlobalValidator(new ownValidKreditStatus());
		
		
		this.add_IDValidator(new BST___Valid_TPA_KOPF_POS_FUHRE_NOT_DELETED_NOT_STORNIERT_IST_KOMPLETT());
		this.add_IDValidator(new BST___Valid_BelegDruck_Acconto_Kunden());
		
		//2014-01-29: validator, der je nach status alte oder neue steuerdefinition die korrekte einstellung der fuhre prueft
		this.add_IDValidator(new __FU_Validator_FuhreErlaubt());
		//------------------------------------------------------------------------

		
//		//2015-09-21: pruefen, ob eine adresse eine korrekte ust-id hat fuer die fuhre
//		this.add_IDValidator(new BST___Valid_Korrekte_Ust_ID());
		
//		//test-messages und agents
//		this.add_oActionAgent(new XX_ActionAgent() {
//			
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("La li lu"));
//			}
//		});
		
		//2016-01-29: pruefung der datumsbereiche
		this.add_oActionAgent(new ____DA_Decision_DatumsDifferenz(this,this.oID_Sammler));
		
		//2016-02-23: und die neue EU-Vertrag-Pruefung
		this.add_oActionAgent(new ____DA_DecisionHat_EU_VERTRAG(this,this.oID_Sammler));
		
		//2016-03-07: die kreditpruefung auch als decision
		this.add_oActionAgent(new own_Decision_CheckKredit(this));
		
		//2016-04-06: weitere desicion: bordercrossing
		this.add_oActionAgent(new ____DA_Decision_BorderCrossing(this,this.oID_Sammler));
		
		this.add_oActionAgent(new ownActionStart_PrintPopup());
		
		this.setToolTipText(new MyE2_String("Drucken / Mailen der Belege Lieferschein/Ladeschein/Abholavis/Lieferavis und CMR").CTrans());
		
		
//		this.add_GlobalValidator(new XX_ActionValidator() {
//			
//			@Override
//			protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//			
//			@Override
//			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
//				return new MyE2_MessageVector(new MyE2_Alarm_Message("Nur der Mann im Mond schaut zu"));
//			}
//		});
		
	}
	

		
	private class ownActionStart_PrintPopup extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			___BUTTON_LIST_BT_Mail_and_Print oThis = ___BUTTON_LIST_BT_Mail_and_Print.this;
			
			Vector<String> vIDs = oID_Sammler.get_vID_VPOS_TPA_FUHRE();
			
			if (bibMSG.get_bHasAlarms())            //im sammler kann einiges passieren
			{
				return;
			}
			
			oThis.vID_VPOS_TPA_FUHRE.removeAllElements();
			oThis.vID_VPOS_TPA_FUHRE.addAll(vIDs);
			
			if (oThis.vID_VPOS_TPA_FUHRE.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Zum Drucken bitte mindestens eine Position auswählen !"));
				return;
			}
					
			bibMSG.add_MESSAGE(oThis.valid_IDValidation(oThis.vID_VPOS_TPA_FUHRE));
			
			if (bibMSG.get_bIsOK())
			{
				new popUp_to_selectWhatToPrint();
			}
		}
	}
	

	
	
	
	private class popUp_to_selectWhatToPrint extends E2_BasicModuleContainer
	{
		private MyE2_CheckBox  oCB_lief = 			new MyE2_CheckBox(new MyE2_String("Lieferschein"));
		private MyE2_CheckBox  oCB_Lade = 			new MyE2_CheckBox(new MyE2_String("Ladeschein"));
		private MyE2_CheckBox  oCB_Abholavis = 		new MyE2_CheckBox(new MyE2_String("Abholavis"));
		private MyE2_CheckBox  oCB_Lieferavis =	 	new MyE2_CheckBox(new MyE2_String("Lieferavis"));
		private MyE2_CheckBox  oCB_Abholnachricht = new MyE2_CheckBox(new MyE2_String("Abholnachricht"));
		private MyE2_CheckBox  oCB_CMR = 			new MyE2_CheckBox(new MyE2_String("CMR"));
		private MyE2_CheckBox  oCB_EU_BLAETTER = 	new MyE2_CheckBox(new MyE2_String("EU-Blätter"));
		
		private MyE2_Button    oButtonVorschau = 	new MyE2_Button(new MyE2_String("Vorschau"));
		private MyE2_Button    oButtonDrucke = 		new MyE2_Button(new MyE2_String("Druck"));
		private MyE2_Button    oButtonMail = 		new MyE2_Button(new MyE2_String("eMail"));
		
		//2014-09-08: sammellieferschein
		private MyE2_CheckBox  oCB_SAMMEL_LIEFERSCHEIN = new MyE2_CheckBox(new MyE2_String("Sammel-Lieferschein"));
		
		private V_JasperHASH 	vJasperHashes = 	new V_JasperHASH();
		private boolean 		bPreview = 			false;
		
		//variable werden hier gebraucht fuer die serverpush-meldungen, die in einem eigene thread laufen
		private boolean         	bStartDownload = 	false;
		private MailBlock_Vector 	VMailBlock   = 		null;
		private E2_MassMailer 		oMassmailer = 		null;

		private boolean     		bPrintOrMail = true;
		

		public popUp_to_selectWhatToPrint() throws myException
		{
			super();
			
			//2014-09-08: Sammellieferschein
			boolean bEnabledSammelschein = new ___BUTTON_LIST_BT_Mail_and_Print_ValidSammellieferscheinAllowed(
					___BUTTON_LIST_BT_Mail_and_Print.this.oID_Sammler).get_bSammelLieferscheinOK();
			
			this.oCB_SAMMEL_LIEFERSCHEIN.setSelected(false);
			this.oCB_SAMMEL_LIEFERSCHEIN.EXT().set_oE2_Style(MyE2_CheckBox.STYLE_NORMAL_WITH_GREY_DISABLE());
			this.oCB_SAMMEL_LIEFERSCHEIN.set_bEnabled_For_Edit(bEnabledSammelschein);
			
			//der sammelschein bekommt in seinen wrapper einen hilfebutton
			MyE2_HelpButtonWithHelpWindow oHelpButton = new MyE2_HelpButtonWithHelpWindow() {
				@Override
				public void fillInternalColumn(MyE2_Column oColumn) throws myException {
					oColumn.removeAll();
					oColumn.add(new MyE2_Label(new MyE2_String("Mehrfach-Lieferschein ist nur möglich, wenn alle  "
															+ "selektierten Fuhren bei der gleichen Abladestation enden,"
															+ "das gleiche LKW-Kennzeichen und den gleichen Status "
															+ "<Ist ohne Abrechnung, JA/NEIN> haben !"),true));
					this.set_extWidth(new Extent(350));
					this.set_extHeight(new Extent(180));
				}
				@Override
				public E2_BasicModuleContainer createOwnBasicModuleContainer() throws myException {
					return new E2_BasicModuleContainer();
				}
			};
			this.oCB_SAMMEL_LIEFERSCHEIN.get_vADDONS_IN_WRAP().add(oHelpButton);

			
			this.oButtonDrucke.add_GlobalAUTHValidator_AUTO("DRUCKEN_TPA_BELEGE");
			this.oButtonVorschau.add_GlobalAUTHValidator_AUTO("VORSCHAU_TPA_BELEGE");
			this.oButtonMail.add_GlobalAUTHValidator_AUTO("MAIL_TPA_BELEGE");
			
			this.oButtonDrucke.add_oActionAgent(new ownActionStartPrint(false));
			this.oButtonVorschau.add_oActionAgent(new ownActionStartPrint(true));
			this.oButtonMail.add_oActionAgent(new ownActionStartMail());

			MyE2_Grid  oGridBase = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

			MyE2_CheckBox cbToggle = new MyE2_CheckBox(new MyE2_String("an/aus"));
			cbToggle.setFont(new E2_FontBoldItalic());
			
			cbToggle.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) 	throws myException
				{
					boolean bAn = ((MyE2_CheckBox)execInfo.get_MyActionEvent().getSource()).isSelected();
					popUp_to_selectWhatToPrint oThis = popUp_to_selectWhatToPrint.this;
					oThis.oCB_Abholavis.setSelected(bAn);
					oThis.oCB_lief.setSelected(bAn);
					oThis.oCB_Lieferavis.setSelected(bAn);
					oThis.oCB_Abholnachricht.setSelected(bAn);
					oThis.oCB_Lade.setSelected(bAn);
					oThis.oCB_CMR.setSelected(bAn);
					oThis.oCB_EU_BLAETTER.setSelected(bAn);
				}
			});
			
			
			
			//zeile 1
			oGridBase.add(new MyE2_Label(new MyE2_String("Druckauswahl für Transportpapiere ..."), MyE2_Label.STYLE_TITEL_BIG()),3,E2_INSETS.I_5_5_5_5);
			
			oGridBase.add(new Separator(),3, E2_INSETS.I_5_10_5_10);
			
			//zeile 2
			oGridBase.add(cbToggle,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(new MyE2_Label(""),2,E2_INSETS.I_0_0_0_0); 
			
			//zeile 3
			oGridBase.add(this.oCB_Lade,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(this.oCB_Abholavis,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(this.oCB_EU_BLAETTER,E2_INSETS.I_0_0_0_0); 

			//zeile4
			oGridBase.add(this.oCB_lief,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(this.oCB_Lieferavis,1,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(this.oCB_Abholnachricht,1,E2_INSETS.I_0_0_0_0); 

			
			//zeile5
			//2014-09-08 sammellieferschein
			
			oGridBase.add(this.oCB_SAMMEL_LIEFERSCHEIN.C_ME(),2,E2_INSETS.I_5_5_5_5);
			oGridBase.add(this.oCB_CMR,E2_INSETS.I_5_5_5_5); 
//			oGridBase.add(new MyE2_Label(""),2,E2_INSETS.I_0_0_0_0);  
			
			oGridBase.add(new Separator(),3, E2_INSETS.I_5_5_5_5);

			//zeile 6
			oGridBase.add(this.oButtonVorschau,1, E2_INSETS.I_5_5_5_5);
			oGridBase.add(this.oButtonDrucke,1, E2_INSETS.I_5_5_5_5);
			oGridBase.add(this.oButtonMail,1, E2_INSETS.I_5_5_5_5);
			
			this.add(oGridBase, E2_INSETS.I_5_5_5_5);
			
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(400), new MyE2_String("Belege auswählen ..."));
			
			this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(popUp_to_selectWhatToPrint.this)
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					___BUTTON_LIST_BT_Mail_and_Print.this.oID_Sammler.rebuild_Navilist_if_Needed();
				}
			});
			
		}
		
		
		private void fill_V_JasperHash(boolean bMail_Is_Yes_In_PrintProtokoll) throws myException
		{
			
			___BUTTON_LIST_BT_Mail_and_Print oThis = ___BUTTON_LIST_BT_Mail_and_Print.this;
			
			this.vJasperHashes.removeAllElements();
			
			for (int i=0;i<oThis.vID_VPOS_TPA_FUHRE.size();i++)
			{
				RECORD_VPOS_TPA_FUHRE  recFUHRE = new RECORD_VPOS_TPA_FUHRE(oThis.vID_VPOS_TPA_FUHRE.get(i));
				
				if (this.oCB_lief.isSelected())
				{
					vJasperHashes.add(new BST___JasperHashLIEFERSCHEIN(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
				}
				if (this.oCB_Lade.isSelected())
				{
					vJasperHashes.add(new BST___JasperHashLADESCHEIN(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
				}
				if (this.oCB_Abholavis.isSelected())
				{
					vJasperHashes.add(new BST___JasperHashABHOLAVIS(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
				}
				if (this.oCB_Lieferavis.isSelected())
				{
					vJasperHashes.add(new BST___JasperHashLIEFERAVIS(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
				}
				if (this.oCB_Abholnachricht.isSelected() && recFUHRE.get_ID_ADRESSE_START_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
				{
					// !!!!! ABHOLNACHRICHT wird nur bei Abholen von eigenen Lagern erzeugt !!!!!!!!! 
					vJasperHashes.add(new BST___JasperHashABHOLNACHRICHT(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
				}
				if (this.oCB_CMR.isSelected())
				{
					vJasperHashes.add(new BST___JasperHashCMR(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
				}
				if (this.oCB_EU_BLAETTER.isSelected())
				{
					vJasperHashes.add(new BST___JasperHash_NUR_EU_BLAETTER(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
				}

				//jetzt nachsehen, ob es noch fuhrenorte gibt
				RECLIST_VPOS_TPA_FUHRE_ORT  reclistOrte = recFUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
				
				if (reclistOrte.get_vKeyValues().size()>0)
				{
					for (int m=0;m<reclistOrte.get_vKeyValues().size();m++)
					{
						RECORD_VPOS_TPA_FUHRE_ORT recORT = reclistOrte.get(m);
						
						if (recORT.is_DELETED_NO())
						{
							if (recORT.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
							{
								if (this.oCB_Lade.isSelected())
								{
									vJasperHashes.add(new BST___JasperHashLADESCHEIN(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
								}
								if (this.oCB_Abholavis.isSelected())
								{
									vJasperHashes.add(new BST___JasperHashABHOLAVIS(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
								}
							}
							else
							{
								if (this.oCB_lief.isSelected())
								{
									vJasperHashes.add(new BST___JasperHashLIEFERSCHEIN(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
								}
								if (this.oCB_Lieferavis.isSelected())
								{
									vJasperHashes.add(new BST___JasperHashLIEFERAVIS(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
								}
								if (this.oCB_Abholnachricht.isSelected())
								{
									vJasperHashes.add(new BST___JasperHashABHOLNACHRICHT(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
								}
								if (this.oCB_EU_BLAETTER.isSelected())
								{
									vJasperHashes.add(new BST___JasperHash_NUR_EU_BLAETTER(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
								}

							}
							
							//CMR wird immer gedruckt
							if (this.oCB_CMR.isSelected())
							{
								vJasperHashes.add(new BST___JasperHashCMR(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME,recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
							}
						}
					}
				}
			}
			
			
			//2014-09-08: falls sammellieferschein, dann wird eine weitere jasperhash erzeugt
			if (this.oCB_SAMMEL_LIEFERSCHEIN.isSelected()) {
				vJasperHashes.add(new BST___JasperHashLIEFERSCHEIN_multi(___BUTTON_LIST_BT_Mail_and_Print.this.cMODULNAME, oThis.vID_VPOS_TPA_FUHRE, bMail_Is_Yes_In_PrintProtokoll));
			}
			
		}
		
		
		
		/**
		 * 
		 * @param StartDownload
		 * @param vMailBlock    wenn dies != null, dann wird gemailt
		 * @throws myException
		 */
		private void build_pdf_dokuments_and_mail_blocks(boolean StartDownload, MailBlock_Vector vMailBlock) throws myException
		{
			this.bStartDownload = StartDownload;
			this.VMailBlock = vMailBlock;
			
			this.bPrintOrMail = (vMailBlock==null);
			
			if (this.vJasperHashes.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine Dokumente erzeugt !")));
				return;
			}

			//wenn der mail-block mit uebergeben wird, dann zuerst die mailer-maske anzeigen (leer) damit das serverpush-fenster darueber liegt
			if (this.VMailBlock != null)
			{
				//massmailer bauen und anzeigen
				//jetzt den mailer aufbauen
			   
				this.oMassmailer =	new  ownMassMailer();
				
				this.oMassmailer.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Transportpapiere versenden ..."));					
			}

			
			
			
			
			if (this.vJasperHashes.size()>10)     //dann ein fortschrittspopup anzeigen lassen
			{
				new E2_ServerPushMessageContainer(new Extent(500),new Extent(200),new MyE2_String("Aufbereitung der Druckjobs ..."),true,true,false,3000)
				{
					@Override
					public void Run_Loop() throws myException
					{
						popUp_to_selectWhatToPrint oThis = popUp_to_selectWhatToPrint.this;
						
						if (! oThis.bPreview)
						{
							this.get_oGridBaseForMessages().addAfterRemoveAll(new MyE2_Label(new MyE2_String("Ich erzeuge die Belegnummern ...")));
							bibDB.ExecMultiSQLVector(vJasperHashes.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS(),true);
						}
			
						if (bibMSG.get_bIsOK())
						{
							oThis.vJasperHashes.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(this, false, oThis.bPrintOrMail);
							
							if (oThis.VMailBlock != null)
							{
								oThis.VMailBlock.addAll(oThis.vJasperHashes.BUILD_AND_GET_V_MAIL_BLOCKS());
								
								oThis.oMassmailer.baue_MailMaske(oThis.VMailBlock,
																bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),true,false,false,0,null);
							}
							
							if (oThis.bStartDownload)
							{
								oThis.vJasperHashes.DOWNLOAD_FILES(this.get_oGridBaseForMessages());
								
								if (bibMSG.get_bIsOK() && !oThis.bPreview)
								{
									this.get_oGridBaseForMessages().addAfterRemoveAll(new MyE2_Label(new MyE2_String("Ich markiere die Belege als erledigt ...")));
									bibDB.ExecMultiSQLVector(vJasperHashes.get_vSQL_STACK_EXECUTE_AFTER_FROM_ALL_HASHMAPS(),true);
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
				if (! this.bPreview)
				{
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vJasperHashes.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS(),true));
					//DEBUG.System_print(vJasperHashes.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS());
				}

				if (bibMSG.get_bIsOK())
				{
					vJasperHashes.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(null, false, this.bPrintOrMail);

					if (this.VMailBlock != null)
					{
						this.VMailBlock.addAll(this.vJasperHashes.BUILD_AND_GET_V_MAIL_BLOCKS());
						this.oMassmailer.baue_MailMaske(this.VMailBlock,bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),true,false,false,0,null);
					}

					
					if (this.bStartDownload)
					{
						vJasperHashes.DOWNLOAD_FILES(null);
						
						if (bibMSG.get_bIsOK() && !this.bPreview)
						{
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vJasperHashes.get_vSQL_STACK_EXECUTE_AFTER_FROM_ALL_HASHMAPS(),true));
						}

					}
					
				}
			}
		}

		
		
		private class ownActionStartPrint extends XX_ActionAgent
		{
			private boolean Preview = false;
			
			public ownActionStartPrint(boolean preview)
			{
				super();
				this.Preview = preview;
			}

			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				___BUTTON_LIST_BT_Mail_and_Print 	ooThis = 	___BUTTON_LIST_BT_Mail_and_Print.this;
				popUp_to_selectWhatToPrint 			oThis = 	popUp_to_selectWhatToPrint.this;

				popUp_to_selectWhatToPrint.this.bPreview = this.Preview;
				
				bibMSG.add_MESSAGE(execInfo.make_ID_Validation((ooThis.vID_VPOS_TPA_FUHRE)));
				
				if (bibMSG.get_bHasAlarms()){return;}

				oThis.fill_V_JasperHash(false);
				if (bibMSG.get_bHasAlarms())    //2011-12-08:  dann  ist der validierer im BST___JasperHashLIEFERSCHEIN/dem dortigen __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE fehlgegangen
				{
					return;
				}

				
				
				if (this.Preview)
				{
					oThis.vJasperHashes.set_TYPE_PREVIEW();
				}
				else
				{
					oThis.vJasperHashes.set_TYPE_PRINT();
				}
				
				if (bibMSG.get_bHasAlarms()){return;}
				
				oThis.build_pdf_dokuments_and_mail_blocks(true,null);
				if (bibMSG.get_bHasAlarms()){return;}
			}
		}


		
		
		
		

		
		private class ownActionStartMail extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				___BUTTON_LIST_BT_Mail_and_Print 		ooThis = 	___BUTTON_LIST_BT_Mail_and_Print.this;
				popUp_to_selectWhatToPrint 			oThis = 	popUp_to_selectWhatToPrint.this;
				
				bibMSG.add_MESSAGE(execInfo.make_ID_Validation((ooThis.vID_VPOS_TPA_FUHRE)));
				
				popUp_to_selectWhatToPrint.this.bPreview = false;             //mail geht nicht mit preview
				
				if (bibMSG.get_bHasAlarms()) {return;}

				oThis.fill_V_JasperHash(true);
				if (bibMSG.get_bHasAlarms())    //2011-12-08:  dann  ist der validierer im BST___JasperHashLIEFERSCHEIN/dem dortigen __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE fehlgegangen
				{
					return;
				}
				
				
				oThis.vJasperHashes.set_TYPE_MAIL();

				
				if (bibMSG.get_bHasAlarms()) {return;}
				
				if (! oThis.vJasperHashes.get_bAll_Have_MAILBLOCK_with_Minimum_One_MailAdresse())
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nicht alle Belege sind zum Senden vorgesehen !")));
					return;
				}
				MailBlock_Vector vMailBlock = new MailBlock_Vector();
				
				oThis.build_pdf_dokuments_and_mail_blocks(false,vMailBlock);

			}
		}
			
	}
	
	
	private class ownMassMailer extends E2_MassMailer
	{

		public ownMassMailer() throws myException
		{
			super("TPA_MAIL_BETREFF", "TPA_MAIL_TEXTBLOCK","TPA", new MailSecurityPolicyAllowNothing_but_EditAdress());
		}

		@Override
		public MailBlock build_MailBlock4Added_EmptyMails() throws myException
		{
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_MitarbeiterMails() throws myException
		{
			return null;
		}

		@Override
		public MailBlock build_MailBlock4Added_SearchedMails() throws myException
		{
			return null;
		}
		
	}

	
	
	//sammelklasse fuer das sammeln von id_vpos_tpa_fuhre-werten (fuer drucke)
	public static abstract class ___Sammler_ID_VPOS_TPA_FUHRE
	{
		public abstract Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException;
		
		public abstract void  rebuild_Navilist_if_Needed() throws myException;
	}

	
	
	
	public static class ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_BUTTON extends ___Sammler_ID_VPOS_TPA_FUHRE
	{
		private String   cID_VPOS_TPA = null;
		
		
		public ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_BUTTON(String  ID_VPOS_TPA)
		{
			super();
			cID_VPOS_TPA = ID_VPOS_TPA;
		}


		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException
		{
			Vector<String> vRueck = new Vector<String>();

		    vRueck.add( new RECORD_VPOS_TPA(cID_VPOS_TPA).get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF());

		    return vRueck;
		}


		@Override
		public void rebuild_Navilist_if_Needed() throws myException
		{
		}

	}

	
	
	public static class ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_FUHRE_LIST extends ___Sammler_ID_VPOS_TPA_FUHRE
	{
		private E2_NavigationList  oNaviList = null;
		
		
		public ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_FUHRE_LIST(E2_NavigationList list)
		{
			super();
			oNaviList = list;
		}


		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.addAll(this.oNaviList.get_vSelectedIDs_Unformated());
			return vRueck;
		}

		
		@Override
		public void rebuild_Navilist_if_Needed() throws myException
		{
			this.oNaviList._REBUILD_ACTUAL_SITE("");
		}

	}

	
	
	public static class ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_FUHRE_MASK extends ___Sammler_ID_VPOS_TPA_FUHRE
	{
		private FU_MASK_ModulContainer  	oFU_Mask = null;
		private boolean   					bViewOnly = false;

		
		
		public ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_FUHRE_MASK(FU_MASK_ModulContainer mask, boolean viewOnly)
		{
			super();
			oFU_Mask = mask;
			this.bViewOnly = 	viewOnly;
		}


		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException
		{
			Vector<String> vRueck = new Vector<String>();
			
			if (this.oFU_Mask.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP()==null)
			{
				//dann wars eine neueingabe
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Druck/Mail kann erst erfolgen, wenn der Datensatz gespeichert war"));
			}

			if (bibMSG.get_bIsOK())
			{
				if (!this.bViewOnly)
				{
					new E2_SaveMaskStandard(this.oFU_Mask,null).doSaveMask(true);
				}
				
				if (bibMSG.get_bIsOK())
				{
					String cID_VPOS_TPA_FUHRE = this.oFU_Mask.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					
				    vRueck.add(cID_VPOS_TPA_FUHRE);
				}
			}
			return vRueck;
		}

		
		@Override
		public void rebuild_Navilist_if_Needed() throws myException
		{
		}

	
	}
	
	
	
	
	
	
	
	public static class ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_LIST extends ___Sammler_ID_VPOS_TPA_FUHRE
	{
		private E2_NavigationList  oNaviList = null;
		
		
		public ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_LIST(E2_NavigationList list)
		{
			super();
			oNaviList = list;
		}


		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException
		{
			Vector<String> vRueck = new Vector<String>();
			
			Vector<String> vID_VPOS_TPA = this.oNaviList.get_vSelectedIDs_Unformated();
			
			RECLIST_VPOS_TPA_FUHRE  recList = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA IN ("+bibALL.Concatenate(vID_VPOS_TPA, ",", "0")+")");
			
			vRueck.addAll(recList.get_vKeyValues());
					
			return vRueck;
		}

		
		@Override
		public void rebuild_Navilist_if_Needed() throws myException
		{
			this.oNaviList._REBUILD_ACTUAL_SITE("");
		}

	}

	
	
	
	public static class ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK extends ___Sammler_ID_VPOS_TPA_FUHRE
	{
		private BST_P_MASK__ModulContainer  oBST_P_Mask = null;
		private boolean   					bViewOnly = false;
		
		
		public ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK(BST_P_MASK__ModulContainer mask, boolean viewOnly)
		{
			super();
			oBST_P_Mask = 		mask;
			this.bViewOnly = 	viewOnly;
		}


		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException
		{
			Vector<String> vRueck = new Vector<String>();
			
			if (this.oBST_P_Mask.get_ComponentMapMask().get_oInternalSQLResultMAP()==null)
			{
				//dann wars eine neueingabe
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Druck/Mail kann erst erfolgen, wenn der Datensatz gespeichert war"));
			}

			if (bibMSG.get_bIsOK())
			{
				if (!this.bViewOnly)
				{
					new E2_SaveMaskStandard(this.oBST_P_Mask,null).doSaveMask(true);
				}
				
				if (bibMSG.get_bIsOK())
				{
					String cID_VPOS_TPA = this.oBST_P_Mask.get_ComponentMapMask().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					
				    RECORD_VPOS_TPA recVPOSTPA = new RECORD_VPOS_TPA(cID_VPOS_TPA);
				    
				    vRueck.add(recVPOSTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF());
				}
			}
			return vRueck;
		}
		
		@Override
		public void rebuild_Navilist_if_Needed() throws myException
		{
		}


	}
	
	
	
	
	
	private class own_Decision_CheckKredit extends DA_Decision_CheckKreditVersicherung {
		public own_Decision_CheckKredit(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Vector<String> get_v_ids_fuhren_2_check_kredit() throws myException {
			Vector<String> vID_VPOS_TPA_FUHRE = new Vector<String>(); 
			vID_VPOS_TPA_FUHRE.addAll(___BUTTON_LIST_BT_Mail_and_Print.this.oID_Sammler.get_vID_VPOS_TPA_FUHRE());
			return vID_VPOS_TPA_FUHRE;
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}

	}
	
	
	
	
	//2016-01-29: descision-pruefungen
	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
	
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return this.storage_vector_4_all_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return this.storage_vector_4_standard_agents;
	}

	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return this.storage_vector_4_decision_agents;
	}

	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		return this.hm_descision_containers;
	}

	
	
}
