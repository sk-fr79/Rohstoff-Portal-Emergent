package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_ROOT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * O
 * @author martin
 * Jasper-Executer-Klasse, baut original-Belege auf und archiviert diese fuer die Versendung per eMail
 *
 */
public class SE_JASPER_EXEC_ARCHIVIERE_PDF extends Jasper_Exe_ROOT {

	private boolean bInaktiv = false;

	private E2_JasperTempFile_and_pipePos_VECT  	vJasperTempFile_and_pipePos_VECT1 = new E2_JasperTempFile_and_pipePos_VECT();
	private E2_JasperTempFile_and_pipePos_VECT  	vJasperTempFile_and_pipePos_VECT2 = new E2_JasperTempFile_and_pipePos_VECT();
	
	
	private myTempFile 			tfArchiv = null;
	private RECORD_ARCHIVMEDIEN record_archivmedien_written = null;
	
	
	public SE_JASPER_EXEC_ARCHIVIERE_PDF() {
		super();
	}


	@Override
	public void EXECUTE(E2_JasperHASH oJasperHash, Object objZusatz, MyE2_MessageVector oMV, Object oSammlerRueckgaben) throws myException {
		SE_JasperHASH_4_ArchivPDF myJasper = null;
		
		//fuer diesen executer ist ein jasperhash vom typ SE_JasperHASH_4_ArchivPDF noetig
		if (!(oJasperHash instanceof SE_JasperHASH_4_ArchivPDF)) {
			throw new myException(this,"Here only type SE_JasperHASH_4_ArchivPDF alloed");
		}
		myJasper = (SE_JasperHASH_4_ArchivPDF)oJasperHash;
		
		//bei status preview keine archivierung (vorschaudruck alt und neu pruefen)
		if (this.bInaktiv || oJasperHash.get_IS_TYP_PREVIEW() || oJasperHash.get_bVorschauDruck()) {
			return;
		}
		
		//zuerst die temp-files aus den beiden verarbeitungsschritten standard und email holen und in die vectoren einsortieren
		this.Collect_Document_Parts(oJasperHash);
		

		if (myJasper.check_if_archive_must_be_done()) {
			// archiv-kopie ablegen
			this.record_archivmedien_written = this._baue_und_archiviere_OriginalFile(myJasper, oMV);
			
			if (this.record_archivmedien_written!=null && oMV.get_bIsOK()) {
			
				//wenn gedruckt wird, ist hier ende
				if (myJasper.get_IS_TYP_MAIL()) {
					//bei mail wird noch eine mail-versand-eintrag angelegt
					Vector<MySqlStatementBuilder>  statements = new Vector<>();
					
					SE_schablonen_finder sf = new SE_schablonen_finder(myJasper.get_used_send_profile());
					
					RECORDNEW_EMAIL_SEND 	rnEmailSend = 	new RECORDNEW_EMAIL_SEND();
					rnEmailSend.set_NEW_VALUE_SENDER_ADRESS(sf.get_absender());
					rnEmailSend.set_NEW_VALUE_BETREFF(sf.get_betreff());
					rnEmailSend.set_NEW_VALUE_TEXT(sf.get_text());
					rnEmailSend.set_NEW_VALUE_SEND_TYPE(sf.get_send_type());
					
//NEUES_FELD_SCHABLONE					
					
					statements.add(rnEmailSend.get_StmtBuilder(true, true));
					
					//jetzt das attachment
					RECORDNEW_EMAIL_SEND_ATTACH  	rn = new RECORDNEW_EMAIL_SEND_ATTACH();
					rn.set_NEW_VALUE_ID_ARCHIVMEDIEN(this.record_archivmedien_written.get_ID_ARCHIVMEDIEN_cUF());
					rn.get_hm_Field_Value_pairs_from_outside().put(EMAIL_SEND_ATTACH.id_email_send.fn(), "SEQ_"+_TAB.email_send.baseTableName()+".currval");
					statements.add(rn.get_StmtBuilder(true, true));
	
					
					ArrayList<String> al_emails =  new ArrayList<>();
					al_emails.addAll(myJasper.get_v_email_targets());
					
					for (String email: al_emails) {
						RECORDNEW_EMAIL_SEND_TARGETS  t = new RECORDNEW_EMAIL_SEND_TARGETS();
						t.set_NEW_VALUE_TARGET_ADRESS(email);
						t.get_hm_Field_Value_pairs_from_outside().put(EMAIL_SEND_ATTACH.id_email_send.fn(), "SEQ_"+_TAB.email_send.baseTableName()+".currval");
						statements.add(t.get_StmtBuilder(true, true));
					}
					
					Vector<String> v_sql = new Vector<>();
					for (MySqlStatementBuilder stb: statements) {
						v_sql.add(stb.get_InsertString());
					}
					
					DEBUG.System_print(v_sql);
					
					if (bibDB.ExecSQL(v_sql, true)) {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurde eine eMail-Sendung vorbereitet !")));
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Anlage der eMail-Sendung !")));
					}
					
				}
			} else {
				bibMSG.add_MESSAGE(oMV);
			}
			
			
		}
	}
	
	
	
	private void Collect_Document_Parts(E2_JasperHASH oJasperHash) throws myException {
		this.vJasperTempFile_and_pipePos_VECT1.removeAllElements();
		this.vJasperTempFile_and_pipePos_VECT2.removeAllElements();

		//zuerst die standard
		for (E2_JasperTempFile_and_pipePos tempFileAndPipePos: oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard()) {
			this.vJasperTempFile_and_pipePos_VECT1.add(tempFileAndPipePos);
		}
		//dann die direktdruck
		for (E2_JasperTempFile_and_pipePos jasperPart: oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck()) {
			this.vJasperTempFile_and_pipePos_VECT2.add(jasperPart);
		}
	}
	
	
	
	/*
	 * methode erzeugt auch ein archiv-file und schreibt ein record_archivmedien in die datenbank 
	 */
	private RECORD_ARCHIVMEDIEN _baue_und_archiviere_OriginalFile(	SE_JasperHASH_4_ArchivPDF 	oJasperHash, 
																	MyE2_MessageVector 			oMV_Prot ) throws myException {
		
		RECORD_ARCHIVMEDIEN  recARCHIVMEDIEN = null;
		
		//jetzt die originale verknuepfen und archivieren
		E2_JasperTempFile_and_pipePos_VECT vAlleOriginale = new E2_JasperTempFile_and_pipePos_VECT();
		vAlleOriginale.addAll(vJasperTempFile_and_pipePos_VECT1);
		vAlleOriginale.addAll(vJasperTempFile_and_pipePos_VECT2);

		
		
		
		if (vAlleOriginale.size()>0) {
			
			String cFileName4Archiv = oJasperHash.generate_filename_4_archiv_without_ending();
			if (S.isEmpty(cFileName4Archiv)) {
				cFileName4Archiv = oJasperHash.get_table().baseTableName()+"_"+bibALL.get_cDateNOWInverse("-");
			}
			
			this.tfArchiv = 	vAlleOriginale.get_vJasperTempFile().get_myTempFileAllConcated(cFileName4Archiv);
			this.tfArchiv.set_bDeleteAtFinalize(true);
			
			Archiver 			oArch = new Archiver(oJasperHash.get_table().baseTableName(), new Date(), Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_CALWEEK);
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
									 oJasperHash.get_table().baseTableName(),   
									 oJasperHash.get_id_tab(),
									 "pdf",
									 oJasperHash.generate_medienkenner_for_archiv()==null?null:oJasperHash.generate_medienkenner_for_archiv().get_DB_WERT(),
									 null,
									 null,
									 null,
									 oJasperHash.generate_programmkenner_for_archiv()==null?null:oJasperHash.generate_programmkenner_for_archiv().get_DB_WERT(),
									 false,
									 oMV_Prot);
			
			if (oMV_Prot.get_bIsOK()) {
				recARCHIVMEDIEN = recNEW.do_WRITE_NEW_ARCHIVMEDIEN(oMV_Prot);
				if (oMV_Prot.get_bIsOK()) {
					oMV_Prot.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurde ein Dokument ins Archiv:").ut("ID:").ut(oJasperHash.get_id_tab()).t(" abgelegt !")));
				}
			}
		} else {
			oMV_Prot.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Keine Ablage möglich, kein Inhalt im Druckvorgang")));
		}
		
		return recARCHIVMEDIEN;
	}
	
	
	
	@Override
	public EXECUTER_JUMPPOINTS get_JUMPMarker() {
		return Jasper_Exe_CONST.EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_PROCESS_ALL_TEMPFILES;
	}


	public RECORD_ARCHIVMEDIEN get_record_archivmedien_written() {
		return this.record_archivmedien_written;
	}

}
