package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingWindowSize;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_ModuleContainerMask;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_RB_DataobjectsCollector;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.archive.Archiver_RG;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.pdf.PDF_Overlay_Factory_4_OriginalDownload;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;

public class ES_bt_StartEmailSendung extends RB_bt_New {

	
	private AM_BasicContainer 		popupWindowContainerArchivmedien = null;
	
	//container mit email-sende-eintraegen
	private ES_ModuleContainerMask 	actual_ES_MaskContainer = null;
	private E2_Button               bt_save_edit = null;      //button, um die maske zu speichern und offen zu halten
	
	public ES_bt_StartEmailSendung(AM_BasicContainer pop_upWindowContainer) throws myException {
		super(E2_ResourceIcon.get_RI("email.png"),true);
		this.popupWindowContainerArchivmedien = pop_upWindowContainer;
		this.set_ToolTip4Button(new MyE2_String("Ausgewählte Archivmedien für eMail-Versendung vorbereiten .."));
		
		this.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.GENERATE_EMAIL));
		
		//this.add_GlobalAUTHValidator_PROGRAMM_WIDE(ES_CONST.get_eMailSendValidationString4EmailButtons(pop_upWindowContainer.get_table_NAME()));
		
		//zusatzbutton rein (direkt senden)
		this.get_grid4MaskExternal().removeAll();
		this.get_grid4MaskExternal().add(new bt_sendEmail(),E2_INSETS.I(1,1,1,1));
		
		//hier einen button zum speichern und offenhalten zufuegen
		this.get_grid4MaskExternal()._setSize(50,130);
		this.bt_save_edit  = new E2_Button()._image(E2_ResourceIcon.get_RI("save_edit.png"), E2_ResourceIcon.get_RI("save_edit__.png"))._ttt(new MyE2_String("Die Maske speichern und offenhalten"));
		this.get_grid4MaskExternal().add(this.bt_save_edit, E2_INSETS.I(2,2,2,2));
		this.bt_save_edit.add_oActionAgent(new ownSaveEdit());

		
		this.add_oActionAgent(new ownActionAgentShow_EmailSendTab());
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		this.actual_ES_MaskContainer = new ES_ModuleContainerMask(null
												, this.popupWindowContainerArchivmedien!=null?this.popupWindowContainerArchivmedien.get_ArchivBaseTable():null
												, this.popupWindowContainerArchivmedien!=null?this.popupWindowContainerArchivmedien.get_ArchiveIdTable():null
												);
		return this.actual_ES_MaskContainer;
	}
	
	@Override
	public RB_DataobjectsCollector generate_DataObjects4New() throws myException {
		
		Vector<String>  vSelected_Archivmedien = new Vector<String>();
		vSelected_Archivmedien.addAll(this.popupWindowContainerArchivmedien.get_navigationList_ArchivFiles().get_vSelectedIDs_Unformated());
		if (vSelected_Archivmedien.size()==0) { 
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst die gewünschten Dateien auswählen zum verschicken !")));
			return null;
		} else {
			//jetzt nachsehen, ob eine ausgewaehlte datei den originalschalter enthaehlt, wenn ja kopieren mit stempel
			Vector<String>  vUebergabeIDs = new Vector<String>();
			for (String id_archivmedien: vSelected_Archivmedien) {
				RECORD_ARCHIVMEDIEN_ext recAr = new RECORD_ARCHIVMEDIEN_ext(new RECORD_ARCHIVMEDIEN(id_archivmedien));
				if (recAr.is_IST_ORIGINAL_YES()) {
					myTempFile t = new PDF_Overlay_Factory_4_OriginalDownload().generateCopyFromOriginal(recAr.get__cCompletePathAndFileName());
					Archiver_RG archiver = new Archiver_RG();
					String cNewFileName = archiver.copyFilenameToNextFree(t.getFileName(), recAr.get_DATEINAME_Orig());
					t.getFile().delete();
					
					MyE2_MessageVector mv_Sammler = new MyE2_MessageVector();
					MyRECORD_NEW recNew =  recAr.get_Record_NEW_FilledWithActualValues(mv_Sammler, true, true);
					recNew.set_NewValueForDatabase(_DB.ARCHIVMEDIEN$IST_ORIGINAL, 	"N");
					recNew.set_NewValueForDatabase(_DB.ARCHIVMEDIEN$MEDIENKENNER, 	Archiver_CONST.MEDIENKENNER.ORIG_KOPIE_EMAIL.get_DB_WERT());
					recNew.set_NewValueForDatabase(_DB.ARCHIVMEDIEN$DATEINAME, 		cNewFileName);
					recNew.set_NewValueForDatabase(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER,"");
					
					MyRECORD recordKopieArchivmedien = recNew.do_WRITE_NEW_RECORD(mv_Sammler);
					if (!(mv_Sammler.get_bIsOK() && recordKopieArchivmedien != null)) {
						throw new myException(this,"Error createing copy-dataset !");
					}
					this.popupWindowContainerArchivmedien.get_navigationList_ArchivFiles()._REBUILD_COMPLETE_LIST("");
					vUebergabeIDs.add(recordKopieArchivmedien.get_UnFormatedValue(_DB.ARCHIVMEDIEN$ID_ARCHIVMEDIEN));
				} else {
					vUebergabeIDs.add(id_archivmedien);
				}
			}
			
			ES_RB_DataobjectsCollector dataCont = new ES_RB_DataobjectsCollector(null, RB__CONST.MASK_STATUS.NEW, vUebergabeIDs);
			return dataCont;
		}
	}

	
	/**
	 * sorgt dafuer, dass wenn er Tab eMail-Sendungen noch nicht vorhanden ist, dieser ab dem klick angezeigt wird
	 * @author martin
	 *
	 */
	private class ownActionAgentShow_EmailSendTab extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (!ES_bt_StartEmailSendung.this.popupWindowContainerArchivmedien.get_bStatusWithTabbedPane()) {
				ES_bt_StartEmailSendung.this.popupWindowContainerArchivmedien.get_es_LIST_BasicModuleContainer().set_Visible_in_AM_BasicContainer(true);
				ES_bt_StartEmailSendung.this.popupWindowContainerArchivmedien.render_Window();
				new E2_UserSettingWindowSize().prepareContainer(ES_bt_StartEmailSendung.this.popupWindowContainerArchivmedien);
			}
		}
		
	}
	
	
	
	/**
	 * button zum direkten senden aus der neueingabe:
	 *    erst speichern
	 *    dann senden (wenn speichern ok)
	 */
	private class bt_sendEmail extends MyE2_Button {

		public bt_sendEmail() {
			super(E2_ResourceIcon.get_RI("email.png"));
			this.setToolTipText(new MyE2_String("eMail versenden ...").CTrans());
			this.add_oActionAgent(new actionSaveAndSend());
//			String cTableNameBase = new Archiver_Normalized_Tablename(ES_bt_StartEmailSendung.this.popupWindowContainerArchivmedien.get_table_NAME()).get_TableBaseName();
			//this.add_GlobalAUTHValidator_PROGRAMM_WIDE(ES_CONST.get_eMailSendValidationString4EmailButtons(cTableNameBase));
			this.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.SENDMAIL));
		}

		
		private class actionSaveAndSend extends XX_ActionAgent {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				ES_bt_StartEmailSendung oThis = ES_bt_StartEmailSendung.this;
				
				MyE2_MessageVector  oMV = oThis.actual_ES_MaskContainer.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true);

				if (oMV.get_bIsOK()) {
					//dann die neu geschriebene RECORD_EMAIL_SEND beschaffen
					String id_EMAIL_SEND_new = bibDB.EinzelAbfrage("SELECT "+_DB.EMAIL_SEND$$SEQ_CURR+" FROM DUAL");
					
					MyLong longTest = new MyLong(id_EMAIL_SEND_new);
					
					if (longTest.get_bOK()) {
						ES_RB_DataobjectsCollector dataCont = new ES_RB_DataobjectsCollector(longTest.get_cUF_LongString(), RB__CONST.MASK_STATUS.EDIT);
						oMV.add_MESSAGE(oThis.actual_ES_MaskContainer.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(dataCont));
						
						if (oMV.get_bIsOK()) {
							RECORD_EMAIL_SEND_ext recSend = new RECORD_EMAIL_SEND_ext(new RECORD_EMAIL_SEND(longTest.get_lValue()));
							oMV.add_MESSAGE(recSend.Send_Email(null,recSend.get_MUST_BE_ATTACHMENT_LIST(oMV)));
							if (oMV.get_bIsOK()) {
								ES_bt_StartEmailSendung.this.actual_ES_MaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							}
						} 
						
						bibMSG.add_MESSAGE(oMV);
						
					} else {
						throw new myException(this,"Error sending eMail ... (2)");
					}
				}
				bibMSG.add_MESSAGE(oMV);
			}
		}
	}



	@Override
	public void define_Actions_4_saveButton(RB_bt_New btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	}

	@Override
	public void define_Actions_4_CloseButton(RB_bt_New btNewInList,	RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)	throws myException {
		bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	}

	
	private class ownSaveEdit extends RB_actionStandardSaveAndReopen {
		
		public ownSaveEdit() throws myException {
			super(new RB_KM(_TAB.email_send));
		}

		@Override
		public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_record) throws myException {
			return new ES_RB_DataobjectsCollector(id_record, RB__CONST.MASK_STATUS.EDIT);		
		}
		
		@Override
		public RB_ModuleContainerMASK get_RB_ModuleContainerMASK() throws myException {
			return ES_bt_StartEmailSendung.this.get_MaskContainer();
		}

		
	}
	

}