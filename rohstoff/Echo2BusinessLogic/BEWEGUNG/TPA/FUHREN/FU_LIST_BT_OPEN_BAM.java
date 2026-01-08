package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.ArrayList;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.AM_BasicContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.BAMF_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.BAMF_SQLFieldMAP;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.BT_MASK_Mail_Print_BAMF;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.BT_MASK_Mail_Print_WEIGER;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.BT_MASK_Unlock_BAMF;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.BT_MASK_Unlock_WEIGER;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.Save_BAMF;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;


/*
 * dieser button wird vom XX_ComponentMAP_SubqueryAGENT mit einem passenden label ausgestattet (grau = keine BAM, rot = unbearbeitet, gelb = teils bearbeitet, gruen = fertig
 * und ruft eine Fuhren-BAM-Maske auf 
 */
public class FU_LIST_BT_OPEN_BAM extends MyE2_ButtonInLIST
{

	private String  					ID_VPOS_TPA_FUHRE = null;
	private String  					ID_VPOS_TPA_FUHRE_ORT = null;
	private E2_NavigationList 			NaviListFuhre = null;
	
	private BAMF_MASK_ModulContainer 	oBAM_ModulContainerMASK = null;
	
	public FU_LIST_BT_OPEN_BAM(String cID_VPOS_TPA_FUHRE, String cID_VPOS_TPA_FUHRE_ORT, E2_NavigationList oNaviListFuhre) throws myException
	{
		super();
		
		this.ID_VPOS_TPA_FUHRE = 		cID_VPOS_TPA_FUHRE;
		this.ID_VPOS_TPA_FUHRE_ORT = 	cID_VPOS_TPA_FUHRE_ORT;
		this.NaviListFuhre = 			oNaviListFuhre;
		
		MyE2_String toolTip = new MyE2_String(S.t("Eingabe einer Beanstandungsmeldung zur Fuhre mit der ID: "),S.ut(cID_VPOS_TPA_FUHRE));
		if (S.isFull(cID_VPOS_TPA_FUHRE_ORT)) {
			toolTip = new MyE2_String(S.t("Eingabe einer Beanstandungsmeldung zum Fuhren-Zusatzort mit der ID: "),S.ut(cID_VPOS_TPA_FUHRE_ORT));
		}
		
		this.setToolTipText(toolTip.CTrans());

		this.add_oActionAgent(new ownActionAgent());
		
		
		//jetzt das aussehen des buttons generisch bestimmten
		if (S.isFull(cID_VPOS_TPA_FUHRE))
		{
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
			
			//jetzt nachschauen, ob es eine BAM an der Fuhre gibt, wenn ja, dann das button-icon bestimmen
			RECORD_FBAM  recBAM = recFuhre.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre().size()==0?null:recFuhre.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre().get(0);
			
			if (recBAM == null)
			{
				if (recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre().size()>0 ||
					recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre().size()>0)	
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_schwarz.png"));
				}
				else
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_grau.png"));
				}
			}
			else
			{
				//jetzt die farbe bestimmen
				if (recBAM.is_ABGESCHLOSSEN_BEHEBUNG_YES()&&recBAM.is_ABGESCHLOSSEN_KONTROLLE_YES())
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_gruen.png"));
				}
				else if (recBAM.is_ABGESCHLOSSEN_BEHEBUNG_YES()&&recBAM.is_ABGESCHLOSSEN_KONTROLLE_NO())
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_gelb.png"));
				}
				else
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_rot.png"));
				}
			}

		}
		else if (S.isFull(cID_VPOS_TPA_FUHRE_ORT))
		{
			
			RECORD_VPOS_TPA_FUHRE_ORT  recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT);
			
			//jetzt nachschauen, ob es eine BAM an der Fuhre gibt, wenn ja, dann das button-icon bestimmen
			RECORD_FBAM  recBAM = recFuhreOrt.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre_ort().size()==0?null:recFuhreOrt.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre_ort().get(0);
			
			if (recBAM == null)
			{
				if (recFuhreOrt.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort().size()>0)	
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_schwarz.png"));
				}
				else
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_grau.png"));
				}
			}
			else
			{
				//jetzt die farbe bestimmen
				if (recBAM.is_ABGESCHLOSSEN_BEHEBUNG_YES()&&recBAM.is_ABGESCHLOSSEN_KONTROLLE_YES())
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_gruen.png"));
				}
				else if (recBAM.is_ABGESCHLOSSEN_BEHEBUNG_YES()&&recBAM.is_ABGESCHLOSSEN_KONTROLLE_NO())
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_gelb.png"));
				}
				else
				{
					this.setIcon(E2_ResourceIcon.get_RI("bam_rot.png"));
				}
			}

			
		}
		
		
		
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU_LIST_BT_OPEN_BAM oThis = FU_LIST_BT_OPEN_BAM.this;
			
			RECORD_VPOS_TPA_FUHRE 		recFuhre = null;
			RECORD_VPOS_TPA_FUHRE_ORT 	recFuhreOrt = null;
			
			if (S.isFull(oThis.ID_VPOS_TPA_FUHRE_ORT))
			{
				recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(oThis.ID_VPOS_TPA_FUHRE_ORT);
				recFuhre = recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
			}
			else if (S.isFull(oThis.ID_VPOS_TPA_FUHRE))
			{
				recFuhre = new RECORD_VPOS_TPA_FUHRE(oThis.ID_VPOS_TPA_FUHRE);
			}
			else
			{
				throw new myException(this,"Error, false defining of BAM-Button !");
			}
				
			if (recFuhre.is_DELETED_YES() || (recFuhreOrt!=null && recFuhreOrt.is_DELETED_YES()))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre/der Fuhrenort ist bereits gelöscht worden !"));
			}
			if (recFuhre.is_IST_STORNIERT_YES())
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre wurde storniert !"));
			}
			if (recFuhre.is_FUHRE_KOMPLETT_NO())
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre wurde noch nicht komplett ausgefüllt!"));
			}

			if (bibMSG.get_bHasAlarms())
			{
				return;
			}
			oThis.oBAM_ModulContainerMASK = new BAMF_MASK_ModulContainer(oThis.NaviListFuhre, new BAMF_SQLFieldMAP(oThis.ID_VPOS_TPA_FUHRE,oThis.ID_VPOS_TPA_FUHRE_ORT));
			
			BT_MASK_Unlock_BAMF   	oButtonToUnlockBAM = 	new BT_MASK_Unlock_BAMF(oBAM_ModulContainerMASK);
			BT_MASK_Unlock_WEIGER   oButtonToUnlockWeiger = new BT_MASK_Unlock_WEIGER(oBAM_ModulContainerMASK);

			MyE2_Button oButtonSave = new MyE2_Button(E2_ResourceIcon.get_RI("save.png"),true);
			MyE2_Button oButtonCancel = new MyE2_Button(E2_ResourceIcon.get_RI("cancel.png"),true);
			oButtonSave.add_oActionAgent(new ActionAgent_Save());
			oButtonCancel.add_oActionAgent(new ActionAgent_Cancel());



			//darf der anwender bearbeiten ?
			MyE2_MessageVector oMV_BenutzerDarf =(new E2_ButtonAUTHValidator_AUTO("BAM_MASKE_AUFRUFEN")).isValid(oThis);
			boolean bDarfBearbeiten = !oMV_BenutzerDarf.get_bHasAlarms();

			
			
			//gibt es eine BAM:
			
			RECORD_FBAM  recFBAM = null;
			
			if (recFuhreOrt != null)
			{
				if (recFuhreOrt.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre_ort().size()>0)
				{
					recFBAM = recFuhreOrt.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre_ort().get(0);
				}
			}
			else
			{
				if (recFuhre.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre().size()>0)
				{
					recFBAM = recFuhre.get_DOWN_RECORD_LIST_FBAM_id_vpos_tpa_fuhre().get(0);
				}
			}
			
			if (recFBAM == null && bDarfBearbeiten)   				//dann neu
			{
				oBAM_ModulContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,null);
				E2_ComponentGroupHorizontal oCompRow = new E2_ComponentGroupHorizontal(0,
															oButtonSave,
															new buttonSaveAndReload(),   //2015-06-09: speichern und neu laden
															oButtonCancel,
															new BT_MASK_Mail_Print_BAMF(oBAM_ModulContainerMASK,null,false),
															new BT_MASK_Mail_Print_WEIGER(oBAM_ModulContainerMASK,null,false),
															oButtonToUnlockBAM, 
															oButtonToUnlockWeiger,
															new ownAttachementButtonInBAM_Mask(),
															new ownButtonCollectAttachements(),
															E2_INSETS.I_0_2_10_2);
				oBAM_ModulContainerMASK.get_oRowForButtons().add(oCompRow);
				
				
			}
			else if (recFBAM == null && !bDarfBearbeiten)   		//verboten
			{
				bibMSG.add_MESSAGE(oMV_BenutzerDarf);
				return;
			}
			else if (recFBAM != null && bDarfBearbeiten)				//dann editieren
			{
				
				oBAM_ModulContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,recFBAM.get_ID_FBAM_cUF());
				E2_ComponentGroupHorizontal oCompRow = new E2_ComponentGroupHorizontal(0,
																	oButtonSave,
																	new buttonSaveAndReload(),   //2015-06-09: speichern und neu laden
																	oButtonCancel,
																	new BT_MASK_Mail_Print_BAMF(oBAM_ModulContainerMASK,null,false),
																	new BT_MASK_Mail_Print_WEIGER(oBAM_ModulContainerMASK,null,false),
																	oButtonToUnlockBAM, 
																	oButtonToUnlockWeiger,
																	new ownAttachementButtonInBAM_Mask(),
																	new ownButtonCollectAttachements(),
																	E2_INSETS.I_0_2_10_2);
				oBAM_ModulContainerMASK.get_oRowForButtons().add(oCompRow);
				

			}
			else if (recFBAM != null && !bDarfBearbeiten)				//dann nur gucken
			{
				
				oBAM_ModulContainerMASK.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,recFBAM.get_ID_FBAM_cUF());
				E2_ComponentGroupHorizontal oCompRow = new E2_ComponentGroupHorizontal(0,
																	oButtonCancel,
																	E2_INSETS.I_0_2_10_2);
				oBAM_ModulContainerMASK.get_oRowForButtons().add(oCompRow);
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Öffnen der BAM !!"));
			}
			

			if (bibMSG.get_bIsOK())
			{
				
				E2_ComponentMAP oMap = oBAM_ModulContainerMASK.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
				
				oBAM_ModulContainerMASK.CREATE_AND_SHOW_POPUPWINDOW(null,null,
						oMap.get_oInternalSQLResultMAP()==null?new MyE2_String("NEUE Beanstandungsmeldung eingeben !"):new MyE2_String("Bestehende Beanstandungsmeldung bearbeiten !"));

			}
			
		}
	}
	
	
	
	/*
	 * action-agent fuer maskenspeicher-button
	 */
	private class ActionAgent_Save extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				FU_LIST_BT_OPEN_BAM oThis = FU_LIST_BT_OPEN_BAM.this;

				RECORD_VPOS_TPA_FUHRE 		recFuhre = null;
				RECORD_VPOS_TPA_FUHRE_ORT 	recFuhreOrt = null;
				
				if (S.isFull(oThis.ID_VPOS_TPA_FUHRE_ORT))
				{
					recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(oThis.ID_VPOS_TPA_FUHRE_ORT);
					recFuhre = recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
				}
				else if (S.isFull(oThis.ID_VPOS_TPA_FUHRE))
				{
					recFuhre = new RECORD_VPOS_TPA_FUHRE(oThis.ID_VPOS_TPA_FUHRE);
				}
				
				
				if (new Save_BAMF(oThis.oBAM_ModulContainerMASK,null).get_bISOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Beanstandungsmeldung wurde gespeichert !"));
					
					/*
					 * liste aktualisieren und die ansicht neu einlesen
					 */
					
					if (! oThis.NaviListFuhre.Refresh_ComponentMAP(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),E2_ComponentMAP.STATUS_VIEW))
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Neuaufbau der Listenzeile !"));

					oThis.NaviListFuhre.Mark_ID_IF_IN_Page(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
					oThis.oBAM_ModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				return;
			}

		}	
	}

	
	
	/*
	 * action-agent fuer maskenabbruch-button
	 */
	private class ActionAgent_Cancel extends XX_ActionAgent
	{
		public ActionAgent_Cancel()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_LIST_BT_OPEN_BAM oThis = FU_LIST_BT_OPEN_BAM.this;
			oThis.oBAM_ModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Bearbeiten der BAM abgebrochen ..."));
		}	
	}

	
	
	private class buttonSaveAndReload extends MyE2_Button {

		public buttonSaveAndReload() {
			super(E2_ResourceIcon.get_RI("save_edit.png"), true);
			this.setToolTipText(new MyE2_String("Maske speichern, anschließend weiterbearbeiten").CTrans());
			this.add_oActionAgent(new actionSaveAndReload());
		}

		private class actionSaveAndReload extends XX_ActionAgent {
			
			public void executeAgentCode(ExecINFO oExecInfo) {
				
				FU_LIST_BT_OPEN_BAM oThis = FU_LIST_BT_OPEN_BAM.this;

				try	{
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(oThis.oBAM_ModulContainerMASK,	null);
					try {
						oSaveMask.doSaveMask(true);
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Der aktuelle Datensatz wurde gespeichert: "+oSaveMask.get_cActualMaskID_Unformated()), false);
						}
					} catch (myExceptionForUser exx) {
						exx.printStackTrace();
						bibMSG.add_MESSAGE(exx.get_ErrorMessage(), false);
					}
				} catch (myException ex) {
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}
	
	
	
	private class ownAttachementButtonInBAM_Mask extends MyE2_Button {
		public ownAttachementButtonInBAM_Mask() {
			super(E2_ResourceIcon.get_RI("attach_mini.png"), E2_ResourceIcon.get_RI("leer.png"));
			this.add_GlobalAUTHValidator_AUTO("ZUSATZDATEIEN");
			this.setToolTipText(new MyE2_String("Anhänge zu dieser BAM bearbeiten ...").CTrans());
			this.add_oActionAgent(new ownActionAgent());
		}
		
		private class ownActionAgent extends XX_ActionAgent {
			
			
			public ownActionAgent()	{
				super();
			}

			public void executeAgentCode(ExecINFO oExecInfo)	{
				
				FU_LIST_BT_OPEN_BAM 			ooThis = FU_LIST_BT_OPEN_BAM.this;
				
				//welche id_fbam ?
				if (ooThis.oBAM_ModulContainerMASK.get_vCombinedComponentMAPs().get(0).get_bIs_Neueingabe()) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte die Beanstandungsmeldung zuerst abspeichern, dann können Anhänge erfaßt werden !")));
				} else {
					try
					{
						String cID = 		ooThis.oBAM_ModulContainerMASK.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_UnFormatedValue(_DB.FBAM$ID_FBAM);
						String cTableName = _DB.FBAM;
						AM_BasicContainer 	oPopUp = new AM_BasicContainer(		cTableName,
																				cID,
																				E2_MODULNAME_ENUM.MODUL.NAME_MODUL_FBAM_LIST.name(),
																				true);
					
						oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Zusatzdateien ..."));
					}
					catch (myException ex)
					{
						ex.printStackTrace();
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error opening Attachment-Window: "));
						bibMSG.add_MESSAGE(ex.get_ErrorMessage());
					}
				}
			}

		}
	}

	
	private class ownButtonCollectAttachements extends MyE2_Button {
		public ownButtonCollectAttachements() {
			super(E2_ResourceIcon.get_RI("camera_import.png"),true);
			this.add_GlobalAUTHValidator_AUTO("ZUSATZDATEIEN");
			boolean bFUO = S.isFull(FU_LIST_BT_OPEN_BAM.this.ID_VPOS_TPA_FUHRE_ORT);
			this.setToolTipText(new MyE2_String("Befundungs-Fotos aus aus "+(bFUO?"dem FuhrenOrt":"der Fuhre")+" an die BAM anfügen ...").CTrans());
			this.add_oActionAgent(new ownActionAgent());
		}
		
		private class ownActionAgent extends XX_ActionAgent {
			boolean b_aktiv = true;

			public ownActionAgent()	{
				super();
			}

			public void executeAgentCode(ExecINFO oExecInfo)	{
				
				FU_LIST_BT_OPEN_BAM 			ooThis = FU_LIST_BT_OPEN_BAM.this;
				
				ArrayList<String> 				alBereitsVorhanden = new ArrayList<String>();
				
				if (b_aktiv) {
	
					//welche id_fbam ?
					if (ooThis.oBAM_ModulContainerMASK.get_vCombinedComponentMAPs().get(0).get_bIs_Neueingabe()) {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte die Beanstandungsmeldung zuerst abspeichern, dann können Anhänge erfaßt werden !")));
					} else {
						try {
							String 		id_FBAM = 		ooThis.oBAM_ModulContainerMASK.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_UnFormatedValue(_DB.FBAM$ID_FBAM);
							RECORD_FBAM recFB = 	new RECORD_FBAM(id_FBAM);
							
							RECLIST_ARCHIVMEDIEN_ext rlArch = new RECLIST_ARCHIVMEDIEN_ext(_DB.FBAM,id_FBAM,null,null);
							for (RECORD_ARCHIVMEDIEN  recAr: rlArch) {
								alBereitsVorhanden.add(new RECORD_ARCHIVMEDIEN_ext(recAr).get__cCompletePathAndFileName());
							}
							
							if (S.isFull(recFB.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(""))) {
								RECORD_VPOS_TPA_FUHRE_ORT 	recORT = recFB.get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort();
								this.Connect_befundungs_anhaenge_To_BAM(new ownRECLIST_ARCHIVMEDIEN_befundung(recORT), id_FBAM,alBereitsVorhanden);
							} else if (S.isFull(recFB.get_ID_VPOS_TPA_FUHRE_cUF_NN(""))) {
								RECORD_VPOS_TPA_FUHRE 	recFU = recFB.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
								this.Connect_befundungs_anhaenge_To_BAM(new ownRECLIST_ARCHIVMEDIEN_befundung(recFU), id_FBAM,alBereitsVorhanden);
							}
						}
						catch (myException ex)
						{
							ex.printStackTrace();
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error Collecting Attachments!"));
							bibMSG.add_MESSAGE(ex.get_ErrorMessage());
						}
					}
				}

			}


			private int[] Connect_befundungs_anhaenge_To_BAM(ownRECLIST_ARCHIVMEDIEN_befundung rlArchivmedien_befund, String ID_FBAM, ArrayList<String> alBereitsVorhanden) throws myException {
				int[] iRueck = new int[2];
				iRueck[0]=0;  // zaehler fuer erfolgreich
				iRueck[1]=0;  // zaehler fuer uebersprungen
				if (rlArchivmedien_befund.get_vKeyValues().size()>0) {
						
					for (RECORD_ARCHIVMEDIEN  recAM: rlArchivmedien_befund) {
						if (!alBereitsVorhanden.contains(new RECORD_ARCHIVMEDIEN_ext(recAM).get__cCompletePathAndFileName())) {
							//jetzt an die BAM anhaengen
							new RECORD_ARCHIVMEDIEN_ext(recAM).connectToAdditionalEntry(_DB.FBAM.substring(3), ID_FBAM, true);
							alBereitsVorhanden.add(new RECORD_ARCHIVMEDIEN_ext(recAM).get__cCompletePathAndFileName());
							iRueck[0]++;
						} else {
							iRueck[1]++;
						}
					}
					
					bibMSG.add_MESSAGE(
							new MyE2_Info_Message(
									new MyE2_String(S.t("Erfolgreich angehängt: "),
													S.ut(""+iRueck[0]),
													S.ut(" // "),
													S.t("Bereits vorhanden: "),
													S.ut(""+iRueck[1]))));
					
				} else {
					bibMSG.add_MESSAGE(
							new MyE2_Alarm_Message(
									new MyE2_String(S.t(rlArchivmedien_befund.bIstFuhre?"Diese Fuhre ":"Dieser Fuhrenort"),S.t(" besitzt keine Anhänge aus Befundungen  !"))));
				}
				return iRueck;
			}

			
			
			/**
			 * spezielle reclist-klasse, die alle archivmedien mit ursprung befundung aus einer fuhre/fuhreort zieht
			 * @author martin
			 *
			 */
			private class ownRECLIST_ARCHIVMEDIEN_befundung extends RECLIST_ARCHIVMEDIEN_ext {

				public boolean bIstFuhre = true;
				
				public ownRECLIST_ARCHIVMEDIEN_befundung(RECORD_VPOS_TPA_FUHRE recFuhre) throws myException {
					super(	_DB.VPOS_TPA_FUHRE, 
							recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(), 
							Archiver_CONST.MEDIENKENNER.IMPORT_ABZUG);
				}
			
				public ownRECLIST_ARCHIVMEDIEN_befundung(RECORD_VPOS_TPA_FUHRE_ORT recFUO) throws myException {
					super(	_DB.VPOS_TPA_FUHRE_ORT, 
							recFUO.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), 
							Archiver_CONST.MEDIENKENNER.IMPORT_ABZUG);
					this.bIstFuhre=false;
				}
			}
		}
	}

	
	
	
}
