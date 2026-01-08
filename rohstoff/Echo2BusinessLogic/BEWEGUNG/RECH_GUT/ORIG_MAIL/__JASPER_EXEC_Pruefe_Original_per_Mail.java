package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_ROOT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_VECT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_schablonen_finder;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.archive.Archiver_Delete_File_WhenAllowed;
import panter.gmbh.indep.archive.Archiver_RG;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailAdressChecker;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_ActionAgent_Mail_Print;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

/**
 * 
 * @author martin
 * Jasper-Executer-Klasse, baut original-Belege auf und archiviert diese fuer die Versendung per eMail
 *
 */
public class __JASPER_EXEC_Pruefe_Original_per_Mail extends Jasper_Exe_ROOT {

	private boolean bInaktiv = false;

	private E2_JasperTempFile_and_pipePos_VECT  	vTempfiles4OriginalVersendung1 = new E2_JasperTempFile_and_pipePos_VECT();
	private E2_JasperTempFile_and_pipePos_VECT  	vTempfiles4OriginalVersendung2 = new E2_JasperTempFile_and_pipePos_VECT();

	/*
	 * hier werden die seq-werte der neu zu schreibenden tabellen eintraege erzeugt. falls es fehler gibt, dann werden
	 * die rueckwaerts wieder geloescht, um den alten status wiederherzustellen 
	 */
	private String  cSEQ_ID_ARCHIVMEDIEN = null;
	private String  cSEQ_ID_EMAIL_SEND = null;
	private String  cSEQ_ID_EMAIL_SEND_TARGETS = null;
	private String  cSEQ_ID_EMAIL_SEND_ATTACH = null;
//	private String  cSEQ_ID_ARCHIVMEDIEN_TO_SEND = null;
	
	/**
	 * normalerweise erfolgt die pruefung auf den ersten druck ueber die pruefung des feldes abgeschlossen=NEIN,
	 * bei Stornos wird aber der Druck direkt ausgeloest und der (neue) storno-beleg wird direkt als abgeschlossen gekennzeichnet. dann 
	 * ist die pruefung auf das feld druckzaehler=0
	 */
	private boolean bPruefung_First_Print_is_Abgeschlossen = true;
	
	
	
	public __JASPER_EXEC_Pruefe_Original_per_Mail(boolean pruefung_First_Print_is_Abgeschlossen) {
		super();
		this.bPruefung_First_Print_is_Abgeschlossen = pruefung_First_Print_is_Abgeschlossen;
	}


	@Override
	public void EXECUTE(E2_JasperHASH oJasperHash, Object objZusatz, MyE2_MessageVector oMV, Object oSammlerRueckgaben) throws myException {
		//zuerst beschaffen einiger informationen
		
		if (this.bInaktiv) {
			return;
		}
		
		
		
		//zuerst die temp-files aus den beiden verarbeitungsschritten standard und email holen und in die vectoren einsortieren
		this.vTempfiles4OriginalVersendung1.removeAllElements();
		this.vTempfiles4OriginalVersendung2.removeAllElements();
		this.Collect_Original_Document_Parts(oJasperHash);
		
		boolean bIsFirstPrint = false;
		if (this.bPruefung_First_Print_is_Abgeschlossen) {
			bIsFirstPrint =  	!((String)oJasperHash.get(BS_K_LIST_ActionAgent_Mail_Print.HASHKEY_ACT_WAS_FINISHED_BEFORE_PRINT)).equals("Y");
		} else {
			bIsFirstPrint =  	((String)oJasperHash.get(BS_K_LIST_ActionAgent_Mail_Print.HASHKEY_ACT_COUNTER_DRUCKZAEHLER_BEFORE_PRINT)).equals("0");
		}
		
		boolean bIsPreview = 		oJasperHash.get_bVorschauDruck();
		boolean bIsRechGut =       ((String)oJasperHash.get(BS_K_LIST_ActionAgent_Mail_Print.HASHKEY_ACT_BELEGTYPE)).equals(myCONST.VORGANGSART_RECHNUNG) ||
				 					((String)oJasperHash.get(BS_K_LIST_ActionAgent_Mail_Print.HASHKEY_ACT_BELEGTYPE)).equals(myCONST.VORGANGSART_GUTSCHRIFT);
		
		String cID_VKOPF_RG = (String)oJasperHash.get(BS_K_LIST_ActionAgent_Mail_Print.HASHKEY_ACT_HEAD_ID);
		

		if (bIsRechGut) {
			
			RECORD_VKOPF_RG  		recRG = 				new RECORD_VKOPF_RG(cID_VKOPF_RG);
			RECORD_ADRESSE_extend  	rec_Adresse = 			new RECORD_ADRESSE_extend(recRG.get_UP_RECORD_ADRESSE_id_adresse());
			
			
			/*
			 * Bei jedenm druck-durchlauf jetzt zusammensuchen der im umfeld der RG vorliegenden eMail-anlagen und falls in der fuhre/fuhrenort angehaengt, automatisch an den RG anhaengen
			 * Damit auch beim drucken die anlagen vorliegen 
			 */
			ArrayList<RECORD_ARCHIVMEDIEN> vRecAM = 
					new RGOM___Attach_Missing_and_Collect_Relevant_Attachements_4_MAIL(
							new RECORD_VKOPF_RG(recRG),oMV).get_v_collected_ArchivMedien();
			
			
			//hier die verketteten pdf-attachments als myTempfile in die E2_JasperHash einfuegen
			myTempFile  downFile = new RGOM___GenerateSingleConcatenatedPdf(recRG, true, oMV).get_TempFile();
			
			if (!bIsPreview && bIsFirstPrint && oMV.get_bIsOK()) {
				
				
				//2021-02-26: falls rechnung/gutschriftsperre im adress-stamm, hier die firma beschaffen
				boolean originalMailSperren = false;
				try {
					Rec21_adresse firma = new Rec21_adresse()._fill_id(recRG.get_ID_ADRESSE_lValue(null));
					if (recRG.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
						originalMailSperren=firma.isRechnungenSperren();
					} else {
						originalMailSperren=firma.isGutschriftenSperren();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					oMV._add2(e1);
				}
				//2021-08-19: weitere option: via Schalter das vorige verhalten konterkarieren 
				if (ENUM_MANDANT_DECISION.ERLAUBE_VERSAND_GESPERRTER_RECH_GUT_BELEGE.is_YES()) {
					originalMailSperren = false;
				}
				
				//2021-02-26: originalsperren ist definiert
				
				if (oMV.isOK()) {
				
					//hier wird erst einmal generell ein original erzeugt und ins archiv gelegt
					RECORD_ARCHIVMEDIEN 	recArchivmedien =  		this._baue_und_archiviere_OriginalFile(oJasperHash, recRG, rec_Adresse, oMV);
			
					
					if (recArchivmedien!=null && oMV.get_bIsOK()) {
					
						this.cSEQ_ID_ARCHIVMEDIEN = recArchivmedien.get_ID_ARCHIVMEDIEN_cUF();
						
						//jetzt pruefen, ob der kunde diese per eMail haben will
						RECORD_EMAIL  			recSingularMail4RG = 	rec_Adresse.get_eMAIL_4_RECH_GUT_MAIL();
						
						if (oMV.get_bIsOK() && recSingularMail4RG!=null && recArchivmedien !=null) {
					
							//einige werte beschaffen
							String cTargetEMail = recSingularMail4RG.get_EMAIL_cUF_NN("");
							
							if (!(new MailAdressChecker(cTargetEMail).isOK())) {
								oMV.add(new MyE2_Alarm_Message(new MyE2_String("Die Zieladresse ",true,"<"+cTargetEMail+">",false," ist nicht korrekt !",true)));
							} else {
								
								//dann werden die original-belege aus der druckpipeline rausgenommen (es wird das erzeuge original-file verschickt)
								oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard().removeAll(vTempfiles4OriginalVersendung1);
								oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck().removeAll(vTempfiles4OriginalVersendung2);
			
								this.cSEQ_ID_EMAIL_SEND = 			bibDB.get_NextSequenceValueOfTable(_DB.EMAIL_SEND);
								this.cSEQ_ID_EMAIL_SEND_TARGETS = 	bibDB.get_NextSequenceValueOfTable(_DB.EMAIL_SEND_TARGETS);
								this.cSEQ_ID_EMAIL_SEND_ATTACH = 	bibDB.get_NextSequenceValueOfTable(_DB.EMAIL_SEND_ATTACH);
								
								RECORDNEW_EMAIL_SEND 	rnEmailSend = 	new RECORDNEW_EMAIL_SEND();
								
								//jetzt die Felder email-Betreff, eMail-Text und eMail-absender aus dem profil holen
								MANDANT_CONST.MAILPROFILE m_prof = null;
								if (recRG.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
									m_prof=MANDANT_CONST.MAILPROFILE.RECHNUNG;
								} else {
									m_prof=MANDANT_CONST.MAILPROFILE.GUTSCHRIFT;
								}
	
								SE_schablonen_finder sf = new SE_schablonen_finder(m_prof);
								
								rnEmailSend.set_NEW_VALUE_SENDER_ADRESS(sf.get_absender());
								rnEmailSend.set_NEW_VALUE_BETREFF(sf.get_betreff());
								rnEmailSend.set_NEW_VALUE_TEXT(sf.get_text());
								rnEmailSend.set_NEW_VALUE_SEND_TYPE(sf.get_send_type());
								
								
								oMV.add_MESSAGE(rnEmailSend.set_NEW_VALUE_ID_EMAIL_SEND(this.cSEQ_ID_EMAIL_SEND));
								
								RECORDNEW_EMAIL_SEND_TARGETS 	rnEmailSend_Target = 	new RECORDNEW_EMAIL_SEND_TARGETS();
								oMV.add_MESSAGE(rnEmailSend_Target.set_NEW_VALUE_SEND_OK("N"));
								oMV.add_MESSAGE(rnEmailSend_Target.set_NEW_VALUE_TARGET_ADRESS(cTargetEMail));
								oMV.add_MESSAGE(rnEmailSend_Target.set_NEW_VALUE_ID_EMAIL_SEND_TARGETS(cSEQ_ID_EMAIL_SEND_TARGETS));
								oMV.add_MESSAGE(rnEmailSend_Target.set_NEW_VALUE_ID_EMAIL_SEND(cSEQ_ID_EMAIL_SEND));
								
								RECORDNEW_EMAIL_SEND_ATTACH     rnEmailAttach = new RECORDNEW_EMAIL_SEND_ATTACH();
								rnEmailAttach.set_NEW_VALUE_ID_EMAIL_SEND(this.cSEQ_ID_EMAIL_SEND);
								rnEmailAttach.set_NEW_VALUE_ID_EMAIL_SEND_ATTACH(this.cSEQ_ID_EMAIL_SEND_ATTACH);
								rnEmailAttach.set_NEW_VALUE_ID_ARCHIVMEDIEN(this.cSEQ_ID_ARCHIVMEDIEN);
								
								
								if  (oMV.get_bIsOK()) {
									
									ArrayList<RECORDNEW_EMAIL_SEND_ATTACH>  vGesammelteArchivmedien = new ArrayList<RECORDNEW_EMAIL_SEND_ATTACH>();
									
									//zuerst die orginale an die mail haengen, dann der rest
									for (RECORD_ARCHIVMEDIEN recAM: vRecAM) {
										if (recAM.is_IST_ORIGINAL_YES()) {
											RECORDNEW_EMAIL_SEND_ATTACH     rnAttach = new RECORDNEW_EMAIL_SEND_ATTACH();
											oMV.add_MESSAGE(rnAttach.set_NEW_VALUE_ID_EMAIL_SEND(this.cSEQ_ID_EMAIL_SEND));
											oMV.add_MESSAGE(rnAttach.set_NEW_VALUE_ID_ARCHIVMEDIEN(recAM.get_ID_ARCHIVMEDIEN_cUF()));
											vGesammelteArchivmedien.add(rnAttach);
											
											DEBUG.System_println("eMail-attach: "+new RECORD_ARCHIVMEDIEN_ext(recAM).get__cCompletePathAndFileName(), DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
										}
									}
									for (RECORD_ARCHIVMEDIEN recAM: vRecAM) {
										if (recAM.is_IST_ORIGINAL_NO()) {
											RECORDNEW_EMAIL_SEND_ATTACH     rnAttach = new RECORDNEW_EMAIL_SEND_ATTACH();
											oMV.add_MESSAGE(rnAttach.set_NEW_VALUE_ID_EMAIL_SEND(this.cSEQ_ID_EMAIL_SEND));
											oMV.add_MESSAGE(rnAttach.set_NEW_VALUE_ID_ARCHIVMEDIEN(recAM.get_ID_ARCHIVMEDIEN_cUF()));
											vGesammelteArchivmedien.add(rnAttach);
											DEBUG.System_println("eMail-attach: "+new RECORD_ARCHIVMEDIEN_ext(recAM).get__cCompletePathAndFileName(), DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
	
										}
									}
									
								
									if (oMV.get_bIsOK()) {
										MySqlStatementBuilder stmbEMAIL_Send = 			rnEmailSend.get__StatementBuilder(true,false);
										MySqlStatementBuilder stmbEMAIL_SendTarget = 	rnEmailSend_Target.get__StatementBuilder(true,false);
										MySqlStatementBuilder stmbEMAIL_SendAttach = 	rnEmailAttach.get__StatementBuilder(true,false);
				
										if (recRG.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
											recArchivmedien.set_NEW_VALUE_PROGRAMM_KENNER(Archiver_CONST.PROGRAMMKENNER.RECHNUNG_ANHANG.get_DB_WERT());
										} else {
											recArchivmedien.set_NEW_VALUE_PROGRAMM_KENNER(Archiver_CONST.PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_DB_WERT());
										}
										
										Vector<String> vSQL = new Vector<String>();
										
										//wenn belegsperre, dann werden keine eMail-daten geschrieben
										if (originalMailSperren) {
											vSQL.add(recArchivmedien.get_SQL_UPDATE_STD());
										} else {
										
											vSQL.add(stmbEMAIL_Send.get_CompleteInsertString(_DB.EMAIL_SEND,bibE2.cTO()));
											vSQL.add(recArchivmedien.get_SQL_UPDATE_STD());
											vSQL.add(stmbEMAIL_SendTarget.get_CompleteInsertString(_DB.EMAIL_SEND_TARGETS,bibE2.cTO()));
											vSQL.add(stmbEMAIL_SendAttach.get_CompleteInsertString(_DB.EMAIL_SEND_ATTACH,bibE2.cTO()));
											
											//jetzt noch die eingesammelten 
											for (RECORDNEW_EMAIL_SEND_ATTACH rAt: vGesammelteArchivmedien) {
												MySqlStatementBuilder stmbAt = 	rAt.get__StatementBuilder(true,true);
												vSQL.add(stmbAt.get_CompleteInsertString(_DB.EMAIL_SEND_ATTACH,bibE2.cTO()));
											}
										}
										
										//DEBUG.System_print(vSQL);
										
										oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
										
										if (!oMV.get_bHasAlarms()) {
											if (originalMailSperren) {
												if (recRG.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
													oMV._addWarn(S.ms("Aufgrund der Stammdateneinstellung <Rechnung sperren> wurde der eMail-Vorgang unterdrückt!"));
												} else {
													oMV._addWarn(S.ms("Aufgrund der Stammdateneinstellung <Gutschrift sperren> wurde der eMail-Vorgang unterdrückt!"));
												}
											}else {
												oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Originalbeleg mit der ID: ",true,cID_VKOPF_RG,false," wurde zum Versenden vorbereitet!",true)));
											}
										}
									}
								}
							}
						} else {
							//in den faellen klassischer verarbeitung werden die anzuhaengenden dateien mit dem druck heruntergeladen/gedruckt
							this.attach_pdfs_at_end(oJasperHash, downFile);
						}
					}
				
				}
				
				//jetzt aufraeumen (falls etwas nicht geklappt hat)
				if (oMV.get_bHasAlarms()) {
					//alles zurueck ...
					Vector<String>  vAllesReset = new Vector<String>();
					String cPFAD = null;
					String cFILE = null;
					
					vAllesReset.add("UPDATE "+bibE2.cTO()+"."+	_DB.VKOPF_RG+" SET "+_DB.VKOPF_RG$ABGESCHLOSSEN+"='N' WHERE "+_DB.VKOPF_RG$ID_VKOPF_RG+"="+cID_VKOPF_RG	);
					if (S.isFull(this.cSEQ_ID_EMAIL_SEND_ATTACH)) {
						try {
							RECORD_EMAIL_SEND_ATTACH  recATTACH = new RECORD_EMAIL_SEND_ATTACH(this.cSEQ_ID_EMAIL_SEND_ATTACH);
							vAllesReset.add(recATTACH.get_DELETE_STATEMENT());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if (S.isFull(this.cSEQ_ID_ARCHIVMEDIEN)) {
						try {
							RECORD_ARCHIVMEDIEN  recAM_ORIG = new RECORD_ARCHIVMEDIEN(new RECORD_ARCHIVMEDIEN(this.cSEQ_ID_ARCHIVMEDIEN));
							cPFAD = recAM_ORIG.get_PFAD_cUF();
							cFILE = recAM_ORIG.get_DATEINAME_cUF();
							vAllesReset.add(recAM_ORIG.get_DELETE_STATEMENT());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					
					if (S.isFull(this.cSEQ_ID_EMAIL_SEND_TARGETS)) {
						try {
							RECORD_EMAIL_SEND_TARGETS  recEMAIL_T = new RECORD_EMAIL_SEND_TARGETS(this.cSEQ_ID_EMAIL_SEND_TARGETS);
							vAllesReset.add(recEMAIL_T.get_DELETE_STATEMENT());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (S.isFull(this.cSEQ_ID_EMAIL_SEND)) {
						try {
							RECORD_EMAIL_SEND  recEMAIL = new RECORD_EMAIL_SEND(this.cSEQ_ID_EMAIL_SEND);
							vAllesReset.add(recEMAIL.get_DELETE_STATEMENT());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if (bibDB.ExecMultiSQLVector(vAllesReset, true).get_bIsOK()) {
						if (S.isFull(cPFAD) && S.isFull(cFILE)) {
							new Archiver_Delete_File_WhenAllowed(cPFAD,cFILE,true);
						}
					}
					
				}
				
			} else if (oMV.get_bIsOK()) {
				//im preview oder mehrfachdruck-fall werden die anhaenge an das letzte pdf der erzeugten pipeline angehaengt
				this.attach_pdfs_at_end(oJasperHash, downFile);
			}
			
		}
	}

	
	/**
	 * and das jeweilig letzte tempfile in den gesammelten pdf-vectoren die verkettete liste aller anzuhaengenden pdf-dokumente dranhaengen
	 * (ausser bei der Archivierungs-pipeline, dort waere das datenvolumen dann zu groß
	 * @param oJasperHash
	 * @param downFile
	 * @throws myException
	 */
	private void attach_pdfs_at_end(E2_JasperHASH oJasperHash, myTempFile downFile) throws myException {
		//hier die verkettete temp-anlagen mit an den beleg haengen
		//an jede 1. temp-datei innerhalb der sammelvectoren die verketteten anhaenge einbauen (ausser ins archiv)
		if (downFile!=null && bibALL.get_RECORD_MANDANT().is_APPEND_ATTACHMENT_PDF_TO_RG_YES()) {
			if (oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard().size()>0) {
				E2_JasperTempFile_and_pipePos part = oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard().get(oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard().size()-1);
				part.get_oJasperTempFile().append_pdf(bibVECTOR.get_Vector(downFile.getFileName()));
			}
			if (oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck().size()>0) {
				E2_JasperTempFile_and_pipePos part = oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck().get(oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck().size()-1);
				part.get_oJasperTempFile().append_pdf(bibVECTOR.get_Vector(downFile.getFileName()));
			}
			if (oJasperHash.get_jasperTempFile_and_pipePos_VECT_KontrollMail().size()>0) {
				E2_JasperTempFile_and_pipePos part = oJasperHash.get_jasperTempFile_and_pipePos_VECT_KontrollMail().get(oJasperHash.get_jasperTempFile_and_pipePos_VECT_KontrollMail().size()-1);
				part.get_oJasperTempFile().append_pdf(bibVECTOR.get_Vector(downFile.getFileName()));
			}
		}
	}

	
	
	
	private void Collect_Original_Document_Parts(E2_JasperHASH oJasperHash) throws myException {

		//zuerst die standard
		for (E2_JasperTempFile_and_pipePos jasperPart: oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard()) {
			boolean bIstOriginal = true;   //falls ohne record-pipeline
			if (jasperPart.get_oRecordPipelinePOS()!=null) {
				bIstOriginal = jasperPart.get_oRecordPipelinePOS().is_ORIGINAL_DOKUMENT_YES();
			}
			if (bIstOriginal) {
				this.vTempfiles4OriginalVersendung1.add(jasperPart);
			}
		}
		
		//dann die direktdruck
		for (E2_JasperTempFile_and_pipePos jasperPart: oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck()) {
			boolean bIstOriginal = true;   //falls ohne record-pipeline
			if (jasperPart.get_oRecordPipelinePOS()!=null) {
				bIstOriginal = jasperPart.get_oRecordPipelinePOS().is_ORIGINAL_DOKUMENT_YES();
			}
			if (bIstOriginal) {
				this.vTempfiles4OriginalVersendung2.add(jasperPart);
			}
		}

	}
	
	
	
	/*
	 * methode erzeugt auch ein archiv-file und schreibt ein record_archivmedien in die datenbank 
	 */
	private RECORD_ARCHIVMEDIEN _baue_und_archiviere_OriginalFile(	E2_JasperHASH 			oJasperHash, 
																	RECORD_VKOPF_RG 		recRG, 
																	RECORD_ADRESSE_extend  	rec_Adresse, 
																	MyE2_MessageVector 		oMV_Prot ) throws myException {
		
		RECORD_ARCHIVMEDIEN  recARCHIVMEDIEN = null;
		
		//jetzt die originale verknuepfen und archivieren
		E2_JasperTempFile_and_pipePos_VECT vAlleOriginale = new E2_JasperTempFile_and_pipePos_VECT();
		vAlleOriginale.addAll(vTempfiles4OriginalVersendung1);
		vAlleOriginale.addAll(vTempfiles4OriginalVersendung2);

		if (vAlleOriginale.size()>0) {
			
			E2_JasperTempFile_VECT 	vFiles4Archiv = vAlleOriginale.get_vJasperTempFile();
			
			String 				cFileName4Archiv = "ORIG_RG_BN_"+recRG.get_BUCHUNGSNUMMER_cUF_NN("<bn>")+"__ADRESSID_"+rec_Adresse.get_ID_ADRESSE_cUF();
			
			myTempFile  		tfArchiv = vFiles4Archiv.get_myTempFileAllConcated(cFileName4Archiv);
			
			Archiver_RG 		oArch = new Archiver_RG();
			String 				cNewFileName = oArch.copyFilenameToNextFree(tfArchiv.getFileName(), cFileName4Archiv+".pdf");
			
			DEBUG.System_println("Dateiname des Originals im Archivordner:"+cNewFileName);
			
//			tfArchiv.getFile().delete();
			
			//datenbankeinträge für den archiveintrag
			RECORDNEW_ARCHIVMEDIEN recNEW = oArch.get_RECNEW_ARCHIVMEDIEN(
									oArch.get_cSUB_DIR_IN_Archiv(),
									cNewFileName,
									cFileName4Archiv+".pdf",
									"Originaldatei für internes Archiv "+cFileName4Archiv,
									 null,
									 null,
									 "VKOPF_RG",   
									 recRG.get_ID_VKOPF_RG_cUF(),
									 "pdf",
									 Archiver_CONST.MEDIENKENNER.ORIGBELEG.get_DB_WERT(),
									 null,
									 null,
									 null,
									 null,
									 true,
									 oMV_Prot);
			
			if (oMV_Prot.get_bIsOK()) {
				recARCHIVMEDIEN = recNEW.do_WRITE_NEW_ARCHIVMEDIEN(oMV_Prot);
				if (oMV_Prot.get_bIsOK()) {
					oMV_Prot.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurde ein Originalbeleg ins Archiv zu Rechnungskopf-ID:",true,recRG.get_ID_VKOPF_RG_cUF(),false," abgelegt !",true)));
				}
			}
			 
		} else {
			oMV_Prot.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann kein Original archiviert werden, da es keine Druckpipeline-Positionen gibt, die als Orignale definiert sind!")));
		}
		
		return recARCHIVMEDIEN;
	}
	
	
	
	@Override
	public EXECUTER_JUMPPOINTS get_JUMPMarker() {
		return Jasper_Exe_CONST.EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_PROCESS_ALL_TEMPFILES;
	}

}
