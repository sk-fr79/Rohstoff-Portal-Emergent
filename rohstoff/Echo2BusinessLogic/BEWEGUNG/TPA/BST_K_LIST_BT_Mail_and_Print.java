package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Component;
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
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
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
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_Decision_CheckKreditVersicherung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN.__FU_Validator_FuhreErlaubt;

public class BST_K_LIST_BT_Mail_and_Print extends MyE2_Button  implements DS_IF_components4decision
{

	private E2_NavigationList onavigationList = null;
	
	//2014-10-08: sammelvector fuer die ID_VPOS_TPA_FUHRE
	private Vector<String>  vID_VPOS_TPA_FUHRE = new Vector<String>();
	
	public BST_K_LIST_BT_Mail_and_Print(E2_NavigationList NavigationList)
	{
		super(E2_ResourceIcon.get_RI("printer.png"),true);
		
		this.onavigationList = NavigationList;
		
//		//2014-11-12: neuer validierer einfuegen
//		this.add_GlobalValidator(new ownValidKreditStatus());

		 
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VKOPF_TPA",
									"NVL(DELETED,'N')," +
									"CASE WHEN (SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA " +
											" WHERE JT_VPOS_TPA.ID_VKOPF_TPA=JT_VKOPF_TPA.ID_VKOPF_TPA AND " +
											" NVL(JT_VPOS_TPA.DELETED,'N')='N')>0 THEN 'Y' ELSE 'N' END",
							 bibALL.get_Array("N","Y"),true, new MyE2_String("Der Transportauftrag wurde bereits gelöscht oder hat keine Positionen")));

		this.add_IDValidator(new ownValidator());
		
		
		//2016-01-29: pruefung der datumsbereiche
		this.add_oActionAgent(new ____DA_Decision_DatumsDifferenz(this,new ownFuhrenIdSammler()));
		//2016-02-23: und die neue EU-Vertrag-Pruefung
		this.add_oActionAgent(new ____DA_DecisionHat_EU_VERTRAG(this,new ownFuhrenIdSammler()));
		//2016-03-07: die kreditpruefung auch als decision
		this.add_oActionAgent(new own_Decision_CheckKredit(this));

		//2016-04-06: weitere desicion: bordercrossing
		this.add_oActionAgent(new ____DA_Decision_BorderCrossing(this,new ownFuhrenIdSammler()));

		
		this.add_oActionAgent(new ownActionStart_selector());
		

	}

	
	
	
	/*
	 * Aenderung 2010-12-22:
	 * Validierer, der nur TPAs drucken laesst mit kompletten
	 * fuhren (falls kopiert wurde)
	 */
	
	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException 
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException 
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			RECORD_VKOPF_TPA  recVkopf = new RECORD_VKOPF_TPA(cID_Unformated);
			
			RECLIST_VPOS_TPA  recListPositions = recVkopf.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa("NVL(DELETED,'N')='N'", null, true);
			boolean bUnvollstaendigeFuhreGefunden = false;
			
			if (recListPositions !=null)
			{
				Iterator<RECORD_VPOS_TPA>  iter = recListPositions.values().iterator();

				while (iter.hasNext())	
				{
					RECORD_VPOS_TPA  recPos = iter.next();
					
					if (recPos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).is_FUHRE_KOMPLETT_NO())
					{
						bUnvollstaendigeFuhreGefunden = true;
					}
					
					//weiter pruefen, ob es eine unbezahlte akonto-lieferung ist
					
					oMV.add_MESSAGE(new ___FUHRE_VALID_HELPER_AKONTO_Sicherung().pruefe_akonto_status(recPos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0)));
					
					//2014-01-30: validierer fuer die korrekte adresse usw
					oMV.add_MESSAGE(new __FU_Validator_FuhreErlaubt().make_valid(recPos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF()));
					
				}
			}
			
			if (bUnvollstaendigeFuhreGefunden)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Fuhre wurde noch nicht bearbeitet :",true,"TPA-ID:",true,cID_Unformated,false)));
			}
			
			return oMV;
		}
		
	}
	
	
		
	private class ownActionStart_selector extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			BST_K_LIST_BT_Mail_and_Print oThis = BST_K_LIST_BT_Mail_and_Print.this;
			
			Vector<String> vIDs = oThis.onavigationList.get_vSelectedIDs_Unformated();
			
			BST_K_LIST_BT_Mail_and_Print.this.vID_VPOS_TPA_FUHRE.removeAllElements();
			BST_K_LIST_BT_Mail_and_Print.this.vID_VPOS_TPA_FUHRE.addAll(new ownFuhrenIdSammler().get_vID_VPOS_TPA_FUHRE());
			
			if (vIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Zum Drucken bitte mindestens einen TPA auswählen !"));
				return;
			}
					
			bibMSG.add_MESSAGE(oThis.valid_IDValidation(vIDs));
			
			if (bibMSG.get_bIsOK())
			{
				new popUp_to_selectWhatToPrint();
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

	
	
	
	private class popUp_to_selectWhatToPrint extends E2_BasicModuleContainer
	{
		private MyE2_CheckBox  oCB_tpa = 			new MyE2_CheckBox(new MyE2_String("Transportauftrag"));
		private MyE2_CheckBox  oCB_lief = 			new MyE2_CheckBox(new MyE2_String("Lieferschein"));
		private MyE2_CheckBox  oCB_Lade = 			new MyE2_CheckBox(new MyE2_String("Ladeschein"));
		private MyE2_CheckBox  oCB_Abholavis = 		new MyE2_CheckBox(new MyE2_String("Abholavis"));
		private MyE2_CheckBox  oCB_Lieferavis =	 	new MyE2_CheckBox(new MyE2_String("Lieferavis"));
		private MyE2_CheckBox  oCB_Abholnachricht = new MyE2_CheckBox(new MyE2_String("Abholnachricht"));
		private MyE2_CheckBox  oCB_CMR = 			new MyE2_CheckBox(new MyE2_String("CMR"));
		
		private MyE2_Button    oButtonVorschau = 	new MyE2_Button(new MyE2_String("Vorschau"));
		private MyE2_Button    oButtonDrucke = 		new MyE2_Button(new MyE2_String("Druck"));
		private MyE2_Button    oButtonMail = 		new MyE2_Button(new MyE2_String("eMail"));
		
		
		//2014-10-08: sammellieferschein
		private MyE2_CheckBox  oCB_SAMMEL_LIEFERSCHEIN = new MyE2_CheckBox(new MyE2_String("Sammel-Lieferschein"));

		
		private V_JasperHASH 	vJasperHashes = 	new V_JasperHASH();
		private boolean 		bPreview = 			false;
		
		//variable werden hier gebraucht fuer die serverpush-meldungen, die in einem eigene thread laufen
		private boolean         	bStartDownload = 	false;
		private MailBlock_Vector 	VMailBlock   = 		null;
		private E2_MassMailer 		oMassmailer = 		null;

		private boolean    			bPrintOrMail = true;
		
		


		public popUp_to_selectWhatToPrint() throws myException
		{
			super();
			
			//2014-10-08: Sammellieferschein
			boolean bEnabledSammelschein = new ___BUTTON_LIST_BT_Mail_and_Print_ValidSammellieferscheinAllowed(
					new ownFuhrenIdSammler()).get_bSammelLieferscheinOK();

			this.oCB_SAMMEL_LIEFERSCHEIN.setSelected(false);
			this.oCB_SAMMEL_LIEFERSCHEIN.EXT().set_oE2_Style(MyE2_CheckBox.STYLE_NORMAL_WITH_GREY_DISABLE());
			this.oCB_SAMMEL_LIEFERSCHEIN.set_bEnabled_For_Edit(bEnabledSammelschein);

			
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
					oThis.oCB_tpa.setSelected(bAn);
					oThis.oCB_CMR.setSelected(bAn);
				}
			});
			
			
			
			//zeile 1
			oGridBase.add(new MyE2_Label(new MyE2_String("Druckauswahl für Transportpapiere ..."), MyE2_Label.STYLE_TITEL_BIG()),3,E2_INSETS.I_5_5_5_5);
			
			oGridBase.add(new Separator(),3, E2_INSETS.I_5_10_5_10);
			
			//zeile 2
			oGridBase.add(this.oCB_tpa,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_0_0); 
			oGridBase.add(cbToggle,1, E2_INSETS.I_5_5_5_5);
			
			
			//zeile 3
			oGridBase.add(this.oCB_Lade,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(this.oCB_Abholavis,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_0_0); 

			//zeile4
			oGridBase.add(this.oCB_lief,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(this.oCB_Lieferavis,E2_INSETS.I_5_5_5_5); 
			oGridBase.add(this.oCB_Abholnachricht,1,E2_INSETS.I_0_0_0_0); 
			
			//zeile5
			oGridBase.add(this.oCB_SAMMEL_LIEFERSCHEIN,2,E2_INSETS.I_5_5_5_5);
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
					BST_K_LIST_BT_Mail_and_Print.this.onavigationList._REBUILD_ACTUAL_SITE("");
				}
			});
			
		}
		
		
		private void fill_V_JasperHash(boolean bMail_Is_Yes_In_PrintProtokoll) throws myException
		{
			Vector<String> vIDs = BST_K_LIST_BT_Mail_and_Print.this.onavigationList.get_vSelectedIDs_Unformated();
		
			this.vJasperHashes.removeAllElements();
			
			for (int i=0;i<vIDs.size();i++)
			{
				RECORD_VKOPF_TPA  recTPA = new RECORD_VKOPF_TPA(vIDs.get(i));
				
				boolean bFirstPosition = true;

				
				//2011-11-29: sortierung beim Drucker nach positionsnummer
				//RECLIST_VPOS_TPA reclistPOS_TPA = recTPA.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa();
				RECLIST_VPOS_TPA reclistPOS_TPA = recTPA.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa(null,"JT_VPOS_TPA.POSITIONSNUMMER",true);
				
				for (int k=0;k<reclistPOS_TPA.get_vKeyValues().size();k++)
				{
					if (reclistPOS_TPA.get(k).is_DELETED_NO() )
					{
						//TPA nur erzeugen, wenn es eine position gibt
						if (this.oCB_tpa.isSelected() && bFirstPosition)
						{
							vJasperHashes.add(new BST___JasperHashTPA("TRANSPORTAUFTRAGSMODUL",vIDs.get(i),bMail_Is_Yes_In_PrintProtokoll));
							bFirstPosition = false;
						}

						
						RECORD_VPOS_TPA_FUHRE recFUHRE = reclistPOS_TPA.get(k).get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0);
						
						if (this.oCB_lief.isSelected())
						{
							vJasperHashes.add(new BST___JasperHashLIEFERSCHEIN("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
						}
						if (this.oCB_Lade.isSelected())
						{
							vJasperHashes.add(new BST___JasperHashLADESCHEIN("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
						}
						if (this.oCB_Abholavis.isSelected())
						{
							vJasperHashes.add(new BST___JasperHashABHOLAVIS("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
						}
						if (this.oCB_Lieferavis.isSelected())
						{
							vJasperHashes.add(new BST___JasperHashLIEFERAVIS("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
						}
						if (this.oCB_Abholnachricht.isSelected() && recFUHRE.get_ID_ADRESSE_START_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
						{
							// !!!!! ABHOLNACHRICHT wird nur bei Abholen von eigenen Lagern erzeugt !!!!!!!!! 
							vJasperHashes.add(new BST___JasperHashABHOLNACHRICHT("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
						}
						if (this.oCB_CMR.isSelected())
						{
							vJasperHashes.add(new BST___JasperHashCMR("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),null,bMail_Is_Yes_In_PrintProtokoll));
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
											vJasperHashes.add(new BST___JasperHashLADESCHEIN("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
										}
										if (this.oCB_Abholavis.isSelected())
										{
											vJasperHashes.add(new BST___JasperHashABHOLAVIS("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
										}
									}
									else
									{
										if (this.oCB_lief.isSelected())
										{
											vJasperHashes.add(new BST___JasperHashLIEFERSCHEIN("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
										}
										if (this.oCB_Lieferavis.isSelected())
										{
											vJasperHashes.add(new BST___JasperHashLIEFERAVIS("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
										}
										if (this.oCB_Abholnachricht.isSelected())
										{
											vJasperHashes.add(new BST___JasperHashABHOLNACHRICHT("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
										}
									}
									
									//CMR wird immer gedruckt
									if (this.oCB_CMR.isSelected())
									{
										vJasperHashes.add(new BST___JasperHashCMR("TRANSPORTAUFTRAGSMODUL",recFUHRE.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),bMail_Is_Yes_In_PrintProtokoll));
									}
								}
							}
						}
					}
				}
			}
			
			
			//2014-09-08: falls sammellieferschein, dann wird eine weitere jasperhash erzeugt
			if (this.oCB_SAMMEL_LIEFERSCHEIN.isSelected()) {
				vJasperHashes.add(new BST___JasperHashLIEFERSCHEIN_multi(	"TRANSPORTAUFTRAGSMODUL",
																			BST_K_LIST_BT_Mail_and_Print.this.vID_VPOS_TPA_FUHRE, 
																			bMail_Is_Yes_In_PrintProtokoll));
			}

			
			
		}
		
		
		
		/**
		 * 
		 * @param StartDownload
		 * @param vMailBlock    wenn dies != null, dann wird gemailt
		 * @throws myException
		 */
		private void build_pdf_dokuments(boolean StartDownload, MailBlock_Vector vMailBlock) throws myException
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
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vJasperHashes.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS(),true));
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
									bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vJasperHashes.get_vSQL_STACK_EXECUTE_AFTER_FROM_ALL_HASHMAPS(),true));
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
				}

				if (bibMSG.get_bIsOK())
				{
					vJasperHashes.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(null, false,this.bPrintOrMail);

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
				popUp_to_selectWhatToPrint oThis = popUp_to_selectWhatToPrint.this;
				
				popUp_to_selectWhatToPrint.this.bPreview = this.Preview;
				
				
				Vector<String> vIDs = BST_K_LIST_BT_Mail_and_Print.this.onavigationList.get_vSelectedIDs_Unformated();

				bibMSG.add_MESSAGE(execInfo.make_ID_Validation((vIDs)));
				
				if (bibMSG.get_bHasAlarms()){return;}

				oThis.fill_V_JasperHash(false);

				if (bibMSG.get_bHasAlarms())    //2011-12-08:  dann  ist der validierer im BST___JasperHashLIEFERSCHEIN/dem dortigen __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE fehlgegangen
				{
					return;
				}

				
				/*
				 * aenderung 2010-10-19: print-pipeline
				 */
				if (this.Preview)
				{
					oThis.vJasperHashes.set_TYPE_PREVIEW();
				}
				else
				{
					oThis.vJasperHashes.set_TYPE_PRINT();
				}
				
				
				if (bibMSG.get_bHasAlarms()){return;}
				
				oThis.build_pdf_dokuments(true,null);
				if (bibMSG.get_bHasAlarms()){return;}
			}
		}


		
		
		
		

		
		private class ownActionStartMail extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				popUp_to_selectWhatToPrint oThis = popUp_to_selectWhatToPrint.this;
				Vector<String> vIDs = BST_K_LIST_BT_Mail_and_Print.this.onavigationList.get_vSelectedIDs_Unformated();
				
				bibMSG.add_MESSAGE(execInfo.make_ID_Validation((vIDs)));
				
				popUp_to_selectWhatToPrint.this.bPreview = false;             //mail geht nicht mit preview
				
				if (bibMSG.get_bHasAlarms()) {return;}

				oThis.fill_V_JasperHash(true);
				if (bibMSG.get_bHasAlarms())    //2011-12-08:  dann  ist der validierer im BST___JasperHashLIEFERSCHEIN/dem dortigen __SPECIAL_VALID_RECORD_VPOS_TPA_FUHRE fehlgegangen
				{
					return;
				}

				/*
				 * aenderung 2010-10-19: print-pipeline
				 */
				oThis.vJasperHashes.set_TYPE_MAIL();
				
				if (bibMSG.get_bHasAlarms()) {return;}
				
				if (! oThis.vJasperHashes.get_bAll_Have_MAILBLOCK_with_Minimum_One_MailAdresse())
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nicht alle Belege sind zum Senden vorgesehen !")));
					return;
				}
				MailBlock_Vector vMailBlock = new MailBlock_Vector();
				
				oThis.build_pdf_dokuments(false,vMailBlock);

			}
		}
			
	}




//	@Override
//	public V_Single_BasicModuleContainer_PopUp_BeforeExecute get_vE2_BasicModuleContainer_PopUp_BeforeExecute() throws myException {
//		V_Single_BasicModuleContainer_PopUp_BeforeExecute vPopups = new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
//		vPopups.add(new ownCHK_Kredit_Status());
//		return vPopups;
//	}
	
	
//	private class ownCHK_Kredit_Status extends CHK_Kredit_Status {
//
//		@Override
//		public Vector<String> get_vID_VPOS_TPA_FUHREN() throws myException {
//			Vector<String> vID_VPOS_TPA_FUHRE = new Vector<String>(); 
//			
//			Vector<String> vID_VKOPF_TPA = new Vector<String>();
//			vID_VKOPF_TPA.addAll(BST_K_LIST_BT_Mail_and_Print.this.onavigationList.get_vSelectedIDs_Unformated());
//			
//			for (String cID_VKOPF_TPA: vID_VKOPF_TPA) {
//				RECORD_VKOPF_TPA  recTPA = new RECORD_VKOPF_TPA(cID_VKOPF_TPA);
//				
//				RECLIST_VPOS_TPA rlPOS_TPA = recTPA.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa();
//				
//				for (RECORD_VPOS_TPA recPOS_TPA: rlPOS_TPA.values()) {
//					if (recPOS_TPA.is_DELETED_NO()) {
//						vID_VPOS_TPA_FUHRE.add(recPOS_TPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF());
//					}
//				}
//			}
//			
//			return vID_VPOS_TPA_FUHRE;
//		}
//		
//	}
//	
	
	
	
	private class ownFuhrenIdSammler extends ___Sammler_ID_VPOS_TPA_FUHRE {

		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException {
			Vector<String>  vIDFuhren = new Vector<String>();
			
			Vector<String> vID_TPA = BST_K_LIST_BT_Mail_and_Print.this.onavigationList.get_vSelectedIDs_Unformated();
			
			for (String cID: vID_TPA) {
				RECORD_VKOPF_TPA  recTPA = new RECORD_VKOPF_TPA(cID);
				
				for (RECORD_VPOS_TPA rec_PTPA: recTPA.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa().values()) {
					if (rec_PTPA.is_DELETED_NO()) {
						vIDFuhren.add(rec_PTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF());
					}
				}
			}
			
			return vIDFuhren;
		}

		@Override
		public void rebuild_Navilist_if_Needed() throws myException {
		}
		
	}
	
//	private class ownValidKreditStatus extends VALIDATOR_KreditStatus {
//
//		@Override
//		public Vector<String> get_Actual_ID_VPOS_TPA_FUHRE_To_Print() throws myException {
//			Vector<String> vID_VPOS_TPA_FUHRE = new Vector<String>(); 
//			
//			Vector<String> vID_VKOPF_TPA = new Vector<String>();
//			vID_VKOPF_TPA.addAll(BST_K_LIST_BT_Mail_and_Print.this.onavigationList.get_vSelectedIDs_Unformated());
//			
//			for (String cID_VKOPF_TPA: vID_VKOPF_TPA) {
//				RECORD_VKOPF_TPA  recTPA = new RECORD_VKOPF_TPA(cID_VKOPF_TPA);
//				
//				RECLIST_VPOS_TPA rlPOS_TPA = recTPA.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa();
//				
//				for (RECORD_VPOS_TPA recPOS_TPA: rlPOS_TPA.values()) {
//					if (recPOS_TPA.is_DELETED_NO()) {
//						vID_VPOS_TPA_FUHRE.add(recPOS_TPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF());
//					}
//				}
//			}
//			
//			return vID_VPOS_TPA_FUHRE;
//		}
//		
//	}

	//2016-03-07: validierung des kreditstatus als decision
	private class own_Decision_CheckKredit extends DA_Decision_CheckKreditVersicherung {
		public own_Decision_CheckKredit(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}
		
		@Override
		public Vector<String> get_v_ids_fuhren_2_check_kredit() throws myException {
			Vector<String> vID_VPOS_TPA_FUHRE = new Vector<String>(); 
			
			Vector<String> vID_VKOPF_TPA = new Vector<String>();
			vID_VKOPF_TPA.addAll(BST_K_LIST_BT_Mail_and_Print.this.onavigationList.get_vSelectedIDs_Unformated());
			
			for (String cID_VKOPF_TPA: vID_VKOPF_TPA) {
				RECORD_VKOPF_TPA  recTPA = new RECORD_VKOPF_TPA(cID_VKOPF_TPA);
				
				RECLIST_VPOS_TPA rlPOS_TPA = recTPA.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa();
				
				for (RECORD_VPOS_TPA recPOS_TPA: rlPOS_TPA.values()) {
					if (recPOS_TPA.is_DELETED_NO()) {
						vID_VPOS_TPA_FUHRE.add(recPOS_TPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF());
					}
				}
			}
			
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
