package panter.gmbh.Echo2.RB.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;

public class RB_Email {

	private MyE2_MessageVector omv;

	private boolean neuEmail = false;

	private RB_Email_dataModel mail;

	private RB_Email_dataModel loadedMail = null;

	private RECORD_EMAIL_SEND mail_record_4_update;


	/**
	 * 
	 */
	public RB_Email()  throws myException{ 
		this.omv = new MyE2_MessageVector();
		this.neuEmail = true;
		this.mail = new RB_Email_dataModel();
		this.mail.setAbsender(new RECORD_USER(bibALL.get_ID_USER()).get_EMAIL_cUF_NN(""));
	}

	/**
	 * 
	 * @param oRecord
	 * @throws myException
	 */
	public RB_Email(RECORD_EMAIL_SEND oRecord) throws myException{
		this.neuEmail = false;

		this.omv = new MyE2_MessageVector();

		RECORD_EMAIL_SEND_ext extRecord = new RECORD_EMAIL_SEND_ext(oRecord);

		RECLIST_ARCHIVMEDIEN archivVector = new RECLIST_ARCHIVMEDIEN();
		RECLIST_ARCHIVMEDIEN archivVectorLoaded = new RECLIST_ARCHIVMEDIEN();
		for(RECORD_EMAIL_SEND_ATTACH rec:extRecord.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send()){
			archivVector.put(rec.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_ID_ARCHIVMEDIEN_cF_NN(""),rec.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien());
			archivVectorLoaded.put(rec.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_ID_ARCHIVMEDIEN_cF_NN(""),rec.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien());
		}

		Vector<String> zielAdresseListe 		= 	new Vector<>();
		Vector<String> zielAdresseListeLoaded 	= 	new Vector<>();
		zielAdresseListe.addAll(extRecord.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send().get_TARGET_ADRESS_hmString_Formated("").values());
		zielAdresseListeLoaded.addAll(extRecord.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send().get_TARGET_ADRESS_hmString_Formated("").values());

		this.loadedMail = new RB_Email_dataModel();

		this.loadedMail.setMailId(extRecord.get_ID_EMAIL_SEND_cUF_NN(""));
		this.loadedMail.setBetreff(extRecord.get_BETREFF_cUF_NN(""));
		this.loadedMail.setMailTyp(SEND_TYPE.valueOf(extRecord.get_SEND_TYPE_cF_NN("")));
		this.loadedMail.setAbsender(extRecord.get_SENDER_ADRESS_cUF_NN(""));
		this.loadedMail.setText(extRecord.get_TEXT_cUF_NN(""));
		this.loadedMail.setSendStatus(extRecord.get_SendStatus());
		this.loadedMail.setAnlageVector(archivVector);
		this.loadedMail.setZielAdresseListe(zielAdresseListe);

		this.mail = new RB_Email_dataModel();

		this.mail.setMailId(extRecord.get_ID_EMAIL_SEND_cUF_NN(""));
		this.mail.setBetreff(oRecord.get_BETREFF_cUF_NN(""));
		this.mail.setMailTyp(SEND_TYPE.valueOf(oRecord.get_SEND_TYPE_cF_NN("")));
		this.mail.setAbsender(oRecord.get_SENDER_ADRESS_cUF_NN(""));
		this.mail.setText(oRecord.get_TEXT_cUF_NN(""));
		this.mail.setSendStatus(extRecord.get_SendStatus());
		this.mail.setAnlageVector(archivVectorLoaded);
		this.mail.setZielAdresseListe(zielAdresseListeLoaded);

		this.mail_record_4_update = new RECORD_EMAIL_SEND(oRecord);
	}

	//TODO---------------- BASIS METHODEN ----------------

	/**
	 * @param oBetreff : betreff
	 * @return
	 * @throws myException
	 */
	public RB_Email _betreff(String oBetreff)throws myException{	
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.setBetreff(oBetreff);
		}
		return this;
	}


	/**
	 * @param oAbsender : absender
	 * @return
	 * @throws myException
	 */
	public RB_Email _absender(String oAbsender)throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.setAbsender(oAbsender);
		}
		return this;
	}

	public RB_Email _send_typ_bcc(){
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.setMailTyp(SEND_TYPE.BCC);
		}
		return this;
	}

	public RB_Email _send_typ_cc(){
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.setMailTyp(SEND_TYPE.CC);
		}
		return this;
	}

	public RB_Email _send_typ(SEND_TYPE eTyp){
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.setMailTyp(eTyp);
		}
		return this;
	}

	public MyE2_MessageVector getOmv() {
		return omv;
	}

	/**
	 * id user as Absender
	 * @param user id
	 * @return
	 * @throws myException
	 */
	public RB_Email _absender_by_userId(String oIdAbsender)throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.setAbsender(new RECORD_USER(oIdAbsender).get_EMAIL_cF_NN(""));
		}
		return this;
	}

	public RB_Email _text(String oText){
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.setText(oText);
		}
		return this;
	}

	public RB_Email _setTable(_TAB oTab){
		this.mail.set_table_base_name(oTab);
		return this;
	}

	public RB_Email _setId(String oId){
		this.mail.set_table_id(oId);
		return this;
	}

	public RB_Email _set_table_and_id(_TAB oTab, String oId){
		this.mail.set_table_base_name(oTab);
		this.mail.set_table_id(oId);
		return this;
	}
	//TODO---------------- ANLAGEN METHODEN ----------------

	public RB_Email _add_anlage(_TAB oTable, String oIdTable) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){

			String bed1 = new vgl(ARCHIVMEDIEN.tablename, oTable.baseTableName()).s();
			String bed2 = new vgl(ARCHIVMEDIEN.id_table, oIdTable).s();

			RECLIST_ARCHIVMEDIEN recListArchiv = new RECLIST_ARCHIVMEDIEN(bed1 + " AND " + bed2, ARCHIVMEDIEN.id_archivmedien.fieldName());
			this.mail.getAnlageVector().ADD(recListArchiv, false);

			//			for(RECORD_ARCHIVMEDIEN record :  recListArchiv){
			//				this.mail.getAnlageVector().put(record.get_ID_ARCHIVMEDIEN_cUF(""), record);
			//			}
		}
		return this;
	}

	public RB_Email _add_anlage(_TAB oTable, Vector<String> vId) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			for(String id: vId){
				this._add_anlage(oTable, id);
			}
		}
		return this;
	}

	public RB_Email _add_anlage(RECORD_ARCHIVMEDIEN oRecArchiv) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.getAnlageVector().ADD(oRecArchiv, false);
		}
		return this;
	}

	public RB_Email _add_anlage(RECLIST_ARCHIVMEDIEN oRecListArchiv) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.getAnlageVector().ADD(oRecListArchiv, false);
		}
		return this;
	}

	public RB_Email _delete_anlage(RECORD_ARCHIVMEDIEN oRecArchiv) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.getAnlageVector().remove(oRecArchiv.get_ID_ARCHIVMEDIEN_cUF());	
		}
		return this;
	}

	public RB_Email _delete_all_anlage(){
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			this.mail.getAnlageVector().clear();
		}
		return this;
	}

	private RB_Email _add_anlage(Vector<String> oPfadVector){
		//		for(String oPfad : oPfadVector){
		//
		//		}
		return this;
	}

	private RB_Email _add_anlage(String oCompletePathName) throws myException{
		String basePath = Archiver.get_ARCHIV_BASE_PATH(true,true);

		String pfad_und_dateiname ="";

		if(oCompletePathName.contains(basePath)){
			pfad_und_dateiname = oCompletePathName.substring(basePath.length());
		}else{
			pfad_und_dateiname = oCompletePathName;
		}
		String dateiname = Archiver.getFileNameFrom_CompletePathName(pfad_und_dateiname);
		String Pfad = Archiver.getPathNameFrom_CompletePathName(pfad_und_dateiname);		


		//		String filename = Archiver.getFileNameFrom_CompletePathName(oCompletePathName);
		//		String pfad = Archiver.getPathNameFrom_CompletePathName(oCompletePathName);
		//		
		//		String check_if_datei_exist_in_db_abfrage = new SEL("count(" + ARCHIVMEDIEN.id_archivmedien.name()+")")
		//		.FROM(_TAB.archivmedien)
		//		.WHERE(new vgl(ARCHIVMEDIEN.pfad, pfad))	
		//		.AND(new vgl(ARCHIVMEDIEN.dateiname, filename)).s();
		//		
		//		if( Integer.parseInt(bibDB.EinzelAbfrage(check_if_datei_exist_in_db_abfrage))>0  ){
		//			mail.getAnlageVector().add(new RECORD_ARCHIVMEDIEN(new vgl(ARCHIVMEDIEN.pfad, pfad).s()+" AND " + new vgl(ARCHIVMEDIEN.dateiname, filename).s()));
		//		}else{
		//			new
		//		}


		return this;
	}

	//TODO---------------- ZIEL ADRESSEN METHODEN ----------------

	public RB_Email _to(String zielAdresse) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			if(S.isFull(zielAdresse)){
				this.mail.getZielAdresseListe().add(zielAdresse);
			}
		}
		return this;
	}

	public RB_Email _to(Vector<String> zielAdressen) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			if(!(zielAdressen == null) && (zielAdressen.size()>0) ){
				this.mail.getZielAdresseListe().addAll(zielAdressen);
			}
		}
		return this;
	}

	public RB_Email _to(RECLIST_EMAIL zielAdressen) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			if(!(zielAdressen == null) && zielAdressen.size()>0){
				for(RECORD_EMAIL zielAdresse: zielAdressen){
					this.mail.getZielAdresseListe().add(zielAdresse.get_EMAIL_cF_NN(""));
				}
			}
		}
		return this;
	}

	public RB_Email _to(RECORD_EMAIL zielAdresse) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			if(!(zielAdresse == null)){
				this.mail.getZielAdresseListe().add(zielAdresse.get_EMAIL_cF_NN(""));
			}
		}
		return this;
	}

	public RB_Email _remove_targets(RECLIST_EMAIL zielAdressen) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			if(!(zielAdressen == null) && zielAdressen.size()>0){
				for(RECORD_EMAIL zielAdresse: zielAdressen){
					this.mail.getZielAdresseListe().remove(zielAdresse.get_EMAIL_cF_NN(""));
				}
			}
		}
		return this;
	}

	public RB_Email _remove_target(RECORD_EMAIL zielAdresse) throws myException{
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			if(!(zielAdresse == null)){
				this.mail.getZielAdresseListe().remove(zielAdresse.get_EMAIL_cF_NN(""));
			}
		}
		return this;
	}

	public RB_Email _remove_target(String zielAdresse){
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			if(S.isFull(zielAdresse)){
				this.mail.getZielAdresseListe().remove(zielAdresse);
			}
		}
		return this;
	}

	public RB_Email _remove_targets(Vector<String> zielAdressen){
		if(this.mail.getSendStatus()==SEND_STATUS.SEND_NONE){
			if(!(zielAdressen == null) && (zielAdressen.size()>0) ){
				this.mail.getZielAdresseListe().removeAll(zielAdressen);
			}
		}
		return this;
	}

	//TODO---------------- LOESCH METHODEN ---------------- 
	public void delete_mail()throws myException{
		if(!neuEmail){
			RECORD_EMAIL_SEND recToDel = new RECORD_EMAIL_SEND(this.loadedMail.getMailId());
			omv.add_MESSAGE(recToDel.DELETE());
		}
	}

	public void delete_and_save() throws myException{

	}


	//TODO---------------- SPEICHERN UND SEND METHODEN ----------------

	public  RB_Email _save() throws myException{
		this._save(null);
		return this;
	}

	public RB_Email _save(SEND_TYPE eTyp, MyE2_MessageVector mv) throws myException{
		mail.setMailTyp(eTyp);
		this._save(mv);
		return this;
	}

	public RB_Email _save(MyE2_MessageVector mv) throws myException{

		if(! (mv==null) ){
			omv = mv;
		}

		email_konsistenz_pruefen();

		if(omv.get_bIsOK()){

			//Neu Email in DB
			if(neuEmail){
				//SQL: insert into
				save_new_in_db();
			}else{

				if(mail.equals(loadedMail)){
					// nix
					DEBUG.System_println("mach nix");
					do_nothing_in_db();
				}else{
					//SQL: update
					DEBUG.System_println("update");			
					save_update_in_db();
				}
			}	
		}else{
			bibMSG.add_MESSAGE(omv);
		}

		return this;
	}


	public RB_Email _save_and_send() throws myException{
		_save(null);
		if(omv.get_bIsOK()){
			_send();
		}else{
			bibMSG.add_MESSAGE(omv);
		}
		return this;
	}

	public RB_Email _save_and_send(SEND_TYPE eTyp) throws myException{
		this.mail.setMailTyp(eTyp);
		this._save_and_send();
		return this;
	}

	
	public RB_Email _save_and_send(MyE2_MessageVector mv) throws myException{
		_save(mv);
		if(omv.get_bIsOK()){
			_send();
		}else{
			bibMSG.add_MESSAGE(omv);
		}
		return this;
	}

	public RB_Email _save_and_send(SEND_TYPE eTyp, MyE2_MessageVector mv) throws myException{
		this.mail.setMailTyp(eTyp);
		this._save_and_send(mv);
		return this;
	}

	
	
	public RB_Email _send() throws myException{
		if(omv.get_bIsOK()){
			String id = bibDB.EinzelAbfrage(this.mail.getMailId());
			RECORD_EMAIL_SEND_ext rec_4_send = new RECORD_EMAIL_SEND_ext(new RECORD_EMAIL_SEND(id));
			rec_4_send.Send_Email(null, 
					rec_4_send.get_MUST_BE_ATTACHMENT_LIST(omv));

		}	
		this.omv.clear();
		return this;
	}


	private void save_new_in_db() {
		Vector<String> stmtVector1 = new Vector<>();
		Vector<String> stmtVector2 = new Vector<>();

		try {
			RECORDNEW_EMAIL_SEND new_record_email_send = new RECORDNEW_EMAIL_SEND();

			new_record_email_send.set_NEW_VALUE_BETREFF(this.mail.getBetreff());

			new_record_email_send.set_NEW_VALUE_TEXT(this.mail.getText());
			new_record_email_send.set_NEW_VALUE_SEND_TYPE(this.mail.getMailTyp().db_val());
			new_record_email_send.set_NEW_VALUE_SENDER_ADRESS(this.mail.getAbsender());

			if( S.isFull(this.mail.get_table_base_name()) && S.isFull(this.mail.get_id_table()) ){
				new_record_email_send.set_NEW_VALUE_TABLE_BASE_NAME(this.mail.get_table_base_name());
				new_record_email_send.set_NEW_VALUE_ID_TABLE(this.mail.get_id_table());
			}

			MySqlStatementBuilder stmt_email_send = new_record_email_send.get__StatementBuilder(false,false);
			stmt_email_send.addSQL_Paar(_TAB.email_send.keyFieldName(), _TAB.email_send.seq_nextval());
			stmt_email_send.set_cTableName(EMAIL_SEND.fullTabName());

			stmtVector1.add(stmt_email_send.get_CompleteInsertString(_TAB.email_send.fullTableName()));

			stmtVector1.addAll( insert_ziel_adresse_in_db("") );

			stmtVector2.addAll( insert_anlage_in_db("") );

			if(omv.get_bIsOK()){
				for(String stmt: stmtVector1){
					if(! bibDB.ExecSQL(stmt, false)){
						omv._addAlarm("#--#Insert in DB Failed, check SQL statement:" +stmt);
					}
				}
			}

			if(omv.get_bIsOK()){
				for(String stmt: stmtVector2){
					if(! bibDB.ExecSQL(stmt, false)){
						omv._addAlarm("#--#Insert in DB Failed, check SQL statement:" +stmt);
					}
				}
			}

			if(omv.get_bIsOK()){
				this.mail.setMailId(_TAB.email_send.sql_currval());
				bibDB.Commit();
			}else{
				bibDB.Rollback();
			}

		}catch (myException e) {
			e.printStackTrace();
			omv.add_MESSAGE(new MyE2_Alarm_Message(e.getLocalizedMessage(),false), false);
			//            omv.add_MESSAGE(new MyE2_Alarm_Message(cSQL_MIT_Fehler,false), false);

			DEBUG.System_println("---------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
			DEBUG.System_println("----- SQL-EXECUTION-ERROR -------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
			DEBUG.System_println("----- Error: "+e.getLocalizedMessage(), DEBUG.DEBUG_FLAG_SQL_ERROR);
			//            DEBUG.System_println(cSQL_MIT_Fehler, DEBUG.DEBUG_FLAG_SQL_ERROR);
			DEBUG.System_println("---------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
			bibDB.Rollback();
		}

	}

	private Vector<String> insert_ziel_adresse_in_db(String id_to_update) throws myException {
		boolean count_aktiv = false;
		int pos = 1;
		if(this.mail.getZielAdresseListe().size()>1){
			pos = 0;
			count_aktiv = true;
		}

		Vector<String> stmtVector = new Vector<>();

		for(String zielAdresse: this.mail.getZielAdresseListe()){
			RECORDNEW_EMAIL_SEND_TARGETS new_record_email_send_targets = new RECORDNEW_EMAIL_SEND_TARGETS();
			new_record_email_send_targets.set_NEW_VALUE_SEND_OK("N");
			new_record_email_send_targets.set_NEW_VALUE_TARGET_ADRESS(zielAdresse);
			new_record_email_send_targets.set_NEW_VALUE_POS(""+pos);

			MySqlStatementBuilder stmt_email_send_target = new_record_email_send_targets.get__StatementBuilder(false, true);
			if(S.isEmpty(id_to_update)){
				stmt_email_send_target.addSQL_Paar(EMAIL_SEND_TARGETS.id_email_send.fieldName(), _TAB.email_send.seq_currval());
			}else{
				stmt_email_send_target.addSQL_Paar(EMAIL_SEND_TARGETS.id_email_send.fieldName(), id_to_update);
			}
			stmt_email_send_target.set_cTableName(EMAIL_SEND_TARGETS.fullTabName());

			stmtVector.add(stmt_email_send_target.get_CompleteInsertString(EMAIL_SEND_TARGETS.fullTabName()));

			if(count_aktiv){
				pos++;
			}	
		}
		return stmtVector;
	}

	private Vector<String> insert_anlage_in_db(String id_to_update) throws myException {
		Vector<String> stmtVector = new Vector<String>();
		for(RECORD_ARCHIVMEDIEN anlage: this.mail.getAnlageVector()){
			RECORDNEW_EMAIL_SEND_ATTACH new_record_email_send_attach = new RECORDNEW_EMAIL_SEND_ATTACH();
			new_record_email_send_attach.set_NEW_VALUE_ID_ARCHIVMEDIEN(anlage.get_ID_ARCHIVMEDIEN_cUF_NN(""));

			MySqlStatementBuilder stmt_email_send_attach = new_record_email_send_attach.get__StatementBuilder(false, true);
			if(S.isEmpty(id_to_update)){
				stmt_email_send_attach.addSQL_Paar(EMAIL_SEND_ATTACH.id_email_send.fieldName(), _TAB.email_send.seq_currval());
			}else{
				stmt_email_send_attach.addSQL_Paar(EMAIL_SEND_ATTACH.id_email_send.fieldName(), id_to_update);
			}
			stmt_email_send_attach.set_cTableName(EMAIL_SEND_ATTACH.fullTabName());

			stmtVector.add(stmt_email_send_attach.get_CompleteInsertString(EMAIL_SEND_ATTACH.fullTabName()));
		}
		return stmtVector;
	}

	private void save_update_in_db() throws myException{
		Vector<String> sql_update_email_send 	= new Vector<>();
		Vector<String> sql_update_ziel_adresse 	= new Vector<>();
		Vector<String> sql_update_anlage 		= new Vector<>();

		try {
			if(omv.get_bIsOK()){

				String mail_id_4_update =  mail_record_4_update.get_ID_EMAIL_SEND_cUF_NN("");

				this.mail_record_4_update.set_NEW_VALUE_BETREFF(this.mail.getBetreff());
				this.mail_record_4_update.set_NEW_VALUE_SENDER_ADRESS(this.mail.getAbsender());
				this.mail_record_4_update.set_NEW_VALUE_SEND_TYPE(this.mail.getMailTyp().db_val());
				this.mail_record_4_update.set_NEW_VALUE_TEXT(this.mail.getText());

				if( S.isFull(this.mail.get_table_base_name()) && S.isFull(this.mail.get_id_table()) ){
					this.mail_record_4_update.set_NEW_VALUE_ID_TABLE(this.mail.get_id_table());
					this.mail_record_4_update.set_NEW_VALUE_TABLE_BASE_NAME(this.mail.get_table_base_name());
				}

				sql_update_email_send.add(mail_record_4_update.get_SQL_UPDATE_STATEMENT(null, true));

				if(this.mail.muss_update_ziel_adresse()){
					for(RECORD_EMAIL_SEND_TARGETS zielAdresseRecord : this.mail_record_4_update.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send()){
						sql_update_ziel_adresse.add(zielAdresseRecord.get_DELETE_STATEMENT());
					}

					sql_update_ziel_adresse.addAll(insert_ziel_adresse_in_db(mail_id_4_update));
				}

				if(this.mail.muss_update_anlage()){
					for(RECORD_EMAIL_SEND_ATTACH anlageRecord : this.mail_record_4_update.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send()){
						sql_update_anlage.addElement(anlageRecord.get_DELETE_STATEMENT());
					}

					sql_update_anlage.addAll(insert_anlage_in_db(mail_id_4_update));
				}

				if(omv.get_bIsOK()){
					for(String stmt: sql_update_email_send){
						if(! bibDB.ExecSQL(stmt, false)){
							omv._addAlarm("#--#Insert in DB Failed, check SQL statement:" +stmt);
						}
					}
				}

				if(omv.get_bIsOK()){
					for(String stmt: sql_update_ziel_adresse){
						if(! bibDB.ExecSQL(stmt, false)){
							omv._addAlarm("#--#Insert in DB Failed, check SQL statement:" +stmt);
						}
					}
				}

				if(omv.get_bIsOK()){
					for(String stmt: sql_update_anlage){
						if(! bibDB.ExecSQL(stmt, false)){
							omv._addAlarm("#--#Insert in DB Failed, check SQL statement:" +stmt);
						}
					}
				}

				if(omv.get_bIsOK()){
					this.mail.setMailId(_TAB.email_send.sql_currval());
					bibDB.Commit();
				}else{
					bibDB.Rollback();
				}

			}
		}catch (myException e) {
			e.printStackTrace();
			omv.add_MESSAGE(new MyE2_Alarm_Message(e.getLocalizedMessage(),false), false);
			//            omv.add_MESSAGE(new MyE2_Alarm_Message(cSQL_MIT_Fehler,false), false);

			DEBUG.System_println("---------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
			DEBUG.System_println("----- SQL-EXECUTION-ERROR -------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
			DEBUG.System_println("----- Error: "+e.getLocalizedMessage(), DEBUG.DEBUG_FLAG_SQL_ERROR);
			//            DEBUG.System_println(cSQL_MIT_Fehler, DEBUG.DEBUG_FLAG_SQL_ERROR);
			DEBUG.System_println("---------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
			bibDB.Rollback();
		}

	}

	private void do_nothing_in_db(){/**/}

	//TODO ---------------- PRUEFUNG METHODEN ----------------

	public RB_Email_dataModel getMail() {
		return mail;
	}

	private void email_konsistenz_pruefen() throws myException{
		if(neuEmail){
			if( S.isEmpty(mail.getBetreff()) ){
				omv.add(new MyE2_Alarm_Message("#--# KEIN BETREFF"));
			}
			if( S.isEmpty(mail.getAbsender()) ){
				omv.add(new MyE2_Alarm_Message("#--# KEIN ABSENDER"));
			}
			if( S.isEmpty(mail.getMailTyp().db_val())){
				omv.add(new MyE2_Alarm_Message("#--# KEIN TYP"));
			}


			if(mail.getZielAdresseListe().size()==0 && (mail.getMailTyp() == SEND_TYPE.SINGLE)){
				omv._addAlarm("#--# KEIN ZIEL ADRESSE");
			}
			if(mail.getZielAdresseListe().size()==0 && (mail.getMailTyp() == SEND_TYPE.CC)){
				omv._addAlarm("#--# KEIN CC ZIEL ADRESSE");
			}
			if(mail.getZielAdresseListe().size()==0 && (mail.getMailTyp() == SEND_TYPE.BCC)){
				omv._addAlarm("#--# KEIN BCC ZIEL ADRESSE");
			}

			if(S.isEmpty( mail.get_table_base_name()) && S.isFull(mail.get_id_table()) ){
				omv._addAlarm("#--# TABLE_BASE_NAME und ID_TABLE müssen gefüllt sein");
			}

			if(S.isFull( mail.get_table_base_name())  && S.isEmpty(mail.get_id_table()) ){
				omv._addAlarm("#--# TABLE_BASE_NAME und ID_TABLE müssen gefüllt sein");
			}

			if(mail.getAnlageVector().size()>0){
				for(RECORD_ARCHIVMEDIEN record: mail.getAnlageVector()){
					RECORD_ARCHIVMEDIEN_ext recExt = new RECORD_ARCHIVMEDIEN_ext(record);

					if(! recExt.get_bFileIsPhysicalExisting()){
						omv._addAlarm("#--# "+ recExt.get_DATEINAME_cUF_NN("") +" DOES NOT EXIST");
					}
					if(recExt.is_IST_ORIGINAL_YES() && mail.getZielAdresseListe().size()>1){
						omv._addAlarm("#--# MAIL ENTÄHLT ORIGINAL MEDIEN UND KANN NUR 1 ZIEL ADRESSE HABEN");
					}
				}
			}

		}else{
			if(loadedMail.getSendStatus()==SEND_STATUS.SEND_ALL || loadedMail.getSendStatus()== SEND_STATUS.SEND_PART){
				omv._addAlarm("#--#MAIL SCHON GESENDET");
			}

			if(this.mail.equals(loadedMail)){
				omv._addInfo("#--# KEIN AENDERUNG");
			}
		}
	}
}
