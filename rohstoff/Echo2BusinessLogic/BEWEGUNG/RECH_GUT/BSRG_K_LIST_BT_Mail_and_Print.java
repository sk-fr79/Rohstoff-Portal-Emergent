package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_ActionAgent_Mail_Print;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL.__JASPER_EXEC_Pruefe_Original_per_Mail;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM.PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_ALTERNATIV3;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM.PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_STD;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VkopfRG;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class BSRG_K_LIST_BT_Mail_and_Print extends MyE2_Button
{
	
	private BS__SETTING BS_Setting = null;
	
	public BSRG_K_LIST_BT_Mail_and_Print(	MyString 						cButtonText, 
											E2_NavigationList 				navList,  
											BS__SETTING 					setting, 
											String 							MODUL_KENNER, 
											boolean 						preview,
											BSRG_K_MASK__ModulContainer  	RG_ModuleContainerMask) throws myException
	{
		super(preview?E2_ResourceIcon.get_RI("print_preview.png"):E2_ResourceIcon.get_RI("print_and_mail.png"));
		
		this.BS_Setting = setting;
		
		
		this.add_IDValidator(new valid_RG_HatPositionen());
		this.add_IDValidator(new validPositionenVollstaendig());
		this.add_IDValidator(new validPositionenLeistungsdatumAusEinemJahr());
		this.add_IDValidator(new validKundenMuessenKreditorenDebitorenNummerHaben());
//		this.add_IDValidator(new validOhneSteuernummerKeineMWST());
//		
//		//2012-08-02: neuer validierter, prueft, ob eine firmenadresse, die eine rechnung/gutschrift erhaelt, innerhalb EU eine UST-ID besitzt
//		this.add_IDValidator(new valid_Kunden_und_Beleg_MUSS_UST_ID_HABEN());
//		this.add_IDValidator(new valid_PRIVAT_KUNDE_MUSS_AUSWEISNUMMER_HABEN());
		
		//2013-11-20: neue validierung mit hilfe der gleichen einstufungsklasse, die auch fuer die steuerfindung benutzt wird
		this.add_IDValidator(new BSRG_K_LIST_BT_Mail_and_Print_Validiere_Steuer());
		//2013-12-11: neue validierung: vergleich der ust-ids in beleg und stammsatz
		this.add_IDValidator(new BSRG_K_LIST_BT_Mail_and_Print_Valid_Ableich_UST_ID());
		
		
		//2019-06-24: validierer, der verhindert, dass forderungen und verbindlichkeiten gemischt werden 
		//this.add_GlobalValidator(new ValidOnlyForderungenOderVerbindlichkeiten(navList, RG_ModuleContainerMask));
		
		//2019-06-24: validierer, der verhindert, dass offene und geschlossene belege gemischt werden
		this.add_GlobalValidator(new ValidKeineGemischtenOffenOderGeschlossen(navList, RG_ModuleContainerMask));
		
		this.add_GlobalValidator(new ValidNurBelegeMitEindeutigenKreditversicherungen(navList, RG_ModuleContainerMask));
		
		/*
		 * dummy-actionagent zur validierung wird deswegen vorgestellt, weil 
		 * this.set_oPopup_ContainerBeforeExecute(new BSRG_K_LIST_BT_MAIL_PRINT_PopupContainer( this, preview));
		 * der popup_bevor-execute probleme macht, wenn dort noch validierungsfehler auflaufen.
		 * Dieser werden vorher alle abgefangen
		 */
		this.add_oActionAgent(new ownAgentOnly4Validation(navList, RG_ModuleContainerMask));
		
		
		if (RG_ModuleContainerMask != null)
		{
			//dann muss die maske (falls im edit-modus) auch gespeichert werden
			this.add_oActionAgent(new ownActionSave(navList, RG_ModuleContainerMask));
		}
		
		
		this.add_oActionAgent(
				new ownActionAgentPrintOrMail(
						new MyE2_String("Drucke / Maile Rechnung oder Gutschrift"), 
						null, 
						"rechnung_gutschrift",
						navList, 
						setting, 
						"id_vkopf_rg", 
						MODUL_KENNER, 
						preview,
						RG_ModuleContainerMask));


		if (RG_ModuleContainerMask != null)
		{
			this.add_oActionAgent(new ownActionLoadAfterPrint(RG_ModuleContainerMask));
		}
				
		
		if (preview)   //bei druck und mail erfolgt die validierung im auswahl-popup
		{
			this.add_GlobalAUTHValidator_AUTO("VORSCHAU_"+setting.get_cBELEGNAME());
			this.setToolTipText(new MyE2_String("Vorschau einer Rechnung/Gutschrift !").CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Drucke oder Mail eine Rechnung/Gutschrift !").CTrans());
		}
		
	}

	

	private class ownAgentOnly4Validation extends XX_ActionAgent
	{
		private E2_NavigationList 				navList = null;  
		private BSRG_K_MASK__ModulContainer  	RG_ModuleContainerMask = null;
		
		
		
		public ownAgentOnly4Validation(E2_NavigationList NaviList, BSRG_K_MASK__ModulContainer rG_ModuleContainerMask)
		{
			super();
			this.navList = NaviList;
			this.RG_ModuleContainerMask = rG_ModuleContainerMask;
		}



		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vIDs = new Vector<String>();
			
			if (this.RG_ModuleContainerMask!=null)
			{
				//fuer druck aus der maske
				//dann ist voellig egal, wie der vIDs aussieht (normalerweiser enthaelt der die ids aus der liste)
				//er wird geloescht und durch die id der maske ersetzt
				
				E2_ComponentMAP  oMap = this.RG_ModuleContainerMask.get_vCombinedComponentMAPs().get(0);
				
				SQLResultMAP  oResultMap = oMap.get_oInternalSQLResultMAP();
				
				if (oResultMap==null)
				{
					throw new myException(this,"Only in edit- or view-masks allowed!!");
				}
				vIDs.add(oResultMap.get_cUNFormatedROW_ID());	
			}
			else
			{
				vIDs.addAll(this.navList.get_vSelectedIDs_Unformated());
			}

			bibMSG.add_MESSAGE(BSRG_K_LIST_BT_Mail_and_Print.this.valid_IDValidation(vIDs));
		}
	}
	

	
	private class valid_RG_HatPositionen extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VKOPF_RG) 	throws myException
		{
			MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
			
			String cAnzahl = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' AND ID_VKOPF_RG="+cID_VKOPF_RG);
			
			int iAnzahl = new MyInteger(cAnzahl).get_iValue();
			
			if (iAnzahl==0)
			{
				oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message("Sie können nur Rechnungen erzeugen, die MINDESTENS eine Position besitzen !"));
			}
			
			return oMV_Rueck;
		}
		
	}
	

	
	
	
	private class validPositionenLeistungsdatumAusEinemJahr extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VKOPF_RG) 	throws myException
		{
			MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
			
			String cAnzahl = bibDB.EinzelAbfrage("SELECT COUNT(DISTINCT TO_CHAR(JT_VPOS_RG.AUSFUEHRUNGSDATUM,'YYYY')) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' AND ID_VKOPF_RG="+cID_VKOPF_RG);
			
			int iAnzahl = new MyInteger(cAnzahl).get_iValue();
			
			if (iAnzahl>=2)
			{
				oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message("Sie können nur Rechnungen erzeugen, deren Positionen ein Leistungsdatum aus EINEM Jahr enthalten !"));
			}
			
			return oMV_Rueck;
		}
		
	}
	
	
	
	
	
	
	/*
	 * validierer, der verhindert, dass ein Druck ohne kreditor/debitor-nummer ausgefuehrt wird
	 */
	private class validKundenMuessenKreditorenDebitorenNummerHaben extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VKOPF_RG) 	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector(); 
			
			RECORD_FIRMENINFO recFirmenInfo = (new RECORD_VKOPF_RG(cID_VKOPF_RG).get_UP_RECORD_ADRESSE_id_adresse()).get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
			
			String cBelegTyp = BSRG_K_LIST_BT_Mail_and_Print.this.BS_Setting.get_cBELEGTYP();
			
			if (cBelegTyp.equals(myCONST.VORGANGSART_GUTSCHRIFT))
			{
				if (S.isEmpty(recFirmenInfo.get_KREDITOR_NUMMER_cUF_NN("")))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Lieferant hat keine Kreditor-Nummer ... ",true,
																recFirmenInfo.get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(bibALL.get_Vector("NAME1", "ORT")),false)));
				}
			}
			else
			{
				if (S.isEmpty(recFirmenInfo.get_DEBITOR_NUMMER_cUF_NN("")))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Abnehmer hat keine Debitor-Nummer ... ",true,
							recFirmenInfo.get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(bibALL.get_Vector("NAME1", "ORT")),false)));
				}
			}
			
			
			
			return oMV;
		}
		
	}

	
	
	
	private class validPositionenVollstaendig extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VKOPF_RG) 	throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			RECORD_VKOPF_RG  recVKOPF_RG = new RECORD_VKOPF_RG(cID_VKOPF_RG);

			if (recVKOPF_RG.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Beleg wurde wurde bereits gelöscht !")));
			}
			else    //dann die positionen pruefen
			{
				for (Entry<String, RECORD_VPOS_RG>  oEntry: recVKOPF_RG.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg().entrySet())
				{
					RECORD_VPOS_RG recVPOS_RG = oEntry.getValue();

					if (S.isEmpty(recVPOS_RG.get_ANZAHL_cUF()))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Position hat keine Menge !")));
					}
					if (S.isEmpty(recVPOS_RG.get_EINZELPREIS_FW_cUF()))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Position hat keinen Einzelpreis !")));
					}
					if (S.isEmpty(recVPOS_RG.get_STEUERSATZ_cUF()))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Position hat keinen Steuersatz !")));
					}
				}
			}
			
			return oMV;
		}
		
	}

	
	
	private class ownActionSave extends XX_ActionAgent
	{
		private E2_NavigationList  				oNaviList = null;
		private BSRG_K_MASK__ModulContainer  	oMaskContainer = null;
		
		public ownActionSave(E2_NavigationList NaviList,BSRG_K_MASK__ModulContainer MaskContainer) 
		{
			super();
			this.oNaviList = 		NaviList;
			this.oMaskContainer = 	MaskContainer;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			if (!this.oMaskContainer.get_vCombinedComponentMAPs().get_cLAST_FILLED_STATUS().equals(E2_ComponentMAP.STATUS_VIEW))
			{
				E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(this.oMaskContainer,	this.oNaviList);
				try
				{
					oSaveMask.doSaveMask(false);
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Der aktuelle Datensatz wurde gespeichert: "+oSaveMask.get_cActualMaskID_Unformated()), false);
	
						//jetzt nachsehen, ob nachgeladen werden muss, und wenn ja, ob in edit oder view-modus
						RECORD_VKOPF_RG  recRG = new RECORD_VKOPF_RG(oSaveMask.get_cActualMaskID_Unformated());
						if (recRG.is_ABGESCHLOSSEN_NO())
						{
							this.oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,oSaveMask.get_cActualMaskID_Unformated());
						}
						else
						{
							this.oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,oSaveMask.get_cActualMaskID_Unformated());
						}
					}
				}
				catch (myExceptionForUser exx)
				{
					exx.printStackTrace();
					bibMSG.add_MESSAGE(exx.get_ErrorMessage(), false);
				}
			}
		}
	}
	

	/**
	 * nach dem drucken muss nochmal geladen werden, damit beim erneuten speichern aus der maske kein Unterschied festgestellt wird aufgrund des 
	 * Speichervorgangs in der Druckaufbereitung
	 * @author martin
	 *
	 */
	private class ownActionLoadAfterPrint extends XX_ActionAgent
	{
		private BSRG_K_MASK__ModulContainer  	oMaskContainer = null;
		
		public ownActionLoadAfterPrint(BSRG_K_MASK__ModulContainer MaskContainer) 
		{
			super();
			this.oMaskContainer = 	MaskContainer;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			if (this.oMaskContainer.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP()!=null)
			{
				String cID_VKOPF_RG = this.oMaskContainer.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				RECORD_VKOPF_RG recRG = new RECORD_VKOPF_RG(cID_VKOPF_RG);
				if (recRG.is_ABGESCHLOSSEN_NO())
				{
					this.oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID_VKOPF_RG);
				}
				else
				{
					this.oMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,cID_VKOPF_RG);
				}
			}
		}
	}
	
	

	
	
	private class ownActionAgentPrintOrMail extends 	BS_K_LIST_ActionAgent_Mail_Print 
	{
		private BSRG_K_MASK__ModulContainer  	oRG_ModuleContainerMask = null;
		private E2_NavigationList               navi_list = null;
		private boolean  						b_preview = false; 		
		
		public ownActionAgentPrintOrMail(	MyE2_String 					fensterTitel,	
												XX_ActionAgent 					actionAfterPrintOrMail, 
												String 							jasperFileName,
												E2_NavigationList 				navList, 
												BS__SETTING 					setting, 
												String 							parameterNameOfHeadIDField,
												String 							MODUL_KENNER, 
												boolean 						preview,
												BSRG_K_MASK__ModulContainer  	RG_ModuleContainerMask) throws myException
		{
			super(fensterTitel, actionAfterPrintOrMail, jasperFileName, navList,null, setting, parameterNameOfHeadIDField, MODUL_KENNER,preview, true);
			
			this.navi_list = navList;
			this.b_preview = preview;
			
			//2014-07-11: wahlweise manuelle oder automatische Vergabe Rechnungsdatum (variante alt oder neu)
			if (	BSRG_K_LIST_BT_Mail_and_Print.this.BS_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_RECHNUNG) &&
					bibALL.get_RECORD_MANDANT().is_RECHDAT_IST_LEISTUNGSDATUM_YES()
				)
			{
				this.set_oPopup_ContainerBeforeExecute(new PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_ALTERNATIV3(this, preview));
			} else {
			
				this.set_oPopup_ContainerBeforeExecute(new PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_STD( this, preview));
			}
			
			this.oRG_ModuleContainerMask = RG_ModuleContainerMask;
		}

		@Override
		public void complete_JasperHASH(E2_JasperHASH jasperHASH)	throws myException
		{
//			//2014-12-16: hier einen jasper-executer einschleusen, der evtl. orignale zum senden vorbereitet ins archiv legt
//			jasperHASH.get_vExecuters().add(new Jasper_Exe_Process_OriginalFileToArchiv());
			
			//2015-01-07: einsprungpunkt fuer die verarbeitung der Belegoriginale
			jasperHASH.get_vExecuters().add(new __JASPER_EXEC_Pruefe_Original_per_Mail(true));

			
		}


		
		@Override
		public boolean bACTION_BEFORE_START___TO_OVERWRITE() throws myException
		{
			this.set_bExplicitSupressMail(false);
			
			if (!this.b_preview) {
			
				//jetzt nachsehen, ob evtl. alle zu druckenden RG-Belege mit direktmail-vermerkt sind, wenn ja, print/mail-abfrage unterdruecken
				Vector<String> vID_RG = new Vector<String>();
				vID_RG.addAll(this.navi_list.get_vSelectedIDs_Unformated());
				
				boolean all_prints_are_directMail_and_all_Prints_are_first = true;
				
				for (String cID: vID_RG) {
					RECORD_VKOPF_RG  recRG = new RECORD_VKOPF_RG(cID);
					 
					RECORD_ADRESSE_extend adrExt = new RECORD_ADRESSE_extend(recRG.get_UP_RECORD_ADRESSE_id_adresse());
					
					if ((!adrExt.get_bAdresse_is_4_MailingOriginal_RG()) || recRG.is_ABGESCHLOSSEN_YES()) {
						all_prints_are_directMail_and_all_Prints_are_first = false;
						break;
					}
				}
				
				//dann wird die abfrage mail/druck direkt mit druck beantwortet
				this.set_bExplicitSupressMail(all_prints_are_directMail_and_all_Prints_are_first);
			}
			
			return true;
		}

		

		@Override
		public void manipulate_IDS_To_Print_From_Other_Source(Vector<String> vIDs) throws myException 
		{
			if (this.oRG_ModuleContainerMask!=null)
			{
				//fuer druck aus der maske
				//dann ist voellig egal, wie der vIDs aussieht (normalerweiser enthaelt der die ids aus der liste)
				//er wird geloescht und durch die id der maske ersetzt
				
				E2_ComponentMAP  oMap = this.oRG_ModuleContainerMask.get_vCombinedComponentMAPs().get(0);
				
				SQLResultMAP  oResultMap = oMap.get_oInternalSQLResultMAP();
				
				if (oResultMap==null)
				{
					throw new myException(this,"Only in edit- or view-masks allowed!!");
				}
				vIDs.removeAllElements();
				vIDs.add(oResultMap.get_cUNFormatedROW_ID());	
			}

		}
	}

	
	
	
//	/**
//	 * 2019-06-24: wegen verlaengerter faktfrist muss jetzt auch validiert werden, ob bei den drucken forderungen und verbindlichkeiten zusammn selektiert werden,
//	 * dies darf nicht mehr sein
//	 */
//	private class ValidOnlyForderungenOderVerbindlichkeiten extends XX_ActionValidator_NG {
//		
//		private E2_NavigationList 				naviList=null;
//		private BSRG_K_MASK__ModulContainer 	rg_ModuleContainerMask = null;
//		
//		public ValidOnlyForderungenOderVerbindlichkeiten(E2_NavigationList p_naviList,BSRG_K_MASK__ModulContainer pModuleContainerMask) {
//			super();
//			this.naviList = p_naviList;
//			this.rg_ModuleContainerMask = pModuleContainerMask;
//		}
//
//		
//		@Override
//		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
//			
//			VEK<String> ids = getAllIdsToPrint(naviList, rg_ModuleContainerMask);
//			
//			
//			int countForderungen = 0;
//			int countVerbindlichkeiten = 0;
//			
//			for (String id: ids) {
//				Rec21_VkopfRG rk = (Rec21_VkopfRG)new Rec21_VkopfRG()._fill_id(id);
//				if (rk.isForderung()) {
//					countForderungen++;
//				} else {
//					countVerbindlichkeiten++;
//				}
//			}
//			
//			if (countForderungen>0 && countVerbindlichkeiten>0) {
//				return bibMSG.getNewMV()._addAlarm("Im Druckvorgang sind sowohl Forderungen als auch Verbindlicheiten ! Bitte drucken Sie Forderungen und Verbindlichkeiten getrennt!");
//						
//			}
//			
//			return null;
//		}
//		
//	}

	
	
	/**
	 * 2019-06-24: wegen verlaengerter faktfrist muss jetzt auch validiert werden, ob bei den drucken forderungen und verbindlichkeiten zusammn selektiert werden,
	 * dies darf nicht mehr sein
	 */
	private class ValidKeineGemischtenOffenOderGeschlossen extends XX_ActionValidator_NG {
		
		private E2_NavigationList 				naviList=null;
		private BSRG_K_MASK__ModulContainer 	rg_ModuleContainerMask = null;
		
		public ValidKeineGemischtenOffenOderGeschlossen(E2_NavigationList p_naviList,BSRG_K_MASK__ModulContainer pModuleContainerMask) {
			super();
			this.naviList = p_naviList;
			this.rg_ModuleContainerMask = pModuleContainerMask;
		}

		
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			VEK<String> ids = getAllIdsToPrint(naviList, rg_ModuleContainerMask);
			
			
			int countOffen = 0;
			int countGeschlossen = 0;
			
			for (String id: ids) {
				Rec21_VkopfRG rk = (Rec21_VkopfRG)new Rec21_VkopfRG()._fill_id(id);
				if (rk.is_yes_db_val(VKOPF_RG.abgeschlossen)) {
					countOffen++;
				} else {
					countGeschlossen++;
				}
			}
			
			if (countOffen>0 && countGeschlossen>0) {
				return bibMSG.getNewMV()._addAlarm("Im Druckvorgang sind sowohl offene als auch geschlossene Belege ausgewählt. Das ist verboten !");
						
			}
			
			return null;
		}
		
	}

	
	
	/**
	 * prueft jeden beleg, ob er eine eindeutige Kreditversicherungsstruktur im Bezug auf verlaengerte Fakturierungsfristen hat
	 * (alle positionen muessen sich in der gleichen weise verhalten (wenn es in der gesamten rechnung um eine forderung geht)
	 * @author martin
	 * @date 27.06.2019
	 *
	 */
	private class ValidNurBelegeMitEindeutigenKreditversicherungen extends XX_ActionValidator_NG {
		private E2_NavigationList 				naviList=null;
		private BSRG_K_MASK__ModulContainer 	rg_ModuleContainerMask = null;
		
		public ValidNurBelegeMitEindeutigenKreditversicherungen(E2_NavigationList p_naviList,BSRG_K_MASK__ModulContainer pModuleContainerMask) {
			super();
			this.naviList = p_naviList;
			this.rg_ModuleContainerMask = pModuleContainerMask;
		}

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			//zuerst checken, ob es eine forderung ist
			
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			VEK<String> ids = getAllIdsToPrint(naviList, rg_ModuleContainerMask);
			
			for (String id: ids) {
				Rec21_VkopfRG rk = (Rec21_VkopfRG)new Rec21_VkopfRG()._fill_id(id);
				
				//pruefung erfolgt nur bei forderungen
				if (rk.isForderung()) {
					if (rk.getAllFaktFristIdsAndVals().size()==0) {
						mv._addAlarm(S.ms("Beleg mit der ID: ").ut(""+id).t(" zeigt einen Fehler beim Ermitteln des Kreditversicherungs-Status !"));
					} else if (rk.getAllFaktFristIdsAndVals().size()>1) {
						mv._addAlarm(S.ms("Im Beleg mit der ID: ").ut(""+id).t(" scheinen unterschiedliche Kreditversicherungen mit Fakturierungsfrist "
								+ "enthalten zu sein ODER in einer Kreditversicherung überlappende Positionen "));
					}
				}
			}
				
			
			
			return mv;
		} 
		
	}

	
	
	
	
	
	
	private VEK<String> getAllIdsToPrint(E2_NavigationList p_naviList,BSRG_K_MASK__ModulContainer pModuleContainerMask) throws myException {
		VEK<String> ids = new VEK<String>();
		if (pModuleContainerMask!=null) {
			//fuer druck aus der maske
			//dann ist voellig egal, wie der vIDs aussieht (normalerweiser enthaelt der die ids aus der liste)
			//er wird geloescht und durch die id der maske ersetzt
			
			E2_ComponentMAP  oMap = pModuleContainerMask.get_vCombinedComponentMAPs().get(0);
			
			SQLResultMAP  oResultMap = oMap.get_oInternalSQLResultMAP();
			
			if (oResultMap==null) {
				throw new myException(this,"Only in edit- or view-masks allowed!!");
			}
			ids.add(oResultMap.get_cUNFormatedROW_ID());	
		} else {
			ids.addAll(p_naviList.get_vSelectedIDs_Unformated());
		}

		return ids;
	}
	
}
