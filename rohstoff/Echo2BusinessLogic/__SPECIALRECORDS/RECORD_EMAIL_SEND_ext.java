package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES__verify_table_name_and_id;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_4_eMail_Status;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_RECORD_VKOPF_RG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.IF_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_4;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CycleBeforeSendOriginalMail;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailException;
import panter.gmbh.indep.mail.SendMail_STD;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL.RGOM___Attach_Missing_and_Collect_Relevant_Attachements_4_MAIL;

public class RECORD_EMAIL_SEND_ext extends RECORD_EMAIL_SEND {

	public RECORD_EMAIL_SEND_ext(RECORD_EMAIL_SEND recordOrig)
	{
		super(recordOrig);
	}
	
	public RECORD_EMAIL_SEND_ext() throws myException {
		super();	
	}

	
	/**
	 * 
	 * @return s id_string when every attached file belongs to same basetable otherwise null
	 * @throws myException
	 */
	public String get_idTable_when_all_relatedAttachments_to_same_basetable(String TableBaseName) throws myException {
		
		String id = null;
		
		String tableBaseName = new Archiver_Normalized_Tablename(TableBaseName).get_TableBaseName();
		
		//nachsehen, ob ein originalfile dranhaengt und zu welcher rechnungs-id es gehoert
		RECLIST_EMAIL_SEND_ATTACH rlSendAttach = this.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send();
		
		for (RECORD_EMAIL_SEND_ATTACH  recATT: rlSendAttach) {
			if (recATT.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_TABLENAME_cUF_NN("-").equals(tableBaseName)) {
				if (S.isEmpty(id)) {
					//erster durchlauf
					id =recATT.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_ID_TABLE_cUF_NN("-");
				} else {
					if (!id.equals(recATT.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_ID_TABLE_cUF_NN("--"))) {
						return null;   //dann ist sind die der mail zugeschlagene archivmedien nicht alle dem datensatz zugeordnet
					}
				}
				
			} else {
				return null;         //nur an rechnungskopf angehaengt sind erlaubt
			}
		}
		return id;   //wenn hier != null, dann haengen alle der eMailsend-aktion angehaengten arvchivmedien am der gleichen basetable-satz
	}


	
	/**
	 * 
	 * @return true when one of the attachment-archivmedien is original
	 * @throws myException
	 */
	public boolean get_containsOriginalAttachFile() throws myException {
		
		boolean bRueck = false;
		
		RECLIST_EMAIL_SEND_ATTACH rlSendAttach = this.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send();
		
		for (RECORD_EMAIL_SEND_ATTACH  recATT: rlSendAttach) {
			if (recATT.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().is_IST_ORIGINAL_YES()) {
				bRueck = true;
			}
		}
		return bRueck;  
	}


	public ES_CONST.SEND_STATUS get_SendStatus() throws myException{
		RECLIST_EMAIL_SEND_TARGETS rlSendTargets = this.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send();
		
		int iToSend = rlSendTargets.get_vKeyValues().size();
		int iSendDone = 0;
		int iSendToDo = 0;
		
		for (RECORD_EMAIL_SEND_TARGETS recTarget: rlSendTargets) {
			if (recTarget.is_SEND_OK_YES()) {
				iSendDone++;
			} else {
				iSendToDo++;
			}
		}
		
		if (iToSend==0) {
			return ES_CONST.SEND_STATUS.SEND_ALL;
		} else {
			if (iSendToDo==iToSend) {
				return ES_CONST.SEND_STATUS.SEND_NONE;
			} else if (iSendDone==iToSend) {
				return ES_CONST.SEND_STATUS.SEND_ALL;
			} else {
				return ES_CONST.SEND_STATUS.SEND_PART;
			}
		}
	}

	public MyE2_Label  get_SendStatusLabel() throws myException {
		E2_ResourceIcon icon = null;
		MyE2_String cTooltips = null;
		if (this.get_bContainsOriginalMedium()) {
			if (this.get_SendStatus()==ES_CONST.SEND_STATUS.SEND_ALL) {
				icon = E2_ResourceIcon.get_RI("listlabel_green_orig.png");
				cTooltips = new MyE2_String("Mail mit Originaldatei wurde an alle Zieladressen versandt");
			} else if (this.get_SendStatus()==ES_CONST.SEND_STATUS.SEND_PART) {
				icon = E2_ResourceIcon.get_RI("listlabel_yellow_orig.png");
				cTooltips = new MyE2_String("Mail mit Originaldatei wurde an einen Teil der Zieladressen versandt");
			} else {
				icon = E2_ResourceIcon.get_RI("listlabel_red_orig.png");
				cTooltips = new MyE2_String("Mail mit Originaldatei wurde noch nicht versandt");
			}
		} else {
			if (this.get_SendStatus()==ES_CONST.SEND_STATUS.SEND_ALL) {
				icon = E2_ResourceIcon.get_RI("listlabel_green.png");
				cTooltips = new MyE2_String("Mail wurde an alle Zieladressen versandt");
			} else if (this.get_SendStatus()==ES_CONST.SEND_STATUS.SEND_PART) {
				icon = E2_ResourceIcon.get_RI("listlabel_yellow.png");
				cTooltips = new MyE2_String("Mail wurde an einen Teil der Zieladressen versandt");
			} else {
				icon = E2_ResourceIcon.get_RI("listlabel_red.png");
				cTooltips = new MyE2_String("Mail wurde noch nicht versandt");
			}
		}
		MyE2_Label lab = new MyE2_Label(icon);
		lab.setToolTipText(cTooltips.CTrans());
		return lab;
		
	}
	
	
	public boolean get_bContainsOriginalMedium() throws myException {
		boolean bRueck = false;
		
		for (RECORD_EMAIL_SEND_ATTACH recAtt: this.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send()) {
			if (recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().is_IST_ORIGINAL_YES()) {
				bRueck = true;
			}
		}
		return bRueck;
	}
	
	
	public boolean get_bAllowedToDelete() throws myException {
		boolean bRueck = true;
		
		//verboten, wenn bereits versandt wurde oder wenn ein original-medium beteiligt ist
		if ((this.get_SendStatus()!=ES_CONST.SEND_STATUS.SEND_NONE) || this.get_bContainsOriginalMedium()) {
			bRueck = false;
		}
		
		return bRueck;
	}
	

	
	public int get_NumberOfTargets() throws myException {
		return this.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send().size();
	}
	
	
	
	/**
	 * 
	 * @param oMV  (sammelt fehlermeldungen)
	 * @param vArchivmedienRG_MUST_BE_ATTACHED (can be null, otherwise is checked) 
	 * @return true wenn keine fehler und der versand erledigt ist 
	 * @throws myException
	 */
	public void check_and_send_email_if_possible(MyE2_MessageVector oMV, ArrayList<RECORD_ARCHIVMEDIEN> vArchivmedienRG_MUST_BE_ATTACHED) throws myException {
		
		//prueft auf vorhandensein und gleichheit der ursprungstabelle der angehaengten Attachments
		String[] nameAndId = this.get_Tablename_and_ID();
		if (nameAndId == null || S.isEmpty(nameAndId[0]) || S.isEmpty(nameAndId[1])) {
			throw new myException(this,"Sendemail without targets is sensless or with Attaments from other tables is forbidden!");
		}
		String cTABLE_BASENAME = 	nameAndId[0];
		String cID_TABLE =  		nameAndId[1];

		
		
		if (cTABLE_BASENAME.equals(_DB.VKOPF_RG.substring(3)) && this.get_bContainsOriginalMedium()) {
			//dann erfolgt der komplette pruefzyklus der RG-orignal-versendung
			CHK_RECORD_VKOPF_RG recRG = new CHK_RECORD_VKOPF_RG(new RECORD_VKOPF_RG(cID_TABLE));
			CHK__CycleBeforeSendOriginalMail chk_complete = new CHK__CycleBeforeSendOriginalMail(recRG);
			
			RECORD_EMAIL_SEND_ext recSendFromCheck = chk_complete.do_checkCycle(oMV, false);
			
			if (recSendFromCheck!=null) {
				if (!(recSendFromCheck.get_ID_EMAIL_SEND_cUF().equals(this.get_ID_EMAIL_SEND_cUF()))) {
					//sollte nicht vorkommen
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zum Rechnungs/Gutschrifts-Kopfsatz:",true,(""+cID_TABLE),false, " existieren mehrere Orignal-eMail-Sendungen!",true)));
				}
			}
			
			if (oMV.get_bIsOK() && recSendFromCheck!=null) {
				oMV.add_MESSAGE(recSendFromCheck.Send_Email(null,vArchivmedienRG_MUST_BE_ATTACHED));
			}
			
			return;
		}
		
		//alle anderen faelle werden im standardmodus behandelt
		CHK_4_eMail_Status check_Standards = new CHK_4_eMail_Status(this);
		IF_STATUS status = check_Standards.check_status(oMV);
		if (oMV.get_bIsOK()) {
			if (status == STATUS_CHK_4.MAIL_IS_CORRECT_SENT) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die eMail wurde bereits komplett verschickt !")));
			} else if (status == STATUS_CHK_4.MAIL_IS_CORRECT_UNSENT) {
				oMV.add_MESSAGE(this.Send_Email(null,vArchivmedienRG_MUST_BE_ATTACHED));
			} else {
				//nur zur sicherheit
				throw new myException(this,"Unknown error !!(534589)");
			}
			
		}
	}


	
	/**
	 * 
	 * @return array mit 2 zeilen, [0]=Tablename, [1]=id_table, wenn nicht ueberall gleich ist, dann null, wenn keine anlagen, dann leere arrayInhalte
	 * @throws myException
	 */
	public String[] get_Tablename_and_ID() throws myException {

		String[] rueck = new String[2];
		rueck[0]=null; rueck[1]=null;
		
		for (RECORD_EMAIL_SEND_ATTACH recAtt: this.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send()) {
			String cTableName = recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_TABLENAME_cUF_NN("");
			String cTableID  = 	recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_ID_TABLE_cUF_NN("");
			
			if (rueck[0]==null) {
				rueck[0]=cTableName;
				rueck[1]=cTableID;
			} else {
				if (cTableName.equals(rueck[0]) && cTableID.equals(rueck[1])) {
					continue;       //ok
				} else {
					rueck=null;
					break;   		//fehler
				}
			}
		}
		return rueck;
	}
	
	
	
	
	public Vector<String>  get_vAllNamesOfAttachmentFiles() throws myException {
		Vector<String> vFiles = new Vector<String>();
		for (RECORD_EMAIL_SEND_ATTACH recAtt: this.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send()) {
			
			RECORD_ARCHIVMEDIEN_ext recArch = new RECORD_ARCHIVMEDIEN_ext(recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien());
			String name =recArch.get__cCompletePathAndFileName();
			vFiles.add(name);
			
			if (!(new File(name).exists())) {
				throw new myException(this,"File: "+name+" is not real existing !");
			}
		}
		return vFiles;	
	}
	

	
	public Vector<FileWithSendName>  get_vAllFileWithSendnames() throws myException {
		Vector<FileWithSendName> vFiles = new Vector<FileWithSendName>();
		RECLIST_EMAIL_SEND_ATTACH  rlAttach = this.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send(null,_DB.EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN,true);
		//zuerst die originale, dann der rest an die mail haengen
		for (RECORD_EMAIL_SEND_ATTACH recAtt: rlAttach) {
			RECORD_ARCHIVMEDIEN_ext recArch = new RECORD_ARCHIVMEDIEN_ext(recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien());
			if (recArch.is_IST_ORIGINAL_YES()) {
				String name =recArch.get__cCompletePathAndFileName();
				FileWithSendName fileWsend = new FileWithSendName(name, recArch.get_DOWNLOADNAME_cF_NN(name), JasperFileDef.findFileDef(recArch.get_DOWNLOADNAME_cF_NN(name)));
				
				if (!(new File(name).exists())) {
					throw new myException(this,"File: "+name+" is not real existing !");
				} else {
					vFiles.add(fileWsend);
				}
			}
		}
		
		for (RECORD_EMAIL_SEND_ATTACH recAtt: rlAttach) {
			RECORD_ARCHIVMEDIEN_ext recArch = new RECORD_ARCHIVMEDIEN_ext(recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien());
			if (recArch.is_IST_ORIGINAL_NO()) {
				String name =recArch.get__cCompletePathAndFileName();
				FileWithSendName fileWsend = new FileWithSendName(name, recArch.get_DOWNLOADNAME_cF_NN(name), JasperFileDef.findFileDef(recArch.get_DOWNLOADNAME_cF_NN(name)));
				
				if (!(new File(name).exists())) {
					throw new myException(this,"File: "+name+" is not real existing !");
				} else {
					vFiles.add(fileWsend);
				}
			}
		}

		
		return vFiles;	
	}

	
	
	/**
	 * @param info4Message (can be null)
	 * @param vArchivmedienRG_MUST_BE_ATTACHED  (when not null, error, if one of this is not attached)
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector Send_Email(MyE2_String info4Message, ArrayList<RECORD_ARCHIVMEDIEN> vArchivmedienRG_MUST_BE_ATTACHED) throws myException {
		MyE2_MessageVector  mv_rueck = new MyE2_MessageVector();
		
		//2016-09-21: betreff und text von platzhaltern befreien und in den datensatz speichern
		
		LinkedHashMap<String, String> db_replacer = new ES__verify_table_name_and_id(null, null, this.ufs(EMAIL_SEND.id_email_send), mv_rueck).get_hm_replacing_list_FromDataSets();
		//ersetzung durchfuehren und speichern
		String betreff2send = 	bibReplacer.ReplaceSysvariablesInStrings(S.NN(this.ufs(EMAIL_SEND.betreff)), db_replacer);
		String text2send = 		bibReplacer.ReplaceSysvariablesInStrings(S.NN(this.ufs(EMAIL_SEND.text)), db_replacer);

		this.nv(EMAIL_SEND.betreff_2_send, betreff2send, mv_rueck);
		this.nv(EMAIL_SEND.text_2_send, text2send, mv_rueck);
		this.SAVE(true);
		
		
		ArrayList<RECORD_ARCHIVMEDIEN>  al_must_be_attached = new ArrayList<RECORD_ARCHIVMEDIEN>();
		if (vArchivmedienRG_MUST_BE_ATTACHED!=null) {
			al_must_be_attached.addAll(vArchivmedienRG_MUST_BE_ATTACHED);
		}
		
		//jetzt pruefen, ob alle RECORD_ARCHIVMEDIEN aus al_must_be_attached angehaengt sind
		boolean bAllIsAttached = true;
		RECLIST_EMAIL_SEND_ATTACH  rlAttach = this.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send(null,_DB.EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN,true);
		ArrayList<String> alAttached_id_archimvedien = new ArrayList<String>(rlAttach.get_ID_ARCHIVMEDIEN_hmString_UnFormated("").values());
		for (RECORD_ARCHIVMEDIEN  ra_must_be: al_must_be_attached) {
			if (!alAttached_id_archimvedien.contains(ra_must_be.get_ID_ARCHIVMEDIEN_cUF())) {
				bAllIsAttached=false;
			}
		}
		
		if (!bAllIsAttached) {
			String[] nameAndId = this.get_Tablename_and_ID();
			if (nameAndId == null || S.isEmpty(nameAndId[0]) || S.isEmpty(nameAndId[1])) {
				throw new myException(this,"Sendemail without targets is sensless or with attaments from different tables is forbidden!");
			}
			String cTABLE_BASENAME = 	nameAndId[0];
			String cID_TABLE =  		nameAndId[1];

			String cInfo = cTABLE_BASENAME;
			if (cInfo.toUpperCase().equals(_DB.VKOPF_RG.substring(3))) {
				cInfo = new MyE2_String("Rechnungs/Gutschriftsbeleg").CTrans();
			}
			
			mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(S.t("Fehler bei der Anlage: Email zu: "),S.ut(cInfo),S.t(" mit der ID: "),S.ut(cID_TABLE), S.t(" enthält nicht alle nötigen Anlagen !"))));;
		}
		
		if (mv_rueck.get_bIsOK()) {
			boolean b_weiter = true;
			
			//jetzt sicherstellen, dass bei den typeb CC und BCC nur komplett unversendete veschickt werden
			if (this.get_SendStatus()==SEND_STATUS.SEND_PART || this.get_SendStatus()==SEND_STATUS.SEND_ALL) {
				if (   this.ufs(EMAIL_SEND.send_type).equals(SEND_TYPE.CC.db_val()) 
					|| this.ufs(EMAIL_SEND.send_type).equals(SEND_TYPE.BCC.db_val())	) {
					mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Teilversendung vom Typ CC oder BCC ist nicht möglich !")));
					b_weiter=false;
				}
			}
			
			if (b_weiter) {
				String cAbsender = this.get_SENDER_ADRESS_cUF_NN("");
				if (S.isEmpty(cAbsender)) {
					cAbsender = bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("");
					this.set_NEW_VALUE_SENDER_ADRESS(cAbsender);
					mv_rueck.add_MESSAGE(this.UPDATE(true));
				}
				if (mv_rueck.get_bIsOK()) {
//					String cBetreff = this.get_BETREFF_cF_NN("Originalbeleg");
//					String cText = this.get_TEXT_cUF_NN(bibALL.get_RECORD_USER().get_MAIL_SIGNATUR_cUF_NN("Versandautomatik"));
					String cBetreff = S.NN(betreff2send,"Belegversand");
					String cText = S.NN(text2send,bibALL.get_RECORD_USER().get_MAIL_SIGNATUR_cUF_NN("Versandautomatik"));
					
					RECLIST_EMAIL_SEND_TARGETS  rl_targets = this.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send(null,EMAIL_SEND_TARGETS.pos.fn(),true);
					
					if (this.ufs(EMAIL_SEND.send_type).equals(SEND_TYPE.SINGLE.db_val()) || rl_targets.get_vKeyValues().size()==1) {     //dann einzelversand
						for (RECORD_EMAIL_SEND_TARGETS recTarget: rl_targets) {
							if (recTarget.is_SEND_OK_NO()) {
								try {
									MyE2_MessageVector  messages_4_save = new MyE2_MessageVector();
									SendMail_STD  sendMail = new SendMail_STD(cAbsender, recTarget.get_TARGET_ADRESS_cUF_NN(""), null, null, cBetreff, cText, this.get_vAllFileWithSendnames());
									sendMail.sendViaStandardMail();
									MyE2_String sendConfirmation = new MyE2_String(S.t("Erfolgreich verschickt an: "),S.ut(recTarget.get_TARGET_ADRESS_cF_NN("")));
									if (S.isFull(info4Message)) {
										sendConfirmation = new MyE2_String(S.ut(info4Message.CTrans()+": "),S.t("Erfolgreich verschickt an: "),S.ut(recTarget.get_TARGET_ADRESS_cF_NN("")));
									}
									
									messages_4_save.add_MESSAGE(new MyE2_Info_Message(sendConfirmation));
									messages_4_save.add_MESSAGE(recTarget.set_NEW_VALUE_SEND_OK("Y"));
									//benutzer nur schreiben, wenn leer
									if (S.isEmpty(recTarget.get_ID_USER_SEND_cUF())) {
										messages_4_save.add_MESSAGE(recTarget.set_NEW_VALUE_ID_USER_SEND(bibALL.get_RECORD_USER().get_ID_USER_cUF()));
									}
									recTarget.get_hm_Field_Value_pairs_from_outside().put(_DB.EMAIL_SEND_TARGETS$SENDING_TIME, "SYSDATE");
									if (messages_4_save.get_bIsOK()) {
										messages_4_save.add_MESSAGE(recTarget.UPDATE(true));
									}
									
									mv_rueck.add_MESSAGE(messages_4_save);
									if (messages_4_save.get_bHasAlarms()) {
										break;
									}
								} catch (MailException e) {
									e.printStackTrace();
								}
							}
						}
					} else {			//versand mit CC oder bcc
						String mail_adresse_main = rl_targets.get(0).ufs(EMAIL_SEND_TARGETS.target_adress) ;
						String infotest_cc_bcc_list = "";
						String infotxt = "";
						Vector<String> v_cc_list = new Vector<>();
						Vector<String> v_bcc_list = new Vector<>();
						
						Vector<String> v_list = new Vector<>();
						for (int i=1;i<rl_targets.get_vKeyValues().size();i++) {
							v_list.add(rl_targets.get(i).ufs(EMAIL_SEND_TARGETS.target_adress));
						}
						infotest_cc_bcc_list = bibALL.Concatenate(v_list, ",", "");

						if (this.ufs(EMAIL_SEND.send_type).equals(SEND_TYPE.CC.db_val())) {
							v_cc_list=v_list;
							infotxt="CC";
						} else if (this.ufs(EMAIL_SEND.send_type).equals(SEND_TYPE.BCC.db_val())) {
							v_bcc_list=v_list;
							infotxt = "BCC";
						} else {
							throw new myException(this,"Critical Error: false mail-type ");
						}
						
						
						try {
							
							MyE2_MessageVector  messages_4_save = new MyE2_MessageVector();
							SendMail_STD  sendMail = new SendMail_STD(cAbsender,mail_adresse_main, v_cc_list, v_bcc_list, cBetreff, cText, this.get_vAllFileWithSendnames());
							sendMail.sendViaStandardMail();
							MyE2_String sendConfirmation = new MyE2_String(S.t("Erfolgreich verschickt an: "),S.ut(mail_adresse_main),S.t("   --  "+infotxt+" an:"),S.ut(infotest_cc_bcc_list));
							if (S.isFull(info4Message)) {
								sendConfirmation = new MyE2_String(S.ut(info4Message.CTrans()+": "),S.ut(mail_adresse_main),S.t("   --  "+infotxt+" an:"),S.ut(infotest_cc_bcc_list));
							}
							
							messages_4_save.add_MESSAGE(new MyE2_Info_Message(sendConfirmation));
							
							//jetzt den sendungs-status schreiben
							for (RECORD_EMAIL_SEND_TARGETS target_adress: rl_targets) {
							
								messages_4_save.add_MESSAGE(target_adress.set_NEW_VALUE_SEND_OK("Y"));
								//benutzer nur schreiben, wenn leer
								if (S.isEmpty(target_adress.get_ID_USER_SEND_cUF())) {
									messages_4_save.add_MESSAGE(target_adress.set_NEW_VALUE_ID_USER_SEND(bibALL.get_RECORD_USER().get_ID_USER_cUF()));
								}
								target_adress.get_hm_Field_Value_pairs_from_outside().put(_DB.EMAIL_SEND_TARGETS$SENDING_TIME, "SYSDATE");
								if (messages_4_save.get_bIsOK()) {
									messages_4_save.add_MESSAGE(target_adress.UPDATE(true));
								}
								mv_rueck.add_MESSAGE(messages_4_save);
								if (messages_4_save.get_bHasAlarms()) {
									break;
								}
							}
						} catch (MailException e) {
							mv_rueck.add_MESSAGE(new MyE2_Alarm_Message("Mail-Error:"+e.getErrorMessage()));
							e.printStackTrace();
							
						}
					}
				}
			}
			
		}
		
		return mv_rueck;
	}
	
	
	/**
	 * 
	 * @param mv
	 * @return ArrayList<RECORD_ARCHIVMEDIEN> with MUST_attach to this email, when no attachments necessary then null
	 * @throws myException
	 */
	public ArrayList<RECORD_ARCHIVMEDIEN>  get_MUST_BE_ATTACHMENT_LIST(MyE2_MessageVector mv) throws myException {
		ArrayList<RECORD_ARCHIVMEDIEN> rlAttachmentsMustBe = null;
		if (this.get_bContainsOriginalMedium()) {
			String[] TableNameAndID = this.get_Tablename_and_ID();
			if (TableNameAndID == null || S.isEmpty(TableNameAndID[0]) || S.isEmpty(TableNameAndID[1])) {
				throw new myException(this,"Sendemail without targets is sensless or with Attaments from other tables is forbidden!");
			}
			String cTABLE_BASENAME = 	TableNameAndID[0];
			String cID_TABLE =  		TableNameAndID[1];
			
			if (cTABLE_BASENAME.equals(_DB.VKOPF_RG.substring(3))) {
				RECORD_VKOPF_RG recVKOPF_RG = new RECORD_VKOPF_RG(cID_TABLE);
				rlAttachmentsMustBe = new RGOM___Attach_Missing_and_Collect_Relevant_Attachements_4_MAIL(recVKOPF_RG,mv).get_v_collected_ArchivMedien();
			}
		}
		return rlAttachmentsMustBe;
	}

	
	
}